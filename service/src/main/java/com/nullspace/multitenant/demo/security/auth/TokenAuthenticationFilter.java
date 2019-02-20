package com.nullspace.multitenant.demo.security.auth;

import com.nullspace.multitenant.demo.multitenant.Exceptions.NoTenantFilesFound;
import com.nullspace.multitenant.demo.multitenant.Exceptions.TenantNotFound;
import com.nullspace.multitenant.demo.multitenant.Exceptions.TenantResolving;
import com.nullspace.multitenant.demo.multitenant.MultiTenantManager;
import com.nullspace.multitenant.demo.security.TokenHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final Log logger = LogFactory.getLog(this.getClass());

    private TokenHelper tokenHelper;

    private UserDetailsService userDetailsService;
    private final MultiTenantManager tenantManager;
    private final List<String> notProtectedUriPatterns;

    private PathMatcher a = new AntPathMatcher();

    public TokenAuthenticationFilter(TokenHelper tokenHelper,
                                     UserDetailsService userDetailsService,
                                     MultiTenantManager tenantManager,
                                     List<String> notProtectedUriPatterns) {
        this.tokenHelper = tokenHelper;
        this.userDetailsService = userDetailsService;
        this.tenantManager = tenantManager;
        this.notProtectedUriPatterns = notProtectedUriPatterns;
    }

    private boolean isUriNotProtected(String uri) {
        for(String pattern: notProtectedUriPatterns) {
            if(a.match(pattern, uri)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

        if ("OPTIONS".equalsIgnoreCase(request.getMethod()) || isUriNotProtected(request.getRequestURI())) {
            chain.doFilter(request, response);
            return;
        }

        String authToken = tokenHelper.getToken(request);

        if (authToken == null) {
            chain.doFilter(request, response);
            return;
        }

        // get username from token
        String username = tokenHelper.getUsernameFromToken(authToken);
        String tenantId = tokenHelper.getTenantIdFromToken(authToken);

        if(tenantId == null) {
            chain.doFilter(request, response);
            return;
        }

        try {
            tenantManager.setCurrentTenant(tenantId);
        } catch (SQLException | TenantNotFound | TenantResolving | NoTenantFilesFound e) {
            logger.error("Failed to set tenant to tenant: " + tenantId + "; Failure in TokenAuthFilter");
            chain.doFilter(request, response);
            return;
        }

        if (username == null) {
            chain.doFilter(request, response);
            return;
        }

        // get user
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (tokenHelper.validateToken(authToken, userDetails)) {
            // create authentication
            TokenBasedAuthentication authentication = new TokenBasedAuthentication(userDetails);
            authentication.setToken(authToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }
}
