package com.github.kmu_shell_we.domain.season.dto.response;

import com.github.kmu_shell_we.domain.season.entity.Season;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor(staticName = "of")
public class SeasonResponse {

    UUID id;
    Integer year;
    Integer semester;
    LocalDateTime startedAt;
    LocalDateTime endedAt;

    public static SeasonResponse from(Season season) {

        return SeasonResponse.of(
                season.getId(),
                season.getYear(),
                season.getSemester(),
                season.getStartedAt(),
                season.getEndedAt()
        );
    }
}
