package com.hkjava.demo.demofinnhub.infra;

import java.time.Instant;
import java.time.ZonedDateTime;

import com.hkjava.demo.demofinnhub.exception.FinnhubException;

public class Utilities {
  
  public long getCurrentTimestamp() {
    return System.currentTimeMillis() / 1000; 
  }

  public long convertToLocalTimestamp(String localDateTimeString) throws FinnhubException{
        ZonedDateTime localDateTime = ZonedDateTime.parse(localDateTimeString);

        Instant instant = localDateTime.toInstant();
        return instant.getEpochSecond();
    }

  public static void main(String[] args) {
    Utilities utilities = new Utilities();
    System.out.println(utilities.getCurrentTimestamp());
  }
 
}
