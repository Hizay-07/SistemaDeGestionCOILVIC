package interfazDeUsuario.Controladores;

import interfazDeUsuario.Alertas.Alertas;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import logicaDeNegocio.DAOImplementacion.DAOUsuarioImplementacion;
import logicaDeNegocio.clases.Colaboracion;
import logicaDeNegocio.clases.ProfesorSingleton;
import logicaDeNegocio.clases.UsuarioSingleton;
import logicaDeNegocio.enums.EnumColaboracion;
import logicaDeNegocio.enums.EnumTipoDeUsuario;
import org.apache.log4j.Logger;


public class Ventana_ColaboracionesControlador implements Initializable {
    
    private static final Logger LOG=Logger.getLogger(Ventana_ColaboracionesControlador.class);
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
    private TableColumn<Colaboracion,Void> column_DarDeBajaColaboracion;
    @FXML
    private ComboBox cmb_TipoDeColaboracion;
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        mostrarColaboraciones("Activa");
        llenarComboBoxTipoDeColaboracion();
    }
    
    public void llenarComboBoxTipoDeColaboracion(){
        ObservableList<String> tiposDeColaboracion = FXCollections.observableArrayList();
        for(EnumColaboracion colaboracion : EnumColaboracion.values()){
            tiposDeColaboracion.add(colaboracion.toString());
        }
        cmb_TipoDeColaboracion.setItems(tiposDeColaboracion);
        UsuarioSingleton usuario = UsuarioSingleton.getInstancia();
        if(usuario.getTipoDeUsuario().equals(EnumTipoDeUsuario.Administrativo.toString())){
            column_DarDeBajaColaboracion.setVisible(true);
            asignarBotonesDarDeBajaColaboracion();
        }else{
            column_DarDeBajaColaboracion.setVisible(false);
        }
    }
    
    public void mostrarConsultaSeleccionada(){
        String seleccion = (String) cmb_TipoDeColaboracion.getSelectionModel().getSelectedItem();
        switch(seleccion){
            case "Activa":
                mostrarColaboraciones("Activa");
                break;
            case "Finalizada":
                mostrarColaboraciones("Finalizada");
                column_DarDeBajaColaboracion.setVisible(false);
                break;
            case "Cerrada":
                mostrarColaboraciones("Cerrada");
                break;
            default:
                Alertas.mostrarMensajeDatosInvalidos();
                break;
        }
    }
    
    public List<Colaboracion> obtenerColaboracionesCerradas(){
        List<Colaboracion> colaboracionesObtenidas = new ArrayList();
        DAOColaboracionImplementacion daoColaboracion = new DAOColaboracionImplementacion();
        colaboracionesObtenidas = daoColaboracion.consultarColaboracionesPorEstado(EnumColaboracion.Cerrada.toString());
        return colaboracionesObtenidas;
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
    
    public void mostrarColaboraciones(String tipoDeColaboracion){
        tableView_Colaboraciones.getItems().clear();
        List<Colaboracion> colaboracionesObtenidas = new ArrayList();
        try{
            switch(tipoDeColaboracion){
                case "Activa" -> colaboracionesObtenidas = obtenerColaboracionesActivas();
                case "Cerrada" -> colaboracionesObtenidas = obtenerColaboracionesCerradas();
                case "Finalizada" -> colaboracionesObtenidas = obtenerColaboracionesFinalizadas();
            }
            
            if(!colaboracionesObtenidas.isEmpty()){
                tableView_Colaboraciones.getItems().addAll(colaboracionesObtenidas);
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
                Alertas.mostrarMensajeColaboracionActiva("No se han encontrado colaboraciones "+tipoDeColaboracion);
            }
        }catch(IllegalArgumentException excepcion){
            LOG.error(excepcion.getCause());
            Alertas.mostrarMensajeErrorAlObtenerDatos();
        }
    }
    
    public void asignarBotonesDarDeBajaColaboracion(){
        Callback<TableColumn<Colaboracion, Void>, TableCell<Colaboracion, Void>> frabricaDeCelda = (final TableColumn<Colaboracion, Void> param) -> {
                final TableCell<Colaboracion, Void> cell = new TableCell<Colaboracion, Void>() {                
                    private final Button btn_DarDeBajaColaboracion = new Button();{
                        btn_DarDeBajaColaboracion.setText("Dar de baja");
                        btn_DarDeBajaColaboracion.setOnAction((ActionEvent event) -> {
                            Colaboracion colaboracionSeleccionada = getTableView().getItems().get(getIndex());
                            darDeBajaColaboracion(colaboracionSeleccionada);
                        });
                    }                
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        }else{ 
                            setGraphic(btn_DarDeBajaColaboracion);
                        }
                    }
                };
            return cell;
            };
        column_DarDeBajaColaboracion.setCellFactory(frabricaDeCelda);
    }
    
    public void darDeBajaColaboracion(Colaboracion colaboracion){
        boolean resultadoAccion = Alertas.mostrarConfirmacionDeAccion("¿Desea dar fin a la Colaboracion seleccionada?");
        if(resultadoAccion){
            if(!colaboracion.getEstadoColaboracion().equals(EnumColaboracion.Activa.toString())){
                    DAOColaboracionImplementacion daoColaboracion = new DAOColaboracionImplementacion();
                    int resultadoModificacion = daoColaboracion.cambiarEstadoColaboracion(EnumColaboracion.Finalizada.toString(), colaboracion);
                    if(resultadoModificacion==1){
                        Alertas.mostrarColaboracionFinalizada();
                        tableView_Colaboraciones.getItems().clear();
                    }else if(resultadoModificacion==-1){
                        Alertas.mostrarMensajeErrorEnLaConexion();
                    }
            }else{
                Alertas.mostrarMensajeColaboracionActiva("No se pueden finalizar colaboraciones activas");
            }
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
            LOG.error(excepcion.getCause());
        }
    }
    
}
