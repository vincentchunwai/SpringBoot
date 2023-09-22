package com.hkjava.demo.demofinnhub.model;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CompanyProfile {
  
  private String country;

  private String currency;

  private String estimateCurrency;

  private String exchange;

  private String finnhubIndustry;

  @JsonProperty(value = "ipo")
  private LocalDate ipoDate;

  private String logo;

  @JsonProperty(value = "marketCapitalization")
  private double marketCap;

  @JsonProperty(value = "name")
  private String companyName;

  private String phone;

  private double shareOutstanding;

  private String ticker;

  private String weburl;

  

}
