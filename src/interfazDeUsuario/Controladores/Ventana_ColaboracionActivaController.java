package interfazDeUsuario.Controladores;

import interfazDeUsuario.Alertas.Alertas;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOColaboracionImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOPropuestaColaboracionImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOEmisionPropuestaImplementacion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logicaDeNegocio.ClasesAuxiliares.EmisionPropuestaAuxiliar;
import logicaDeNegocio.DAOImplementacion.DAOPeticionColaboracionImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOProfesorImplementacion;
import logicaDeNegocio.clases.EmisionPropuesta;
import logicaDeNegocio.clases.PeticionColaboracion;
import logicaDeNegocio.clases.Profesor;
import logicaDeNegocio.clases.ProfesorSingleton;
import logicaDeNegocio.clases.PropuestaColaboracion;
import org.apache.log4j.Logger;



public class Ventana_ColaboracionActivaController implements Initializable {
    
    private static final Logger LOG=Logger.getLogger(Ventana_ColaboracionActivaController.class);
    private Stage escenario;
    @FXML 
    private AnchorPane anchor_Ventana;
    @FXML 
    private Pane pane_ColaboracionActiva;
    @FXML 
    private Label lbl_Titulo;
    @FXML 
    private Pane pane_DatosColaboracion;
    @FXML 
    private ImageView img_ImagenDeFondo;
    @FXML 
    private Label lbl_Colaboracion;
    @FXML 
    private Label lbl_TipoDeColaboracion;
    @FXML 
    private Label lbl_ObjGeneral;
    @FXML 
    private Label lbl_ObjetivoGeneralDato;
    @FXML 
    private Label lbl_TipoDeColaboracionDato;
    @FXML 
    private Label lbl_NombreColaboracion;
    @FXML 
    private Button btn_IniciarActividad;
    @FXML 
    private Button btn_Actividades;
    @FXML 
    private Button btn_CerrarColaboracion;
    @FXML 
    private VBox vb_Profesores;
    @FXML 
    private Label lbl_Profesor1;
    @FXML 
    private Label lbl_Profesor2;
    @FXML 
    private Label lbl_Profesor3;
    @FXML 
    private Label lbl_Profesor4;
    @FXML 
    private Button btn_Regresar;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn_Regresar.setOnAction(Event -> {
            regresarAMenuPrincipal();
        });
        
        btn_Actividades.setOnAction(Event ->{
            inicializarVentanaActividades();
        });
        
        btn_IniciarActividad.setOnAction(Event-> {
            inicializarVentanaIniciarActividad();
        });
       
    }   
    
     public void cerrarVentana(){
        escenario = (Stage)anchor_Ventana.getScene().getWindow();
        escenario.close();
    }
     
    public Profesor obtenerDatosDeProfesorSingleton(){
        ProfesorSingleton profesorSingleton = ProfesorSingleton.getInstancia();
        Profesor profesor = new Profesor();
        try{
            profesor.setIdProfesor(profesorSingleton.getIdProfesor());
            profesor.setNombre(profesorSingleton.getNombre());
            profesor.setApellidoMaterno(profesorSingleton.getApellidoMaterno());
            profesor.setApellidoPaterno(profesorSingleton.getApellidoPaterno());
            profesor.setCorreo(profesorSingleton.getCorreo());
        }catch(IllegalArgumentException excepcion){
            LOG.error(excepcion.getCause());
            Alertas.mostrarMensajeErrorAlObtenerDatos();
        }     
        return profesor;
    }
    
    public List<Profesor> obtenerProfesoresColaboracion(int idColaboracion){
        List<Profesor> profesoresObtenidos = new ArrayList();
        DAOColaboracionImplementacion daoColaboracion = new DAOColaboracionImplementacion();
        
        return profesoresObtenidos;
    }
    
    
    public PropuestaColaboracion obtenerDatosDeColaboracionDesdeEmisiorDeColaboracion(){
        Profesor profesorActivo = obtenerDatosDeProfesorSingleton();
        PropuestaColaboracion propuestaActiva = new PropuestaColaboracion();
        DAOPropuestaColaboracionImplementacion daoPropuestaColaboracion = new DAOPropuestaColaboracionImplementacion();
        DAOEmisionPropuestaImplementacion daoEmisionPropuesta = new DAOEmisionPropuestaImplementacion();
        EmisionPropuesta emisionPropuesta = new EmisionPropuesta();
        
        int idPropuesta = daoEmisionPropuesta.consultarIdPropuestaDeColaboracionPorIdProfesor(profesorActivo);
        if(idPropuesta!=-1){
            emisionPropuesta.setIdPropuestaColaboracion(idPropuesta);
            EmisionPropuestaAuxiliar.setInstancia(emisionPropuesta);
            propuestaActiva = daoPropuestaColaboracion.obtenerPropuestaDeColaboracionPorId(idPropuesta);
        }else{
            Alertas.mostrarMensajeErrorEnLaConexion();
        }
        
        return propuestaActiva;
    }
    
    public PropuestaColaboracion obtenerDatosDeColaboracionDesdePeticionesDeColaboracion(){
        Profesor profesorActivo = obtenerDatosDeProfesorSingleton();
        PeticionColaboracion peticion= new PeticionColaboracion();
        PropuestaColaboracion propuestaActiva = new PropuestaColaboracion();
        DAOPeticionColaboracionImplementacion daoPeticionColaboracion = new DAOPeticionColaboracionImplementacion();
        DAOPropuestaColaboracionImplementacion daoPropuestaColaboracion = new DAOPropuestaColaboracionImplementacion();    
        int idPropuesta = daoPeticionColaboracion.consultarIdPropuestaDeColaboracionPorIdProfesor(profesorActivo);
        if(idPropuesta!=-1){
            peticion.setIdPropuestaColaboracion(idPropuesta);
            propuestaActiva = daoPropuestaColaboracion.obtenerPropuestaDeColaboracionPorId(peticion.getIdPropuestaColaboracion());
        }else{
            Alertas.mostrarMensajeFechaInvalida();
        }
        return propuestaActiva;
    }
    
    
    
    public void asignarDatosDeColaboracion(PropuestaColaboracion propuestaActiva){
        lbl_NombreColaboracion.setText(propuestaActiva.getExperienciaEducativa());
        lbl_TipoDeColaboracionDato.setText(propuestaActiva.getTipoColaboracion().getTipo());
        lbl_ObjetivoGeneralDato.setText(propuestaActiva.getObjetivo());
    }
    
    public void cargarDatosDeColaboracion(){
        PropuestaColaboracion propuestaActiva = new PropuestaColaboracion();
        propuestaActiva = obtenerDatosDeColaboracionDesdeEmisiorDeColaboracion();
        if(propuestaActiva!=null){
          
        }else{
            propuestaActiva = obtenerDatosDeColaboracionDesdePeticionesDeColaboracion();
            if(propuestaActiva!=null){
                
            }else{
                Alertas.mostrarMensajeErrorAlObtenerDatos();
            }
        }
    }
    
    public void regresarAMenuPrincipal(){
        String rutaVentanaFXML="/interfazDeUsuario/Ventana_MenuPrincipalProfesor.fxml";
        desplegarVentanaCorrespondiente(rutaVentanaFXML);  
    }
    
    public void inicializarVentanaActividades(){
        String rutaVentanaFXML="/interfazDeUsuario/Ventana_Actividades.fxml";
        desplegarVentanaCorrespondiente(rutaVentanaFXML);  
    }
    
    public void inicializarVentanaIniciarActividad(){    
        String rutaVentanaFXML="/interfazDeUsuario/Ventana_IniciarActividad.fxml";
        desplegarVentanaCorrespondiente(rutaVentanaFXML);   
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
