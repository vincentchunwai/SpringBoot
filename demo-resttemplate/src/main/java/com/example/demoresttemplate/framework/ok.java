package com.example.demoresttemplate.framework;

import lombok.Getter;

@Getter
public class ok {
  private int code;
  private String desc;

  public ok(){
    this.code = Code.OK.getCode();
    this.desc = Code.OK.getDesc();
  }
}
