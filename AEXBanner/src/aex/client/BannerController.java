package aex.client;

import aex.Stock.IStock;
<<<<<<< HEAD
import aex.Tasks.GenerateStocks;
=======
>>>>>>> c0ed5bcc5352ee0aee58aef860e99ae125f27199
import aex.server.StockExchange.IStockExchange;

import aex.server.StockExchange.MockStockExchange;
import fontyspublisher.IRemotePropertyListener;
import fontyspublisher.IRemotePublisherForListener;
<<<<<<< HEAD
import fontyspublisher.RemotePublisher;
=======
>>>>>>> c0ed5bcc5352ee0aee58aef860e99ae125f27199

import java.beans.PropertyChangeEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;

public class BannerController extends UnicastRemoteObject implements IRemotePropertyListener {
<<<<<<< HEAD
    //
    //I AM THE CLIENT.
    //

    private AEXBanner banner;
    private Timer timer;
    private IStockExchange stockExchange;
    private IRemotePublisherForListener publisherListner;
    private RemotePublisher publisher;
=======

>>>>>>> c0ed5bcc5352ee0aee58aef860e99ae125f27199
    // Set binding name for student administration
    private static final String bindingName = "StockExchange";
    private List<IStock> stocks;
    // References to registry and student administration
    private Registry registry = null;

    public BannerController(AEXBanner banner) throws RemoteException {
<<<<<<< HEAD
        publisher = new RemotePublisher();

        timer = new Timer();
=======
>>>>>>> c0ed5bcc5352ee0aee58aef860e99ae125f27199
        this.banner = banner;
        stocks = new ArrayList<IStock>();
        // Welcome message
        System.out.println("CLIENT USING REGISTRY");

        // Get ip address of server
        Scanner input = new Scanner(System.in);
        System.out.print("Client: Enter IP address of server: ");
        String ipAddress = input.nextLine();

        // Get port number
        System.out.print("Client: Enter port number: ");
        int portNumber = input.nextInt();

        // Create client
        createClient(ipAddress, portNumber);
<<<<<<< HEAD
        // Start polling timer: update banner every two seconds
        stockExchange = new MockStockExchange(publisher);
=======



>>>>>>> c0ed5bcc5352ee0aee58aef860e99ae125f27199

    }

    private void createClient(String ipAddress, int portNumber) {

        // Print IP address and port number for registry
        System.out.println("Client: IP Address: " + ipAddress);
        System.out.println("Client: Port number " + portNumber);


        //Locate registry at IP address and port number
        try {
            registry = LocateRegistry.getRegistry(ipAddress, portNumber);
        } catch (RemoteException ex) {
            System.out.println("Client: Cannot locate registry");
            System.out.println("Client: RemoteException: " + ex.getMessage());
            registry = null;
        }

        //init publisher
        try {
<<<<<<< HEAD
            publisherListner = (IRemotePublisherForListener) registry.lookup("stockPublisher");
            publisherListner.subscribeRemoteListener(this, "stocks");
=======
>>>>>>> c0ed5bcc5352ee0aee58aef860e99ae125f27199
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

<<<<<<< HEAD
=======

>>>>>>> c0ed5bcc5352ee0aee58aef860e99ae125f27199
    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) throws RemoteException {
        this.stocks.add((IStock)propertyChangeEvent.getNewValue());
        banner.setStocks(stocks);
<<<<<<< HEAD
        System.out.println("SETTING ONE STOCK ON BANNER.");
=======
>>>>>>> c0ed5bcc5352ee0aee58aef860e99ae125f27199
    }
}
