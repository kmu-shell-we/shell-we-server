package com.github.kmu_shell_we.domain.season._team._mission_team._submission_mission_team.controller;


import com.github.kmu_shell_we.domain.season._team._mission_team._submission_mission_team.dto.request.CreateSubmissionRequest;
import com.github.kmu_shell_we.domain.season._team._mission_team._submission_mission_team.dto.response.SubmissionListResponse;
import com.github.kmu_shell_we.domain.season._team._mission_team._submission_mission_team.dto.response.SubmissionResponse;
import com.github.kmu_shell_we.domain.season._team._mission_team._submission_mission_team.service.SubmissionService;
import com.github.kmu_shell_we.global.response.ApiResponse;
import com.github.kmu_shell_we.global.security.guard.MemberGuard;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@MemberGuard
@RestController
@RequestMapping("/seasons/{seasonId}/teams/{teamId}/missions/{missionId}/submissions")
@RequiredArgsConstructor
@Tag(name = "팀 미션 제출")
public class SubmissionController {

    private final SubmissionService submissionService;

    @GetMapping
    @Operation(summary = "팀 미션 제출 목록 조회")
    public ApiResponse<SubmissionListResponse> getSubmissions(
            @Parameter(description = "시즌 ID") @PathVariable UUID seasonId,
            @Parameter(description = "팀 ID") @PathVariable UUID teamId,
            @Parameter(description = "미션 ID") @PathVariable UUID missionId
    ) {

        return ApiResponse.ok(submissionService.getSubmissions(seasonId, teamId, missionId));
    }

    @PostMapping
    @Operation(summary = "팀 미션 제출")
    public ApiResponse<SubmissionResponse> submitMission(
            @Parameter(description = "시즌 ID") @PathVariable UUID seasonId,
            @Parameter(description = "팀 ID") @PathVariable UUID teamId,
            @Parameter(description = "미션 ID") @PathVariable UUID missionId,
            @RequestBody @Valid CreateSubmissionRequest request
    ) {

        return ApiResponse.ok(submissionService.submitSubmission(seasonId, teamId, missionId, request));
    }
}