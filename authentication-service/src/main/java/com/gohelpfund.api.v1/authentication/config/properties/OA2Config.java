package com.gohelpfund.api.v1.authentication.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "oauth2")
public class OA2Config {

    private List<String> grantTypes;
    private List<String> scopes;

    public OA2Config() {
        grantTypes = new ArrayList<>();
        scopes = new ArrayList<>();
    }

    public List<String> getGrantTypes() {
        return grantTypes;
    }

    public List<String> getScopes() {
        return scopes;
    }
}
