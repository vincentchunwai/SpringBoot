package com.hkjava.demo.demofinnhub.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "finnhub_copy")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class StockSymbolEntity implements Serializable{
  
  @Id // primary key
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "stock_symbol")
  private String stockSymbol;

  @Column(name = "company_name")
  private String companyName;

  @Column(name = "ipo_date")
  private LocalDate ipoDate;

  private String logo;

  @Column(name = "market_cap", columnDefinition = "NUMERIC(15,2)")
  private double marketCap;

  @Column(name = "currency")
  private String currency;

  @Column(name = "current_price", columnDefinition = "NUMERIC(15,2)")
  private double currentPrice;

  @Column(name = "day_high", columnDefinition = "NUMERIC(15,2)")
  private double dayHigh;

  @Column(name = "day_low", columnDefinition = "NUMERIC(15,2)")
  private double dayLow;

  @Column(name = "day_open", columnDefinition = "NUMERIC(15,2)")
  private double dayOpen;

  @Column(name = "prev_day_close", columnDefinition = "NUMERIC(15,2)")
  private double prevDayClose;
}
