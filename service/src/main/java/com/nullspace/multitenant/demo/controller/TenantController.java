package com.nullspace.multitenant.demo.controller;

import com.nullspace.multitenant.demo.exceptions.InvalidDbPropertiesException;
import com.nullspace.multitenant.demo.models.requests.TenantRequest;
import com.nullspace.multitenant.demo.multitenant.MultiTenantManager;
import com.nullspace.multitenant.demo.multitenant.TenantNotFoundException;
import com.nullspace.multitenant.demo.multitenant.TenantResolvingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

		String url = tenantRequest.getUrl();
		String username = tenantRequest.getUsername();
		String password = tenantRequest.getPassword();

		if (url == null || username == null || password == null) {
			log.error("[!] Received database params are incorrect or not full!");
			throw new InvalidDbPropertiesException();
		}

		tenantManager.createTenantDb(url, username, password);
		log.info("[i] Loaded DataSource for tenant '{}'.", url);
		return ResponseEntity.ok(tenantRequest);
	}
}
