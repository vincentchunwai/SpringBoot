package com.hkjava.demo.demofinnhub.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "finnhub_stocks")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Stock implements Serializable {

  @Id // primary key
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String country;

  @Column(name = "company_name")
  private String companyName;

  @Column(name = "ipo_date")
  private LocalDate ipoDate;

  private String logo;

  @Column(name = "market_cap", columnDefinition = "NUMERIC(15,2)")
  private double marketCap;

  @Column(name = "currency")
  private String currency;

  @Override
  public boolean equals(Object o){
    if(this == o){
      return true;
    }
    if (! (o instanceof Stock)){
      return false;
    }
    Stock stock = (Stock) o;
    return Objects.equals(this.id, stock.id);
  }

}
