package com.gohelpfund.api.v1.authentication_service.clients;

import com.gohelpfund.api.v1.authentication_service.model.EventAttendance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class EventRestTemplateClient {
    private static final Logger logger = LoggerFactory.getLogger(EventRestTemplateClient.class);

    @Autowired
    RestTemplate restTemplate;

    public EventAttendance createAttendance(String id, HttpEntity httpEntity) {
        //RestTemplate template = new RestTemplate();

        logger.info("id: {}", id);
        ResponseEntity<EventAttendance> restExchange =
                restTemplate.exchange(
                        "http://campaign-service:9100/api/v1/events/{id}/attendance",
                        HttpMethod.POST,
                        httpEntity,
                        EventAttendance.class, id);
        return restExchange.getBody();
    }

}
