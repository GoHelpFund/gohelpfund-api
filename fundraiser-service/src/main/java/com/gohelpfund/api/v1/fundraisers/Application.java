package com.gohelpfund.api.v1.fundraisers;

import com.gohelpfund.api.v1.fundraisers.utils.UserContextFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import javax.servlet.Filter;

@SpringBootApplication
@EnableEurekaClient
@EnableBinding(Source.class)
@EnableResourceServer
//@EnableGlobalMethodSecurity(securedEnabled = true)
public class Application {

    @Bean
    public Sampler defaultSampler() {
        return new AlwaysSampler();
    }

    @Bean
    public Filter userContextFilter() {
        return new UserContextFilter();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
