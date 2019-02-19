package com.nullspace.multitenant.demo.controllers;

import com.nullspace.multitenant.demo.exceptions.InvalidDbPropertiesException;
import com.nullspace.multitenant.demo.models.UserRoleName;
import com.nullspace.multitenant.demo.models.entities.Authority;
import com.nullspace.multitenant.demo.models.entities.User;
import com.nullspace.multitenant.demo.models.requests.TenantRequest;
import com.nullspace.multitenant.demo.multitenant.MultiTenantManager;
import com.nullspace.multitenant.demo.multitenant.TenantNotFoundException;
import com.nullspace.multitenant.demo.multitenant.TenantResolvingException;
import com.nullspace.multitenant.demo.service.interfaces.IUserService;
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
@RequestMapping("/tenants")
public class TenantController {
	
	private final MultiTenantManager tenantManager;
	private final IUserService userService;
	
	public TenantController(MultiTenantManager tenantManager, IUserService userService) {
		this.tenantManager = tenantManager;
		this.userService = userService;
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
		} catch (SQLException | TenantResolvingException | TenantNotFoundException e) {
			System.out.println("Failed to set current tenant to new tenant");
			e.printStackTrace();
		}
		log.info("[i] Loaded DataSource for tenant '{}'.", url);
		return ResponseEntity.ok(tenantRequest);
	}
}
