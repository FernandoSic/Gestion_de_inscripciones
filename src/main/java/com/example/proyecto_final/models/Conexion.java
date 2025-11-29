package com.example.proyecto_final.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Conexion {
    public Connection conexion = null;
    private String url = "jdbc:sqlite:control_asingaciones.db";

    public Conexion() {
        if (conexion == null) {
            try {
                Class.forName("org.sqlite.JDBC");
                conexion = DriverManager.getConnection(this.url);
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Connection getConexion() {
        return this.conexion;
    }

    public void cerrarConexion() {
        try {
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Curso> obtenerCursos() {
        ObservableList<Curso> listaCursos = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Curso";
        try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                listaCursos.add(new Curso(rs.getInt("idcurso"), rs.getString("nombrecurso")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaCursos;
    }

    public ObservableList<Estudiante> obtenerEstudiantes() {
        ObservableList<Estudiante> listaEstudiantes = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Estudiante";
        try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                listaEstudiantes.add(new Estudiante(rs.getInt("carnet"), rs.getString("nombres"), rs.getString("apellidos"), rs.getString("correo")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaEstudiantes;
    }

    public ObservableList<Pago> obtenerPagos() {
        ObservableList<Pago> listaPagos = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Pago";
        try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                listaPagos.add(new Pago(rs.getInt("idpago"), rs.getString("estado")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaPagos;
    }

    public ObservableList<Asignacion> obtenerAsignaciones() {
        ObservableList<Asignacion> listaAsignaciones = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Asignacion";
        try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                listaAsignaciones.add(new Asignacion(rs.getInt("idasignacion"), rs.getInt("carnet"), rs.getInt("idcurso"), rs.getString("fechaasignacion"), rs.getInt("idpago")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaAsignaciones;
    }

    public void insertarEstudiante(Estudiante estudiante) {
        String sql = "INSERT INTO Estudiante (carnet, nombres, apellidos, correo) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, estudiante.getCarnet());
            pstmt.setString(2, estudiante.getNombres());
            pstmt.setString(3, estudiante.getApellidos());
            pstmt.setString(4, estudiante.getCorreo());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertarPago(Pago pago) {
        String sql = "INSERT INTO Pago (estado) VALUES (?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, pago.getEstado());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertarAsignacion(Asignacion asignacion) {
        String sql = "INSERT INTO Asignacion (carnet, idcurso, fechaasignacion, idpago) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, asignacion.getCarnet());
            pstmt.setInt(2, asignacion.getIdCurso());
            pstmt.setString(3, asignacion.getFechaAsignacion());
            pstmt.setInt(4, asignacion.getIdPago());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
