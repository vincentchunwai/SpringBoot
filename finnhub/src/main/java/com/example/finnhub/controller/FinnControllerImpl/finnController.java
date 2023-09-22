package com.example.finnhub.controller.FinnControllerImpl;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.finnhub.controller.finnIOperation;
import com.example.finnhub.entity.StockEntity;
import com.example.finnhub.framework.ApiResponse;
import com.example.finnhub.framework.BusinessException;
import com.example.finnhub.framework.Code;
import com.example.finnhub.model.Combine;
import com.example.finnhub.service.finnService;

@RestController
@RequestMapping(value = "/api/v1")
public class finnController implements finnIOperation {
  
  @Autowired
  finnService finnService;

  
  @Override
  public ResponseEntity<ApiResponse<Combine>> showInfo(String symbol) throws Exception{
    Combine combine =  Combine.builder()
      .quote(finnService.showQuote(symbol))
      .stock(finnService.showStock(symbol))
      .build();
    if(combine.getStock().getName() == null)
      //throw new RuntimeException("Stock not Found!");
      throw new BusinessException(Code.NOTFOUND);
    ApiResponse<Combine> response = ApiResponse.<Combine>builder().ok().data(combine).build();
    return ResponseEntity.ok().body(response);
  }

  @Override
  public StockEntity save(StockEntity stock){
    return finnService.save(stock);
  }

}
