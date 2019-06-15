package com.orange.minip.Config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootConfiguration
public class RestTemplateConfig{

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
