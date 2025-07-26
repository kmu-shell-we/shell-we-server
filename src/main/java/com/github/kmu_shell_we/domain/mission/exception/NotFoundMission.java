package com.github.kmu_shell_we.domain.mission.exception;

import com.github.kmu_shell_we.global.exception.ApiException;

public class NotFoundMission extends ApiException {

    public NotFoundMission() {

        super("MISSION_001","미션을 찾을 수 없습니다.");
    }
}
