package interfazDeUsuario.Controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import interfazDeUsuario.Alertas.Alertas;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import logicaDeNegocio.ClasesAuxiliares.ActividadAuxiliar;
import logicaDeNegocio.ClasesAuxiliares.ColaboracionAuxiliar;
import logicaDeNegocio.DAOImplementacion.DAOActividadImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOUsuarioImplementacion;
import logicaDeNegocio.clases.Actividad;
import logicaDeNegocio.clases.ProfesorSingleton;
import logicaDeNegocio.clases.PropuestaColaboracion;
import logicaDeNegocio.clases.UsuarioSingleton;
import org.apache.log4j.Logger;

public class Ventana_ActividadesColaboracionActivaControlador implements Initializable {

    private static final Logger LOG=Logger.getLogger(Ventana_ActividadesColaboracionActivaControlador.class);
    private Stage escenario;
    @FXML
    private Button btn_Regresar;
    @FXML
    private AnchorPane anchor_Ventana;
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
    
    private List<Actividad> obtenerActividades(){
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
    
    private void cerrarVentana(){
        escenario = (Stage)anchor_Ventana.getScene().getWindow();
        escenario.close();
    }
    
    private void asignarBotonDeModificacion(){
        ColaboracionAuxiliar colaboracion = ColaboracionAuxiliar.getInstancia();
        String estadoColaboracion = colaboracion.getEstadoColaboracion();
        if(estadoColaboracion.equals("Activa")){
            Callback<TableColumn<Actividad, Void>, TableCell<Actividad, Void>> frabricaDeCelda = (final TableColumn<Actividad, Void> param) -> {
                final TableCell<Actividad, Void> cell = new TableCell<Actividad, Void>() {                
                    private final Button btn_ModificarActividad = new Button();{
                        btn_ModificarActividad.setText("Modificar actividad");
                        btn_ModificarActividad.setOnAction((ActionEvent event) -> {
                            if(validarFechasDeColaboracion()){
                                Actividad actividadSeleccionada = getTableView().getItems().get(getIndex());
                                ActividadAuxiliar.setInstancia(actividadSeleccionada);
                                String ruta = "/interfazDeUsuario/Ventana_ModificarActividad.fxml";
                                desplegarVentanaCorrespondiente(ruta);   
                            }else{
                                Alertas.mostrarMensajeFechaDeCierreColaboracion();
                            }              
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
    
    private void asignarBotonEvidencias(){
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
    
    private boolean validarFechasDeColaboracion(){
        boolean resultadoValidacion;
        PropuestaColaboracion propuesta = ColaboracionAuxiliar.getInstancia().getPropuestaColaboracion();
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaCierreColaboracion = LocalDate.parse(propuesta.getFechaCierre());
        if(fechaActual.isEqual(fechaCierreColaboracion)||fechaActual.isAfter(fechaCierreColaboracion)){
            resultadoValidacion=false;
        }else{
            resultadoValidacion=true;
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
     
    private void salirAlInicioDeSesion(){
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
