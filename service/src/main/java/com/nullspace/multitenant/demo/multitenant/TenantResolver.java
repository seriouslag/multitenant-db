package com.nullspace.multitenant.demo.multitenant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

@Slf4j
@Component
public class TenantResolver {

    @Value("${tenantRuntimePath}")
    private String runtimePath;

    public DataSourceProperties resolveById(String tenantId) throws TenantNotFoundException {
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
        throw new TenantNotFoundException(msg);
    }

    public String getTenantsIdByName(String tenantName) throws TenantNotFoundException {
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
        throw new TenantNotFoundException(msg);
    }

    public File[] getTenantFilesFromPath(String path) {
        // only get the files that end with .properties
        File[] files = Paths.get(path)
                                .toFile()
                                .listFiles(pathname -> pathname.canRead() && pathname.getPath().endsWith(".properties"));

        if (files == null) {
            String msg = "[!] Tenant property files not found at " + path + "!";
            log.error(msg);
            throw new RuntimeException(msg);
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
            throw new RuntimeException(msg, e);
        }

        return tenantProperties;
    }
}
