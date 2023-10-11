package com.hkjava.demo.demofinnhub.infra;

public enum Indicator {
  SMA("SIMPLE MOVING AVERAGE"),
  RSI("RELATIVE STRENGTH INDEX"),
  MACD("MOVING AVERAGE CONVERGENCE/DIVERGENCE"),
  ATR("AVERAGE TRUE RANGE VALUE"),
  ;

  private String fullName;

  private Indicator(String fullName){
    this.fullName = fullName;
  }
  
}
