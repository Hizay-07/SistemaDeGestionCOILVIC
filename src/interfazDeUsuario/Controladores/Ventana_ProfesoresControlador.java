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
import logicaDeNegocio.ClasesAuxiliares.ProfesorAuxiliar;
import logicaDeNegocio.DAOImplementacion.DAOProfesorExternoImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOProfesorUVImplementacion;
import logicaDeNegocio.DAOImplementacion.DAORepresentanteInstitucionalImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOUsuarioImplementacion;
import logicaDeNegocio.clases.Profesor;
import logicaDeNegocio.clases.ProfesorExterno;
import logicaDeNegocio.clases.ProfesorUV;
import logicaDeNegocio.clases.UsuarioSingleton;
import logicaDeNegocio.enums.EnumTipoDeProfesor;
import org.apache.log4j.Logger;


public class Ventana_ProfesoresControlador implements Initializable {
    
    private static final Logger LOG=Logger.getLogger(Ventana_ActividadesColaboracionActivaControlador.class);
    private Stage escenario;
    @FXML
    private AnchorPane anchor_Ventana;
    @FXML
    private TableView<Profesor> tableView_Profesores;
    @FXML
    private TableColumn<Profesor, String> column_Nombre;
    @FXML
    private TableColumn<Profesor, String> column_ApellidoPaterno;
    @FXML
    private TableColumn<Profesor, String> column_ApellidoMaterno;
    @FXML
    private TableColumn<Profesor, String> column_Universidad;
    @FXML
    private TableColumn<Profesor, Void> column_Modificar;
    @FXML
    private TableColumn<Profesor, String> column_Correo;
    @FXML
    private TableColumn<Profesor, String> column_EstadoProfesor;
    @FXML
    private Button btn_Regresar;
    @FXML
    private ComboBox cmb_TipoDeProfesor;
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        mostrarProfesoresUV();
        asignarBotonesDeModificarPerfil();
        llenarComboBoxTipoDeProfesor();
    }    
    
    private void llenarComboBoxTipoDeProfesor(){
        ObservableList<String> tiposDeProfesor = FXCollections.observableArrayList();
        for(EnumTipoDeProfesor tipoDeProfesor : EnumTipoDeProfesor.values()){
            tiposDeProfesor.add(tipoDeProfesor.toString());
        }
        cmb_TipoDeProfesor.setItems(tiposDeProfesor);
    }
    
    public void mostrarConsultaSeleccionada(){
        String seleccion = (String) cmb_TipoDeProfesor.getSelectionModel().getSelectedItem();
        switch(seleccion){
            case "Uv":
                mostrarProfesoresUV();
                break;
            case "Externo":
                mostrarProfesoresExternos();
                break;
            default:
                Alertas.mostrarMensajeDatosInvalidos();
                break;
        }
    }
    
    public void mostrarProfesoresUV(){
        tableView_Profesores.getItems().clear();
        try{
            List<ProfesorUV> profesoresUV = obtenerProfesoresUV();
            if(!profesoresUV.isEmpty()){
                tableView_Profesores.getItems().addAll(profesoresUV);
                column_Nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
                column_ApellidoPaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoPaterno"));
                column_ApellidoMaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoMaterno"));
                column_Correo.setCellValueFactory(new PropertyValueFactory<>("correo"));
                column_EstadoProfesor.setCellValueFactory(new PropertyValueFactory<>("estado"));
                column_Universidad.setCellValueFactory(cellData -> {
                    Profesor profesor = cellData.getValue();
                    String valorInstitucion = obtenerValorInstitucion(profesor);
                    return new SimpleStringProperty(valorInstitucion);
                });
            }else{
                Alertas.mostrarMensajeSinResultadosEncontrados("No se han encontrado profesores UV registrados");
            }
        }catch(IllegalArgumentException excepcion){
            LOG.error(excepcion.getCause());
            Alertas.mostrarMensajeErrorAlObtenerDatos();
        }
    }
    
    public void mostrarProfesoresExternos(){
        tableView_Profesores.getItems().clear();
        try{
            List<ProfesorExterno> profesoresExternos = obtenerProfesoresExternos();
            if(!profesoresExternos.isEmpty()){
                tableView_Profesores.getItems().addAll(profesoresExternos);
                column_Nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
                column_ApellidoPaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoPaterno"));
                column_ApellidoMaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoMaterno"));
                column_Correo.setCellValueFactory(new PropertyValueFactory<>("correo"));
                column_EstadoProfesor.setCellValueFactory(new PropertyValueFactory<>("estado"));
                column_Universidad.setCellValueFactory(cellData -> {
                    Profesor profesor = cellData.getValue();
                    String valorInstitucion = obtenerValorInstitucion(profesor);
                    return new SimpleStringProperty(valorInstitucion);
                });
            }else{
                Alertas.mostrarMensajeSinResultadosEncontrados("No se han encontrado profesores UV registrados");
            }
        }catch(IllegalArgumentException excepcion){
            LOG.error(excepcion.getCause());
            Alertas.mostrarMensajeErrorAlObtenerDatos();
        }
    }
    
    public String obtenerValorInstitucion(Profesor profesor){
        DAOProfesorExternoImplementacion daoProfesorExterno=new DAOProfesorExternoImplementacion();
        int idRepresentanteInstitucional=daoProfesorExterno.consultarIdRepresentanteInstitucionalPorIdProfesor(profesor.getIdProfesor());                
        if(idRepresentanteInstitucional==0){
            return "UV";
        }else{
            DAORepresentanteInstitucionalImplementacion daoRepresentanteInstitucional=new DAORepresentanteInstitucionalImplementacion();
            String valorInstitucion=daoRepresentanteInstitucional.consultarNombreInstitucionPorIdRepresentanteInstitucional(idRepresentanteInstitucional);            
            return valorInstitucion;
        }                        
    } 
    
    public List<ProfesorExterno> obtenerProfesoresExternos(){
        List<ProfesorExterno> profesoresExternosObtenidos = new ArrayList();
        DAOProfesorExternoImplementacion daoProfesores = new DAOProfesorExternoImplementacion();
        profesoresExternosObtenidos = daoProfesores.consultarProfesoresExternos();
        return profesoresExternosObtenidos;
    }
    
    public List<ProfesorUV> obtenerProfesoresUV(){
        List<ProfesorUV> profesoresUVObtenidos = new ArrayList();
        DAOProfesorUVImplementacion daoProfesor = new DAOProfesorUVImplementacion();
        profesoresUVObtenidos = daoProfesor.consultarProfesoresUV();
        return profesoresUVObtenidos;
    }
    
    public void asignarBotonesDeModificarPerfil(){
        Callback<TableColumn<Profesor, Void>, TableCell<Profesor, Void>> frabricaDeCelda = (final TableColumn<Profesor, Void> param) -> {
                final TableCell<Profesor, Void> cell = new TableCell<Profesor, Void>() {                
                    private final Button btn_Modificar = new Button();{
                        btn_Modificar.setText("Actualizar");
                        btn_Modificar.setOnAction((ActionEvent event) -> {
                            Profesor profesorSeleccionado = getTableView().getItems().get(getIndex());
                            ProfesorAuxiliar.setInstancia(profesorSeleccionado);
                            String ruta = "/interfazDeUsuario/Ventana_ActualizarPerfilProfesor.fxml";
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
            return cell;
            };
            column_Modificar.setCellFactory(frabricaDeCelda);
    }
    
    public void regresarMenuPrincipal(){
        String rutaVentanaFXML="/interfazDeUsuario/Ventana_MenuAdministrador.fxml";
        desplegarVentanaCorrespondiente(rutaVentanaFXML);  
    }
    
    public void cerrarVentana(){
        escenario = (Stage)anchor_Ventana.getScene().getWindow();
        escenario.close();
    }
    
    public void desplegarVentanaCorrespondiente(String ruta){
        if(validarConexionEstable()){
            String rutaVentanaFXML = ruta;
            try{
                Parent root=FXMLLoader.load(getClass().getResource(rutaVentanaFXML));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
                cerrarVentana();
            }catch(IOException excepcion){
                Alertas.mostrarMensajeErrorAlDesplegarVentana();
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
            cerrarVentana();
        } catch (IOException excepcion) {
            Alertas.mostrarMensajeErrorAlDesplegarVentana();
            LOG.error(excepcion.getCause());
        }
    }
}
