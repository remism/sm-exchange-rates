package com.shipmonk.testingday;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "exchange")
public record ExchangeProperties(String baseCurrency) {

}
