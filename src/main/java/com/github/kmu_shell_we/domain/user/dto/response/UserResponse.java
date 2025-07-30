package com.github.kmu_shell_we.domain.user.dto.response;

import com.github.kmu_shell_we.domain.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Schema(description = "유저 응답")
@AllArgsConstructor(staticName = "of")
public class UserResponse {

    @Schema(description = "ID")
    UUID id;

    @Schema(description = "생성 시간")
    LocalDateTime createdAt;

    @Schema(description = "학번")
    String studentId;

    @Schema(description = "이름")
    String name;

    @Schema(description = "프로필 사진")
    String avatar;

    @Schema(description = "역할")
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
