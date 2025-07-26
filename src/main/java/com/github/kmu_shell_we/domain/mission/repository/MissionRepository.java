package com.github.kmu_shell_we.domain.mission.repository;

import com.github.kmu_shell_we.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MissionRepository extends JpaRepository<Mission, UUID> {

}
