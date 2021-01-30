package org.mostafayehya;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    public Label statusLable;
    @FXML
    private TextField idTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    private void switchToSecondary() throws IOException {
    }

    public void createNew(ActionEvent actionEvent) {

        Customer customer = new Customer(Integer.valueOf(idTextField.getText()), firstNameTextField.getText(), lastNameTextField.getText(), emailTextField.getText());
        customer = DataLoaderService.createRow(customer);

        if (customer != null) {
            idTextField.clear();
            firstNameTextField.clear();
            lastNameTextField.clear();
            emailTextField.clear();
            statusLable.setText("Row Added !");
            statusLable.setVisible(true);

        } else { //Handle Error

            statusLable.setText("Coulnd't add data");
            statusLable.setVisible(true);


        }
    }

    public void updateRow(ActionEvent actionEvent) {
        Customer customer = new Customer(Integer.valueOf(idTextField.getText()), firstNameTextField.getText(), lastNameTextField.getText(), emailTextField.getText());
        customer = DataLoaderService.updateRow(customer);

        if (customer != null) {
            idTextField.setText(String.valueOf(customer.customer_id));
            firstNameTextField.setText(customer.firstName);
            lastNameTextField.setText(customer.lastName);
            emailTextField.setText(customer.email);
            statusLable.setText("Row updated !");
            statusLable.setVisible(true);

        } else { //Handle Error

            statusLable.setText("Coulnd't update data");
            statusLable.setVisible(true);


        }

    }

    public void deleteRow(ActionEvent actionEvent) {

        if(DataLoaderService.deleteRow()){
            statusLable.setText("Row Deleted!");
        }else {
            statusLable.setVisible(false);
            statusLable.setText("Can't delete row");
        }
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
