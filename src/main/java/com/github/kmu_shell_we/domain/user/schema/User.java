package com.github.kmu_shell_we.domain.user.schema;

import com.github.kmu_shell_we.common.database.BaseSchema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@EqualsAndHashCode(callSuper = true)
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

    @Column
    @NotNull
    Role role;

    public enum Role {
        MEMBER,
        ADMIN, // ADMIN은 MEMBER의 권한을 상속받음
        ;
    }

}
