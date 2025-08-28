package com.github.kmu_shell_we.domain.season._team._mission_team.controller;

import com.github.kmu_shell_we.domain.mission.entity.Mission;
import com.github.kmu_shell_we.domain.season._team._mission_team.dto.request.CreateSpecialTeamMissionRequest;
import com.github.kmu_shell_we.domain.season._team._mission_team.dto.response.TeamMissionListResponse;
import com.github.kmu_shell_we.domain.season._team._mission_team.entity.TeamMission;
import com.github.kmu_shell_we.domain.season._team._mission_team.service.TeamMissionService;
import com.github.kmu_shell_we.domain.season.entity.Season;
import com.github.kmu_shell_we.global.response.ApiResponse;
import com.github.kmu_shell_we.global.security.guard.AdminGuard;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AdminGuard
@RestController
@RequestMapping("/admin/seasons/{season}/teams/missions")
@RequiredArgsConstructor
@Tag(name = "[관리자] 팀 미션")
public class AdminTeamMissionController {

    private final TeamMissionService teamMissionService;

    @PostMapping("/{mission}")
    @Operation(summary = "특별 팀 미션을 모든 팀에 생성")
    public ApiResponse<TeamMissionListResponse> createSpecialMissionToAllTeams(
            @Parameter(schema=@Schema(type = "string", format = "uuid")) @PathVariable Season season,
            @Parameter(schema=@Schema(type = "string", format = "uuid")) @PathVariable Mission mission,
            @RequestBody @Valid CreateSpecialTeamMissionRequest request
    ) {
        List<TeamMission> teamMissions = teamMissionService.createSpecialMission(
                season,
                mission,
                request.getStartedAt(),
                request.getEndedAt()
        );
        return ApiResponse.ok(
                TeamMissionListResponse.from(teamMissions)
        );
    }
}
