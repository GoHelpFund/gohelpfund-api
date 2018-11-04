package com.gohelpfund.api.v1.authentication.services;

import com.gohelpfund.api.v1.authentication.model.User;
import com.gohelpfund.api.v1.authentication.model.UserRole;
import com.gohelpfund.api.v1.authentication.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.gohelpfund.api.v1.authentication.security.constants.AuthoritiesConstants.BACKER;
import static com.gohelpfund.api.v1.authentication.security.constants.AuthoritiesConstants.FUNDRAISER;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRoleService userRoleService;

    public User getUser(String username) {
        User user = userRepository.findByUsername(username);
        List<UserRole> roles = userRoleService.getAll(username);
        user.setRoles(roles);

        return user;
    }

    public User addUser(User user) {
        String id = UUID.randomUUID().toString();

        user.setId(id);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);

        List<UserRole> roles =
                userRoleService.saveAll(
                        Arrays.asList(new UserRole(FUNDRAISER), new UserRole(BACKER)),
                        user.getUsername()
                );

        user.withRoles(roles);
        return userRepository
                .save(user);
    }
}
