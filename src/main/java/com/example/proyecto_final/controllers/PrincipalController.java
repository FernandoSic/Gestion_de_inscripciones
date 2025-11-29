package com.example.proyecto_final.controllers;

import com.example.proyecto_final.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class PrincipalController {

    @FXML
    private Button bootonVentanaAsignar;

    @FXML
    private Button bottonVentanaVerificar;

    Application app;
    public void setMain(Application main){ //Asignarlo como ventana principal
        this.app = main;
    }

    @FXML
    void abrirVentanaAsignacion(ActionEvent event) throws IOException {
        app.cargarInscripcion();

    }

    @FXML
    void abrirVentanaVerificacion(ActionEvent event) throws IOException {
        app.cargarVerificacion();
    }
}