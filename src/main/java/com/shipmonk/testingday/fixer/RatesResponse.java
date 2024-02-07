package com.shipmonk.testingday.fixer;

import java.util.Map;

//representation of fixer response

public record RatesResponse(
    Boolean success, Error error, Boolean historical, String date, String timestamp, String base, Map<String, Float> rates
) {
    public record Error (Integer code, String info, String type) {}
}
