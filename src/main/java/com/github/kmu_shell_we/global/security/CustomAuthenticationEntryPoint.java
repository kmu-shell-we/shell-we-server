package com.github.kmu_shell_we.global.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.kmu_shell_we.domain.auth.exception.AuthExceptions;
import com.github.kmu_shell_we.global.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException {

        ApiResponse<?> apiResponse = ApiResponse.error(AuthExceptions.NOT_PERMIT.toException());

        String content = objectMapper.writeValueAsString(apiResponse);

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.addHeader("Content-Type", "application/json");
        response.getWriter().write(content);
        response.getWriter().flush();
    }
}