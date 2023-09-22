package com.example.finnhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.finnhub.entity.StockEntity;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, Long>{
  
}
