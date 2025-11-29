package com.example.proyecto_final.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Estudiante {
    SimpleIntegerProperty carnet;
    SimpleStringProperty nombres;
    SimpleStringProperty apellidos;
    SimpleStringProperty correo;

    public Estudiante(Integer carnet, String nombres, String apellidos, String correo) {
        this.carnet = new SimpleIntegerProperty(carnet);
        this.nombres = new SimpleStringProperty(nombres);
        this.apellidos = new SimpleStringProperty(apellidos);
        this.correo = new SimpleStringProperty(correo);
    }

    public Integer getCarnet() {
        return carnet.get();
    }

    public SimpleIntegerProperty carnetProperty() {
        return carnet;
    }

    public void setCarnet(Integer carnet) {
        this.carnet.set(carnet);
    }

    public String getNombres() {
        return nombres.get();
    }

    public SimpleStringProperty nombresProperty() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres.set(nombres);
    }

    public String getApellidos() {
        return apellidos.get();
    }

    public SimpleStringProperty apellidosProperty() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos.set(apellidos);
    }

    public String getCorreo() {
        return correo.get();
    }

    public SimpleStringProperty correoProperty() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo.set(correo);
    }
}
