package aex.client;

import aex.server.StockExchange.IStockExchange;
import aex.Tasks.UpdateBanner;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.Timer;

public class BannerController {

    private AEXBanner banner;
    private Timer pollingTimer;
    private IStockExchange stockExchange;

    // Set binding name for student administration
    private static final String bindingName = "StockExchange";

    // References to registry and student administration
    private Registry registry = null;
    private IStockExchange tempStockExchange = null;

    public BannerController(AEXBanner banner) {
        pollingTimer = new Timer();
        this.banner = banner;

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



        // Start polling timer: update banner every two seconds
        pollingTimer.schedule(new UpdateBanner(banner, tempStockExchange), 0, 1000);
       // List<IStock> stocks = tempStockExchange.getStock();
       // banner.setStocks(stocks);
    }

    private void createClient(String ipAddress, int portNumber) {
        // Print IP address and port number for registry
        System.out.println("Client: IP Address: " + ipAddress);
        System.out.println("Client: Port number " + portNumber);

        // Locate registry at IP address and port number
        try {
            registry = LocateRegistry.getRegistry(ipAddress, portNumber);
        } catch (RemoteException ex) {
            System.out.println("Client: Cannot locate registry");
            System.out.println("Client: RemoteException: " + ex.getMessage());
            registry = null;
        }

        // Print result locating registry
        if (registry != null) {
            System.out.println("Client: Registry located");
        } else {
            System.out.println("Client: Cannot locate registry");
            System.out.println("Client: Registry is null pointer");
        }

        // Print contents of registry
        if (registry != null) {
            printContentsRegistry();
        }

        // Bind student administration using registry
        if (registry != null) {
            try {
                tempStockExchange = (IStockExchange) registry.lookup(bindingName);
            } catch (RemoteException ex) {
                System.out.println("Client: Cannot bind student administration");
                System.out.println("Client: RemoteException: " + ex.getMessage());
                tempStockExchange = null;
            } catch (NotBoundException ex) {
                System.out.println("Client: Cannot bind student administration");
                System.out.println("Client: NotBoundException: " + ex.getMessage());
                tempStockExchange = null;
            }
        }

        // Print result binding student administration
        if (tempStockExchange != null) {
            System.out.println("Client: Student administration bound");
        } else {
            System.out.println("Client: Student administration is null pointer");
        }

        // Test RMI connection
        if (tempStockExchange != null) {
            ///////////
            //testStudentAdministration();

            stockExchange = tempStockExchange;

        }
    }

    // Print contents of registry
    private void printContentsRegistry() {
        try {
            String[] listOfNames = registry.list();
            System.out.println("Client: list of names bound in registry:");
            if (listOfNames.length != 0) {
                for (String s : registry.list()) {
                    System.out.println(s);
                }
            } else {
                System.out.println("Client: list of names bound in registry is empty");
            }
        } catch (RemoteException ex) {
            System.out.println("Client: Cannot show list of names bound in registry");
            System.out.println("Client: RemoteException: " + ex.getMessage());
        }
    }

    // Stop banner controller
    public void stop() {
        // Stop simulation timer
        pollingTimer.cancel();

        try {
            tempStockExchange.stopTimer();
        } catch (RemoteException rex) {
            System.out.println(rex.getStackTrace());
        }
    }
}
