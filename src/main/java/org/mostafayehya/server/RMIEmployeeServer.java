package org.mostafayehya.server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIEmployeeServer {

    public static void main(String[] args) {
        new RMIEmployeeServer();
    }
    public RMIEmployeeServer() {
        try {
            EmployeeService server = new EmployeeServiceImpl();
            Registry registry = LocateRegistry.createRegistry(1099); // Default port is 1099, know the difference between create registry and getRegistry()

            registry.bind("EmployeeService", server);
            System.err.println("Server Ready");


        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
        }

    }


}
