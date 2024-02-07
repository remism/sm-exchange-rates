package com.shipmonk.testingday;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
@RequestMapping(
    path = "/api/v1/rates"
)
public class ExchangeRatesController {
    private final RatesProvider ratesProvider;

    private final ExchangeProperties exchangeProperties;
    public ExchangeRatesController(RatesProvider ratesProvider, ExchangeProperties exchangeProperties) {
        this.ratesProvider = ratesProvider;
        this.exchangeProperties = exchangeProperties;
    }


    @RequestMapping(method = RequestMethod.GET, path = "/{day}")
    public ResponseEntity<Object> getRates(@PathVariable("day") String day) throws RatesProvider.RatesProviderException {
        //validate the input

        final LocalDate localDate = LocalDate.parse(day, DateTimeFormatter.ISO_LOCAL_DATE);

        ratesProvider.getRates(localDate, exchangeProperties.baseCurrency());
        //handle the response

        return new ResponseEntity<>(
            Map.of(),
            HttpStatus.OK
        );
    }
    @ExceptionHandler({ RatesProvider.RatesProviderException.class})
    public void handleException() {
        //
    }
}
