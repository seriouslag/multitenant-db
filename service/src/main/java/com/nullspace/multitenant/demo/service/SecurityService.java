package com.nullspace.multitenant.demo.service;

import com.nullspace.multitenant.demo.models.entities.User;
import com.nullspace.multitenant.demo.multitenant.MultiTenantManager;
import com.nullspace.multitenant.demo.multitenant.TenantNotFoundException;
import com.nullspace.multitenant.demo.multitenant.TenantResolver;
import com.nullspace.multitenant.demo.multitenant.TenantResolvingException;
import com.nullspace.multitenant.demo.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.sql.SQLException;

@Service
public class SecurityService {
    private final IUserService userService;
    private final TenantResolver tenantResolver;
    private final MultiTenantManager tenantManager;

    @Value("${rootTenantName}")
    private String rootTenantName;

    @Autowired
    public SecurityService(IUserService userService, TenantResolver tenantResolver, MultiTenantManager tenantManager) {
        this.userService = userService;
        this.tenantResolver = tenantResolver;
        this.tenantManager = tenantManager;
    }

    public boolean isUser(Principal principal, Long userId) {
        User user = userService.findByUsername(principal.getName());

        return user.getId().equals(userId);
    }

    public boolean isRoot(Principal principal) {
        try {
            tenantManager.setCurrentTenant(tenantResolver.getTenantsIdByName(rootTenantName));
            User user = userService.findByUsername(principal.getName());

            if(user == null) {
                return false;
            }

            boolean isAdmin = false;
            for(GrantedAuthority authority: user.getAuthorities()) {
                if(authority.getAuthority().equals("ROLE_ADMIN")) {
                    isAdmin = true;
                }
            }

            return isAdmin;
        } catch (TenantNotFoundException | TenantResolvingException | SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to load root tenant.");
        }
        return false;
    }
}
