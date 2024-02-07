package com.shipmonk.testingday.fixer;

import com.shipmonk.testingday.RatesData;
import com.shipmonk.testingday.RatesProvider;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

//this should act as a bridge between app and fixer client (generic)
@Service
public class FixerRatesProvider implements RatesProvider {

    private final FixerClient fixerClient;

    public FixerRatesProvider(FixerClient fixerClient) {
        this.fixerClient = fixerClient;
    }

    @Override
    public RatesData getRates(LocalDate date, String base) throws RatesProviderException {
        return new RatesData(fixerClient.getRates(date, base), date, base);
    }
}
