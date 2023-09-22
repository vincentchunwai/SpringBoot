package com.hkjava.demo.demofinnhub.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.hkjava.demo.demofinnhub.controller.StockOperation;
import com.hkjava.demo.demofinnhub.entity.StockSymbolEntity;
import com.hkjava.demo.demofinnhub.exception.FinnhubException;
import com.hkjava.demo.demofinnhub.infra.ApiResponse;
import com.hkjava.demo.demofinnhub.model.StockSymbol;
import com.hkjava.demo.demofinnhub.model.dto.StockDTO;
import com.hkjava.demo.demofinnhub.model.dto.StockSymbolDTO;
import com.hkjava.demo.demofinnhub.service.StockService;
import com.hkjava.demo.demofinnhub.service.WebStockService;

@RestController
@RequestMapping(value = "/api/v1")
public class StockController implements StockOperation {

  @Autowired
  WebStockService webStockService;

  @Autowired
  StockService stockService;

  @Override
  public ApiResponse<StockDTO> stockInfo(String symbol) // ""
      throws FinnhubException {
    if (symbol.isBlank())
      throw new IllegalArgumentException("Parameter Symbol is blank");
      
    return ApiResponse.<StockDTO>builder() //
        .ok() //
        .data(webStockService.stockInfo(symbol)) //
        .build();
  }

  @Override
  public ApiResponse<List<StockSymbol>> symList(String exchange)
      throws FinnhubException {
    if(exchange.isBlank())
        throw new IllegalArgumentException("Parameter exchange is blank");

     return ApiResponse.<List<StockSymbol>>builder()
        .ok()
        .data(stockService.getSymbol(exchange))
        .build();
  }

  @Override
  public List<StockSymbolDTO> findStockGreaterOrEqual(@RequestParam double price){
    return webStockService.symbolFindBycurrentPrice(price);
  }

  @Override
  public List<StockSymbolEntity> findAll(){
    return webStockService.findAll();
  }

}
