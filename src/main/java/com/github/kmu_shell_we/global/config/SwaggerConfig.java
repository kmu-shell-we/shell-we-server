package com.github.kmu_shell_we.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {

        return new OpenAPI().components(new Components().addSecuritySchemes("JWT", securityScheme())).info(info());
    }

    private Info info() {

        return new Info()
                .title("SHELL WE")
                .description("WINK 친목 플랫폼 SHELL WE")
                .version("0.3.0");
    }

    private SecurityScheme securityScheme() {

        return new SecurityScheme().type(SecurityScheme.Type.HTTP).bearerFormat("JWT").scheme("bearer");
    }
}
