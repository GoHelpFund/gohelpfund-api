package com.gohelpfund.api.v1.campaign_service.campaigns.clients;

import com.gohelpfund.api.v1.campaign_service.campaigns.model.category.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthRestTemplateClient {
    @Autowired
    RestTemplate restTemplate;

    public Category getUser(String accessToken){
        ResponseEntity<Category> restExchange =
                restTemplate.exchange(
                        "http://authentication-service:8764/api/v1/auth/oauth/check_token?token={accessToken}",
                        HttpMethod.GET,
                        null, Category.class, accessToken);

        return restExchange.getBody();
    }
}
