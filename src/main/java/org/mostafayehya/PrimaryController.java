package org.mostafayehya;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PrimaryController implements Initializable {

    public TextField middleTextField;
    public TextField firstNameTextField;
    public TextField lastNameTextField;
    public TextField phoneTextField;
    public TextField emailTextField;
    public Button newButton;
    public Button updateButton;
    public Button deleteButton;
    public Button firstButton;
    public Button previousButton;
    public Button lastButton;
    public TextField zipTextField;
    @FXML
    private TextField idTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    private void switchToSecondary() throws IOException {
    }

    public void createNew(ActionEvent actionEvent) {
    }

    public void updateRow(ActionEvent actionEvent) {
    }

    public void deleteRow(ActionEvent actionEvent) {
    }

    public void getFirstRow(ActionEvent actionEvent) {

        Customer customer = DataLoaderService.fetchFirst();

        idTextField.setText(String.valueOf(customer.customer_id));
        firstNameTextField.setText(customer.firstName);
        lastNameTextField.setText(customer.lastName);
        emailTextField.setText(customer.email);


    }

    public void getPreviousRow(ActionEvent actionEvent) {
        Customer customer = DataLoaderService.fetchPrevious();
        idTextField.setText(String.valueOf(customer.customer_id));
        firstNameTextField.setText(customer.firstName);
        lastNameTextField.setText(customer.lastName);
        emailTextField.setText(customer.email);
    }

    public void getLastRow(ActionEvent actionEvent) {
        Customer customer = DataLoaderService.fetchLast();
        idTextField.setText(String.valueOf(customer.customer_id));
        firstNameTextField.setText(customer.firstName);
        lastNameTextField.setText(customer.lastName);
        emailTextField.setText(customer.email);
    }

    public void nextRow(ActionEvent actionEvent) {

        Customer customer = DataLoaderService.fetchNext();
        idTextField.setText(String.valueOf(customer.customer_id));
        firstNameTextField.setText(customer.firstName);
        lastNameTextField.setText(customer.lastName);
        emailTextField.setText(customer.email);
    }
}
