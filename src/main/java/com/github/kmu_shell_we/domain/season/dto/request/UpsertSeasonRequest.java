package com.github.kmu_shell_we.domain.season.dto.request;

import java.time.LocalDateTime;

public interface UpsertSeasonRequest {

    LocalDateTime getStartedAt();
    LocalDateTime getEndedAt();
}
