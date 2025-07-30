package com.github.kmu_shell_we.domain.team.exception;

import com.github.kmu_shell_we.global.exception.ApiExceptionCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TeamExceptions implements ApiExceptionCode {

    NOT_FOUND("TEAM_001", "팀을 찾을 수 없습니다."),
    ;

    private final String code;
    private final String message;
}
