package com.github.kmu_shell_we.domain.team._userTeam.entity;

import com.github.kmu_shell_we.domain.team.entity.Team;
import com.github.kmu_shell_we.domain.user.entity.User;
import com.github.kmu_shell_we.global.infra.mysql.BaseSchema;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;


@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UserTeam extends BaseSchema {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",  nullable = false)
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id",   nullable = false)
    Team team;
}
