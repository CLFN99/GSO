package aex.Tasks;

import aex.IStock;
import aex.Stock;
import aex.StockExchange.MockStockExchange;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

public class GenerateStocks extends TimerTask {
    private MockStockExchange mock;

    public GenerateStocks(MockStockExchange mock){
        this.mock = mock;
    }

    public void run (){
        // randomly generate stock
        ArrayList<IStock> stocks = new ArrayList<IStock>();
        double min = 0.00;
        double max = 100.00;

        double random = ThreadLocalRandom.current().nextDouble(min, max);
        stocks.add(new Stock("Unilever", ThreadLocalRandom.current().nextDouble(min, max)));
        stocks.add(new Stock("Shell", ThreadLocalRandom.current().nextDouble(min, max)));
        stocks.add(new Stock("Google", ThreadLocalRandom.current().nextDouble(min, max)));
        mock.setStock(stocks);
        System.out.print("Task " + stocks.size() + "\n");
    }
}
