package com.gohelpfund.api.v1.authentication.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationRestTemplateClient {

    public OAuth2AccessToken getToken(HttpEntity requestEntity){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<OAuth2AccessToken> restExchange =
                restTemplate.exchange(
                        "http://authentication-service:8764/api/v1/auth/oauth/token",
                        HttpMethod.POST,
                        requestEntity,
                        OAuth2AccessToken.class, "");

        return restExchange.getBody();
    }

}
