package com.example.swagger.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * springdoc-openapi (OpenAPI 3) replacement for the abandoned springfox {@code Docket}.
 *
 * @author kunanan.t
 */
@Configuration
public class SwaggerConfig {

    private static final String BASIC_AUTH = "basicAuth";

    @Value("${initial.version}")
    private String version;

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("Example Swagger")
                        .description("API DOCUMENT")
                        .version(version)
                        .license(new License().name("Kunanan").url("https://github.com/kunanan-tan")))
                .components(new Components().addSecuritySchemes(BASIC_AUTH, new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("basic")))
                .addSecurityItem(new SecurityRequirement().addList(BASIC_AUTH));
    }
}
