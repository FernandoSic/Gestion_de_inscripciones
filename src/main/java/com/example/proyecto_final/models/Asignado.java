package com.example.proyecto_final.models;

public class Asignado {
    private int carnet;
    private String nombre;
    private String apellido;
    private String correo;
    private String curso;
    private String fechaInscripcion;
    private String solvente;

    public Asignado(int carnet, String nombre,String apellido, String correo, String curso, String fechaInscripcion, String solvente) {
        this.carnet = carnet;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.curso = curso;
        this.fechaInscripcion = fechaInscripcion;
        this.solvente = solvente;
    }

    // Getters and setters
    public int getCarnet() { return carnet; }
    public void setCarnet(int carnet) { this.carnet = carnet; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) {this.apellido=apellido;}
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }
    public String getFechaInscripcion() { return fechaInscripcion; }
    public void setFechaInscripcion(String fechaInscripcion) { this.fechaInscripcion = fechaInscripcion; }
    public String getSolvente() { return solvente; }
    public void setSolvente(String solvente) { this.solvente = solvente; }
}