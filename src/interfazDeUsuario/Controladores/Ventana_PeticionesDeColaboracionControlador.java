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
        if(profesores.size()==0){
            Alertas.mostrarMensajeSinResultadosEncontrados("No hay peticiones de colaboracion nuevas");
        }else if(!profesores.get(0).getNombre().equals("Excepcion")){
           tableView_PeticionesDeColaboracion.getItems().addAll(profesores);
        }else{
            Alertas.mostrarMensajeErrorEnLaConexion();
            salirAlInicioDeSesion();
        }
        agregarBotonAceptar();
        agregarBotonRechazar();                                       
    }    
    
    private void cerrarVentana(){
        stage_ventana=(Stage) vb_PeticionesDeColaboracion.getScene().getWindow();
        stage_ventana.close();
    }
    
    public void salirDeLaVentana(){
        if(obtenerResultadoValidacionConexion()){
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
            salirAlInicioDeSesion();
        }                     
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
        int idPropuestaColaboracion=daoPropuestaColaboracion.obtenerIdPropuestaColaboracionPorEstadoPorIdProfesor(idProfesor,"Aprobada");
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
        if(!idProfesores.isEmpty()){
            if(idProfesores.get(0)==-1||profesores.get(0).getNombre().equals("Excepcion")){
            profesores.get(0).setNombre("Excepcion");
            }
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
        Callback<TableColumn<Profesor, Void>, TableCell<Profesor, Void>> fabricasCeldas = (final TableColumn<Profesor, Void> param) -> {
            final TableCell<Profesor, Void> celda = new TableCell<Profesor, Void>() {                
                private final Button btn_Aceptar = new Button();{
                    btn_Aceptar.setText("Aceptar");                    
                    btn_Aceptar.setOnAction((ActionEvent event) -> {
                        if(Alertas.confirmarEvaluacionPeticion()){
                            if(obtenerResultadoValidacionConexion()){
                                Profesor profesor= getTableView().getItems().get(getIndex());
                                aceptarPropuestaDeColaboracion(profesor);
                            }else{
                                salirAlInicioDeSesion();
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
            return celda;
        };
        column_Aceptar.setCellFactory(fabricasCeldas);       
    }
    
    private void agregarBotonRechazar() {        
        Callback<TableColumn<Profesor, Void>, TableCell<Profesor, Void>> fabricaCeldas = (final TableColumn<Profesor, Void> param) -> {
            final TableCell<Profesor, Void> celdas = new TableCell<Profesor, Void>() {                
                private final Button btn_Rechazar = new Button();{
                    btn_Rechazar.setText("Rechazar");
                    btn_Rechazar.setOnAction((ActionEvent event) -> {
                        if(Alertas.confirmarEvaluacionPeticion()){
                            if(obtenerResultadoValidacionConexion()){
                                Profesor profesor= getTableView().getItems().get(getIndex());
                                rechazarPropuestaDeColaboracion(profesor);
                            }else{
                                salirAlInicioDeSesion();
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
                        setGraphic(btn_Rechazar);
                    }
                }
            };
            return celdas;
        };
        column_Rechazar.setCellFactory(fabricaCeldas);       
    }
    
    private void aceptarPropuestaDeColaboracion(Profesor profesor){
        int idProfesor=profesor.getIdProfesor();
        DAOPeticionColaboracionImplementacion daoPeticionColaboracion = new DAOPeticionColaboracionImplementacion();
        int idPropuesta = daoPeticionColaboracion.consultarIdPropuestaDeColaboracionPorIdProfesor(idProfesor);
        if(idPropuesta!=-1){
            daoPeticionColaboracion.aceptarPeticionColaboracion(idPropuesta, idProfesor);
            int numeroPeticiones = validarNumeroPeticiones();
            if (numeroPeticiones == 1) {
                ProfesorSingleton profesorSingleton = ProfesorSingleton.getInstancia();
                int idProfesorSingleton = profesorSingleton.getIdProfesor();
                DAOPropuestaColaboracionImplementacion daoPropuestaColaboracion = new DAOPropuestaColaboracionImplementacion();
                int idPropuestaACambiar = daoPropuestaColaboracion.obtenerIdPropuestaColaboracionPorEstadoPorIdProfesor(idProfesorSingleton,"Aprobada");
                if (idPropuestaACambiar > 0) {
                    daoPeticionColaboracion.cambiarEstadoPeticionesRegistradasPorIdPropuesta(idPropuestaACambiar);
                }
                Alertas.mostrarLimitePeticionesColaboracion();
                salirDeLaVentana();
            } else {
                mandarCorreoRespuestaPeticionDeColaboracion(profesor, "Aceptada");
                tableView_PeticionesDeColaboracion.getItems().clear();
                List<Profesor> profesores = consultarProfesores();
                if(!profesores.isEmpty()){
                    if(profesores.get(0).getNombre().equals("Excepcion")){
                    Alertas.mostrarMensajeErrorEnLaConexion();
                    salirAlInicioDeSesion();
                    }else{
                        tableView_PeticionesDeColaboracion.getItems().addAll(profesores);
                    }
                }
            } 
        }else{
            Alertas.mostrarMensajeErrorEnLaConexion();
            salirAlInicioDeSesion();
        }
    }
    
    private void rechazarPropuestaDeColaboracion(Profesor profesor){
        int idProfesor = profesor.getIdProfesor();
        DAOPeticionColaboracionImplementacion daoPeticionColaboracion = new DAOPeticionColaboracionImplementacion();
        int idPropuesta = daoPeticionColaboracion.consultarIdPropuestaDeColaboracionPorIdProfesor(idProfesor);        
        if(idPropuesta!=-1){
            daoPeticionColaboracion.rechazarPeticionColaboracion(idPropuesta, idProfesor);
            DAOProfesorImplementacion daoProfesor = new DAOProfesorImplementacion();
            daoProfesor.cambiarEstadoProfesor(idProfesor, EnumProfesor.Activo.toString());
            mandarCorreoRespuestaPeticionDeColaboracion(profesor, "Rechazada");
            tableView_PeticionesDeColaboracion.getItems().clear();
            List<Profesor> profesores = consultarProfesores();
            if(!profesores.isEmpty()){
                if(profesores.get(0).getNombre().equals("Excepcion")){
                    Alertas.mostrarMensajeErrorEnLaConexion();
                    salirAlInicioDeSesion();
                }else{
                    tableView_PeticionesDeColaboracion.getItems().addAll(profesores);
                }
            }
            
        }else{
            Alertas.mostrarMensajeErrorEnLaConexion();
            salirAlInicioDeSesion();
        }
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
