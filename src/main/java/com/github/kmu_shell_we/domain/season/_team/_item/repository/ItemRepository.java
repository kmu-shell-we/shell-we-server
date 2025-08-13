package com.github.kmu_shell_we.domain.season._team._item.repository;

import com.github.kmu_shell_we.domain.season._team._item.entity.Item;
import com.github.kmu_shell_we.domain.season._team.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {

    List<Item> findAllByTeam(Team team);
}
