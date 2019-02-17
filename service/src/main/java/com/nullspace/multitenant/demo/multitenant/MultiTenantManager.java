package com.nullspace.multitenant.demo.multitenant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import static java.lang.String.format;

@Slf4j
@Configuration
public class MultiTenantManager {

	private final ThreadLocal<String> currentTenant = new ThreadLocal<>();
	private final Map<Object, Object> tenantDataSources = new ConcurrentHashMap<>();
	private final DataSourceProperties properties;

	private Function<String, DataSourceProperties> tenantResolver;
	private final static String DATABASE_URL = "jdbc:postgresql://localhost:5432/";

	private AbstractRoutingDataSource multiTenantDataSource;

	public MultiTenantManager(DataSourceProperties properties) {
		this.properties = properties;
	}

	@Bean
	public DataSource dataSource() {
		multiTenantDataSource = new AbstractRoutingDataSource() {
			@Override
			protected Object determineCurrentLookupKey() {
				return currentTenant.get();
			}
		};
		multiTenantDataSource.setTargetDataSources(tenantDataSources);
		multiTenantDataSource.setDefaultTargetDataSource(defaultDataSource());
		multiTenantDataSource.afterPropertiesSet();
		return multiTenantDataSource;
	}

	public void setTenantResolver(Function<String, DataSourceProperties> tenantResolver) {
		this.tenantResolver = tenantResolver;
	}

	public void loadTenant(String tenantId) throws TenantResolvingException, TenantNotFoundException, SQLException {
		if (tenantIsAbsent(tenantId)) {
			if (tenantResolver != null) {
				DataSourceProperties properties = tenantResolver.apply(tenantId);

				try {
					properties = tenantResolver.apply(tenantId);
					log.debug("[d] Datasource properties resolved for tenant ID '{}'", tenantId);
				} catch (Exception e) {
					throw new TenantResolvingException(e, "Could not resolve the tenant!");
				}

				String url = properties.getUrl();
				String username = properties.getUsername();
				String password = properties.getPassword();

				addTenant(tenantId, url, username, password);
			} else {
				throw new TenantNotFoundException(format("Tenant %s not found!", tenantId));
			}
		}
	}

	public void setCurrentTenant(String tenantId) throws SQLException, TenantNotFoundException, TenantResolvingException {
		loadTenant(tenantId);
		currentTenant.set(tenantId);
		log.debug("[d] Tenant '{}' set as current.", tenantId);
	}


	private ResultSet createTenantDb(String tenantName) {
		String sql = loadSqlFromFile("sql/newDb.sql");
		sql = sql.replace("*TENENTNAME*", tenantName);
		return executeSql(sql);
	}

	private String loadSqlFromFile(String filePath) {
		try {
			Resource resource = new ClassPathResource(filePath);
			InputStream resourceInputStream = resource.getInputStream();

			// https://stackoverflow.com/questions/309424/how-do-i-read-convert-an-inputstream-into-a-string-in-java
			try(java.util.Scanner s = new java.util.Scanner(resourceInputStream)) {
				return s.useDelimiter("\\A").hasNext() ? s.next() : "";
			}
		} catch (Exception e) {
			log.error("Failed to load sql file for tenant create");
		}

		// replace with exception
		return "";
	}

	private ResultSet executeSql(String sql) {
		try {
			Connection connection = DriverManager.getConnection(DATABASE_URL, "postgres", "postgres");
			Statement statement = connection.createStatement();
			statement.execute(sql);
			return statement.getResultSet();

		} catch (Exception e) {
			log.error("Failed to execute sql: " + sql);
		}
		// throw exception
		return null;
	}

	private ResultSet tenantDbExists(String tenantName) {
		String sql = loadSqlFromFile("sql/dbExists.sql");
		sql = sql.replace("*TENANTNAME*", tenantName);
		return executeSql(sql);
	}

	public void addTenant(String tenantId, String tenantName, String username, String password) throws SQLException {

		ResultSet databaseExistsResponse = tenantDbExists(tenantName);

		// if databaseExitst response is empty then create that database
		if(!databaseExistsResponse.next()) {
			createTenantDb(tenantName);
		}

		// Load datasource

		DataSource dataSource = DataSourceBuilder.create()
				.driverClassName(properties.getDriverClassName())
				.url(DATABASE_URL + tenantName)
				.username(username)
				.password(password)
				.build();

		// Check that new connection is 'live'. If not - throw exception
		try(Connection connection = dataSource.getConnection()) {
			tenantDataSources.put(tenantId, dataSource);
			multiTenantDataSource.afterPropertiesSet();
			log.debug("[d] Tenant '{}' added.", tenantId);
		}
	}

	public DataSource removeTenant(String tenantId) {
		Object removedDataSource = tenantDataSources.remove(tenantId);
		multiTenantDataSource.afterPropertiesSet();
		return (DataSource) removedDataSource;
	}

	public boolean tenantIsAbsent(String tenantId) {
		return !tenantDataSources.containsKey(tenantId);
	}

	public Collection<Object> getTenantList() {
		return tenantDataSources.keySet();
	}

	private DriverManagerDataSource defaultDataSource() {
		DriverManagerDataSource defaultDataSource = new DriverManagerDataSource();
		defaultDataSource.setDriverClassName("org.h2.Driver");
		defaultDataSource.setUrl("jdbc:h2:mem:default");
		defaultDataSource.setUsername("default");
		defaultDataSource.setPassword("default");
		return defaultDataSource;
	}
}
