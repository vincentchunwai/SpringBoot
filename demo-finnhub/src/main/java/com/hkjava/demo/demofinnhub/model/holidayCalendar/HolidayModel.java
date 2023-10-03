package com.hkjava.demo.demofinnhub.model.holidayCalendar;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class HolidayModel implements Serializable{
  
  private LocalDate date;

  private String localName;

  private String name;

  private String countryCode;

  private boolean fixed;

  private boolean global;

  private List<String> counties;

  private String launchYear;

  private List<String> types;
}
