package com.shipmonk.testingday.fixer;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Configuration
@EnableConfigurationProperties(FixerProperties.class)
public class FixerConfiguration {

    @Bean("fixer")
//    configuration on property
    public RestClient restClient(FixerProperties properties) {
        return RestClient.builder()
            //.requestFactory(...) consider underlying http client implementation
            .baseUrl(properties.baseUrl())
            .defaultUriVariables(Map.of("access_key", properties.apiKey()))
            .build();
    }
}
