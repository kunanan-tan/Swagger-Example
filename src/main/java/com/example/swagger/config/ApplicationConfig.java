package com.example.swagger.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
/**
 *
 * @author kunanan.t
 */

@Configuration
@PropertySources({
    @PropertySource("classpath:application.properties"),
    @PropertySource("classpath:application-${env.key}.properties"),
    @PropertySource(value = "classpath:git.properties", ignoreResourceNotFound = true)
})
public class ApplicationConfig {

}
