package com.github.kmu_shell_we.domain.season.dto.request;

import java.time.LocalDate;

public interface UpsertSeasonRequest {

    LocalDate getStartedAt();
    LocalDate getEndedAt();
}
