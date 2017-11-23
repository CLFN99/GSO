package aex.server.StockExchange;

import aex.Stock.IStock;
import aex.Tasks.GenerateStocks;
import fontyspublisher.RemotePublisher;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

/** This is the server. Will push/do changes. RemotePublisher
 * must implement IRemotePublisherForListener  **/
public class MockStockExchange extends UnicastRemoteObject implements aex.server.StockExchange.IStockExchange {
    //
    //I AM THE SERVER.
    //

    private List<IStock> stocks;
    private Timer timer;
    private RemotePublisher publisher;
    private int counter = 0;

<<<<<<< HEAD
    public MockStockExchange(RemotePublisher publisher) throws RemoteException {
        counter++;
        stocks = new ArrayList<>();
        timer = new Timer();
        this.publisher = publisher;

        publisher.registerProperty("stocks");
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind("stockPublisher", publisher);
        generateStocks();
=======
>>>>>>> c0ed5bcc5352ee0aee58aef860e99ae125f27199
    }

    public void generateStocks() throws RemoteException {
        //randomly generate stock and add it to list every second
<<<<<<< HEAD
        GenerateStocks generateStocks = new GenerateStocks(this.publisher, this);
        timer.schedule(generateStocks, 0, 1000);
        if(stocks!= null || stocks.size() != 0){

        }


=======
        GenerateStocks generateStocks = new GenerateStocks(publisher);
        //timer.schedule(generateStocks, 0, 1000);
        if(counter == 1){
            //at start of application

            generateStocks.run();
            counter++;
        }
        else {
            timer.schedule(generateStocks, 0, 1000);
        }
>>>>>>> c0ed5bcc5352ee0aee58aef860e99ae125f27199
    }

    @Override
    public List<IStock> getStock() throws RemoteException {
<<<<<<< HEAD
       // System.out.print("GET MOCK \n");
>>>>>>> c0ed5bcc5352ee0aee58aef860e99ae125f27199
        return stocks;
    }

    public void setStock(List<IStock> stocks) throws RemoteException {
        this.stocks = stocks;
        //System.out.print("Set mock " + stocks.size() + "\n");
    }

    public void stopTimer() throws RemoteException {
        timer.cancel();
    }

<<<<<<< HEAD

=======
>>>>>>> c0ed5bcc5352ee0aee58aef860e99ae125f27199
}
