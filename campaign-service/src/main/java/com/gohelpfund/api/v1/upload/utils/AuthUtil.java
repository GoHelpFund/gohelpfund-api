package com.gohelpfund.api.v1.upload.utils;

import com.amazonaws.auth.AWS4Signer;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.SigningAlgorithm;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.time.format.DateTimeFormatter.ofPattern;

public class AuthUtil extends AWS4Signer {
    private static final Logger logger = LoggerFactory.getLogger(AuthUtil.class);

    private String serviceName;
    private AWSCredentials credentials;
    private String region;

    public AuthUtil(AWSCredentials credentials, String region, String serviceName) {
        this.credentials = credentials;
        this.region = region;
        this.serviceName = serviceName;
    }

    public String getSignature(String policy, LocalDateTime dateTime) throws GeneralSecurityException {
        try {
            String dateStamp = dateTime.format(ofPattern("yyyyMMdd"));
            return Hex.encodeHexString(hmacSha256(newSigningKey(credentials, dateStamp, region, serviceName), policy));
        } catch (NoSuchAlgorithmException | InvalidKeyException ex) {
            throw new GeneralSecurityException("Error", ex);
        }
    }

    public String getPolicy(String env) throws IOException {
        String fileName;
        switch (env) {
            case "local":
                fileName = "local-policy.txt";
                break;
            case "dev":
                fileName = "dev-policy.txt";
                break;
            case "prod":
                fileName = "prod-policy.txt";
                break;
            default:
                fileName = "local-policy.txt";
        }
        String policy = StreamUtils.copyToString((new ClassPathResource(fileName)).getInputStream(), UTF_8);
        return Base64.getEncoder().encodeToString(policy.getBytes(UTF_8));
    }

    private byte[] hmacSha256(byte[] key, String data) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance(SigningAlgorithm.HmacSHA256.name());
        mac.init(new SecretKeySpec(key, SigningAlgorithm.HmacSHA256.name()));
        return mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
    }

}
