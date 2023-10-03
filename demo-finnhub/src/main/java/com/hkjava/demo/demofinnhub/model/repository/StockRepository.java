package com.hkjava.demo.demofinnhub.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.hkjava.demo.demofinnhub.entity.Stock;

import jakarta.transaction.Transactional;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

  
  List<Stock> findByCompanyName(String companyName);

  List<Stock> findByCountry(String country);

  @Modifying
  @Transactional
  @Query("DELETE FROM Stock")
  void deleteAllCustom();
  

  @Query("SELECT s FROM Stock s")
  List<Stock> selectTable();

  @Query(value = "select s from Stock s where s.id = :id")
  List<Stock> findAllById(@Param(value = "id") Long id);

}
