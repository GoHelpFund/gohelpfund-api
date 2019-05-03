package com.gohelpfund.api.v1.campaign_service.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
                .antMatchers("/api/v1/campaigns/**", "/api/v1/categories/**", "/api/v1/upload")
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.DELETE, "/api/v1/campaigns/**").hasRole("FUNDRAISER")
                .antMatchers(HttpMethod.POST, "/api/v1/campaigns/**").hasRole("FUNDRAISER")
                .antMatchers(HttpMethod.PUT, "/api/v1/campaigns/**").hasRole("FUNDRAISER")
                .antMatchers(HttpMethod.DELETE, "/api/v1/categories/**").denyAll()
                .antMatchers(HttpMethod.POST, "/api/v1/categories/**").denyAll()
                .antMatchers(HttpMethod.PUT, "/api/v1/categories/**").denyAll()
                .anyRequest().authenticated();
    }
}
