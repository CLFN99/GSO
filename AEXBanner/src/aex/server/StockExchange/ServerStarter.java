package aex.server.StockExchange;

import fontyspublisher.RemotePublisher;
import javafx.application.Application;
import javafx.stage.Stage;

public class ServerStarter extends Application {
    private MockStockExchange stockExchange;
    private RemotePublisher publisher;

    @Override
    public void start(Stage primaryStage) throws Exception {
        publisher = new RemotePublisher();
        stockExchange = new MockStockExchange(publisher);
    }
}
