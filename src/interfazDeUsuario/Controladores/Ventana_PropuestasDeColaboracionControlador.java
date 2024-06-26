package interfazDeUsuario.Controladores;

import interfazDeUsuario.Alertas.Alertas;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import logicaDeNegocio.DAOImplementacion.DAOProfesorExternoImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOPropuestaColaboracionImplementacion;
import logicaDeNegocio.DAOImplementacion.DAORepresentanteInstitucionalImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOUsuarioImplementacion;
import logicaDeNegocio.clases.Profesor;
import logicaDeNegocio.clases.PropuestaColaboracion;
import logicaDeNegocio.clases.TipoColaboracion;
import logicaDeNegocio.clases.UsuarioSingleton;
import org.apache.log4j.Logger;

public class Ventana_PropuestasDeColaboracionControlador implements Initializable {
    
    private static final Logger LOG=Logger.getLogger(Ventana_PropuestasDeColaboracionControlador.class);      
    @FXML
    private TableColumn<PropuestaColaboracion,String> column_ExperienciaEducativa;
    @FXML
    private TableColumn<PropuestaColaboracion,String> column_FechaInicio;
    @FXML
    private TableColumn<PropuestaColaboracion,String> column_FechaCierre;
    @FXML
    private TableColumn<PropuestaColaboracion,String> column_Idioma;
    @FXML
    private TableColumn<PropuestaColaboracion,String> column_Institucion;
    @FXML
    private TableColumn<PropuestaColaboracion,String> column_ObjetivoGeneral;
    @FXML
    private TableColumn column_Opcion;
    @FXML
    private TableColumn<PropuestaColaboracion,Profesor> column_Profesor;
    @FXML
    private TableColumn<PropuestaColaboracion,String> column_ProgramaEducativo;
    @FXML
    private TableColumn<PropuestaColaboracion,TipoColaboracion> column_TipoDeColaboracion;
    @FXML
    private TableView<PropuestaColaboracion> tableView_PropuestasDeColaboracion;
    @FXML
    private VBox vb_PropuestasDeColaboracion;
    private Stage stage_ventana;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        column_Idioma.setCellValueFactory(new PropertyValueFactory<>("idioma"));
        column_FechaInicio.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
        column_FechaCierre.setCellValueFactory(new PropertyValueFactory<>("fechaCierre"));
        column_ExperienciaEducativa.setCellValueFactory(new PropertyValueFactory<>("experienciaEducativa"));
        column_ProgramaEducativo.setCellValueFactory(new PropertyValueFactory<>("programaEducativoEstudiantil"));
        column_ObjetivoGeneral.setCellValueFactory(new PropertyValueFactory<>("objetivo"));
        column_TipoDeColaboracion.setCellValueFactory(new PropertyValueFactory<>("tipoColaboracion"));        
        column_Profesor.setCellValueFactory(new PropertyValueFactory<>("profesor"));               
        column_Institucion.setCellValueFactory(cellData -> {
            PropuestaColaboracion propuesta = cellData.getValue();
            String valorInstitucion = obtenerValorInstitucion(propuesta);
            return new SimpleStringProperty(valorInstitucion);
        });
        
        List<PropuestaColaboracion> propuestas=obtenerPropuestasDeColaboracion();
        if(propuestas.isEmpty()){
            Alertas.mostrarMensajeSinResultadosEncontrados("No se han encontrado propuestas de colaboración registradas");
        }else if(propuestas.get(0).getEstadoPropuesta().equals("Excepcion")){
            Alertas.mostrarMensajeErrorEnLaConexion();
            salirAlInicioDeSesion();
        }else{
            tableView_PropuestasDeColaboracion.getItems().addAll(propuestas);                                        
            agregarBoton();
        }
    }   
    
    private List<PropuestaColaboracion> obtenerPropuestasDeColaboracion(){
        List<PropuestaColaboracion> propuestas=new ArrayList<>(); 
        DAOPropuestaColaboracionImplementacion daoPropuestas=new DAOPropuestaColaboracionImplementacion();
        propuestas=daoPropuestas.consultarPropuestasDeColaboracionRegistradas();
        List<PropuestaColaboracion> propuestasFinales=new ArrayList<>();                
        for(PropuestaColaboracion propuesta : propuestas){                                                
            propuestasFinales.add(propuesta);
        }
        return propuestasFinales;                
    } 
    
    private String obtenerValorInstitucion(PropuestaColaboracion propuesta){
        DAOProfesorExternoImplementacion daoProfesorExterno=new DAOProfesorExternoImplementacion();
        int idRepresentanteInstitucional=daoProfesorExterno.consultarIdRepresentanteInstitucionalPorIdProfesor(propuesta.getProfesor().getIdProfesor());                
        if(idRepresentanteInstitucional==0){
            return "UV";
        }else{
            DAORepresentanteInstitucionalImplementacion daoRepresentanteInstitucional=new DAORepresentanteInstitucionalImplementacion();
            String valorInstitucion=daoRepresentanteInstitucional.consultarNombreInstitucionPorIdRepresentanteInstitucional(idRepresentanteInstitucional);            
            return valorInstitucion;
        }                        
    }    
    
    public String obtenerFechaActual(){
        LocalDate fechaActual = LocalDate.now();                
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaActualFormateada = fechaActual.format(formatter);
        return fechaActualFormateada;
    }
    
    private void agregarBoton() {        
        Callback<TableColumn<PropuestaColaboracion, Void>, TableCell<PropuestaColaboracion, Void>> fabricaCeldas = (final TableColumn<PropuestaColaboracion, Void> param) -> {
            final TableCell<PropuestaColaboracion, Void> celda = new TableCell<PropuestaColaboracion, Void>() {                
                private final Button btn_EvaluarPropuestaColaboracion = new Button();                                
                {
                    btn_EvaluarPropuestaColaboracion.setText("Evaluar propuesta de colaboracion");
                    btn_EvaluarPropuestaColaboracion.setOnAction((ActionEvent event) -> {
                        PropuestaColaboracion propuestaColaboracion = getTableView().getItems().get(getIndex());
                        int idPropuestaColaboracion=propuestaColaboracion.getIdPropuestaColaboracion();                                                                       
                        desplegarVentanaEvaluacionDePropuesta(idPropuestaColaboracion);                      
                    });
                }                
                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(btn_EvaluarPropuestaColaboracion);
                    }
                }
            };
            return celda;
        };
        column_Opcion.setCellFactory(fabricaCeldas);       
    }
    
    public void salirDeLaVentana(){
        if(obtenerResultadoValidacionConexion()){
            String rutaVentanaFXML = null;
            try{
                rutaVentanaFXML = "/interfazDeUsuario/Ventana_MenuAdministrador.fxml";
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
            salirAlInicioDeSesion();
        }                       
    }
    
    public int validarConexionEstable(){
        int resultado;
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
    
    private void cerrarVentana(){
        stage_ventana=(Stage) vb_PropuestasDeColaboracion.getScene().getWindow();
        stage_ventana.close();
    }
    
    private void desplegarVentanaEvaluacionDePropuesta(int idPropuestaColaboracion){   
        if(obtenerResultadoValidacionConexion()){
            try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/interfazDeUsuario/Ventana_EvaluacionDePropuesta.fxml"));
            Parent root = loader.load();
            Ventana_EvaluacionDePropuestaControlador controlador = loader.getController();
            controlador.inicializar(idPropuestaColaboracion); 
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
            salirAlInicioDeSesion();
        }   
    }
    
}
