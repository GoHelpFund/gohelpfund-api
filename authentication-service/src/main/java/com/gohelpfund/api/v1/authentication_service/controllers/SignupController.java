package com.gohelpfund.api.v1.authentication_service.controllers;

import com.gohelpfund.api.v1.authentication_service.clients.AuthenticationRestTemplateClient;
import com.gohelpfund.api.v1.authentication_service.model.User;
import com.gohelpfund.api.v1.authentication_service.model.UserSignUp;
import com.gohelpfund.api.v1.authentication_service.security.exceptions.UsernameAlreadyExistsException;
import com.gohelpfund.api.v1.authentication_service.services.UserService;
import com.gohelpfund.api.v1.authentication_service.utils.UserContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/signup")
public class SignupController {
    private static final Logger logger = LoggerFactory.getLogger(SignupController.class);

    @Autowired
    private UserService signupService;

    @Autowired
    AuthenticationRestTemplateClient authClient;

    @PostMapping()
    public ResponseEntity<OAuth2AccessToken> signup(@RequestBody UserSignUp signUp) {
        String username = signUp.getUsername();
        String email = signUp.getEmail();
        String password = signUp.getPassword();
        String scope = signUp.getScope();

        if (signupService.getUser(username) != null) {
            throw new UsernameAlreadyExistsException("Username " + username + " already exists");
        }

        String basicAuthToken = UserContextHolder.getContext().getAuthToken();

        OAuth2AccessToken clientToken = authClient.getToken(getHttpEntity(basicAuthToken, scope));

        User user = signupService.addUser(clientToken.getValue(),
                new User()
                        .withUsername(username)
                        .withEmail(email)
                        .withPassword(password));

        OAuth2AccessToken userToken = authClient.getToken(getHttpEntity(basicAuthToken, username, password, scope));

        return getResponse(userToken);
    }

    private ResponseEntity<OAuth2AccessToken> getResponse(OAuth2AccessToken accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cache-Control", "no-store");
        headers.set("Pragma", "no-cache");
        return new ResponseEntity(accessToken, headers, HttpStatus.CREATED);
    }

    private HttpEntity<Map<String, String>> getHttpEntity(String token, String scope) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        headers.set("Content-Type", "application/json");

        Map<String, String> map = new HashMap<>();
        map.put("grant_type", "client_credentials");
        map.put("scope", scope);

        return new HttpEntity<>(map, headers);
    }

    private HttpEntity<Map<String, String>> getHttpEntity(String token, String username, String password, String scope) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        headers.set("Content-Type", "application/json");

        Map<String, String> map = new HashMap<>();
        map.put("grant_type", "password");
        map.put("scope", scope);
        map.put("username", username);
        map.put("password", password);

        return new HttpEntity<>(map, headers);
    }

}