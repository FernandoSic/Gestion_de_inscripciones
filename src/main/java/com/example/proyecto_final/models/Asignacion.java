package com.example.proyecto_final.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Asignacion {
    SimpleIntegerProperty idAsignacion;
    SimpleIntegerProperty Carnet;
    SimpleIntegerProperty idCurso;
    SimpleStringProperty FechaAsignacion;
    SimpleIntegerProperty idPago;

    public Asignacion(int idAsignacion, int carnet, int idCurso, String fechaAsignacion, int idPago) {
        this.idAsignacion = new SimpleIntegerProperty(idAsignacion);
        this.Carnet = new SimpleIntegerProperty(carnet);
        this.idCurso = new SimpleIntegerProperty(idCurso);
        this.FechaAsignacion = new SimpleStringProperty(fechaAsignacion);
        this.idPago = new SimpleIntegerProperty(idPago);
    }

    public int getIdAsignacion() {
        return idAsignacion.get();
    }

    public SimpleIntegerProperty idAsignacionProperty() {
        return idAsignacion;
    }

    public void setIdAsignacion(int idAsignacion) {
        this.idAsignacion.set(idAsignacion);
    }

    public int getCarnet() {
        return Carnet.get();
    }

    public SimpleIntegerProperty carnetProperty() {
        return Carnet;
    }

    public void setCarnet(int carnet) {
        this.Carnet.set(carnet);
    }

    public int getIdCurso() {
        return idCurso.get();
    }

    public SimpleIntegerProperty idCursoProperty() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso.set(idCurso);
    }

    public String getFechaAsignacion() {
        return FechaAsignacion.get();
    }

    public SimpleStringProperty fechaAsignacionProperty() {
        return FechaAsignacion;
    }

    public void setFechaAsignacion(String fechaAsignacion) {
        this.FechaAsignacion.set(fechaAsignacion);
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

    public int getCarnetEstudiante(Estudiante estudiante) {
        return estudiante.getCarnet();
    }

    public int getIdCurso(Curso curso) {
        return curso.getIdCurso();
    }

    public int getIdPago(Pago pago) {
        return pago.getIdPago();
    }
}
