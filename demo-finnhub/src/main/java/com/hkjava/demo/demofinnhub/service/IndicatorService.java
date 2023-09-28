package com.hkjava.demo.demofinnhub.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.hkjava.demo.demofinnhub.exception.FinnhubException;
import com.hkjava.demo.demofinnhub.infra.ApiResponse;
import com.hkjava.demo.demofinnhub.infra.Indicator;
import com.hkjava.demo.demofinnhub.model.Indicators;


public interface IndicatorService {
  
  Indicators stockIndicator
  (String stockSymbol, String indicator)
  throws FinnhubException;
}
