package com.github.kmu_shell_we.domain.auth.exception;

import com.github.kmu_shell_we.global.exception.ApiExceptionCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthExceptions implements ApiExceptionCode {

    NOT_PERMIT("AUTH-001", "권한이 없습니다."),
    AUTHENTICATION_FAILED("AUTH-002", "인증에 실패했습니다."),
    ACCESS_TOKEN_EXPIRED("AUTH-003", "엑세스 토큰이 만료되었습니다."),
    ;

    private final String code;
    private final String message;
}