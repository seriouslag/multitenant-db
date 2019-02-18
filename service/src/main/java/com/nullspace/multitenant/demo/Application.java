package com.nullspace.multitenant.demo;

import com.nullspace.multitenant.demo.multitenant.MultiTenantManager;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Properties;

import static java.lang.String.format;

@Slf4j
@SpringBootApplication
public class Application {
	
	private final MultiTenantManager tenantManager;
	
	public Application(MultiTenantManager tenantManager) {
		this.tenantManager = tenantManager;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Load tenant datasource properties from the folder 'tenants/onStartUp`
	 * when the app has started.
	 */
	@SneakyThrows(IOException.class)
	@EventListener
	public void onReady(ApplicationReadyEvent event) {

		File[] files = Paths.get("tenants/onStartUp").toFile().listFiles();

		if (files == null) {
			log.warn("[!] Tenant property files not found at ./tenants/onStartUp folder!");
			return;
		}

		for (File propertyFile : files) {
			Properties tenantProperties = new Properties();
			tenantProperties.load(new FileInputStream(propertyFile));

			String tenantId = tenantProperties.getProperty("id");
			String url = tenantProperties.getProperty("url");
			String username = tenantProperties.getProperty("username");
			String password = tenantProperties.getProperty("password");

			try {
				tenantManager.addTenant(tenantId, url, username, password);
				log.info("[i] Loaded DataSource for tenant '{}'.", tenantId);
			} catch (SQLException e) {
				log.error(format("[!] Could not load DataSource for tenant '%s'!", tenantId), e);
			}
		}
	}
}
