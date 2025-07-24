package com.github.kmu_shell_we.domain.season.dto.request;

import com.github.kmu_shell_we.domain.season.util.validation.FutureYear;
import com.github.kmu_shell_we.domain.season.util.validation.UpsertStartBeforeEndDate;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@FutureYear
@UpsertStartBeforeEndDate
public class CreateSeasonRequest implements UpsertSeasonRequest {

    @NotNull
    @Max(2099)
    Integer year;

    @NotNull
    @Min(1)
    @Max(2)
    Integer semester;

    @NotNull
    LocalDateTime startedAt;

    @NotNull
    LocalDateTime endedAt;
}
