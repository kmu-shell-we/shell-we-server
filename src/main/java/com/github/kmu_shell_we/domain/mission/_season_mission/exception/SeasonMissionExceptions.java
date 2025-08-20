package com.github.kmu_shell_we.domain.mission._season_mission.exception;

import com.github.kmu_shell_we.global.exception.ApiExceptionCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SeasonMissionExceptions implements ApiExceptionCode {

    NOT_FOUND_SEASON_MISSION("SEASON_MISSION_001", "시즌 미션을 찾을 수 없습니다."),
    ALREADY_ADDED_SEASON_MISSION("SEASON_MISSION_002", "이미 추가된 시즌 미션입니다."),
    ;

    private final String code;
    private final String message;
}