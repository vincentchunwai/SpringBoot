package com.example.finnhub.framework;

import lombok.Getter;

@Getter
public enum Code {
  OK(20000, "OK"),
  // 40000 - 49999
  NOTFOUND(40000, "Wrong Stock Symbol!!!!!!!"),
  JPH_NOTFOUND(40001, "JsonPlaceHolder RestClientNotFound"),
  // Server
  SERVER_TIMEOUT(50000, "Server Timeout"),
  ;

  int code;
  String desc;

  private Code(int code, String desc){
    this.code = code;
    this.desc = desc;
  }

}
