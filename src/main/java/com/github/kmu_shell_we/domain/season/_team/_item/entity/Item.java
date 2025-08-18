package com.github.kmu_shell_we.domain.season._team._item.entity;

import com.github.kmu_shell_we.domain.season._team._item.constant.ItemType;
import com.github.kmu_shell_we.domain.season._team.entity.Team;
import com.github.kmu_shell_we.global.infra.mysql.BaseSchema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Item extends BaseSchema {

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    Team team;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    ItemType type;

    @Column(columnDefinition = "JSON")
    String itemData;
}
