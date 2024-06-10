package interfazDeUsuario.Controladores;

import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.enums.EnumTipoDeUsuario;
import logicaDeNegocio.DAOImplementacion.DAOUsuarioImplementacion;
import logicaDeNegocio.clases.UsuarioSingleton;
import interfazDeUsuario.Alertas.Alertas;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logicaDeNegocio.DAOImplementacion.DAOProfesorImplementacion;
import logicaDeNegocio.clases.Profesor;
import logicaDeNegocio.enums.EnumProfesor;
import org.apache.log4j.Logger;

public class ventana_InicioDeSesionControlador implements Initializable {
    
    private static final Logger LOG=Logger.getLogger(ventana_InicioDeSesionControlador.class);
    private Stage escenario;
    @FXML
    private AnchorPane anchor_Ventana;
    @FXML
    private TextField txtf_Usuario;
    @FXML
    private PasswordField pwdf_Contrasenia;
    @FXML
    private Label lbl_ErrorContrasena;
    @FXML
    private Label lbl_ErrorUsuario;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ocultarLabelErrores();
    }    
    
    private void ocultarLabelErrores(){
        lbl_ErrorUsuario.setVisible(false);
        lbl_ErrorContrasena.setVisible(false);
    }
    
    private void cerrarVentana(){
        escenario = (Stage) anchor_Ventana.getScene().getWindow();
        escenario.close();
    }
    
    private boolean validarDatosProfesorExterno(){
        boolean resultado = true;
         Usuario usuarioAIngresar = new Usuario();
        resultado &= validarAuxiliar(()->usuarioAIngresar.setNombreUsuario(txtf_Usuario.getText().toLowerCase()),lbl_ErrorUsuario);
        resultado &= validarAuxiliar(()->usuarioAIngresar.setContrasenia(pwdf_Contrasenia.getText()),lbl_ErrorContrasena);
        return resultado; 
    }    
    
    private boolean validarAuxiliar(Runnable setter, Label errorLabel){
        boolean resultado = true;
        try{
            setter.run();
            resultado = true;
        }catch(IllegalArgumentException | NullPointerException excepcion){
            LOG.info(excepcion);
            errorLabel.setVisible(true);
            resultado = false;
        }
        return resultado;
    }
    
    public void iniciarSesion(Usuario logger){
        ocultarLabelErrores();
        if(validarDatosProfesorExterno()){
            Usuario usuarioAIngresar = new Usuario();
            usuarioAIngresar.setNombreUsuario(txtf_Usuario.getText().toLowerCase());
            usuarioAIngresar.setContrasenia(pwdf_Contrasenia.getText());
            DAOUsuarioImplementacion DAOUsuario = new DAOUsuarioImplementacion();
            int validacionCredencial = DAOUsuario.validarCredenciales(usuarioAIngresar, logger);
            if(validacionCredencial==1){
                usuarioAIngresar.setTipoDeUsuario(DAOUsuario.obtenerTipoDeUsuario(usuarioAIngresar,logger));
                usuarioAIngresar.setIdUsuario(DAOUsuario.obtenerIdUsuario(usuarioAIngresar,logger));
                if(usuarioAIngresar.getTipoDeUsuario().equals(EnumTipoDeUsuario.Profesor.toString())){
                    validarEstadoProfesor(usuarioAIngresar);
                }else{
                    desplegarVentanaCorrespondiente(usuarioAIngresar);
                }
            }else if(validacionCredencial==0){
                Alertas.mostrarMensajeUsuarioNoEncontrado();
            }else{
                Alertas.mostrarMensajeErrorEnLaConexion();
            }     
        }else{
            Alertas.mostrarMensajeDatosInvalidos();
        }
                   
    }
    
    public void confirmarConexionALaBaseDeDatos(){
        Usuario logger = new Usuario();
        logger.setTipoDeUsuario(EnumTipoDeUsuario.Logger.toString());
        DAOUsuarioImplementacion DAOUsuario = new DAOUsuarioImplementacion();
        boolean resultadoConfirmacionDeAccion = DAOUsuario.confirmarConexionDeInicioDeSesion(logger);
        if(resultadoConfirmacionDeAccion){
            iniciarSesion(logger);
        }else{
            Alertas.mostrarMensajeErrorEnLaConexion();
        }
    }
    
    private void validarEstadoProfesor(Usuario usuario){
        Usuario logger = new Usuario();
        logger.setTipoDeUsuario(EnumTipoDeUsuario.Logger.toString());
        try{
            DAOProfesorImplementacion daoProfesor = new DAOProfesorImplementacion();
            Profesor profesorSesion = daoProfesor.obtenerProfesorPorIdUsuario(usuario.getIdUsuario(),logger);
            if(profesorSesion.getEstado().equals(EnumProfesor.Archivado.toString())){
                Alertas.mostrarMensajeAccesoDenegado();
            }else{
                desplegarVentanaCorrespondiente(usuario);
            }
        }catch(NullPointerException excepcion){
            LOG.error(excepcion.getMessage());
            Alertas.mostrarMensajeErrorEnLaConexion();
        }
    }
    
    private void desplegarVentanaCorrespondiente(Usuario usuario){
        UsuarioSingleton usuarioIngresado = UsuarioSingleton.getInstancia(usuario);
        usuarioIngresado.getIdUsuario();
        String rutaVentanaFXML=null;
        try{
            if(usuarioIngresado.getTipoDeUsuario().equals(EnumTipoDeUsuario.Administrativo.toString())){
                rutaVentanaFXML = "/interfazDeUsuario/Ventana_MenuAdministrador.fxml";
            }else if(usuarioIngresado.getTipoDeUsuario().equals(EnumTipoDeUsuario.Profesor.toString())){
                rutaVentanaFXML = "/interfazDeUsuario/Ventana_MenuPrincipalProfesor.fxml";
            }
            Parent root=FXMLLoader.load(getClass().getResource(rutaVentanaFXML));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }catch(IOException excepcion){
            Alertas.mostrarMensajeErrorAlDesplegarVentana();
            LOG.error(excepcion);
        }
        cerrarVentana();
    }
     
}
