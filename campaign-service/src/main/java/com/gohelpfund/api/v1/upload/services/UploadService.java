package com.gohelpfund.api.v1.upload.services;

import com.amazonaws.auth.BasicAWSCredentials;
import com.gohelpfund.api.v1.config.ServiceConfig;
import com.gohelpfund.api.v1.upload.model.Upload;
import com.gohelpfund.api.v1.upload.utils.AuthUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UploadService {
    private static final Logger logger = LoggerFactory.getLogger(UploadService.class);

    @Autowired
    private ServiceConfig config;
    private AuthUtil auth;

    public Upload getUploadConfigParameters() {
        auth = new AuthUtil(new BasicAWSCredentials(config.getS3AccessKeyId(), config.getS3SecretAccessKey()), config.getS3BucketRegion(), "s3");

        Upload upload = null;
        String policy;
        String signature;

        try {
            policy = auth.getPolicy(config.getPlatformEnv());
            signature = auth.getSignature(policy, LocalDateTime.now());
            upload = new Upload()
                    .withAccessKeyId(config.getS3AccessKeyId())
                    .withSecretAccessKey(config.getS3SecretAccessKey())
                    .withBucketName(config.getS3BucketName())
                    .withEndpointUrl(config.getS3EndpointUrl())
                    .withBucketRegion(config.getS3BucketRegion())
                    .withPolicy(policy)
                    .withSignature(signature);
            logger.debug("GET | Config Server | found | upload config parameters ");
        } catch (IOException ex) {
            logger.error("GET | local resource | error file read | policy | exception: {}", ex);
        } catch (GeneralSecurityException ex) {
            logger.error("GET | JVM | error HMAC SHA256 | policy_signature | exception: {}", ex);
        }

        return upload;
    }

}
