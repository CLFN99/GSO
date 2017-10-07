package aex.StockExchange;

import aex.IStock;
import java.util.List;
import java.util.Timer;

public interface IStockExchange {
    List<IStock> getStock();
    void setStock(List<IStock> stocks);
    void stopTimer();
}
