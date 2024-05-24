package interfazDeUsuario.Controladores;

import interfazDeUsuario.Alertas.Alertas;
import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import javafx.fxml.Initializable;
import logicaDeNegocio.DAOImplementacion.DAOProfesorImplementacion;
import logicaDeNegocio.clases.Profesor;
import logicaDeNegocio.clases.ProfesorSingleton;
import logicaDeNegocio.clases.UsuarioSingleton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logicaDeNegocio.DAOImplementacion.DAOPeticionColaboracionImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOPropuestaColaboracionImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOUsuarioImplementacion;
import logicaDeNegocio.clases.PropuestaColaboracion;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.enums.EnumProfesor;
import logicaDeNegocio.enums.EnumPropuestaColaboracion;
import logicaDeNegocio.enums.EnumTipoDeUsuario;
import org.apache.log4j.Logger;


public class Ventana_MenuPrincipalProfesorControlador implements Initializable{
    
    private static final Logger LOG=Logger.getLogger(Ventana_MenuPrincipalProfesorControlador.class);
    private Stage escenario;
    @FXML 
    private AnchorPane anchor_PanelPrincipal;
    @FXML 
    private Button btn_ColaboracionActiva;
    @FXML 
    private Button btn_ColaborarConProfesor;
    @FXML 
    private Button btn_ProponerColaboracion;
    @FXML 
    private Button btn_VerPeticionesDeColaboracion;
    @FXML 
    private Button btn_VisualizarColaboraciones;
    @FXML 
    private Button btn_Salir;
    @FXML
    private Label lbl_NombreProfesor;
    @FXML
    private Button btn_IniciarColaboracion;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn_ColaboracionActiva.setOnAction(Event ->{
            visualizarColaboracionActiva();
        });
        
        btn_ProponerColaboracion.setOnAction(Event ->{
            realizarPropuestaDeColaboracion();
        });
        
        btn_ColaborarConProfesor.setOnAction(Event ->{
            visualizarOfertasDeColaboracion();
        });
        
        btn_Salir.setOnAction(Event ->{
            regresarAlInicioDeSesion();
        });
        
        btn_VerPeticionesDeColaboracion.setOnAction(Event ->{
            visualizarPeticionesColaboracion();
        });
        
        btn_IniciarColaboracion.setOnAction(Event ->{
            visualizarInicioColaboracion();
        });
        
       
        mostrarMensajeProfesor();
    }    
       
    public void mostrarMensajeProfesor(){
        Usuario logger = new Usuario();
        logger.setTipoDeUsuario(EnumTipoDeUsuario.Logger.toString());
        DAOProfesorImplementacion daoProfesor = new DAOProfesorImplementacion();
        UsuarioSingleton usuario = UsuarioSingleton.getInstancia();
        Profesor profesorSesion = daoProfesor.obtenerProfesorPorIdUsuario(usuario.getIdUsuario(),logger);
        ProfesorSingleton.setInstancia(profesorSesion);
        String nombreCompleto = profesorSesion.getNombre() + " " + profesorSesion.getApellidoPaterno() + " " + profesorSesion.getApellidoMaterno();
        lbl_NombreProfesor.setText(nombreCompleto);
    }
    
    public boolean validarAutoridadDeColaboracion(){
        boolean resultadoValidacion=false;
        ProfesorSingleton profesor = ProfesorSingleton.getInstancia();
        DAOPropuestaColaboracionImplementacion daoPropuesta = new DAOPropuestaColaboracionImplementacion();
        int idPropuestaColaboracion = daoPropuesta.obtenerIdPropuestaColaboracionAprobadaPorIdProfesor(profesor.getIdProfesor());
        PropuestaColaboracion propuestaObtenida = daoPropuesta.obtenerPropuestaDeColaboracionPorId(idPropuestaColaboracion);
        if(Objects.nonNull(propuestaObtenida.getTipoColaboracion())&&propuestaObtenida.getEstadoPropuesta().equals(EnumPropuestaColaboracion.Aprobada.toString())){
            resultadoValidacion = true;
        }else{
            resultadoValidacion = false;
        }
        return resultadoValidacion;
    }
    
    public void visualizarColaboracionActiva(){
        ProfesorSingleton profesor = ProfesorSingleton.getInstancia();
        if(profesor.getEstado().equals(EnumProfesor.Colaborando.toString())){
            String rutafxml = "/interfazDeUsuario/Ventana_ColaboracionActiva.fxml";
            desplegarVentana(rutafxml);
        }else{
          Alertas.mostrarColaboracionInactiva();
        }
    }
    
    public void visualizarColaboraciones(){
        String rutafxml = "/interfazDeUsuario/Ventana_Colaboraciones.fxml";
        desplegarVentana(rutafxml);
    }
    
    public void realizarPropuestaDeColaboracion(){
        ProfesorSingleton profesor = ProfesorSingleton.getInstancia();
        if(profesor.getEstado().equals(EnumProfesor.Activo.toString())){
            String rutafxml = "/interfazDeUsuario/Ventana_ProponerColaboracion.fxml";
            desplegarVentana(rutafxml);
        }else{
            String mensaje = "No se pueden realizar propuestas de colaboración estando en una colaboracion activa";
            Alertas.mostrarMensajeColaboracionActiva(mensaje);
        }
    }
    
    public void visualizarOfertasDeColaboracion(){
        ProfesorSingleton profesor = ProfesorSingleton.getInstancia();
        
        if(profesor.getEstado().equals(EnumProfesor.Activo.toString())){
            String rutafxml = "/interfazDeUsuario/Ventana_OfertaDeColaboraciones.fxml" ;
            desplegarVentana(rutafxml);
        }else{
            String mensaje = "No se puede acceder a ofertas de colaboración estando en una colaboracion activa";
            Alertas.mostrarMensajeColaboracionActiva(mensaje);
        }
    }
     public void visualizarPeticionesColaboracion(){
        ProfesorSingleton profesor = ProfesorSingleton.getInstancia();
        if(validarAutoridadDeColaboracion()){
            if(profesor.getEstado().equals(EnumProfesor.Esperando.toString())){
            String rutafxml = "/interfazDeUsuario/Ventana_PeticionesDeColaboracion.fxml";
            desplegarVentana(rutafxml);
            }else if (profesor.getEstado().equals(EnumProfesor.Colaborando.toString())){
                String mensaje = "La colaboracion ya ha iniciado";
                Alertas.mostrarMensajeColaboracionActiva(mensaje);
            }else{
                String mensaje = "No tiene ninguna colaboracion por iniciar";
                Alertas.mostrarMensajeColaboracionActiva(mensaje);            
            }   
        }else{
            Alertas.mostrarMensajeAccesoAVentanaInvalida();
        }                         
    }
    
    public void visualizarInicioColaboracion(){
        ProfesorSingleton profesor = ProfesorSingleton.getInstancia();
        DAOPropuestaColaboracionImplementacion daoPropuesta = new DAOPropuestaColaboracionImplementacion();
        DAOPeticionColaboracionImplementacion daoPeticion = new DAOPeticionColaboracionImplementacion();
        int idPropuesta = daoPropuesta.obtenerIdPropuestaColaboracionAprobadaPorIdProfesor(profesor.getIdProfesor());
        List<Integer> peticionesAceptadas = daoPeticion.consultarIdProfesoresPorIdPropuestaColaboracionAceptadas(idPropuesta);
        if(profesor.getEstado().equals(EnumProfesor.Esperando.toString())&&peticionesAceptadas.size()>=1&&peticionesAceptadas.size()<=3){
            String rutafxml = "/interfazDeUsuario/Ventana_IniciarColaboracion.fxml";
            desplegarVentana(rutafxml);
        }else{
            String mensaje = "No se pueden iniciar una colaboración sin una propuesta de colaboración o sin peticiones de colaboracion aceptadas";
            Alertas.mostrarMensajeColaboracionActiva(mensaje);
        }   
    }

    public void desplegarVentana(String rutaFxml){
        if(validarConexionEstable()==true){
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
            Alertas.mostrarMensajeSinConexion();
            salirAlMenuPrincipal();
        }
    }
    
    public void regresarAlInicioDeSesion(){
         boolean resultadoEleccion = Alertas.mostrarConfirmacionDeAccion("¿Desea regresar al inicio de sesión?");
         if(resultadoEleccion){
             salirAlMenuPrincipal();
         }
    }
    
    public boolean validarConexionEstable(){
        boolean resultado;
        DAOUsuarioImplementacion daoUsuario = new DAOUsuarioImplementacion();
        resultado = daoUsuario.confirmarConexionDeUsuario();
        return resultado;
    }
     
    public void salirAlMenuPrincipal(){
            String rutaVentanaFXML = null;
            try{
                rutaVentanaFXML = "/interfazDeUsuario/Ventana_InicioDeSesion.fxml";
                Parent root=FXMLLoader.load(getClass().getResource(rutaVentanaFXML));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
                UsuarioSingleton.resetSingleton();
                ProfesorSingleton.resetSingleton();
                cerrarVentana();
            }catch(IOException excepcion){
                Alertas.mostrarMensajeErrorAlDesplegarVentana();
                LOG.error(excepcion.getCause());
            }
    }
    
     public void cerrarVentana(){
        escenario = (Stage) anchor_PanelPrincipal.getScene().getWindow();
        escenario.close();
    }
}
