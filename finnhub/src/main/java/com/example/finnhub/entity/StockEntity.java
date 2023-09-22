package com.example.finnhub.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "My_finnhub_stocks")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StockEntity implements Serializable{
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String country;

  @Column(name = "company_name")
  private String companyName;

  @Column(name = "ipo")
  private LocalDate ipo;

  private String logo;

  @Column(name = "market_cap", columnDefinition = "NUMERIC(15,2)")
  private double marketCap;

  @Column(name = "currency")
  private String currency;
}
