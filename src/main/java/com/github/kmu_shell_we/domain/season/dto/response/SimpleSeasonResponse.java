package com.github.kmu_shell_we.domain.season.dto.response;

import com.github.kmu_shell_we.domain.season.entity.Season;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor(staticName = "of")
public class SimpleSeasonResponse {

    UUID id;
    Integer year;
    Integer semester;

    public static SimpleSeasonResponse from(Season season) {

        return SimpleSeasonResponse.of(
                season.getId(),
                season.getYear(),
                season.getSemester()
        );
    }
}
