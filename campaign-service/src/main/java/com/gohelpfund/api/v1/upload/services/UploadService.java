package com.gohelpfund.api.v1.upload.services;

import com.amazonaws.auth.BasicAWSCredentials;
import com.gohelpfund.api.v1.upload.config.ServiceConfig;
import com.gohelpfund.api.v1.upload.model.Upload;
import com.gohelpfund.api.v1.upload.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UploadService {

    @Autowired
    private ServiceConfig config;

    private AuthUtil auth;


    public Optional<Upload> getConfig(){

        auth = new AuthUtil(new BasicAWSCredentials(config.getS3AccessKeyId(), config.getS3SecretAccessKey()), config.getS3BucketRegion(), "s3");

        String policy = null;

        try {
            policy = auth.getPolicy();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String signature = auth.getSignature(policy, LocalDateTime.now());

        Upload upload = new Upload().withAccessKeyId(config.getS3AccessKeyId())
                .withSecretAccessKey(config.getS3SecretAccessKey())
                .withBucketName(config.getS3BucketName())
                .withEndpointUrl(config.getS3EndpointUrl())
                .withBucketRegion(config.getS3BucketRegion())
                .withPolicy(policy)
                .withSignature(signature);

        return Optional.ofNullable(upload);
    }

}
