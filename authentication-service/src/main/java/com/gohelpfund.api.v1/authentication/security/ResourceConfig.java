package com.gohelpfund.api.v1.authentication.security;

import com.gohelpfund.api.v1.authentication.security.filters.JsonToUrlEncodedAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class ResourceConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private JsonToUrlEncodedAuthenticationFilter jsonFilter;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .addFilterAfter(jsonFilter, BasicAuthenticationFilter.class)
                .csrf().disable().exceptionHandling().and()
                .headers().frameOptions().disable().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // restricting access to authenticated users
                .requestMatchers().and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/signup").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/oauth/token").authenticated()
                .antMatchers(HttpMethod.GET, "/auth/user").authenticated()
                .anyRequest().authenticated();
    }

}