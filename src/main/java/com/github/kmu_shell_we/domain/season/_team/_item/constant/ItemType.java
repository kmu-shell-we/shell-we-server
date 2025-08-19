package com.github.kmu_shell_we.domain.season._team._item.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ItemType {

    BOOM("꽝", "꽝입니다"),
    EXPERIENCE_DOUBLE("경험치 2배권", "다음 미션 완료시 경험치를 2배로 획득합니다"),
    MISSION_CHANGE("미션 재배정권", "현재 진행중인 미션 중 하나를 새로운 미션으로 변경합니다"),
    SCORE_DEDUCTION("상대팀 견제권", "다른 팀들의 경험치를 감소시킵니다"),
    ;

    private final String name;
    private final String description;
}
