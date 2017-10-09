package aex.Tasks;

import aex.Stock.IStock;
import aex.server.StockExchange.IStockExchange;
import aex.client.AEXBanner;

import java.rmi.RemoteException;
import java.util.List;
import java.util.TimerTask;

public class UpdateBanner extends TimerTask {

    private IStockExchange stockExchange;
    private AEXBanner banner;

    public UpdateBanner(AEXBanner banner, IStockExchange mock){
        System.out.print("SET STOCKS + \n");
        this.banner = banner;
        this.stockExchange = mock;
    }

    @Override
    public void run() {
        try {
            List<IStock> stocks = stockExchange.getStock(); //TODO: SERVER CALL
            System.out.print("SET STOCKS + \n");
            banner.setStocks(stocks);
        } catch (RemoteException rex) {
            rex.printStackTrace();
        }
    }
}
