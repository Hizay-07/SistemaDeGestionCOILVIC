package interfazDeUsuario.Controladores;

import interfazDeUsuario.Alertas.Alertas;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import logicaDeNegocio.ClasesAuxiliares.ActividadAuxiliar;
import logicaDeNegocio.ClasesAuxiliares.ColaboracionAuxiliar;
import logicaDeNegocio.ClasesAuxiliares.EvidenciaAuxiliar;
import logicaDeNegocio.DAOImplementacion.DAOEvidenciaImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOUsuarioImplementacion;
import logicaDeNegocio.clases.Evidencia;
import logicaDeNegocio.clases.ProfesorSingleton;
import logicaDeNegocio.clases.UsuarioSingleton;
import org.apache.log4j.Logger;


public class Ventana_EvidenciasColaboracionGlobalControlador implements Initializable {
    
    private static final Logger LOG=Logger.getLogger(Ventana_EvidenciasControlador.class);
    private Stage escenario;
    @FXML
    private AnchorPane anchor_Ventana;
    @FXML
    private Label lbl_NombreActividad;
    @FXML
    private Label lbl_FechaDeInicio;
    @FXML
    private Label lbl_FechaDeCierre;
    @FXML
    private TableView<Evidencia> tableView_TablaEvidencias;
    @FXML
    private TableColumn<Evidencia,String> column_NombreEvidencia;
    @FXML
    private TableColumn<Evidencia,Void> column_VisualizarEvidencia;    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ActividadAuxiliar actividadDeEvidencia = ActividadAuxiliar.getInstancia();
        ColaboracionAuxiliar colaboracionAuxiliar = ColaboracionAuxiliar.getInstancia();
        column_NombreEvidencia.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        try{
            List<Evidencia> evidenciasObtenidas = obtenerEvidenciasDeActividad();
            tableView_TablaEvidencias.getItems().addAll(evidenciasObtenidas);
        }catch(IllegalArgumentException excepcion){
            Alertas.mostrarMensajeErrorAlObtenerDatos();
        }
        if(actividadDeEvidencia.getEstado().equals("Inactiva")){            
            Alertas.mostrarMensajeActividadInactiva();
        }
        inicializarDatosDeActividadDeEvidencia();
        asignarBotonDeVisualizar();
    }    
    
    private List<Evidencia> obtenerEvidenciasDeActividad(){
        List<Evidencia> listaDeEvidencias = new ArrayList();
        DAOEvidenciaImplementacion daoEvidencia = new DAOEvidenciaImplementacion();
        ActividadAuxiliar actividadAuxiliar = ActividadAuxiliar.getInstancia();
        listaDeEvidencias = daoEvidencia.obtenerEvidenciasDeActividad(actividadAuxiliar.getIdActividad());
        return listaDeEvidencias;
    }

    private void inicializarDatosDeActividadDeEvidencia(){
        ActividadAuxiliar actividadDatos = ActividadAuxiliar.getInstancia();
        lbl_NombreActividad.setText(actividadDatos.getNombre());
        lbl_FechaDeInicio.setText(actividadDatos.getFechaDeInicio());
        lbl_FechaDeCierre.setText(actividadDatos.getFechaDeCierre());
    }
    
     private void asignarBotonDeVisualizar(){
         Callback<TableColumn<Evidencia, Void>, TableCell<Evidencia, Void>> frabricaDeCelda = (final TableColumn<Evidencia, Void> param) -> {
            final TableCell<Evidencia, Void> cell = new TableCell<Evidencia, Void>() {
                private final Button btn_VisualizarEvidencia = new Button();{
                    btn_VisualizarEvidencia.setText("Visualizar");
                    btn_VisualizarEvidencia.setOnAction((ActionEvent event) -> {
                        Evidencia evidenciaSeleccionada = getTableView().getItems().get(getIndex());
                        File archivoEvidencia = new File(evidenciaSeleccionada.getRutaEvidencia());
                        if(archivoEvidencia.exists() && archivoEvidencia.isFile()){
                            try{
                                Desktop.getDesktop().open(archivoEvidencia);
                            }catch(IOException excepcion){
                                LOG.error(excepcion);
                                Alertas.mostrarMensajeErrorAlObtenerDatos();
                            }
                        }else{
                            Alertas.mostrarMensajeErrorAlAccederAlaCarpeta();
                        }
                    });
                }
                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    }else{
                        setGraphic(btn_VisualizarEvidencia);
                    }
                }
            };
            return cell;
        };
        column_VisualizarEvidencia.setCellFactory(frabricaDeCelda);
    }
    
    public void regresarVentanaActividades(){
        String rutaVentanaFXML="/interfazDeUsuario/Ventana_ActividadesColaboracion.fxml";
        desplegarVentanaCorrespondiente(rutaVentanaFXML);  
    }
    
    private void cerrarVentana(){
        escenario = (Stage)anchor_Ventana.getScene().getWindow();
        escenario.close();
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
}
