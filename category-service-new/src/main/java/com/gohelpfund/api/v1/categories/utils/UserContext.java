package com.gohelpfund.api.v1.categories.utils;

import org.springframework.stereotype.Component;

@Component
public class UserContext {
    public static final String CORRELATION_ID = "ghf-correlation-id";
    public static final String AB_TESTING = "ghf-ab-testing";

    private String correlationId = "";
    private String abTesting = "";

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getAbTesting() {
        return abTesting;
    }

    public void setAbTesting(String abTesting) {
        this.abTesting = abTesting;
    }
}