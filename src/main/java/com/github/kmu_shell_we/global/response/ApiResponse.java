package com.github.kmu_shell_we.global.response;

import com.github.kmu_shell_we.global.exception.ApiException;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse<T> {

    private final boolean success;
    private final String code;
    private final String message;
    private final T data;

    public static ApiResponse<Void> ok() {

        return ok(null);
    }

    public static <T> ApiResponse<T> ok(T data) {

        return new ApiResponse<>(true, null, null, data);
    }

    public static <T> ApiResponse<T> error(ApiException e) {

        return error(e.getCode(), e.getMessage());
    }

    public static <T> ApiResponse<T> error(String code, String message) {

        return new ApiResponse<>(false, code, message, null);
    }
}