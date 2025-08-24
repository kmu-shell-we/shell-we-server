package com.github.kmu_shell_we.domain.season._team._team_mission._submission.controller;


import com.github.kmu_shell_we.domain.season._team._team_mission._submission.dto.request.CreateSubmissionRequest;
import com.github.kmu_shell_we.domain.season._team._team_mission._submission.dto.response.SubmissionListResponse;
import com.github.kmu_shell_we.domain.season._team._team_mission._submission.dto.response.SubmissionResponse;
import com.github.kmu_shell_we.domain.season._team._team_mission._submission.service.SubmissionService;
import com.github.kmu_shell_we.domain.season._team._team_mission.entity.TeamMission;
import com.github.kmu_shell_we.domain.season._team.entity.Team;
import com.github.kmu_shell_we.domain.season.entity.Season;
import com.github.kmu_shell_we.domain.user.entity.User;
import com.github.kmu_shell_we.global.response.ApiResponse;
import com.github.kmu_shell_we.global.security.guard.MemberGuard;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
            @AuthenticationPrincipal User user,
            @Parameter(description = "시즌 ID", schema = @Schema(type = "string", format = "uuid")) @PathVariable Season season,
            @Parameter(description = "팀 ID", schema = @Schema(type = "string", format = "uuid")) @PathVariable Team team
    ) {

        return ApiResponse.ok(submissionService.getSubmissions(user, season, team));
    }

    @GetMapping("/team_mission/{team_mission}/submissions")
    @Operation(summary = "팀 미션 제출 조회")
    public ApiResponse<SubmissionResponse> getSubmission(
            @AuthenticationPrincipal User user,
            @Parameter(description = "시즌 ID", schema = @Schema(type = "string", format = "uuid")) @PathVariable Season season,
            @Parameter(description = "팀 ID", schema = @Schema(type = "string", format = "uuid")) @PathVariable Team team,
            @Parameter(description = "팀 미션 ID", schema = @Schema(type = "string", format = "uuid"))@PathVariable TeamMission team_mission
    ) {

        return ApiResponse.ok(submissionService.getSubmission(user, season, team, team_mission));
    }

    @PostMapping("/team_mission/{team_mission}/submissions")
    @Operation(summary = "팀 미션 제출")
    public ApiResponse<SubmissionResponse> submitMission(
            @AuthenticationPrincipal User user,
            @Parameter(description = "시즌 ID", schema = @Schema(type = "string", format = "uuid")) @PathVariable Season season,
            @Parameter(description = "팀 ID", schema = @Schema(type = "string", format = "uuid")) @PathVariable Team team,
            @Parameter(description = "팀 미션 ID", schema = @Schema(type = "string", format = "uuid")) @PathVariable TeamMission team_mission,
            @RequestBody @Valid CreateSubmissionRequest request
    ) {

        return ApiResponse.ok(submissionService.submitSubmission(user, season, team, team_mission, request));
    }
}