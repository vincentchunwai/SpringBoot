package com.hkjava.demo.demofinnhub.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Data
@NoArgsConstructor
@ToString
public class StockSymbol {

  @JsonProperty(value = "symbol")
  private String symbol;
}
