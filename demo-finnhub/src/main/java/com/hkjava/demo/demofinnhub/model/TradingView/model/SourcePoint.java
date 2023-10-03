package com.hkjava.demo.demofinnhub.model.TradingView.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hkjava.demo.demofinnhub.entity.StockPrice;
import com.hkjava.demo.demofinnhub.entity.StockSymbolEntity;
import com.hkjava.demo.demofinnhub.service.CompanyService;
import com.hkjava.demo.demofinnhub.service.WebStockService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Builder
@Component
public class SourcePoint {

  @Autowired
  CompanyService companyService;

  @Autowired
  WebStockService webStockService;

  public static Map<String, List<SourcePoint>> sourceMapForDay = new HashMap<>();
  public static Map<String, List<SourcePoint>> sourceMapOnSecond = new HashMap<>();

  private double price;
  private LocalDate time;

  public void insertDataOnDailyRate(String stockSymbol){
    StockSymbolEntity target = webStockService.findBySymbol(stockSymbol);
    List<StockPrice> allPrices = companyService.findLastDataForEachStock()
        .stream()
        .filter(p -> p.getStock().getId() == target.getId())
        .collect(Collectors.toList());

      List<SourcePoint> listToInsert = new ArrayList<>();

      for(StockPrice p: allPrices){
        listToInsert.add(SourcePoint.builder()
          .price(p.getPrevDayClose())
          .time(p.getDateTime().toLocalDate())
          .build());
      }
      sourceMapForDay.put(stockSymbol, listToInsert);
  }

  public void insertDataOnSecondRate(String stockSymbol){
    StockSymbolEntity target = webStockService.findBySymbol(stockSymbol);
    List<StockPrice> allPrices = webStockService.findAllPriceById(target.getId());

    List<SourcePoint> listToInsert = new ArrayList<>();

      for(StockPrice p: allPrices){
        listToInsert.add(SourcePoint.builder()
          .price(p.getCurrentPrice())
          .time(p.getDateTime().toLocalDate())
          .build());
      }
      sourceMapOnSecond.put(stockSymbol, listToInsert);
  }



}
