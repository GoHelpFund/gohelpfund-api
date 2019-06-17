package com.gohelpfund.api.v1.fundraiser_service;

import com.gohelpfund.api.v1.fundraiser_service.utils.UserContextFilter;
import com.gohelpfund.api.v1.fundraiser_service.utils.UserContextInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.client.RestTemplate;

import javax.servlet.Filter;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
@EnableBinding(Source.class)
@EnableResourceServer
//@EnableGlobalMethodSecurity(securedEnabled = true)
public class Application {

/*    @Bean
    public Sampler defaultSampler() {
        return new AlwaysSampler();
    }*/

    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate() {
        RestTemplate template = new RestTemplate();
        List interceptors = template.getInterceptors();
        if (interceptors == null) {
            template.setInterceptors(Collections.singletonList(new UserContextInterceptor()));
        } else {
            interceptors.add(new UserContextInterceptor());
            template.setInterceptors(interceptors);
        }

        return template;
    }

    @Bean
    public Filter userContextFilter() {
        return new UserContextFilter();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
