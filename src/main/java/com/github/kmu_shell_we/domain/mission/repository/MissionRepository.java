package com.github.kmu_shell_we.domain.mission.repository;

import com.github.kmu_shell_we.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MissionRepository extends JpaRepository<Mission, UUID> {

}
