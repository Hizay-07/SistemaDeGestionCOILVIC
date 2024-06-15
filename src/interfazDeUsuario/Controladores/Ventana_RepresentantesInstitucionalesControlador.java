package interfazDeUsuario.Controladores;

import interfazDeUsuario.Alertas.Alertas;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import logicaDeNegocio.ClasesAuxiliares.RepresentanteAuxiliar;
import logicaDeNegocio.DAOImplementacion.DAORepresentanteInstitucionalImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOUsuarioImplementacion;
import logicaDeNegocio.clases.RepresentanteInstitucional;
import logicaDeNegocio.clases.UsuarioSingleton;
import org.apache.log4j.Logger;

public class Ventana_RepresentantesInstitucionalesControlador implements Initializable {
    
    private static final Logger LOG=Logger.getLogger(Ventana_RepresentantesInstitucionalesControlador.class);
    private Stage escenario;
    @FXML
    private AnchorPane anchor_Ventana;
    @FXML
    private TableView<RepresentanteInstitucional> tableView_RepresentanteInstitucional;
    @FXML
    private TableColumn<RepresentanteInstitucional, String> column_Nombre;
    @FXML
    private TableColumn<RepresentanteInstitucional, String> column_ClaveInstitucional;
    @FXML
    private TableColumn<RepresentanteInstitucional, String> column_Contacto;
    @FXML
    private TableColumn<RepresentanteInstitucional, String> column_Pais;
    @FXML
    private TableColumn<RepresentanteInstitucional, Void> column_Actualizar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mostrarDatosRepresentantesInstitucionales();
        asignarBotonesDeModificarRepresentante();
    }    
    
    private List<RepresentanteInstitucional> obtenerRepresentantesInstitucionales(){
        DAORepresentanteInstitucionalImplementacion daoRepresentante = new DAORepresentanteInstitucionalImplementacion();
        List<RepresentanteInstitucional> representantesObtenidos = new ArrayList();
        representantesObtenidos = daoRepresentante.obtenerRepresentantesInstitucionales();
        return representantesObtenidos;
    }
    
    private void mostrarDatosRepresentantesInstitucionales(){
        try{
            List<RepresentanteInstitucional> representantes = obtenerRepresentantesInstitucionales();
            if(representantes.isEmpty()){
                Alertas.mostrarMensajeSinResultadosEncontrados("No se han encontrado Representantes Institucionales registrados");
            }else if(representantes.get(0).getNombreInstitucion().equals("Excepcion")){
                Alertas.mostrarMensajeErrorEnLaConexion();
                salirAlInicioDeSesion();
            }else{
                tableView_RepresentanteInstitucional.getItems().addAll(representantes);
                column_Nombre.setCellValueFactory(new PropertyValueFactory<>("nombreInstitucion"));
                column_ClaveInstitucional.setCellValueFactory(new PropertyValueFactory<>("claveInstitucional"));
                column_Contacto.setCellValueFactory(new PropertyValueFactory<>("contacto"));
                column_Pais.setCellValueFactory(cellData->{ 
                    SimpleStringProperty property = new SimpleStringProperty();
                    if (cellData.getValue() != null && cellData.getValue().getPais() != null) {
                        property.setValue(cellData.getValue().getPais().getNombrePais());
                    }
                    return property;
                });
            }
        }catch(IllegalArgumentException excepcion){
            LOG.error(excepcion.getCause());
            Alertas.mostrarMensajeErrorAlObtenerDatos();
        }
    }
    
    private void asignarBotonesDeModificarRepresentante(){
        Callback<TableColumn<RepresentanteInstitucional, Void>, TableCell<RepresentanteInstitucional, Void>> frabricaDeCelda = (final TableColumn<RepresentanteInstitucional, Void> param) -> {
                final TableCell<RepresentanteInstitucional, Void> celda = new TableCell<RepresentanteInstitucional, Void>() {                
                    private final Button btn_Modificar = new Button();{
                        btn_Modificar.setText("Actualizar");
                        btn_Modificar.setOnAction((ActionEvent event) -> {
                            RepresentanteInstitucional representanteSeleccionado = getTableView().getItems().get(getIndex());
                            RepresentanteAuxiliar.setInstancia(representanteSeleccionado);
                            String ruta = "/interfazDeUsuario/Ventana_ActualizarRepresentanteInstitucional.fxml";
                            desplegarVentanaCorrespondiente(ruta);
                        });
                    }                
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        }else{ 
                            setGraphic(btn_Modificar);
                        }
                    }
                };
            return celda;
            };
            column_Actualizar.setCellFactory(frabricaDeCelda);
    }
    
    public void regresarMenuPrincipal(){
        String rutaVentanaFXML="/interfazDeUsuario/Ventana_MenuAdministrador.fxml";
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
            cerrarVentana();
        } catch (IOException excepcion) {
            Alertas.mostrarMensajeErrorAlDesplegarVentana();
            LOG.error(excepcion.getCause());
        }
    }
}
