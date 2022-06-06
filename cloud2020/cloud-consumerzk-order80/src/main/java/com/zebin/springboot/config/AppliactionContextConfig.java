package com.zebin.springboot.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppliactionContextConfig {
    @Bean
    @LoadBalanced   //使得拥有负载均衡的能力
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
