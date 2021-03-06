package org.mostafayehya.client;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.mostafayehya.server.Employee;

public class PrimaryController implements Initializable {

    @FXML
    private TextField idTextField;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField sexTextField1;

    @FXML
    private TextField ageTextField;

    @FXML
    private TextField addressTextField;

    @FXML
    private TextField phoneTextField1;

    @FXML
    private TextField balanceTextField11;

    @FXML
    private Label statusLable;

    @FXML
    private Button newButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button firstButton;

    @FXML
    private Button previousButton;

    @FXML
    private Button nextButton;

    @FXML
    private Button lastButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    public void createNew(ActionEvent actionEvent) {

        Employee employee = new Employee(Integer.parseInt(idTextField.getText()), firstNameTextField.getText(),
                lastNameTextField.getText(), sexTextField1.getText(), Integer.parseInt(ageTextField.getText()),
                addressTextField.getText(), Integer.parseInt(phoneTextField1.getText()), Integer.parseInt(balanceTextField11.getText()));
        Employee result = RMIDBClient.createEmployee(employee);

        if (result != null) {
            idTextField.clear();
            firstNameTextField.clear();
            lastNameTextField.clear();
            sexTextField1.clear();
            ageTextField.clear();
            addressTextField.clear();
            phoneTextField1.clear();
            balanceTextField11.clear();
            statusLable.setText("Row Added !");
            statusLable.setVisible(true);

        } else { //Handle Error

            statusLable.setText("Coulnd't add data");
            statusLable.setVisible(true);

        }
    }

    public void updateRow(ActionEvent actionEvent) {
        Employee employee = new Employee(Integer.parseInt(idTextField.getText()), firstNameTextField.getText(),
                lastNameTextField.getText(), sexTextField1.getText(), Integer.parseInt(ageTextField.getText()),
                addressTextField.getText(), Integer.parseInt(phoneTextField1.getText()), Integer.parseInt(balanceTextField11.getText()));
        Employee result = RMIDBClient.updateEmployee(employee);

        if (result != null) {
            updateUI(result);
            statusLable.setText("Row updated !");
            statusLable.setVisible(true);

        } else { //Handle Error

            statusLable.setText("Coulnd't update data");
            statusLable.setVisible(true);


        }

    }

    public void deleteRow(ActionEvent actionEvent) {
        statusLable.setVisible(true);

        Boolean result = RMIDBClient.deleteEmployee();

        if (result) {
            idTextField.clear();
            firstNameTextField.clear();
            lastNameTextField.clear();
            sexTextField1.clear();
            ageTextField.clear();
            addressTextField.clear();
            phoneTextField1.clear();
            balanceTextField11.clear();
            statusLable.setText("Row Deleted!");
        } else {
            statusLable.setVisible(false);
            statusLable.setText("Can't delete row");
        }
    }

    public void getFirstRow(ActionEvent actionEvent) {
        Employee employee = RMIDBClient.getFirstEmployee();
        if (employee != null)
            updateUI(employee);
    }

    public void getPreviousRow(ActionEvent actionEvent) {
        Employee employee = RMIDBClient.getPreviousEmployee();
        if (employee != null)
            updateUI(employee);
    }

    private void updateUI(Employee employee) {
        if (employee.employee_id > 0) {
            idTextField.setText(String.valueOf(employee.employee_id));
            firstNameTextField.setText(employee.firstName);
            lastNameTextField.setText(employee.lastName);
            sexTextField1.setText(employee.sex);
            ageTextField.setText(String.valueOf(employee.age));
            addressTextField.setText(employee.address);
            phoneTextField1.setText(String.valueOf(employee.phoneNumber));
            balanceTextField11.setText(String.valueOf(employee.vacationBalance));
        }
    }

    public void getLastRow(ActionEvent actionEvent) {
        Employee employee = RMIDBClient.getLastEmployee();
        if (employee != null)
            updateUI(employee);
    }

    public void nextRow(ActionEvent actionEvent) {

        Employee employee = RMIDBClient.getNextEmployee();
        if (employee != null)
            updateUI(employee);

    }
}
