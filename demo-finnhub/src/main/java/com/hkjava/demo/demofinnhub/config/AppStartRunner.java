package com.hkjava.demo.demofinnhub.config;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.hkjava.demo.demofinnhub.controller.impl.StockController;
import com.hkjava.demo.demofinnhub.entity.Stock;
import com.hkjava.demo.demofinnhub.entity.StockPrice;
import com.hkjava.demo.demofinnhub.entity.StockSymbolEntity;
import com.hkjava.demo.demofinnhub.model.StockSymbol;
import com.hkjava.demo.demofinnhub.model.apiModel.CompanyProfile;
import com.hkjava.demo.demofinnhub.model.apiModel.Quote;
import com.hkjava.demo.demofinnhub.model.dto.CompanyProfileDTO;
import com.hkjava.demo.demofinnhub.model.dto.StockDTO;
import com.hkjava.demo.demofinnhub.model.dto.StockSymbolDTO;
import com.hkjava.demo.demofinnhub.model.repository.StockPriceRepository;
import com.hkjava.demo.demofinnhub.model.repository.StockRepository;
import com.hkjava.demo.demofinnhub.model.repository.StockSymbolRepository;
import com.hkjava.demo.demofinnhub.service.CompanyService;
import com.hkjava.demo.demofinnhub.service.StockService;
import com.hkjava.demo.demofinnhub.service.WebStockService;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

@Profile("!test")
@Component
public class AppStartRunner implements CommandLineRunner{

  public static final List<String> stockInventory = List.of("AAPL", "MSFT", "TSLA", "NVDA", "GOOGL");

  public static final List<String> availableSymbols = new ArrayList<>();


  @Autowired
  StockService stockService;

  @Autowired
  StockSymbolRepository stockSymbolRepository;

  @Autowired
  StockRepository stockRepository;

  @Autowired
  StockPriceRepository stockPriceRepository;

  @Autowired
  WebStockService webStockService;

  @Autowired
  CompanyService companyService;

  @Autowired
  ModelMapper mapper;
  
  @Override
  //@Transactional
  //@PostConstruct
  public synchronized void run(String ... args) throws Exception{
      

      /* stockSymbolRepository.deleteAllCustom();
      stockRepository.deleteAllCustom();
      stockPriceRepository.deleteAllCustom(); */

      List<StockSymbol> symbols = stockService
          .getSymbol("US")
          .stream()
          .filter(stock -> stockInventory.contains(stock.getSymbol()))
          .collect(Collectors.toList());

      for(StockSymbol symbol: symbols){
        
       try{
        StockDTO stockDTO =  webStockService.stockInfo(symbol.getSymbol());
        StockSymbolEntity target = StockSymbolEntity.builder()
              .stockSymbol(symbol.getSymbol())
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
              if(!stockSymbolRepository.existsByStockSymbol(target.getStockSymbol()))
                  stockSymbolRepository.save(target);
                  availableSymbols.add(symbol.getSymbol());
                
          
       CompanyProfile companyDTO = companyService.getCompanyProfile(symbol.getSymbol());
       Stock stockToSave = Stock.builder()
            .companyName(companyDTO.getCompanyName())
            .country(companyDTO.getCountry())
            .marketCap(companyDTO.getMarketCap())
            .currency(companyDTO.getCurrency())
            .ipo(companyDTO.getIpoDate())
            .logo(companyDTO.getLogo())
            .build();
              if(!stockRepository.existsByCompanyName(stockToSave.getCompanyName()))
                  companyService.save(stockToSave);
              

       Quote quote = stockService.getQuote(symbol.getSymbol());
        StockPrice stockPriceToSave = StockPrice.builder()
              .currentPrice(quote.getCurrentPrice())
              .dayHigh(quote.getDayHigh())
              .dayLow(quote.getDayLow())
              .dayOpen(quote.getDayOpen())
              .prevDayClose(quote.getPrevDayClose())
              .stock(stockToSave)
              .build();
            if(!stockPriceRepository.existsByStock(stockToSave))
              companyService.save(stockToSave.getId(), stockPriceToSave);
      } catch (IllegalStateException ie){};
      }
    
    System.out.println("CommandLineRunner Completed");
    SchedulerMarketUpdate.start = true;
  }
}
