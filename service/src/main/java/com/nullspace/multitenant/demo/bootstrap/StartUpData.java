package com.nullspace.multitenant.demo.bootstrap;

import com.nullspace.multitenant.demo.models.UserRoleName;
import com.nullspace.multitenant.demo.models.entities.Authority;
import com.nullspace.multitenant.demo.models.entities.User;
import com.nullspace.multitenant.demo.multitenant.MultiTenantManager;
import com.nullspace.multitenant.demo.multitenant.TenantNotFoundException;
import com.nullspace.multitenant.demo.multitenant.TenantResolvingException;
import com.nullspace.multitenant.demo.service.interfaces.IUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class StartUpData implements CommandLineRunner {

    private final IUserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MultiTenantManager tenantManager;

    public StartUpData(IUserService userService, BCryptPasswordEncoder bCryptPasswordEncoder, MultiTenantManager tenantManager) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tenantManager = tenantManager;
    }

    @Override
    public void run(String... args) {
        loadData();
    }

    private void loadData() {
        loadTenant1();
        loadTenant2();
    }

    private void loadTenant1() {
        // setting data to tenant 1
        try {
            String id = tenantManager.createTenantDb("tenant1", "postgres", "postgres");
            tenantManager.addTenant(id, "tenant1", "postgres", "postgres");
            tenantManager.setCurrentTenant(tenantManager.getTenantsIdByName("tenant1"));
            // Roles
            Authority adminAuth = new Authority(UserRoleName.ROLE_ADMIN);
            List<Authority> adminAuthorities = new ArrayList<>();
            adminAuthorities.add(adminAuth);
            // End Roles

            User user = new User("Landon", "Gavin", "landongavin@example.com", bCryptPasswordEncoder.encode("test"), "landongavin@example.com");
            user.setAuthorities(adminAuthorities);

            userService.save(user);
        } catch (SQLException | TenantNotFoundException | TenantResolvingException e) {
            e.printStackTrace();
        }
    }

    private void loadTenant2() {
        // setting data to tenant 2
        try {
            String id = tenantManager.createTenantDb("tenant2", "postgres", "postgres");
            tenantManager.addTenant(id,"tenant2", "postgres", "postgres");
            tenantManager.setCurrentTenant(tenantManager.getTenantsIdByName("tenant2"));

            // Roles
            Authority adminAuth = new Authority(UserRoleName.ROLE_ADMIN);
            List<Authority> adminAuthorities = new ArrayList<>();
            adminAuthorities.add(adminAuth);
            // End Roles

            User user = new User("Test", "Tester", "testertest@example.com", bCryptPasswordEncoder.encode("test"), "testertest@example.com");
            user.setAuthorities(adminAuthorities);

            userService.save(user);
        } catch (SQLException | TenantNotFoundException | TenantResolvingException e) {
            e.printStackTrace();
        }
    }
}
