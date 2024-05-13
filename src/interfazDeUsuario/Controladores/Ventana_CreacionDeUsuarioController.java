package interfazDeUsuario.Controladores;

import interfazDeUsuario.Alertas.Alertas;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logicaDeNegocio.DAOImplementacion.DAOProfesorImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOUsuarioImplementacion;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.enums.EnumTipoDeUsuario;
import org.apache.log4j.Logger;


public class Ventana_CreacionDeUsuarioController implements Initializable {
    
    private static final Logger LOG=Logger.getLogger(Ventana_CreacionDeUsuarioController.class);
    private Stage escenario;
    @FXML
    private TextField txfd_NombreDeUsuario;
    @FXML
    private TextField txfd_Contrasenia;
    @FXML
    private TextField txfd_Correo;
    @FXML
    private ComboBox cmb_TipoDeUsuario;
    @FXML
    private AnchorPane anchor_Ventana;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarComboboxTipoDeUsuario();
    }
    
    public void cerrarVentana(){
        escenario = (Stage) anchor_Ventana.getScene().getWindow();
        escenario.close();
    }
    
    public void regresarVentanaPrincipal(){
        String ruta = "/interfazDeUsuario/Ventana_MenuAdministrador.fxml";
        try{
            Parent root=FXMLLoader.load(getClass().getResource(ruta));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }catch(IOException excepcion){
            LOG.error(excepcion);
        }
        
        cerrarVentana();
    }
    
    public void llenarComboboxTipoDeUsuario(){
        for(EnumTipoDeUsuario tipos : EnumTipoDeUsuario.values()){
            if(tipos != EnumTipoDeUsuario.Logger){
                cmb_TipoDeUsuario.getItems().add(tipos.toString());
            }
        }
    }
    
    public Usuario crearUsuario(){
        Usuario usuario = new Usuario();
        String tipoDeUsuario = (String)cmb_TipoDeUsuario.getSelectionModel().getSelectedItem();
        try{
            usuario.setNombreUsuario(txfd_NombreDeUsuario.getText());
            usuario.setContrasenia(txfd_Contrasenia.getText());
            usuario.setTipoDeUsuario(tipoDeUsuario);
            usuario.setCorreo(txfd_Correo.getText());
        }catch(IllegalArgumentException excepcion){
            Alertas.mostrarMensajeDatosInvalidos();
        }
        
        return usuario;
    }
    
    public void limpiarInformacion(){
        this.txfd_Contrasenia.setText("");
        this.txfd_Correo.setText("");
        this.txfd_NombreDeUsuario.setText("");
        cmb_TipoDeUsuario.getSelectionModel().clearSelection();        
        cmb_TipoDeUsuario.setPromptText("Tipo de usuario");
    }
    
    
    public void registrarUsuarioAdministrativo(Usuario usuario){
        DAOUsuarioImplementacion daoUsuario = new DAOUsuarioImplementacion();
        int resultadoRegistro = daoUsuario.registrarUsuario(usuario);
        
        switch (resultadoRegistro) {
            case 1 -> Alertas.mostrarMensajeDatosIngresados();
            case 0 -> Alertas.mostrarMensajeDatosDuplicados();
            case -1 -> Alertas.mostrarMensajeErrorEnLaConexion();
        }
    }
    
    public void registrarUsuarioProfesor(Usuario usuario){
        DAOUsuarioImplementacion daoUsuario = new DAOUsuarioImplementacion();
        DAOProfesorImplementacion daoProfesor = new DAOProfesorImplementacion();
        int idProfesor = daoProfesor.obtenerIdProfesorPorCorreo(usuario.getCorreo());
        if(idProfesor!=0){
            int resultadoRegistro = daoUsuario.registrarUsuario(usuario);
            switch (resultadoRegistro) {
                case 1 -> {
                    int resultadoModificacion = daoProfesor.asignarUsuarioDeProfesorPorCorreo(usuario.getCorreo());
                    if(resultadoModificacion==0){
                        Alertas.mostrarMensajeProfesorConUsuario();
                    }else{
                        Alertas.mostrarMensajeDatosIngresados();
                    }
                }
                case -1 -> Alertas.mostrarMensajeErrorEnLaConexion();
                default -> Alertas.mostrarMensajeDatosDuplicados();
            }
        }else if(idProfesor==0){
            Alertas.mostrarCorreoDeProfesorInexistente();
        }
    }
    
    
    public void registrarUsuario() {
        Usuario usuarioNuevo = crearUsuario();
        try{
             if (usuarioNuevo.getTipoDeUsuario().equals("Administrativo")) {
            registrarUsuarioAdministrativo(usuarioNuevo);
            } else if (usuarioNuevo.getTipoDeUsuario().equals("Profesor")) {
                registrarUsuarioProfesor(usuarioNuevo);
            }
        }catch(NullPointerException excepcion){
            LOG.error(excepcion.getMessage());
            Alertas.mostrarMensajeDatosInvalidos();
        }

        limpiarInformacion();
    }
}
