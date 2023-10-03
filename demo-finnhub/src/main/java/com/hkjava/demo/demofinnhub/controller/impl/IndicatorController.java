package com.hkjava.demo.demofinnhub.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hkjava.demo.demofinnhub.controller.IndicatorOperation;
import com.hkjava.demo.demofinnhub.exception.FinnhubException;
import com.hkjava.demo.demofinnhub.infra.ApiResponse;
import com.hkjava.demo.demofinnhub.infra.Indicator;
import com.hkjava.demo.demofinnhub.model.apiModel.Indicators;
import com.hkjava.demo.demofinnhub.service.IndicatorService;

@RestController
@RequestMapping(value = "/api/v1")
public class IndicatorController implements IndicatorOperation{
  

  @Autowired
  IndicatorService indicatorService;

  @Override
  public ApiResponse<Indicators> stockIndicator(String stockSymbol, String indicator) throws FinnhubException{
    Indicators response = new Indicators(stockSymbol);
    Indicators jsonReponse = indicatorService.stockIndicator(stockSymbol, indicator);
      //response.setAtr(jsonReponse.getAtr());
      //response.setMacd(jsonReponse.getMacd());
      response.setRsi(jsonReponse.getRsi());
      //response.setSma(jsonReponse.getSma());
    return ApiResponse.<Indicators>builder()
          .ok()
          .data(response)
          .build();
  }
}
