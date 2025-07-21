package com.github.kmu_shell_we.global.exception;

public interface ApiExceptionCode {

    String getMessage();

    default ApiException toException() {

        return new ApiException(this);
    }
}