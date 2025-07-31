package com.github.kmu_shell_we.domain.mission.dto.response;

import com.github.kmu_shell_we.domain.mission._season_mission.entity.SeasonMission;
import com.github.kmu_shell_we.domain.mission.entity.Mission;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor(staticName = "of")
@Schema(description = "미션 목록 응답")
public class MissionListResponse {

    @Schema(description = "미션 목록")
    List<MissionResponse> missions;

    public static MissionListResponse from(List<Mission> missions) {

        return MissionListResponse.of(missions.stream().map(MissionResponse::from).toList());
    }

    public static MissionListResponse fromSeasonMission(List<SeasonMission> seasonMissions) {

        return MissionListResponse.of(seasonMissions.stream().map(MissionResponse::from).toList());
    }
}
