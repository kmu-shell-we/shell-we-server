package com.github.kmu_shell_we.domain.user.controller;

import com.github.kmu_shell_we.domain.user.dto.response.UserListResponse;
import com.github.kmu_shell_we.domain.user.dto.response.UserResponse;
import com.github.kmu_shell_we.domain.user.entity.User;
import com.github.kmu_shell_we.domain.user.service.AdminUserService;
import com.github.kmu_shell_we.global.response.ApiResponse;
import com.github.kmu_shell_we.global.security.guard.AdminGuard;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AdminGuard
@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
@Tag(name = "[관리자] 유저")
public class AdminUserController {

    private final AdminUserService adminUserService;

    @GetMapping
    @Operation(summary = "전체 유저 목록 조회")
    public ApiResponse<UserListResponse> getUsers() {

        return ApiResponse.ok(adminUserService.getUsers());
    }

    @GetMapping("/{user}")
    @Operation(summary = "유저 상세 조회")
    public ApiResponse<UserResponse> getUser(@PathVariable User user) {

        return ApiResponse.ok(UserResponse.from(user));
    }
}
