package interfazDeUsuario.Controladores;

import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.DAOImplementacion.DAOUsuarioImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOProfesorImplementacion;
import logicaDeNegocio.clases.Profesor;
import logicaDeNegocio.clases.ProfesorSingleton;
import logicaDeNegocio.clases.UsuarioSingleton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

public class Ventana_MenuAdministradorController implements Initializable{
    
    private static final Logger LOG=Logger.getLogger(ventana_InicioDeSesionController.class);
    private Stage escenario;
    @FXML
    private AnchorPane anchor_PanelPrincipal;
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
    private Button btn_VisualizarPropuestasDeColaboracion;
    @FXML
    private Button btn_ConsultarRepresentanteInstitucional;
    @FXML
    private Button btn_CreacionDeUsuario;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn_Salir.setOnAction(Event ->{ 
            salirDelMenuPrincipal();
        });   
        
        btn_RegistrarProfesor.setOnAction(Event ->{
            registrarProfesor();
        });
        
        btn_CreacionDeUsuario.setOnAction(Event ->{
            registrarUsuario();
        });
        btn_VisualizarPropuestasDeColaboracion.setOnAction(Event ->{
            visualizarPropuestasColaboracion();            
        });                
        mostrarDetallesUsuario();
    }
    
    public void mostrarDetallesUsuario(){
        UsuarioSingleton usuario = UsuarioSingleton.getInstancia();
        lbl_Administrador.setText(usuario.getNombreUsuario());
    }        
    
    public void registrarProfesor(){
        String ruta = "/interfazDeUsuario/Ventana_RegistroDeProfesor.fxml";
        desplegarVentana(ruta);
    }
    
    public void registrarUsuario(){
        String ruta = "/interfazDeUsuario/Ventana_CreacionDeUsuario.fxml";
        desplegarVentana(ruta);
    }
    
    public void desplegarVentana(String ruta){
        String rutaVentanaFXML = ruta;
        try{
            Parent root=FXMLLoader.load(getClass().getResource(rutaVentanaFXML));
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
        escenario = (Stage) anchor_PanelPrincipal.getScene().getWindow();
        escenario.close();
    }
    
    public void salirDelMenuPrincipal(){
         String rutaVentanaFXML = null;
        try{
            rutaVentanaFXML = "/interfazDeUsuario/Ventana_InicioDeSesion.fxml";
            Parent root=FXMLLoader.load(getClass().getResource(rutaVentanaFXML));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }catch(IOException excepcion){
            LOG.error(excepcion);
        }
        
        UsuarioSingleton.resetSingleton();
        cerrarVentana();
    }
    
    public void visualizarPropuestasColaboracion(){
        String ruta = "/interfazDeUsuario/Ventana_PropuestasDeColaboracion.fxml";
        desplegarVentana(ruta);        
    }
}
