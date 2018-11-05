package com.gohelpfund.api.v1.categories.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServiceConfig {

  @Value("${signing.key}")
  private String jwtSigningKey;

  public String getJwtSigningKey() {
    return jwtSigningKey;
  }
}
