package com.github.kmu_shell_we.domain.season._team.repository;

import com.github.kmu_shell_we.domain.season._team.entity.Team;
import com.github.kmu_shell_we.domain.season.entity.Season;
import com.github.kmu_shell_we.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TeamRepository extends JpaRepository<Team, UUID> {

    List<Team> findAllBySeason(Season season);

    @Query("SELECT t FROM Team t JOIN UserTeam ut ON t = ut.team WHERE ut.user = :user AND t.season = :season")
    Optional<Team> findByUserAndSeason(User user, Season season);

    @Query("SELECT t FROM Team t WHERE t != :team ORDER BY RAND() LIMIT 1")
    Optional<Team> findRandomTeamExcept(Team team);
}
