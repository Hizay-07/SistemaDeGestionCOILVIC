package interfazDeUsuario.Controladores;

import interfazDeUsuario.Alertas.Alertas;
import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;
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
import logicaDeNegocio.enums.EnumProfesor;
import org.apache.log4j.Logger;


public class Ventana_MenuPrincipalProfesorController implements Initializable{
    
    private static final Logger LOG=Logger.getLogger(Ventana_MenuPrincipalProfesorController.class);
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
            salirDelMenuPrincipal();
        });
        
        mostrarMensajeProfesor();
    }    
       
    public void mostrarMensajeProfesor(){
        DAOProfesorImplementacion daoProfesor = new DAOProfesorImplementacion();
        UsuarioSingleton usuario = UsuarioSingleton.getInstancia();
        Profesor profesorSesion = daoProfesor.obtenerProfesorPorIdUsuario(usuario.getIdUsuario());
        ProfesorSingleton.setInstancia(profesorSesion);
        String nombreCompleto = profesorSesion.getNombre() + " " + profesorSesion.getApellidoPaterno() + " " + profesorSesion.getApellidoMaterno();
        lbl_NombreProfesor.setText(nombreCompleto);
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
    
    public void realizarPropuestaDeColaboracion(){
        ProfesorSingleton profesor = ProfesorSingleton.getInstancia();
        if(profesor.getEstado().equals(EnumProfesor.Disponible.toString())){
            String rutafxml = "/interfazDeUsuario/Ventana_ProponerColaboracion.fxml";
            desplegarVentana(rutafxml);
        }else{
            String mensaje = "No se pueden realizar propuestas de colaboración estando en una colaboracion activa";
            Alertas.mostrarMensajeColaboracionActiva(mensaje);
        }
    }
    
    public void visualizarOfertasDeColaboracion(){
        ProfesorSingleton profesor = ProfesorSingleton.getInstancia();
        
        if(profesor.getEstado().equals(EnumProfesor.Disponible.toString())){
            String rutafxml = "/interfazDeUsuario/Ventana_OfertaDeColaboraciones.fxml" ;
            desplegarVentana(rutafxml);
        }else{
            String mensaje = "No se puede acceder a ofertas de colaboración estando en una colaboracion activa";
            Alertas.mostrarMensajeColaboracionActiva(mensaje);
        }
    }

    public void desplegarVentana(String rutaFxml){
        try{
            Parent root=FXMLLoader.load(getClass().getResource(rutaFxml));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            cerrarVentana();
        }catch(IOException excepcion){
            LOG.error(excepcion.getCause());
        }
    }
     
    public void salirDelMenuPrincipal(){
         String rutaVentanaFXML = null;
        try{
            rutaVentanaFXML = "/interfazDeUsuario/Ventana_InicioDeSesion.fxml";
            Parent root=FXMLLoader.load(getClass().getResource(rutaVentanaFXML));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }catch(IOException excepcion){
            LOG.error(excepcion.getCause());
        }
        
        UsuarioSingleton.resetSingleton();
        ProfesorSingleton.resetSingleton();
        cerrarVentana();
    }
    
     public void cerrarVentana(){
        escenario = (Stage) anchor_PanelPrincipal.getScene().getWindow();
        escenario.close();
    }
    
}
