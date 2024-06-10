package interfazDeUsuario.Controladores;

import interfazDeUsuario.Alertas.Alertas;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import logicaDeNegocio.ClasesAuxiliares.ActividadAuxiliar;
import logicaDeNegocio.ClasesAuxiliares.ColaboracionAuxiliar;
import logicaDeNegocio.DAOImplementacion.DAOEvidenciaImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOUsuarioImplementacion;
import logicaDeNegocio.clases.Actividad;
import logicaDeNegocio.clases.Colaboracion;
import logicaDeNegocio.clases.Evidencia;
import logicaDeNegocio.clases.ProfesorSingleton;
import logicaDeNegocio.clases.UsuarioSingleton;
import logicaDeNegocio.manejadorDeArchivos.ManejadorDeArchivos;
import org.apache.log4j.Logger;

public class Ventana_RegistrarEvidenciaControlador implements Initializable {
    
    private static final Logger LOG=Logger.getLogger(Ventana_RegistrarEvidenciaControlador.class);
    private Stage escenario;
    private File archivoASubir;
    @FXML
    private AnchorPane anchor_Ventana;
    @FXML
    private Label lbl_NombreArchivo;
    @FXML
    private TextField txfd_NombreEvidencia;
    @FXML
    private FileChooser filechooser_Evidencia = new FileChooser();
    @FXML
    private Label lbl_ErrorNombreEvidencia;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ocultarLabelErrores();
    } 
    
    private void ocultarLabelErrores(){
        lbl_ErrorNombreEvidencia.setVisible(false);
    }
    
    private void restringirTiposDeArchivo(){
        FileChooser.ExtensionFilter archivosWord2007 = new FileChooser.ExtensionFilter("Archivos de Word 2007 (.doc)", "*.doc");
        FileChooser.ExtensionFilter archivosWordActual = new FileChooser.ExtensionFilter("Archivos de Word (.docx)", "*.docx");
        FileChooser.ExtensionFilter archivosPDF = new FileChooser.ExtensionFilter("Archivos PDF (.pdf)", "*.pdf");
        FileChooser.ExtensionFilter archivosExcel = new FileChooser.ExtensionFilter("Archivos de Excel (.xlsx)", "*.xlsx");
        filechooser_Evidencia.getExtensionFilters().addAll(archivosWord2007,archivosWordActual,archivosPDF,archivosExcel);
    }
    
    private boolean validarDatosEvidencia(){
        boolean resultado = true;
        Evidencia evidencia = new Evidencia();
        resultado &= validarAuxiliar(()->evidencia.setNombre(txfd_NombreEvidencia.getText()),lbl_ErrorNombreEvidencia);
        return resultado; 
    }    
    
    private boolean validarAuxiliar(Runnable setter, Label errorLabel){
        boolean resultado = true;
        try{
            setter.run();
            resultado = true;
        }catch(IllegalArgumentException | NullPointerException excepcion){
            LOG.info(excepcion);
            errorLabel.setVisible(true);
            resultado = false;
        }
        return resultado;
    }
    
    public void obtenerArchivoASubir(){
        setArchivoASubir(null);
        lbl_NombreArchivo.setText("Seleccione un archivo");
        filechooser_Evidencia.setTitle("Seleccionar Evidencia de actividad");
        restringirTiposDeArchivo();
        try{
            File archivoSeleccionado = filechooser_Evidencia.showOpenDialog(escenario);
            setArchivoASubir(archivoSeleccionado);
            lbl_NombreArchivo.setText(archivoSeleccionado.getName());
        }catch(NullPointerException excepcion){
            LOG.error(excepcion.getCause());
        }
    }
    
    public void subirEvidencia(){
        ocultarLabelErrores();
        Evidencia nuevaEvidencia = new Evidencia();
        File archivoASubir = getArchivoASubir();
        ColaboracionAuxiliar colaboracionAuxiliar = ColaboracionAuxiliar.getInstancia();
        ActividadAuxiliar actividadAuxiliar = ActividadAuxiliar.getInstancia();
        Actividad actividad = new Actividad();
        Colaboracion colaboracion = new Colaboracion();
        actividad.setIdActividad(actividadAuxiliar.getIdActividad());
        colaboracion.setIdColaboracion(colaboracionAuxiliar.getIdColaboracion());
        DAOEvidenciaImplementacion daoEvidencia = new DAOEvidenciaImplementacion();
        ManejadorDeArchivos manejadorArchivos = new ManejadorDeArchivos();
        boolean resultadoAccesoACarpeta = manejadorArchivos.crearCarpetaDeActividad(actividad, colaboracion);
        int numeroDeEvidencias = daoEvidencia.obtenerNumeroDeEvidencia(actividad.getIdActividad()) + 1;
        if(resultadoAccesoACarpeta&&Objects.nonNull(archivoASubir)){
            if(!manejadorArchivos.validarExistenciaDeArchivo(actividad, colaboracion, archivoASubir, numeroDeEvidencias)){
                String rutaArchivo = manejadorArchivos.guardarEvidenciaDeActividad(actividad, colaboracion, archivoASubir,numeroDeEvidencias);
                if(validarDatosEvidencia()){
                    if(numeroDeEvidencias>=0){
                            nuevaEvidencia.setNombre(txfd_NombreEvidencia.getText());
                            nuevaEvidencia.setRutaEvidencia(rutaArchivo);
                            nuevaEvidencia.setIdActividad(actividad.getIdActividad());
                            int resultadoInsercion = daoEvidencia.agregarEvidencia(nuevaEvidencia);
                            switch(resultadoInsercion) {
                                case 1 -> {
                                    Alertas.mostrarMensajeDatosIngresados();
                                    cancelarRegistro();
                                }
                                case 0 -> Alertas.mostrarMensajeDatosDuplicados();
                                default -> {
                                    Alertas.mostrarMensajeErrorEnLaConexion();
                                    manejadorArchivos.borrarArchivoDeEvidencia(rutaArchivo);
                                }
                            }
                    }else{
                        Alertas.mostrarMensajeErrorEnLaConexion();
                    }
                }else{
                    manejadorArchivos.borrarArchivoDeEvidencia(rutaArchivo);
                    Alertas.mostrarMensajeDatosInvalidos();
                }
            }else{
                Alertas.mostrarMensajeArchivoASubirExistente();
            }
        }else{
            Alertas.mostrarMensajeArchivoSinSeleccionar();
        }
    }
    
    private File getArchivoASubir(){
        return this.archivoASubir;
    }
    private void setArchivoASubir(File archivo){
        archivoASubir = archivo;
    }
    private void cerrarVentana(){
        escenario = (Stage)anchor_Ventana.getScene().getWindow();
        escenario.close();
    }
    
    public void cancelarRegistro(){
        String rutaVentanaFXML="/interfazDeUsuario/Ventana_Evidencias.fxml";
        desplegarVentanaCorrespondiente(rutaVentanaFXML); 
    }
    
    public int validarConexionEstable(){
        int resultado;
        DAOUsuarioImplementacion daoUsuario = new DAOUsuarioImplementacion();
        resultado = daoUsuario.confirmarConexionDeUsuario();
        return resultado;
    }
    
    public void desplegarVentanaCorrespondiente(String rutaFxml){
        int resultadoValidacionConexion = validarConexionEstable();
        if(resultadoValidacionConexion==1){
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
        }else if(resultadoValidacionConexion == 0){
            Alertas.mostrarMensajeUsuarioNoEncontrado();
        }else if(resultadoValidacionConexion == -1){
             Alertas.mostrarMensajeErrorEnLaConexion();
              salirAlInicioDeSesion();
        }
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
    
}
