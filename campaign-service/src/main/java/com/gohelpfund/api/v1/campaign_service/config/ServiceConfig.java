package com.gohelpfund.api.v1.campaign_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServiceConfig {

  @Value("${signing.key}")
  private String jwtSigningKey;

  @Value("${aws.s3.client.id}")
  private String s3AccessKeyId;

  @Value("${aws.s3.client.key}")
  private String s3SecretAccessKey;

  @Value("${aws.s3.client.bucketName}")
  private String s3BucketName;

  @Value("${aws.s3.client.endpointUrl}")
  private String s3EndpointUrl;

  @Value("${aws.s3.client.region}")
  private String s3BucketRegion;

  @Value("${redis.server}")
  private String redisServer;

  @Value("${redis.port}")
  private String redisPort;

  @Value("${redis.password}")
  private String redisPassword;

  @Value("${redis.secure}")
  private String redisSecure;

  @Value("${platform.env}")
  private String platformEnv;

  public String getS3AccessKeyId() {
    return s3AccessKeyId;
  }

  public String getS3SecretAccessKey() {
    return s3SecretAccessKey;
  }

  public String getS3BucketName() {
    return s3BucketName;
  }

  public String getS3BucketRegion() {
    return s3BucketRegion;
  }

  public String getS3EndpointUrl() {
    return s3EndpointUrl;
  }

  public String getPlatformEnv() {
    return platformEnv;
  }

  public String getJwtSigningKey() {
    return jwtSigningKey;
  }

  public String getRedisServer() {
    return redisServer;
  }

  public String getRedisPort() {
    return redisPort;
  }

  public String getRedisPassword() {
    return redisPassword;
  }

  public String getRedisSecure() {
    return redisSecure;
  }
}
