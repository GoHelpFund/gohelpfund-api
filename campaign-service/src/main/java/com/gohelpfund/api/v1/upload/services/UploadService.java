package com.gohelpfund.api.v1.upload.services;

import com.gohelpfund.api.v1.upload.config.ServiceConfig;
import com.gohelpfund.api.v1.upload.model.Upload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UploadService {

    @Autowired
    ServiceConfig config;

    public Optional<Upload> getConfig() {
        return Optional.ofNullable(new Upload().withAccessKeyId(config.getS3AccessKeyId())
                .withSecretAccessKey(config.getS3SecretAccessKey())
                .withBucketName(config.getS3BucketName())
                .withEndpointUrl(config.getS3EndpointUrl())
                .withBucketRegion(config.getS3BucketRegion()));
    }

}
