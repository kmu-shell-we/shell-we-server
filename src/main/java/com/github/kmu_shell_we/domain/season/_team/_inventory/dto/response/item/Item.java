package com.github.kmu_shell_we.domain.season._team._inventory.dto.response.item;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Item.NextMissionExp2x.class,      name = "NEXT_MISSION_EXP_2X"),
        @JsonSubTypes.Type(value = Item.DecreaseOtherTeamExp.class,  name = "DECREASE_OTHER_TEAM_EXP"),
        @JsonSubTypes.Type(value = Item.MissionReset.class,          name = "MISSION_RESET")
})
public interface Item {

    @JsonTypeName("NEXT_MISSION_EXP_2X")
    record NextMissionExp2x(
            String type,
            String name,
            String description
    ) implements Item {
        public NextMissionExp2x() {
            this("NEXT_MISSION_EXP_2X",
                    "경험치 2배권",
                    "다음 미션 완료시 경험치를 2배로 획득합니다");
        }
    }

    @JsonTypeName("DECREASE_OTHER_TEAM_EXP")
    record DecreaseOtherTeamExp(
            String type,
            String name,
            String description,
            int decreaseAmount
    ) implements Item {
        public DecreaseOtherTeamExp() {
            this("DECREASE_OTHER_TEAM_EXP",
                    "상대팀 견제권",
                    "다른 팀들의 경험치를 감소시킵니다",
                    50);
        }
    }

    @JsonTypeName("MISSION_RESET")
    record MissionReset(
            String type,
            String name,
            String description
    ) implements Item {
        public MissionReset() {
            this("MISSION_RESET",
                    "미션 재배정권",
                    "현재 진행중인 미션 중 하나를 새로운 미션으로 변경합니다");
        }
    }
}
