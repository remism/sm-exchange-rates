package com.shipmonk.testingday;

import com.shipmonk.testingday.fixer.FixerRatesProvider;
import com.shipmonk.testingday.pgcache.PostgresRatesCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@SpringBootApplication
@EnableConfigurationProperties(ExchangeProperties.class)
public class TestingdayExchangeRatesApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(TestingdayExchangeRatesApplication.class, args);
    }


    //conditional loading if necessary?

    @Bean
    @Primary
    public RatesProvider transferService(FixerRatesProvider provider, PostgresRatesCache cache) {
        return new CachedRatesProvider(provider, cache);
    }
}
