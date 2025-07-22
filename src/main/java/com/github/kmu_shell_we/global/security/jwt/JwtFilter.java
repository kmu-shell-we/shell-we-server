package com.github.kmu_shell_we.global.security.jwt;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.kmu_shell_we.domain.auth.exception.AuthExceptionCode;
import com.github.kmu_shell_we.global.exception.ApiException;
import com.github.kmu_shell_we.global.response.ApiResponse;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(
            @Nonnull HttpServletRequest request,
            @Nonnull HttpServletResponse response,
            @Nonnull FilterChain filterChain
    ) throws ServletException, IOException {

        String accessToken = extractTokenFromRequest(request);

        try {
            if (accessToken != null && jwtTokenProvider.validateToken(accessToken)) {
                Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (TokenExpiredException e) {
            handleException(response, AuthExceptionCode.ACCESS_TOKEN_EXPIRED.toException());
            return;
        } catch (ApiException e) {
            handleException(response, e);
            return;
        } catch (Exception e) {
            handleException(response, new ApiException("JWT 인증 처리 중 오류 발생"));
            return;
        }

        filterChain.doFilter(request, response);
    }

    private String extractTokenFromRequest(HttpServletRequest request) {

        String authorization = request.getHeader("Authorization");
        return (authorization != null && authorization.startsWith("Bearer "))
                ? authorization.substring(7) : null;
    }

    private void handleException(HttpServletResponse response, ApiException e) throws IOException {

        ApiResponse<?> apiResponse = ApiResponse.error(e.getMessage());
        String content = objectMapper.writeValueAsString(apiResponse);

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(content);
        response.getWriter().flush();
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {

        String uri = request.getRequestURI();

        return Stream.of("/api/auth/refresh-token").anyMatch(uri::equalsIgnoreCase);
    }
}
