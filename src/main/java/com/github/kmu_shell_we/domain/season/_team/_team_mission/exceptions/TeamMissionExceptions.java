package com.github.kmu_shell_we.domain.season._team._team_mission.exceptions;

import com.github.kmu_shell_we.global.exception.ApiExceptionCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TeamMissionExceptions implements ApiExceptionCode {

    NOT_FOUND_TEAM_MISSION("TEAM_MISSION_001", "해당 팀 미션을 찾을 수 없습니다."),
    ;

    private final String code;
    private final String message;
}