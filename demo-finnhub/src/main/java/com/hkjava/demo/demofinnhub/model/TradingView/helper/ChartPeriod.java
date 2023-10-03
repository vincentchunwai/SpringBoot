package com.hkjava.demo.demofinnhub.model.TradingView.helper;

import lombok.Getter;


@Getter
public enum ChartPeriod {
    MIN_15(15),
    MIN_30(30),
    MIN_60(60),
    HOUR_2(120),
    HOUR_3(180),
    HOUR_4(240),
    DAY_1(1440),
    DAY_10(14400),
    DAY_20(28800),
    DAY_50(72000),
    WEEK(10080),
    ;
    private ChartPeriod(final int period){
        this.period = period;
    }
    private int period;
}
