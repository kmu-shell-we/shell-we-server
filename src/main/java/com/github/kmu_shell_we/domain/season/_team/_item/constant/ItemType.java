package com.github.kmu_shell_we.domain.season._team._item.constant;

import com.github.kmu_shell_we.domain.season._team._item.dto.response.*;
import com.github.kmu_shell_we.domain.season._team._item.dto.response.detail.BoomResponse;
import com.github.kmu_shell_we.domain.season._team._item.dto.response.detail.ExperienceDoubleResponse;
import com.github.kmu_shell_we.domain.season._team._item.dto.response.detail.MissionChangeResponse;
import com.github.kmu_shell_we.domain.season._team._item.dto.response.detail.ScoreDeductionResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ItemType {

    BOOM(BoomResponse.class, "꽝", "꽝입니다"),
    EXPERIENCE_DOUBLE(ExperienceDoubleResponse.class, "경험치 2배권", "다음 미션 완료시 경험치를 2배로 획득합니다"),
    MISSION_CHANGE(MissionChangeResponse.class, "미션 재배정권", "현재 진행중인 미션 중 하나를 새로운 미션으로 변경합니다"),
    SCORE_DEDUCTION(ScoreDeductionResponse.class, "상대팀 견제권", "다른 팀들의 경험치를 감소시킵니다"),
    ;

    private final Class<? extends ItemResponse> clazz;
    private final String name;
    private final String description;
}
