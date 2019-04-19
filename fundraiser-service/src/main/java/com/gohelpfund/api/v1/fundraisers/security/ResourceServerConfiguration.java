package com.gohelpfund.api.v1.fundraisers.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/api/v1/fundraisers/**")
                .authorizeRequests()
                .antMatchers(HttpMethod.DELETE, "/api/v1/fundraisers/**").hasAnyRole("FUNDRAISER", "BACKER")
                //.antMatchers(HttpMethod.POST, "/api/v1/fundraisers/**").hasAnyRole("FUNDRAISER", "BACKER")
                .antMatchers(HttpMethod.PUT, "/api/v1/fundraisers/**").hasAnyRole("FUNDRAISER", "BACKER")
                .anyRequest().authenticated();
    }
}
