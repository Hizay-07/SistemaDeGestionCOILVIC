package interfazDeUsuario.Controladores;

import interfazDeUsuario.Alertas.Alertas;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logicaDeNegocio.DAOImplementacion.DAOEmisionPropuestaImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOEvaluacionPropuestaImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOProfesorImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOPropuestaColaboracionImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOUsuarioImplementacion;
import logicaDeNegocio.clases.EvaluacionPropuesta;
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
    private Stage stage_ventana;
    private int idPropuestaColaboracion;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmb_EvaluarPropuesta.getItems().addAll("Aprobada","Rechazada");                
    }    
    
    public void inicializar(int idPropuestaColaboracion){
        this.idPropuestaColaboracion=idPropuestaColaboracion;
    }        
    
    public void registrarEvaluacionPropuesta(){
        EvaluacionPropuesta evaluacionPropuesta=new EvaluacionPropuesta();
        UsuarioSingleton usuario=UsuarioSingleton.getInstancia();
        int idUsuario=usuario.getIdUsuario();
        DAOEvaluacionPropuestaImplementacion daoEvaluacionPropuesta=new DAOEvaluacionPropuestaImplementacion();
        DAOPropuestaColaboracionImplementacion daoPropuestaColaboracion=new DAOPropuestaColaboracionImplementacion();
        DAOProfesorImplementacion daoProfesor=new DAOProfesorImplementacion();
        DAOEmisionPropuestaImplementacion daoEmisionPropuesta=new DAOEmisionPropuestaImplementacion();
        
        try{
            evaluacionPropuesta.setIdUsuario(idUsuario);
            evaluacionPropuesta.setJustificacion(txa_Justificacion.getText());
            String evaluacion=(String) cmb_EvaluarPropuesta.getSelectionModel().getSelectedItem();
            evaluacionPropuesta.setEvaluacion(evaluacion);
            evaluacionPropuesta.setFechaEvaluacion(obtenerFechaActual());
            evaluacionPropuesta.setIdPropuestaColaboracion(idPropuestaColaboracion);
            daoEvaluacionPropuesta.registrarEvaluacionPropuesta(evaluacionPropuesta);            
            if(evaluacion.equals("Aprobada")){
                daoPropuestaColaboracion.aprobarPropuestaColaboracionPorId(idPropuestaColaboracion);                
            }else{
                daoPropuestaColaboracion.rechazarPropuestaColaboracionPorId(idPropuestaColaboracion);                
                int idProfesor=daoEmisionPropuesta.consultarIdProfesorPorIdPropuestaColaboracion(idPropuestaColaboracion);
                daoProfesor.cambiarEstadoProfesor(idProfesor, EnumProfesor.Activo.toString());
            }            
            salirDeLaVentana();
        }catch(IllegalArgumentException excepcion){     
            Alertas.mostrarMensajeDatosInvalidos();
            LOG.info(excepcion);
        }
    }
    
    private String obtenerFechaActual(){
        LocalDate fechaActual = LocalDate.now();                
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaActualFormateada = fechaActual.format(formatter);
        return fechaActualFormateada;
    }
    
    public void salirDeLaVentana(){
        if(validarConexionEstable()){
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
            Alertas.mostrarMensajeSinConexion();
            salirAlInicioDeSesion();
        }               
    }
    
    private boolean validarConexionEstable(){
        boolean resultado;
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
