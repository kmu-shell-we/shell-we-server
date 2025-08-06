package com.github.kmu_shell_we.domain.user._schedule.entity;

import com.github.kmu_shell_we.domain.user.entity.User;
import com.github.kmu_shell_we.global.infra.mysql.BaseSchema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Schedule extends BaseSchema {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    Schedule.DayOfWeek dayOfWeek;

    @Column(nullable = false)
    @NotBlank
    String startedAt;

    @Column(nullable = false)
    @NotBlank
    String endedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    public enum DayOfWeek {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    }
}
