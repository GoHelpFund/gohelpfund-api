package com.gohelpfund.api.v1.campaign_service.campaigns.clients;

import com.gohelpfund.api.v1.campaign_service.campaigns.model.category.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CategoryRestTemplateClient {
    @Autowired
    RestTemplate restTemplate;

    public Category getCategory(String id){
        ResponseEntity<Category> restExchange =
                restTemplate.exchange(
                        "http://campaign-service:9100/api/v1/categories/{id}",
                        HttpMethod.GET,
                        null, Category.class, id);

        return restExchange.getBody();
    }
}
