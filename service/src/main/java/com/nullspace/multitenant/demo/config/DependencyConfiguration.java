package com.nullspace.multitenant.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@Configuration
public class DependencyConfiguration {

    @Value("${notProtectedUriPatterns}")
    private String notProtectedUriPatterns;

    @Bean
    public List<String> notProtectedUriPatterns() {

        String uris;

        try {
            Resource resource = new ClassPathResource(notProtectedUriPatterns);
            InputStream resourceInputStream = resource.getInputStream();
            try(java.util.Scanner s = new java.util.Scanner(resourceInputStream)) {
                uris =  s.useDelimiter("\\A").hasNext() ? s.next() : "";
            }

            return new ArrayList<>(Arrays.asList(uris.split(",")));


        } catch (Exception e) {
            log.error("Failed to load notProtectedUri file for tenant create");
        }

        return Collections.emptyList();
    }
}
