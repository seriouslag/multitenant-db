package com.nullspace.multitenant.demo.controller;

import com.nullspace.multitenant.demo.exception.InvalidDbPropertiesException;
import com.nullspace.multitenant.demo.exception.LoadDataSourceException;
import com.nullspace.multitenant.demo.models.requests.TenantRequest;
import com.nullspace.multitenant.demo.multitenant.MultiTenantManager;
import com.nullspace.multitenant.demo.multitenant.TenantNotFoundException;
import com.nullspace.multitenant.demo.multitenant.TenantResolvingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.SQLException;

@Slf4j
@RestController
@RequestMapping("/tenants")
public class TenantController {
	
	private final MultiTenantManager tenantManager;
	
	public TenantController(MultiTenantManager tenantManager) {
		this.tenantManager = tenantManager;
	}

	/**
	 * Get list of all tenants in the local storage
	 */
	@GetMapping
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(tenantManager.getTenantList());
	}

	@RequestMapping("/{tenantId}")
	@PostMapping
	public ResponseEntity<?> load(@PathVariable String tenantId) {
		try {
			tenantManager.loadTenant(tenantId);
			return ResponseEntity.ok("Loaded tenant: " + tenantId);
		} catch (TenantResolvingException e) {
			e.printStackTrace();
		} catch (TenantNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(500).build();
	}

	/**
	 * Add the new tenant on the fly
	 *
	 * @param tenantRequest Map with tenantId and related datasource properties
	 */
	@PostMapping
	public ResponseEntity<?> add(@RequestBody TenantRequest tenantRequest) {
		
		log.info("[i] Received add new tenant params request {}", tenantRequest);
		
		String tenantId = tenantRequest.getId();
		String url = tenantRequest.getUrl();
		String username = tenantRequest.getUsername();
		String password = tenantRequest.getPassword();
		
		if (tenantId == null || url == null || username == null || password == null) {
			log.error("[!] Received database params are incorrect or not full!");
			throw new InvalidDbPropertiesException();
		}

		try {
			File file = new File("./tenants/onStartUp/" + tenantId + ".properties");
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
				System.out.println("Tenant file already exists.");
			}
		} catch (Exception e) {
			System.out.println("Tenant file failed to create");
		}
		
		try {
			tenantManager.addTenant(tenantId, url, username, password);
			log.info("[i] Loaded DataSource for tenant '{}'.", tenantId);
			return ResponseEntity.ok(tenantRequest);
		} catch (SQLException e) {
			throw new LoadDataSourceException(e);
		}
	}
}
