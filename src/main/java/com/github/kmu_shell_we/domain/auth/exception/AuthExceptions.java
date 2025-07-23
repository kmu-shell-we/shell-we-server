package com.github.kmu_shell_we.domain.auth.exception;

import com.github.kmu_shell_we.global.exception.ApiExceptionCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthExceptions implements ApiExceptionCode {

    AUTHENTICATION_FAILED("인증에 실패했습니다."),
    ACCESS_TOKEN_EXPIRED("엑세스 토큰이 만료되었습니다."),
    ;

    private final String message;
}