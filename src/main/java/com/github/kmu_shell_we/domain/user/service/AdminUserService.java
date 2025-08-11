package com.github.kmu_shell_we.domain.user.service;

import com.github.kmu_shell_we.domain.user.dto.response.UserListResponse;
import com.github.kmu_shell_we.domain.user.entity.User;
import com.github.kmu_shell_we.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminUserService {

    private final UserRepository userRepository;

    public UserListResponse getUsers() {

        List<User> users = userRepository.findAll();

        return UserListResponse.from(users);
    }
}
