package com.nullspace.multitenant.demo.multitenant;

import com.nullspace.multitenant.demo.models.TenantObject;
import com.nullspace.multitenant.demo.multitenant.Exceptions.FailedToLoadTenantFile;
import com.nullspace.multitenant.demo.multitenant.Exceptions.NoTenantFilesFound;
import com.nullspace.multitenant.demo.multitenant.Exceptions.TenantNotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import static com.nullspace.multitenant.demo.utils.Utils.joinArrayGeneric;

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
        File[] files = getTenantFilesFromPath(runtimePath);

        for (File propertyFile : files) {
            Properties tenantProperties = getTenantPropertiesFromFile(propertyFile);

            String name = tenantProperties.getProperty("url");
            if (tenantName.equals(name)) {
                return tenantProperties.getProperty("id");
            }
        }
        String msg = "[!] Any tenant property files not found at ./tenants/atRuntime folder!";
        log.error(msg);
        throw new TenantNotFound(msg);
    }

    public List<TenantObject> getListOfAvailableTenants() {
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

        List<TenantObject> tenants = new ArrayList<>();

        for (File file: tenantFiles) {
            Properties tenantProperties = getTenantPropertiesFromFile(file);
            tenants.add(new TenantObject(tenantProperties.getProperty("id"), tenantProperties.getProperty("url")));
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

    private Properties getTenantPropertiesFromFile(File propertyFile) {
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
