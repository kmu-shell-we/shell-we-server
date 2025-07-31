package com.github.kmu_shell_we.domain.season._team.controller;

import com.github.kmu_shell_we.domain.season._team.dto.response.GetOtherTeamResponse;
import com.github.kmu_shell_we.domain.season._team.dto.response.GetTeamResponse;
import com.github.kmu_shell_we.domain.season._team.dto.response.TeamListResponse;
import com.github.kmu_shell_we.domain.season._team.service.TeamService;
import com.github.kmu_shell_we.domain.user.entity.User;
import com.github.kmu_shell_we.global.response.ApiResponse;
import com.github.kmu_shell_we.global.security.guard.MemberGuard;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@MemberGuard
@RestController
@RequestMapping("/seasons/{seasonId}/teams")
@RequiredArgsConstructor
@Tag(name = "팀")
public class TeamController {

    private final TeamService teamService;

    @GetMapping
    @Operation(summary = "전체 팀 목록 조회")
    public ApiResponse<TeamListResponse> getTeams(@Parameter(description = "시즌 ID") @PathVariable UUID seasonId) {

        return ApiResponse.ok(teamService.getTeams(seasonId));
    }

    @GetMapping("/me")
    @Operation(summary = "내 팀 조회")
    public ApiResponse<GetTeamResponse> getMyTeam(
            @AuthenticationPrincipal User user,
            @Parameter(description = "시즌 ID") @PathVariable UUID seasonId
    ) {
        return ApiResponse.ok(teamService.getMyTeam(user, seasonId));
    }

    @GetMapping("/{teamId}")
    @Operation(summary = "다른 팀 조회")
    public ApiResponse<GetOtherTeamResponse> getOtherTeam(
            @Parameter(description = "시즌 ID") @PathVariable UUID seasonId,
            @Parameter(description = "팀 ID") @PathVariable UUID teamId
    ) {

        return ApiResponse.ok(teamService.getOtherTeam(seasonId, teamId));
    }
}