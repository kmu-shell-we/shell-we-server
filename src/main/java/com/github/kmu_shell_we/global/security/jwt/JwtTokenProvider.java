package com.github.kmu_shell_we.global.security.jwt;

import com.github.kmu_shell_we.global.property.JwtProperty;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtProperty jwtProperty;
    private final UserDetailsService userDetailsService;

    private Algorithm algorithm() {
        return Algorithm.HMAC256(jwtProperty.getKey());
    }

    public String createAccessToken(String email) {
        try {
            Instant now = Instant.now();
            return JWT.create()
                    .withIssuer("shell-we")
                    .withSubject("access-token")
                    .withIssuedAt(Date.from(now))
                    .withExpiresAt(Date.from(now.plus(jwtProperty.getAccessTokenExpirationHours(), ChronoUnit.HOURS)))
                    .withClaim("email", email)
                    .sign(algorithm());
        } catch (JWTCreationException e) {
            throw new RuntimeException("AccessToken 생성 실패", e);
        }
    }

    public boolean validateToken(String token) {
        try {
            JWT.require(algorithm())
                    .withIssuer("shell-we")
                    .build()
                    .verify(token);
            return true;
        } catch (JWTVerificationException | IllegalArgumentException e) {
            return false;
        }
    }

    // 간단하게 한 줄로 처리 (DecodedJWT 변수 선언 없음)
    public String extractEmailFromToken(String token) {
        return JWT.require(algorithm())
                .withIssuer("shell-we")
                .build()
                .verify(token)
                .getClaim("email")
                .asString();
    }

    public Authentication getAuthentication(String accessToken) {
        String email = extractEmailFromToken(accessToken);
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
