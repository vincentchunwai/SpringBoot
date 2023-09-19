package com.example.finnhub.mapper;

import com.example.finnhub.model.Quote;
import com.example.finnhub.model.QuoteDTO;

public class QuoteMapper {
  public static QuoteDTO map(Quote quote){
    return QuoteDTO.builder()
      .c(quote.getC())
      .d(quote.getD())
      .dp(quote.getDp())
      .h(quote.getH())
      .l(quote.getL())
      .o(quote.getO())
      .pc(quote.getPc())
      .build();
  }
}
