package com.github.kmu_shell_we.domain.user.entity;

import com.github.kmu_shell_we.global.infra.mysql.BaseSchema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseSchema {

    @Column(unique = true, nullable = false)
    String providerId;

    @Column(unique = true, nullable = false)
    String studentId;

    @Column(nullable = false)
    String name;

    @Column
    String avatar;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    Role role;

    public enum Role {

        MEMBER,
        ADMIN,
    }
}
