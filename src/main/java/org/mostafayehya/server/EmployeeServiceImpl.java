package org.mostafayehya.server;

import javax.sql.DataSource;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.Optional;

import com.mysql.cj.jdbc.MysqlDataSource;

public class EmployeeServiceImpl extends UnicastRemoteObject implements EmployeeService {

    static DataSource dataSource = null;
    static Connection connection = null;
    static PreparedStatement preparedStatement = null;
    static ResultSet resultSet = null;

    static {
        loadAllEmployees();
    }

    public EmployeeServiceImpl() throws RemoteException {
        super();
    }


    public static DataSource getDataSource() {

        MysqlDataSource mysqlDataSource = new MysqlDataSource();


        mysqlDataSource.setURL("jdbc:mysql://localhost:3306/sakila");
        mysqlDataSource.setUser("root");
        mysqlDataSource.setPassword("root");

        return mysqlDataSource;

    }

    public static void loadAllEmployees() {
        try {
            dataSource = getDataSource();
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("select * from employee", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee createEmployee(Employee employee) {

        try {
            resultSet.moveToInsertRow();
            resultSet.updateInt("Id", employee.employee_id);
            resultSet.updateString("F_Name", employee.firstName);
            resultSet.updateString("L_NAME", employee.lastName);
            resultSet.updateString("Sex", employee.sex);
            resultSet.updateInt("Age", employee.age);
            resultSet.updateString("Address", employee.address);
            resultSet.updateInt("Phone_Number", employee.phoneNumber);
            resultSet.updateInt("Vacation_Balance", employee.vacationBalance);

            resultSet.insertRow();
            return new Employee(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                    resultSet.getInt(5), resultSet.getString(6), resultSet.getInt(7), resultSet.getInt(8));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Employee updateEmployee(Employee employee) {

        try {
            resultSet.updateInt("Id", employee.employee_id);
            resultSet.updateString("F_Name", employee.firstName);
            resultSet.updateString("L_NAME", employee.lastName);
            resultSet.updateString("Sex", employee.sex);
            resultSet.updateInt("Age", employee.age);
            resultSet.updateString("Address", employee.address);
            resultSet.updateInt("Phone_Number", employee.phoneNumber);
            resultSet.updateInt("Vacation_Balance", employee.vacationBalance);
            resultSet.updateRow();
            return new Employee(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                    resultSet.getInt(5), resultSet.getString(6), resultSet.getInt(7), resultSet.getInt(8));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public Boolean deleteEmployee() {

        try {
            resultSet.deleteRow();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Employee getFirstEmployee() {

        Employee employee;
        try {
            if (resultSet.first()) {
                employee = new Employee(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                        resultSet.getInt(5), resultSet.getString(6), resultSet.getInt(7), resultSet.getInt(8));
                return employee;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Employee getLastEmployee() {

        Employee employee;
        try {
            if (resultSet.last()) {
                employee = new Employee(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                        resultSet.getInt(5), resultSet.getString(6), resultSet.getInt(7), resultSet.getInt(8));
                return employee;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Employee getNextEmployee() {

        Employee employee;
        try {
            if (!resultSet.isLast() && resultSet.next()) {
                employee = new Employee(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                        resultSet.getInt(5), resultSet.getString(6), resultSet.getInt(7), resultSet.getInt(8));
                return employee;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Employee getPreviousEmployee() {


        Employee employee;
        try {
            if (!resultSet.isFirst() && resultSet.previous()) {
                employee = new Employee(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                        resultSet.getInt(5), resultSet.getString(6), resultSet.getInt(7), resultSet.getInt(8));
                return employee;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void releaseResources() {
        try {
            if (resultSet != null)
                resultSet.close();
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
