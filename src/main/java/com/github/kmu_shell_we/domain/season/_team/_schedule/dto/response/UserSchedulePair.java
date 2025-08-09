package com.github.kmu_shell_we.domain.season._team._schedule.dto.response;

import com.github.kmu_shell_we.domain.user._schedule.dto.response.ScheduleResponse;
import com.github.kmu_shell_we.domain.user._schedule.entity.Schedule;
import com.github.kmu_shell_we.domain.user.dto.response.SimpleUserResponse;
import com.github.kmu_shell_we.domain.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "개별 유저 시간표 응답 DTO")
@AllArgsConstructor(staticName = "of")
public class UserSchedulePair {

    private final SimpleUserResponse user;
    private final ScheduleResponse schedule;

    public static UserSchedulePair from(User user, List<Schedule> schedules) {

        return UserSchedulePair.of(
                SimpleUserResponse.from(user),
                ScheduleResponse.from(schedules)
        );
    }
}
