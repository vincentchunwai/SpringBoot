package com.hkjava.demo.demofinnhub.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.hkjava.demo.demofinnhub.exception.FinnhubException;
import com.hkjava.demo.demofinnhub.infra.ApiResponse;
import com.hkjava.demo.demofinnhub.infra.Indicator;
import com.hkjava.demo.demofinnhub.model.apiModel.Indicators;

public interface IndicatorOperation {
  
  @GetMapping(value = "/indicator")
  @ResponseStatus(value = HttpStatus.OK)
  ApiResponse<Indicators> stockIndicator(@RequestParam(name = "symbol") String stockSymbol,
  @RequestParam(name = "indicator") String indicator)
    throws FinnhubException;



}
