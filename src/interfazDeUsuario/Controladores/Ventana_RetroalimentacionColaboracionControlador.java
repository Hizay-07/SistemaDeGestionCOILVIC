
package interfazDeUsuario.Controladores;

import envioDeCorreos.EnvioDeCorreo;
import interfazDeUsuario.Alertas.Alertas;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logicaDeNegocio.DAOImplementacion.DAOUsuarioImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOColaboracionImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOColaboracionProfesorImplementacion;
import logicaDeNegocio.clases.ProfesorSingleton;
import logicaDeNegocio.clases.UsuarioSingleton;
import logicaDeNegocio.clases.Colaboracion;
import logicaDeNegocio.clases.Profesor;
import logicaDeNegocio.ClasesAuxiliares.ColaboracionAuxiliar;
import org.apache.log4j.Logger;


public class Ventana_RetroalimentacionColaboracionControlador implements Initializable {

    private static final Logger LOG=Logger.getLogger(Ventana_ColaboracionesControlador.class);
    private Stage escenario;
    @FXML
    private AnchorPane anchor_Ventana;
    @FXML
    private TextArea txa_Retroalimentacion;
    @FXML
    private Label lbl_FechaDeInicioDato;
    @FXML
    private Label lbl_FechaDeCierreDato;
    @FXML
    private Label lbl_ExperienciaEducativaDato;
    @FXML
    private Label lbl_ProgramaEducativoDato;
    @FXML
    private Label lbl_ErrorTextArea;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       cargarElementoColaboracion();
       ocultarLblErrores();
       limitarTextFields();
    }    
    
    private void limitarTextFields(){
        ComponentesDeVentanaControlador.limitarTextArea(txa_Retroalimentacion, 250);
    }
    
    private boolean validarDatosEvaluacion(){
        boolean resultado = true;
        Colaboracion colaboracion = new Colaboracion();
        resultado &= validarAuxiliar(()->colaboracion.setRetroalimentacion(txa_Retroalimentacion.getText()),lbl_ErrorTextArea);
        return resultado; 
    }    
    private boolean validarAuxiliar(Runnable asignador, Label lbl_Error){
        boolean resultado = true;
        try{
            asignador.run();
            resultado = true;
        }catch(IllegalArgumentException | NullPointerException excepcion){
            LOG.info(excepcion);
            lbl_Error.setVisible(true);
            resultado = false;
        }
        return resultado;
    }
    
    public void ocultarLblErrores(){
        lbl_ErrorTextArea.setVisible(false);
    }
    
    private void cargarElementoColaboracion(){
        ColaboracionAuxiliar colaboracion = ColaboracionAuxiliar.getInstancia();
        lbl_FechaDeInicioDato.setText(colaboracion.getPropuestaColaboracion().getFechaInicio());
        lbl_FechaDeCierreDato.setText(colaboracion.getPropuestaColaboracion().getFechaCierre());
        lbl_ExperienciaEducativaDato.setText(colaboracion.getPropuestaColaboracion().getExperienciaEducativa());
        lbl_ProgramaEducativoDato.setText(colaboracion.getPropuestaColaboracion().getProgramaEducativoEstudiantil());
    }
    
    private void cerrarVentana(){
        escenario = (Stage)anchor_Ventana.getScene().getWindow();
        escenario.close();
    }
    
    public void regresarDeVentana(){
        String ruta = "/interfazDeUsuario/Ventana_Colaboraciones.fxml";
        desplegarVentanaCorrespondiente(ruta);
    }
    
    public void cancelarRegistro(){
        if(Alertas.mostrarConfirmacionDeAccion("¿Desea cancelar la creacion de la retroalimentación?")){
            String ruta = "/interfazDeUsuario/Ventana_Colaboraciones.fxml";
            desplegarVentanaCorrespondiente(ruta);
        }
    }
    
    public void realizarRegistroRetroalimentacion(){
        ocultarLblErrores();
        if(obtenerResultadoValidacionConexion()){
            if(validarDatosEvaluacion()){
                Colaboracion colaboracionAActualizar = new Colaboracion();
                DAOColaboracionImplementacion daoColaboracion = new DAOColaboracionImplementacion();
                colaboracionAActualizar.setIdColaboracion(ColaboracionAuxiliar.getInstancia().getIdColaboracion());
                colaboracionAActualizar.setRetroalimentacion(txa_Retroalimentacion.getText());
                int resultadoModificacion = daoColaboracion.realizarRetroalimentacionColaboracion(colaboracionAActualizar);
                if(resultadoModificacion==1){
                    Alertas.mostrarMensajeDatosIngresados();
                    enviarCorreoConRetroalimentacion(colaboracionAActualizar);
                    regresarDeVentana();
                }else{
                    Alertas.mostrarMensajeErrorEnLaConexion();
                    salirAlInicioDeSesion();
                }
            }else{
                Alertas.mostrarMensajeDatosInvalidos();
            }
        }else{
            salirAlInicioDeSesion();
        }
    }
    
    private void enviarCorreoConRetroalimentacion(Colaboracion colaboracion){
        DAOColaboracionProfesorImplementacion daoColaboracion = new DAOColaboracionProfesorImplementacion();
        List<Profesor> profesores = daoColaboracion.obtenerProfesoresPorIdColaboracion(colaboracion);
        if(profesores.get(0).getNombre().equals("Excepcion")){
            Alertas.mostrarMensajeErrorEnLaConexion();
            salirAlInicioDeSesion();
        }else{
            int resultadoEnviosDeCorreo=0;
            for(int numeroDeProfesor=0;numeroDeProfesor<profesores.size();numeroDeProfesor++){
                int resultadoEnvioDeCorreo=0;
                EnvioDeCorreo mandarCorreoCreacionDeUsuario = new EnvioDeCorreo();
                String asuntoCorreo = "Retroalimentación de colaboración COIL-VIC";
                String cuerpoCorreo = "Estimado profesor "+profesores.get(numeroDeProfesor).getNombre()+" "+profesores.get(numeroDeProfesor).getApellidoPaterno()+": "+
                        "\nSe ha realizado una retroalimentación sobre su colaboracion: \n"+txa_Retroalimentacion.getText()+"\n"
                        + "ATTE: Sistema de gestión COIL-VIC";
                String destinatario =profesores.get(numeroDeProfesor).getCorreo();
                mandarCorreoCreacionDeUsuario.setAsunto(asuntoCorreo);
                mandarCorreoCreacionDeUsuario.setContenido(cuerpoCorreo);
                mandarCorreoCreacionDeUsuario.setDestinatario(destinatario);
                resultadoEnvioDeCorreo = mandarCorreoCreacionDeUsuario.enviarCorreo();
                if(resultadoEnvioDeCorreo==1){
                    resultadoEnviosDeCorreo++;
                }
            }
            if(resultadoEnviosDeCorreo==profesores.size()){
                Alertas.mostrarMensajeNotificacionEnviada();
            }else{
                Alertas.mostrarSinConexionAInternet("No se ha podido mandar la notificación al profesor destinatario.\n"
                     + "Por favor verifique su conexión a internet.");
            }
        }
    }
    
    public int validarConexionEstable(){
        int resultado;
        DAOUsuarioImplementacion daoUsuario = new DAOUsuarioImplementacion();
        resultado = daoUsuario.confirmarConexionDeUsuario();
        return resultado;
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
    
    public void desplegarVentanaCorrespondiente(String rutaFxml){
        if(obtenerResultadoValidacionConexion()){
            try{
            Parent root=FXMLLoader.load(getClass().getResource(rutaFxml));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            cerrarVentana();
            }catch(IOException excepcion){
                Alertas.mostrarMensajeErrorAlDesplegarVentana();
                LOG.error(excepcion.getCause());            
            }
        }else{
            salirAlInicioDeSesion();
        }
    }
     
    public void salirAlInicioDeSesion(){
        String rutaVentanaFXML = null;
        try {
            rutaVentanaFXML = "/interfazDeUsuario/Ventana_InicioDeSesion.fxml";
            Parent root = FXMLLoader.load(getClass().getResource(rutaVentanaFXML));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            UsuarioSingleton.resetSingleton();
            ProfesorSingleton.resetSingleton();
            cerrarVentana();
        } catch (IOException excepcion) {
            LOG.error(excepcion.getCause());
        }
    }
}
