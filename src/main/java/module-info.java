module org.mostafayehya {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;
    requires java.naming;
    requires java.rmi;

    opens org.mostafayehya.client to javafx.fxml;

    exports org.mostafayehya.client;
    exports org.mostafayehya.server;
}