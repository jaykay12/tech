package tech.jaykay12;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class PortfolioTest {
    Portfolio portfolio = null;

    @Mock
    static StockService stockService;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @BeforeAll
    void setUp() {
        when(stockService.getRealtimePrice("infosys")).thenReturn(2200.0f);
        when(stockService.getRealtimePrice("reliance")).thenReturn(3100.0f);
    }

    @BeforeEach
    void init(){
        List<Stock> stocks = new ArrayList<>();
        stocks.add(new Stock("infosys",10));
        stocks.add(new Stock("reliance", 5));
        portfolio = new Portfolio(stocks, 35000.0f);
        portfolio.setStockService(stockService);
    }

    @Test
    void calculateMarketValueTest(){
        
    }

}
