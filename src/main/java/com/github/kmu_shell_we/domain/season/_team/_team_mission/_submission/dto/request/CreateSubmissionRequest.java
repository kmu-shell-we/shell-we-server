package com.github.kmu_shell_we.domain.season._team._team_mission._submission.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "팀 미션 제출 요청")
public class CreateSubmissionRequest {

    @NotNull
    @Schema(description = "팀 미션 인증 이미지 URL")
    String image;
}
