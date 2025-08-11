package com.github.kmu_shell_we.domain.mission.entity;

import com.github.kmu_shell_we.domain.mission._season_mission.entity.SeasonMission;
import com.github.kmu_shell_we.domain.mission.constant.MissionType;
import com.github.kmu_shell_we.global.infra.mysql.BaseSchema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Mission extends BaseSchema {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    MissionType type;

    @Column(nullable = false)
    @NotNull
    String name;

    @Column(nullable = false)
    @NotNull
    Integer reward;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    List<SeasonMission> seasonMissions = List.of();
}
