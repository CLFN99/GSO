package aex.Tasks;

import aex.Stock.IStock;
import aex.Stock.Stock;
import aex.server.StockExchange.MockStockExchange;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

public class GenerateStocks extends TimerTask {
    private MockStockExchange mock;

    public GenerateStocks(MockStockExchange mock){
        this.mock = mock;
    }

    public void run() {
        // randomly generate stock
        ArrayList<IStock> stocks = new ArrayList<IStock>();
        double min = 0.00;
        double max = 100.00;

        double random = ThreadLocalRandom.current().nextDouble(min, max);
        stocks.add(new Stock("Unilever", ThreadLocalRandom.current().nextDouble(min, max)));
        stocks.add(new Stock("Shell", ThreadLocalRandom.current().nextDouble(min, max)));
        stocks.add(new Stock("Google", ThreadLocalRandom.current().nextDouble(min, max)));
        stocks.add(new Stock("Apple", ThreadLocalRandom.current().nextDouble(min, max)));
        stocks.add(new Stock("Jumbo BV", ThreadLocalRandom.current().nextDouble(min, max)));
        stocks.add(new Stock("ABN AMRO", ThreadLocalRandom.current().nextDouble(min, max)));
        stocks.add(new Stock("Heineken", ThreadLocalRandom.current().nextDouble(min, max)));
        stocks.add(new Stock("KPN", ThreadLocalRandom.current().nextDouble(min, max)));
        try {
            mock.setStock(stocks);
            System.out.print("Task " + stocks.size() + "\n");
        } catch (RemoteException rex) {
            rex.printStackTrace();
        }
    }
}
