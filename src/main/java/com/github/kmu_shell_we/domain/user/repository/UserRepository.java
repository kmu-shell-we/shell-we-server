package com.github.kmu_shell_we.domain.user.repository;

import com.github.kmu_shell_we.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByProviderId(String providerId);
}
