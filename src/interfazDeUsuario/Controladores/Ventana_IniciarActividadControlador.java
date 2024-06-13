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
import javafx.scene.control.DateCell;
import javafx.scene.control.Label;
import javafx.util.Callback;
import logicaDeNegocio.DAOImplementacion.DAOUsuarioImplementacion;
import logicaDeNegocio.clases.ProfesorSingleton;
import logicaDeNegocio.clases.PropuestaColaboracion;
import logicaDeNegocio.clases.UsuarioSingleton;

public class Ventana_IniciarActividadControlador implements Initializable {

    private static final Logger LOG=Logger.getLogger(Ventana_ColaboracionActivaControlador.class);
    
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
    @FXML
    private Label lbl_ErrorNumeroActividad;
    @FXML
    private Label lbl_ErrorNombreActividad;
    @FXML
    private Label lbl_ErrorDescripcionActividad;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        limitarFechasDePeriodoActividad();
        ocultarLabelErrores();
        limitarTextFields();
    }    
    
    public void cerrarVentana(){
        Stage escenario = (Stage) anchor_Ventana.getScene().getWindow();
        escenario.close();
    }
    
    private void ocultarLabelErrores(){
        lbl_ErrorNumeroActividad.setVisible(false);
        lbl_ErrorNombreActividad.setVisible(false);
        lbl_ErrorDescripcionActividad.setVisible(false);
    }
    
    public void regresarMenuPrincipal(){
        String rutaVentanaFXML="/interfazDeUsuario/Ventana_ColaboracionActiva.fxml";
        desplegarVentanaCorrespondiente(rutaVentanaFXML);
    }
    
    private void limitarTextFields(){
        ComponentesDeVentanaControlador.limitarTextfield(txfd_NumeroDeActividad, 2);
        ComponentesDeVentanaControlador.limitarTextfield(txfd_NombreDeActividad, 150);
        ComponentesDeVentanaControlador.limitarTextArea(txa_Descripcion, 255);
    }
    
    private void limitarFechasDePeriodoActividad(){
        ColaboracionAuxiliar colaboracionActual = ColaboracionAuxiliar.getInstancia();
        PropuestaColaboracion propuesta = colaboracionActual.getPropuestaColaboracion();
        LocalDate fechaMaxima = LocalDate.parse(propuesta.getFechaCierre());
        LocalDate fechaMinima = LocalDate.parse(propuesta.getFechaInicio());
        dtp_FechaDeInicio.setDayCellFactory(createDayCellFactory(fechaMinima, fechaMaxima));
        dtp_FechaDeInicio.setValue(fechaMinima);
        dtp_FechaDeCierre.setDayCellFactory(createDayCellFactory(fechaMinima, fechaMaxima));
        dtp_FechaDeCierre.setValue(fechaMaxima);
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
    
    private boolean validarDatosActividad(){
        boolean resultado = true;
        Actividad actividad = new Actividad();
        resultado &= validarAuxiliar(()->actividad.setNumeroActividad(Integer.parseInt(txfd_NumeroDeActividad.getText())),lbl_ErrorNumeroActividad);
        resultado &= validarAuxiliar(()->actividad.setNombre(txfd_NombreDeActividad.getText()),lbl_ErrorNombreActividad);
        resultado &= validarAuxiliar(()->actividad.setDescripcion(txa_Descripcion.getText()),lbl_ErrorDescripcionActividad);
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
    
    private Actividad obtenerDatosActividad(){
        Actividad nuevaActividad = new Actividad();
        ColaboracionAuxiliar colaboracionActual = ColaboracionAuxiliar.getInstancia();
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaInicioActividad = dtp_FechaDeInicio.getValue(); 
        String estadoActividad;
        if (fechaActual.isAfter(fechaInicioActividad) || fechaActual.isEqual(fechaInicioActividad)) {
            estadoActividad = "Activa";
        } else {
            estadoActividad = "Inactiva";
        }
        nuevaActividad.setNumeroActividad(Integer.parseInt(txfd_NumeroDeActividad.getText()));
        nuevaActividad.setNombre(txfd_NombreDeActividad.getText());
        nuevaActividad.setFechaDeInicio(dtp_FechaDeInicio.getValue().toString());
        nuevaActividad.setFechaDeCierre(dtp_FechaDeCierre.getValue().toString());
        nuevaActividad.setDescripcion(txa_Descripcion.getText());
        nuevaActividad.setIdColaboracion(colaboracionActual.getIdColaboracion());
        nuevaActividad.setEstado(estadoActividad);
        return nuevaActividad;
    }
    
    public void realizarRegistroDeActividad(ActionEvent event){
        ocultarLabelErrores();
        if(obtenerResultadoValidacionConexion()){
            if(validarDatosActividad()){
                if(validarFechasDeActividad()){
                    Actividad nuevaActividad = obtenerDatosActividad();
                    nuevaActividad.setIdColaboracion(ColaboracionAuxiliar.getInstancia().getIdColaboracion());
                    DAOActividadImplementacion daoActividad = new DAOActividadImplementacion();
                    if(!daoActividad.validarInexistenciaDeActividad(nuevaActividad)){
                        int resultadoRegistro = daoActividad.registrarActividad(nuevaActividad);
                        if (resultadoRegistro == 1) {
                            Alertas.mostrarMensajeDatosIngresados();
                        } else if (resultadoRegistro == -1) {
                            Alertas.mostrarMensajeErrorEnLaConexion();
                            regresarMenuPrincipal();
                        }
                    }else{
                        Alertas.mostrarMensajeActividadExistente();
                    }
                }else{
                    Alertas.mostrarMensajeFechaInvalida();
                }  
            }else{
                Alertas.mostrarMensajeDatosInvalidos();
            }
        }else{
            salirAlInicioDeSesion();
        }
    }
    
}
