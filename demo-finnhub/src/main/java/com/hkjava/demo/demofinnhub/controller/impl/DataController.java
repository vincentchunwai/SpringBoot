package com.hkjava.demo.demofinnhub.controller.impl;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hkjava.demo.demofinnhub.controller.DataOperation;
import com.hkjava.demo.demofinnhub.entity.Stock;
import com.hkjava.demo.demofinnhub.entity.StockPrice;
import com.hkjava.demo.demofinnhub.service.CompanyService;

@RestController
@RequestMapping(value = "/api/v1")
public class DataController implements DataOperation {

  @Autowired
  private CompanyService companyService;

  @Override
  public List<Stock> findAll(){
    return companyService.findAll();
  }

  @Override
  public StockPrice save(Long stock_id, StockPrice stockPrice){
    return companyService.save(stock_id, stockPrice);
  }

  @Override
  public Stock save(Stock stock) {
    return companyService.save(stock);
  }

  @Override
  public void deleteById(Long id) {
    companyService.deleteById(id);
  }

  @Override
  public List<Stock> findByCountry(String country){
    return companyService.findByCountry(country);
  }

  @Override
  public List<Stock> findAll2(){
    return companyService.selectTable();
  }

  @Override
  public List<StockPrice> findAllPrices(){
    return companyService.findAllPrices();
  }

  @Override
  public List<StockPrice> findByDateTime(LocalDateTime localDateTime){
    return companyService.findByDateTime(localDateTime);
  }

  @Override
  public List<StockPrice> findByCurrentPriceGreaterThanPrevClose(){
    return companyService.findByDateTimeGreaterThanPrevDateClose();
  }
}
