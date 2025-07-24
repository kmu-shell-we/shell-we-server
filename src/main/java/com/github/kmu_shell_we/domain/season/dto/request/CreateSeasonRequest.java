package com.github.kmu_shell_we.domain.season.dto.request;

import com.github.kmu_shell_we.domain.season.util.validation.UpsertStartBeforeEndDate;
import com.github.kmu_shell_we.domain.season.util.validation.FutureYear;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@FutureYear
@UpsertStartBeforeEndDate
public class CreateSeasonRequest implements UpsertSeasonRequest {

    @NotBlank
    @Max(2099)
    Integer year;

    @NotBlank
    @Min(1)
    @Max(2)
    Integer semester;

    @NotBlank
    LocalDateTime startedAt;

    @NotBlank
    LocalDateTime endedAt;
}
