package interfazDeUsuario.Controladores;

import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.event.ActionEvent;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.enums.EnumUsuario;
import logicaDeNegocio.DAOImplementacion.DAOUsuarioImplementacion;
import logicaDeNegocio.clases.UsuarioSingleton;
import interfazDeUsuario.Alertas.Alertas;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.log4j.Logger;


public class ventana_InicioDeSesionController implements Initializable {
    
    private static final Logger LOG=Logger.getLogger(ventana_InicioDeSesionController.class);
    
    private Stage escenario;
    
    @FXML
    private AnchorPane anchor_Ventana;
    @FXML
    private TextField txtf_Usuario;
    @FXML
    private PasswordField pwdf_Contrasenia;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void cerrarVentana(){
        escenario = (Stage) anchor_Ventana.getScene().getWindow();
        escenario.close();
    }
    
    public void iniciarSesion(ActionEvent event){
        Usuario logger = new Usuario();
        Usuario usuarioAIngresar = new Usuario();
        
        try{
            logger.setTipoDeUsuario(EnumUsuario.Logger.toString());
            usuarioAIngresar.setNombreUsuario(txtf_Usuario.getText());
            usuarioAIngresar.setContrasenia(pwdf_Contrasenia.getText());
            
            DAOUsuarioImplementacion DAOUsuario = new DAOUsuarioImplementacion();
            boolean validacionCredencial = DAOUsuario.validarCredenciales(usuarioAIngresar, logger);

            if(validacionCredencial){
                usuarioAIngresar.setTipoDeUsuario(DAOUsuario.obtenerTipoDeUsuario(usuarioAIngresar));
                desplegarVentanaCorrespondiente(usuarioAIngresar);
            }else{
                Alertas.mostrarMensajeUsuarioNoEncontrado();
            }            
        }catch(IllegalArgumentException excepcion){
            LOG.error(excepcion);
            Alertas.mostrarMensajeDatosInvalidos();
        }
    }
    
    public void desplegarVentanaCorrespondiente(Usuario usuario){
        UsuarioSingleton usuarioIngresado = UsuarioSingleton.getInstancia(usuario);
        try{
            String rutaVentanaFXML=null;
            if(usuarioIngresado.getTipoDeUsuario().equals(EnumUsuario.Administrativo.toString())){
                rutaVentanaFXML = "/interfazDeUsuario/Ventana_ModificarActividad.fxml";
            }else if(usuarioIngresado.getTipoDeUsuario().equals(EnumUsuario.Profesor.toString())){
                rutaVentanaFXML = "/interfazDeUsuario/Ventana_ColaboracionActiva.fxml";
            }
            
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
    
    
}
