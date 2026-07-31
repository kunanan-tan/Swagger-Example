package com.example.swagger.config;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

/**
 * The previous {@code @PropertySources({"classpath:application.properties",
 * "classpath:application-${env.key}.properties"})} duplicated what Spring Boot already does and
 * loaded the files at a lower precedence than command line / environment variables, so overrides
 * silently lost. Profile selection now goes through {@code spring.profiles.active}.
 *
 * @author kunanan.t
 */
@Configuration
@ConfigurationPropertiesScan("com.example.swagger")
public class ApplicationConfig {
}
