package com.example.swagger.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Implements {@link WebMvcConfigurer} instead of extending {@code WebMvcConfigurationSupport}:
 * the old base class silently disables Spring Boot's MVC auto-configuration (content negotiation,
 * message converters, static resources, springdoc handlers).
 *
 * <p>springdoc serves its own UI/resources, so no manual resource handlers are needed.
 * CORS is owned by {@link WebSecurityConfig} so a single policy applies to the whole chain.
 *
 * @author kunanan.t
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
}
