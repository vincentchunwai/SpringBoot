package com.hkjava.demo.demofinnhub.model.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StockSymbolDTO {
  
  private Long id;

  private String stockSymbol;

  private String companyName;

  private LocalDate ipoDate;

  private String logo;

  private double marketCap;

  private String currency;

  private double currentPrice;

  private double dayHigh;

  private double dayLow;

  private double dayOpen;

  private double prevDayClose;
}
