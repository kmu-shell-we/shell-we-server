package com.github.kmu_shell_we.global.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.github.kmu_shell_we.domain.auth.exception.AuthExceptions;
import com.github.kmu_shell_we.domain.user.entity.User;
import com.github.kmu_shell_we.global.property.JwtProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.UUID;

@Configuration
@RequiredArgsConstructor
public class JwtUtil {

    private final JwtProperty jwtProperty;

    @Bean
    public Algorithm algorithm() {

        return Algorithm.HMAC256(jwtProperty.getKey());
    }

    public String generateToken(User user) {

        return JWT.create()
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plus(jwtProperty.getTokenExpirationHours(), ChronoUnit.HOURS))
                .withClaim("id", user.getId().toString())
                .sign(algorithm());
    }

    public UUID extractId(String token) {

        return UUID.fromString(JWT.require(algorithm())
                .build()
                .verify(token)
                .getClaim("id")
                .asString());
    }

    public boolean validateToken(String token) {

        if (Objects.isNull(token)) return false;

        try {

            JWT.require(algorithm()).build().verify(token);
            return true;
        } catch (TokenExpiredException e) {

            throw AuthExceptions.ACCESS_TOKEN_EXPIRED.toException();
        } catch (Exception e) {

            return false;
        }
    }
}