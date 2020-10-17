package tech.jaykay12;

import java.util.List;

public class Portfolio {
    private List<Stock> stocks;
    private StockService stockService;
    private Float portfolioValue;

    public Portfolio(List<Stock> stocks, Float portfolioValue) {
        this.stocks = stocks;
        this.portfolioValue = portfolioValue;
    }

    public List<Stock> getStocks() { return stocks; }

    public void setStockService(StockService stockService) { this.stockService = stockService; }

    public Float getPortfolioValue() { return portfolioValue; }

    public Float calculateMarketValue() {
        Float marketValue = 0.0f;
        for(Stock stock: this.stocks) {
            marketValue += (stock.getQuantity()*stockService.getRealtimePrice(stock.getName()));
        }
        return marketValue;
    }

    public Boolean isInProfit() {
        return (portfolioValue<calculateMarketValue()?true:false);
    }

}
