package com.nullspace.multitenant.multitenant;

import com.nullspace.multitenant.models.Module;
import com.nullspace.multitenant.models.responses.Tenant;
import com.nullspace.multitenant.multitenant.Exceptions.FailedToLoadTenantFile;
import com.nullspace.multitenant.multitenant.Exceptions.NoTenantFilesFound;
import com.nullspace.multitenant.multitenant.Exceptions.TenantNotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

import static com.nullspace.multitenant.utils.Utils.joinArrayGeneric;

@Slf4j
@Component
public class TenantResolver {

    @Value("${tenantRuntimePath}")
    private String runtimePath;

    @Value("${tenantStartupPath}")
    private String startupPath;

    public DataSourceProperties resolveById(String tenantId) throws TenantNotFound, NoTenantFilesFound {
        File[] files = getTenantFilesFromPath(runtimePath);

        for (File propertyFile : files) {
            Properties tenantProperties = getTenantPropertiesFromFile(propertyFile);

            String id = tenantProperties.getProperty("id");
            if (tenantId.equals(id)) {
                DataSourceProperties properties = new DataSourceProperties();
                properties.setUrl(tenantProperties.getProperty("url"));
                properties.setUsername(tenantProperties.getProperty("username"));
                properties.setPassword(tenantProperties.getProperty("password"));
                return properties;
            }
        }
        String msg = "[!] Any tenant property files not found at ./tenants/atRuntime folder!";
        log.error(msg);
        throw new TenantNotFound(msg);
    }

    public String getTenantsIdByName(String tenantName) throws TenantNotFound, NoTenantFilesFound {
        return getTenantByName(tenantName).getProperty("id");
    }

    public File getTenantFileById(String id) throws NoTenantFilesFound, TenantNotFound {
        File[] files = getTenantFilesFromPath(runtimePath);

        for (File propertyFile : files) {
            Properties tenantProperties = getTenantPropertiesFromFile(propertyFile);

            String fileId = tenantProperties.getProperty("id");

            if(fileId.equals(id)) {
                return propertyFile;
            }
        }

        String msg = "[!] Any tenant property files not found at ./tenants/atRuntime folder!";
        throw new TenantNotFound(msg);
    }

    public Properties getTenantByProperty(String property, String value) throws NoTenantFilesFound, TenantNotFound {
        File[] files = getTenantFilesFromPath(runtimePath);
        for (File propertyFile : files) {
            Properties tenantProperties = getTenantPropertiesFromFile(propertyFile);

            String tenantValue = tenantProperties.getProperty(property);
            if (value.equals(tenantValue)) {
                return tenantProperties;
            }
        }
        String msg = "[!] Any tenant property files not found at ./tenants/atRuntime folder!";
        log.error(msg);
        throw new TenantNotFound(msg);
    }

    public Properties getTenantByName(String tenantName) throws TenantNotFound, NoTenantFilesFound {
        return getTenantByProperty("url", tenantName);
    }

    public Properties getTenantById(String tenantId) throws NoTenantFilesFound, TenantNotFound {
        return getTenantByProperty("id", tenantId);
    }

    public List<Tenant> getListOfAvailableTenants() {
        File[] runtime = new File[0];
        File[] startup = new File[0];
        try {
           runtime = getTenantFilesFromPath(runtimePath);
        } catch (NoTenantFilesFound e) {
            String msg = "[!] Tenant property files not found at " + runtimePath + "!";
            log.warn(msg);
        }
        try {
           startup = getTenantFilesFromPath(startupPath);
        } catch (NoTenantFilesFound e) {
            String msg = "[!] Tenant property files not found at " + startupPath + "!";
            log.warn(msg);
        }

        if(runtime.length == 0 && startup.length == 0) {
            return Collections.emptyList();
        }

        File[] tenantFiles = joinArrayGeneric(runtime, startup);

        List<Tenant> tenants = new ArrayList<>();

        for (File file: tenantFiles) {
            Properties tenantProperties = getTenantPropertiesFromFile(file);
            tenants.add(new Tenant(tenantProperties.getProperty("id"), tenantProperties.getProperty("url")));
        }

        return tenants;
    }

    public File[] getTenantFilesFromPath(String path) throws NoTenantFilesFound {
        // only get the files that end with .properties
        File[] files = Paths.get(path)
                                .toFile()
                                .listFiles(pathname -> pathname.canRead() && pathname.getPath().endsWith(".properties"));

        if (files == null) {
             throw new NoTenantFilesFound("No tenant files at path: " + path);
        }

        return files;
    }

    public boolean checkIfTenantHasModule(String tenantName, Module module) throws NoTenantFilesFound, TenantNotFound {
        Properties properties = getTenantByName(tenantName);
        var modules = properties.getProperty("modules");

        var modulesArray = Arrays.asList(modules.split(",")).parallelStream().map(Module::valueOf).toArray();

        for(var m : modulesArray) {
            if(m == module) {
                return true;
            }
        }

        return false;
    }

    public Properties getTenantPropertiesFromFile(File propertyFile) {
        Properties tenantProperties = new Properties();
        try {
            tenantProperties.load(new FileInputStream(propertyFile));
        } catch (IOException e) {
            String msg = "[!] Could not read tenant property file at " + propertyFile.getPath() + " folder!";
            log.error(msg);
            throw new FailedToLoadTenantFile(msg, e);
        }

        return tenantProperties;
    }
}
