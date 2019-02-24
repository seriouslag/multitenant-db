package com.nullspace.multitenant.bootstrap;

import com.nullspace.multitenant.models.UserRoleName;
import com.nullspace.multitenant.models.entities.Authority;
import com.nullspace.multitenant.models.entities.User;
import com.nullspace.multitenant.multitenant.Exceptions.NoTenantFilesFound;
import com.nullspace.multitenant.multitenant.Exceptions.TenantNotFound;
import com.nullspace.multitenant.multitenant.Exceptions.TenantResolving;
import com.nullspace.multitenant.multitenant.MultiTenantManager;
import com.nullspace.multitenant.services.exceptions.AlreadyExists;
import com.nullspace.multitenant.services.interfaces.IUserService;
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
        loadRootUser();
        loadTenant1();
        loadTenant2();
    }

    private void loadRootUser() {
        System.out.println("Creating root tenant");

        try {
            // Add/create the tenant
            tenantManager.addTenant("root", "postgres", "postgres");
            // Set this context to using the new tenant
            tenantManager.setCurrentTenant(tenantManager.getTenantsIdByName("root"));

            // Roles
            Authority adminAuth = new Authority(UserRoleName.ROLE_ADMIN);
            List<Authority> adminAuthorities = new ArrayList<>();
            adminAuthorities.add(adminAuth);
            // End Roles

            User user = new User("Root", "Root", "root@example.com", bCryptPasswordEncoder.encode("password"), "root");
            user.setAuthorities(adminAuthorities);


            try {
                userService.save(user);
            } catch (AlreadyExists e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Finished loading tenant-root.");
        } catch (SQLException | TenantNotFound | TenantResolving | NoTenantFilesFound e) {
            e.printStackTrace();
            System.out.println("Failed to load tenant-root");
        }
    }

    private void loadTenant1() {
        System.out.println("Loading/creating tenant1...");
        try {
            // Add/create the tenant
            tenantManager.addTenant("tenant1", "postgres", "postgres");
            // Set this context to using the new tenant
            tenantManager.setCurrentTenant(tenantManager.getTenantsIdByName("tenant1"));
            // Roles
            Authority adminAuth = new Authority(UserRoleName.ROLE_ADMIN);
            List<Authority> adminAuthorities = new ArrayList<>();
            adminAuthorities.add(adminAuth);
            // End Roles

            User user = new User("Landon", "Gavin", "landongavin@example.com", bCryptPasswordEncoder.encode("test"), "landongavin@example.com");
            user.setAuthorities(adminAuthorities);

            try {
                userService.save(user);
            } catch (AlreadyExists e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Finished loading tenant1.");
        } catch (SQLException | TenantNotFound | TenantResolving | NoTenantFilesFound e) {
            e.printStackTrace();
            System.out.println("Failed to load tenant1");
        }
    }

    private void loadTenant2() {
        System.out.println("Loading/creating tenant2...");
        try {
            // Add/create the tenant
            tenantManager.addTenant("tenant2", "postgres", "postgres");
            // Set this context to using the new tenant
            tenantManager.setCurrentTenant(tenantManager.getTenantsIdByName("tenant2"));

            // Roles
            Authority adminAuth = new Authority(UserRoleName.ROLE_ADMIN);
            List<Authority> adminAuthorities = new ArrayList<>();
            adminAuthorities.add(adminAuth);
            // End Roles

            User user = new User("Test", "Tester", "testertest@example.com", bCryptPasswordEncoder.encode("test"), "testertest@example.com");
            user.setAuthorities(adminAuthorities);

            try {
                userService.save(user);
            } catch (AlreadyExists e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Finished loading tenant2.");
        } catch (SQLException | TenantNotFound | TenantResolving | NoTenantFilesFound e) {
            e.printStackTrace();
            System.out.println("Failed to load tenant2");
        }
    }
}
