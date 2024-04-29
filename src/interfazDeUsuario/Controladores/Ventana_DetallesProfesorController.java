package interfazDeUsuario.Controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import interfazDeUsuario.Alertas.Alertas;
import java.io.IOException;
import logicaDeNegocio.DAOImplementacion.DAOProfesorImplementacion;
import logicaDeNegocio.clases.Profesor;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logicaDeNegocio.clases.ProfesorSingleton;
import org.apache.log4j.Logger;

public class Ventana_DetallesProfesorController implements Initializable {
    
    private static final Logger LOG=Logger.getLogger(Ventana_DetallesProfesorController.class);
    
    private Stage escenario;
    
    @FXML
    private AnchorPane anchor_Ventana;

    @FXML
    private TextField txfd_Nombre;

    @FXML
    private TextField txfd_ApellidoPaterno;

    @FXML
    private TextField txfd_ApellidoMaterno;

    @FXML
    private TextField txfd_Correo;

    @FXML
    private Button btn_ActualizarPerfil;

    @FXML
    private Button btn_Cancelar;

    @FXML
    private Label lbl_Nombre;

    @FXML
    private Label lbl_ApellidoPaterno;

    @FXML
    private Label lbl_ApellidoMaterno;

    @FXML
    private Label lbl_Correo;

    private DAOProfesorImplementacion daoProfesor;

    private int idProfesor;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        daoProfesor = new DAOProfesorImplementacion();

        
        btn_ActualizarPerfil.setOnAction(event -> {
            abrirSiguienteVentana();
        });

        btn_Cancelar.setOnAction(event -> {
           cerrarVentana();
        });
    }

    public void initData(int id) {
        idProfesor = id;
        cargarDetallesProfesor();
    }
    
    public void abrirSiguienteVentana(){
        String rutaVentana = "/interfazDeUsuario/Ventana_ActualizarPerfilProfesor.fxml";
        try{
             Parent root = FXMLLoader.load(getClass().getResource(rutaVentana));
             Scene scene = new Scene(root);
             Stage stage = new Stage();
             stage.setScene(scene);
             stage.show();
        }catch(IOException excepcion){
            LOG.error(excepcion);
        }
        cerrarVentana();
    }
    
    public void cerrarVentana(){
        escenario = (Stage) anchor_Ventana.getScene().getWindow();
        escenario.close();
    }

    private void cargarDetallesProfesor() {
        ProfesorSingleton profesor = ProfesorSingleton.getInstancia();
        txfd_Nombre.setText(profesor.getNombre());
        txfd_ApellidoPaterno.setText(profesor.getApellidoPaterno());
        txfd_ApellidoMaterno.setText(profesor.getApellidoMaterno());
        txfd_Correo.setText(profesor.getCorreo());
    }
}
