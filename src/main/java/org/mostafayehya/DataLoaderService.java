package org.mostafayehya;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

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
        FileInputStream fis = null;
        Properties properties = new Properties();
        MysqlDataSource mysqlDataSource = null;

        try {
            fis = new FileInputStream("src/main/resources/db.properties");
            properties.load(fis);
            mysqlDataSource = new MysqlDataSource();

            mysqlDataSource.setURL(properties.getProperty("MYSQL_URL"));
            mysqlDataSource.setUser(properties.getProperty("MYSQL_USERNAME"));
            mysqlDataSource.setPassword(properties.getProperty("MYSQL_PASSWORD"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return mysqlDataSource;

    }

    public static void fetchData() {
        try {
            dataSource = getDataSource();
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("select * from customer", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Customer createRow(Customer customer) {

        try {
            resultSet.moveToInsertRow();
            resultSet.updateInt("customer_id", customer.customer_id);
            resultSet.updateString("first_name", customer.firstName);
            resultSet.updateString("last_name", customer.lastName);
            resultSet.updateString("email", customer.email);
            resultSet.updateString("store_id", "2");
            resultSet.updateString("address_id", "2");
            resultSet.updateDate("create_date", new Date(System.currentTimeMillis()));
            resultSet.updateDate("last_update", new Date(System.currentTimeMillis()));
            resultSet.insertRow();
            return new Customer(resultSet.getInt(1), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Customer updateRow(Customer customer) {

        try {
            String store_id = resultSet.getString("store_id");
            String address_id = resultSet.getString("address_id");
            Date create_date = resultSet.getDate("create_date");
            Date last_update = resultSet.getDate("last_update");

            resultSet.updateInt("customer_id", customer.customer_id);
            resultSet.updateString("first_name", customer.firstName);
            resultSet.updateString("last_name", customer.lastName);
            resultSet.updateString("email", customer.email);
            resultSet.updateString("store_id", store_id);
            resultSet.updateString("address_id", address_id);
            resultSet.updateDate("create_date", create_date);
            resultSet.updateDate("last_update", last_update);
            resultSet.updateRow();
            return new Customer(resultSet.getInt(1), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static Boolean deleteRow() {

        try {
             resultSet.deleteRow();
             return resultSet.rowDeleted();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Customer fetchFirst() {

        Customer customer;
        try {
            if (resultSet.first()) {
                customer = new Customer(resultSet.getInt(1), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Customer();
    }

    public static Customer fetchLast() {

        Customer customer;
        try {
            if (resultSet.last()) {
                customer = new Customer(resultSet.getInt(1), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Customer();
    }

    public static Customer fetchNext() {

        Customer customer;
        try {
            if (resultSet.next()) {
                customer = new Customer(resultSet.getInt(1), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Customer();
    }

    public static Customer fetchPrevious() {

        Customer customer;
        try {
            if (resultSet.previous()) {
                customer = new Customer(resultSet.getInt(1), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Customer();
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
