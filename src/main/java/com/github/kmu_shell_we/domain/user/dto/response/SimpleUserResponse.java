package com.github.kmu_shell_we.domain.user.dto.response;

import com.github.kmu_shell_we.domain.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@Schema(description = "간략화된 유저 응답")
@AllArgsConstructor(staticName = "of")
public class SimpleUserResponse {

    @Schema(description = "ID")
    UUID id;

    @Schema(description = "이름")
    String name;

    @Schema(description = "프로필 사진")
    String avatar;

    public static SimpleUserResponse from(User user) {

        return SimpleUserResponse.of(user.getId(), user.getName(), user.getAvatar());
    }
}
