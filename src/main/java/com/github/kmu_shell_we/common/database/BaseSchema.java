package com.github.kmu_shell_we.common.database;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public abstract class BaseSchema {

    @Id
    @Column(nullable = false, updatable = false, columnDefinition = "BINARY(16)")
    @NotNull
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    UUID id;

    @Column(nullable = false, updatable = false)
    @NotNull
    @CreatedDate
    LocalDateTime createdAt;

    @Column(nullable = false)
    @NotNull
    @LastModifiedDate
    LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BaseSchema that = (BaseSchema) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
