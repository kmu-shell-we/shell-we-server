package com.github.kmu_shell_we.domain.mission.repository;

import com.github.kmu_shell_we.domain.mission.entity.Mission;
import com.github.kmu_shell_we.domain.mission.entity.SeasonMission;
import com.github.kmu_shell_we.domain.season.entity.Season;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SeasonMissionRepository extends JpaRepository<SeasonMission, UUID> {

    SeasonMission findBySeasonAndMission(Season season, Mission mission);

    List<SeasonMission> findAllSeasonMissionsBySeasonId(UUID seasonId);
}
