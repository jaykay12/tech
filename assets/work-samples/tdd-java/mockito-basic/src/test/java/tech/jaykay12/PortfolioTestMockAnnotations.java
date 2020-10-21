package tech.jaykay12;

import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//@RunWith(MockitoJUnitRunner.class)
public class PortfolioTestMockAnnotations {

    //@Rule public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    StockService stockService;

    @InjectMocks
    Portfolio portfolio;

    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
        
        when(stockService.getRealtimePrice("infosys")).thenReturn(2200.0f);
        when(stockService.getRealtimePrice("reliance")).thenReturn(3100.0f);
        when(stockService.getRealtimePrice("indiamart")).thenReturn(4000.0f);

        List<Stock> stocks = new ArrayList<>();
        stocks.add(new Stock("infosys",10));
        stocks.add(new Stock("reliance", 5));
        portfolio = new Portfolio(stocks, 35000.0f);
        portfolio.setStockService(stockService);
    }

    @Test
    public void calculateMarketValueTest() {
        Assertions.assertEquals(portfolio.calculateMarketValue(),37500);
    }

    @Test
    public void calculateIsInProfitTest() {
        Assertions.assertTrue(portfolio.isInProfit());
    }
}
