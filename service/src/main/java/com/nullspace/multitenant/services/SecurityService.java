package com.nullspace.multitenant.services;

import com.nullspace.multitenant.models.entities.User;
import com.nullspace.multitenant.multitenant.Exceptions.NoTenantFilesFound;
import com.nullspace.multitenant.multitenant.Exceptions.TenantNotFound;
import com.nullspace.multitenant.multitenant.Exceptions.TenantResolving;
import com.nullspace.multitenant.multitenant.MultiTenantManager;
import com.nullspace.multitenant.multitenant.TenantResolver;
import com.nullspace.multitenant.services.interfaces.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.sql.SQLException;

@Slf4j
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
        } catch (TenantNotFound | TenantResolving | SQLException e) {
            e.printStackTrace();
            log.error("Failed to load root tenant.");
        } catch (NoTenantFilesFound e) {
            e.printStackTrace();
            log.error("No tenant files were found!");
        }
        return false;
    }
}
