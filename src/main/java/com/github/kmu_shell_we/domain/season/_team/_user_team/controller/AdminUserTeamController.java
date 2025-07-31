package com.github.kmu_shell_we.domain.season._team._user_team.controller;

import com.github.kmu_shell_we.domain.season._team._user_team.service.AdminUserTeamService;
import com.github.kmu_shell_we.global.response.ApiResponse;
import com.github.kmu_shell_we.global.security.guard.AdminGuard;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AdminGuard
@RestController
@RequestMapping("/admin/seasons/{seasonId}/teams/{teamId}/members/{userId}")
@RequiredArgsConstructor
@Tag(name = "관리자용 유저 팀 API")
public class AdminUserTeamController {

    private final AdminUserTeamService adminUserTeamService;

    @PostMapping
    @Operation(summary = "팀에 유저 추가", description = "관리자가 팀에 유저를 추가할 수 있습니다.")
    public ApiResponse<Void> addMember(@PathVariable UUID seasonId, @PathVariable UUID teamId, @PathVariable UUID userId) {

        adminUserTeamService.addMember(seasonId, teamId, userId);

        return ApiResponse.ok();
    }

    @DeleteMapping
    @Operation(summary = "팀에 유저 삭제", description = "관리자가 팀에서 유저를 삭제할 수 있습니다.")
    public ApiResponse<Void> deleteMember(@PathVariable UUID seasonId, @PathVariable UUID teamId, @PathVariable UUID userId) {

        adminUserTeamService.deleteMember(seasonId, teamId, userId);

        return ApiResponse.ok();
    }
}
