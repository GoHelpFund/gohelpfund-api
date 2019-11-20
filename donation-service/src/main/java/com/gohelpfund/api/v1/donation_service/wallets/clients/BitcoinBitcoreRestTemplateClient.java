package com.gohelpfund.api.v1.donation_service.wallets.clients;

import com.gohelpfund.api.v1.donation_service.config.ServiceConfig;
import com.gohelpfund.api.v1.donation_service.wallets.models.api.bitcore.BitcoreAddr;
import com.gohelpfund.api.v1.donation_service.wallets.models.api.insight.InsightAddr;
import com.gohelpfund.api.v1.donation_service.wallets.models.api.insight.InsightTx;
import com.gohelpfund.api.v1.donation_service.wallets.models.api.insight.InsightTxResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BitcoinBitcoreRestTemplateClient {
    private static final Logger logger = LoggerFactory.getLogger(BitcoinBitcoreRestTemplateClient.class);

    @Autowired
    ServiceConfig config;

    public BitcoreAddr getBitcoreBitcoinAddrBalance(String id){
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0");

        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<BitcoreAddr> restExchange =
                template.exchange(
                        config.getExplorerBitcoin() + "/address/{id}/balance",
                        HttpMethod.GET,
                        entity, BitcoreAddr.class, id);

        return restExchange.getBody();
    }

    public InsightTxResponse setTransaction(HttpEntity requestEntity) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<InsightTxResponse> restExchange =
                restTemplate.exchange(
                        config.getExplorerBitcoin() + "/tx/send",
                        HttpMethod.POST,
                        requestEntity,
                        InsightTxResponse.class,
                        "");
        return restExchange.getBody();
    }
}
