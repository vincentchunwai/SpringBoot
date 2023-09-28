package com.hkjava.demo.demofinnhub.config;

import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hkjava.demo.demofinnhub.entity.Stock;
import com.hkjava.demo.demofinnhub.entity.StockPrice;
import com.hkjava.demo.demofinnhub.entity.StockSymbolEntity;
import com.hkjava.demo.demofinnhub.model.CompanyProfile;
import com.hkjava.demo.demofinnhub.model.Quote;
import com.hkjava.demo.demofinnhub.model.dto.StockDTO;
import com.hkjava.demo.demofinnhub.model.repository.StockRepository;
import com.hkjava.demo.demofinnhub.model.repository.StockSymbolRepository;
import com.hkjava.demo.demofinnhub.service.CompanyService;
import com.hkjava.demo.demofinnhub.service.StockService;
import com.hkjava.demo.demofinnhub.service.WebStockService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Component
@EnableScheduling
public class SchedulerTaskConfig {
  
  public static boolean start = false;

  @PersistenceContext
  private EntityManager entityManager;

  @Autowired
  WebStockService webStockService;

  @Autowired
  StockService stockService;

  @Autowired
  CompanyService companyService;

  @Autowired
  StockSymbolRepository symbolRepository;

  @Autowired
  StockSymbolEntity stockSymbolEntity;

  @Autowired
  ModelMapper modelMapper;

  @Scheduled(fixedRate = 10000)
  public void fixedRateTask() throws InterruptedException{
    if(start){
      System.out.println("fixedRate task -" + System.currentTimeMillis());
      Thread.sleep(15000L);
    }
  }

  @Scheduled(fixedDelay = 4000)
  public void fixedDelayTask() throws InterruptedException{
    if(start){
      System.out.println("FixedDelay Task -" + System.currentTimeMillis());
    }
  }

  @Scheduled(cron = "0/30 * 9-23 25 9 MON-FRI")
  public void fixedTimeTask(){
    if (start){
      System.out.println("FixedTime Task -" + System.currentTimeMillis());
    }
  }

  @Scheduled(fixedRate = 10000)
  @Transactional()
  public void stockUpdate() throws Exception{
    for(StockSymbolEntity s: symbolRepository.findAll()){
      
      StockDTO q = webStockService.stockInfo(s.getStockSymbol());
    StockSymbolEntity stockSymbolEntity = entityManager.find(StockSymbolEntity.class, s.getId());
    CompanyProfile companyProfile = companyService.getCompanyProfile(s.getCompanyName());
    Stock stockToUpdate = entityManager.find(Stock.class, s.getId());
     Quote quote = stockService.getQuote(s.getStockSymbol());
     StockPrice priceToSave = StockPrice.builder()
          .currentPrice(q.getCurrentPrice())
          .dayHigh(quote.getDayHigh())
          .dayLow(quote.getDayLow())
          .dayOpen(quote.getDayOpen())
          .prevDayClose(quote.getPrevDayClose())
          .stock(stockToUpdate)
          .build();
    companyService.save(s.getId(), priceToSave); 

    if(Objects.nonNull(stockToUpdate)){
      stockToUpdate.setMarketCap(companyProfile.getMarketCap());
    }
    
    if(Objects.nonNull(stockSymbolEntity)){
     stockSymbolEntity.setCurrentPrice(q.getCurrentPrice());
     stockSymbolEntity.setDayHigh(q.getDayHigh());
     stockSymbolEntity.setDayLow(q.getDayLow());
     stockSymbolEntity.setMarketCap(q.getCompanyProfile().getMarketCap());
     stockSymbolEntity.setStockStatus('A');
     System.out.println("completed stock symbol" + s);
    }
    else{
      System.out.println("Stock symbol NOT FOUND" + s);
      stockSymbolEntity.setStockStatus('I');
    }

     entityManager.merge(stockSymbolEntity);
    }
  }
}
  

