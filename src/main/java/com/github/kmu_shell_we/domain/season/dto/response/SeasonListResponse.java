package com.github.kmu_shell_we.domain.season.dto.response;

import com.github.kmu_shell_we.domain.season.entity.Season;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor(staticName = "of")
@Schema(description = "학기 목록 응답 DTO")
public class SeasonListResponse {

    @Schema(description = "학기 목록")
    List<SimpleSeasonResponse> seasons;

    public static SeasonListResponse from(List<Season> seasons) {

        return SeasonListResponse.of(seasons.stream().map(SimpleSeasonResponse::from).toList());
    }

}
