package com.github.kmu_shell_we.domain.season._team._user_team.controller;

import com.github.kmu_shell_we.domain.season._team._user_team.service.AdminUserTeamService;
import com.github.kmu_shell_we.domain.season._team.entity.Team;
import com.github.kmu_shell_we.domain.season.entity.Season;
import com.github.kmu_shell_we.domain.user.entity.User;
import com.github.kmu_shell_we.global.response.ApiResponse;
import com.github.kmu_shell_we.global.security.guard.AdminGuard;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AdminGuard
@RestController
@RequestMapping("/admin/seasons/{season}/teams/{team}/members")
@RequiredArgsConstructor
@Tag(name = "[관리자] 유저 팀")
public class AdminUserTeamController {

    private final AdminUserTeamService adminUserTeamService;

    @PostMapping("/{user}")
    @Operation(summary = "팀에 유저 추가")
    public ApiResponse<Void> addMember(
            @Parameter(description = "시즌 ID") @PathVariable Season season,
            @Parameter(description = "팀 ID") @PathVariable Team team,
            @Parameter(description = "유저 ID") @PathVariable User user
    ) {

        adminUserTeamService.addMember(season, team, user);

        return ApiResponse.ok();
    }

    @DeleteMapping("/{user}")
    @Operation(summary = "팀에 유저 삭제")
    public ApiResponse<Void> deleteMember(
            @Parameter(description = "시즌 ID") @PathVariable Season season,
            @Parameter(description = "팀 ID") @PathVariable Team team,
            @Parameter(description = "유저 ID") @PathVariable User user
    ) {

        adminUserTeamService.deleteMember(season, team, user);

        return ApiResponse.ok();
    }
}
