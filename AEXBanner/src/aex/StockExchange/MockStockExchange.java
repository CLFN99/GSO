package aex.StockExchange;

import aex.IStock;
import aex.Tasks.GenerateStocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class MockStockExchange implements IStockExchange {

    private List<IStock> stocks;
    private Timer timer;

    public MockStockExchange() {
        stocks = new ArrayList<>();
        timer = new Timer();
        // TODO
        // randomly generate stock and add it to list every second
        timer.schedule(new GenerateStocks(this), 0, 1000);
    }

    @Override
    public List<IStock> getStock() {
        System.out.print("GET MOCK \n");
        return stocks;
    }

    public void setStock(List<IStock> stocks){
        this.stocks = stocks;
        System.out.print("Set mock" + stocks.size() + "\n");
    }
}
