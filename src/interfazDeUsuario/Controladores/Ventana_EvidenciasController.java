package interfazDeUsuario.Controladores;

import interfazDeUsuario.Alertas.Alertas;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.awt.Desktop;
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
import logicaDeNegocio.clases.Actividad;
import logicaDeNegocio.clases.Evidencia;
import org.apache.log4j.Logger;


public class Ventana_EvidenciasController implements Initializable {
    
    private static final Logger LOG=Logger.getLogger(Ventana_EvidenciasController.class);
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
    @FXML
    private TableColumn<Evidencia,Void> column_ModificarEvidencia;
    @FXML
    private Button btn_SubirEvidencia;
    
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
            btn_SubirEvidencia.setVisible(false);
            Alertas.mostrarMensajeActividadInactiva();
        }
        asignarBotonDeVisualizar();
        if(colaboracionAuxiliar.getEstadoColaboracion().equals("Cerrada")||colaboracionAuxiliar.getEstadoColaboracion().equals("Finalizada")||actividadDeEvidencia.getEstado().equals("Inactiva")){
            column_ModificarEvidencia.setVisible(false);
        }else{
            asignarBotonDeModificar();
        }
        inicializarDatosDeActividadDeEvidencia();
    }
    
    public List<Evidencia> obtenerEvidenciasDeActividad(){
        List<Evidencia> listaDeEvidencias = new ArrayList();
        DAOEvidenciaImplementacion daoEvidencia = new DAOEvidenciaImplementacion();
        ActividadAuxiliar actividadAuxiliar = ActividadAuxiliar.getInstancia();
        listaDeEvidencias = daoEvidencia.obtenerEvidenciasDeActividad(actividadAuxiliar.getIdActividad());
        return listaDeEvidencias;
    }

    public void inicializarDatosDeActividadDeEvidencia(){
        ActividadAuxiliar actividadDatos = ActividadAuxiliar.getInstancia();
        lbl_NombreActividad.setText(actividadDatos.getNombre());
        lbl_FechaDeInicio.setText(actividadDatos.getFechaDeInicio());
        lbl_FechaDeCierre.setText(actividadDatos.getFechaDeCierre());
    }
    
    public void asignarBotonDeVisualizar(){
         Callback<TableColumn<Evidencia, Void>, TableCell<Evidencia, Void>> frabricaDeCelda = (final TableColumn<Evidencia, Void> param) -> {
            final TableCell<Evidencia, Void> cell = new TableCell<Evidencia, Void>() {
                private final Button btn_VisualizarEvidencia = new Button();{
                    btn_VisualizarEvidencia.setText("Visualizar Evidencia");
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
    
    public void asignarBotonDeModificar(){
        Callback<TableColumn<Evidencia, Void>, TableCell<Evidencia, Void>> frabricaDeCelda = (final TableColumn<Evidencia, Void> param) -> {
            final TableCell<Evidencia, Void> cell = new TableCell<Evidencia, Void>() {
                private final Button btn_ModificarEvidencia = new Button();{
                    btn_ModificarEvidencia.setText("Modificar Entrega");
                    btn_ModificarEvidencia.setOnAction((ActionEvent event) -> {
                        Evidencia evidenciaSeleccionada = getTableView().getItems().get(getIndex());
                        EvidenciaAuxiliar.setInstancia(evidenciaSeleccionada);
                        String ruta = "/interfazDeUsuario/Ventana_ModificarEvidencia.fxml";
                        desplegarVentanaCorrespondiente(ruta);
                    });
                }
                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    }else{
                        setGraphic(btn_ModificarEvidencia);
                    }
                }
            };
            return cell;
        };
        column_ModificarEvidencia.setCellFactory(frabricaDeCelda);
    }
    
    public void subirEvidencia(){
       String rutaVentanaFXML="/interfazDeUsuario/Ventana_RegistrarEvidencia.fxml";
        desplegarVentanaCorrespondiente(rutaVentanaFXML); 
    }
    
    public void regresarVentanaActividades(){
        String rutaVentanaFXML="/interfazDeUsuario/Ventana_ActividadesColaboracionActiva.fxml";
        desplegarVentanaCorrespondiente(rutaVentanaFXML);  
    }
    
    public void cerrarVentana(){
        escenario = (Stage)anchor_Ventana.getScene().getWindow();
        escenario.close();
    }
    
    public void desplegarVentanaCorrespondiente(String rutaVentanaFXML){
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
}
