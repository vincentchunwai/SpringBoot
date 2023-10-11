package com.hkjava.demo.demofinnhub.service.publicHolidayAPI;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;

import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import com.hkjava.demo.demofinnhub.infra.Protocol;
import com.hkjava.demo.demofinnhub.model.holidayCalendar.HolidayModel;

@Service
//@Qualifier("HolidayService")
public class HolidayService {

  @Autowired
  private RestTemplate restTemplate;

  @Value(value = "${api.holiday.domain}")
  private String domain;

  @Value(value = "${api.holiday.base-url}")
  private String baseUrl;

  @Value(value = "${api.holiday.endpoint}")
  private String endPoint;

  public List<HolidayModel> getHolidayModel(String year, String country) {
    String encodedYear = UriUtils.encodePathSegment(year, StandardCharsets.UTF_8);
    String encodedCountry = UriUtils.encodePathSegment(country, StandardCharsets.UTF_8);
    String url = UriComponentsBuilder.newInstance()
        .scheme(Protocol.HTTPS.name())
        .host(domain)
        .pathSegment(baseUrl)
        .pathSegment(endPoint)
        .path(encodedYear + "/" + encodedCountry)
        .build()
        .toUriString();
    try {
      HolidayModel[] holidays = restTemplate.getForObject(url, HolidayModel[].class);
      return Arrays.asList(holidays);
    } catch (RestClientException e) {
      throw new RuntimeJsonMappingException("object mapping fails");
    }
  }

}
