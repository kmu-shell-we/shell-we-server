package com.github.kmu_shell_we.domain.auth.controller;

import com.github.kmu_shell_we.domain.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "인증")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/oauth/wink")
    @SneakyThrows(IOException.class)
    @Operation(summary = "WINK OAuth 리다이렉트")
    public void redirect(HttpServletResponse response) {

        response.sendRedirect(authService.getWinkOauthUri());
    }

    @GetMapping("/oauth/wink/callback")
    @SneakyThrows(IOException.class)
    @Operation(summary = "WINK OAuth 콜백")
    public void callback(HttpServletResponse response, @RequestParam String token) {

        response.sendRedirect(authService.callback(token));
    }
}
