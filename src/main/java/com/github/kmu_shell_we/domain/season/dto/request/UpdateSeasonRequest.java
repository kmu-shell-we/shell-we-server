package com.github.kmu_shell_we.domain.season.dto.request;

import com.github.kmu_shell_we.domain.season.util.validation.UpsertStartBeforeEndDate;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@UpsertStartBeforeEndDate
@Schema(description = "시즌 수정 요청 DTO")
public class UpdateSeasonRequest implements UpsertSeasonRequest {

    @NotNull
    @Schema(description = "시즌 시작 일시", example = "2025-03-01T00:00:00")
    LocalDateTime startedAt;

    @NotNull
    @Schema(description = "시즌 종료 일시", example = "2025-08-31T23:59:59")
    LocalDateTime endedAt;
}
