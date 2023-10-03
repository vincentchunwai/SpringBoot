package com.hkjava.demo.demofinnhub.model.TradingView.helper;
import java.time.LocalDateTime;
import java.util.List;

public interface IndicatorFormula {
  

  List<Double> smaData(ChartPeriod chartPeriod, String stockSymbol);

  List<Double> rsiData(ChartPeriod chartPeriod, String stockSymbol);

  List<Double> macdData(ChartPeriod chartPeriod, String stockSymbol);

}
