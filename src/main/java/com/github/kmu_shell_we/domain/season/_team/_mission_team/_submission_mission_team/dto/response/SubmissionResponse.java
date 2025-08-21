package com.github.kmu_shell_we.domain.season._team._mission_team._submission_mission_team.dto.response;

import com.github.kmu_shell_we.domain.season._team._mission_team._submission_mission_team.entity.Submission;
import com.github.kmu_shell_we.domain.season._team._mission_team.dto.response.TeamMissionResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
@Schema(description = "팀 미션 제출 응답")
public class SubmissionResponse {

    @Schema(description = "팀 미션")
    TeamMissionResponse teamMission;

    @Schema(description = "팀 미션 인증 사진 URL")
    String image;

    public static SubmissionResponse from(Submission submission) {

        return SubmissionResponse.of(
                TeamMissionResponse.from(submission.getTeamMission()),
                submission.getImage()
        );
    }
}
