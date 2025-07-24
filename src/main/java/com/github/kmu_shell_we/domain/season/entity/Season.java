package com.github.kmu_shell_we.domain.season.entity;

import com.github.kmu_shell_we.global.infra.mysql.BaseSchema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"year", "semester"})})
@Getter
@Setter
@Builder(toBuilder = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Season extends BaseSchema {

    @Column(nullable = false)
    @NotNull
    Integer year;

    @Column(nullable = false)
    @NotNull
    Integer semester;

    @Column(nullable = false)
    @NotNull
    LocalDateTime startedAt;

    @Column(nullable = false)
    @NotNull
    LocalDateTime endedAt;
}