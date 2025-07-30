package com.github.kmu_shell_we.domain.mission.dto.response;

import com.github.kmu_shell_we.domain.mission.constant.MissionType;
import com.github.kmu_shell_we.domain.mission.entity.Mission;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor(staticName = "of")
@Schema(description = "미션 응답 DTO")
public class MissionResponse {

    @Schema(description = "미션 ID", example = "550e8400-e29b-41d4-a716-446655440000")
    UUID id;
    
    @Schema(description = "미션 이름", example = "학식 먹기")
    String name;

    @Schema(description = "미션 보상", example = "100")
    Integer reward;

    @Schema(description = "미션 타입", example = "DAILY")
    MissionType type;

    public static MissionResponse from(Mission mission) {
        
        return MissionResponse.of(
                mission.getId(),
                mission.getName(),
                mission.getReward(),
                mission.getType()
        );
    }
}
