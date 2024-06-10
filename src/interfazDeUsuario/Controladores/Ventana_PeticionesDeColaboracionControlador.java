package interfazDeUsuario.Controladores;

import envioDeCorreos.EnvioDeCorreo;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import logicaDeNegocio.DAOImplementacion.DAOPeticionColaboracionImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOProfesorExternoImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOProfesorImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOPropuestaColaboracionImplementacion;
import logicaDeNegocio.DAOImplementacion.DAORepresentanteInstitucionalImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOUsuarioImplementacion;
import logicaDeNegocio.clases.Profesor;
import logicaDeNegocio.clases.ProfesorSingleton;
import logicaDeNegocio.enums.EnumProfesor;
import logicaDeNegocio.clases.UsuarioSingleton;
import org.apache.log4j.Logger;

public class Ventana_PeticionesDeColaboracionControlador implements Initializable {
    
    private static final Logger LOG=Logger.getLogger(Ventana_PeticionesDeColaboracionControlador.class);    
    @FXML
    private TableColumn<Profesor,String> column_Institucion;
    @FXML
    private TableColumn column_Aceptar;
    @FXML
    private TableColumn column_Rechazar;
    @FXML
    private TableColumn<Profesor,String> column_Profesor;
    @FXML
    private TableColumn<Profesor,String> column_Contacto;
    @FXML
    private TableView<Profesor> tableView_PeticionesDeColaboracion;
    @FXML
    private VBox vb_PeticionesDeColaboracion;
    private Stage stage_ventana; 
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        column_Profesor.setCellValueFactory(cellData -> {
            Profesor profesor = cellData.getValue();        
            String resultado = profesor.toString();        
            return new SimpleStringProperty(resultado);
        });
        column_Institucion.setCellValueFactory(cellData -> {
            Profesor profesor = cellData.getValue();
            String valorInstitucion = obtenerValorInstitucion(profesor);
            return new SimpleStringProperty(valorInstitucion);
        });   
        column_Contacto.setCellValueFactory(cellData -> {
            Profesor profesor = cellData.getValue();
            String contacto = profesor.getCorreo();
            return new SimpleStringProperty(contacto);
        }); 
        List<Profesor> profesores=new ArrayList<>();
        profesores=consultarProfesores();
        tableView_PeticionesDeColaboracion.getItems().addAll(profesores);
        agregarBotonAceptar();
        agregarBotonRechazar();                                       
    }    
    
    private void cerrarVentana(){
        stage_ventana=(Stage) vb_PeticionesDeColaboracion.getScene().getWindow();
        stage_ventana.close();
    }
    
    public void salirDeLaVentana(){
        if(validarConexionEstable()){
            String rutaVentanaFXML = null;
            try{
                rutaVentanaFXML = "/interfazDeUsuario/Ventana_MenuPrincipalProfesor.fxml";
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
    
    private int obtenerIdPropuestaColaboracion(){
        ProfesorSingleton profesor = ProfesorSingleton.getInstancia();
        int idProfesor=profesor.getIdProfesor();
        DAOPropuestaColaboracionImplementacion daoPropuestaColaboracion=new DAOPropuestaColaboracionImplementacion();
        int idPropuestaColaboracion=daoPropuestaColaboracion.obtenerIdPropuestaColaboracionAprobadaPorIdProfesor(idProfesor);
        return idPropuestaColaboracion;
    }
    
    private List<Integer> obtenerIdProfesores(){
        int idPropuestaColaboracion=obtenerIdPropuestaColaboracion();
        List<Integer> idProfesores=new ArrayList<>();
        DAOPeticionColaboracionImplementacion daoPeticionColaboracion=new DAOPeticionColaboracionImplementacion();
        idProfesores=daoPeticionColaboracion.consultarIdProfesoresPorIdPropuestaColaboracion(idPropuestaColaboracion);
        return idProfesores;        
    }
    
    private List<Profesor> consultarProfesores(){
        List<Profesor> profesores=new ArrayList<>();
        List<Integer> idProfesores=new ArrayList<>();
        idProfesores=obtenerIdProfesores();
        DAOProfesorImplementacion daoProfesor=new DAOProfesorImplementacion();        
        for (Integer idProfesor : idProfesores) {
            profesores.add(daoProfesor.consultarProfesorPorId(idProfesor));
        }
        return profesores;
    }
    
    private String obtenerValorInstitucion(Profesor profesor){
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
    
    private void agregarBotonAceptar() {        
        Callback<TableColumn<Profesor, Void>, TableCell<Profesor, Void>> cellFactory = (final TableColumn<Profesor, Void> param) -> {
            final TableCell<Profesor, Void> cell = new TableCell<Profesor, Void>() {                
                private final Button btn_Aceptar = new Button();                                
                {
                    btn_Aceptar.setText("Aceptar");                    
                    btn_Aceptar.setOnAction((ActionEvent event) -> {
                        if(Alertas.confirmarEvaluacionPeticion()){
                            Profesor profesor= getTableView().getItems().get(getIndex());
                            int idProfesor=profesor.getIdProfesor();
                            DAOPeticionColaboracionImplementacion daoPeticionColaboracion=new DAOPeticionColaboracionImplementacion();
                            int idPropuesta=daoPeticionColaboracion.consultarIdPropuestaDeColaboracionPorIdProfesor(idProfesor);
                            daoPeticionColaboracion.aceptarPeticionColaboracion(idPropuesta, idProfesor); 
                            int numeroPeticiones=validarNumeroPeticiones();
                            if(numeroPeticiones==1){                                
                                ProfesorSingleton profesorSingleton = ProfesorSingleton.getInstancia();
                                int idProfesorSingleton=profesorSingleton.getIdProfesor();  
                                DAOPropuestaColaboracionImplementacion daoPropuestaColaboracion=new DAOPropuestaColaboracionImplementacion();
                                int idPropuestaACambiar=daoPropuestaColaboracion.obtenerIdPropuestaColaboracionAprobadaPorIdProfesor(idProfesorSingleton);
                                if(idPropuestaACambiar>0){
                                    daoPeticionColaboracion.cambiarEstadoPeticionesRegistradasPorIdPropuesta(idPropuestaACambiar);                
                                }                                
                                Alertas.mostrarLimitePeticionesColaboracion();
                                salirDeLaVentana();                                                            
                            }else{
                                mandarCorreoRespuestaPeticionDeColaboracion(profesor,"Aceptada");
                                tableView_PeticionesDeColaboracion.getItems().clear();
                                tableView_PeticionesDeColaboracion.getItems().addAll(consultarProfesores());                             
                            }
                                                   
                        }                        
                    });
                }                
                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(btn_Aceptar);
                    }
                }
            };
            return cell;
        };
        column_Aceptar.setCellFactory(cellFactory);       
    }
    
    private void agregarBotonRechazar() {        
        Callback<TableColumn<Profesor, Void>, TableCell<Profesor, Void>> cellFactory = (final TableColumn<Profesor, Void> param) -> {
            final TableCell<Profesor, Void> cell = new TableCell<Profesor, Void>() {                
                private final Button btn_Rechazar = new Button();                                
                {
                    btn_Rechazar.setText("Rechazar");
                    btn_Rechazar.setOnAction((ActionEvent event) -> {
                        if(Alertas.confirmarEvaluacionPeticion()){
                            Profesor profesor= getTableView().getItems().get(getIndex());
                            int idProfesor=profesor.getIdProfesor();
                            DAOPeticionColaboracionImplementacion daoPeticionColaboracion=new DAOPeticionColaboracionImplementacion();
                            int idPropuesta=daoPeticionColaboracion.consultarIdPropuestaDeColaboracionPorIdProfesor(idProfesor);
                            daoPeticionColaboracion.rechazarPeticionColaboracion(idPropuesta, idProfesor);
                            DAOProfesorImplementacion daoProfesor=new DAOProfesorImplementacion();
                            daoProfesor.cambiarEstadoProfesor(idProfesor, EnumProfesor.Activo.toString()); 
                            mandarCorreoRespuestaPeticionDeColaboracion(profesor,"Rechazada");
                            tableView_PeticionesDeColaboracion.getItems().clear();
                            tableView_PeticionesDeColaboracion.getItems().addAll(consultarProfesores());                         
                        }                                                                                               
                    });
                }                
                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(btn_Rechazar);
                    }
                }
            };
            return cell;
        };
        column_Rechazar.setCellFactory(cellFactory);       
    }
    
    private void mandarCorreoRespuestaPeticionDeColaboracion(Profesor profesor, String resultado){
        int resultadoEnvioDeCorreo;
        ProfesorSingleton profesorAutor = ProfesorSingleton.getInstancia();
        EnvioDeCorreo mandarCorreoCreacionDeUsuario = new EnvioDeCorreo();
        String asuntoCorreo = "Respuesta de peticion de colaboración COIL-VIC";
        String cuerpoCorreo = "Estimado profesor "+profesor.getNombre()+" "+profesor.getApellidoPaterno()+": "+
                "\nLa petición de solicitud que ha enviado para colaborar con el profesor "+profesorAutor.getNombre()+" "+
                profesorAutor.getApellidoPaterno()+" ha sido "+resultado+"\n\n Buen día. \n ATTE: Sistema de gestión COIL-VIC";
        String destinatario = profesor.getCorreo();
        mandarCorreoCreacionDeUsuario.setAsunto(asuntoCorreo);
        mandarCorreoCreacionDeUsuario.setContenido(cuerpoCorreo);
        mandarCorreoCreacionDeUsuario.setDestinatario(destinatario);
        resultadoEnvioDeCorreo = mandarCorreoCreacionDeUsuario.enviarCorreo();
        if(resultadoEnvioDeCorreo==1){
            Alertas.mostrarMensajeNotificacionEnviada();
        }else{
             Alertas.mostrarSinConexionAInternet("No se ha podido mandar la notificación al profesor destinatario.\n"
                     + "Por favor verifique su conexión a internet.");
        }
    }
    
    private int validarNumeroPeticiones(){
        DAOPeticionColaboracionImplementacion daoPeticionColaboracion=new DAOPeticionColaboracionImplementacion();
        ProfesorSingleton profesorSingleton = ProfesorSingleton.getInstancia();
        int idProfesor=profesorSingleton.getIdProfesor();  
        int resultadoPrecondicion=daoPeticionColaboracion.revisarPrecondicionEvaluarPeticionesPorIdProfesor(idProfesor);
        return resultadoPrecondicion;
    }
    
}
