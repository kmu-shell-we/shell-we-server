package com.github.kmu_shell_we.global.property;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "app.auth.oauth")
public class OauthProperty {

    @NotBlank
    String redirectUri;
}
