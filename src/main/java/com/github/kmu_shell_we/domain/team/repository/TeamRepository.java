package com.github.kmu_shell_we.domain.team.repository;

import com.github.kmu_shell_we.domain.season.entity.Season;
import com.github.kmu_shell_we.domain.team.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TeamRepository extends JpaRepository<Team, UUID> {

    List<Team> findAllBySeason(Season season);
}
