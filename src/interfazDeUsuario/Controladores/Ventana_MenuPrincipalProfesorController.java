package interfazDeUsuario.Controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class Ventana_MenuPrincipalProfesorController implements Initializable {

    @FXML
    private Button btn_ColaboracionActiva;
    @FXML
    private Button btn_ColaborarConProfesor;
    @FXML
    private Button btn_ProponerColaboracion;
    @FXML
    private Button btn_VerPeticionesDeColaboracion;
    @FXML
    private Button btn_VisualizarColaboraciones;
    @FXML
    private Button btn_Salir;
    @FXML
    private Label lbl_Accion;
    @FXML
    private Label lbl_Bienvenido;
    @FXML
    private Label lbl_NombreProfesor;
    @FXML
    private Separator sprt_Divisor;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbl_Accion.setFont(Font.font("System", 20));
        lbl_Bienvenido.setFont(Font.font("System", 22));
        lbl_NombreProfesor.setFont(Font.font("System", 23));
        btn_ColaboracionActiva.setOnAction(event -> {
            // Aquí puedes implementar la lógica para la opción de Colaboración Activa
        });

        btn_ColaborarConProfesor.setOnAction(event -> {
            // Aquí puedes implementar la lógica para la opción de Colaborar con Profesor
        });

        btn_ProponerColaboracion.setOnAction(event -> {
            // Aquí puedes implementar la lógica para la opción de Proponer Colaboración
        });

        btn_VerPeticionesDeColaboracion.setOnAction(event -> {
            // Aquí puedes implementar la lógica para la opción de Ver Peticiones de Colaboración
        });

        btn_VisualizarColaboraciones.setOnAction(event -> {
            // Aquí puedes implementar la lógica para la opción de Visualizar Colaboraciones
        });

        btn_Salir.setOnAction(event -> {
            // Aquí puedes implementar la lógica para la opción de Salir
        });
    }

    public void setNombreProfesor(String nombre) {
        lbl_NombreProfesor.setText(nombre);
    }

    public void setAccion(String accion) {
        lbl_Accion.setText(accion);
    }
}
