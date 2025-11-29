module com.example.proyecto_final {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;


    opens com.example.proyecto_final to javafx.fxml;
    exports com.example.proyecto_final;
    exports com.example.proyecto_final.controllers;
    opens com.example.proyecto_final.controllers to javafx.fxml;
    opens com.example.proyecto_final.models;
}