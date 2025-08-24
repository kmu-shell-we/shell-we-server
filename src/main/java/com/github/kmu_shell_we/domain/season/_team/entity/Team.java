package com.github.kmu_shell_we.domain.season._team.entity;

import com.github.kmu_shell_we.domain.season._team._item.entity.Item;
import com.github.kmu_shell_we.domain.season._team._user_team.entity.UserTeam;
import com.github.kmu_shell_we.domain.season.entity.Season;
import com.github.kmu_shell_we.global.infra.mysql.BaseSchema;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Team extends BaseSchema {

    @Column
    String name;

    @Column(nullable = false)
    @Builder.Default
    Integer experience = 0;

    @Column(nullable = false)
    @Builder.Default
    Integer point = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id", nullable = false)
    Season season;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    @Setter(AccessLevel.NONE)
    List<UserTeam> userTeams = new ArrayList<>();

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    @Setter(AccessLevel.NONE)
    List<Item> items = new ArrayList<>();
}
