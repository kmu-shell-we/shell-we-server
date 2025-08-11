package com.github.kmu_shell_we.domain.season._team._user_team.exception;

import com.github.kmu_shell_we.global.exception.ApiExceptionCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserTeamExceptions implements ApiExceptionCode {

    NOT_FOUND_USER_TEAM("USER_TEAM_001", "해당 유저가 해당 팀에 속해 있지 않습니다."),
    ALREADY_MEMBER("USER_TEAM_002", "해당 유저는 이미 팀에 속해 있습니다."),
    ;

    private final String code;
    private final String message;
}
