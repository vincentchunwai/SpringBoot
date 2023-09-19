package com.example.finnhub.mapper;

import com.example.finnhub.model.Combine;
import com.example.finnhub.model.Quote;
import com.example.finnhub.model.QuoteDTO;
import com.example.finnhub.model.Stock;
import com.example.finnhub.model.Combine.CombineBuilder;


public class StockMapper {
  public static Combine map(QuoteDTO quote, Stock stock){
    return Combine.builder()
      .quote(quote)
      .stock(stock)
      .build();
  }
}
