package com.github.kmu_shell_we.domain.season.dto.response;

import com.github.kmu_shell_we.domain.season.entity.Season;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor(staticName = "of")
@Schema(description = "시즌 응답 DTO")
public class SeasonResponse {

    @Schema(description = "시즌 ID", example = "550e8400-e29b-41d4-a716-446655440000")
    UUID id;

    @Schema(description = "연도", example = "2025")
    Integer year;

    @Schema(description = "학기 (1: 1학기, 2: 2학기)", example = "1")
    Integer semester;

    @Schema(description = "시즌 시작 일시", example = "2025-03-01T00:00:00")
    LocalDateTime startedAt;

    @Schema(description = "시즌 종료 일시", example = "2025-08-31T23:59:59")
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
