package com.example.finnhub.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
@Builder
public class QuoteDTO {
  @JsonProperty(value = "Current price" )
  private Long c;
  @JsonProperty(value = "Change" )
  private Long d;
  @JsonProperty(value = "Percent change" )
  private Long dp;
  @JsonProperty(value = "High price of the day" )
  private Long h;
  @JsonProperty(value = "Low price of the day" )
  private Long l;
  @JsonProperty(value = "Open price of the day" )
  private Long o;
  @JsonProperty(value = "Previous close price" )
  private Long pc;
  
}
