package com.hkjava.demo.demofinnhub.config;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hkjava.demo.demofinnhub.entity.Stock;
import com.hkjava.demo.demofinnhub.entity.StockPrice;
import com.hkjava.demo.demofinnhub.entity.StockSymbolEntity;
import com.hkjava.demo.demofinnhub.model.apiModel.CompanyProfile;
import com.hkjava.demo.demofinnhub.model.apiModel.Quote;
import com.hkjava.demo.demofinnhub.model.dto.StockDTO;
import com.hkjava.demo.demofinnhub.model.holidayCalendar.HolidayModel;
import com.hkjava.demo.demofinnhub.model.repository.StockRepository;
import com.hkjava.demo.demofinnhub.model.repository.StockSymbolRepository;
import com.hkjava.demo.demofinnhub.service.CompanyService;
import com.hkjava.demo.demofinnhub.service.StockService;
import com.hkjava.demo.demofinnhub.service.WebStockService;
import com.hkjava.demo.demofinnhub.service.publicHolidayAPI.HolidayService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Component
@EnableScheduling
@Profile("!test")
public class SchedulerMarketUpdate {

  public static boolean start = false;

  @PersistenceContext
  private EntityManager entityManager;

  @Autowired
  WebStockService webStockService;

  @Autowired
  StockService stockService;

  @Autowired
  CompanyService companyService;

  @Autowired
  StockSymbolRepository symbolRepository;

  @Autowired
  StockSymbolEntity stockSymbolEntity;

  @Autowired
  ModelMapper modelMapper;

  @Autowired
  HolidayService holidayService;

  @Scheduled(fixedRate = 60000)
  @Transactional()
  public void stockUpdate() throws Exception {
    TimeZone.setDefault(TimeZone.getTimeZone("America/New_York"));
    DayOfWeek currentDayOfWeek = LocalDate.now(ZoneId.of("America/New_York")).getDayOfWeek();
    LocalDate now = LocalDate.now();
    List<LocalDate> holidays = holidayService.getHolidayModel("2023", "US")
        .stream()
        .map(HolidayModel::getDate)
        .collect(Collectors.toList());
    if (currentDayOfWeek != DayOfWeek.SATURDAY && currentDayOfWeek != DayOfWeek.SUNDAY
    /* && !holidays.contains(now) */) {
      LocalTime currentTime = LocalTime.now(ZoneId.of("America/New_York"));
      LocalTime marketOpenTime = LocalTime.of(9, 30);
      LocalTime marketCloseTime = LocalTime.of(16, 0);
      if (currentTime.isAfter(marketOpenTime) && currentTime.isBefore(marketCloseTime)) {
        for (StockSymbolEntity s : symbolRepository.findAll()) {

          StockDTO q = webStockService.stockInfo(s.getStockSymbol());
          StockSymbolEntity stockSymbolEntity = entityManager.find(StockSymbolEntity.class, s.getId());
          CompanyProfile companyProfile = companyService.getCompanyProfile(s.getCompanyName());
          Stock stockToUpdate = entityManager.find(Stock.class, s.getId());
          Quote quote = stockService.getQuote(s.getStockSymbol());
          StockPrice priceToSave = StockPrice.builder()
              .currentPrice(q.getCurrentPrice())
              .dayHigh(quote.getDayHigh())
              .dayLow(quote.getDayLow())
              .dayOpen(quote.getDayOpen())
              .prevDayClose(quote.getPrevDayClose())
              .stock(stockToUpdate)
              .build();
          companyService.save(s.getId(), priceToSave);

          if (Objects.nonNull(stockToUpdate)) {
            stockToUpdate.setMarketCap(companyProfile.getMarketCap());
          }

          if (Objects.nonNull(stockSymbolEntity)) {
            stockSymbolEntity.setCurrentPrice(q.getCurrentPrice());
            stockSymbolEntity.setDayHigh(q.getDayHigh());
            stockSymbolEntity.setDayLow(q.getDayLow());
            stockSymbolEntity.setMarketCap(q.getCompanyProfile().getMarketCap());
            stockSymbolEntity.setStockStatus('A');
            System.out.println("completed stock symbol" + s);
          } else {
            System.out.println("Stock symbol NOT FOUND" + s);
          }

          entityManager.merge(stockSymbolEntity);
          entityManager.merge(stockToUpdate);
          System.out.println("Operation: Stocks info updated!");
          System.out.println("Current Day of Week: " + currentDayOfWeek);
          System.out.println("Current Date: " + now);
          System.out.println("Current Time: " + LocalTime.now());
        }
      } else {
        System.out.println("Stock market is closed. No updates will be performed.");
      }
    } else {

      System.out.println("It's the weekend. No updates will be performed.");
    }
  }

}
