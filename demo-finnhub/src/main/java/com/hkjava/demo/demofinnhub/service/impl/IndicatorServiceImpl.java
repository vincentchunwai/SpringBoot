package com.hkjava.demo.demofinnhub.service.impl;

import java.util.Arrays;
import java.util.List;

import javax.swing.text.html.parser.Entity;

import org.apache.tomcat.util.http.parser.MediaType;
import org.hibernate.engine.internal.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hkjava.demo.demofinnhub.exception.FinnhubException;
import com.hkjava.demo.demofinnhub.infra.ApiResponse;
import com.hkjava.demo.demofinnhub.infra.Code;
import com.hkjava.demo.demofinnhub.infra.Indicator;
import com.hkjava.demo.demofinnhub.infra.Protocol;
import com.hkjava.demo.demofinnhub.infra.UnixTimeHelper;
import com.hkjava.demo.demofinnhub.model.StockSymbol;
import com.hkjava.demo.demofinnhub.model.apiModel.Indicators;
import com.hkjava.demo.demofinnhub.model.dto.StockDTO;
import com.hkjava.demo.demofinnhub.service.IndicatorService;

import io.lettuce.core.StrAlgoArgs.By;

@Service
public class IndicatorServiceImpl implements IndicatorService {

  @Autowired
  @Qualifier(value = "finnhubToken")
  private String token;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private UnixTimeHelper utilities;

  @Autowired
  private RestTemplate restTemplate;

  @Value(value = "${api.finnhub.base-url}")
  private String baseUrl;

  @Value(value = "${api.finnhub.domain}")
  private String domain;

  @Value(value = "${api.finnhub.endpoints.stock.indicator}")
  private String endpoint;

  @Override
  public Indicators stockIndicator(String stockSymbol, String indicator) throws FinnhubException{
     long fromTimePeriod = utilities.getCurrentTimestamp()-50000;
     long toTimePeriod = utilities.getCurrentTimestamp();
    String url = UriComponentsBuilder.newInstance()
        .scheme(Protocol.HTTPS.name())
        .host(domain)
        .pathSegment(baseUrl)
        .path(endpoint)
        .queryParam("symbol", stockSymbol)
        .queryParam("resolution", "D")
        .queryParam("from", 1583098857)
        .queryParam("to", 1584308457)
        .queryParam("indicator", indicator)
        .queryParam("token", token)
        .build()
        .toUriString();

    try {
    String jsonResponse = restTemplate.getForObject(url, String.class);
      Indicators values = objectMapper.readValue(jsonResponse, Indicators.class);
      return values;
      
    } catch (Exception e) {
    e.printStackTrace();
    throw new FinnhubException(Code.NOTFOUND);

  }
} 


}
