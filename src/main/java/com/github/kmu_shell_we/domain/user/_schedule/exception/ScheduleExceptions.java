package com.github.kmu_shell_we.domain.user._schedule.exception;

import com.github.kmu_shell_we.global.exception.ApiExceptionCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ScheduleExceptions implements ApiExceptionCode {

    NOT_FOUND_SCHEDULE("SCHEDULE_001", "시간표를 불러올 수 없습니다."),
    SCHEDULE_PARSE_FAILED("SCHEDULE_002", "시간표를 파싱하는데 실패했습니다.")
    ;

    private final String code;
    private final String message;
}
