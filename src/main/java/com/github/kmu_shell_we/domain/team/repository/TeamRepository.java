package com.github.kmu_shell_we.domain.team.repository;

import com.github.kmu_shell_we.domain.season.entity.Season;
import com.github.kmu_shell_we.domain.team.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TeamRepository extends JpaRepository<Team, UUID> {

    Optional<Team> findByIdAndSeason(UUID teamId, Season season);

    List<Team> findAllBySeason(Season season);
}
