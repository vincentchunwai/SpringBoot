package com.hkjava.demo.demofinnhub.service;

import java.time.LocalDateTime;
import java.util.List;

import com.hkjava.demo.demofinnhub.entity.Stock;
import com.hkjava.demo.demofinnhub.entity.StockPrice;
import com.hkjava.demo.demofinnhub.exception.FinnhubException;
import com.hkjava.demo.demofinnhub.model.CompanyProfile;
import com.hkjava.demo.demofinnhub.entity.StockSymbolEntity;

public interface CompanyService {
  
  CompanyProfile getCompanyProfile(String symbol) throws FinnhubException;

  Stock save(Stock stock);

  void deleteById(Long id);
  
  List<Stock> findAll();

  List<Stock> findByCountry(String country);

  List<Stock> selectTable();

  StockPrice save(Long stock_id, StockPrice stockPrice);

  List<StockPrice> findAllPrices();

  List<StockPrice> findByDateTime(LocalDateTime localDateTime);

  List<StockPrice> findByDateTimeGreaterThanPrevDateClose();
}
