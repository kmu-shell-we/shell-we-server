package com.github.kmu_shell_we.domain.user.exception;

import com.github.kmu_shell_we.global.exception.ApiExceptionCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserExceptionCode implements ApiExceptionCode {

    NOT_FOUND_USER("USER_001", "유저를 찾을 수 없습니다."),
    ;

    private final String code;
    private final String message;
}
