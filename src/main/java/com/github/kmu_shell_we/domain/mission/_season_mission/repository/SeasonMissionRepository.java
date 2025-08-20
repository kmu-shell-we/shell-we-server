package com.github.kmu_shell_we.domain.mission._season_mission.repository;

import com.github.kmu_shell_we.domain.mission._season_mission.entity.SeasonMission;
import com.github.kmu_shell_we.domain.mission.constant.MissionType;
import com.github.kmu_shell_we.domain.mission.entity.Mission;
import com.github.kmu_shell_we.domain.season.entity.Season;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SeasonMissionRepository extends JpaRepository<SeasonMission, UUID> {

    @Nonnull
    @Override
    @EntityGraph(attributePaths = {"season", "mission"})
    Optional<SeasonMission> findById(@Nonnull UUID id);

    @Nonnull
    @Override
    @EntityGraph(attributePaths = {"season", "mission"})
    List<SeasonMission> findAll();

    @EntityGraph(attributePaths = {"season", "mission"})
    Optional<SeasonMission> findBySeasonAndMission(Season season, Mission mission);

    List<SeasonMission> findAllBySeason(Season season);

    List<SeasonMission> findAllBySeasonAndMissionType(Season season, MissionType missionType);

    @Query("SELECT sm FROM SeasonMission sm WHERE sm.season = :season and sm.mission.type = 'DAILY' ORDER BY RAND() LIMIT 1")
    Optional<SeasonMission> findRandomDailyMissionBySeason(Season season);
}