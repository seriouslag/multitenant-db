package com.nullspace.multitenant.controllers;

import com.nullspace.multitenant.exceptions.NotFound;
import com.nullspace.multitenant.models.entities.User;
import com.nullspace.multitenant.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = GET, value = "/user/{userId}")
    @PreAuthorize("@securityService.isUser(#user, #userId) or hasRole('ROLE_ADMIN')")
    public User loadById(Principal user, @PathVariable Long userId) {
        return this.userService.findById(userId);
    }

    @RequestMapping(method = GET, value = "/user")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> loadAll() {
        return this.userService.findAll();
    }

    @RequestMapping(method = PUT, value = "/user/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User update(@RequestBody User user, @PathVariable Long userId) {
        if (user.getId().equals(userId)) {
            return this.userService.update(user);
        }
        throw new NotFound();
    }

    @RequestMapping(method = POST, value = "/user")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User add(@RequestBody User user) {
        return this.userService.save(user);
    }

    @RequestMapping(method = DELETE, value = "/user/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable Long userId) {
        this.userService.deleteById(userId);
    }

    @RequestMapping("/auth/whoami")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public User user(Principal user) {
        return this.userService.findByUsername(user.getName());
    }

    @PreAuthorize("@securityService.isUser(#user, #userId)")
    @RequestMapping(method = GET, value = "/test/{userId}")
    public boolean test2(Principal user, @PathVariable Long userId) {
        return true;
    }
}
