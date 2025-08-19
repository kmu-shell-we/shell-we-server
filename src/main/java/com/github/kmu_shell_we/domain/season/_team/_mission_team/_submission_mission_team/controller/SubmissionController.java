package com.github.kmu_shell_we.domain.season._team._mission_team._submission_mission_team.controller;


import com.github.kmu_shell_we.domain.mission.entity.Mission;
import com.github.kmu_shell_we.domain.season._team._mission_team._submission_mission_team.dto.request.CreateSubmissionRequest;
import com.github.kmu_shell_we.domain.season._team._mission_team._submission_mission_team.dto.response.SubmissionListResponse;
import com.github.kmu_shell_we.domain.season._team._mission_team._submission_mission_team.dto.response.SubmissionResponse;
import com.github.kmu_shell_we.domain.season._team._mission_team._submission_mission_team.service.SubmissionService;
import com.github.kmu_shell_we.domain.season._team.entity.Team;
import com.github.kmu_shell_we.domain.season.entity.Season;
import com.github.kmu_shell_we.global.response.ApiResponse;
import com.github.kmu_shell_we.global.security.guard.MemberGuard;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@MemberGuard
@RestController
@RequestMapping("/seasons/{season}/teams/{team}")
@RequiredArgsConstructor
@Tag(name = "팀 미션 제출")
public class SubmissionController {

    private final SubmissionService submissionService;

    @GetMapping("/submissions")
    @Operation(summary = "팀 미션 제출 목록 조회")
    public ApiResponse<SubmissionListResponse> getSubmissions(
            @Parameter(description = "시즌 ID", schema = @Schema(type = "string", format = "uuid")) @PathVariable Season season,
            @Parameter(description = "팀 ID", schema = @Schema(type = "string", format = "uuid")) @PathVariable Team team
    ) {

        return ApiResponse.ok(submissionService.getSubmissions(season, team));
    }

    @GetMapping("/missions/{mission}/submissions")
    @Operation(summary = "팀 미션 제출 조회")
    public ApiResponse<SubmissionResponse> getSubmission(
            @Parameter(description = "시즌 ID", schema = @Schema(type = "string", format = "uuid")) @PathVariable Season season,
            @Parameter(description = "팀 ID", schema = @Schema(type = "string", format = "uuid")) @PathVariable Team team,
            @Parameter(description = "미션 ID", schema = @Schema(type = "string", format = "uuid"))@PathVariable Mission mission
    ) {

        return ApiResponse.ok(submissionService.getSubmission(season, team, mission));
    }

    @PostMapping("/missions/{mission}/submissions")
    @Operation(summary = "팀 미션 제출")
    public ApiResponse<SubmissionResponse> submitMission(
            @Parameter(description = "시즌 ID", schema = @Schema(type = "string", format = "uuid")) @PathVariable Season season,
            @Parameter(description = "팀 ID", schema = @Schema(type = "string", format = "uuid")) @PathVariable Team team,
            @Parameter(description = "미션 ID", schema = @Schema(type = "string", format = "uuid")) @PathVariable Mission mission,
            @RequestBody @Valid CreateSubmissionRequest request
    ) {

        return ApiResponse.ok(submissionService.submitSubmission(season, team, mission, request));
    }
}