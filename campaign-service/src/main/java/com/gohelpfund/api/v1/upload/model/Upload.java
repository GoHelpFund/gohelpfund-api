package com.gohelpfund.api.v1.upload.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"accessKeyId", "secretAccessKey", "endpointUrl", "bucketName", "bucketRegion", "signature", "policy"})
public class Upload {
    @JsonProperty("access_key_id")
    private String accessKeyId;

    @JsonProperty("access_key_secret")
    private String secretAccessKey;

    @JsonProperty("endpoint_url")
    private String endpointUrl;

    @JsonProperty("bucket_name")
    private String bucketName;

    @JsonProperty("bucket_region")
    private String bucketRegion;

    @JsonProperty("policy")
    private String policy;

    @JsonProperty("signature")
    private String signature;

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getSecretAccessKey() {
        return secretAccessKey;
    }

    public void setSecretAccessKey(String secretAccessKey) {
        this.secretAccessKey = secretAccessKey;
    }

    public String getEndpointUrl() {
        return endpointUrl;
    }

    public void setEndpointUrl(String endpointUrl) {
        this.endpointUrl = endpointUrl;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketRegion() {
        return bucketRegion;
    }

    public void setBucketRegion(String bucketRegion) {
        this.bucketRegion = bucketRegion;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Upload withAccessKeyId(String accessKeyId){
        this.setAccessKeyId(accessKeyId);
        return this;
    }

    public Upload withSecretAccessKey(String secretAccessKey){
        this.setSecretAccessKey(secretAccessKey);
        return this;
    }

    public Upload withEndpointUrl(String endpointUrl){
        this.setEndpointUrl(endpointUrl);
        return this;
    }


    public Upload withBucketName(String bucketName){
        this.setBucketName(bucketName);
        return this;
    }

    public Upload withBucketRegion(String bucketRegion){
        this.setBucketRegion(bucketRegion);
        return this;
    }

    public Upload withPolicy(String policy){
        this.setPolicy(policy);
        return this;
    }

    public Upload withSignature(String signature){
        this.setSignature(signature);
        return this;
    }
}
