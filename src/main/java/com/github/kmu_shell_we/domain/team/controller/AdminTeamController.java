package com.github.kmu_shell_we.domain.team.controller;

import com.github.kmu_shell_we.domain.team.dto.response.TeamListResponse;
import com.github.kmu_shell_we.domain.team.dto.response.TeamResponse;
import com.github.kmu_shell_we.domain.team.service.AdminTeamService;
import com.github.kmu_shell_we.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping("/admin/teams")
@RequiredArgsConstructor
@Tag(name = "관리자용 팀 API")
public class AdminTeamController {

    private final AdminTeamService adminTeamService;

    @GetMapping("/season/{seasonId}")
    @Operation(summary = "전체 팀 조회", description = "관리자가 해당 시즌의 전체 팀을 조회합니다.")
    public ApiResponse<TeamListResponse> getTeams(@Parameter(description = "시즌 ID") @PathVariable UUID seasonId) {

        return ApiResponse.ok(adminTeamService.getTeams(seasonId));
    }

    @GetMapping("/{teamId}")
    @Operation(summary = "팀 상세 조회", description = "관리자가 팀을 상세 조회합니다.")
    public ApiResponse<TeamResponse> getTeam(@Parameter(description = "팀 ID") @PathVariable UUID teamId) {

        return ApiResponse.ok(adminTeamService.getTeam(teamId));
    }

    @PostMapping("/{seasonId}")
    @Operation(summary = "팀 생성", description = "관리자가 팀을 생성합니다.")
    public ApiResponse<TeamResponse> createTeam(@Parameter(description = "시즌 ID") @PathVariable UUID seasonId) {

        return ApiResponse.ok(adminTeamService.createTeam(seasonId));
    }

    @DeleteMapping("/{teamId}")
    @Operation(summary = "팀 삭제", description = "관리자가 팀을 삭제합니다.")
    public ApiResponse<Void> deleteTeam(@Parameter(description = "팀 ID") @PathVariable UUID teamId) {

        adminTeamService.deleteTeam(teamId);

        return ApiResponse.ok();
    }
}
