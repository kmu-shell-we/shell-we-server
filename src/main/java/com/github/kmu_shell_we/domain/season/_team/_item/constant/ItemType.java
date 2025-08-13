package com.github.kmu_shell_we.domain.season._team._item.constant;

import lombok.Getter;

@Getter
public enum ItemType {

    NEXT_MISSION_EXP_2X("경험치 2배권", "다음 미션 완료시 경험치를 2배로 획득합니다"),
    MISSION_RESET("미션 재배정권", "현재 진행중인 미션 중 하나를 새로운 미션으로 변경합니다"),
    DECREASE_OTHER_TEAM_EXP("상대팀 견제권", "다른 팀들의 경험치를 감소시킵니다", 50);

    private final String name;
    private final String description;
    private final Integer decreaseAmount;

    ItemType(String name, String description) {
        this(name, description, null);
    }

    ItemType(String name, String description, Integer decreaseAmount) {
        this.name = name;
        this.description = description;
        this.decreaseAmount = decreaseAmount;
    }

}