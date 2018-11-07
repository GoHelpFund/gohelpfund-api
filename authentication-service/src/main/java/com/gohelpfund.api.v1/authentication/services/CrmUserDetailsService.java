package com.gohelpfund.api.v1.authentication.services;

import com.gohelpfund.api.v1.authentication.model.CrmUserDetails;
import com.gohelpfund.api.v1.authentication.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CrmUserDetailsService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(CrmUserDetailsService.class);

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {

        User user = userService.getUser(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username " + username + " not found");
        }
        return new CrmUserDetails(user);
    }
}