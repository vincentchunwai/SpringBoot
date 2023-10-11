package com.hkjava.demo.demofinnhub.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.hkjava.demo.demofinnhub.annotation.CheckSymbol;
import com.hkjava.demo.demofinnhub.entity.StockPrice;
import com.hkjava.demo.demofinnhub.entity.StockSymbolEntity;
import com.hkjava.demo.demofinnhub.exception.FinnhubException;
import com.hkjava.demo.demofinnhub.infra.ApiResponse;
import com.hkjava.demo.demofinnhub.model.StockSymbol;
import com.hkjava.demo.demofinnhub.model.dto.StockDTO;
import com.hkjava.demo.demofinnhub.model.dto.StockSymbolDTO;
import com.hkjava.demo.demofinnhub.model.dto.req.SymbolDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.Valid;
import springfox.documentation.annotations.ApiIgnore;

@Validated
@Api(tags = "StockOperation")
public interface StockOperation {

    @GetMapping(value = "/stock")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Get stockInfo", notes = "This endpoint retrieves stocks' information")
    @io.swagger.annotations.ApiResponses({
        @io.swagger.annotations.ApiResponse(code = 200, message = "Get Resource sucessfully"), 
        @io.swagger.annotations.ApiResponse(code = 405, message = "Invalid input data")})
    ApiResponse<StockDTO> stockInfo(@CheckSymbol @RequestParam("symbol") SymbolDTO symbol)
            throws FinnhubException;

    @GetMapping(value = "/stock/symbols")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Get all Stock symbols", notes = "This endpoint retrieves all stocks' symbol")
    @io.swagger.annotations.ApiResponses({
        @io.swagger.annotations.ApiResponse(code = 200, message = "Get Resource sucessfully"), 
        @io.swagger.annotations.ApiResponse(code = 405, message = "Invalid input data")})
    ApiResponse<List<StockSymbol>> symList(@RequestParam("exchange") String exchange)
            throws FinnhubException;

    @GetMapping(value = "/stock/price")
    @ApiOperation(value = "Get all Stock symbols greater or equals to /price", notes = "This endpoint retrieves all stocks that are greater or equal to input price")
    @io.swagger.annotations.ApiResponses({
        @io.swagger.annotations.ApiResponse(code = 200, message = "Get Resource sucessfully"), 
        @io.swagger.annotations.ApiResponse(code = 405, message = "Invalid input data")})
    List<StockSymbolDTO> findStockGreaterOrEqual(@RequestParam double price);

    @GetMapping(value = "/stocks/all")
    @Hidden
    List<StockSymbolEntity> findAll();

    @GetMapping(value = "/stock/symbol")
    @Hidden
    StockSymbolEntity findBySymbol(@RequestParam String symbol);
    

    @GetMapping(value = "/stock/allprice")
    @Hidden
    List<StockPrice> findAllPriceByIdOrderByStockPriceId(@RequestParam long id);
}
