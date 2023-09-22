package com.hkjava.demo.demofinnhub.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.hkjava.demo.demofinnhub.entity.StockSymbolEntity;
import com.hkjava.demo.demofinnhub.exception.FinnhubException;
import com.hkjava.demo.demofinnhub.infra.Code;
import com.hkjava.demo.demofinnhub.model.CompanyProfile;
import com.hkjava.demo.demofinnhub.model.Quote;
import com.hkjava.demo.demofinnhub.model.dto.StockDTO;
import com.hkjava.demo.demofinnhub.model.dto.StockSymbolDTO;
import com.hkjava.demo.demofinnhub.model.mapper.FinnhubMapper;
import com.hkjava.demo.demofinnhub.model.repository.StockSymbolRepository;
import com.hkjava.demo.demofinnhub.service.CompanyService;
import com.hkjava.demo.demofinnhub.service.StockService;
import com.hkjava.demo.demofinnhub.service.WebStockService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class WebStockServiceImpl implements WebStockService {

  @Autowired
  CompanyService companyService;

  @Autowired
  StockService stockService;

  @Autowired
  ModelMapper modelMapper;

  @Autowired
  StockSymbolRepository stockSymbolRepository;

  @Autowired
  FinnhubMapper finnhubMapper;

  @Override
  public StockDTO stockInfo(String symbol) throws FinnhubException {
    CompanyProfile profile = companyService.getCompanyProfile(symbol);
    Quote quote = stockService.getQuote(symbol);
    if (profile == null && quote == null)
      throw new FinnhubException(Code.THIRD_PARTY_SERVER_UNAVAILABLE);
    return finnhubMapper.map(profile, quote);
  }

  @Override
  public List<StockSymbolDTO> symbolFindBycurrentPrice(double currentPrice) {

    List<StockSymbolEntity> syms = stockSymbolRepository.findAllBycurrentPrice(currentPrice);
    List<StockSymbolDTO> result = syms.stream()
        .map(s -> StockSymbolDTO.builder()
                .id(s.getId())
                .stockSymbol(s.getStockSymbol())
                .companyName(s.getCompanyName())
                .ipoDate(s.getIpoDate())
                .logo(s.getLogo())
                .marketCap(s.getMarketCap())
                .currency(s.getCurrency())
                .currentPrice(s.getCurrentPrice())
                .dayHigh(s.getDayHigh())
                .dayLow(s.getDayLow())
                .dayOpen(s.getDayOpen())
                .prevDayClose(s.getPrevDayClose())
                .build())
        .collect(Collectors.toList());

    
    return result;

  }

  @Override
  public List<StockSymbolEntity> findAll() {
    return this.stockSymbolRepository.findAll();
  }

}
