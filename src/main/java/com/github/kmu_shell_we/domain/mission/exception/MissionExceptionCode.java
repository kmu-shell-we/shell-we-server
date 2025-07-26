package com.github.kmu_shell_we.domain.mission.exception;

import com.github.kmu_shell_we.global.exception.ApiExceptionCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MissionExceptionCode implements ApiExceptionCode {

    NOT_FOUND_MISSION("MISSION_001","미션을 찾을 수 없습니다."),
    ;

    private final String code;
    private final String message;
}