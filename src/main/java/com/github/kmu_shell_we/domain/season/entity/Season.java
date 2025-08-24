package com.github.kmu_shell_we.domain.season.entity;

import com.github.kmu_shell_we.domain.mission._season_mission.entity.SeasonMission;
import com.github.kmu_shell_we.domain.season._team.entity.Team;
import com.github.kmu_shell_we.global.infra.mysql.BaseSchema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"year", "semester"})})
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Season extends BaseSchema {

    @Column(nullable = false)
    @NotNull
    Integer year;

    @Column(nullable = false)
    @NotNull
    Integer semester;

    @Column(nullable = false)
    @NotNull
    LocalDateTime startedAt;

    @Column(nullable = false)
    @NotNull
    LocalDateTime endedAt;

    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    List<Team> teams = List.of();

    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    List<SeasonMission> seasonMissions = List.of();

    public boolean isCurrentSeason() {

        LocalDateTime now = LocalDateTime.now();

        return now.isAfter(startedAt) && now.isBefore(endedAt);
    }
}

// season.getSeasonMissions