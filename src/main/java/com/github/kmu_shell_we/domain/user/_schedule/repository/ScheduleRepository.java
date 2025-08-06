package com.github.kmu_shell_we.domain.user._schedule.repository;

import com.github.kmu_shell_we.domain.user._schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {
}
