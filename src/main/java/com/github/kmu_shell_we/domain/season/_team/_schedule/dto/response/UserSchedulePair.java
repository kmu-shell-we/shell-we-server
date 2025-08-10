package com.github.kmu_shell_we.domain.season._team._schedule.dto.response;

import com.github.kmu_shell_we.domain.user._schedule.dto.response.TimeRange;
import com.github.kmu_shell_we.domain.user._schedule.entity.Schedule;
import com.github.kmu_shell_we.domain.user.dto.response.SimpleUserResponse;
import com.github.kmu_shell_we.domain.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Schema(description = "개별 유저 시간표 응답 DTO")
@AllArgsConstructor(staticName = "of")
public class UserSchedulePair {

    private final SimpleUserResponse user;
    Map<DayOfWeek, List<TimeRange>> schedules;

    public static UserSchedulePair from(User user) {

        Map<DayOfWeek, List<TimeRange>> collect = user.getSchedules().stream()
                .collect(Collectors.groupingBy(
                        Schedule::getDayOfWeek,
                        Collectors.mapping(TimeRange::from, Collectors.toList())
                ));

        return UserSchedulePair.of(
                SimpleUserResponse.from(user),
                collect
        );
    }
}
