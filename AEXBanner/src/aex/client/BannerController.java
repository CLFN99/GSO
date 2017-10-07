package aex.client;

import aex.IStock;
import aex.StockExchange.IStockExchange;
import aex.StockExchange.MockStockExchange;
import aex.Tasks.UpdateBanner;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class BannerController {

    private AEXBanner banner;
    private IStockExchange stockExchange;
    private Timer pollingTimer;

    public BannerController(AEXBanner banner) {
        pollingTimer = new Timer();
        this.banner = banner;
        this.stockExchange = new MockStockExchange();

        // Start polling timer: update banner every two seconds

        // TODO
        pollingTimer.schedule(new UpdateBanner(banner, stockExchange), 0, 1000);
       // List<IStock> stocks = stockExchange.getStock();
       // banner.setStocks(stocks);
    }

    // Stop banner controller
    public void stop() {
        // Stop simulation timer
        pollingTimer.cancel();

        stockExchange.stopTimer();
    }
}
