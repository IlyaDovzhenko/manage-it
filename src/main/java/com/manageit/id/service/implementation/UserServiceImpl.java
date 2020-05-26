package com.manageit.id.service.implementation;

import com.manageit.id.model.Role;
import com.manageit.id.model.Status;
import com.manageit.id.model.User;
import com.manageit.id.repository.RoleRepository;
import com.manageit.id.repository.UserRepository;
import com.manageit.id.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           BCryptPasswordEncoder passEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passEncoder = passEncoder;
    }

    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName("RoleUser");

        user.setPassword(passEncoder.encode(user.getPassword()));
        user.getRoles().add(roleUser);
        user.setStatus(Status.ACTIVE);

        User registeredUser = userRepository.save(user);

        log.info("IN register - user: {} is registered!", registeredUser);
        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        log.info("IN getAll - {} users found!", users.size());
        return users;
    }

    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        log.info("IN findByUsername - user: {} found by username: {}", user, username);
        return user;
    }

    @Override
    public User findById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            log.info("IN findById - no user found by id: {}", id);
            return null;
        }
        log.info("IN findById - user: {} find by id: {}", user, id);
        return user;
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
        log.info("IN delete - user with id: {} successfully deleted", id);
    }
}
