package com.example.finnhub.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Data
@Builder
@Getter
public class Combine {
  private QuoteDTO quote;
  private Stock stock;
}
