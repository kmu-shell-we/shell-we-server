package com.github.kmu_shell_we.global.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.github.kmu_shell_we.domain.user.entity.User;
import com.github.kmu_shell_we.global.property.JwtProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Configuration
@RequiredArgsConstructor
public class JwtUtil {

    private final JwtProperty jwtProperty;

    @Bean
    public Algorithm algorithm() {

        return Algorithm.HMAC256(jwtProperty.getKey());
    }

    public String generateToken(User user) {

        Instant now = Instant.now();

        return JWT.create()
                .withIssuer("shell-we")
                .withSubject("access-token")
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(now.plus(jwtProperty.getTokenExpirationHours(), ChronoUnit.HOURS)))
                .withClaim("id", user.getId().toString())
                .sign(algorithm());
    }

    public boolean validateToken(String token) {

        try {

            JWT.require(algorithm()).withIssuer("shell-we").build().verify(token);
            return true;
        } catch (JWTVerificationException e) {

            return false;
        }
    }

    public String extractId(String token) {

        return JWT.require(algorithm()).withIssuer("shell-we").build().verify(token).getClaim("id").asString();
    }
}
