package interfazDeUsuario.Controladores;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import interfazDeUsuario.Alertas.Alertas;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import static javax.swing.text.StyleConstants.Background;
import logicaDeNegocio.ClasesAuxiliares.ActividadAuxiliar;
import logicaDeNegocio.ClasesAuxiliares.ColaboracionAuxiliar;
import logicaDeNegocio.DAOImplementacion.DAOActividadImplementacion;
import logicaDeNegocio.clases.Actividad;
import org.apache.log4j.Logger;

public class Ventana_ActividadesController implements Initializable {

    private static final Logger LOG=Logger.getLogger(Ventana_ActividadesController.class);
    private Stage escenario;
    @FXML
    private Button btn_Regresar;
    @FXML
    private AnchorPane anchor_Ventana;
    @FXML
    private Pane Superior;
    @FXML
    private Label Titulo;
    @FXML
    private TableView<Actividad> tableView_TablaActividades;
    @FXML
    private TableColumn<Actividad,Integer> column_No_Actividad;
    @FXML
    private TableColumn<Actividad,String> column_Nombre;
    @FXML
    private TableColumn<Actividad,String> column_Descripcion;
    @FXML
    private TableColumn<Actividad,String> column_FechaDeInicio;
    @FXML
    private TableColumn<Actividad,String> column_FechaDeCierre;
    @FXML
    private TableColumn<Actividad,String> column_EstadoDeActividad;
    @FXML
    private TableColumn<Actividad, Void> column_Modificar;
    @FXML
    private TableColumn<Actividad, Void> column_Evidencia;
    @FXML
    private Pane pane_Inferior;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        column_No_Actividad.setCellValueFactory(new PropertyValueFactory<>("numeroActividad"));
        column_Nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        column_Descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        column_FechaDeInicio.setCellValueFactory(new PropertyValueFactory<>("fechaDeInicio"));
        column_FechaDeCierre.setCellValueFactory(new PropertyValueFactory<>("fechaDeCierre"));
        column_EstadoDeActividad.setCellValueFactory(new PropertyValueFactory<>("estado"));
        try{
            List<Actividad> actividadesDeColaboracion = obtenerActividades();
            tableView_TablaActividades.getItems().addAll(actividadesDeColaboracion);
        }catch(IllegalArgumentException excepcion){
            Alertas.mostrarMensajeErrorAlObtenerDatos();
        }
        asignarBotonDeModificacion();
        asignarBotonEvidencias();
        btn_Regresar.setOnAction(Event ->{
            regresarVentanaColaboracionActiva();
        });
    }
    
    public List<Actividad> obtenerActividades(){
        List<Actividad> actividadesObtenidas = new ArrayList();
        ColaboracionAuxiliar colaboracion = ColaboracionAuxiliar.getInstancia();
        DAOActividadImplementacion daoActividad = new DAOActividadImplementacion();
        actividadesObtenidas = daoActividad.obtenerActividades(colaboracion.getIdColaboracion());
        return actividadesObtenidas;
    }
    
    public void regresarVentanaColaboracionActiva(){
        String rutaVentanaFXML="/interfazDeUsuario/Ventana_ColaboracionActiva.fxml";
        desplegarVentanaCorrespondiente(rutaVentanaFXML);  
    }
    
    public void cerrarVentana(){
        escenario = (Stage)anchor_Ventana.getScene().getWindow();
        escenario.close();
    }
    
    public void asignarBotonDeModificacion(){
        ColaboracionAuxiliar colaboracion = ColaboracionAuxiliar.getInstancia();
        String estadoColaboracion = colaboracion.getEstadoColaboracion();
        if(estadoColaboracion.equals("Activa")){
            Callback<TableColumn<Actividad, Void>, TableCell<Actividad, Void>> frabricaDeCelda = (final TableColumn<Actividad, Void> param) -> {
                final TableCell<Actividad, Void> cell = new TableCell<Actividad, Void>() {                
                    private final Button btn_ModificarActividad = new Button();{
                        btn_ModificarActividad.setText("Modificar actividad");
                        btn_ModificarActividad.setOnAction((ActionEvent event) -> {
                            Actividad actividadSeleccionada = getTableView().getItems().get(getIndex());
                            ActividadAuxiliar.setInstancia(actividadSeleccionada);
                            String ruta = "/interfazDeUsuario/Ventana_ModificarActividad.fxml";
                            desplegarVentanaCorrespondiente(ruta);                     
                        });
                    }                
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        }else{ 
                            setGraphic(btn_ModificarActividad);
                        }
                    }
                };
            return cell;
            };
            column_Modificar.setCellFactory(frabricaDeCelda);
        }else{
            column_Modificar.setVisible(false);
        }
    }
    
    public void asignarBotonEvidencias(){
        Callback<TableColumn<Actividad, Void>, TableCell<Actividad, Void>> frabricaDeCelda = (final TableColumn<Actividad, Void> param) -> {
            final TableCell<Actividad, Void> cell = new TableCell<Actividad, Void>() {
                private final Button btn_Evidencias = new Button();

                {
                    btn_Evidencias.setText("Evidencias");
                    btn_Evidencias.setOnAction((ActionEvent event) -> {
                        Actividad actividadSeleccionada = getTableView().getItems().get(getIndex());
                        ActividadAuxiliar.setInstancia(actividadSeleccionada);
                        String ruta = "/interfazDeUsuario/Ventana_Evidencias.fxml";
                        desplegarVentanaCorrespondiente(ruta);
                    });
                }

                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(btn_Evidencias);
                    }
                }
            };
            return cell;
        };
        column_Evidencia.setCellFactory(frabricaDeCelda);
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
