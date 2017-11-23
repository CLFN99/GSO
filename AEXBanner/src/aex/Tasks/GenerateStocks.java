package aex.Tasks;

import aex.Stock.IStock;
import aex.Stock.Stock;

import aex.server.StockExchange.MockStockExchange;

import fontyspublisher.RemotePublisher;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

public class GenerateStocks extends TimerTask {

    private MockStockExchange stockExchange;
    private RemotePublisher publisher;
    public GenerateStocks(RemotePublisher publisher, MockStockExchange stockExchange) {
        this.publisher = publisher;
        this.stockExchange = stockExchange;
    }

    public void run() {
        // randomly generate stock
        ArrayList<IStock> stocks = new ArrayList<IStock>();
        double min = 0.00;
        double max = 100.00;



        stocks.add(new Stock("Unilever", ThreadLocalRandom.current().nextDouble(min, max)));
        stocks.add(new Stock("Shell", ThreadLocalRandom.current().nextDouble(min, max)));
        stocks.add(new Stock("Google", ThreadLocalRandom.current().nextDouble(min, max)));
        stocks.add(new Stock("Apple", ThreadLocalRandom.current().nextDouble(min, max)));
        stocks.add(new Stock("Jumbo BV", ThreadLocalRandom.current().nextDouble(min, max)));
        stocks.add(new Stock("ABN AMRO", ThreadLocalRandom.current().nextDouble(min, max)));
        stocks.add(new Stock("Heineken", ThreadLocalRandom.current().nextDouble(min, max)));
        stocks.add(new Stock("KPN", ThreadLocalRandom.current().nextDouble(min, max)));
        try {

           //stockExchange.setStock(stocks);
            for(IStock stock : stocks){
                publisher.inform("stocks", null, stock);
            }
        } catch (RemoteException e) {
            e.printStackTrace();

            for(IStock stock : stocks){
                try {
                    publisher.inform("stocks", null, stock);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
            }

        }
    }
}
