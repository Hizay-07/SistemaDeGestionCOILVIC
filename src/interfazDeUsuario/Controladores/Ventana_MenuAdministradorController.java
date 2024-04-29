package interfazDeUsuario.Controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class Ventana_MenuAdministradorController implements Initializable {

    @FXML
    private AnchorPane anchor_PanelPrincipal;
    @FXML
    private ImageView img_ImagenDeFondo;
    @FXML
    private Pane Pane_Superior;
    @FXML
    private ImageView img_Logo;
    @FXML
    private Label lbl_Administrador;
    @FXML
    private Button btn_RegistrarProfesor;
    @FXML
    private Button btn_ConaultarColaboracionesEnCurso;
    @FXML
    private Button btn_ConsultarProfesores;
    @FXML
    private Button btn_ConsultarColaboracionesPendientes;
    @FXML
    private Button btn_VisualizarColaboraciones;
    @FXML
    private Button btn_Salir;
    @FXML
    private Label lbl_Acción;
    @FXML
    private Label lbl_Bienvenido;
    @FXML
    private Separator sprt_Divisor;
    @FXML
    private Button btn_VisualizarPropuestasDeColaboracion;
    @FXML
    private Button btn_ConsultarRepresentanteInstitucional;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO: Implementar la inicialización del controlador
    }
}
