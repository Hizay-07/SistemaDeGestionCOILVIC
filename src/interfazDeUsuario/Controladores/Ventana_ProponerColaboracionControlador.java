package interfazDeUsuario.Controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import interfazDeUsuario.Alertas.Alertas;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logicaDeNegocio.DAOImplementacion.DAOEmisionPropuestaImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOProfesorImplementacion;
import logicaDeNegocio.clases.PropuestaColaboracion;
import logicaDeNegocio.DAOImplementacion.DAOPropuestaColaboracionImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOTipoColaboracionImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOUsuarioImplementacion;
import logicaDeNegocio.clases.EmisionPropuesta;
import logicaDeNegocio.clases.ProfesorSingleton;
import logicaDeNegocio.clases.TipoColaboracion;
import logicaDeNegocio.enums.EnumProfesor;
import logicaDeNegocio.enums.EnumPropuestaColaboracion;
import logicaDeNegocio.clases.UsuarioSingleton;
import org.apache.log4j.Logger;

public class Ventana_ProponerColaboracionControlador implements Initializable {
    private static final Logger LOG=Logger.getLogger(Ventana_ProponerColaboracionControlador.class);    
    @FXML
    private TextField txfd_ExperienciaEducativa;
    @FXML
    private TextField txfd_ProgramaEducativo;
    @FXML
    private TextField txfd_ObjetivoGeneral;
    @FXML
    private TextField txfd_Idioma;    
    @FXML
    private ComboBox<String> cmb_TipoColaboracion;  
    @FXML
    private DatePicker dtp_FechaCierre;
    @FXML
    private DatePicker dtp_FechaInicio;
    @FXML
    private Button btn_Aceptar;
    @FXML
    private Button btn_Cancelar;
    @FXML
    private AnchorPane anchor_VentanaProponerColaboracion;    
    private Stage stage_ventana;   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarTiposColaboracion();        
        btn_Aceptar.setOnAction(event -> {
            registrarPropuestaColaboracion();
        });

        btn_Cancelar.setOnAction(event -> {
            salirDeLaVentana();
        });
    }
    
    public void cerrarVentana(){
        stage_ventana=(Stage) anchor_VentanaProponerColaboracion.getScene().getWindow();
        stage_ventana.close();
    }
        
    private void cargarTiposColaboracion() {
        DAOTipoColaboracionImplementacion daoTipoColaboracion=new DAOTipoColaboracionImplementacion();
        List<TipoColaboracion> tiposColaboracion=daoTipoColaboracion.consultarTiposDeColaboracion();
        if(!tiposColaboracion.isEmpty()){
            ObservableList<String> tipos = FXCollections.observableArrayList();
            for(TipoColaboracion tipoColaboracion : tiposColaboracion){
                tipos.add(tipoColaboracion.getTipo());
            }
            cmb_TipoColaboracion.setItems(tipos);        
        }else{
            Alertas.mostrarMensajeErrorEnLaConexion();
            salirDeLaVentana();
        }
                
    }
    
    public void registrarPropuestaColaboracion(){
        LocalDate fechaInicio=dtp_FechaInicio.getValue();
        LocalDate fechaCierre=dtp_FechaCierre.getValue();
        if(validarFechas(fechaInicio,fechaCierre)){
            PropuestaColaboracion propuestaColaboracion=new PropuestaColaboracion();
            TipoColaboracion tipoColaboracion=new TipoColaboracion();        
            DAOTipoColaboracionImplementacion daoTipoColaboracion=new DAOTipoColaboracionImplementacion();
            String tipo=(String) cmb_TipoColaboracion.getSelectionModel().getSelectedItem();
            DAOPropuestaColaboracionImplementacion daoPropuestaColaboracion=new DAOPropuestaColaboracionImplementacion();
            try{            
                tipoColaboracion.setTipo(tipo);
                tipoColaboracion.setIdTipoColaboracion(daoTipoColaboracion.consultarIdTipoColaboracionPorTipo(tipo));
                propuestaColaboracion.setExperienciaEducativa(txfd_ExperienciaEducativa.getText());
                propuestaColaboracion.setProgramaEducativoEstudiantil(txfd_ProgramaEducativo.getText());
                propuestaColaboracion.setObjetivo(txfd_ObjetivoGeneral.getText());
                propuestaColaboracion.setTipoColaboracion(tipoColaboracion);
                propuestaColaboracion.setIdioma(txfd_Idioma.getText());
                propuestaColaboracion.setFechaInicio(dtp_FechaInicio.getValue().toString());
                propuestaColaboracion.setFechaCierre(dtp_FechaCierre.getValue().toString());                                                                        
                propuestaColaboracion.setEstadoPropuesta(EnumPropuestaColaboracion.Registrada.toString());            
                int idPropuesta=daoPropuestaColaboracion.registrarPropuestaColaboracion(propuestaColaboracion);
                if(idPropuesta!=-1){
                    registrarEmisionPropuesta(idPropuesta);            
                }else{
                    Alertas.mostrarMensajeErrorEnLaConexion();
                }            
            }catch(IllegalArgumentException excepcion){ 
                Alertas.mostrarMensajeDatosInvalidos();  
                LOG.info(excepcion);
            }                                
        }else{
            Alertas.mostrarFechasInvalidas();
        }                        
    }
    
    public void registrarEmisionPropuesta(int idPropuesta){
        ProfesorSingleton profesor = ProfesorSingleton.getInstancia();
        int idProfesor=profesor.getIdProfesor();        
        EmisionPropuesta emisionPropuesta=new EmisionPropuesta();
        emisionPropuesta.setIdProfesor(idProfesor);
        emisionPropuesta.setIdPropuestaColaboracion(idPropuesta);
        emisionPropuesta.setFechaEmision(obtenerFechaActual());
        DAOEmisionPropuestaImplementacion daoEmisionPropuesta=new DAOEmisionPropuestaImplementacion();
        daoEmisionPropuesta.registrarEmisionPropuesta(emisionPropuesta);     
        DAOProfesorImplementacion daoProfesor=new DAOProfesorImplementacion();
        daoProfesor.cambiarEstadoProfesor(idProfesor, EnumProfesor.Esperando.toString());
        Alertas.mostrarRegistroPropuesta();
        salirDeLaVentana();
    }    
    
    public boolean validarFechas(LocalDate fechaInicio,LocalDate fechaCierre){
        boolean validacion=false;
        LocalDate fechaActual = LocalDate.now();
        if(fechaInicio.isBefore(fechaCierre)&&fechaInicio.isAfter(fechaActual)){
            int mesInicio=fechaInicio.getMonthValue();
            int mesCierre=fechaCierre.getMonthValue();        
            if(validarMes(mesInicio)&&validarMes(mesCierre)&&mesInicio+1<mesCierre){
                int anioInicio=fechaInicio.getYear();
                int anioCierre=fechaCierre.getYear();
                if(anioInicio==anioCierre){
                    validacion=true;                
                }
            }        
        }            
        return validacion;                                                        
    }
    
    public boolean validarMes(int mes){
        return mes!=1&&mes!=6&&mes!=7&&mes!=12;    
    }
    
    public String obtenerFechaActual(){
        LocalDate fechaActual = LocalDate.now();                
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaActualFormateada = fechaActual.format(formatter);
        return fechaActualFormateada;
    }
    
    public void salirDeLaVentana(){
        if(validarConexionEstable()){
            String rutaVentanaFXML = null;
            try{
                rutaVentanaFXML = "/interfazDeUsuario/Ventana_MenuPrincipalProfesor.fxml";
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
    
    public boolean validarConexionEstable(){
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

}
