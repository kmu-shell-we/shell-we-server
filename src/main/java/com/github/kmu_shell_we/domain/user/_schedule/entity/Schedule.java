package com.github.kmu_shell_we.domain.user._schedule.entity;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.kmu_shell_we.domain.user.entity.User;
import com.github.kmu_shell_we.global.infra.mysql.BaseSchema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.function.Function;

@Entity
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)

public class Schedule extends BaseSchema {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    DayOfWeek dayOfWeek;

    @Column(nullable = false)
    @NotNull
    LocalTime startedAt;

    @Column(nullable = false)
    @NotNull
    LocalTime endedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    public static Schedule fromEverytime(JsonNode node, User user) {

        Function<Integer, LocalTime> fromEverytimeTime = time -> {

            int minute = time * 5;

            int hour = minute / 60;
            minute %= 60;

            return LocalTime.of(hour, minute, 0);
        };

        return Schedule.builder()
                .dayOfWeek(DayOfWeek.of(Integer.parseInt(node.get("day").asText()) + 1))
                .startedAt(fromEverytimeTime.apply(Integer.parseInt(node.get("starttime").asText())))
                .endedAt(fromEverytimeTime.apply(Integer.parseInt(node.get("endtime").asText())))
                .user(user)
                .build();
    }
}
