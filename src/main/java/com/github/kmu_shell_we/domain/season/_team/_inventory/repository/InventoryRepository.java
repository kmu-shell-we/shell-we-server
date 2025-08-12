package com.github.kmu_shell_we.domain.season._team._inventory.repository;

import com.github.kmu_shell_we.domain.season._team._inventory.entity.Inventory;
import com.github.kmu_shell_we.domain.season._team.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface InventoryRepository extends JpaRepository<Inventory, UUID> {

    Optional<Inventory> findByTeam(Team team);
}
