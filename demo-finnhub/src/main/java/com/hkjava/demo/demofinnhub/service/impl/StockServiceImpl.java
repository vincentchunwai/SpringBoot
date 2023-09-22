package com.hkjava.demo.demofinnhub.service.impl;


import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.hkjava.demo.demofinnhub.entity.StockSymbolEntity;
import com.hkjava.demo.demofinnhub.exception.FinnhubException;
import com.hkjava.demo.demofinnhub.infra.Code;
import com.hkjava.demo.demofinnhub.infra.Protocol;
import com.hkjava.demo.demofinnhub.model.Quote;
import com.hkjava.demo.demofinnhub.model.StockSymbol;
import com.hkjava.demo.demofinnhub.service.StockService;

@Service
public class StockServiceImpl implements StockService {

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  @Qualifier(value = "finnhubToken")
  private String token;

  @Value(value = "${api.finnhub.domain}")
  private String domain;

  @Value(value = "${api.finnhub.base-url}")
  private String baseUrl;

  @Value(value = "${api.finnhub.endpoints.stock.quote}")
  private String quoteEndpoint;

  @Value(value = "${api.finnhub.endpoints.stock.all}")
  private String quoteEndpoint2;

  @Override
  public Quote getQuote(String symbol) /* throws FinnhubException */ {
    String url = UriComponentsBuilder.newInstance() //
        .scheme(Protocol.HTTPS.name()) //
        .host(domain) //
        .pathSegment(baseUrl) //
        .path(quoteEndpoint) //
        .queryParam("symbol", symbol) //
        .queryParam("token", token) //
        .build() //
        .toUriString();
    System.out.println("url=" + url);
    /* try { */
      return restTemplate.getForObject(url, Quote.class);
    /* } catch (RestClientException e) {
      throw new FinnhubException(Code.FINNHUB_QUOTE_NOTFOUND);
    } */
  }

  @Override
  public List<StockSymbol> getSymbol(String exchange) throws FinnhubException {
    String url = UriComponentsBuilder.newInstance() //
        .scheme(Protocol.HTTPS.name()) //
        .host(domain) //
        .pathSegment(baseUrl) //
        .path(quoteEndpoint2) //
        .queryParam("exchange", exchange) //
        .queryParam("token", token)
        .build() //
        .toUriString();
    System.out.println("url=" + url);
    try {
      StockSymbol[] symbols = restTemplate.getForObject(url, StockSymbol[].class);
      return Arrays.asList(symbols);
    } catch (RestClientException e) {
      throw new FinnhubException(Code.FINNHUB_QUOTE_NOTFOUND);
    }
  }

  

}
