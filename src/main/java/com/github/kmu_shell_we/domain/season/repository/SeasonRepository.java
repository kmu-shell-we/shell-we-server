package com.github.kmu_shell_we.domain.season.repository;

import com.github.kmu_shell_we.domain.season.entity.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface SeasonRepository extends JpaRepository<Season, UUID> {

    @Query("SELECT s FROM Season s WHERE s.startedAt <= CURRENT_TIMESTAMP AND s.endedAt >= CURRENT_TIMESTAMP")
    Optional<Season> findCurrentSeason();
}
