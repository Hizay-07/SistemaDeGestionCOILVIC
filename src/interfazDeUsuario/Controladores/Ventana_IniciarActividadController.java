
package interfazDeUsuario.Controladores;

import logicaDeNegocio.clases.Actividad;
import interfazDeUsuario.Alertas.Alertas;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logicaDeNegocio.DAOImplementacion.DAOActividadImplementacion;
import org.apache.log4j.Logger;

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
        // TODO
    }    
    
    public void cancelarRegistroDeActividad(ActionEvent event){
        Stage escenario = (Stage) anchor_Ventana.getScene().getWindow();
        escenario.close();
    }
    
    public void realizarRegistroDeActividad(ActionEvent event){
        Actividad nuevaActividad = new Actividad();
        DAOActividadImplementacion daoActividad = new DAOActividadImplementacion();
        try{
            nuevaActividad.setNumeroActividad(Integer.parseInt(txfd_NumeroDeActividad.getText()));
            nuevaActividad.setNombre(txfd_NombreDeActividad.getText());
            nuevaActividad.setFechaDeInicio(dtp_FechaDeInicio.getValue().toString());
            nuevaActividad.setFechaDeCierre(dtp_FechaDeCierre.getValue().toString());
            nuevaActividad.setDescripcion(txa_Descripcion.getText());
            nuevaActividad.setIdColaboracion(1);
            nuevaActividad.setEstado("nueva");
            int resultadoRegistro = daoActividad.registrarActividad(nuevaActividad);
            if(resultadoRegistro == 1){
                Alertas.mostrarMensajeDatosIngresados();
            }else if (resultadoRegistro == -1){
                Alertas.mostrarMensajeErrorEnLaConexion();
            }
        }catch(IllegalArgumentException excepcion){
            LOG.error(excepcion);
            Alertas.mostrarMensajeDatosInvalidos();
        }
        
    }
}
