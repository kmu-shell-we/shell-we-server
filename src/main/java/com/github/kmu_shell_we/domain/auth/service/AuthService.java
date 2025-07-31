package com.github.kmu_shell_we.domain.auth.service;

import com.github.kmu_shell_we.domain.auth.exception.AuthExceptions;
import com.github.kmu_shell_we.domain.user.entity.User;
import com.github.kmu_shell_we.domain.user.repository.UserRepository;
import com.github.kmu_shell_we.global.property.OauthProperty;
import com.github.kmu_shell_we.global.property.WinkOauthProperty;
import com.github.kmu_shell_we.global.security.jwt.JwtUtil;
import kong.unirest.core.ContentType;
import kong.unirest.core.Unirest;
import kong.unirest.core.UnirestInstance;
import kong.unirest.core.json.JSONObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    private final OauthProperty oauthProperty;
    private final WinkOauthProperty winkOauthProperty;

    public String getWinkOauthUri() {

        return "%s/application/%s/oauth?callback=%s".formatted(
                winkOauthProperty.getBaseUri(),
                winkOauthProperty.getClientId(),
                winkOauthProperty.getCallbackUri()
        );
    }

    public String callback(String token) {

        try (UnirestInstance instance = Unirest.spawnInstance()) {

            JSONObject response =
                    instance.post("%s/api/application/oauth/token".formatted(winkOauthProperty.getBaseUri()))
                            .contentType(ContentType.APPLICATION_JSON)
                            .body(Map.ofEntries(
                                    Map.entry("clientId", winkOauthProperty.getClientId()),
                                    Map.entry("clientSecret", winkOauthProperty.getClientSecret()),
                                    Map.entry("token", token)
                            ))
                            .asJson()
                            .getBody()
                            .getObject();

            if (!response.getBoolean("success")) {

                throw AuthExceptions.AUTHENTICATION_FAILED.toException();
            }

            JSONObject userResponse = response.getJSONObject("content").getJSONObject("user");

            User user = userRepository.findByProviderId(userResponse.getString("id"))
                    .orElseGet(() -> from(userResponse));

            return oauthProperty.getRedirectUri() + "?token=" + jwtUtil.generateToken(user);
        }
    }

    private User from(JSONObject response) {

        User.Role role = response.getString("role").equals("MEMBER") || response.getString("role").equals("GRADUATED")
                ? User.Role.MEMBER
                : User.Role.ADMIN;

        return userRepository.save(User.builder()
                .providerId(response.getString("id"))
                .studentId(response.getString("studentId"))
                .name(response.getString("name"))
                .avatar(response.optString("avatar"))
                .role(role)
                .build());
    }
}
