package com.shipmonk.testingday;

import java.time.LocalDate;
import java.util.Map;


//this should be internal representation of rates (api, cache agnostic)
public record RatesData(Map<String, Float> rates, LocalDate date, String base) {
}
