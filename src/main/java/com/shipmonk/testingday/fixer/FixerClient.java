package com.shipmonk.testingday.fixer;

import com.shipmonk.testingday.RatesProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Service
public class FixerClient {
    private final RestClient restClient;


    public FixerClient(@Qualifier("fixer") RestClient restClient) {
        this.restClient = restClient;
    }

    public Map<String, Float> getRates(LocalDate day, String base) throws RatesProvider.RatesProviderException {
        //Properly use new RestClient API
        RestClient.ResponseSpec response = restClient.get().uri(
            uriBuilder -> uriBuilder
                .path("/{day}")
                .queryParam("access_key", "{access_key}")
                .queryParam("base", base)
                .build(Map.of("day", day.format(DateTimeFormatter.ISO_LOCAL_DATE)))).retrieve();
        ResponseEntity<RatesResponse> entity = response.toEntity(RatesResponse.class);
        if (entity.getStatusCode().isError()) {
            throw new RatesProvider.RatesProviderException();// handle exception
        }

        RatesResponse body = entity.getBody();

        if (body == null) {
            //??? This is unexpected exception, maybe special exception for this
            throw new RatesProvider.RatesProviderException();
        } else {
            if (body.success()) {
                return Optional.of(body.rates()).orElseGet(Collections::emptyMap);
            } else {
                // Handle exceptions
                throw new RatesProvider.RatesProviderException();
            }
        }
    }
}
