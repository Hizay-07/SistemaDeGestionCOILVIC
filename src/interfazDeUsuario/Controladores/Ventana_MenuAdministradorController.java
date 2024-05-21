package interfazDeUsuario.Controladores;

import interfazDeUsuario.Alertas.Alertas;
import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;
import javafx.fxml.Initializable;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logicaDeNegocio.DAOImplementacion.DAOUsuarioImplementacion;
import logicaDeNegocio.clases.ProfesorSingleton;
import org.apache.log4j.Logger;

public class Ventana_MenuAdministradorController implements Initializable{
    
    private static final Logger LOG=Logger.getLogger(Ventana_MenuAdministradorController.class);
    private Stage escenario;
    @FXML
    private AnchorPane anchor_PanelPrincipal;
    @FXML
    private Label lbl_Administrador;
    @FXML
    private Button btn_RegistrarProfesor;
    @FXML
    private Button btn_ConaultarColaboracionesEnCurso;
    @FXML
    private Button btn_ConsultarProfesores;
    @FXML
    private Button btn_RegistrarRepresentante;;
    @FXML
    private Button btn_Salir;
    @FXML
    private Button btn_VisualizarPropuestasDeColaboracion;
    @FXML
    private Button btn_ConsultarRepresentanteInstitucional;
    @FXML
    private Button btn_CreacionDeUsuario;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn_Salir.setOnAction(Event ->{ 
            regresarAlInicioDeSesion();
        });   
        
        btn_RegistrarProfesor.setOnAction(Event ->{
            registrarProfesor();
        });
        
        btn_CreacionDeUsuario.setOnAction(Event ->{
            registrarUsuario();
        });
        
        btn_ConaultarColaboracionesEnCurso.setOnAction(Event->{ 
            mostrarVentanaColaboraciones();
        });
        
        btn_VisualizarPropuestasDeColaboracion.setOnAction(Event ->{
            visualizarPropuestasColaboracion();            
        });    
        
        btn_ConsultarProfesores.setOnAction(Event ->{
            consultarProfesores();
        });
        
        btn_RegistrarRepresentante.setOnAction(Event->{
            registrarRepresentanteInstitucional();
        });
        
        btn_ConsultarRepresentanteInstitucional.setOnAction(Event ->{ 
            consultarRepresentanteInstitucional();
        });
       
        mostrarDetallesUsuario();
    }
    
    public void mostrarDetallesUsuario(){
        UsuarioSingleton usuario = UsuarioSingleton.getInstancia();
        lbl_Administrador.setText(usuario.getNombreUsuario());
    }        
    
    public void mostrarVentanaColaboraciones(){
        String ruta = "/interfazDeUsuario/Ventana_Colaboraciones.fxml";
        desplegarVentana(ruta);
    }
    
    public void registrarProfesor(){
        String ruta = "/interfazDeUsuario/Ventana_RegistroDeProfesor.fxml";
        desplegarVentana(ruta);
    }
    
    public void registrarUsuario(){
        String ruta = "/interfazDeUsuario/Ventana_CreacionDeUsuario.fxml";
        desplegarVentana(ruta);
    }
    
    public void consultarProfesores(){
        String ruta = "/interfazDeUsuario/Ventana_Profesores.fxml";
        desplegarVentana(ruta);
    }
    
    public void registrarRepresentanteInstitucional(){
        String ruta = "/interfazDeUsuario/Ventana_RegistroDeRepresentanteInstitucional.fxml";
        desplegarVentana(ruta);
    }
    
    public void consultarRepresentanteInstitucional(){
        String ruta = "/interfazDeUsuario/Ventana_RepresentantesInstitucionales.fxml";
        desplegarVentana(ruta);
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
    
    public void visualizarPropuestasColaboracion(){
        String ruta = "/interfazDeUsuario/Ventana_PropuestasDeColaboracion.fxml";
        desplegarVentana(ruta);        
    }
}
