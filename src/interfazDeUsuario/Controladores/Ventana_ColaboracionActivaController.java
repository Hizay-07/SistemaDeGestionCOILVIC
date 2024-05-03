package interfazDeUsuario.Controladores;

import interfazDeUsuario.Alertas.Alertas;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOColaboracionImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOColaboracionProfesorImplementacion;
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
import logicaDeNegocio.DAOImplementacion.DAOPropuestaColaboracionImplementacion;
import logicaDeNegocio.clases.Colaboracion;
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
    
    public List<Profesor> obtenerProfesoresColaboracion(Colaboracion colaboracion){
        List<Profesor> profesoresObtenidos = new ArrayList();
        DAOColaboracionProfesorImplementacion daoColaboracionProfesor = new DAOColaboracionProfesorImplementacion();
        profesoresObtenidos = daoColaboracionProfesor.obtenerProfesoresPorIdColaboracion(colaboracion);
        return profesoresObtenidos;
    }
    
    public Colaboracion obtenerDatosColaboracion(Profesor profesor){
        Colaboracion colaboracionObtenida = new Colaboracion();
        DAOColaboracionProfesorImplementacion daoColaboracionImplementacion = new DAOColaboracionProfesorImplementacion();
        colaboracionObtenida = daoColaboracionImplementacion.obtenerColaboracionPorIdProfesor(profesor);
        return colaboracionObtenida;
    }
    
    
    public PropuestaColaboracion obtenerDatosPropuestaColaboracion(Colaboracion colaboracion){
        PropuestaColaboracion propuestaObtenida = new PropuestaColaboracion();
        DAOPropuestaColaboracionImplementacion daoPropuestaColaboracion = new DAOPropuestaColaboracionImplementacion();
        daoPropuestaColaboracion.obtenerPropuestaDeColaboracionPorId(colaboracion.getIdPropuestaColaboracion());
        return propuestaObtenida;
    }
    
    public void cargarDatosColaboracion(PropuestaColaboracion propuestaActiva,List<Profesor> profesoresObtenidos, Colaboracion colaboracionObtenida){
        Profesor[] profesoresDeColaboracion = new Profesor[4];
        int numeroDeProfesor = 0;
        while(!profesoresObtenidos.isEmpty()){
            profesoresDeColaboracion[numeroDeProfesor] = profesoresObtenidos.get(numeroDeProfesor);
            numeroDeProfesor++;
        }   
        lbl_NombreColaboracion.setText(propuestaActiva.getExperienciaEducativa());
        lbl_TipoDeColaboracionDato.setText(propuestaActiva.getTipoColaboracion().getTipo());
        lbl_ObjetivoGeneralDato.setText(propuestaActiva.getObjetivo());
        lbl_Profesor1.setText(profesoresDeColaboracion[0].getNombre()+" "+profesoresDeColaboracion[0].getApellidoPaterno()+" "+profesoresDeColaboracion[0].getApellidoMaterno());
        lbl_Profesor2.setText(profesoresDeColaboracion[1].getNombre()+" "+profesoresDeColaboracion[1].getApellidoPaterno()+" "+profesoresDeColaboracion[1].getApellidoMaterno());
        if(profesoresDeColaboracion[2]!=null) {
            lbl_Profesor3.setText(profesoresDeColaboracion[2].getNombre()+" "+profesoresDeColaboracion[2].getApellidoPaterno()+" "+profesoresDeColaboracion[2].getApellidoMaterno());
        }else{
            lbl_Profesor3.setText("---------------------");
        }
        if(profesoresDeColaboracion[3]!=null){
            lbl_Profesor4.setText(profesoresDeColaboracion[3].getNombre()+" "+profesoresDeColaboracion[3].getApellidoPaterno()+" "+profesoresDeColaboracion[3].getApellidoMaterno());
        }else{
            lbl_Profesor4.setText("---------------------");
        }
        
    }
    
    public void asignarDatosDeColaboracion(){
        PropuestaColaboracion propuestaActiva = new PropuestaColaboracion();
        Colaboracion colaboracionActiva = new Colaboracion();
        List<Profesor> profesoresObtenidos = new ArrayList();
        try{
            Profesor profesorActivo = obtenerDatosDeProfesorSingleton();
            colaboracionActiva = obtenerDatosColaboracion(profesorActivo);
            profesoresObtenidos = obtenerProfesoresColaboracion(colaboracionActiva);
            propuestaActiva = obtenerDatosPropuestaColaboracion(colaboracionActiva);
            cargarDatosColaboracion(propuestaActiva,profesoresObtenidos,colaboracionActiva);
        }catch(IllegalArgumentException excepcion){
            LOG.error(excepcion.getCause());
            Alertas.mostrarMensajeErrorAlObtenerDatos();
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
