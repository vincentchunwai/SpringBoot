package com.example.finnhub.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Stock {
  private String country;
  private String currency;
  private String estimateCurrency;
  private String exchange;
  private String finnhubIndustry;
  private String ipo;
  private String logo;
  private Long marketCapitalization;
  private String name;
  private String phone;
  private Long shareOutstanding;
  private String ticker;
  private String weburl;
}
