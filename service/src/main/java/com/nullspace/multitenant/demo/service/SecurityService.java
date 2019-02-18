package com.nullspace.multitenant.demo.service;

import com.nullspace.multitenant.demo.models.entities.User;
import com.nullspace.multitenant.demo.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class SecurityService {
    private final IUserService userService;

    @Autowired
    public SecurityService(IUserService userService) {
        this.userService = userService;
    }

    public boolean isUser(Principal principal, Long userId) {
        User user = userService.findByUsername(principal.getName());

        return user.getId().equals(userId);
    }
}
