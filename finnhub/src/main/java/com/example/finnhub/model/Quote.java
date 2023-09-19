package com.example.finnhub.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@Data
public class Quote {
  private Long c;
  private Long d;
  private Long dp;
  private Long h;
  private Long l;
  private Long o;
  private Long pc;
  private Long t;
}
