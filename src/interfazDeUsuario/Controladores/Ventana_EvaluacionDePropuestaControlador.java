package interfazDeUsuario.Controladores;

import envioDeCorreos.EnvioDeCorreo;
import interfazDeUsuario.Alertas.Alertas;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.function.Supplier;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logicaDeNegocio.DAOImplementacion.DAOEmisionPropuestaImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOEvaluacionPropuestaImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOProfesorImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOPropuestaColaboracionImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOUsuarioImplementacion;
import logicaDeNegocio.clases.EvaluacionPropuesta;
import logicaDeNegocio.clases.Profesor;
import logicaDeNegocio.clases.ProfesorSingleton;
import logicaDeNegocio.clases.UsuarioSingleton;
import logicaDeNegocio.enums.EnumProfesor;
import org.apache.log4j.Logger;

public class Ventana_EvaluacionDePropuestaControlador implements Initializable {
    
    private static final Logger LOG=Logger.getLogger(Ventana_EvaluacionDePropuestaControlador.class);    
    @FXML
    private AnchorPane anchor_EvaluacionDePropuesta;
    @FXML
    private ComboBox<String> cmb_EvaluarPropuesta;
    @FXML
    private TextArea txa_Justificacion;
    @FXML
    private Label lbl_ErrorJustificacion;
    @FXML
    private Label lbl_ErrorEvaluacionPropuesta;
    private Stage stage_ventana;
    private int idPropuestaColaboracion;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmb_EvaluarPropuesta.getItems().addAll("Aprobada","Rechazada");
        limitarTextFields();
    }    
    
    public void inicializar(int idPropuestaColaboracion){
        this.idPropuestaColaboracion=idPropuestaColaboracion;
    }        
    
    private void limitarTextFields(){
        ComponentesDeVentanaControlador.limitarTextArea(txa_Justificacion, 250);
    }
    
    public void registrarEvaluacionPropuesta(){
        if(obtenerResultadoValidacionConexion()){
            EvaluacionPropuesta evaluacionPropuesta=new EvaluacionPropuesta();
            UsuarioSingleton usuario=UsuarioSingleton.getInstancia();
            int idUsuario=usuario.getIdUsuario();
            DAOEvaluacionPropuestaImplementacion daoEvaluacionPropuesta=new DAOEvaluacionPropuestaImplementacion();
            DAOPropuestaColaboracionImplementacion daoPropuestaColaboracion=new DAOPropuestaColaboracionImplementacion();
            DAOProfesorImplementacion daoProfesor=new DAOProfesorImplementacion();
            DAOEmisionPropuestaImplementacion daoEmisionPropuesta=new DAOEmisionPropuestaImplementacion();
            if(validarDatosEvaluacion()){
                String justificacion = txa_Justificacion.getText();
                evaluacionPropuesta.setIdUsuario(idUsuario);
                evaluacionPropuesta.setJustificacion(justificacion);
                String evaluacion=(String) cmb_EvaluarPropuesta.getSelectionModel().getSelectedItem();
                evaluacionPropuesta.setEvaluacion(evaluacion);
                evaluacionPropuesta.setFechaEvaluacion(obtenerFechaActual());
                evaluacionPropuesta.setIdPropuestaColaboracion(idPropuestaColaboracion);
                daoEvaluacionPropuesta.registrarEvaluacionPropuesta(evaluacionPropuesta);            
                if(evaluacion.equals("Aprobada")){
                    daoPropuestaColaboracion.aprobarPropuestaColaboracionPorId(idPropuestaColaboracion); 
                    int idProfesor=daoEmisionPropuesta.consultarIdProfesorPorIdPropuestaColaboracion(idPropuestaColaboracion);
                    mandarCorreoRespuestaPropuestaDeColaboracion(idProfesor,"Aprobada",justificacion);
                }else{
                    daoPropuestaColaboracion.rechazarPropuestaColaboracionPorId(idPropuestaColaboracion);                
                    int idProfesor=daoEmisionPropuesta.consultarIdProfesorPorIdPropuestaColaboracion(idPropuestaColaboracion);
                    daoProfesor.cambiarEstadoProfesor(idProfesor, EnumProfesor.Activo.toString());
                    mandarCorreoRespuestaPropuestaDeColaboracion(idProfesor,"Rechazada",justificacion);
                }            
            salirDeLaVentana();
            }else{
                Alertas.mostrarMensajeDatosInvalidos();
            }
        }else{
            salirAlInicioDeSesion();
        }
        
    }
    
     private boolean validarDatosEvaluacion(){
        boolean resultado = true;
        EvaluacionPropuesta evaluacionPropuesta = new EvaluacionPropuesta();
        resultado &= validarAuxiliar(()->evaluacionPropuesta.setJustificacion(txa_Justificacion.getText()),lbl_ErrorJustificacion);
        resultado &= validarSeleccion(()->(String) cmb_EvaluarPropuesta.getSelectionModel().getSelectedItem(),lbl_ErrorEvaluacionPropuesta);
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
    
    private boolean validarSeleccion(Supplier<String> selector,Label lbl_Error){
        boolean resultado = true;
        try{
            String seleccion = selector.get();
            if(!seleccion.isEmpty()){
                resultado = true;
            }
        }catch(IllegalArgumentException | NullPointerException excepcion){
            LOG.info(excepcion);
            lbl_Error.setVisible(true);
            resultado = false;
        }
        return resultado;
    }
    
    private void mandarCorreoRespuestaPropuestaDeColaboracion(int idProfesor, String resultado,String justificacion){
        int resultadoEnvioDeCorreo;
        DAOProfesorImplementacion daoProfesor = new DAOProfesorImplementacion();
        Profesor profesor = daoProfesor.consultarProfesorPorId(idProfesor);
        EnvioDeCorreo mandarCorreoCreacionDeUsuario = new EnvioDeCorreo();
        String asuntoCorreo = "Respuesta de propuesta de colaboración COIL-VIC";
        String cuerpoCorreo = "Estimado profesor "+profesor.getNombre()+" "+profesor.getApellidoPaterno()+": "+
                "\nLa propuesta de colaboración que ha emitido previamente ha sido "+resultado+""
                +"\n\nJustificación: \n"+justificacion+"\n\n Buen día. \n ATTE: Sistema de gestión COIL-VIC";
        String destinatario = profesor.getCorreo();
        mandarCorreoCreacionDeUsuario.setAsunto(asuntoCorreo);
        mandarCorreoCreacionDeUsuario.setContenido(cuerpoCorreo);
        mandarCorreoCreacionDeUsuario.setDestinatario(destinatario);
        resultadoEnvioDeCorreo = mandarCorreoCreacionDeUsuario.enviarCorreo();
        if(resultadoEnvioDeCorreo==1){
            Alertas.mostrarMensajeNotificacionEnviada();
        }else{
             Alertas.mostrarSinConexionAInternet("No se ha podido mandar la notificación al profesor destinatario.\n"
                     + "Por favor verifique su conexión a internet.");
        }
    }
    
    private String obtenerFechaActual(){
        LocalDate fechaActual = LocalDate.now();                
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaActualFormateada = fechaActual.format(formatter);
        return fechaActualFormateada;
    }
    
    public void salirDeLaVentana(){
        if(obtenerResultadoValidacionConexion()){
            String rutaVentanaFXML = null;
            try{
                rutaVentanaFXML = "/interfazDeUsuario/Ventana_PropuestasDeColaboracion.fxml";
                Parent root=FXMLLoader.load(getClass().getResource(rutaVentanaFXML));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
                cerrarVentana();
            }catch(IOException excepcion){
                Alertas.mostrarMensajeErrorAlDesplegarVentana();
                LOG.error(excepcion);
            }    
        }else{
            salirAlInicioDeSesion();
        }             
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
    
    public int validarConexionEstable(){
        int resultado;
        DAOUsuarioImplementacion daoUsuario = new DAOUsuarioImplementacion();
        resultado = daoUsuario.confirmarConexionDeUsuario();
        return resultado;
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
            Alertas.mostrarMensajeErrorAlDesplegarVentana();
            LOG.error(excepcion.getCause());
        }
    }
    
    private void cerrarVentana(){
        stage_ventana=(Stage) anchor_EvaluacionDePropuesta.getScene().getWindow();
        stage_ventana.close();
    }
    
}
