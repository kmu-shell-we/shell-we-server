package com.github.kmu_shell_we.global.security.jwt;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.kmu_shell_we.domain.auth.exception.AuthExceptions;
import com.github.kmu_shell_we.global.exception.ApiException;
import com.github.kmu_shell_we.global.response.ApiResponse;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;

    private final JwtUtil jwtUtil;

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(
            @Nonnull HttpServletRequest request,
            @Nonnull HttpServletResponse response,
            @Nonnull FilterChain filterChain
    ) throws ServletException, IOException {

        Optional<String> accessToken = extractTokenFromRequest(request);

        try {
            if (accessToken.isPresent() && jwtUtil.validateToken(accessToken.get())) {

                String id = jwtUtil.extractId(accessToken.get());

                UserDetails userDetails = userDetailsService.loadUserByUsername(id);

                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (TokenExpiredException e) {

            handleException(response, AuthExceptions.ACCESS_TOKEN_EXPIRED.toException());
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

    private Optional<String> extractTokenFromRequest(HttpServletRequest request) {

        String authorization = request.getHeader("Authorization");

        return (Objects.nonNull(authorization) && authorization.startsWith("Bearer "))
                ? Optional.of(authorization.substring(7))
                : Optional.empty();
    }

    private void handleException(HttpServletResponse response, ApiException e) throws IOException {

        ApiResponse<?> apiResponse = ApiResponse.error(e.getMessage());
        String content = objectMapper.writeValueAsString(apiResponse);

        response.setContentType("application/json");
        response.getWriter().write(content);
        response.getWriter().flush();
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {

        String uri = request.getRequestURI();

        return Stream.of("/api/auth/refresh-token").anyMatch(uri::equalsIgnoreCase);
    }
}
