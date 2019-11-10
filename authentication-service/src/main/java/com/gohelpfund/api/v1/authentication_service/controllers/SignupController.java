package com.gohelpfund.api.v1.authentication_service.controllers;

import com.gohelpfund.api.v1.authentication_service.clients.AuthenticationRestTemplateClient;
import com.gohelpfund.api.v1.authentication_service.config.ServiceConfig;
import com.gohelpfund.api.v1.authentication_service.model.User;
import com.gohelpfund.api.v1.authentication_service.model.UserChangePassword;
import com.gohelpfund.api.v1.authentication_service.model.UserSignUp;
import com.gohelpfund.api.v1.authentication_service.security.exceptions.UsernameAlreadyExistsException;
import com.gohelpfund.api.v1.authentication_service.services.UserService;
import com.gohelpfund.api.v1.authentication_service.utils.UserContextHolder;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "")
public class SignupController {
    private static final Logger logger = LoggerFactory.getLogger(SignupController.class);

    @Autowired
    private UserService signupService;

    @Autowired
    private ServiceConfig config;

    @Autowired
    AuthenticationRestTemplateClient authClient;

    @PostMapping(value = "/signup")
    public ResponseEntity<OAuth2AccessToken> signup(@RequestBody UserSignUp signUp,
                                                    @RequestParam(required = false) String event,
                                                    @RequestParam(required = false) String table,
                                                    @RequestParam(required = false) String type) {
        String username = signUp.getUsername();
        String email = signUp.getEmail();
        String name = signUp.getName();
        String password = signUp.getPassword();
        String scope = signUp.getScope();

        if (signupService.getUser(username) != null) {
            throw new UsernameAlreadyExistsException("Username " + username + " already exists");
        }

        String basicAuthToken = UserContextHolder.getContext().getAuthToken();

        OAuth2AccessToken clientToken = authClient.getToken(getHttpEntity(basicAuthToken, scope));

        signupService.addUser(clientToken.getValue(),
                name,
                event,
                table,
                type,
                new User()
                        .withUsername(username)
                        .withEmail(email)
                        .withPassword(password));

        OAuth2AccessToken userToken = authClient.getToken(getHttpEntity(basicAuthToken, username, password, scope));

        return getResponse(userToken);
    }

    @PostMapping(value="/changePassword")
    public void changePassword(@RequestBody UserChangePassword userChangePassword){

        String username = getValueFromJWTByKey("user_name");
        signupService.changeUserPassword(username, userChangePassword.getNewPassword());

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

    private String getValueFromJWTByKey(String key) {
        String value;
        String authToken = UserContextHolder.getContext().getAuthToken().replace("Bearer ", "");

        try {
            Claims claims =
                    Jwts.parser()
                            .setSigningKey(config.getJwtSigningKey().getBytes("UTF-8"))
                            .parseClaimsJws(authToken)
                            .getBody();
            value = claims.get(key).toString();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException("Unable to parse JWT");
        }
        return value;
    }
}