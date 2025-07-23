package com.github.kmu_shell_we.domain.user.entity;

import com.github.kmu_shell_we.global.infra.mysql.BaseSchema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseSchema {

    @Column(unique = true, nullable = false)
    @NotNull
    String studentId;

    @Column(nullable = false)
    @NotNull
    String name;

    @Column(unique = true, nullable = false)
    @NotNull
    String email;

    @Column(unique = true, nullable = false)
    @NotNull
    String phone;

    @Column
    String avatar;

    @Column(nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    Role role;

    public enum Role {

        MEMBER,
        ADMIN,
    }
}
