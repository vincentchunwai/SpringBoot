package com.hkjava.demo.demofinnhub.service.impl;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.hkjava.demo.demofinnhub.entity.Stock;
import com.hkjava.demo.demofinnhub.entity.StockPrice;
import com.hkjava.demo.demofinnhub.entity.StockSymbolEntity;
import com.hkjava.demo.demofinnhub.exception.FinnhubException;
import com.hkjava.demo.demofinnhub.infra.Code;
import com.hkjava.demo.demofinnhub.infra.Protocol;
import com.hkjava.demo.demofinnhub.infra.RedisHelper;
import com.hkjava.demo.demofinnhub.model.StockSymbol;
import com.hkjava.demo.demofinnhub.model.apiModel.CompanyProfile;
import com.hkjava.demo.demofinnhub.model.repository.StockPriceRepository;
import com.hkjava.demo.demofinnhub.model.repository.StockRepository;
import com.hkjava.demo.demofinnhub.service.CompanyService;

import jakarta.persistence.EntityNotFoundException;

@Service
//@Qualifier("CompanyServiceImpl")
public class CompanyServiceImpl implements CompanyService {

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private RedisHelper<CompanyProfile> redisProfileHelper;

  @Autowired
  StockRepository stockRepository;

  @Autowired
  StockPriceRepository stockPriceRepository;

  @Autowired
  @Qualifier(value = "finnhubToken")
  private String token;

  @Value(value = "${api.finnhub.domain}")
  private String domain;

  @Value(value = "${api.finnhub.base-url}")
  private String baseUrl;

  @Value(value = "${api.finnhub.endpoints.stock.profile2}")
  private String companyProfile2Endpoint;


  @Override
  public Stock save(Stock stock) {
    return stockRepository.save(stock); // insert into
  }

  @Override
  public void refreshCompanyProfile(String symbol) throws FinnhubException{
    // getCompanyProfile(String symbol)
    // if normal reponse, findById
  }
  @Override
  public StockPrice save(Long Stock_id, StockPrice stockPrice){
    Stock stock = stockRepository.findById(Stock_id).orElseThrow(() -> new EntityNotFoundException());
    stockPrice.setStock(stock);
    return stockPriceRepository.save(stockPrice);
  }


  @Override
  public List<StockPrice> findByDateTime(LocalDateTime localDateTime){
    return stockPriceRepository.findByDateTime(localDateTime);
  }

  @Override
  public List<Stock> selectTable(){
    return stockRepository.selectTable();
  }

  @Override
  public List<Stock> findByCountry(String country){
    return stockRepository.findByCountry(country);
  }

  @Override
  public List<Stock> findAll(){
    return stockRepository.findAll();
  }

  @Override
  public void deleteById(Long id) {
    stockRepository.deleteById(id); // delete from table where id = ?
  }

  @Override
  public List<StockPrice> findAllPrices(){
    return stockPriceRepository.findAll();
  }

  @Override
  public List<StockPrice> findByDateTimeGreaterThanPrevDateClose(){
    return stockPriceRepository.findByCurrentPriceIsGreaterThanPrevDayClose();
  }

  @Override
  public List<StockPrice> findLastDataForEachStock(){
    return stockPriceRepository.findLastDataForEachStock();
  }


  @Override
  public CompanyProfile getCompanyProfile(String symbol)
      throws FinnhubException {
    String url = UriComponentsBuilder.newInstance() //
        .scheme(Protocol.HTTPS.name()) //
        .host(domain) //
        .pathSegment(baseUrl) //
        .path(companyProfile2Endpoint) //
        .queryParam("symbol", symbol) //
        .queryParam("token", token) //
        .build() //
        .toUriString();
    System.out.println("url=" + url);
    try {
      CompanyProfile profile = restTemplate.getForObject(url, CompanyProfile.class);

      if(Objects.nonNull(profile)){
        redisProfileHelper.set(symbol, profile, 60000);
        return profile;
      }
      else{
        Object cachedObject = redisProfileHelper.get(symbol);
         if(Objects.isNull(cachedObject) && cachedObject instanceof CompanyProfile)
          return (CompanyProfile) cachedObject;
      }
    } catch (RestClientException e) {
      Object profileFromRedis = redisProfileHelper.get(symbol);
      if(profileFromRedis == null)
          throw new FinnhubException(Code.FINNHUB_PROFILE2_NOTFOUND);
    }
    throw new FinnhubException(Code.NOTFOUND);

  }



}
