package com.nullspace.multitenant.services;

import com.nullspace.multitenant.exceptions.NotFound;
import com.nullspace.multitenant.models.entities.User;
import com.nullspace.multitenant.models.requests.UserRequest;
import com.nullspace.multitenant.repositories.UserRepository;
import com.nullspace.multitenant.services.exceptions.AlreadyExists;
import com.nullspace.multitenant.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User findByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public User findById(Long id) throws AccessDeniedException {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User with id was not found"));
    }

    @Override
    public boolean exists(Long id) {
        return userRepository.existsById(id);
    }


    @Override
    public User update(User updatedUser) {
        User oldUser = userRepository.findById(updatedUser.getId()).orElseThrow(NotFound::new);
        if (!updatedUser.getFirstName().isEmpty()) {
            oldUser.setFirstName(updatedUser.getFirstName());
        }
        if (!updatedUser.getLastName().isEmpty()) {
            oldUser.setLastName(updatedUser.getLastName());
        }

        if (!updatedUser.getEmail().isEmpty()) {
            oldUser.setEmail(updatedUser.getEmail());
        }

        oldUser.setEnabled(updatedUser.isEnabled());
        userRepository.save(oldUser);
        return oldUser;
    }

    @Override
    public User save(User user) throws AlreadyExists {
        if(findByUsername(user.getUsername()) != null) {
            throw new AlreadyExists();
        }
        return userRepository.save(user);
    }

    public User save(UserRequest userRequest) throws AlreadyExists {
        if(findByUsername(userRequest.getUsername()) != null) {
            throw new AlreadyExists();
        }
        User user = new User(userRequest.getFirstName(),
                             userRequest.getLastName(),
                             userRequest.getEmail(),
                             bCryptPasswordEncoder.encode(userRequest.getPassword()));
        return save(user);
    }

    @Override
    public void delete(User object) {
        userRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        userRepository.deleteById(aLong);
    }

    @Override
    public List<User> findAll() throws AccessDeniedException {
        return userRepository.findAll();
    }
}
