package com.github.kmu_shell_we.domain.user.service;

import com.github.kmu_shell_we.domain.user.dto.response.UserListResponse;
import com.github.kmu_shell_we.domain.user.dto.response.UserResponse;
import com.github.kmu_shell_we.domain.user.entity.User;
import com.github.kmu_shell_we.domain.user.exception.UserExceptions;
import com.github.kmu_shell_we.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminUserService {

    private final UserRepository userRepository;

    public UserListResponse getUsers() {

        List<User> users = userRepository.findAll();

        return UserListResponse.from(users);
    }

    public UserResponse getUser(UUID userId) {

        User user = userRepository.findById(userId).orElseThrow(UserExceptions.NOT_FOUND_USER::toException);

        return UserResponse.from(user);
    }
}
