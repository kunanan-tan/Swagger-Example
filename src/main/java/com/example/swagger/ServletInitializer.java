package com.example.swagger;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Required for {@code war} packaging - without it the application never boots when the war is
 * deployed into an external servlet container.
 *
 * @author kunanan.t
 */
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SwaggerApplication.class);
    }
}
