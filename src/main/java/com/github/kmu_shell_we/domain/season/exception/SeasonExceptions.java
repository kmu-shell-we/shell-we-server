package com.github.kmu_shell_we.domain.season.exception;

import com.github.kmu_shell_we.global.exception.ApiExceptionCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SeasonExceptions implements ApiExceptionCode {

    NOT_FOUND_SEASON("SEASON_001", "시즌을 찾을 수 없습니다."),
    NOT_FOUND_CURRENT_SEASON("SEASON_002", "진행중인 시즌을 찾을 수 없습니다."),
    ;

    private final String code;
    private final String message;
}