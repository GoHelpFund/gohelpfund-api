package com.gohelpfund.api.v1.authentication.security;

import com.gohelpfund.api.v1.authentication.services.PlatformUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    private final DataSource dataSource;
    private final AuthenticationManager authenticationManager;
    private final PlatformUserDetailsService platformUserDetailsService;
    private final TokenStore tokenStore;
    private final JwtAccessTokenConverter jwtAccessTokenConverter;
    private final TokenEnhancer jwtTokenEnhancer;


    @Autowired
    DefaultTokenServices tokenServices;

    @Autowired
    public AuthorizationConfig(@Qualifier("dataSource") DataSource dataSource, AuthenticationManager authenticationManager, PlatformUserDetailsService platformUserDetailsService, JwtAccessTokenConverter jwtAccessTokenConverter, TokenEnhancer jwtTokenEnhancer, TokenStore tokenStore) {
        this.dataSource = dataSource;
        this.authenticationManager = authenticationManager;
        this.platformUserDetailsService = platformUserDetailsService;
        this.jwtAccessTokenConverter = jwtAccessTokenConverter;
        this.jwtTokenEnhancer = jwtTokenEnhancer;
        this.tokenStore = tokenStore;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(jwtTokenEnhancer, jwtAccessTokenConverter));

        endpoints
                .tokenStore(tokenStore)
                .accessTokenConverter(jwtAccessTokenConverter)
                .tokenEnhancer(tokenEnhancerChain)
                .authenticationManager(authenticationManager)
                .userDetailsService(platformUserDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer.allowFormAuthenticationForClients()
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);
    }

}
