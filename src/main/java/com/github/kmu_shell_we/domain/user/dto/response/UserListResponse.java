package com.github.kmu_shell_we.domain.user.dto.response;

import com.github.kmu_shell_we.domain.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "유저 목록 응답")
@AllArgsConstructor(staticName = "of")
public class UserListResponse {

    @Schema(description = "유저 목록")
    List<SimpleUserResponse> users;

    public static UserListResponse from(List<User> users) {

        return UserListResponse.of(users.stream().map(SimpleUserResponse::from).toList());
    }
}
