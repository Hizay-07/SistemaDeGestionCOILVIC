package interfazDeUsuario.Controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import logicaDeNegocio.DAOImplementacion.DAOActividadImplementacion;
import interfazDeUsuario.Alertas.Alertas;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logicaDeNegocio.ClasesAuxiliares.ActividadAuxiliar;
import logicaDeNegocio.ClasesAuxiliares.ColaboracionAuxiliar;
import logicaDeNegocio.clases.Actividad;
import logicaDeNegocio.DAOImplementacion.DAOActividadImplementacion;
import org.apache.log4j.Logger;



public class Ventana_ModificarActividadController implements Initializable {

    private static final Logger LOG=Logger.getLogger(Ventana_ColaboracionActivaController.class);
    private Stage escenario;
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
    @FXML
    private Label lbl_NumeroDeActividad;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatosActividadAModificar();
    }
    
    public void cerrarVentana(){
        ActividadAuxiliar.resetInstancia();
        escenario = (Stage)anchor_Ventana.getScene().getWindow();
        escenario.close();
    }    
    
    public void cancelarModificacionDeActividad(){
        String ruta = "/interfazDeUsuario/Ventana_ActividadesColaboracionActiva.fxml";
        abrirVentanaCorrespondiente(ruta);     
    }
    
    public void abrirVentanaCorrespondiente(String rutaVentanaFXML){
         try{
            Parent root=FXMLLoader.load(getClass().getResource(rutaVentanaFXML));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show(); 
        }catch(IOException excepcion){
            LOG.error(excepcion.getCause());
        }
        cerrarVentana();
    }
    
    public void cargarDatosActividadAModificar(){
        ActividadAuxiliar actividad = ActividadAuxiliar.getInstancia();
        txfd_NombreDeActividad.setText(actividad.getNombre());
        dtp_FechaDeCierre.setValue(LocalDate.parse(actividad.getFechaDeCierre()));
        dtp_FechaDeInicio.setValue(LocalDate.parse(actividad.getFechaDeInicio()));
        txa_Descripcion.setText(actividad.getDescripcion());
        lbl_NumeroDeActividad.setText("Numero de actividad "+actividad.getNumeroActividad());
    }
    
    
    public Actividad obtenerDatosActividad(){
        Actividad actividadAModificar = new Actividad();  
        ActividadAuxiliar actividadAuxiliar = ActividadAuxiliar.getInstancia();
        LocalDate fechaCierreNueva = dtp_FechaDeCierre.getValue();
        LocalDate fechaInicioNueva = dtp_FechaDeInicio.getValue();
        LocalDate fechaActual = LocalDate.now(); 
        if(fechaCierreNueva.isAfter(fechaActual)&&fechaCierreNueva.isAfter(fechaInicioNueva)){
            String estadoActividad;
            if(fechaInicioNueva.isBefore(fechaActual)||fechaInicioNueva.isEqual(fechaActual)){
                estadoActividad = "Activa";
            }else{
                estadoActividad = "Inactiva";
            }
            try{
                actividadAModificar.setNombre(txfd_NombreDeActividad.getText());
                actividadAModificar.setFechaDeCierre(fechaCierreNueva.toString());
                actividadAModificar.setFechaDeInicio(fechaInicioNueva.toString());
                actividadAModificar.setDescripcion(txa_Descripcion.getText());
                actividadAModificar.setIdActividad(actividadAuxiliar.getIdActividad());
                actividadAModificar.setEstado(estadoActividad);
            }catch(IllegalArgumentException excepcion){
               LOG.error(excepcion);
               Alertas.mostrarMensajeDatosInvalidos();
               actividadAModificar = null;
            }
        }else{
            Alertas.mostrarFechasInvalidas();
        }
        return actividadAModificar;
    }    
    
     public void realizarModificacionDeActividad(){
        Actividad actividadAModificar = obtenerDatosActividad();
        DAOActividadImplementacion daoActividad = new DAOActividadImplementacion();
        if(Objects.nonNull(actividadAModificar)){
            int resultadoModificacionDatos = daoActividad.modificarActividad(actividadAModificar);
            int resultadoModificacionFechas = daoActividad.modificarFechaActividad(actividadAModificar);
            if (resultadoModificacionDatos == -1 || resultadoModificacionFechas == -1) {
                Alertas.mostrarMensajeErrorEnLaConexion();
            } else if (resultadoModificacionDatos == 1 && resultadoModificacionFechas == 1) {
                Alertas.mostrarMensajeDatosModificados();
            }
        }
    }
}
