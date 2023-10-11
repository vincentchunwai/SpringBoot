package com.hkjava.demo.demofinnhub;

import java.time.LocalDate;


import org.assertj.core.internal.Bytes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hkjava.demo.demofinnhub.model.apiModel.CompanyProfile;


public class DeserializationForRestTemplateTest {

  private static ObjectMapper objectMapper;
  @BeforeAll
  static void init(){
    objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
  } 

  //@Test
  void testDeserializationForRestTemplate() throws JsonProcessingException{
    // JSON -> Object
    CompanyProfile companyProfile = CompanyProfile.builder()
            .companyName("AAPL Company")
            .country("US")
            .currency("USD")
            .estimateCurrency("USD")
            .exchange("XYZ")
            .finnhubIndustry("IJK")
            .ipoDate(LocalDate.of(1988, 12, 30))
            .marketCap(3300.12)
            .logo("/abc.png")
            .phone("12348092")
            .shareOutstanding(23.90)
            .ticker("AAPL")
            .build();
      String mockedReponseInJson = objectMapper.writeValueAsString(companyProfile);
     String mockResponse = objectMapper.writeValueAsString(companyProfile);

     JsonNode jsonNode = objectMapper.readTree(mockResponse);
      assertThat(jsonNode.path("country").asText(), is("US"));
      assertThat(jsonNode.path("ipo").asText(), is("1988-12-30"));
      assertThat(jsonNode.path("marketCapitalization").asDouble(), is(3300.12));

    CompanyProfile afterCompanyProfile =
          objectMapper.readValue(mockedReponseInJson, CompanyProfile.class);
    Assertions.assertEquals(true, afterCompanyProfile.getIpoDate().equals(companyProfile.getIpoDate())); 
    Assertions.assertEquals(true, afterCompanyProfile.getMarketCap() == companyProfile.getMarketCap());
    Assertions.assertEquals(true, afterCompanyProfile.getCountry().equals(companyProfile.getCountry()));
  }
}
