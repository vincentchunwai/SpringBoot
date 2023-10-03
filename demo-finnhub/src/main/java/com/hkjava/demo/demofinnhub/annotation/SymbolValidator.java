package com.hkjava.demo.demofinnhub.annotation;

import java.util.Arrays;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;

import com.hkjava.demo.demofinnhub.config.AppStartRunner;
import com.hkjava.demo.demofinnhub.entity.StockSymbolEntity;
import com.hkjava.demo.demofinnhub.model.dto.req.SymbolDTO;
import com.hkjava.demo.demofinnhub.model.repository.StockSymbolRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SymbolValidator implements ConstraintValidator<CheckSymbol, SymbolDTO>{
  
  @Override
  public boolean isValid(SymbolDTO symbol, ConstraintValidatorContext cxt){
    return Objects.nonNull(symbol.getSymbol())
    && AppStartRunner.availableSymbols.contains(symbol.getSymbol());
  } 
  
}
