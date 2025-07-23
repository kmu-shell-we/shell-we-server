package com.github.kmu_shell_we.global.exception;

import com.github.kmu_shell_we.global.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ApiResponse<?> apiException(ApiException e) {

        return ApiResponse.error(e);
    }
}