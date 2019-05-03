package com.gohelpfund.api.v1.donation_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServiceConfig {

    @Value("${signing.key}")
    private String jwtSigningKey;

    @Value("${platform.env}")
    private String platformEnv;


    public String getPlatformEnv() {
        return platformEnv;
    }

    public String getJwtSigningKey() {
        return jwtSigningKey;
    }

}
