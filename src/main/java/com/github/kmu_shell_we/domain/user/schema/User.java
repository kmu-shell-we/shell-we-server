package com.github.kmu_shell_we.domain.user.schema;

import com.github.kmu_shell_we.common.database.BaseSchema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
        ADMIN(MEMBER), // ADMIN은 MEMBER의 권한을 상속받음
        ;

        // 상속된 역할들
        private final Role[] inheritedRoles;

        // 생성자
        Role(Role... inheritedRoles) {
            this.inheritedRoles = inheritedRoles;
        }

        // 권한 리스트 반환
        public Collection<SimpleGrantedAuthority> getAuthorities() {
            Set<Role> authorization = new HashSet<>();
            collectionAuthorization(this, authorization);
            return authorization.stream()
                    .map(role -> "ROLE_" + role.name())
                    .map(SimpleGrantedAuthority::new)
                    .toList();
        }

        // 재귀적으로 상속된 역할 모두 수집
        private void collectionAuthorization(Role role, Set<Role> roles) {
            roles.add(role); // 자기 자신을 추가
            for (Role inheritedRole : role.inheritedRoles) {
                collectionAuthorization(inheritedRole, roles); // 상속된 역할도 재귀적으로 수집
            }
        }
    }

}
