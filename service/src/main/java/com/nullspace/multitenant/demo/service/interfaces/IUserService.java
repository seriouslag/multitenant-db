package com.nullspace.multitenant.demo.service.interfaces;

import com.nullspace.multitenant.demo.models.entities.User;

import java.util.List;

public interface IUserService extends ICrudService<User, Long> {
    User update(User user);

    User findById(Long id);

    User findByUsername(String username);

    boolean existsByUsername(String username);

    List<User> findAll();

    boolean exists(Long id);
}
