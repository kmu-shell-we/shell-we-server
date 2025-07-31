package com.github.kmu_shell_we.domain.season._team._user_team.repository;

import com.github.kmu_shell_we.domain.season._team._user_team.entity.UserTeam;
import com.github.kmu_shell_we.domain.season._team.entity.Team;
import com.github.kmu_shell_we.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserTeamRepository extends JpaRepository<UserTeam, UUID> {

    void deleteByUserAndTeam(User user, Team team);
}
