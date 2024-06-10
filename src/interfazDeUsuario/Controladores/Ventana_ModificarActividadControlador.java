package interfazDeUsuario.Controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import interfazDeUsuario.Alertas.Alertas;
import java.io.IOException;
import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import logicaDeNegocio.ClasesAuxiliares.ActividadAuxiliar;
import logicaDeNegocio.ClasesAuxiliares.ColaboracionAuxiliar;
import logicaDeNegocio.clases.Actividad;
import logicaDeNegocio.DAOImplementacion.DAOActividadImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOUsuarioImplementacion;
import logicaDeNegocio.clases.ProfesorSingleton;
import logicaDeNegocio.clases.PropuestaColaboracion;
import logicaDeNegocio.clases.UsuarioSingleton;
import org.apache.log4j.Logger;

public class Ventana_ModificarActividadControlador implements Initializable {

    private static final Logger LOG=Logger.getLogger(Ventana_ColaboracionActivaControlador.class);
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
    @FXML
    private Label lbl_ErrorActividad;
    @FXML
    private Label lbl_ErrorDescripcion;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatosActividadAModificar();
        limitarFechasDePeriodoActividad();
        ocultarLabelErrores();
    }
    
    private void ocultarLabelErrores(){
        lbl_ErrorActividad.setVisible(false);
        lbl_ErrorDescripcion.setVisible(false);
    }
    
    private void cerrarVentana(){
        ActividadAuxiliar.resetInstancia();
        escenario = (Stage)anchor_Ventana.getScene().getWindow();
        escenario.close();
    }    
    
    public void cancelarModificacionDeActividad(){
        String ruta = "/interfazDeUsuario/Ventana_ActividadesColaboracionActiva.fxml";
        desplegarVentanaCorrespondiente(ruta);     
    }
    
    private void limitarFechasDePeriodoActividad(){
        ColaboracionAuxiliar colaboracionActual = ColaboracionAuxiliar.getInstancia();
        PropuestaColaboracion propuesta = colaboracionActual.getPropuestaColaboracion();
        LocalDate fechaMaxima = LocalDate.parse(propuesta.getFechaCierre());
        LocalDate fechaMinima = LocalDate.parse(propuesta.getFechaInicio());
        dtp_FechaDeInicio.setDayCellFactory(createDayCellFactory(fechaMinima, fechaMaxima));
        dtp_FechaDeCierre.setDayCellFactory(createDayCellFactory(fechaMinima, fechaMaxima));
    }
    
    private Callback<DatePicker, DateCell> createDayCellFactory(LocalDate minDate, LocalDate maxDate) {
        return new Callback<>(){
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item.isBefore(minDate) || item.isAfter(maxDate)) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        };
    }
    
    public int validarConexionEstable(){
        int resultado;
        DAOUsuarioImplementacion daoUsuario = new DAOUsuarioImplementacion();
        resultado = daoUsuario.confirmarConexionDeUsuario();
        return resultado;
    }
    
    public void desplegarVentanaCorrespondiente(String rutaFxml){
        int resultadoValidacionConexion = validarConexionEstable();
        if(resultadoValidacionConexion==1){
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
        }else if(resultadoValidacionConexion == 0){
            Alertas.mostrarMensajeUsuarioNoEncontrado();
        }else if(resultadoValidacionConexion == -1){
             Alertas.mostrarMensajeErrorEnLaConexion();
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
            Alertas.mostrarMensajeErrorAlDesplegarVentana();
            LOG.error(excepcion.getCause());
        }
    }
    
    private void cargarDatosActividadAModificar(){
        ActividadAuxiliar actividad = ActividadAuxiliar.getInstancia();
        txfd_NombreDeActividad.setText(actividad.getNombre());
        dtp_FechaDeCierre.setValue(LocalDate.parse(actividad.getFechaDeCierre()));
        dtp_FechaDeInicio.setValue(LocalDate.parse(actividad.getFechaDeInicio()));
        txa_Descripcion.setText(actividad.getDescripcion());
        lbl_NumeroDeActividad.setText("Numero de actividad "+actividad.getNumeroActividad());
    }
    
    private boolean validarDatosActividad(){
        boolean resultado = true;
        Actividad actividad = new Actividad();
        resultado &= validarAuxiliar(()->actividad.setNombre(txfd_NombreDeActividad.getText()),lbl_ErrorActividad);
        resultado &= validarAuxiliar(()->actividad.setDescripcion(txa_Descripcion.getText()),lbl_ErrorDescripcion);
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
    
    private boolean validarFechasDeActividad(){
        boolean resultado = true;
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaInicioActividad = dtp_FechaDeInicio.getValue();
        LocalDate fechaCierreActividad = dtp_FechaDeCierre.getValue(); 
        if(fechaInicioActividad.isBefore(fechaCierreActividad)&&fechaCierreActividad.isAfter(fechaActual)||fechaCierreActividad.isEqual(fechaInicioActividad)){
            resultado = true;
        }else{
            resultado = false;
        }
        return resultado;
    }
    
    private boolean validarModificacionDeCampos(){
        boolean resultado = true;
        ActividadAuxiliar actividadAuxiliar = ActividadAuxiliar.getInstancia();
        Actividad actividad = obtenerDatosActividad();
        if(actividad.getDescripcion().equals(actividadAuxiliar.getDescripcion())&&actividad.getFechaDeCierre().equals(actividadAuxiliar.getFechaDeCierre())&&
                actividad.getFechaDeInicio().equals(actividadAuxiliar.getFechaDeInicio())&&actividad.getNombre().equals(actividadAuxiliar.getNombre())){
            resultado = true;
        }else{
            resultado = false;
        }
        return resultado;
    }
    
    private Actividad obtenerDatosActividad(){
        Actividad actividadAModificar = new Actividad();  
        ActividadAuxiliar actividadAuxiliar = ActividadAuxiliar.getInstancia();
        LocalDate fechaCierreNueva = dtp_FechaDeCierre.getValue();
        LocalDate fechaInicioNueva = dtp_FechaDeInicio.getValue();
        LocalDate fechaActual = LocalDate.now(); 
        String estadoActividad;
        if (fechaInicioNueva.isBefore(fechaActual) || fechaInicioNueva.isEqual(fechaActual)) {
            estadoActividad = "Activa";
        } else {
            estadoActividad = "Inactiva";
        }
        actividadAModificar.setNombre(txfd_NombreDeActividad.getText());
        actividadAModificar.setFechaDeCierre(fechaCierreNueva.toString());
        actividadAModificar.setFechaDeInicio(fechaInicioNueva.toString());
        actividadAModificar.setDescripcion(txa_Descripcion.getText());
        actividadAModificar.setIdActividad(actividadAuxiliar.getIdActividad());
        actividadAModificar.setEstado(estadoActividad);
        return actividadAModificar;
    }    
    
     public void realizarModificacionDeActividad(){
        if(validarDatosActividad()){
            if(validarFechasDeActividad()){
                if(!validarModificacionDeCampos()){
                    Actividad actividadAModificar = obtenerDatosActividad();
                    actividadAModificar.setIdColaboracion(ColaboracionAuxiliar.getInstancia().getIdColaboracion());
                    DAOActividadImplementacion daoActividad = new DAOActividadImplementacion();
                    if(!daoActividad.validarInexistenciaDeActividad(actividadAModificar)){
                        int resultadoModificacionDatos = daoActividad.modificarActividad(actividadAModificar);
                        int resultadoModificacionFechas = daoActividad.modificarFechaActividad(actividadAModificar);
                        if (resultadoModificacionDatos == -1 || resultadoModificacionFechas == -1) {
                            Alertas.mostrarMensajeErrorEnLaConexion();
                        } else if (resultadoModificacionDatos == 1 && resultadoModificacionFechas == 1) {
                            Alertas.mostrarMensajeDatosModificados();
                        }
                    }else{
                        Alertas.mostrarMensajeActividadExistente();
                    }
                }else{
                    Alertas.mostrarMensajeSinModificarDatos();
                }
            }else{
                Alertas.mostrarMensajeFechaInvalida();
            }
        }else{
            Alertas.mostrarMensajeDatosInvalidos();
        }
    }
}
