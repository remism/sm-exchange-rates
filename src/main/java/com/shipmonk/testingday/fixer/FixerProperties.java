package com.shipmonk.testingday.fixer;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "fixer")
public record FixerProperties(String baseUrl, String apiKey) {

}
