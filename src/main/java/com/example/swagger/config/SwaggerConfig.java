package com.example.swagger.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 *
 * @author kunanan.t
 */


@Configuration
@EnableSwagger2
@ComponentScan("com.example.swagger.controller")
public class SwaggerConfig {

    @Value("${initial.version}")
    private String version;

    @Bean
    public Docket apiInfo() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.swagger.controller"))
                .paths(PathSelectors.regex("/.*"))
                .build().apiInfo(apiEndPointsInfo());

    }

    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Example Swagger")
                .description("API DOCUMENT")
                .license("Kunanan")
                .licenseUrl("https://github.com/kunanan-tan")
                .version(version)
                .build();
    }

}
