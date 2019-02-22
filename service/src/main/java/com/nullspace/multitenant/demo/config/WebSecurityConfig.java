package com.nullspace.multitenant.demo.config;

import com.nullspace.multitenant.demo.multitenant.MultiTenantManager;
import com.nullspace.multitenant.demo.security.TokenHelper;
import com.nullspace.multitenant.demo.security.auth.RestAuthenticationEntryPoint;
import com.nullspace.multitenant.demo.security.auth.TokenAuthenticationFilter;
import com.nullspace.multitenant.demo.service.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.util.List;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    private final CustomUserDetailsService jwtUserDetailsService;
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    private final TokenHelper tokenHelper;
    private final MultiTenantManager tenantManager;
    private final List<String> notProtectedUriPatterns;

    @Autowired
    public WebSecurityConfig(@Lazy CustomUserDetailsService userDetailsService,
                             RestAuthenticationEntryPoint entryPoint,
                             TokenHelper tokenHelper,
                             MultiTenantManager tenantManager,
                             List<String> notProtectedUriPatterns) {
        this.jwtUserDetailsService = userDetailsService;
        this.restAuthenticationEntryPoint = entryPoint;
        this.tokenHelper = tokenHelper;
        this.tenantManager = tenantManager;
        this.notProtectedUriPatterns = notProtectedUriPatterns;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Configuration
    public class ApiWebServiceConfigurationAdaptor extends WebSecurityConfigurerAdapter {

        @Bean
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            String[] notProtectUriPatternArray = notProtectedUriPatterns.toArray(new String[0]);

            http
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and() // Set api to stateless
                    .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and() // Adding default entry point
                    .authorizeRequests()
                    .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                    .antMatchers(HttpMethod.GET, "/api/tenants").permitAll()
                    .antMatchers(notProtectUriPatternArray).permitAll()
                    .anyRequest().authenticated().and()
                    .addFilterBefore(new TokenAuthenticationFilter(tokenHelper, jwtUserDetailsService, tenantManager, notProtectedUriPatterns), BasicAuthenticationFilter.class);

            http.csrf().disable();
        }
    }

    @Configuration
    @Order(1)
    public class ApiWebServiceConfigurationAdaptor2 extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http
                    .antMatcher("/api/tenants")
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // Set api to stateless

            http.csrf().disable();
        }
    }
}