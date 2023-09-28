package com.hkjava.demo.demofinnhub.model.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hkjava.demo.demofinnhub.entity.StockPrice;

import jakarta.transaction.Transactional;

@Repository
public interface StockPriceRepository extends JpaRepository<StockPrice, Long>{
  

  List<StockPrice> findByDateTime(LocalDateTime dateTime);

  @Modifying
  @Transactional
  @Query("DELETE FROM StockPrice")
  void deleteAllCustom();

  @Query("Select sp from StockPrice sp where sp.currentPrice > sp.prevDayClose")
  List<StockPrice> findByCurrentPriceIsGreaterThanPrevDayClose();
}
