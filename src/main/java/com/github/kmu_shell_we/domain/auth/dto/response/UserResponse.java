package com.github.kmu_shell_we.domain.auth.dto.response;

import com.github.kmu_shell_we.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor(staticName = "of")
public class UserResponse {

    UUID id;
    LocalDateTime createdAt;
    String studentId;
    String name;
    String avatar;
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
