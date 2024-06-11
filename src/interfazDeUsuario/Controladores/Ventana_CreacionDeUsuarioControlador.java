package interfazDeUsuario.Controladores;

import envioDeCorreos.EnvioDeCorreo;
import interfazDeUsuario.Alertas.Alertas;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Supplier;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logicaDeNegocio.DAOImplementacion.DAOProfesorImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOUsuarioImplementacion;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.ClasesAuxiliares.GeneradorDeContrasenias;
import logicaDeNegocio.clases.UsuarioSingleton;
import logicaDeNegocio.enums.EnumTipoDeUsuario;
import org.apache.log4j.Logger;

public class Ventana_CreacionDeUsuarioControlador implements Initializable {
    
    private static final Logger LOG=Logger.getLogger(Ventana_CreacionDeUsuarioControlador.class);
    private Stage escenario;
    @FXML
    private TextField txfd_NombreDeUsuario;
    @FXML
    private ComboBox cmb_TipoDeUsuario;
    @FXML
    private AnchorPane anchor_Ventana;
    @FXML
    private Label lbl_ErrorNombreDeUsuario;
    @FXML 
    private Label lbl_ErrorSeleccion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarComboboxTipoDeUsuario();
        ocultarLabelErrores();
    }
    
    private void ocultarLabelErrores(){
        lbl_ErrorNombreDeUsuario.setVisible(false);
        lbl_ErrorSeleccion.setVisible(false);
    }
    
    private void cerrarVentana(){
        escenario = (Stage) anchor_Ventana.getScene().getWindow();
        escenario.close();
    }
    
    public void regresarVentanaPrincipal(){
        int resultadoValidacion = validarConexionEstable();
        if(resultadoValidacion==1){
           String ruta = "/interfazDeUsuario/Ventana_MenuAdministrador.fxml";
           try{
               Parent root=FXMLLoader.load(getClass().getResource(ruta));
               Scene scene = new Scene(root);
               Stage stage = new Stage();
               stage.setScene(scene);
               stage.show();
               cerrarVentana();
           }catch(IOException excepcion){
               Alertas.mostrarMensajeErrorAlDesplegarVentana();
               LOG.error(excepcion);
           }   
        }else if(resultadoValidacion==-1){
            Alertas.mostrarMensajeErrorEnLaConexion();
            salirAlInicioDeSesion();
        }else if(resultadoValidacion==0){
            Alertas.mostrarMensajeUsuarioNoEncontrado();
        }       
    }
    
     public int validarConexionEstable(){
        int resultado;
        DAOUsuarioImplementacion daoUsuario = new DAOUsuarioImplementacion();
        resultado = daoUsuario.confirmarConexionDeUsuario();
        return resultado;
    }
     
    public void salirAlInicioDeSesion(){
        int resultadoValidacion = validarConexionEstable();
        if(resultadoValidacion==1){
            try {
            String rutaVentanaFXML = "/interfazDeUsuario/Ventana_InicioDeSesion.fxml";
            Parent root = FXMLLoader.load(getClass().getResource(rutaVentanaFXML));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            UsuarioSingleton.resetSingleton();
            cerrarVentana();
            } catch (IOException excepcion) {
                Alertas.mostrarMensajeErrorAlDesplegarVentana();
                LOG.error(excepcion.getCause());
            }
        }else if(resultadoValidacion==-1){
            Alertas.mostrarMensajeErrorEnLaConexion();
            salirAlInicioDeSesion();
        }else if(resultadoValidacion==0){
            Alertas.mostrarMensajeUsuarioNoEncontrado();
        }
    }
    
    private void llenarComboboxTipoDeUsuario(){
        for(EnumTipoDeUsuario tipos : EnumTipoDeUsuario.values()){
            if(tipos != EnumTipoDeUsuario.Logger){
                cmb_TipoDeUsuario.getItems().add(tipos.toString());
            }
        }
    }
    
    private Usuario crearUsuario(){
        Usuario usuario = new Usuario();
        String tipoDeUsuario = (String)cmb_TipoDeUsuario.getSelectionModel().getSelectedItem();
        String contrasenia = GeneradorDeContrasenias.generarContraseña();
        try{
            usuario.setNombreUsuario(txfd_NombreDeUsuario.getText().toLowerCase());
            usuario.setContrasenia(contrasenia);
            usuario.setTipoDeUsuario(tipoDeUsuario);
            usuario.setCorreo(txfd_NombreDeUsuario.getText().toLowerCase());
        }catch(IllegalArgumentException excepcion){
            Alertas.mostrarMensajeDatosInvalidos();
        }
        
        return usuario;
    }
    
    private boolean validarDatosUsuario(){
        boolean resultado = true;
        Usuario usuario = new Usuario();
        resultado &= validarAuxiliar(()->usuario.setNombreUsuario(txfd_NombreDeUsuario.getText().toLowerCase()),lbl_ErrorNombreDeUsuario);
        resultado &= validarSeleccion(()->(String) cmb_TipoDeUsuario.getSelectionModel().getSelectedItem(),lbl_ErrorSeleccion);
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
    
    private boolean validarSeleccion(Supplier<String> selector,Label errorLabel){
        boolean resultado = true;
        try{
            String seleccion = selector.get();
            if(!seleccion.isEmpty()){
                resultado = true;
            }
        }catch(IllegalArgumentException | NullPointerException excepcion){
            LOG.info(excepcion);
            errorLabel.setVisible(true);
            resultado = false;
        }
        return resultado;
    }
    
    private void limpiarInformacion(){
        this.txfd_NombreDeUsuario.setText("");
        cmb_TipoDeUsuario.getSelectionModel().clearSelection();        
        cmb_TipoDeUsuario.setPromptText("Tipo de usuario");
    }
    
    
    private void registrarUsuarioAdministrativo(Usuario usuario){
         if(obtenerResultadoValidacionConexion()){
            DAOUsuarioImplementacion daoUsuario = new DAOUsuarioImplementacion();
            int resultadoRegistro = daoUsuario.registrarUsuario(usuario);
            switch (resultadoRegistro) {
                case 1 -> {
                    int resultadoCorreo = mandarCorreo(usuario.getNombreUsuario(),usuario.getContrasenia(),usuario.getTipoDeUsuario());
                    if(resultadoCorreo == 1) {
                       Alertas.mostrarMensajeDatosIngresados();
                    }else{
                       Alertas.mostrarSinConexionAInternet("Por favor verifique su conexion a internet antes de registrar un usuario administrativo");
                    }
                }
                case 0 -> Alertas.mostrarMensajeDatosDuplicados();
                case -1 ->{ 
                    salirAlInicioDeSesion();
                    Alertas.mostrarMensajeErrorEnLaConexion();
                }
            }
        }else{
            salirAlInicioDeSesion();
        }
    }
    
    private void registrarUsuarioProfesor(Usuario usuario){
        if(obtenerResultadoValidacionConexion()){
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
                            int resultadoCorreo = mandarCorreo(usuario.getNombreUsuario(),usuario.getContrasenia(),usuario.getTipoDeUsuario());
                            if(resultadoCorreo==1){
                                Alertas.mostrarMensajeDatosIngresados();
                            }else if(resultadoCorreo==-1){
                                Alertas.mostrarSinConexionAInternet("Por favor verifique su conexion a internet o el correo proporcionado antes de registrar un usuario de profesor");
                            }
                        }
                    }
                    case -1 ->{ 
                        Alertas.mostrarMensajeErrorEnLaConexion();
                        salirAlInicioDeSesion();
                    }
                    default -> Alertas.mostrarMensajeDatosDuplicados();
                }
            }else if(idProfesor==0){
                Alertas.mostrarCorreoDeProfesorInexistente();
            }
        }else{
            salirAlInicioDeSesion();
        }        
    }
    
    private int mandarCorreo(String usuario, String contrasenia, String tipoDeUsuario){
        int resultadoEnvioDeCorreo;
        EnvioDeCorreo mandarCorreoCreacionDeUsuario = new EnvioDeCorreo();
        String asuntoCorreo = "Clave de acceso sistema coil vic";
        String cuerpoCorreo = "Se le ha registrado como "+tipoDeUsuario+" dentro del sistema COIL-VIC. \n\n"+
                "A continuación se le presentan sus claves de acceso para acceder al sistema: \n\n"+
                "Usuario: "+usuario+"\n\nContraseña: "+contrasenia+"\nBuen día\nAtte: Sistema de gestión COIL-VIC";
        String destinatario = usuario;
        mandarCorreoCreacionDeUsuario.setAsunto(asuntoCorreo);
        mandarCorreoCreacionDeUsuario.setContenido(cuerpoCorreo);
        mandarCorreoCreacionDeUsuario.setDestinatario(destinatario);
        resultadoEnvioDeCorreo = mandarCorreoCreacionDeUsuario.enviarCorreo();
        return resultadoEnvioDeCorreo;
    }
    
    public boolean obtenerResultadoValidacionConexion(){
        boolean resultadoValidacion = true;
        int resultado = validarConexionEstable();
        switch (resultado) {
            case 1:
                resultadoValidacion = true;
                break;
            case 0:
                Alertas.mostrarMensajeUsuarioNoEncontrado();
                resultadoValidacion = false;
                break;
            case -1:
                Alertas.mostrarMensajeErrorEnLaConexion();
                resultadoValidacion = false;
                break;
            default:
                break;
        }
        return resultadoValidacion;
    }
    
    public void registrarUsuario() {
        ocultarLabelErrores();
        if(validarDatosUsuario()){
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
        }else{
            Alertas.mostrarMensajeDatosInvalidos();
        }
    }
    
}
