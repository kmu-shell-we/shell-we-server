package com.github.kmu_shell_we.global.security.user_details;

import com.github.kmu_shell_we.domain.auth.exception.AuthExceptions;
import com.github.kmu_shell_we.domain.user.repository.UserRepository;
import com.github.kmu_shell_we.domain.user.schema.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

        User user = userRepository.findById(UUID.fromString(id))
                        .orElseThrow(AuthExceptions.AUTHENTICATION_FAILED::toException);

        return UserDetailsImpl.of(user);
    }
}
