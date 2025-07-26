package com.github.kmu_shell_we.domain.mission.repository;

import com.github.kmu_shell_we.domain.mission.entity.SpecialMission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpecialMissionRepository extends JpaRepository<SpecialMission, UUID> {

}
