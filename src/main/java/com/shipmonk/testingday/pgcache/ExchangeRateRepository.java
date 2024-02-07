package com.shipmonk.testingday.pgcache;

import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {


    //Can we fetch directly into map?
    @Query("SELECT e.target as target, e.rate as rate FROM ExchangeRate e WHERE e.base = :baseCurrency AND e.day = :date")
    List<Tuple> findRatesByBaseCurrencyAndDate(LocalDate date, String baseCurrency);
}
