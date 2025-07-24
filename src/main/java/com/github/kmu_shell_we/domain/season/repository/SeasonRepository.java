package com.github.kmu_shell_we.domain.season.repository;

import com.github.kmu_shell_we.domain.season.entity.Season;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SeasonRepository extends JpaRepository<Season, UUID> {

    Season findByYearAndSemester(@NotNull int year,@NotNull int semester);
}
