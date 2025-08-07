package com.github.kmu_shell_we.domain.user._schedule.exception;

import com.github.kmu_shell_we.global.exception.ApiExceptionCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ScheduleExceptions implements ApiExceptionCode {

    NOT_FOUND_SCHEDULE("SCHEDULE_001", "시간표를 찾을 수 없습니다."),
    ;

    private final String code;
    private final String message;
}
