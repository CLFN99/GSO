package aex.client;

import aex.StockExchange.IStockExchange;
import aex.StockExchange.MockStockExchange;

import java.util.Timer;

public class BannerController {

    private AEXBanner banner;
    private IStockExchange effectenbeurs;
    private Timer pollingTimer;

    public BannerController(AEXBanner banner) {

        this.banner = banner;
        this.effectenbeurs = new MockStockExchange();

        // Start polling timer: update banner every two seconds
        pollingTimer = new Timer();
        // TODO
    }

    // Stop banner controller
    public void stop() {
        pollingTimer.cancel();
        // Stop simulation timer of effectenbeurs
        // TODO
    }
}
