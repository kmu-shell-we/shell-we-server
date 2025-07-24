package com.github.kmu_shell_we.domain.season.dto.request;

import com.github.kmu_shell_we.domain.season.util.validation.UpsertStartBeforeEndDate;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@UpsertStartBeforeEndDate
public class UpdateSeasonRequest implements UpsertSeasonRequest {

    @NotBlank
    LocalDateTime startedAt;

    @NotBlank
    LocalDateTime endedAt;
}
