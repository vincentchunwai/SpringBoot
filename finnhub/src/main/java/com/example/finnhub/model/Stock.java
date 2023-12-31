package com.example.finnhub.model;

import com.fasterxml.jackson.annotation.JsonProperty;

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
  @JsonProperty(value = "ipo")
  private String ipo_date;
  private String logo;
  @JsonProperty(value = "marketCapitalization")
  private Long marketcap;
  private String name;
  private String phone;
  private Long shareOutstanding;
  private String ticker;
  private String weburl;
}
