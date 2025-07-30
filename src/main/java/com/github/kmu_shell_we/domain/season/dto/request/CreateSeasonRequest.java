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
@UpsertStartBeforeEndDate
@Schema(description = "학기 생성 요청")
public class CreateSeasonRequest implements UpsertSeasonRequest {

    @Max(2099)
    @NotNull
    @FutureYear
    @Schema(description = "연도")
    Integer year;

    @Min(1)
    @Max(2)
    @NotNull
    @Schema(description = "학기")
    Integer semester;

    @NotNull
    @Schema(description = "시작 일시")
    LocalDateTime startedAt;

    @NotNull
    @Schema(description = "종료 일시")
    LocalDateTime endedAt;
}
