package com.github.kmu_shell_we.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Bean
    public OpenAPI openAPI() {

        String jwt = "JWT";
        Components components = new Components().addSecuritySchemes(jwt, new SecurityScheme()
                .name(jwt)
                .type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer")
        );
        return new OpenAPI()
                .components(components)
                .info(apiInfo())
                .addServersItem(new Server().url(contextPath));
    }

    private Info apiInfo() {
        return new Info()
                .title("KMU-Shell WE API")
                .description("KMU-Shell WE의 API 문서입니다.")
                .version("1.0.0");
    }
}
