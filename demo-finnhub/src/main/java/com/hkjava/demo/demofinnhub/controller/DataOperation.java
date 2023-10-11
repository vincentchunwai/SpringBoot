package com.hkjava.demo.demofinnhub.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.hkjava.demo.demofinnhub.entity.Stock;
import com.hkjava.demo.demofinnhub.entity.StockPrice;

import io.swagger.annotations.ApiOperation;

public interface DataOperation {

  @PostMapping(value = "/data/stock")
  @ResponseStatus(value = HttpStatus.OK)
  @ApiOperation(value = "Get stockInfo", notes = "This endpoint retrieves stocks' information")
    @io.swagger.annotations.ApiResponses({
        @io.swagger.annotations.ApiResponse(code = 200, message = "Get Resource sucessfully"), 
        @io.swagger.annotations.ApiResponse(code = 405, message = "Invalid input data")})
  Stock save(@RequestBody Stock stock);

  @PostMapping(value = "/data/{stock_id}/stockprice")
  @ResponseStatus(value = HttpStatus.OK)
  StockPrice save(@PathVariable Long stock_id ,@RequestBody StockPrice stockPrice);

  @DeleteMapping(value = "/data/stock/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  void deleteById(@PathVariable Long id);

  @GetMapping(value = "/data/stocks")
  @ResponseStatus(value = HttpStatus.OK)
  List<Stock> findAll();

  @GetMapping(value = "/data/stock/country")
  @ResponseStatus(value = HttpStatus.OK)
  List<Stock> findByCountry(@RequestParam String country);

  @GetMapping(value = "/data/stocks/all")
  @ResponseStatus(value = HttpStatus.OK)
  List<Stock> findAll2();

  @GetMapping(value = "/data/stockprice")
  @ResponseStatus(value = HttpStatus.OK)
  List<StockPrice> findAllPrices();

  @GetMapping(value = "/data/stockprice/{localDateTime}")
  @ResponseStatus(value = HttpStatus.OK)
  List<StockPrice> findByDateTime(@RequestParam LocalDateTime localDateTime);

  @GetMapping(value = "/data/stockprice/trending")
  @ResponseStatus(value = HttpStatus.OK)
  List<StockPrice> findByCurrentPriceGreaterThanPrevClose();
}
