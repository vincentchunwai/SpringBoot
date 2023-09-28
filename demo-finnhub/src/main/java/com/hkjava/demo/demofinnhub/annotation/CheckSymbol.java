package com.hkjava.demo.demofinnhub.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.hkjava.demo.demofinnhub.entity.StockSymbolEntity;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target( { ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = SymbolValidator.class)
public @interface CheckSymbol {
    //error message
    public String message() default "Invalid Stock Symbol.";
    //represents group of constraints
    public Class<?>[] groups() default {};
    
    public Class<? extends Payload>[] payload() default {};
}
