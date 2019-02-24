package com.nullspace.multitenant.multitenant;

import com.nullspace.multitenant.exceptions.InvalidDbPropertiesException;
import com.nullspace.multitenant.exceptions.InvalidTenantIdExeption;
import com.nullspace.multitenant.multitenant.Exceptions.NoTenantFilesFound;
import com.nullspace.multitenant.multitenant.Exceptions.TenantNotFound;
import com.nullspace.multitenant.multitenant.Exceptions.TenantResolving;
import com.nullspace.multitenant.utils.Cuid;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.sql.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.String.format;

@Slf4j
@Configuration
public class MultiTenantManager {

	private final ThreadLocal<String> currentTenant = new ThreadLocal<>();
	private final Map<Object, Object> tenantDataSources = new ConcurrentHashMap<>();
	private final DataSourceProperties properties;

	private final TenantResolver tenantResolver;

    @Value("${databaseBaseUrl}")
	private String DATABASE_BASE_URL;

	private static final String MSG_INVALID_TENANT_ID = "[!] DataSource not found for given tenant Id '{}'!";
	private static final String MSG_INVALID_DB_PROPERTIES_ID = "[!] DataSource properties related to the given tenant ('{}') is invalid!";
	private static final String MSG_RESOLVING_TENANT_ID = "[!] Could not resolve tenant ID '{}'!";
	private static final String MSG_NO_TENANT_FILES_FOUND = "[!] Could not find tenant files '{}'!";

	@Value("${tenantRuntimePath}")
	private String runtimePath;

	@Value("${rootTenantName}")
	private String rootTenantName;

	private AbstractRoutingDataSource multiTenantDataSource;

	public MultiTenantManager(DataSourceProperties properties, TenantResolver tenantResolver) {
		this.properties = properties;
		this.tenantResolver = tenantResolver;
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

	public void loadTenant(String tenantId) throws TenantResolving, TenantNotFound, SQLException, NoTenantFilesFound {
		if (tenantIsAbsent(tenantId)) {
			if (tenantResolver != null) {
				DataSourceProperties properties = tenantResolver.resolveById(tenantId);

				try {
					log.debug("[d] Datasource properties resolved for tenant ID '{}'", tenantId);
				} catch (Exception e) {
					throw new TenantResolving(e, "Could not resolve the tenant!");
				}

				String url = properties.getUrl();
				String username = properties.getUsername();
				String password = properties.getPassword();

				addTenant(url, username, password);
			} else {
				throw new TenantNotFound(format("Tenant %s not found!", tenantId));
			}
		}
	}

	public void setCurrentTenant(String tenantId) throws SQLException, TenantNotFound, TenantResolving, NoTenantFilesFound {
		loadTenant(tenantId);
		currentTenant.set(tenantId);
		log.debug("[d] Tenant '{}' set as current.", tenantId);
	}

	public String getTenantsIdByName(String name) throws TenantNotFound, NoTenantFilesFound {
		return tenantResolver.getTenantsIdByName(name);
	}

	//returns id of created tenant
	public String createTenantDb(String url, String username, String password) {
		ResultSet databaseExistsResponse = tenantDbExists(url);

		// if databaseExists response is empty then create that database
		try {
			if(databaseExistsResponse.next()) {
				System.out.println("Tenant already exists! Not creating new one.");
				return tenantResolver.getTenantsIdByName(url);
			} else {
				System.out.println("Tenant not found! Creating new one.");

				System.out.println("Tenant does not exist, creating new tenant.");
				String tenantId = Cuid.createCuid();

				try {
					File file = new File(runtimePath + url + ".properties");
					if (file.exists()) {
						System.out.println("Tenant file already exists.");
					} else {

						BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
						writer.write("id=" + tenantId);
						writer.newLine();
						writer.write("url=" + url);
						writer.newLine();
						writer.write("username=" + username);
						writer.newLine();
						writer.write("password=" + password);
						writer.flush();
						writer.close();
						if (file.canRead()) {
							System.out.println("New tenant file is created!");
						} else {
							System.out.println("Tenant file failed to be created!");
						}
					}
				} catch (Exception e) {
					System.out.println("Tenant file failed to create");
				}

				String sqlDb = loadSqlFromFile("sql/newDb.sql");
				sqlDb = sqlDb.replace("*TENENTNAME*", url);
				executeSql(sqlDb);

				String sqlTables = loadSqlFromFile("sql/dbTables.sql");
				executeBatchSqlOnDb(sqlTables, url);
				return tenantId;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			log.error("Tenant failed to create!");
		} catch (TenantNotFound e) {
			e.printStackTrace();
			log.error("Tenant failed to create! - 2");
		} catch (NoTenantFilesFound e) {
			e.printStackTrace();
			log.error("No tenant files where found!");
		}
		return "";
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

	private void executeBatchSqlOnDb(String sql, String url) {
		try {
			Connection connection = DriverManager.getConnection(DATABASE_BASE_URL + url, "postgres", "postgres");
			Statement statement = connection.createStatement();

			List<String> list = new ArrayList<>(Arrays.asList(sql.split(";")));

			for (String sqlStatement :
					list) {
				statement.addBatch(sqlStatement.trim() + "");
			}

			statement.executeBatch();

		} catch (Exception e) {
			log.error("Failed to execute sql: " + sql);
		}
	}

	private ResultSet executeSql(String sql) {
		try {
			Connection connection = DriverManager.getConnection(DATABASE_BASE_URL, "postgres", "postgres");
			Statement statement = connection.createStatement();
			statement.execute(sql);
			return statement.getResultSet();

		} catch (Exception e) {
			log.error("Failed to execute sql: " + sql);
		}
		// throw exceptions
		return null;
	}

	private ResultSet tenantDbExists(String tenantName) {
		String sql = loadSqlFromFile("sql/dbExists.sql");
		StringBuilder sb = new StringBuilder();
		Formatter fmt = new Formatter(sb);
		fmt.format(sql, tenantName);
		String formattedSql = fmt.toString();
		return executeSql(formattedSql);
	}

	public void addTenant(String url, String username, String password) throws SQLException {
		String tenantId = createTenantDb(url, username, password);

		// Load datasource
		DataSource dataSource = DataSourceBuilder.create()
				.driverClassName(properties.getDriverClassName())
				.url(DATABASE_BASE_URL + url)
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

	private boolean tenantIsAbsent(String tenantId) {
		return !tenantDataSources.containsKey(tenantId);
	}

	public Collection<Object> getTenantList() {
		return tenantDataSources.keySet();
	}

	private HikariDataSource defaultDataSource() {
		HikariDataSource defaultDataSource = new HikariDataSource();
		defaultDataSource.setDriverClassName("org.h2.Driver");
		defaultDataSource.setJdbcUrl("jdbc:h2:mem:default");
		defaultDataSource.setUsername("default");
		defaultDataSource.setPassword("default");
		return defaultDataSource;
	}

	public void setTenant(String tenantId) {
		try {
			setCurrentTenant(tenantId);
		} catch (SQLException e) {
			log.error(MSG_INVALID_DB_PROPERTIES_ID, tenantId);
			throw new InvalidDbPropertiesException();
		} catch (TenantNotFound e) {
			log.error(MSG_INVALID_TENANT_ID, tenantId);
			throw new InvalidTenantIdExeption();
		} catch (TenantResolving e) {
			log.error(MSG_RESOLVING_TENANT_ID, tenantId);
			throw new InvalidTenantIdExeption();
		} catch (NoTenantFilesFound e) {
			log.error(MSG_NO_TENANT_FILES_FOUND, tenantId);
			e.printStackTrace();
		}
	}
}
