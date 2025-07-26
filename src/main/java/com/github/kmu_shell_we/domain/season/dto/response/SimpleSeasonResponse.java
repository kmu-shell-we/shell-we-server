package com.github.kmu_shell_we.domain.season.dto.response;

import com.github.kmu_shell_we.domain.season.entity.Season;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor(staticName = "of")
@Schema(description = "시즌 응답 DTO")
public class SimpleSeasonResponse {

    @Schema(description = "시즌 ID", example = "550e8400-e29b-41d4-a716-446655440000")
    UUID id;

    @Schema(description = "연도", example = "2025")
    Integer year;

    @Schema(description = "학기 (1: 1학기, 2: 2학기)", example = "1")
    Integer semester;

    public static SimpleSeasonResponse from(Season season) {

        return SimpleSeasonResponse.of(
                season.getId(),
                season.getYear(),
                season.getSemester()
        );
    }
}
