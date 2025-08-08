package com.github.kmu_shell_we.domain.season._team._schedule.dto.response;

import com.github.kmu_shell_we.domain.user._schedule.dto.response.ScheduleResponse;
import com.github.kmu_shell_we.domain.user.dto.response.SimpleUserResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Schema(description = "팀 시간표 응답 DTO")
@AllArgsConstructor(staticName = "of")
public class TeamScheduleResponse {

    List<Map<SimpleUserResponse, ScheduleResponse>> schedules;
}
