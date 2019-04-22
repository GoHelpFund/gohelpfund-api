package com.gohelpfund.api.v1.authentication.clients;

import com.gohelpfund.api.v1.authentication.model.fundraiser.Fundraiser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FundraiserRestTemplateClient {
    private static final Logger logger = LoggerFactory.getLogger(FundraiserRestTemplateClient.class);
    @Autowired
    RestTemplate restTemplate;

    public Fundraiser createFundraiser(HttpEntity httpEntity){
        ResponseEntity<Fundraiser> restExchange =
                restTemplate.exchange(
                        "http://fundraiser-service:9200/api/v1/fundraisers/",
                        HttpMethod.POST,
                        httpEntity,
                        Fundraiser.class, "");

        Fundraiser fundraiser = restExchange.getBody();

        return fundraiser;
    }


}
