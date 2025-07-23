package com.github.kmu_shell_we.global.security.authentication;

import com.github.kmu_shell_we.domain.user.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class UserAuthentication implements Authentication {

    private final User user;

    @Getter
    @Setter
    private boolean authenticated = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
    }

    @Override
    public String getName() {

        return user.getName();
    }

    @Override
    public User getPrincipal() {

        return user;
    }

    @Override
    public Object getDetails() {

        return null;
    }

    @Override
    public String getCredentials() {

        return null;
    }
}