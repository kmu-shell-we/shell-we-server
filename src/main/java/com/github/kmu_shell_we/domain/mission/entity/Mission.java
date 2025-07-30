package com.github.kmu_shell_we.domain.mission.entity;

import com.github.kmu_shell_we.global.infra.mysql.BaseSchema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.nio.charset.Charset;

@Entity
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Mission extends BaseSchema {

    @Column(nullable = false)
    @NotNull
    String name;

    @Column(nullable = false)
    @NotNull
    Integer reward;

    @Column(nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    Mission.MissionType type;

    public enum MissionType {

        DAILY,
        WEEKLY,
        SPECIAL,
    }
}
