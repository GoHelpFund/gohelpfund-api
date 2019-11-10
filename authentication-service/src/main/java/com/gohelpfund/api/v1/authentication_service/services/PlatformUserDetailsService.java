package com.gohelpfund.api.v1.authentication_service.services;

import com.gohelpfund.api.v1.authentication_service.model.PlatformUserDetails;
import com.gohelpfund.api.v1.authentication_service.model.User;
import com.gohelpfund.api.v1.authentication_service.utils.UserContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PlatformUserDetailsService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(PlatformUserDetailsService.class);

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {

        User user = userService.getUser(username);

        if (user != null) {
            logger.debug(user.toString());
            UserContextHolder.getContext().setUserId(user.getFundraiserId());
            UserContextHolder.getContext().setUserPassChanged(String.valueOf(user.isPasswordChanged()));
            UserContextHolder.getContext().setUserType(user.getFundraiserType());

            PlatformUserDetails platformUser = new PlatformUserDetails(user);
            logger.debug("GET | /api/v1/fundraisers/{id} | found | platform_user_name: {} platform_user_fundraiser id: {}", platformUser.getUsername(), platformUser.getFundraiserId());

            return platformUser;
        } else {
            logger.debug("GET | /api/v1/fundraisers/{id} | not found | platform_user_name: {}", username);
            throw new UsernameNotFoundException("Username " + username + " not found");
        }
    }
}