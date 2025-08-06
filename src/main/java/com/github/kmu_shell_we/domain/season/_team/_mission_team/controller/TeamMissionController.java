package com.github.kmu_shell_we.domain.season._team._mission_team.controller;


import com.github.kmu_shell_we.domain.season._team._mission_team.dto.response.TeamMissionListResponse;
import com.github.kmu_shell_we.domain.season._team._mission_team.service.TeamMissionService;
import com.github.kmu_shell_we.global.response.ApiResponse;
import com.github.kmu_shell_we.global.security.guard.MemberGuard;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.UUID;

@MemberGuard
@RestController
@RequestMapping("/seasons/{seasonId}/teams/{teamId}/missions")
@RequiredArgsConstructor
@Tag(name = "팀 미션")
public class TeamMissionController {

    private final TeamMissionService teamMissionService;

    @GetMapping
    @Operation(summary = "팀 미션 목록 조회")
    public ApiResponse<TeamMissionListResponse> getTeamMissions(
            @Parameter(description = "시즌 ID")
            @PathVariable UUID seasonId,
            @Parameter(description = "팀 ID")
            @PathVariable UUID teamId
    ) {

        return ApiResponse.ok(teamMissionService.getTeamMissions(seasonId, teamId));
    }
}
