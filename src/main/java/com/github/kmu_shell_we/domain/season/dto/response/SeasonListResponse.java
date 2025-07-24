package com.github.kmu_shell_we.domain.season.dto.response;

import com.github.kmu_shell_we.domain.season.entity.Season;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor(staticName = "of")
public class SeasonListResponse {

    List<SimpleSeasonResponse> seasons;

    public static SeasonListResponse from(List<Season> seasons) {

        return SeasonListResponse.of(seasons.stream().map(SimpleSeasonResponse::from).toList());
    }

}
