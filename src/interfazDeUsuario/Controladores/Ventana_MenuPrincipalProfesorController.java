package interfazDeUsuario.Controladores;

import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.event.ActionEvent;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.enums.EnumTipoDeUsuario;
import logicaDeNegocio.DAOImplementacion.DAOUsuarioImplementacion;
import logicaDeNegocio.clases.UsuarioSingleton;
import interfazDeUsuario.Alertas.Alertas;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.log4j.Logger;


public class Ventana_MenuPrincipalProfesorController implements Initializable{
    
    private static final Logger LOG=Logger.getLogger(ventana_InicioDeSesionController.class);
    private Stage escenario;
    @FXML 
    private AnchorPane anchor_Ventana;
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn_ColaboracionActiva.setOnAction(Event ->{
            desplegarVentanaColaboracionActiva();
        });
        
        btn_ProponerColaboracion.setOnAction(Event ->{
            desplegarVentanaProponerColaboracion();
        });
        
        btn_ColaborarConProfesor.setOnAction(Event ->{
            
        });
        
    }    
    
    public void cerrarVentana(){
        escenario = (Stage) anchor_Ventana.getScene().getWindow();
        escenario.close();
    }
     
    public void desplegarVentanaColaboracionActiva(){
        String rutaVentanaFXML = null;
        try{
            rutaVentanaFXML = "/interfazDeUsuario/Ventana_ColaboracionActiva.fxml";
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
    
    public void desplegarVentanaProponerColaboracion(){
        String rutaVentanaFXML = null;
        try{
            rutaVentanaFXML = "/interfazDeUsuario/Ventana_ProponerColaboracion.fxml";
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
    
    public void desplegarVentanaOfertasDeColaboracion(){
        String rutaVentanaFXML = null;
        try{
            rutaVentanaFXML = "/interfazDeUsuario/Ventana_OfertaDeColaboraciones.fxml";
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
    
    //TO DO realizar la ventana de ver peticion de colaboracion
     public void desplegarVentanaPeticionDeColaboracion(){
       
    }
}
