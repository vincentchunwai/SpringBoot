package com.hkjava.demo.demofinnhub;

import java.time.LocalDate;
import java.util.List;



import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.hkjava.demo.demofinnhub.entity.Stock;
import com.hkjava.demo.demofinnhub.exception.FinnhubException;
import com.hkjava.demo.demofinnhub.model.CompanyProfile;
import com.hkjava.demo.demofinnhub.model.repository.StockRepository;
import com.hkjava.demo.demofinnhub.service.CompanyService;
import com.hkjava.demo.demofinnhub.service.StockService;

@SpringBootTest
public class CompanyServiceTest {
  
  @MockBean
  private StockRepository stockRepository;

  @Autowired
  private CompanyService companyService;

  @MockBean 
  private RestTemplate restTemplate;

  @Autowired
  @Qualifier("finnhubToken")
  private String finnhubToken;

  @Test
  void testFindAll(){
    Stock stock1 = Stock.builder().id(1L).country("US").build();
    Stock stock2 = Stock.builder().id(2L).country("HK").build();
    Mockito.when(companyService.findAll()).thenReturn(List.of(stock1, stock2));

    List<Stock> stocks = companyService.findAll();
    assertThat(stocks,hasItem(hasProperty("country", equalTo("HK"))));
  }

  @Test
  void testRestTemplate() throws FinnhubException {
    String expectedUrl = 
      "HTTPS://finnhub.io/api/v1/stock/profile2?symbol=AAPL&token="
        .concat(finnhubToken);
    CompanyProfile mockedCompanyProfile = CompanyProfile.builder()
        .country("US")
        .ipoDate(LocalDate.of(1988, 12, 31))
        .build();
    Mockito.when(restTemplate.getForObject(expectedUrl, CompanyProfile.class))
        .thenReturn(mockedCompanyProfile);
    CompanyProfile profile = companyService.getCompanyProfile("AAPL");
      assertThat(profile, hasProperty("country",equalTo("US")));
  }

 /*  @Test
  void testRestTemplate(){
    String expectedURL = "https://finnhub"
  } */
}
