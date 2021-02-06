package org.mostafayehya;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DataLoaderService {

    static DataSource dataSource = null;
    static Connection connection = null;
    static PreparedStatement preparedStatement = null;
    static ResultSet resultSet = null;

    static {
        fetchData();
    }

    public static DataSource getDataSource() {

        MysqlDataSource mysqlDataSource = new MysqlDataSource();


        mysqlDataSource.setURL("jdbc:mysql://localhost:3306/sakila");
        mysqlDataSource.setUser("root");
        mysqlDataSource.setPassword("root");

        return mysqlDataSource;

    }

    public static void fetchData() {
        try {
            dataSource = getDataSource();
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("select * from employee", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Employee createRow(Employee employee) {

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

    public static Optional<Employee> updateRow(Employee employee) {

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
            return Optional.of(new Employee(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                    resultSet.getInt(5), resultSet.getString(6), resultSet.getInt(7), resultSet.getInt(8)));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();

    }

    public static Boolean deleteRow() {

        try {
            resultSet.deleteRow();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Employee fetchFirst() {

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
        return new Employee();
    }

    public static Optional<Employee> fetchLast() {

        Employee employee;
        try {
            if (resultSet.last()) {
                employee = new Employee(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                        resultSet.getInt(5), resultSet.getString(6), resultSet.getInt(7), resultSet.getInt(8));
                return Optional.of(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public static Employee fetchNext() {

        Employee employee;
        try {
            if (!resultSet.isLast()&& resultSet.next() ) {
                employee = new Employee(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                        resultSet.getInt(5), resultSet.getString(6), resultSet.getInt(7), resultSet.getInt(8));
                return employee;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Employee();
    }

    public static Optional<Employee> fetchPrevious() {


        Employee employee;
        try {
            if (!resultSet.isFirst() && resultSet.previous()) {
                employee = new Employee(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                        resultSet.getInt(5), resultSet.getString(6), resultSet.getInt(7), resultSet.getInt(8));
                return Optional.of(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
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
