
package interfazDeUsuario.Controladores;

import logicaDeNegocio.clases.Actividad;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logicaDeNegocio.ClasesAuxiliares.ColaboracionAuxiliar;
import logicaDeNegocio.DAOImplementacion.DAOActividadImplementacion;
import org.apache.log4j.Logger;
import java.time.LocalDate;
import java.util.Objects;

public class Ventana_IniciarActividadController implements Initializable {

    private static final Logger LOG=Logger.getLogger(Ventana_ColaboracionActivaController.class);
    
    @FXML
    private AnchorPane anchor_Ventana;
    @FXML
    private TextField txfd_NumeroDeActividad;
    @FXML
    private TextField txfd_NombreDeActividad;
    @FXML
    private DatePicker dtp_FechaDeInicio;
    @FXML
    private DatePicker dtp_FechaDeCierre;
    @FXML
    private TextArea txa_Descripcion;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void cerrarVentana(){
        Stage escenario = (Stage) anchor_Ventana.getScene().getWindow();
        escenario.close();
    }
    
    public void regresarMenuPrincipal(){
        String rutaVentanaFXML="/interfazDeUsuario/Ventana_ColaboracionActiva.fxml";
        desplegarVentanaCorrespondiente(rutaVentanaFXML);
    }
    
    public void desplegarVentanaCorrespondiente(String rutaVentanaFXML){
        try {
            Parent root = FXMLLoader.load(getClass().getResource(rutaVentanaFXML));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException excepcion) {
            LOG.error(excepcion);
        }
        cerrarVentana();
    }
    
    public Actividad obtenerDatosActividad(){
        Actividad nuevaActividad = new Actividad();
        ColaboracionAuxiliar colaboracionActual = ColaboracionAuxiliar.getInstancia();
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaInicioActividad = dtp_FechaDeInicio.getValue();
        LocalDate fechaCierreActividad = dtp_FechaDeCierre.getValue();
        
        if(fechaInicioActividad.isBefore(fechaCierreActividad)&&fechaCierreActividad.isAfter(fechaActual)){
            String estadoActividad;
            if(fechaActual.isAfter(fechaInicioActividad)||fechaActual.isEqual(fechaInicioActividad)){
                estadoActividad = "Activa";
            }else{
                estadoActividad = "Inactiva";
            }
            try{
                nuevaActividad.setNumeroActividad(Integer.parseInt(txfd_NumeroDeActividad.getText()));
                nuevaActividad.setNombre(txfd_NombreDeActividad.getText());
                nuevaActividad.setFechaDeInicio(dtp_FechaDeInicio.getValue().toString());
                nuevaActividad.setFechaDeCierre(dtp_FechaDeCierre.getValue().toString());
                nuevaActividad.setDescripcion(txa_Descripcion.getText());
                nuevaActividad.setIdColaboracion(colaboracionActual.getIdColaboracion());
                nuevaActividad.setEstado(estadoActividad);
            }catch(IllegalArgumentException excepcion){
                LOG.error(excepcion);
                Alertas.mostrarMensajeDatosInvalidos();
            }
        }else{
            Alertas.mostrarMensajeFechaInvalida();
            nuevaActividad=null;
        }
        
        return nuevaActividad;
    }
    
    public void realizarRegistroDeActividad(ActionEvent event){
        Actividad nuevaActividad = obtenerDatosActividad();
        DAOActividadImplementacion daoActividad = new DAOActividadImplementacion();
        if(Objects.nonNull(nuevaActividad)){
            int resultadoRegistro = daoActividad.registrarActividad(nuevaActividad);
            if (resultadoRegistro == 1) {
                Alertas.mostrarMensajeDatosIngresados();
            } else if (resultadoRegistro == -1) {
                Alertas.mostrarMensajeErrorEnLaConexion();
            }
        }
    }
}
