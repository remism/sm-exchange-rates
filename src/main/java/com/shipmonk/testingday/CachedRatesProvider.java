package com.shipmonk.testingday;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

public class CachedRatesProvider implements RatesProvider {

    private final RatesProvider ratesProvider;
    private final RatesCache ratesCache;

    public CachedRatesProvider(RatesProvider ratesProvider, RatesCache ratesCache) {
        this.ratesProvider = ratesProvider;
        this.ratesCache = ratesCache;
    }

    @Override
    public RatesData getRates(LocalDate date, String base) throws RatesProviderException {
        //simple get or retrieve->store->return
        final Optional<RatesData> cacheRates;
        try {
            cacheRates = ratesCache.getRates(date, base);
        } catch (RatesCache.RatesCacheException e) {
            throw new RatesProviderException();//rethrow the cache exception
        }
        if (cacheRates.isPresent()) {
            return cacheRates.get();
        } else {
            RatesData ratesData = ratesProvider.getRates(date, base);
            try {
                ratesCache.storeRates(ratesData.rates(), ratesData.date(), ratesData.base());
            } catch (RatesCache.RatesCacheException e) {
                // throw? Is log enough if we have values?
            }
            return ratesData;
        }
    }
}
