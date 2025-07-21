package com.github.kmu_shell_we.global.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "app.jwt")
public class JwtProperty {
    private String key;
    private long accessTokenExpirationHours;
    private long refreshTokenExpirationHours;
}
