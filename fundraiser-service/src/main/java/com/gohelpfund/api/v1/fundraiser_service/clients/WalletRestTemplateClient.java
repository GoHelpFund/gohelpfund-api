package com.gohelpfund.api.v1.fundraiser_service.clients;

import com.gohelpfund.api.v1.fundraiser_service.model.wallet.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WalletRestTemplateClient {
    @Autowired
    RestTemplate restTemplate;

    public Wallet getWallet(String id){
        ResponseEntity<Wallet> restExchange =
                restTemplate.exchange(
                        "http://donation-service:9000/api/v1/wallets/{id}",
                        HttpMethod.GET,
                        null, Wallet.class, id);

        return restExchange.getBody();
    }

    public Wallet createWallet(HttpEntity httpEntity){
        RestTemplate template = new RestTemplate();
        ResponseEntity<Wallet> restExchange =
                template.exchange(
                        "http://donation-service:9000/api/v1/wallets",
                        HttpMethod.POST,
                        httpEntity, Wallet.class, "");

        return restExchange.getBody();
    }
}
