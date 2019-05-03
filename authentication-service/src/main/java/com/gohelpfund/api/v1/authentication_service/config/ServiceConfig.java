package com.gohelpfund.api.v1.authentication_service.config;

import com.gohelpfund.api.v1.authentication_service.config.properties.OA2Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceConfig {

  @Autowired
  private OA2Config oA2Config;

  @Value("${signing.key}")
  private String jwtSigningKey;

  @Value("${oauth2.secret}")
  private String oAuth2Secret;

  @Value("${oauth2.client}")
  private String oAuth2Client;

  public List<String> getOAuth2Scopes() {
    return oA2Config.getScopes();
  }

  public List<String> getOAuth2GrantTypes() {
    return oA2Config.getGrantTypes();
  }

  public String getOAuth2Client() {
    return oAuth2Client;
  }

  public String getOAuth2Secret() {
    return oAuth2Secret;
  }

  public String getJwtSigningKey() {
    return jwtSigningKey;
  }

}
