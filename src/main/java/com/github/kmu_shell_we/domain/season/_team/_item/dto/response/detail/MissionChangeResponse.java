package com.github.kmu_shell_we.domain.season._team._item.dto.response.detail;

import com.github.kmu_shell_we.domain.mission.dto.response.SimpleMissionResponse;
import com.github.kmu_shell_we.domain.mission.entity.Mission;
import com.github.kmu_shell_we.domain.season._team._item.constant.ItemType;
import com.github.kmu_shell_we.domain.season._team._item.dto.response.ItemResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Schema(description = "[아이템] 미션 변경 응답 DTO")
@EqualsAndHashCode(callSuper = true)
public class MissionChangeResponse extends ItemResponse {

    @Schema(description = "이전 미션")
    SimpleMissionResponse previous;

    @Schema(description = "이후 미션")
    SimpleMissionResponse after;

    public static MissionChangeResponse of(SimpleMissionResponse previous, SimpleMissionResponse after) {

        MissionChangeResponse response = new MissionChangeResponse();

        response.setType(ItemType.MISSION_CHANGE);
        response.setPrevious(previous);
        response.setAfter(after);

        return response;
    }

    public static MissionChangeResponse from(Mission previous, Mission after) {

        return of(SimpleMissionResponse.from(previous), SimpleMissionResponse.from(after));
    }
}
