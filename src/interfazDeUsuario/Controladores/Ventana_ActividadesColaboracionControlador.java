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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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
    private Button btn_Regresar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        column_NoActividad.setCellValueFactory(new PropertyValueFactory<>("numeroActividad"));
        column_Nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        column_Descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        column_Inicio.setCellValueFactory(new PropertyValueFactory<>("fechaDeInicio"));
        column_Cierre.setCellValueFactory(new PropertyValueFactory<>("fechaDeCierre"));
        column_Estado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        try{
            List<Actividad> actividadesDeColaboracion = obtenerActividades();
            tableView_Actividades.getItems().addAll(actividadesDeColaboracion);
        }catch(IllegalArgumentException excepcion){
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
    
    private void desplegarVentanaCorrespondiente(String rutaVentanaFXML){
        if(validarConexionEstable()){
            try{
            Parent root=FXMLLoader.load(getClass().getResource(rutaVentanaFXML));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show(); 
            cerrarVentana();
            }catch(IOException excepcion){
                LOG.error(excepcion);
            }
        }else{
            Alertas.mostrarMensajeSinConexion();
            salirAlInicioDeSesion();
        }
    }
    
     private boolean validarConexionEstable(){
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
