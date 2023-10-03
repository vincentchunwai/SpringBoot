package com.hkjava.demo.demofinnhub.model.TradingView.view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.hkjava.demo.demofinnhub.model.TradingView.helper.ChartPeriod;
import com.hkjava.demo.demofinnhub.model.TradingView.helper.IndicatorFormula;
import com.hkjava.demo.demofinnhub.model.TradingView.model.SourcePoint;
import com.hkjava.demo.demofinnhub.model.TradingView.model.TradeModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class StockView extends TradeModel implements IndicatorFormula {

    SourcePoint sourcePoint = new SourcePoint();

    public StockView(String symbol){
        super(symbol);
        sourcePoint.insertDataOnDailyRate(symbol);
        sourcePoint.insertDataOnSecondRate(symbol);
    }

    @Override
    public List<Double> smaData(ChartPeriod period, String stockSymbol) {
        List<SourcePoint> data = SourcePoint.sourceMapOnSecond.get(stockSymbol);

        if (Objects.isNull(data)) {
            throw new RuntimeException("Failed to retrieve smaData." );
        }

        int periodValue = period.getPeriod();

        List<Double> smaValues = new ArrayList<>();
        double sum = 0.0;

        for (int i = 0; i < data.size(); i++) {
            sum += data.get(i).getPrice();

            if (i >= periodValue) {
                sum -= data.get(i - periodValue).getPrice();
                double sma = sum / periodValue;
                smaValues.add(sma);
            }
        }

        return smaValues;
    }

    @Override
    public List<Double> rsiData(ChartPeriod period, String stockSymbol) {
        List<SourcePoint> data = SourcePoint.sourceMapOnSecond.get(stockSymbol);

        if (Objects.isNull(data)) {
            throw new RuntimeException("Failed to retrieve smaData.");
        }

        List<Double> rsiValues = new ArrayList<>();
        int periodValue = period.getPeriod();

        if (data.size() < periodValue + 1) {
            throw new IllegalArgumentException("Insufficient data for RSI calculation.");
        }

        for (int i = periodValue; i < data.size(); i++) {
            List<Double> priceChanges = new ArrayList<>();
            for (int j = i; j > i - periodValue; j--) {
                priceChanges.add(data.get(j).getPrice() - data.get(j - 1).getPrice());
            }

            List<Double> gains = new ArrayList<>();
            List<Double> losses = new ArrayList<>();

            for (int j = 0; j < priceChanges.size(); j++) {
                if (priceChanges.get(j) >= 0) {
                    gains.add(priceChanges.get(j));
                    losses.add(0.0);
                } else {
                    gains.add(0.0);
                    losses.add(-priceChanges.get(j));
                }
            }

            double avgGain = calculateAverage(gains);
            double avgLoss = calculateAverage(losses);

            if (avgLoss == 0) {
                rsiValues.add(100.0);
            } else {
                double rs = avgGain / avgLoss;
                rsiValues.add(100.0 - (100.0 / (1.0 + rs)));
            }
        }

        return rsiValues;
    }

    @Override
    public List<Double> macdData(ChartPeriod chartPeriod, String stockSymbol) {
        List<SourcePoint> data = SourcePoint.sourceMapOnSecond.get(stockSymbol);
        if (Objects.isNull(data)) {
            throw new RuntimeException("Failed to retrieve data.");
        }
        
        List<Double> prices = data.stream().map(SourcePoint::getPrice).collect(Collectors.toList());

        int shortTermEMAPeriod = 12;
        int longTermEMAPeriod = 26;
        int signalLineEMAPeriod = 9;
        
        List<Double> macdValues = new ArrayList<>();

        List<Double> shortTermEMA = calculateEMA
        (prices, shortTermEMAPeriod);
        List<Double> longTermEMA = calculateEMA(prices, longTermEMAPeriod);

        List<Double> macdLine = new ArrayList<>();
        for (int i = 0; i < prices.size(); i++) {
            double macd = shortTermEMA.get(i) - longTermEMA.get(i);
            macdLine.add(macd);
        }

        List<Double> signalLine = calculateEMA(macdLine, signalLineEMAPeriod);

        for (int i = 0; i < prices.size(); i++) {
            double histogram = macdLine.get(i) - signalLine.get(i);
            macdValues.add(histogram);
        }

        return macdValues;
    }

    private static double calculateAverage(List<Double> values) {
        double sum = 0.0;
        for (Double value : values) {
            sum += value;
        }
        return sum / values.size();
    }

    private static List<Double> calculateEMA(List<Double> prices, int period) {
        List<Double> emaValues = new ArrayList<>();
        double multiplier = 2.0 / (period + 1);
        double ema = 0;

        for (int i = 0; i < prices.size(); i++) {
            if (i < period - 1) {
                emaValues.add(null); // EMA is undefined for the first few data points
            } else if (i == period - 1) {
                ema = calculateSimpleMovingAverage(prices.subList(0, period));
                emaValues.add(ema);
            } else {
                ema = (prices.get(i) - ema) * multiplier + ema;
                emaValues.add(ema);
            }
        }

        return emaValues;
    }

    private static double calculateSimpleMovingAverage(List<Double> prices) {
        double sum = 0;
        for (double price : prices) {
            sum += price;
        }
        return sum / prices.size();
    }
}
