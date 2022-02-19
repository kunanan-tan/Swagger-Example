package com.example.swagger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import java.util.Date;

@Slf4j
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class SwaggerApplication {

    @Autowired
    private Environment env;

    public static void main(String[] args) {
        SpringApplication.run(SwaggerApplication.class, args);
    }

    @Bean
    public CommandLineRunner intro() {
        return (args) -> {
            log.info(String.format("Swagger: %s", env.getProperty("initial.version")));
            log.info(String.format("Env: %s ", env.getProperty("env.key")));
            log.info(String.format("Date: %s", new Date()));
        };
    }

}
