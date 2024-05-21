package interfazDeUsuario.Controladores;

import interfazDeUsuario.Alertas.Alertas;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import logicaDeNegocio.DAOImplementacion.DAOEmisionPropuestaImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOPropuestaColaboracionImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOColaboracionImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOColaboracionProfesorImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOPeticionColaboracionImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOProfesorImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOUsuarioImplementacion;
import logicaDeNegocio.clases.Colaboracion;
import logicaDeNegocio.clases.Profesor;
import logicaDeNegocio.clases.ProfesorSingleton;
import logicaDeNegocio.clases.PropuestaColaboracion;
import logicaDeNegocio.clases.UsuarioSingleton;
import logicaDeNegocio.enums.EnumColaboracion;
import org.apache.log4j.Logger;

public class Ventana_IniciarColaboracionControlador implements Initializable {
    private static final Logger LOG=Logger.getLogger(Ventana_IniciarColaboracionControlador.class);    

    private Stage stage_ventana;
    
    @FXML
    private AnchorPane anchor_IniciarColaboracion;

    @FXML
    private Spinner<Integer> spin_NumeroEstudiantes;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SpinnerValueFactory<Integer> valueFactory =new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 50, 1);        
        spin_NumeroEstudiantes.setValueFactory(valueFactory);                   
    }    
    
    public void cerrarVentana(){
        stage_ventana=(Stage) anchor_IniciarColaboracion.getScene().getWindow();
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
            ProfesorSingleton.resetSingleton();
            cerrarVentana();
        } catch (IOException excepcion) {
            Alertas.mostrarMensajeErrorAlDesplegarVentana();
            LOG.error(excepcion.getCause());
        }
    }
    
    public PropuestaColaboracion obtenerPropuesta(){
        DAOEmisionPropuestaImplementacion daoEmisionPropuesta=new DAOEmisionPropuestaImplementacion();
        ProfesorSingleton profesorSingleton = ProfesorSingleton.getInstancia();
        Profesor profesor=new Profesor();
        profesor.setIdProfesor(profesorSingleton.getIdProfesor());
        List<Integer> idPropuestas=daoEmisionPropuesta.consultarIdPropuestaDeColaboracionPorIdProfesor(profesor);
        DAOPropuestaColaboracionImplementacion daoPropuestaColaboracion=new DAOPropuestaColaboracionImplementacion();
        PropuestaColaboracion propuestaColaboracion=new PropuestaColaboracion();
        int indice=0;
        while(indice<idPropuestas.size()){
            propuestaColaboracion=daoPropuestaColaboracion.obtenerPropuestaDeColaboracionPorId(idPropuestas.get(indice));
            if(propuestaColaboracion.getEstadoPropuesta().equals("Aprobada")){
                indice=idPropuestas.size();
            }
            indice++;
        }        
        return propuestaColaboracion;                        
    }
    
    public Colaboracion obtenerColaboracion(){        
        PropuestaColaboracion propuestaColaboracion=new PropuestaColaboracion();
        propuestaColaboracion=obtenerPropuesta();
        Colaboracion colaboracion=new Colaboracion();
        try{
            colaboracion.setPropuestaColaboracion(propuestaColaboracion);
            colaboracion.setCantidadEstudiantes(obtenerCantidadEstudiantes());
            colaboracion.setEstadoColaboracion(EnumColaboracion.Activa.toString());
        }catch(IllegalArgumentException excepcion){
            LOG.info(excepcion);                        
            Alertas.mostrarMensajeDatosInvalidos(); 
        }
        return colaboracion;        
    }
    
    public void registrarColaboracion(){      
        Colaboracion colaboracion=obtenerColaboracion();        
        if(colaboracion!=null){
            DAOColaboracionImplementacion daoColaboracion=new DAOColaboracionImplementacion();
            daoColaboracion.registrarColaboracion(colaboracion);  
            int idPropuestaColaboracion=colaboracion.getPropuestaColaboracion().getIdPropuestaColaboracion();
            DAOPropuestaColaboracionImplementacion daoPropuestaColaboracion=new DAOPropuestaColaboracionImplementacion();
            daoPropuestaColaboracion.cambiarEstadoIniciadaPropuestaColaboracionPorId(idPropuestaColaboracion);                
            DAOProfesorImplementacion daoProfesor=new DAOProfesorImplementacion();
            ProfesorSingleton profesorSingleton = ProfesorSingleton.getInstancia();
            int idProfesor=profesorSingleton.getIdProfesor();
            daoProfesor.cambiarEstadoProfesor(idProfesor, "Colaborando");
            agregarProfesoresAColaboracion(idPropuestaColaboracion);
            Alertas.mostrarColaboracionIniciada();
            salirDeLaVentana();
        }                                
    }
    

    public void agregarProfesoresAColaboracion(int idPropuestaColaboracion){
        DAOColaboracionImplementacion daoColaboracion=new DAOColaboracionImplementacion();
        int idColaboracion=daoColaboracion.obtenerIdColaboracionPorIdPropuesta(idPropuestaColaboracion);
        DAOPeticionColaboracionImplementacion daoPeticionColaboracin=new DAOPeticionColaboracionImplementacion();        
        List<Integer> idProfesores=daoPeticionColaboracin.consultarIdProfesoresPorIdPropuestaColaboracionAceptadas(idPropuestaColaboracion);
        DAOColaboracionProfesorImplementacion daoColaboracionProfesor=new DAOColaboracionProfesorImplementacion();
        DAOProfesorImplementacion daoProfesor=new DAOProfesorImplementacion();        
        Profesor profesor=new Profesor();        
        Colaboracion colaboracion=new Colaboracion();
        colaboracion.setIdColaboracion(idColaboracion);
        for(Integer idProfesor : idProfesores){
            profesor.setIdProfesor(idProfesor);
            daoColaboracionProfesor.registrarColaboracionProfesor(profesor,colaboracion);            
            daoProfesor.cambiarEstadoProfesor(idProfesor,"Colaborando");
        }                            
    }
    
    public int obtenerCantidadEstudiantes(){  
        int cantidadEstudiantes=0;
        try{
            cantidadEstudiantes=spin_NumeroEstudiantes.getValue();              
        }catch(NumberFormatException excepcion){
            LOG.info(excepcion);
        }                        
        return cantidadEstudiantes;
    }        
    
}
