package com.gohelpfund.api.v1.donation_service.wallets.clients;

import com.gohelpfund.api.v1.donation_service.wallets.models.api.insight.InsightHelpAddr;
import com.gohelpfund.api.v1.donation_service.wallets.models.api.insight.InsightHelpTx;
import com.gohelpfund.api.v1.donation_service.wallets.models.api.insight.InsightHelpTxResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HelpInsightRestTemplateClient {
    private static final Logger logger = LoggerFactory.getLogger(HelpInsightRestTemplateClient.class);

    public InsightHelpAddr getInsightHelpAddr(String id){
        RestTemplate template = new RestTemplate();
        ResponseEntity<InsightHelpAddr> restExchange =
                template.exchange(
                        "http://insight.gohelpfund.com/insight-api/addr/{id}",
                        HttpMethod.GET,
                        null, InsightHelpAddr.class, id);

        return restExchange.getBody();
    }

    public InsightHelpTx getInsightHelpTx(String id){
        RestTemplate template = new RestTemplate();
        ResponseEntity<InsightHelpTx> restExchange =
                template.exchange(
                        "http://insight.gohelpfund.com/insight-api/tx/{id}",
                        HttpMethod.GET,
                        null, InsightHelpTx.class, id);

        return restExchange.getBody();
    }

    public InsightHelpTxResponse setTransaction(HttpEntity requestEntity) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<InsightHelpTxResponse> restExchange =
                restTemplate.exchange(
                        "http://insight.gohelpfund.com/insight-api/tx/send",
                        HttpMethod.POST,
                        requestEntity,
                        InsightHelpTxResponse.class,
                        "");
        return restExchange.getBody();
    }
}
