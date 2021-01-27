module org.mostafayehya {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;
    requires java.naming;

    opens org.mostafayehya to javafx.fxml;
    exports org.mostafayehya;
}