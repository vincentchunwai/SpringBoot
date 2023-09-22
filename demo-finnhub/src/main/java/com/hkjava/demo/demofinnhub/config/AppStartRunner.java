package com.hkjava.demo.demofinnhub.config;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.hkjava.demo.demofinnhub.controller.impl.StockController;
import com.hkjava.demo.demofinnhub.entity.StockSymbolEntity;
import com.hkjava.demo.demofinnhub.model.StockSymbol;
import com.hkjava.demo.demofinnhub.model.dto.StockDTO;
import com.hkjava.demo.demofinnhub.model.dto.StockSymbolDTO;
import com.hkjava.demo.demofinnhub.model.repository.StockSymbolRepository;
import com.hkjava.demo.demofinnhub.service.StockService;
import com.hkjava.demo.demofinnhub.service.WebStockService;

import jakarta.transaction.Transactional;

@Component
public class AppStartRunner implements CommandLineRunner{

  @Autowired
  StockService stockService;

  @Autowired
  StockSymbolRepository stockSymbolRepository;

  @Autowired
  WebStockService webStockService;

  @Autowired
  ModelMapper mapper;
  
  @Override
  @Transactional
  public synchronized void run(String ... args) throws Exception{

      stockSymbolRepository.deleteAllCustom();
      //Before Server Start
      // 0. get all symbol(US) from below api
      //    https://finnhub.io/api/v1/stock/symbol?exchange=US
      // 1. Get Company Profile 2 and insert into database
      // 2. Get Stock price and insert into database

      List<StockSymbol> symbols = stockService.getSymbol("US");
      for(int i =0; i< 10; i++){
        
       try{
        StockDTO stockDTO =  webStockService.stockInfo(symbols.get(i).getSymbol());
        StockSymbolEntity target = StockSymbolEntity.builder()
              .stockSymbol(symbols.get(i).getSymbol())
              .companyName(stockDTO.getCompanyProfile().getCompanyName())
              .currency(stockDTO.getCompanyProfile().getCurrency())
              .currentPrice(stockDTO.getCurrentPrice())
              .marketCap(stockDTO.getCompanyProfile().getMarketCap())
              .dayHigh(stockDTO.getDayHigh())
              .dayLow(stockDTO.getDayLow())
              .logo(stockDTO.getCompanyProfile().getLogo())
              .dayOpen(stockDTO.getDayOpen())
              .prevDayClose(stockDTO.getPrevDayClose())
              .ipoDate(stockDTO.getCompanyProfile().getIpoDate())
              .build();
              stockSymbolRepository.save(target);
      } catch (IllegalStateException ie){};
      }
    
    
  }
}
