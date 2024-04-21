package interfazDeUsuario.Controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.enums.EnumUsuario;
import logicaDeNegocio.DAOImplementacion.DAOUsuarioImplementacion;
import interfazDeUsuario.Alertas.Alertas;
import javafx.fxml.FXML;

public class FXMLInterfazInicioDeSesionController implements Initializable {
    
    @FXML
    private TextField txtf_Usuario;
    @FXML
    private PasswordField pwdf_Contrasenia;
    @FXML
    private Button btn_Ingresar;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void iniciarSesion(ActionEvent event){
        Usuario logger = new Usuario();
        logger.setTipoDeUsuario(EnumUsuario.Logger.toString());
        Usuario usuarioAIngresar = new Usuario();
        usuarioAIngresar.setNombreUsuario(txtf_Usuario.getText());
        usuarioAIngresar.setContrasenia(pwdf_Contrasenia.getText());
        DAOUsuarioImplementacion DAOUsuario = new DAOUsuarioImplementacion();
        
        
        boolean validacionCredencial = DAOUsuario.validarCredenciales(usuarioAIngresar, logger);
        
        if(validacionCredencial==true){
            usuarioAIngresar.setTipoDeUsuario(DAOUsuario.obtenerTipoDeUsuario(usuarioAIngresar));
        }else if(validacionCredencial==false){
            Alertas.mostrarMensajeUsuarioNoEncontrado();
        }
    }
    
}
