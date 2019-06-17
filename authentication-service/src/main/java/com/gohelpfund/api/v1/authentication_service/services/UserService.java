package com.gohelpfund.api.v1.authentication_service.services;

import com.gohelpfund.api.v1.authentication_service.clients.EventRestTemplateClient;
import com.gohelpfund.api.v1.authentication_service.clients.FundraiserRestTemplateClient;
import com.gohelpfund.api.v1.authentication_service.model.EventAttendance;
import com.gohelpfund.api.v1.authentication_service.model.User;
import com.gohelpfund.api.v1.authentication_service.model.UserRole;
import com.gohelpfund.api.v1.authentication_service.model.fundraiser.Fundraiser;
import com.gohelpfund.api.v1.authentication_service.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.gohelpfund.api.v1.authentication_service.security.constants.AuthoritiesConstants.BACKER;
import static com.gohelpfund.api.v1.authentication_service.security.constants.AuthoritiesConstants.FUNDRAISER;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    FundraiserRestTemplateClient fundraiserClient;

    @Autowired
    EventRestTemplateClient eventClient;


    @Autowired
    private UserRoleService userRoleService;

    public User getUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            logger.debug("GET | PostgreSQL | found | user_name: {} fundraiser id: {}", user.getUsername(), user.getFundraiserId());
            List<UserRole> roles = userRoleService.getAll(username);
            user.setRoles(roles);
        } else {
            logger.debug("GET | PostgreSQL | not found | user_name: {}", username);
        }
        return user;
    }

    public User addUser(String clientToken,
                        String name,
                        String eventId,
                        String table,
                        String type,
                        User user) {
        String id = UUID.randomUUID().toString();
        String username = user.getUsername();
        String source = eventId != null ? "event" : "default";
        Fundraiser fundraiser = createFundraiser(username, source, getHttpEntity(name, clientToken));

        if(eventId != null && table!= null && type!= null){
            createAttendance(username, eventId, getHttpEntity(fundraiser.getId(), name, table, type, clientToken));
        }

        user.withId(id)
                .withPassword(passwordEncoder.encode(user.getPassword()))
                .withFundraiserId(fundraiser.getId())
                .withRoles(createRoles(username))
                .withEnabled(true);

        User newUser = userRepository.save(user);

        logger.debug("POST | PostgreSQL | created | user id: {} ", newUser.getId());

        return newUser;
    }

    private Fundraiser createFundraiser(String username, String source, HttpEntity httpEntity) {
        Fundraiser newFundraiser = fundraiserClient.createFundraiser(source, httpEntity);

        if (newFundraiser != null) {
            logger.debug("POST | /api/v1/fundraisers | created | user_name: {} fundraiser id: {}", username, newFundraiser.getId());
        } else {
            logger.debug("POST | /api/v1/fundraisers | creation failed | user_name: {}", username);
        }

        return newFundraiser;
    }

    private EventAttendance createAttendance(String username, String eventId, HttpEntity httpEntity) {
        EventAttendance newAttendance = eventClient.createAttendance(eventId, httpEntity);

        if (newAttendance != null) {
            logger.debug("POST | /api/v1/events/{}/attendance | created | user_name: {}", eventId, username);
        } else {
            logger.debug("POST | /api/v1/events/{}/attendance | creation failed | user_name: {}", eventId, username);
        }

        return newAttendance;
    }

/*    private Fundraiser updateFundraiser(String fundraiserId, HttpEntity httpEntity) {
        Fundraiser newFundraiser = fundraiserClient.updateFundraiser(fundraiserId, httpEntity);

        if (newFundraiser != null) {
            logger.debug("POST | /api/v1/fundraisers | updated | user_name: {} fundraiser id: {}", fundraiserId, newFundraiser.getId());
        } else {
            logger.debug("POST | /api/v1/fundraisers | creation failed | user_name: {}", fundraiserId);
        }

        return newFundraiser;
    }*/

    private List<UserRole> createRoles(String username) {
        List<UserRole> roles = Arrays.asList(
                new UserRole(FUNDRAISER),
                new UserRole(BACKER));

        return userRoleService.saveAll(username, roles);
    }

    private HttpEntity<Map<String, String>> getHttpEntity(String clientToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + clientToken);

        return new HttpEntity<>(null, headers);
    }

    private HttpEntity<Map<String, String>> getHttpEntity(String name,String clientToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + clientToken);
        headers.set("Content-Type", "application/json");

        Map<String, String> map = new HashMap<>();
        map.put("name", name);

        return new HttpEntity<>(map, headers);
    }

    private HttpEntity<Map<String, String>> getHttpEntity(String fundraiserId, String fundraiserName, String tableId, String type, String clientToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + clientToken);
        headers.set("Content-Type", "application/json");

        Map<String, String> map = new HashMap<>();
        map.put("fundraiser_id", fundraiserId);
        map.put("fundraiser_name", fundraiserName);
        map.put("fundraiser_type", type);
        map.put("table_id", tableId);

        return new HttpEntity<>(map, headers);
    }

}
