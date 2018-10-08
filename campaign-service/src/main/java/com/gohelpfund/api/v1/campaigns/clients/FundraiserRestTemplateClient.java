package com.gohelpfund.api.v1.campaigns.clients;

import com.gohelpfund.api.v1.campaigns.model.fundraiser.Fundraiser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FundraiserRestTemplateClient {
    @Autowired
    RestTemplate restTemplate;

    public Fundraiser getFundraiser(String id){
        ResponseEntity<Fundraiser> restExchange =
                restTemplate.exchange(
                        "http://fundraiser-service:9200/api/v1/fundraisers/{id}",
                        HttpMethod.GET,
                        null, Fundraiser.class, id);

        return restExchange.getBody();
    }

    public Fundraiser generateFundraiser(){
        HttpEntity<Fundraiser> request = new HttpEntity<>(new Fundraiser());
        ResponseEntity<Fundraiser> restExchange =
                restTemplate.exchange(
                        "http://fundraiser-service:9200/api/v1/fundraisers/",
                        HttpMethod.POST,
                        request, Fundraiser.class);

        return restExchange.getBody();
    }
}
