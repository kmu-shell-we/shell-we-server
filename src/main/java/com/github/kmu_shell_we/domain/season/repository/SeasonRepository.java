package com.github.kmu_shell_we.domain.season.repository;

import com.github.kmu_shell_we.domain.season.entity.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface SeasonRepository extends JpaRepository<Season, UUID> {

    @Query("SELECT s FROM Season s WHERE s.startedAt < :current and s.endedAt > :current")
    Optional<Season> findCurrent(LocalDateTime current);
}
