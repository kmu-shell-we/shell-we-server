package com.github.kmu_shell_we.domain.season.dto.response;

import com.github.kmu_shell_we.domain.season.entity.Season;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor(staticName = "of")
@Schema(description = "간략화된 시즌 응답")
public class SimpleSeasonResponse {

    @Schema(description = "시즌 ID")
    UUID id;

    @Schema(description = "연도")
    Integer year;

    @Schema(description = "학기")
    Integer semester;

    public static SimpleSeasonResponse from(Season season) {

        return SimpleSeasonResponse.of(season.getId(), season.getYear(), season.getSemester());
    }
}
