package com.github.kmu_shell_we.domain.season._team._item.exception;

import com.github.kmu_shell_we.global.exception.ApiExceptionCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ItemExceptions implements ApiExceptionCode {

    FAILED_TO_DRAW("ITEM_001", "아이템을 뽑는데 실패했습니다."),
    NOT_ENOUGH_POINT("ITEM_001", "포인트가 부족합니다."),
    ;

    private final String code;
    private final String message;
}
