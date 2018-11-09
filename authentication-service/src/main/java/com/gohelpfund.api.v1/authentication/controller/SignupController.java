package com.gohelpfund.api.v1.authentication.controller;

import com.gohelpfund.api.v1.authentication.clients.AuthenticationRestTemplateClient;
import com.gohelpfund.api.v1.authentication.model.User;
import com.gohelpfund.api.v1.authentication.services.UserService;
import com.gohelpfund.api.v1.authentication.utils.UserContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
    public ResponseEntity<OAuth2AccessToken> signup(@RequestBody Map<String, String> parameters) {
        String username = parameters.get("username");
        String password = parameters.get("password");
        String scope = parameters.get("scope");

        signupService.addUser(new User().withUsername(username).withPassword(password));

        OAuth2AccessToken token = authClient.getToken(getHttpEntity(username, password, scope));

        return getResponse(token);
    }

    private ResponseEntity<OAuth2AccessToken> getResponse(OAuth2AccessToken accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cache-Control", "no-store");
        headers.set("Pragma", "no-cache");
        return new ResponseEntity(accessToken, headers, HttpStatus.CREATED);
    }

    private HttpEntity<Map<String, String>> getHttpEntity(String username, String password, String scope) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", UserContextHolder.getContext().getAuthToken());

        Map<String, String> map = new HashMap<>();
        map.put("grant_type", "password");
        map.put("scope", scope);
        map.put("username", username);
        map.put("password", password);

        return new HttpEntity<>(map, headers);
    }

}