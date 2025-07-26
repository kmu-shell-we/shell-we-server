package com.github.kmu_shell_we.domain.auth.controller;

import com.github.kmu_shell_we.domain.auth.dto.response.UserResponse;
import com.github.kmu_shell_we.domain.auth.service.AuthService;
import com.github.kmu_shell_we.domain.user.entity.User;
import com.github.kmu_shell_we.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "인증 API", description = "인증 관련 API")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/wink")
    @SneakyThrows(IOException.class)
    @Operation(summary = "Wink 인증 리다이렉트", description = "Wink OAuth 인증을 위한 리다이렉트 URI로 이동합니다.")
    public void redirect(HttpServletResponse response) {

        response.sendRedirect(authService.getWinkOauthUri());
    }

    @GetMapping("/wink/callback")
    @SneakyThrows(IOException.class)
    @Operation(summary = "Wink 인증 콜백", description = "Wink OAuth 인증 후 콜백 URI로 이동합니다. 토큰을 받아 세션에 저장합니다.")
    public void callback(HttpServletResponse response, @RequestParam String token) {

        response.sendRedirect(authService.callback(token));
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "내 정보 조회", description = "인증된 사용자의 정보를 조회합니다.")
    public ApiResponse<UserResponse> getMe(@AuthenticationPrincipal User user) {

        return ApiResponse.ok(UserResponse.from(user));
    }
}
