package aex.StockExchange;

import aex.IStock;
import java.util.List;

public interface IStockExchange {
    List<IStock> getRates();
}
