package com.hkjava.demo.demofinnhub.service;

import java.util.List;

import com.hkjava.demo.demofinnhub.entity.StockSymbolEntity;
import com.hkjava.demo.demofinnhub.exception.FinnhubException;
import com.hkjava.demo.demofinnhub.model.dto.StockDTO;
import com.hkjava.demo.demofinnhub.model.dto.StockSymbolDTO;

public interface WebStockService {
  
  StockDTO stockInfo(String symbol) throws FinnhubException;

  List<StockSymbolDTO> symbolFindBycurrentPrice(double currentPrice);

  List<StockSymbolEntity> findAll();
}
