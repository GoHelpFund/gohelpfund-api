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
        List<UserRole> roles = repository.findByUsername(username);
        if (roles == null) {
            logger.debug("GET | PostgreSQL | not found | roles size: 0");
        } else {
            logger.debug("GET | PostgreSQL | found | roles size: {}", roles.size());
        }
        return roles;
    }

    public List<UserRole> saveAll(String username, List<UserRole> userRoles) {
        List<UserRole> newUserRoles = new ArrayList<>();

        if (userRoles != null && !userRoles.isEmpty()) {
            for (UserRole userRole : userRoles) {
                newUserRoles.add(saveOne(username, userRole));
            }
            logger.debug("POST | PostgreSQL | saved | user_roles size: {} ", newUserRoles.size());
        }
        return newUserRoles;
    }

    public UserRole saveOne(String username, UserRole userRole) {
        final String id = UUID.randomUUID().toString();
        userRole.withId(id);
        userRole.withUsername(username);

        UserRole newRole = repository.save(userRole);
        logger.debug("POST | PostgreSQL | created | user_role id: {} user_name: {} ", newRole.getId(), newRole.getUsername());

        return newRole;
    }

}
