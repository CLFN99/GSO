package aex.server.StockExchange;

import aex.Stock.IStock;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IStockExchange extends Remote {
    List<IStock> getStock() throws RemoteException;
    void setStock(List<IStock> stocks) throws RemoteException;
    void stopTimer() throws RemoteException;
    void generateStocks() throws RemoteException;
}
