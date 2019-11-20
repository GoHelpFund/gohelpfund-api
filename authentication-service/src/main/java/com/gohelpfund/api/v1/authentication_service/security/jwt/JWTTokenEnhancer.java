package com.gohelpfund.api.v1.authentication_service.security.jwt;

import com.gohelpfund.api.v1.authentication_service.utils.UserContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class JWTTokenEnhancer implements TokenEnhancer {
    private static final Logger logger = LoggerFactory.getLogger(JWTTokenEnhancer.class);

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> additionalInfo = new HashMap<>();

        logger.debug("enhance passChanged| {}", UserContextHolder.getContext().isUserPassChanged());

        additionalInfo.put("fundraiser_id", UserContextHolder.getContext().getUserId());
        additionalInfo.put("fundraiser_type", UserContextHolder.getContext().getUserType());

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}
