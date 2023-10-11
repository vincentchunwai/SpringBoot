package com.hkjava.demo.demofinnhub.infra;

import java.time.Instant;
import java.time.ZonedDateTime;

import com.hkjava.demo.demofinnhub.exception.FinnhubException;

public class UnixTimeHelper {
  
  public long getCurrentTimestamp() {
    return System.currentTimeMillis() / 1000; 
  }

  public long convertToLocalTimestamp(String localDateTimeString) throws FinnhubException{
        ZonedDateTime localDateTime = ZonedDateTime.parse(localDateTimeString);

        Instant instant = localDateTime.toInstant();
        return instant.getEpochSecond();
    }
 
}
