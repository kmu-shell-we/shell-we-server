package com.github.kmu_shell_we.domain.user.controller;

import com.github.kmu_shell_we.domain.user.dto.response.UserResponse;
import com.github.kmu_shell_we.domain.user.entity.User;
import com.github.kmu_shell_we.global.response.ApiResponse;
import com.github.kmu_shell_we.global.security.guard.MemberGuard;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@MemberGuard
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "유저")
public class UserController {

    @GetMapping("/me")
    @Operation(summary = "내 정보 조회")
    public ApiResponse<UserResponse> getMyInfo(@AuthenticationPrincipal User user) {

        return ApiResponse.ok(UserResponse.from(user));
    }
}
