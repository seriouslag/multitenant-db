package com.nullspace.multitenant.demo.security.auth;

import com.nullspace.multitenant.demo.multitenant.MultiTenantManager;
import com.nullspace.multitenant.demo.multitenant.TenantNotFoundException;
import com.nullspace.multitenant.demo.multitenant.TenantResolvingException;
import com.nullspace.multitenant.demo.security.TokenHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final Log logger = LogFactory.getLog(this.getClass());

    private TokenHelper tokenHelper;

    private UserDetailsService userDetailsService;
    private final MultiTenantManager tenantManager;

    public TokenAuthenticationFilter(TokenHelper tokenHelper, UserDetailsService userDetailsService, MultiTenantManager tenantManager){
        this.tokenHelper = tokenHelper;
        this.userDetailsService = userDetailsService;
        this.tenantManager = tenantManager;
    }

    @Override
    public void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

        String authToken = tokenHelper.getToken(request);

        if (authToken == null) {
            chain.doFilter(request, response);
            return;
        }

        // get username from token
        String username = tokenHelper.getUsernameFromToken(authToken);
        String tenantId = tokenHelper.getTenantIdFromToken(authToken);

        try {
            tenantManager.setCurrentTenant(tenantId);
        } catch (SQLException | TenantNotFoundException | TenantResolvingException e) {
            logger.error("Failed to set tenant to tenant: " + tenantId + "; Failure in TokenAuthFilter");
        }

        if (username != null) {
            // get user
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (tokenHelper.validateToken(authToken, userDetails)) {
                // create authentication
                TokenBasedAuthentication authentication = new TokenBasedAuthentication(userDetails);
                authentication.setToken(authToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(request, response);
    }
}
