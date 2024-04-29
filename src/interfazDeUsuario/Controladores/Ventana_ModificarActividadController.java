package interfazDeUsuario.Controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import logicaDeNegocio.DAOImplementacion.DAOActividadImplementacion;
import interfazDeUsuario.Alertas.Alertas;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logicaDeNegocio.clases.Actividad;
import logicaDeNegocio.DAOImplementacion.DAOActividadImplementacion;
import org.apache.log4j.Logger;



public class Ventana_ModificarActividadController implements Initializable {

    private static final Logger LOG=Logger.getLogger(Ventana_ColaboracionActivaController.class);
    
    @FXML
    private AnchorPane anchor_Ventana;
    @FXML
    private TextField txfd_NombreDeActividad;
    @FXML
    private TextArea txa_Descripcion;
    @FXML
    private DatePicker dtp_FechaDeInicio;
    @FXML
    private DatePicker dtp_FechaDeCierre;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void cancelarModificacionDeActividad(){
        Stage escenario = (Stage) anchor_Ventana.getScene().getWindow();
        escenario.close();
    }
    
    public Actividad obtenerDatosActividad(){
        Actividad actividadAModificar = new Actividad();  
        try{
            actividadAModificar.setNombre(txfd_NombreDeActividad.getText());
            actividadAModificar.setFechaDeCierre(dtp_FechaDeCierre.getValue().toString());
            actividadAModificar.setFechaDeInicio(dtp_FechaDeInicio.getValue().toString());
            actividadAModificar.setDescripcion(txa_Descripcion.getText());
            actividadAModificar.setIdActividad(3);
         }catch(IllegalArgumentException excepcion){
            LOG.error(excepcion);
            Alertas.mostrarMensajeDatosInvalidos();
         }
        return actividadAModificar;
    }    
    
     public void realizarModificacionDeActividad(){
        Actividad actividadAModificar = new Actividad();
        DAOActividadImplementacion daoActividad = new DAOActividadImplementacion();

        int resultadoModificacionDatos = daoActividad.modificarActividad(actividadAModificar);
        int resultadoModificacionFechas = daoActividad.modificarFechaActividad(actividadAModificar);

        if (resultadoModificacionDatos == -1 || resultadoModificacionFechas == -1) {
            Alertas.mostrarMensajeErrorEnLaConexion();
        } else if (resultadoModificacionDatos == 1 && resultadoModificacionFechas == 1) {
            Alertas.mostrarMensajeDatosModificados();
        }
    }
}
