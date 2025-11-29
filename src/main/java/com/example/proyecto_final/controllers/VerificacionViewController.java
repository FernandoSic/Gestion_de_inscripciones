package com.example.proyecto_final.controllers;
import com.example.proyecto_final.Application;
import com.example.proyecto_final.models.ListasDatosObtenidosDeDB;
import com.example.proyecto_final.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class VerificacionViewController {

    @FXML
    private PieChart GraficaSolventes;

    @FXML
    private TableColumn<Asignado, Integer> columnaCarnet;
    @FXML
    private TableColumn<Asignado, String> columnaApellido;


    @FXML
    private TableColumn<Asignado, String> columnaCorreo;
    @FXML
    private TableColumn<Asignado, String> columnaCurso;

    @FXML
    private TableColumn<Asignado, String> columnaFechaIscripcion;

    @FXML
    private TableColumn<Asignado, String> columnaNombre;

    @FXML
    private TableColumn<Asignado, String> columnaSolvente;

    @FXML
    private ComboBox<String> comboBoxCurso;

    @FXML
    private DatePicker datePickerFecha;

    @FXML
    private TableView<Asignado> tablaAsignados;
    ObservableList<Asignado> listaAsignadosTabla = FXCollections.observableArrayList();

    Application app;

    public void setMain(Application main) {
        this.app = main;
    }

    @FXML
    public void initialize() {
        columnaCarnet.setCellValueFactory(new PropertyValueFactory<>("carnet"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        columnaCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        columnaFechaIscripcion.setCellValueFactory(new PropertyValueFactory<>("fechaInscripcion"));
        columnaSolvente.setCellValueFactory(new PropertyValueFactory<>("solvente"));
        columnaCurso.setCellValueFactory(new PropertyValueFactory<>("curso"));


        comboBoxCurso.setItems(ListasDatosObtenidosDeDB.listaNombresCursos);

        comboBoxCurso.setOnAction(e -> aplicarFiltros());
        datePickerFecha.setOnAction(e -> aplicarFiltros());

        GraficaSolventes.setData(FXCollections.observableArrayList(
                new PieChart.Data("Solventes", 0),
                new PieChart.Data("Pendientes", 0)
        ));

        tablaAsignados.setItems(listaAsignadosTabla);
        tablaAsignados.getItems().addListener((ListChangeListener<Asignado>) c -> actualizarGrafica());
    }

    private void aplicarFiltros() {
        String cursoSeleccionado = comboBoxCurso.getValue();
        LocalDate fechaSeleccionada = datePickerFecha.getValue();

        ObservableList<Asignado> listaFiltrada = FXCollections.observableArrayList();
        for (Asignacion asignacion : ListasDatosObtenidosDeDB.listaAsignaciones) {
            Curso curso = ListasDatosObtenidosDeDB.listaCursos.stream()
                    .filter(c -> c.getIdCurso() == asignacion.getIdCurso())
                    .findFirst()
                    .orElse(null);

            if (curso != null && (cursoSeleccionado == null || curso.getNombreCurso().equals(cursoSeleccionado))) {
                Estudiante estudiante = ListasDatosObtenidosDeDB.listaEstudiantes.stream()
                        .filter(e -> e.getCarnet() == asignacion.getCarnet())
                        .findFirst()
                        .orElse(null);

                Pago pago = ListasDatosObtenidosDeDB.listaPagos.stream()
                        .filter(p -> p.getIdPago() == asignacion.getIdPago())
                        .findFirst()
                        .orElse(null);

                if (estudiante != null && pago != null) {
                    Asignado asignado = new Asignado(
                            estudiante.getCarnet(),
                            estudiante.getNombres(),
                            estudiante.getApellidos(),
                            estudiante.getCorreo(),
                            curso.getNombreCurso(),
                            asignacion.getFechaAsignacion(),
                            pago.getEstado()
                    );

                    if (fechaSeleccionada == null || LocalDate.parse(asignado.getFechaInscripcion()).equals(fechaSeleccionada)) {
                        listaFiltrada.add(asignado);
                    }
                }
            }
        }
        tablaAsignados.setItems(listaFiltrada);
        actualizarGrafica();
    }

    private void actualizarGrafica() {
        int solventes = 0;
        int pendientes = 0;
        for (Asignado asignado : tablaAsignados.getItems()) {
            if (asignado.getSolvente().equals("solvente")) {
                solventes++;
            } else {
                pendientes++;
            }
        }
        GraficaSolventes.getData().get(0).setPieValue(solventes);
        GraficaSolventes.getData().get(1).setPieValue(pendientes);
    }
}