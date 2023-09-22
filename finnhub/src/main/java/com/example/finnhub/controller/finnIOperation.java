package com.example.finnhub.controller;

import java.util.List;

import com.example.finnhub.entity.StockEntity;
import com.example.finnhub.framework.ApiResponse;
import com.example.finnhub.model.Combine;
import com.example.finnhub.model.Quote;
import com.example.finnhub.model.QuoteDTO;
import com.example.finnhub.model.Stock;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface finnIOperation {
  
  @GetMapping(value = "/general/{symbol}")
  ResponseEntity<ApiResponse<Combine>> showInfo(@PathVariable(name = "symbol") String symbol) throws Exception;

  @PostMapping(value = "/data/stock")
  @ResponseStatus(value = HttpStatus.OK)
  StockEntity save(@RequestBody StockEntity stock);
  
}
