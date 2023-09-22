package com.example.finnhub.service;
import java.util.List;

import com.example.finnhub.entity.StockEntity;
import com.example.finnhub.framework.BusinessException;
import com.example.finnhub.model.Quote;
import com.example.finnhub.model.QuoteDTO;
import com.example.finnhub.model.Stock;

public interface finnService {
  
  QuoteDTO showQuote(String symbol) throws BusinessException;

  Stock showStock(String symbol) throws BusinessException;

  StockEntity save(StockEntity stock);
}
