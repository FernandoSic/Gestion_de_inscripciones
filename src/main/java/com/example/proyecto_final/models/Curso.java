package com.example.proyecto_final.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Curso {
    SimpleIntegerProperty idCurso;
    SimpleStringProperty NombreCurso;

    public Curso(Integer idCurso, String NombreCurso) {
        this.idCurso = new SimpleIntegerProperty(idCurso);
        this.NombreCurso = new SimpleStringProperty(NombreCurso);
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

    public String getNombreCurso() {
        return NombreCurso.get();
    }

    public SimpleStringProperty nombreCursoProperty() {
        return NombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.NombreCurso.set(nombreCurso);
    }
}
