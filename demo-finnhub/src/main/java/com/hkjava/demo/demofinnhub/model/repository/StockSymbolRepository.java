package com.hkjava.demo.demofinnhub.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hkjava.demo.demofinnhub.entity.StockSymbolEntity;

import jakarta.transaction.Transactional;

@Repository
public interface StockSymbolRepository extends JpaRepository<StockSymbolEntity, Long>{
  
  @Query("select s from StockSymbolEntity s where s.currentPrice >= :price")
  List<StockSymbolEntity> findAllBycurrentPrice(double price);

  @Modifying
  @Transactional
  @Query("DELETE FROM StockSymbolEntity")
  void deleteAllCustom();
}
