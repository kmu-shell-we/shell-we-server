package com.github.kmu_shell_we.domain.season.dto.request;

import com.github.kmu_shell_we.domain.season.util.validation.FutureYear;
import com.github.kmu_shell_we.domain.season.util.validation.UpsertStartBeforeEndDate;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@FutureYear
@UpsertStartBeforeEndDate
@Schema(description = "학기 생성 요청 DTO")
public class CreateSeasonRequest implements UpsertSeasonRequest {

    @NotNull
    @Max(2099)
    @Schema(description = "시즌 연도", example = "2025")
    Integer year;

    @NotNull
    @Min(1)
    @Max(2)
    @Schema(description = "시즌 학기 (1: 1학기, 2: 2학기)", example = "1")
    Integer semester;

    @NotNull
    @Schema(description = "시즌 시작 일시", example = "2025-03-01T00:00:00")
    LocalDateTime startedAt;

    @NotNull
    @Schema(description = "시즌 종료 일시", example = "2025-08-31T23:59:59")
    LocalDateTime endedAt;
}
