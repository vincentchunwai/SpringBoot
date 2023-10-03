package com.hkjava.demo.demofinnhub.service;

import java.util.List;

import com.hkjava.demo.demofinnhub.entity.StockSymbolEntity;
import com.hkjava.demo.demofinnhub.exception.FinnhubException;
import com.hkjava.demo.demofinnhub.model.StockSymbol;
import com.hkjava.demo.demofinnhub.model.apiModel.Quote;

public interface StockService {

  Quote getQuote(String symbol) throws FinnhubException;
  
  List<StockSymbol> getSymbol(String exchange) throws FinnhubException;

}
