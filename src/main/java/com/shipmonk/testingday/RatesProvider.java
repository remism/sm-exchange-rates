package com.shipmonk.testingday;

import java.time.LocalDate;
import java.util.Map;

public interface RatesProvider {

    /*
    This should encapsulate the (REST) API exceptions

     */
    class RatesProviderException extends Exception{}



    /*
    Assume that array is empty or exception for edge cases
     */
    RatesData getRates(LocalDate date, String base) throws RatesProviderException;
}
