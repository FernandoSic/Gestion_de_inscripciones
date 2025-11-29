package com.example.proyecto_final.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Pago {
    SimpleIntegerProperty idPago;
    SimpleStringProperty Estado;

    public Pago(Integer idPago, String Estado) {
        this.idPago = new SimpleIntegerProperty(idPago);
        this.Estado = new SimpleStringProperty(Estado);
    }

    public int getIdPago() {
        return idPago.get();
    }

    public SimpleIntegerProperty idPagoProperty() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago.set(idPago);
    }

    public String getEstado() {
        return Estado.get();
    }

    public SimpleStringProperty estadoProperty() {
        return Estado;
    }

    public void setEstado(String estado) {
        this.Estado.set(estado);
    }
}
