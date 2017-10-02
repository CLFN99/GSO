package aex.StockExchange;

import aex.IStock;

import java.util.ArrayList;
import java.util.List;

public class MockStockExchange implements IStockExchange {

    private List<IStock> stocks;

    public MockStockExchange() {
        stocks = new ArrayList<>();
    }

    @Override
    public List<IStock> getRates() {
        return stocks;
    }
}
