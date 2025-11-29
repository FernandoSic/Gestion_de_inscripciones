package com.example.proyecto_final.controllers;

import com.example.proyecto_final.Application;
import com.example.proyecto_final.models.ListasDatosObtenidosDeDB;
import com.example.proyecto_final.models.Asignacion;
import com.example.proyecto_final.models.Curso;
import com.example.proyecto_final.models.Estudiante;
import com.example.proyecto_final.models.Pago;
import com.example.proyecto_final.models.Conexion;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class InscripcionViewController {

    @FXML
    private CheckBox ChceckBoxPagarAhora;

    @FXML
    private Button bottonAsignarCursos;

    @FXML
    private Button bottonValidarDatos;

    @FXML
    private ListView<String> listViewCursosDisponibles;

    @FXML
    private ListView<String> listViewCursosSeleccionados;

    @FXML
    private TextField textFieldAnoExpiraTarjeta;

    @FXML
    private TextField textFieldApellidos;

    @FXML
    private TextField textFieldCarnet;

    @FXML
    private TextField textFieldCodigoSeguridadTarjeta;

    @FXML
    private TextField textFieldCorreo;

    @FXML
    private TextField textFieldMesExpiraTarjeta;

    @FXML
    private TextField textFieldNombreTarjeta;

    @FXML
    private TextField textFieldNombres;

    @FXML
    private TextField textFieldNumeroTarjeta;

    private Conexion conexion = new Conexion();

    @FXML
    void asignarCursos(ActionEvent event) {
        // Crear estudiante
        crearEstudiante();
        // Crear pago
        crearPago();
        // Crear asignacion
        crearAsignacion();
        // Mostrar ventana emergente con mensaje de exito
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Exito");
        alert.setHeaderText("Cursos asignados correctamente");
        String pagoEstado = ChceckBoxPagarAhora.isSelected() ? "Pago realizado" : "Pago no realizado";
        String cursos = String.join(", ", listaNombreCursosSeleccionados);
        String mensaje = String.format("Estudiante: %s %s\nCorreo: %s\nFecha: %s\n%s\nCursos: %s",
                textFieldNombres.getText(), textFieldApellidos.getText(), textFieldCorreo.getText(),
                LocalDate.now().toString(), pagoEstado, cursos);
        alert.setContentText(mensaje);
        System.out.println(mensaje);
        alert.show();
        bottonAsignarCursos.setDisable(true);
    }

    @FXML
    void crearEstudiante() {
        int carnet = Integer.parseInt(textFieldCarnet.getText());
        String nombre = textFieldNombres.getText();
        String apellidos = textFieldApellidos.getText();
        String correo = textFieldCorreo.getText();
        Estudiante estudiante = new Estudiante(carnet, nombre, apellidos, correo);
        conexion.insertarEstudiante(estudiante);
        ListasDatosObtenidosDeDB.listaEstudiantes.add(estudiante);
    }

    @FXML
    void crearPago() {
        String estado = ChceckBoxPagarAhora.isSelected() ? "solvente" : "pendiente";
        Pago pago = new Pago(0, estado); // El id se autoincrementa
        conexion.insertarPago(pago);
        ListasDatosObtenidosDeDB.listaPagos.add(pago);
    }

    @FXML
    void crearAsignacion() {
        int carnet = Integer.parseInt(textFieldCarnet.getText());
        for (String nombreCurso : listaNombreCursosSeleccionados) {
            Curso curso = ListasDatosObtenidosDeDB.listaCursos.stream()
                    .filter(c -> c.getNombreCurso().equals(nombreCurso))
                    .findFirst()
                    .orElse(null);
            if (curso != null) {
                int idCurso = curso.getIdCurso();
                int idPago = ListasDatosObtenidosDeDB.listaPagos.size();
                String fecha = LocalDate.now().toString();
                Asignacion asignacion = new Asignacion(0, carnet, idCurso, fecha, idPago); // The id is auto-incremented
                conexion.insertarAsignacion(asignacion);
                ListasDatosObtenidosDeDB.listaAsignaciones.add(asignacion);
            }
        }
    }

    @FXML
    void validarDatosFormulario(ActionEvent event) {
        if (ChceckBoxPagarAhora.isSelected()) {
            verificarDatosConPago();
        } else {
            verificarDatosSinPago();
        }
    }

    ObservableList<String> listaNombreCursosSeleccionados = FXCollections.observableArrayList();

    Application app;
    public void setMain(Application main) { // Asignarlo como ventana principal
        this.app = main;
    }

    @FXML
    public void initialize() {
        ChceckBoxPagarAhora.setOnAction(event -> {
            boolean isSelected = ChceckBoxPagarAhora.isSelected();
            textFieldNombreTarjeta.setDisable(!isSelected);
            textFieldNumeroTarjeta.setDisable(!isSelected);
            textFieldCodigoSeguridadTarjeta.setDisable(!isSelected);
            textFieldMesExpiraTarjeta.setDisable(!isSelected);
            textFieldAnoExpiraTarjeta.setDisable(!isSelected);
        });

        listViewCursosDisponibles.setItems(ListasDatosObtenidosDeDB.listaNombresCursos);
        listViewCursosSeleccionados.setItems(listaNombreCursosSeleccionados);

        listViewCursosDisponibles.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listViewCursosSeleccionados.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        listViewCursosDisponibles.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !listaNombreCursosSeleccionados.contains(newValue)) {
                listaNombreCursosSeleccionados.add(newValue);
                listViewCursosDisponibles.getSelectionModel().clearSelection();
            }
        });

        listViewCursosSeleccionados.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                listaNombreCursosSeleccionados.remove(newValue);
            }
        });
    }

    // Validaciones
    Boolean nombreValido = false;
    Boolean apellidosValido = false;
    Boolean carnetValido = false;
    Boolean correoValido = false;
    Boolean cursosValidos = false;
    Boolean nombreTarjetaValido = false;
    Boolean numeroTarjetaValido = false;
    Boolean codigoSeguridadValido = false;
    Boolean mesExpiracionValido = false;
    Boolean anoExpiracionValido = false;

    @FXML
    public void validarNombre() {
        String nombre = textFieldNombres.getText();
        nombreValido = nombre.matches("[a-zA-Z]+");
    }

    @FXML
    public void validarApellidos() {
        String apellidos = textFieldApellidos.getText();
        apellidosValido = apellidos.matches("[a-zA-Z]+");
    }

    @FXML
    public void validarCarnet() {
        String carnet = textFieldCarnet.getText();
        carnetValido = carnet.matches("[0-9]+");
    }

    @FXML
    public void validarCorreo() {
        String correo = textFieldCorreo.getText();
        correoValido = correo.contains("@") && correo.contains(".");
    }

    @FXML
    public void validarCursos() {
        cursosValidos = !listViewCursosSeleccionados.getItems().isEmpty();
    }

    @FXML
    public void validarNombreTarjeta() {
        String nombreTarjeta = textFieldNombreTarjeta.getText();
        nombreTarjetaValido = nombreTarjeta.matches("[a-zA-Z]+");
    }

    @FXML
    public void validarNumeroTarjeta() {
        String numeroTarjeta = textFieldNumeroTarjeta.getText();
        numeroTarjetaValido = numeroTarjeta.matches("[0-9]+");
    }

    @FXML
    public void validarCodigoSeguridad() {
        String codigoSeguridad = textFieldCodigoSeguridadTarjeta.getText();
        codigoSeguridadValido = codigoSeguridad.matches("[0-9]{3}");
    }

    @FXML
    public void validarMesExpiracion() {
        String mesExpiracion = textFieldMesExpiraTarjeta.getText();
        if (mesExpiracion.matches("[0-9]{2}")) {
            int mes = Integer.parseInt(mesExpiracion);
            mesExpiracionValido = mes >= 1 && mes <= 12;
        } else {
            mesExpiracionValido = false;
        }
    }

    @FXML
    public void validarAnoExpiracion() {
        String anoExpiracion = textFieldAnoExpiraTarjeta.getText();
        if (anoExpiracion.matches("[0-9]{4}")) {
            int ano = Integer.parseInt(anoExpiracion);
            anoExpiracionValido = ano >= 2021;
        } else {
            anoExpiracionValido = false;
        }
    }

    @FXML
    public void verificarDatosSinPago() {
        validarNombre();
        validarApellidos();
        validarCarnet();
        validarCorreo();
        validarCursos();
        if (nombreValido && apellidosValido && carnetValido && correoValido && cursosValidos) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exito");
            alert.setHeaderText("Datos validados correctamente");
            alert.setContentText("Puede asignar los cursos");
            alert.show();
            bloquearDatos();
            bottonAsignarCursos.setDisable(false);
        } else {
            bottonAsignarCursos.setDisable(true);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al validar datos");
            alert.setContentText("Verifique los datos ingresados");
            System.out.println("Nombre: " + nombreValido + "\n" +
                    "Apellidos: " + apellidosValido + "\n" +
                    "Carnet: " + carnetValido + "\n" +
                    "Correo: " + correoValido + "\n" +
                    "Cursos: " + cursosValidos + "\n");
            alert.show();
        }
    }

    @FXML
    public void verificarDatosConPago() {
        validarNombre();
        validarApellidos();
        validarCarnet();
        validarCorreo();
        validarCursos();
        validarNombreTarjeta();
        validarNumeroTarjeta();
        validarCodigoSeguridad();
        validarMesExpiracion();
        validarAnoExpiracion();
        if (nombreValido && apellidosValido && carnetValido && correoValido && cursosValidos && nombreTarjetaValido && numeroTarjetaValido && codigoSeguridadValido && mesExpiracionValido && anoExpiracionValido) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exito");
            alert.setHeaderText("Datos validados correctamente");
            alert.setContentText("Puede asignar los cursos");
            alert.show();
            bottonAsignarCursos.setDisable(false);
            bloquearDatos();
        } else {
            bottonAsignarCursos.setDisable(true);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al validar datos");
            alert.setContentText("Verifique los datos ingresados");
            System.out.println("Nombre: " + nombreValido + "\n" +
                    "Apellidos: " + apellidosValido + "\n" +
                    "Carnet: " + carnetValido + "\n" +
                    "Correo: " + correoValido + "\n" +
                    "Cursos: " + cursosValidos + "\n" +
                    "Nombre Tarjeta: " + nombreTarjetaValido + "\n" +
                    "Numero Tarjeta: " + numeroTarjetaValido + "\n" +
                    "Codigo Seguridad: " + codigoSeguridadValido + "\n" +
                    "Mes Expiracion: " + mesExpiracionValido + "\n" +
                    "Año Expiracion: " + anoExpiracionValido + "\n");
            alert.show();
        }
    }

    @FXML
    public void bloquearDatos() {
        textFieldNombres.setDisable(true);
        textFieldApellidos.setDisable(true);
        textFieldCarnet.setDisable(true);
        textFieldCorreo.setDisable(true);
        listViewCursosDisponibles.setDisable(true);
        listViewCursosSeleccionados.setDisable(true);
        textFieldNombreTarjeta.setDisable(true);
        textFieldNumeroTarjeta.setDisable(true);
        textFieldCodigoSeguridadTarjeta.setDisable(true);
        textFieldMesExpiraTarjeta.setDisable(true);
        textFieldAnoExpiraTarjeta.setDisable(true);
        bottonValidarDatos.setDisable(true);
        ChceckBoxPagarAhora.setVisible(false);
    }
}
