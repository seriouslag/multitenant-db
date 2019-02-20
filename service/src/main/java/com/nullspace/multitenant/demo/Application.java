package com.nullspace.multitenant.demo;

import com.nullspace.multitenant.demo.multitenant.Exceptions.NoTenantFilesFound;
import com.nullspace.multitenant.demo.multitenant.MultiTenantManager;
import com.nullspace.multitenant.demo.multitenant.TenantResolver;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import static java.lang.String.format;

@Slf4j
@SpringBootApplication
public class Application {

	@Value("${tenantStartupPath}")
	private String startupPath;
	
	private final MultiTenantManager tenantManager;
	private final TenantResolver tenantResolver;
	
	public Application(MultiTenantManager tenantManager, TenantResolver tenantResolver) {
		this.tenantManager = tenantManager;
		this.tenantResolver = tenantResolver;
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

		// Getting tenant property files
		File[] files = new File[0];
		try {
			files = tenantResolver.getTenantFilesFromPath(startupPath);
		} catch (NoTenantFilesFound noTenantFilesFound) {
			log.warn("No tenant files were found at path: " + startupPath);
		}

		for (File propertyFile : files) {
			// Getting tenant properties out of files
			Properties tenantProperties = new Properties();
			tenantProperties.load(new FileInputStream(propertyFile));

			String tenantId = tenantProperties.getProperty("id");
			String url = tenantProperties.getProperty("url");
			String username = tenantProperties.getProperty("username");
			String password = tenantProperties.getProperty("password");

			try {
				// Loading tenant
				tenantManager.addTenant(url, username, password);
				log.info("[i] Loaded DataSource for tenant '{}'.", tenantId);
			} catch (SQLException e) {
				log.error(format("[!] Could not load DataSource for tenant '%s'!", tenantId), e);
			}
		}
	}
}
