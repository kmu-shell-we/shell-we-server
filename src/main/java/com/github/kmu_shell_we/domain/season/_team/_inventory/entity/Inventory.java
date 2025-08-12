package com.github.kmu_shell_we.domain.season._team._inventory.entity;

import com.github.kmu_shell_we.domain.season._team._inventory.constant.ItemType;
import com.github.kmu_shell_we.domain.season._team.entity.Team;
import com.github.kmu_shell_we.global.infra.mysql.BaseSchema;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Inventory extends BaseSchema {

    @OneToOne
    @JoinColumn(name = "team_id", nullable = false, unique = true)
    Team team;

    @Enumerated(EnumType.STRING)
    @Column(name = "item")
    List<ItemType> items;
}
