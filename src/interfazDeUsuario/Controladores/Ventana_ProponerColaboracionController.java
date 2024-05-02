package interfazDeUsuario.Controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import interfazDeUsuario.Alertas.Alertas;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logicaDeNegocio.DAOImplementacion.DAOEmisionPropuestaImplementacion;
import logicaDeNegocio.clases.PropuestaColaboracion;
import logicaDeNegocio.DAOImplementacion.DAOPropuestaColaboracionImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOTipoColaboracionImplementacion;
import logicaDeNegocio.clases.EmisionPropuesta;
import logicaDeNegocio.clases.ProfesorSingleton;
import logicaDeNegocio.clases.TipoColaboracion;
import org.apache.log4j.Logger;

public class Ventana_ProponerColaboracionController implements Initializable {
    private static final Logger LOG=Logger.getLogger(Ventana_ProponerColaboracionController.class);    
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
    private AnchorPane anchor_ventana;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarTiposColaboracion();        
        btn_Aceptar.setOnAction(event -> {
            registrarPropuestaColaboracion();
        });

        btn_Cancelar.setOnAction(event -> {
            Stage escenario = (Stage) anchor_ventana.getScene().getWindow();
            escenario.close();
        });
    }

    private void cargarTiposColaboracion() {
        DAOTipoColaboracionImplementacion daoTipoColaboracion=new DAOTipoColaboracionImplementacion();
        List<TipoColaboracion> tiposColaboracion=daoTipoColaboracion.consultarTiposDeColaboracion();
        ObservableList<String> tipos = FXCollections.observableArrayList();
        for(TipoColaboracion tipoColaboracion : tiposColaboracion){
            tipos.add(tipoColaboracion.getTipo());
        }
        cmb_TipoColaboracion.setItems(tipos);        
    }
    
    public void registrarPropuestaColaboracion(){
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
                                                
            //Revisar estado en maquina de estado
            propuestaColaboracion.setEstadoPropuesta("Registrada");            
            daoPropuestaColaboracion.registrarPropuestaColaboracion(propuestaColaboracion);
            registrarEmisionPropuesta(propuestaColaboracion);
        }catch(IllegalArgumentException excepcion){ 
            Alertas.mostrarMensajeDatosInvalidos();  
            LOG.info(excepcion);
        }
        
    }
    
    public void registrarEmisionPropuesta(PropuestaColaboracion propuesta){
        ProfesorSingleton profesor = ProfesorSingleton.getInstancia();
        int idProfesor=profesor.getIdProfesor();
        int idPropuesta=propuesta.getIdPropuestaColaboracion();
        EmisionPropuesta emisionPropuesta=new EmisionPropuesta();
        emisionPropuesta.setIdProfesor(idProfesor);
        emisionPropuesta.setIdPropuestaColaboracion(idPropuesta);
        DAOEmisionPropuestaImplementacion daoEmisionPropuesta=new DAOEmisionPropuestaImplementacion();
        daoEmisionPropuesta.registrarEmisionPropuesta(emisionPropuesta);        
    }
    
    
    /*

    private void registrarPropuestaColaboracion() {
        //String fechaInicio = txfd_FechaInicio.getText();
        //String fechaDeInicio = txfd_FechaDeInicio.getText();
        String experienciaEducativa = txfd_ExperienciaEducativa.getText();
        String programaEducativo = txfd_ProgramaEducativo.getText();
        String objetivoGeneral = txfd_ObjetivoGeneral.getText();
        String tipoColaboracion = cmb_TipoColaboracion.getValue();

        /*if (fechaInicio.isEmpty() || fechaDeInicio.isEmpty() || experienciaEducativa.isEmpty() ||
                programaEducativo.isEmpty() || objetivoGeneral.isEmpty() || tipoColaboracion == null) {
            Alertas.mostrarMensajeDatosIncompletos();
            return;
        }

        PropuestaColaboracion propuestaColaboracion = new PropuestaColaboracion();
        //propuestaColaboracion.setFechaInicio(fechaInicio);
        //propuestaColaboracion.setFechaCierre(fechaDeInicio);
        propuestaColaboracion.setExperienciaEducativa(experienciaEducativa);
        propuestaColaboracion.setProgramaEducativoEstudiantil(programaEducativo);
        propuestaColaboracion.setObjetivo(objetivoGeneral);

        DAOPropuestaColaboracionImplementacion daoPropuestaColaboracion = new DAOPropuestaColaboracionImplementacion();
        int filasAfectadas = daoPropuestaColaboracion.registrarPropuestaColaboracion(propuestaColaboracion);

        if (filasAfectadas > 0) {
            Alertas.mostrarMensajeDatosIngresados();
        } else {
            Alertas.mostrarMensajeDatosIngresados();

        }
    }
    */

}
