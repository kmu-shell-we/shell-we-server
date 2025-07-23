package com.github.kmu_shell_we.domain.auth.controller;

import com.github.kmu_shell_we.domain.auth.dto.response.UserResponse;
import com.github.kmu_shell_we.domain.auth.service.AuthService;
import com.github.kmu_shell_we.domain.user.entity.User;
import com.github.kmu_shell_we.global.response.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/wink")
    @SneakyThrows(IOException.class)
    public void redirect(HttpServletResponse response) {

        response.sendRedirect(authService.getWinkOauthUri());
    }

    @GetMapping("/wink/callback")
    @SneakyThrows(IOException.class)
    public void callback(HttpServletResponse response, @RequestParam String token) {

        response.sendRedirect(authService.callback(token));
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ApiResponse<UserResponse> getMe(@AuthenticationPrincipal User user) {

        return ApiResponse.ok(UserResponse.from(user));
    }
}
