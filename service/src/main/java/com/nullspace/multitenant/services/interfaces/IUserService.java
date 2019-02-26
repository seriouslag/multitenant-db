package com.nullspace.multitenant.services.interfaces;

import com.nullspace.multitenant.models.entities.User;
import com.nullspace.multitenant.models.requests.UserRequest;
import com.nullspace.multitenant.models.requests.UserUpdateRequest;
import com.nullspace.multitenant.services.exceptions.AlreadyExists;

import java.util.List;

public interface IUserService extends ICrudService<User, Long> {
    User findById(Long id);
    User findByUsername(String username);
    boolean existsByUsername(String username);
    List<User> findAll();
    boolean exists(Long id);
    User update(User updatedUser);
    User update(UserUpdateRequest userUpdateRequest);
    User save(UserRequest userRequest) throws AlreadyExists;
}
