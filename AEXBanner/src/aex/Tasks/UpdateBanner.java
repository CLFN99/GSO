package aex.Tasks;

import aex.IStock;
import aex.StockExchange.IStockExchange;
import aex.client.AEXBanner;

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
        List<IStock> stocks = stockExchange.getStock();
        System.out.print("SET STOCKS + \n");
        banner.setStocks(stocks);
    }
}
