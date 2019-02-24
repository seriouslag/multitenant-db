package com.nullspace.multitenant.controllers;

import com.nullspace.multitenant.exceptions.InvalidDbPropertiesException;
import com.nullspace.multitenant.models.UserRoleName;
import com.nullspace.multitenant.models.entities.Authority;
import com.nullspace.multitenant.models.entities.User;
import com.nullspace.multitenant.models.requests.TenantRequest;
import com.nullspace.multitenant.multitenant.Exceptions.NoTenantFilesFound;
import com.nullspace.multitenant.multitenant.Exceptions.TenantNotFound;
import com.nullspace.multitenant.multitenant.Exceptions.TenantResolving;
import com.nullspace.multitenant.multitenant.MultiTenantManager;
import com.nullspace.multitenant.multitenant.TenantResolver;
import com.nullspace.multitenant.services.exceptions.AlreadyExists;
import com.nullspace.multitenant.services.interfaces.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/tenants")
public class TenantController {
	
	private final MultiTenantManager tenantManager;
	private final TenantResolver tenantResolver;
	private final IUserService userService;
	
	public TenantController(MultiTenantManager tenantManager, IUserService userService, TenantResolver tenantResolver) {
		this.tenantManager = tenantManager;
		this.userService = userService;
		this.tenantResolver = tenantResolver;
	}

	/**
	 * Get list of all tenants in the local storage
	 */
	@GetMapping
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(tenantResolver.getListOfAvailableTenants());
	}


	@RequestMapping("/{tenantId}")
	@PostMapping
	public ResponseEntity<?> load(@PathVariable String tenantId) {
		try {
			tenantManager.loadTenant(tenantId);
			return ResponseEntity.ok("Loaded tenant: " + tenantId);
		} catch (TenantResolving e) {
			log.error("Failed to resolve tenant!");
			e.printStackTrace();
		} catch (TenantNotFound e) {
			log.error("Failed to find tenant!");
			return ResponseEntity.notFound().build();
		} catch (SQLException e) {
			log.error("Failed to load tenant, sql error!");
			e.printStackTrace();
		} catch (NoTenantFilesFound e) {
			log.error("No tenant files were found!");
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
	@PreAuthorize("@securityService.isRoot(#principal)")
	public ResponseEntity<?> add(Principal principal, @RequestBody TenantRequest tenantRequest) {

		log.info("[i] Received add new tenant params request {}", tenantRequest);

		String url = tenantRequest.getUrl();
		String username = tenantRequest.getUsername();
		String password = tenantRequest.getPassword();

		// TODO change to accept list of root users
		String rootUserFirstName = tenantRequest.getRootUserFirstName();
		String rootUserLastName = tenantRequest.getRootUserLastName();
		String rootUserEmail = tenantRequest.getRootUserEmail();
		String rootUserPassword = tenantRequest.getRootUserPassword();

		if (url == null || username == null || password == null
				|| rootUserEmail == null || rootUserPassword == null
		) {
			log.error("[!] Received database params are incorrect or not full!");
			throw new InvalidDbPropertiesException();
		}

		Authority adminAuth = new Authority(UserRoleName.ROLE_ADMIN);
		List<Authority> adminAuthorities = new ArrayList<>();
		adminAuthorities.add(adminAuth);
		User rootUser = new User(rootUserFirstName, rootUserLastName, rootUserEmail, rootUserPassword);
		rootUser.setAuthorities(adminAuthorities);

		String id = tenantManager.createTenantDb(url, username, password);
		try {
			tenantManager.setCurrentTenant(id);
			System.out.println("Added root user to new tenant");
			userService.save(rootUser);
		} catch (SQLException | TenantResolving | TenantNotFound | NoTenantFilesFound e) {
			System.out.println("Failed to set current tenant to new tenant");
			e.printStackTrace();
		} catch (AlreadyExists alreadyExists) {
			System.out.println("Root user already exists");
			alreadyExists.printStackTrace();
		}
		log.info("[i] Loaded DataSource for tenant '{}'.", url);
		return ResponseEntity.ok(tenantRequest);
	}
}
