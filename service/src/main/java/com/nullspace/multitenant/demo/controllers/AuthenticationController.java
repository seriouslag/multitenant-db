package com.nullspace.multitenant.demo.controllers;

import com.nullspace.multitenant.demo.models.UserTokenState;
import com.nullspace.multitenant.demo.models.entities.User;
import com.nullspace.multitenant.demo.multitenant.MultiTenantManager;
import com.nullspace.multitenant.demo.security.TokenHelper;
import com.nullspace.multitenant.demo.security.auth.JwtAuthenticationRequest;
import com.nullspace.multitenant.demo.service.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    private final TokenHelper tokenHelper;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final MultiTenantManager tenantManager;

    @Autowired
    public AuthenticationController(TokenHelper tokenHelper,
                                    CustomUserDetailsService userDetailsService,
                                    @Qualifier("authenticationManagerBean") AuthenticationManager authenticationManager,
                                    MultiTenantManager tenantManager) {
        this.tokenHelper = tokenHelper;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.tenantManager = tenantManager;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest,
            @RequestHeader(value = "X-TenantID") String tenantId,
            HttpServletResponse response
    ) throws AuthenticationException {
        tenantManager.setTenant(tenantId);

        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );

        // Inject into security context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // token creation
        User user = (User) authentication.getPrincipal();
        String jws = tokenHelper.generateToken(user.getUsername(), tenantId);
        int expiresIn = tokenHelper.getExpiredIn();
        String id = user.getId().toString();
        // Return the token
        return ResponseEntity.ok(new UserTokenState(jws, expiresIn, id));
    }

    @RequestMapping(value = "/refresh", method = RequestMethod.POST)
    public ResponseEntity<?> refreshAuthenticationToken(
            HttpServletRequest request,
            HttpServletResponse response,
            Principal principal
    ) {

        String authToken = tokenHelper.getToken(request);

        if (authToken != null && principal != null) {

            // TODO check user password last update
            String refreshedToken = tokenHelper.refreshToken(authToken);
            int expiresIn = tokenHelper.getExpiredIn();
            String id = tokenHelper.getTenantIdFromToken(authToken);

            return ResponseEntity.ok(new UserTokenState(refreshedToken, expiresIn, id));
        } else {
            UserTokenState userTokenState = new UserTokenState();
            return ResponseEntity.accepted().body(userTokenState);
        }
    }

    @RequestMapping(value = "/change-password", method = RequestMethod.POST)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> changePassword(@RequestBody PasswordChanger passwordChanger) {
        userDetailsService.changePassword(passwordChanger.oldPassword, passwordChanger.newPassword);
        Map<String, String> result = new HashMap<>();
        result.put("result", "success");
        return ResponseEntity.accepted().body(result);
    }

    static class PasswordChanger {
        String oldPassword;
        String newPassword;
    }
}