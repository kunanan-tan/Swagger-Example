package com.example.swagger;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.time.OffsetDateTime;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class SwaggerApplication {

    private final Environment env;

    public static void main(String[] args) {
        SpringApplication.run(SwaggerApplication.class, args);
    }

    @Bean
    public CommandLineRunner intro() {
        return args -> {
            log.info("Swagger: {}", env.getProperty("initial.version"));
            log.info("Active profiles: {}", (Object) env.getActiveProfiles());
            log.info("Started at: {}", OffsetDateTime.now());
        };
    }

}
