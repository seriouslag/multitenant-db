package com.nullspace.multitenant.demo.multitenant;

import lombok.extern.slf4j.Slf4j;
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

    public DataSourceProperties resolveById(String tenantId) {
        File[] files = Paths.get("tenants/atRuntime").toFile().listFiles();

        if (files == null) {
            String msg = "[!] Tenant property files not found at ./tenants/atRuntime folder!";
            log.error(msg);
            throw new RuntimeException(msg);
        }

        for (File propertyFile : files) {
            Properties tenantProperties = new Properties();
            try {
                tenantProperties.load(new FileInputStream(propertyFile));
            } catch (IOException e) {
                String msg = "[!] Could not read tenant property file at ./tenants/atRuntime folder!";
                log.error(msg);
                throw new RuntimeException(msg, e);
            }

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
        throw new RuntimeException(msg);
    }

    public String getTenantsIdByName(String tenantName) {
        File[] files = Paths.get("tenants/atRuntime").toFile().listFiles();

        if (files == null) {
            String msg = "[!] Tenant property files not found at ./tenants/atRuntime folder!";
            log.error(msg);
            throw new RuntimeException(msg);
        }

        for (File propertyFile : files) {
            Properties tenantProperties = new Properties();
            try {
                tenantProperties.load(new FileInputStream(propertyFile));
            } catch (IOException e) {
                String msg = "[!] Could not read tenant property file at ./tenants/atRuntime folder!";
                log.error(msg);
                throw new RuntimeException(msg, e);
            }

            String name = tenantProperties.getProperty("url");
            if (tenantName.equals(name)) {
                return tenantProperties.getProperty("id");
            }
        }
        String msg = "[!] Any tenant property files not found at ./tenants/atRuntime folder!";
        log.error(msg);
        throw new RuntimeException(msg);
    }
}
