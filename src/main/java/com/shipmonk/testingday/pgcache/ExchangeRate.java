package com.shipmonk.testingday.pgcache;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "EXCHAGE_RATES_CACHE")
public class ExchangeRate {

    //Handle some unique key

    //We should not simply store timestamp of "validity" => mapping table (normalize it)
    //we can also normalize base + target + date => and store values separately

    //handle the id column, as we probably do not need it (day + base + target ?)
    @Id//long for simplification now
    @GeneratedValue
    private Long id;

    @Column(name = "DAY")
    private LocalDate day;

    @Column(name = "BASE")
    private String base;

    @Column(name = "TARGET")
    private String target;

    @Column(name = "RATE")
    private Float rate;


    public ExchangeRate(LocalDate day, String base, String target, Float rate) {
        this.day = day;
        this.base = base;
        this.target = target;
        this.rate = rate;
    }


}
