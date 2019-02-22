package com.nullspace.multitenant.repositories;

import com.nullspace.multitenant.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String email);

    boolean existsById(Long Id);

    boolean existsByUsername(String username);
}

