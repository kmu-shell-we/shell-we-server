package com.github.kmu_shell_we.domain.season.dto.response;

import com.github.kmu_shell_we.domain.season.entity.Season;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor(staticName = "of")
@Schema(description = "시즌 응답")
public class SeasonResponse {

    @Schema(description = "시즌 ID")
    UUID id;

    @Schema(description = "연도")
    Integer year;

    @Schema(description = "학기")
    Integer semester;

    @Schema(description = "시작 일시")
    LocalDateTime startedAt;

    @Schema(description = "종료 일시")
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

    public static SeasonResponse empty() {

        return SeasonResponse.of(null, null, null, null, null);
    }
}
