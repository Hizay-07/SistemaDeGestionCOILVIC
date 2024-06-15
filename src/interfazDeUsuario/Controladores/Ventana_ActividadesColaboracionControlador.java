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
import logicaDeNegocio.clases.UsuarioSingleton;
import org.apache.log4j.Logger;

public class Ventana_ActividadesColaboracionControlador implements Initializable {
    
    private static final Logger LOG=Logger.getLogger(Ventana_ActividadesColaboracionControlador.class);
    private Stage escenario;
    @FXML
    private AnchorPane anchor_Ventana;
    @FXML
    private TableView<Actividad> tableView_Actividades;
    @FXML
    private TableColumn<Actividad, String> column_NoActividad;
    @FXML
    private TableColumn<Actividad, String> column_Nombre;
    @FXML
    private TableColumn<Actividad, String> column_Descripcion;
    @FXML
    private TableColumn<Actividad, String> column_Inicio;
    @FXML
    private TableColumn<Actividad, String> column_Cierre;
    @FXML
    private TableColumn<Actividad, String> column_Estado;
    @FXML
    private TableColumn<Actividad, Void> column_Evidencias;
    @FXML
    private Button btn_Regresar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        column_NoActividad.setCellValueFactory(new PropertyValueFactory<>("numeroActividad"));
        column_Nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        column_Descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        column_Inicio.setCellValueFactory(new PropertyValueFactory<>("fechaDeInicio"));
        column_Cierre.setCellValueFactory(new PropertyValueFactory<>("fechaDeCierre"));
        column_Estado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        String tipoDeUsuario = UsuarioSingleton.getInstancia().getTipoDeUsuario();
        if(tipoDeUsuario.equals("Administrativo")){
            asignarBotonEvidencias();
        }else{
            column_Evidencias.setVisible(false);
        }
        try{
            List<Actividad> actividadesDeColaboracion = obtenerActividades();
            tableView_Actividades.getItems().addAll(actividadesDeColaboracion);
            if(actividadesDeColaboracion.isEmpty()){
                Alertas.mostrarMensajeSinResultadosEncontrados("No se han encontrado actividades registradas");
            }else if(actividadesDeColaboracion.get(0).getNombre().equals("Excepcion")){
                Alertas.mostrarMensajeErrorEnLaConexion();
            }else{
                tableView_Actividades.getItems().addAll(actividadesDeColaboracion);
            }  
        }catch(IllegalArgumentException excepcion){
            LOG.info(excepcion);
            Alertas.mostrarMensajeErrorAlObtenerDatos();
        }
        btn_Regresar.setOnAction(Event ->{
            regresarVentanaColaboraciones();
        });
    }
    
    private List<Actividad> obtenerActividades(){
        List<Actividad> actividadesObtenidas = new ArrayList();
        ColaboracionAuxiliar colaboracion = ColaboracionAuxiliar.getInstancia();
        DAOActividadImplementacion daoActividad = new DAOActividadImplementacion();
        actividadesObtenidas = daoActividad.obtenerActividades(colaboracion.getIdColaboracion());
        return actividadesObtenidas;
    }
    
    public void regresarVentanaColaboraciones(){
        String rutaVentanaFXML = "/interfazDeUsuario/Ventana_DetallesDeColaboracion.fxml";
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
    
    private void asignarBotonEvidencias(){
        Callback<TableColumn<Actividad, Void>, TableCell<Actividad, Void>> fabricaDeCelda = (final TableColumn<Actividad, Void> param) -> {
            final TableCell<Actividad, Void> celda = new TableCell<Actividad, Void>() {
                private final Button btn_Evidencias = new Button();{
                    btn_Evidencias.setText("Evidencias");
                    btn_Evidencias.setOnAction((ActionEvent event) -> {
                        Actividad actividadSeleccionada = getTableView().getItems().get(getIndex());
                        ActividadAuxiliar.setInstancia(actividadSeleccionada);
                        String ruta = "/interfazDeUsuario/Ventana_EvidenciasColaboracionGlobal.fxml";
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
            return celda;
        };
        column_Evidencias.setCellFactory(fabricaDeCelda);
    }
    
    public void desplegarVentanaCorrespondiente(String rutaFxml){
        if(obtenerResultadoValidacionConexion()){
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
        }else{
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
