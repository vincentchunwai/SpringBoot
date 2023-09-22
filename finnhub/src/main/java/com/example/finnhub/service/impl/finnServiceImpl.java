package com.example.finnhub.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.finnhub.entity.StockEntity;
import com.example.finnhub.framework.BusinessException;
import com.example.finnhub.framework.Code;
import com.example.finnhub.framework.Protocol;
import com.example.finnhub.mapper.QuoteMapper;
import com.example.finnhub.model.Quote;
import com.example.finnhub.model.QuoteDTO;
import com.example.finnhub.model.Stock;
import com.example.finnhub.repository.StockRepository;
import com.example.finnhub.service.finnService;

@Service
public class finnServiceImpl implements finnService{
  
  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  StockRepository stockRepository;

  @Value(value = "${api.Finnhub.domain}")
  private String finnhubDomain;

  @Value(value = "${api.Finnhub.stockEndpoint}")
  private String stockEndpoint;

  @Value(value = "${api.Finnhub.token}")
  private String token;

  @Value(value = "${api.Finnhub.quoteEndpoint}")
  private String quoteEndpoint;

  @Override
  public QuoteDTO showQuote(String symbol) throws BusinessException{
    String url = UriComponentsBuilder.newInstance()
      .scheme(Protocol.HTTPS.name())
      .host(finnhubDomain)
      .path(quoteEndpoint)
      .queryParam("symbol", symbol)
      .queryParam("token", token)
      .toUriString();
    
    
    try{
      Quote quotes = restTemplate.getForObject(url, Quote.class);
      return QuoteMapper.map(quotes);
    } catch (RestClientException ex){
      throw new BusinessException(Code.JPH_NOTFOUND);
    }
    

  }

  @Override
  public Stock showStock(String symbol) throws BusinessException{
    String url = UriComponentsBuilder.newInstance()
      .scheme(Protocol.HTTPS.name())
      .host(finnhubDomain)
      .path(stockEndpoint)
      .queryParam("symbol", symbol)
      .queryParam("token", token)
      .toUriString();
    
    try{
      Stock stocks = restTemplate.getForObject(url, Stock.class);
      return stocks;
    } catch (RestClientException ex){
      throw new BusinessException(Code.JPH_NOTFOUND);
    }
    

  }

  @Override
  public StockEntity save(StockEntity stock){
    return stockRepository.save(stock);
  }

}
