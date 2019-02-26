package com.nullspace.multitenant.models;

import com.nullspace.multitenant.models.exceptions.DuplicateModule;
import com.nullspace.multitenant.multitenant.Exceptions.NoTenantFilesFound;
import com.nullspace.multitenant.multitenant.Exceptions.TenantNotFound;
import com.nullspace.multitenant.multitenant.TenantResolver;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

@Getter
public class Tenant {
    private String url;
    private String id;
    private String username;
    private String password;
    private List<Module> modules;
    private TenantResolver tenantResolver;

    public Tenant(@Autowired TenantResolver tenantResolver, String id, String url, String username, String password) {
        this.tenantResolver = tenantResolver;
        this.url = url;
        this.id = id;
        this.username = username;
        this.password = password;
        this.modules = new ArrayList<>();
    }

    public Tenant(@Autowired TenantResolver tenantResolver,String id, String url, String username, String password, List<Module> modules) {
        this.tenantResolver = tenantResolver;
        this.url = url;
        this.id = id;
        this.username = username;
        this.password = password;
        this.modules = modules;
    }

    public boolean hasModule(Module module) {
        return modules.stream().anyMatch(m -> m == module);
    }

    public void addModule(Module module) throws DuplicateModule {
        if (hasModule(module)) {
            throw new DuplicateModule("Module " + module.name() + " has already been added to this tenant.");
        }

        modules.add(module);
        savePropertiesToFile();
    }

    private void savePropertiesToFile() {
        try {
            File file = tenantResolver.getTenantFileById(this.id);
            Properties properties = tenantResolver.getTenantPropertiesFromFile(file);
            if(!properties.getProperty("url").equals(this.url)) {
                properties.setProperty("url", url);
            }
            if(!properties.getProperty("id").equals(this.id)) {
                properties.setProperty("id", id);
            }
            if(!properties.getProperty("username").equals(this.username)) {
                properties.setProperty("username", username);
            }
            if(!properties.getProperty("password").equals(this.password)) {
                properties.setProperty("password", password);
            }

            var modulesArray = Arrays.stream(properties.getProperty("modules").split(",")).parallel().map(Module::valueOf);
            if(!modules.parallelStream().allMatch(m -> modulesArray.anyMatch(ma -> ma == m))) {
                properties.setProperty("modules", modules.parallelStream().map(Module::name).collect(Collectors.joining(",")));
            }

        } catch (TenantNotFound | NoTenantFilesFound e) {
            e.printStackTrace();
        }
    }
}
