package com.hkjava.demo.demofinnhub.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.hkjava.demo.demofinnhub.entity.StockSymbolEntity;
import com.hkjava.demo.demofinnhub.exception.FinnhubException;
import com.hkjava.demo.demofinnhub.infra.ApiResponse;
import com.hkjava.demo.demofinnhub.model.StockSymbol;
import com.hkjava.demo.demofinnhub.model.dto.StockDTO;
import com.hkjava.demo.demofinnhub.model.dto.StockSymbolDTO;

public interface StockOperation {

    @GetMapping(value = "/stock")
    @ResponseStatus(value = HttpStatus.OK)
    ApiResponse<StockDTO> stockInfo(@RequestParam("symbol") String symbol)
            throws FinnhubException;

    @GetMapping(value = "/stock/symbols")
    @ResponseStatus(value = HttpStatus.OK)
    ApiResponse<List<StockSymbol>> symList(@RequestParam("exchange") String exchange)
            throws FinnhubException;

    @GetMapping(value = "/stock/price")
    List<StockSymbolDTO> findStockGreaterOrEqual(@RequestParam double price);

    @GetMapping(value = "/stocks/all")
    List<StockSymbolEntity> findAll();
    
}
