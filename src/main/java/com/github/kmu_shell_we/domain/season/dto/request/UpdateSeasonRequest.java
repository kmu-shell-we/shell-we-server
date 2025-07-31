package com.github.kmu_shell_we.domain.season.dto.request;

import com.github.kmu_shell_we.domain.season.util.validation.UpsertStartBeforeEndDate;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@UpsertStartBeforeEndDate
@Schema(description = "시즌 수정 요청")
public class UpdateSeasonRequest implements UpsertSeasonRequest {

    @NotNull
    @Schema(description = "시작 일시")
    LocalDateTime startedAt;

    @NotNull
    @Schema(description = "종료 일시")
    LocalDateTime endedAt;
}
