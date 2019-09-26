package com.gohelpfund.api.v1.donation_service.wallets.clients;

import com.gohelpfund.api.v1.donation_service.config.ServiceConfig;
import com.gohelpfund.api.v1.donation_service.wallets.models.api.insight.InsightAddr;
import com.gohelpfund.api.v1.donation_service.wallets.models.api.insight.InsightTx;
import com.gohelpfund.api.v1.donation_service.wallets.models.api.insight.InsightTxResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HelpInsightRestTemplateClient {
    private static final Logger logger = LoggerFactory.getLogger(HelpInsightRestTemplateClient.class);

    @Autowired
    ServiceConfig config;

    public InsightAddr getInsightHelpAddr(String id){
        RestTemplate template = new RestTemplate();
        ResponseEntity<InsightAddr> restExchange =
                template.exchange(
                        config.getExplorerHelp() + "/addr/{id}",
                        HttpMethod.GET,
                        null, InsightAddr.class, id);

        return restExchange.getBody();
    }

    public InsightTx getInsightHelpTx(String id){
        RestTemplate template = new RestTemplate();
        ResponseEntity<InsightTx> restExchange =
                template.exchange(
                        config.getExplorerHelp() + "/tx/{id}",
                        HttpMethod.GET,
                        null, InsightTx.class, id);

        return restExchange.getBody();
    }

    public InsightTxResponse setTransaction(HttpEntity requestEntity) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<InsightTxResponse> restExchange =
                restTemplate.exchange(
                        config.getExplorerHelp() + "/tx/send",
                        HttpMethod.POST,
                        requestEntity,
                        InsightTxResponse.class,
                        "");
        return restExchange.getBody();
    }
}
