package com.hkjava.demo.demofinnhub.model.dto;

import com.hkjava.demo.demofinnhub.annotation.CheckSymbol;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SymbolDTO {
  
  @CheckSymbol
  String symbol;
}
