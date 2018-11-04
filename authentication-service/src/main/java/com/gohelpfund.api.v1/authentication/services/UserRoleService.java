package com.gohelpfund.api.v1.authentication.services;

import com.gohelpfund.api.v1.authentication.model.UserRole;
import com.gohelpfund.api.v1.authentication.repository.UserRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserRoleService {
    private static final Logger logger = LoggerFactory.getLogger(UserRoleService.class);

    @Autowired
    private UserRoleRepository repository;

    public List<UserRole> getAll(String username) {
        return repository.findByUsername(username);
    }

    public List<UserRole> saveAll(List<UserRole> userRoles, String username) {
        List<UserRole> newUserRoles = new ArrayList<>();

        if (userRoles != null && !userRoles.isEmpty()) {
            for (UserRole userRole : userRoles) {
                newUserRoles.add(saveOne(userRole, username));
            }
        }
        return newUserRoles;
    }

    public UserRole saveOne(UserRole userRole, String username) {
        final String id = UUID.randomUUID().toString();
        userRole.withId(id);
        userRole.withUsername(username);

        return repository.save(userRole);
    }

    public UserRole save(String username, String role) {
        String id = UUID.randomUUID().toString();

        UserRole userRole = new UserRole(username)
                .withId(id)
                .withRole(role);

        return repository.save(userRole);
    }
}
