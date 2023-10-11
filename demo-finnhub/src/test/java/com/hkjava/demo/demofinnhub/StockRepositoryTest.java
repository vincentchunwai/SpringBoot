package com.hkjava.demo.demofinnhub;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.notNull;

import org.h2.engine.Session;
import org.hamcrest.CoreMatchers;
import static org.hibernate.SessionFactory.*;

import org.hibernate.SessionFactory;
import org.hibernate.mapping.List;

import static org.hamcrest.Matcher.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import com.hkjava.demo.demofinnhub.config.TestDatabaseConfig;
import com.hkjava.demo.demofinnhub.entity.Stock;
import com.hkjava.demo.demofinnhub.model.repository.StockRepository;

import jakarta.transaction.Transactional;

//@DataJpaTest // inject Repository related Bean
@Import(TestDatabaseConfig.class)
@TestPropertySource(properties = { "spring.jpa.hibernate.ddl-auto=update" })
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
//@Transactional
public class StockRepositoryTest {

  @Autowired
  private StockRepository stockRepository;

  @Autowired
  private TestEntityManager entityManager;

  
/* 
  @Test
  @Order(1)
  void testFindById() {
    Stock entity = new Stock();
    // entity.setId(1L);
    entity.setCountry("HK");
    entity.setCompanyName("Orange Company");
    entity.setMarketCap(9876123.23);
    entityManager.persist(entity); // JPA <-> cache memory -> database harddisk
    entityManager.flush(); // Database commit; -> harddisk

    Stock stock = stockRepository.findById(1L).orElse(null);
    // assertThat(stock, notNull());
    assertThat(stock, hasProperty("companyName", equalTo("Orange Company")));
    stock.setCountry("US");

    Stock stock2 = new Stock();
    // entity.setId(1L);
    stock2.setCountry("US");
    stock2.setCompanyName("Orange Company");
    stock2.setMarketCap(9876123.23);

    entityManager.persist(stock2);
    entityManager.flush();
    stock2 = stockRepository.findById(2L).orElse(null);
    assertThat(stock2, hasProperty("country", equalTo("US")));
  }

  @Test
  @Order(2)
  void testFindByCountry() {
    Stock entity = new Stock();
    // entity.setId(1L);
    entity.setCountry("US");
    entity.setCompanyName("Orange Company");
    entity.setMarketCap(9876123.23);
    entityManager.persist(entity); // JPA <-> cache memory -> database harddisk
    entityManager.flush();
    java.util.List<Stock> stocks = stockRepository.findByCountry("US");

    assertThat(stocks, hasItem(hasProperty("country", equalTo("US"))));

  }

  @Test
  @Order(3)
  void testDeleteById() {
    Stock entity = new Stock();
    entity.setCountry("CN");
    entity.setCompanyName("China Company");
    entity.setMarketCap(92010223.23);
    entityManager.persist(entity);
    entityManager.flush();

    java.util.List<Stock> stocks = stockRepository.findByCountry("CN");
    Long id = (Long) entityManager.persistAndGetId(entity);

    stockRepository.deleteById(id);
    Stock afterDeleted = entityManager.find(Stock.class, id);
    assertThat(afterDeleted, CoreMatchers.nullValue());
  }

  @Test
  @Order(4)
  void testFindAllById() {
    Stock entity = new Stock();
    entity.setCountry("CN");
    entity.setCompanyName("China Company");
    entity.setMarketCap(92010223.23);
    entityManager.persist(entity);
    entityManager.flush();

    java.util.List<Stock> stocks = stockRepository.findAllById(5L);
    assertThat(stocks, hasItem(hasProperty("companyName", equalTo("China Company"))));

  } */

}
