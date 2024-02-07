package com.shipmonk.testingday;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

/*
Allows us to change implementation of the cache
 */
public interface RatesCache {

    /*
    this should encapsulate internals of cache
     */
    class RatesCacheException extends Exception{}

    /*
    This is similar to Provide, but we want to denote missing item => optional
     */
    Optional<RatesData> getRates(LocalDate date, String base) throws RatesCacheException;

    void storeRates(Map<String, Float> rates, LocalDate date, String base) throws RatesCacheException;
}
