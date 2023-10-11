package com.hkjava.demo.demofinnhub.model.apiModel;

import java.io.Serializable;
import java.util.List;

import org.hibernate.type.SerializableType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Indicators implements Serializable{
  private String stockSymbol;
  @JsonIgnore
  private List<Double> c;
  @JsonIgnore
  private List<Double> h;
  @JsonIgnore
  private List<Double> l;
  @JsonIgnore
  private List<Double> o;
  @JsonIgnore
  private String s;
  @JsonIgnore
  private List<Double> t;
  //@JsonIgnore
  private List<Double> v;
  //@JsonInclude(JsonInclude.Include.NON_NULL)
  private List<Double> rsi;
  //@JsonInclude(JsonInclude.Include.NON_NULL)
  private List<Double> macd;
  //@JsonInclude(JsonInclude.Include.NON_NULL)
  private List<Double> sma;
  //@JsonInclude(JsonInclude.Include.NON_NULL)
  private List<Double> atr;

  public Indicators(String stockSymbol){
    this.stockSymbol = stockSymbol;
  }
}
