package com.nullspace.multitenant.services.interfaces;

import com.nullspace.multitenant.models.entities.User;
import com.nullspace.multitenant.models.requests.UserRequest;
import com.nullspace.multitenant.services.exceptions.AlreadyExists;

import java.util.List;

public interface IUserService extends ICrudService<User, Long> {
    User update(User user);

    User findById(Long id);

    User findByUsername(String username);

    boolean existsByUsername(String username);

    List<User> findAll();

    boolean exists(Long id);

    User save(UserRequest userRequest) throws AlreadyExists;
}
