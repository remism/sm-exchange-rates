package com.shipmonk.testingday.pgcache;

import com.shipmonk.testingday.RatesCache;
import com.shipmonk.testingday.RatesData;
import jakarta.persistence.Tuple;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostgresRatesCache implements RatesCache {

    private final ExchangeRateRepository rateRepository;

    public PostgresRatesCache(ExchangeRateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    //handle cache cleanup - but those are historical, they are "valid forever"
    //instead consider some max size

    //Also consider some in memory cache
    @Override
    public Optional<RatesData> getRates(LocalDate date, String base) throws RatesCacheException {
        //handle exception
        //handle conversion


        List<Tuple> rates = rateRepository.findRatesByBaseCurrencyAndDate(date, base);
        if (rates.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(new RatesData(rates.stream()
                .collect(Collectors.toMap(it -> it.get("target", String.class), it -> it.get("rate", Float.class))), date, base));
    }

    @Override
    public void storeRates(Map<String, Float> rates, LocalDate date, String base) throws RatesCacheException {
        //we should handle the concurrency here
        //but the worst case is that it will be written twice and write should be idempotent => not important now
        //for now it might duplicate the data
        //finetune the insert
        rateRepository.saveAll(rates.entrySet().stream().map(entry -> new ExchangeRate(date, base, entry.getKey(), entry.getValue())).collect(Collectors.toSet()));
    }
}
