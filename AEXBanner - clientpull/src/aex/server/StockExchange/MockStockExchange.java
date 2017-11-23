package aex.server.StockExchange;

import aex.Stock.IStock;
import aex.Tasks.GenerateStocks;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class MockStockExchange extends UnicastRemoteObject implements aex.server.StockExchange.IStockExchange {

    private List<IStock> stocks;
    private Timer timer;

    public MockStockExchange() throws RemoteException {
        stocks = new ArrayList<>();
        timer = new Timer();
        GenerateStocks generateStocks = new GenerateStocks(this);
        generateStocks.run();
        // TODO
        // randomly generate stock and add it to list every second
        timer.schedule(generateStocks, 0, 1000);
    }

    @Override
    public List<IStock> getStock() throws RemoteException{
        System.out.print("GET MOCK \n");
        return stocks;
    }

    public void setStock(List<IStock> stocks) throws RemoteException{
        this.stocks = stocks;
        System.out.print("Set mock " + stocks.size() + "\n");
    }

    public void stopTimer() throws RemoteException {
        timer.cancel();
    }
}
