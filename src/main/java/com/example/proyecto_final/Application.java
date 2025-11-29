package com.example.proyecto_final;

import com.example.proyecto_final.controllers.InscripcionViewController;
import com.example.proyecto_final.controllers.PrincipalController;
import com.example.proyecto_final.controllers.VerificacionViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    Stage PrincipalStage;
    InscripcionViewController inscripcionViewController;
    VerificacionViewController verificacionViewController;
    PrincipalController principalController;

    @Override
    public void start(Stage stage) throws IOException {
        this.PrincipalStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("principal.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Ventana Principal");
        stage.setScene(scene);
        principalController = fxmlLoader.getController();
        principalController.setMain(this);
        stage.show();
    }

    public void cargarInscripcion() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("inscripcion-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        Stage inscripcionStage = new Stage();
        inscripcionStage.setTitle("Asignación de cursos");
        inscripcionStage.setScene(scene);
        inscripcionStage.initOwner(PrincipalStage);
        inscripcionViewController = fxmlLoader.getController();
        inscripcionViewController.setMain(this);
        PrincipalStage.close();
        inscripcionStage.show();

        //al cerrar la ventana de inscripcion se abre la ventana principal
        inscripcionStage.setOnCloseRequest(event -> {
            try {
                FXMLLoader fxmlLoader2 = new FXMLLoader(Application.class.getResource("principal.fxml"));
                Scene scene2 = new Scene(fxmlLoader2.load(), 600, 400);
                PrincipalStage.setTitle("Ventana Principal");
                PrincipalStage.setScene(scene2);
                principalController = fxmlLoader2.getController();
                principalController.setMain(this);
                PrincipalStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    public void cargarVerificacion() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("verificacion-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 750, 600);
        Stage verificacionStage = new Stage();
        verificacionStage.setTitle("Verificación de cursos");
        verificacionStage.setScene(scene);
        verificacionStage.initOwner(PrincipalStage);
        verificacionViewController = fxmlLoader.getController();
        verificacionViewController.setMain(this);
        PrincipalStage.close();
        verificacionStage.show();
        //al cerrar la ventana de verificacion se abre la ventana principal
        verificacionStage.setOnCloseRequest(event -> {
            try {
                FXMLLoader fxmlLoader2 = new FXMLLoader(Application.class.getResource("principal.fxml"));
                Scene scene2 = new Scene(fxmlLoader2.load(), 600, 400);
                PrincipalStage.setTitle("Ventana Principal");
                PrincipalStage.setScene(scene2);
                principalController = fxmlLoader2.getController();
                principalController.setMain(this);
                PrincipalStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    public static void main(String[] args) {
        launch();
    }

}

