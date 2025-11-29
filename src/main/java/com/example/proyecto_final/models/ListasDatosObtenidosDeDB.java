package com.example.proyecto_final.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ListasDatosObtenidosDeDB {

    public static ObservableList<Curso> listaCursos = FXCollections.observableArrayList();
    public static ObservableList<Estudiante> listaEstudiantes = FXCollections.observableArrayList();
    public static ObservableList<Pago> listaPagos = FXCollections.observableArrayList();
    public static ObservableList<Asignacion> listaAsignaciones = FXCollections.observableArrayList();
    public static ObservableList<String> listaNombresCursos = FXCollections.observableArrayList();

    static {
        Conexion conexion = new Conexion();

        listaCursos = conexion.obtenerCursos();
        listaEstudiantes = conexion.obtenerEstudiantes();
        listaPagos = conexion.obtenerPagos();
        listaAsignaciones = conexion.obtenerAsignaciones();

        for (Curso curso : listaCursos) {
            listaNombresCursos.add(curso.getNombreCurso());
        }

        conexion.cerrarConexion();
    }
}