package com.hkjava.demo.demofinnhub.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.hkjava.demo.demofinnhub.entity.Stock;
import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

  // select * from table where company_name = "";
  List<Stock> findByCompanyName(String companyName);

  List<Stock> findByCountry(String country);

  
  //List<Stock> findById(double id);
  

  @Query("SELECT s FROM Stock s")
  List<Stock> selectTable();

  // JPQL (Java Persistence query language)
  @Query(value = "select s from Stock s where s.id = :id")
  List<Stock> findAllById(@Param(value = "id") Long id);

  // JPQL (Java Persistence quuery language)
  /*
   * @Query(
   * value = "select s from finnhub_stocks s where s.id = :id"
   * )
   * List<Stock> findAllById3 (@Param(value = "id") Long id);
   */
}
