package com.hkjava.demo.demofinnhub.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hkjava.demo.demofinnhub.model.holidayCalendar.HolidayModel;
import com.hkjava.demo.demofinnhub.service.publicHolidayAPI.HolidayService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping(value = "/api/v1")
public class HolidayController {

  @Autowired
  HolidayService holidayService;

  @GetMapping(value = "/holiday")
  @ResponseStatus(value = HttpStatus.OK)
  public List<HolidayModel> showHolidayModel(@RequestParam(name = "year") String year, @RequestParam(name = "countrycode") String country){
    return holidayService.getHolidayModel(year, country);
  }
}
