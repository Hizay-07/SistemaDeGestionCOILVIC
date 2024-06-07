package interfazDeUsuario.Controladores;

import interfazDeUsuario.Alertas.Alertas;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import logicaDeNegocio.ClasesAuxiliares.ColaboracionAuxiliar;
import logicaDeNegocio.ClasesAuxiliares.PropuestaColaboracionAuxiliar;
import logicaDeNegocio.DAOImplementacion.DAOColaboracionImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOColaboracionProfesorImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOProfesorImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOUsuarioImplementacion;
import logicaDeNegocio.clases.Colaboracion;
import logicaDeNegocio.clases.Profesor;
import logicaDeNegocio.clases.ProfesorSingleton;
import logicaDeNegocio.clases.PropuestaColaboracion;
import logicaDeNegocio.clases.UsuarioSingleton;
import logicaDeNegocio.enums.EnumColaboracion;
import logicaDeNegocio.enums.EnumProfesor;
import logicaDeNegocio.manejadorDeArchivos.ManejadorDeArchivos;
import org.apache.log4j.Logger;


public class Ventana_CerrarColaboracionControlador implements Initializable {
    
    private static final Logger LOG=Logger.getLogger(Ventana_CerrarColaboracionControlador.class);
    private Stage escenario;
    @FXML
    private AnchorPane anchor_Ventana;
    @FXML
    private Button btn_Aceptar;
    @FXML
    private Button btn_Cancelar;
    @FXML
    private Button btn_SubirArchivo;
    @FXML
    private Label lbl_NombreDeArchivo;
    @FXML
    private FileChooser filechooser_Syllabus = new FileChooser();
    private File archivoSyllabus;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbl_NombreDeArchivo.setText("Seleccione un archivo");
    }
    
    private File getArchivoSyllabus(){
        return this.archivoSyllabus;
    }
    
    private void setArchivoSyllabus(File archivo){
        this.archivoSyllabus = archivo;
    }
    
    private void restringirTipoDeArchivo(){
        FileChooser.ExtensionFilter archivosPDF = new FileChooser.ExtensionFilter("Archivos PDF (.pdf)", "*.pdf");
        filechooser_Syllabus.getExtensionFilters().add(archivosPDF);
    }
    
    public void obtenerArchivoDeSyllabus(){
        setArchivoSyllabus(null);
        lbl_NombreDeArchivo.setText("Seleccione un archivo");
        filechooser_Syllabus.setTitle("Seleccionar archivo de Syllabus");
        restringirTipoDeArchivo();
        try{
            File archivoSeleccionado = filechooser_Syllabus.showOpenDialog(escenario);
            setArchivoSyllabus(archivoSeleccionado);
            lbl_NombreDeArchivo.setText(archivoSeleccionado.getName());
        }catch(NullPointerException excepcion){
            LOG.error(excepcion.getCause());
        }
    }  
    
    private int cambiarDeEstadoProfesores(){
        int resultadoModificacionProfesores = 0;
        List<Profesor> profesores = new ArrayList();
        ColaboracionAuxiliar colaboracion = ColaboracionAuxiliar.getInstancia();
        Colaboracion colaboracionActual = new Colaboracion();
        colaboracionActual.setIdColaboracion(colaboracion.getIdColaboracion());
        DAOColaboracionProfesorImplementacion daoColaboracionProfesor = new DAOColaboracionProfesorImplementacion();
        profesores = daoColaboracionProfesor.obtenerProfesoresPorIdColaboracion(colaboracionActual);
        if(!profesores.isEmpty()){
            DAOProfesorImplementacion daoProfesor = new DAOProfesorImplementacion();
            for(int numeroDeProfesorDeLaLista=0;numeroDeProfesorDeLaLista<profesores.size();numeroDeProfesorDeLaLista++){
                Profesor profesor = profesores.get(numeroDeProfesorDeLaLista);
                resultadoModificacionProfesores += daoProfesor.cambiarEstadoProfesor(profesor.getIdProfesor(), EnumProfesor.Activo.toString());
            }
        }
        return resultadoModificacionProfesores;
    }
    
    private int cambiarDeEstadoColaboracion(){
        int resultadoModificacionColaboracion = 0;
        ColaboracionAuxiliar colaboracion = ColaboracionAuxiliar.getInstancia();
        Colaboracion colaboracionActual = new Colaboracion();
        colaboracionActual.setIdColaboracion(colaboracion.getIdColaboracion());
        DAOColaboracionImplementacion daoColaboracion = new DAOColaboracionImplementacion();
        resultadoModificacionColaboracion = daoColaboracion.cambiarEstadoColaboracion(EnumColaboracion.Cerrada.toString(), colaboracionActual);
        return resultadoModificacionColaboracion;
    }
    
    public void cerrarColaboracion(){
        boolean resultadoEleccion = Alertas.mostrarConfirmacionDeAccion("¿Desea cerrar esta colaboracion?, una vez cerrada ya no se podrá volver a acceder a ella.");
        if(resultadoEleccion){
            if(Objects.nonNull(this.archivoSyllabus)){
                Colaboracion colaboracion = new Colaboracion();
                colaboracion.setIdColaboracion(ColaboracionAuxiliar.getInstancia().getIdColaboracion());
                ManejadorDeArchivos manejadorArchivos = new ManejadorDeArchivos();
                int resultadoGuardadoDeSyllabus = manejadorArchivos.guardarSyllabusDeColaboracion(colaboracion, archivoSyllabus);
                if(resultadoGuardadoDeSyllabus==1){
                    int resultadoModificacionEstadoProfesores = cambiarDeEstadoProfesores();
                    int resultadoModificacionEstadoColaboracion = cambiarDeEstadoColaboracion();
                    if(resultadoModificacionEstadoProfesores>=2&&resultadoModificacionEstadoProfesores<=4&&resultadoModificacionEstadoColaboracion==1){
                        Alertas.mostrarColaboracionCerrada();
                        regresarAMenuPrincipalProfesor();
                    }else{
                        Alertas.mostrarMensajeErrorEnLaConexion();
                        salirAlInicioDeSesion();
                    }
                }else{
                    Alertas.mostrarMensajeErrorAlAccederAlaCarpeta();
                }
            }else{
                Alertas.mostrarMensajeArchivoSinSeleccionar();
            }
        }
    }
    
    public void regresarVentanaColaboracionActiva(){
        boolean resultadoEleccion = Alertas.mostrarConfirmacionDeAccion("¿Desea regresar a la ventana Colaboración Activa?");
        if(resultadoEleccion){
            String rutaVentanaFXML="/interfazDeUsuario/Ventana_ColaboracionActiva.fxml";
            desplegarVentanaCorrespondiente(rutaVentanaFXML);
        }  
    }
    
    public void regresarAMenuPrincipalProfesor(){
        String rutaVentanaFXML="/interfazDeUsuario/Ventana_MenuPrincipalProfesor.fxml";
        desplegarVentanaCorrespondiente(rutaVentanaFXML);  
    }
    
    private void cerrarVentana(){
        escenario = (Stage)anchor_Ventana.getScene().getWindow();
        escenario.close();
    }
        
    private void desplegarVentanaCorrespondiente(String rutaVentanaFXML){
        if(validarConexionEstable()){
            try{
            Parent root=FXMLLoader.load(getClass().getResource(rutaVentanaFXML));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show(); 
            cerrarVentana();
            }catch(IOException excepcion){
                LOG.error(excepcion);
                Alertas.mostrarMensajeErrorAlDesplegarVentana();
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
            LOG.error(excepcion.getCause());
            Alertas.mostrarMensajeErrorAlDesplegarVentana();
        }
    }
    
}
