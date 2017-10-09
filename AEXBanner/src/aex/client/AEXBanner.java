package aex.client;

import aex.Stock.IStock;
import aex.server.StockExchange.IStockExchange;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.text.DecimalFormat;
import java.util.List;

public class AEXBanner extends Application {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 100;
    public static final int NANO_TICKS = 20000000;
    // FRAME_RATE = 1000000000/NANO_TICKS = 50;

    private List<IStock> stocks;
    private Text text;
    private double textLength;
    private double textPosition;
    private BannerController controller;
    private AnimationTimer animationTimer;
    private IStockExchange stockExchange;

//    public AEXBanner(IStockExchange stockExchange) {
//        this.stockExchange = stockExchange;
//        //Application.launch();
//        AEXBanner.launch(AEXBanner.class);
//    }
//
//    public AEXBanner(String[] args) {
//
//    }

    @Override
    public void start(Stage primaryStage) {
        controller = new BannerController(this);

        Font font = new Font("Arial", HEIGHT - 10);
        text = new Text();
        text.setFont(font);
        text.setFill(Color.BLACK);

        Pane root = new Pane();
        root.getChildren().add(text);
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        primaryStage.setTitle("AEX banner");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.toFront();


        // Start animation: text moves from right to left
        animationTimer = new AnimationTimer() {
            private long prevUpdate;

            @Override
            public void handle(long now) {
                long lag = now - prevUpdate;
                if (lag >= NANO_TICKS) {
                    // calculate new location of text

                    DecimalFormat df = new DecimalFormat("000.00");
                    String stockString = "";
                    for (IStock stock: stocks) {
                        String stock1 =  stock.getName() + ": " + df.format(stock.getRate());
                        stockString = stockString + " " + stock1;
                    }
                    for(int i = 0; i < 100; i++){
                        setStock(stockString + " " + stockString);
                    }

                    //System.out.printf("Text width: %s; Text X: %s%n", text.getLayoutBounds().getWidth(), text.getLayoutX());
                    if (-text.getLayoutX() < text.getLayoutBounds().getWidth()) { //Minus text.getLayoutX() to account for the negative x number (when scrolling to the left)
                        text.relocate(text.getLayoutX() - 2, 0);
                    } else {
                        text.relocate(0, 0);
                    }
                    prevUpdate = now;
                }
            }
            @Override
            public void start() {
                prevUpdate = System.nanoTime();
                textPosition = 0;
                super.start();
            }
        };
        animationTimer.start();
    }

    public void setStocks(List<IStock> stocks){
        this.stocks = stocks;
    }

    public void setStock(String stock) {
        text.setText(stock + " " );
        textLength = WIDTH;
    }

    @Override
    public void stop() {
        controller.stop();
        animationTimer.stop();
    }
}
