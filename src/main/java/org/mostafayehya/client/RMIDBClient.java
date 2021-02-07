package org.mostafayehya.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.mostafayehya.server.Employee;
import org.mostafayehya.server.EmployeeService;
import org.mostafayehya.server.EmployeeServiceImpl;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Optional;


/**
 * JavaFX App
 */
public class RMIDBClient extends Application {

    private static Scene scene;
    private static EmployeeService employeeService;

    @Override
    public void init() throws Exception {

        // Configuration of RMI
        Registry registry = LocateRegistry.getRegistry("127.0.0.1");
        employeeService = (EmployeeService) registry.lookup("EmployeeService");

    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.setTitle("Crud operations");
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        EmployeeServiceImpl.releaseResources();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {

        File fx = new File("src/main/resources/primary.fxml");
        URI uri = fx.toURI();
        URL url = uri.toURL();
        FXMLLoader fxmlLoader = new FXMLLoader(RMIDBClient.class.getClassLoader().getResource("primary.fxml"));

        System.out.println(RMIDBClient.class.getClassLoader().getResource("primary.fxml"));

        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }


    public static Employee createEmployee(Employee employee) {
        try {
            return employeeService.createEmployee(employee);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Employee updateEmployee(Employee employee) {
        try {
            return employeeService.updateEmployee(employee);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Boolean deleteEmployee() {
        try {
            return employeeService.deleteEmployee();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Employee getFirstEmployee() {
        try {
            return employeeService.getFirstEmployee();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Employee getPreviousEmployee() {
        try {
            return employeeService.getPreviousEmployee();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static Employee getLastEmployee() {

        try {
            return employeeService.getLastEmployee();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Employee getNextEmployee() {

        try {
            return employeeService.getNextEmployee();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

}