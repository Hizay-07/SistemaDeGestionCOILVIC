package interfazDeUsuario.Controladores;

import interfazDeUsuario.Alertas.Alertas;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import logicaDeNegocio.ClasesAuxiliares.ColaboracionAuxiliar;
import logicaDeNegocio.DAOImplementacion.DAOColaboracionImplementacion;
import logicaDeNegocio.clases.Colaboracion;
import logicaDeNegocio.clases.UsuarioSingleton;
import logicaDeNegocio.enums.EnumColaboracion;
import org.apache.log4j.Logger;


public class Ventana_ColaboracionesController implements Initializable {
    
    private static final Logger LOG=Logger.getLogger(Ventana_ColaboracionesController.class);
    private Stage escenario;
    @FXML
    private AnchorPane anchor_Ventana;
    @FXML
    private TableView<Colaboracion> tableView_Colaboraciones;
    @FXML
    private TableColumn<Colaboracion,Integer> column_IdColaboracion;
    @FXML
    private TableColumn<Colaboracion,String> column_ProgramaEducativo;
    @FXML
    private TableColumn<Colaboracion,String> column_FechaDeInicio;
    @FXML
    private TableColumn<Colaboracion,String> column_FechaDeCierre;
    @FXML
    private TableColumn<Colaboracion,String> column_Estado;
    @FXML
    private TableColumn<Colaboracion,Void> column_Visualizar;
    @FXML
    private ComboBox cmb_TipoDeColaboracion;
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        mostrarColaboracionesActivas();
        llenarComboBoxTipoDeColaboracion();
    }
    
    public void llenarComboBoxTipoDeColaboracion(){
        ObservableList<String> tiposDeColaboracion = FXCollections.observableArrayList();
        for(EnumColaboracion colaboracion : EnumColaboracion.values()){
            tiposDeColaboracion.add(colaboracion.toString());
        }
        cmb_TipoDeColaboracion.setItems(tiposDeColaboracion);
    }
    
    public void mostrarConsultaSeleccionada(){
        String seleccion = (String) cmb_TipoDeColaboracion.getSelectionModel().getSelectedItem();
        switch(seleccion){
            case "Activa":
                mostrarColaboracionesActivas();
                break;
            case "Finalizada":
                mostrarColaboracionesFinalizadas();
                break;
            default:
                Alertas.mostrarMensajeDatosInvalidos();
                break;
        }
    }
    
    public List<Colaboracion> obtenerColaboracionesActivas(){
        List<Colaboracion> colaboracionesObtenidas = new ArrayList();
        DAOColaboracionImplementacion daoColaboracion = new DAOColaboracionImplementacion();
        colaboracionesObtenidas = daoColaboracion.consultarColaboracionesPorEstado(EnumColaboracion.Activa.toString());
        return colaboracionesObtenidas;
    }
    
    public List<Colaboracion> obtenerColaboracionesFinalizadas(){
        List<Colaboracion> colaboracionesObtenidas = new ArrayList();
        DAOColaboracionImplementacion daoColaboracion = new DAOColaboracionImplementacion();
        colaboracionesObtenidas = daoColaboracion.consultarColaboracionesPorEstado(EnumColaboracion.Finalizada.toString());
        return colaboracionesObtenidas;
    }
    
    public void mostrarColaboracionesActivas(){
        tableView_Colaboraciones.getItems().clear();
        try{
            List<Colaboracion> colaboracionesActivasObtenidas = obtenerColaboracionesActivas();
            if(Objects.nonNull(colaboracionesActivasObtenidas)){
                tableView_Colaboraciones.getItems().addAll(colaboracionesActivasObtenidas);
                column_IdColaboracion.setCellValueFactory(new PropertyValueFactory<>("idColaboracion"));
                column_Estado.setCellValueFactory(new PropertyValueFactory<>("estadoColaboracion"));
                column_ProgramaEducativo.setCellValueFactory(cellData->{
                    Colaboracion colaboracion = cellData.getValue();
                    return new SimpleStringProperty(colaboracion.getPropuestaColaboracion().getProgramaEducativoEstudiantil());
                });
                column_FechaDeInicio.setCellValueFactory(cellData->{
                    Colaboracion colaboracion = cellData.getValue();
                    return new SimpleStringProperty(colaboracion.getPropuestaColaboracion().getFechaInicio());
                });
                column_FechaDeCierre.setCellValueFactory(cellData->{
                    Colaboracion colaboracion = cellData.getValue();
                    return new SimpleStringProperty(colaboracion.getPropuestaColaboracion().getFechaCierre());
                });
                asignarBotonesDeVisualizarDetalles();
            }else{
                Alertas.mostrarMensajeColaboracionActiva("No se han encontrado colaboraciones activas");
            }
        }catch(IllegalArgumentException excepcion){
            LOG.error(excepcion.getCause());
            Alertas.mostrarMensajeErrorAlObtenerDatos();
        }
    }
    
    public void mostrarColaboracionesFinalizadas(){
        tableView_Colaboraciones.getItems().clear();
        try{
            List<Colaboracion> colaboracionesFinalizadasObtenidas = obtenerColaboracionesFinalizadas();
            if(Objects.nonNull(colaboracionesFinalizadasObtenidas)){
                tableView_Colaboraciones.getItems().addAll(colaboracionesFinalizadasObtenidas);
                column_IdColaboracion.setCellValueFactory(new PropertyValueFactory<>("idColaboracion"));
                column_Estado.setCellValueFactory(new PropertyValueFactory<>("estadoColaboracion"));
                column_ProgramaEducativo.setCellValueFactory(cellData->{
                    Colaboracion colaboracion = cellData.getValue();
                    return new SimpleStringProperty(colaboracion.getPropuestaColaboracion().getProgramaEducativoEstudiantil());
                });
                column_FechaDeInicio.setCellValueFactory(cellData->{
                    Colaboracion colaboracion = cellData.getValue();
                    return new SimpleStringProperty(colaboracion.getPropuestaColaboracion().getFechaInicio());
                });
                column_FechaDeCierre.setCellValueFactory(cellData->{
                    Colaboracion colaboracion = cellData.getValue();
                    return new SimpleStringProperty(colaboracion.getPropuestaColaboracion().getFechaCierre());
                });
                asignarBotonesDeVisualizarDetalles();
            }else{
                Alertas.mostrarMensajeColaboracionActiva("No se han encontrado colaboraciones finalizadas");
            }  
        }catch(IllegalArgumentException excepcion){
            LOG.error(excepcion.getCause());
            Alertas.mostrarMensajeErrorAlObtenerDatos();
        }
    }
    
    public void asignarBotonesDeVisualizarDetalles(){
        Callback<TableColumn<Colaboracion, Void>, TableCell<Colaboracion, Void>> frabricaDeCelda = (final TableColumn<Colaboracion, Void> param) -> {
                final TableCell<Colaboracion, Void> cell = new TableCell<Colaboracion, Void>() {                
                    private final Button btn_VisualizarDetalles = new Button();{
                        btn_VisualizarDetalles.setText("Visualizar Detalles");
                        btn_VisualizarDetalles.setOnAction((ActionEvent event) -> {
                            Colaboracion colaboracionSeleccionada = getTableView().getItems().get(getIndex());
                            ColaboracionAuxiliar.setInstancia(colaboracionSeleccionada);
                            String ruta = "/interfazDeUsuario/Ventana_DetallesDeColaboracion.fxml";
                            desplegarVentanaCorrespondiente(ruta);
                        });
                    }                
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        }else{ 
                            setGraphic(btn_VisualizarDetalles);
                        }
                    }
                };
            return cell;
            };
            column_Visualizar.setCellFactory(frabricaDeCelda);
    }
    
    public void regresarAMenuPrincipal(){
        UsuarioSingleton usuario = UsuarioSingleton.getInstancia();
        if(usuario.getTipoDeUsuario().equals("Profesor")){
            String rutaVentanaFXML="/interfazDeUsuario/Ventana_MenuPrincipalProfesor.fxml";
            desplegarVentanaCorrespondiente(rutaVentanaFXML);   
        }else if(usuario.getTipoDeUsuario().equals("Administrativo")){
            String rutaVentanaFXML="/interfazDeUsuario/Ventana_MenuAdministrador.fxml";
            desplegarVentanaCorrespondiente(rutaVentanaFXML); 
        }    
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
            LOG.error(excepcion);
        }
        cerrarVentana();
    }
    
}
