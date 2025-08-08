package com.github.kmu_shell_we.domain.season._team._mission_team._submission_mission_team.dto.response;

import com.github.kmu_shell_we.domain.season._team._mission_team._submission_mission_team.entity.Submission;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor(staticName = "of")
@Schema(description = "제출 목록 응답")
public class SubmissionListResponse {

    @Schema(description = "제출 목록")
    List<SubmissionResponse> submissions;

    public static SubmissionListResponse from(List<Submission> submissions) {

        return SubmissionListResponse.of(submissions.stream().map(SubmissionResponse::from).toList());
    }
}
