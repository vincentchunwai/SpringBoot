package com.hkjava.demo.demofinnhub.model.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hkjava.demo.demofinnhub.entity.Stock;
import com.hkjava.demo.demofinnhub.entity.StockPrice;

import jakarta.transaction.Transactional;

@Repository
public interface StockPriceRepository extends JpaRepository<StockPrice, Long>{
  

  List<StockPrice> findByDateTime(LocalDateTime dateTime);

  boolean existsByStock(Stock stock);

  @Modifying
  @Transactional
  @Query("DELETE FROM StockPrice")
  void deleteAllCustom();

  @Query("Select sp from StockPrice sp where sp.currentPrice > sp.prevDayClose")
  List<StockPrice> findByCurrentPriceIsGreaterThanPrevDayClose();

  @Query("Select sp from StockPrice sp where sp.stock.id = :id")
  List<StockPrice> findAllPriceById(@Param(value = "id") Long id); 

  @Query(value = "SELECT s.stock_id AS stockId, CAST(s.datetime AS DATE) AS date, MAX(s.datetime) AS maxDateTime, prev_day_close" +
  "FROM finnhub_stock_prices s" +
  "GROUP BY s.stock_id, CAST(s.datetime AS DATE);", 
    nativeQuery = true)
    List<StockPrice> findLastDataForEachStock();
}
