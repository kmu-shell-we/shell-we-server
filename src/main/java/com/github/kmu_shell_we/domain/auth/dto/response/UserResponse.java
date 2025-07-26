package com.github.kmu_shell_we.domain.auth.dto.response;

import com.github.kmu_shell_we.domain.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor(staticName = "of")
@Schema(description = "사용자 응답 DTO")
public class UserResponse {

    @Schema(description = "사용자 ID", example = "550e8400-e29b-41d4-a716-446655440000")
    UUID id;

    @Schema(description = "사용자 생성 시간", example = "2023-10-01T12:00:00")
    LocalDateTime createdAt;

    @Schema(description = "학번", example = "20250001")
    String studentId;

    @Schema(description = "사용자 이름", example = "홍길동")
    String name;

    @Schema(description = "사용자 아바타 URL", example = "https://example.com/avatar.png")
    String avatar;

    @Schema(description = "사용자 역할", example = "ADMIN'")
    User.Role role;

    public static UserResponse from(User user) {

        return UserResponse.of(
                user.getId(),
                user.getCreatedAt(),
                user.getStudentId(),
                user.getName(),
                user.getAvatar(),
                user.getRole()
        );
    }
}
