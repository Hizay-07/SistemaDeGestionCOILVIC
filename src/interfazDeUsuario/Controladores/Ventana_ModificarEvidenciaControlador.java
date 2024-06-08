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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import logicaDeNegocio.ClasesAuxiliares.ActividadAuxiliar;
import logicaDeNegocio.ClasesAuxiliares.ColaboracionAuxiliar;
import logicaDeNegocio.ClasesAuxiliares.EvidenciaAuxiliar;
import logicaDeNegocio.DAOImplementacion.DAOEvidenciaImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOUsuarioImplementacion;
import logicaDeNegocio.clases.Actividad;
import logicaDeNegocio.clases.Colaboracion;
import logicaDeNegocio.clases.Evidencia;
import logicaDeNegocio.clases.ProfesorSingleton;
import logicaDeNegocio.clases.UsuarioSingleton;
import logicaDeNegocio.manejadorDeArchivos.ManejadorDeArchivos;
import org.apache.log4j.Logger;

public class Ventana_ModificarEvidenciaControlador implements Initializable {
    
    private static final Logger LOG=Logger.getLogger(Ventana_RegistrarEvidenciaControlador.class);
    private Stage escenario;
    private File archivoASubir;
    @FXML
    private AnchorPane anchor_Ventana;
    @FXML
    private Pane pane_ColorSuperior;
    @FXML
    private Pane pane_ColorInferior;
    @FXML
    private Button btn_EscogerArchivo;
    @FXML
    private Label lbl_TituloVentana;
    @FXML
    private Label lbl_NombreEvidencia;
    @FXML
    private Label lbl_ArchivoSeleccionado;
    @FXML
    private TextField txfd_NombreEvidenciaModificador;
    @FXML
    private Label lbl_NombreArchivo;
    @FXML
    private Button btn_Guardar;  
    @FXML
    private Button btn_Cancelar;
    @FXML
    private FileChooser filechooser_Evidencia = new FileChooser();
    @FXML
    private Label lbl_ErrorNombreEvidencia;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatosEvidencia();
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
    
    private void cargarDatosEvidencia(){
        EvidenciaAuxiliar evidencia = EvidenciaAuxiliar.getEvidencia();
        txfd_NombreEvidenciaModificador.setText(evidencia.getNombre());
    }
    
    private boolean validarDatosEvidencia(){
        boolean resultado = true;
        Evidencia evidencia = new Evidencia();
        resultado &= validarAuxiliar(()->evidencia.setNombre(txfd_NombreEvidenciaModificador.getText()),lbl_ErrorNombreEvidencia);
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
    
    public void modificarEvidencia(){
        Evidencia nuevaEvidencia = new Evidencia();
        File archivoASubir = getArchivoASubir();
        ColaboracionAuxiliar colaboracionAuxiliar = ColaboracionAuxiliar.getInstancia();
        ActividadAuxiliar actividadAuxiliar = ActividadAuxiliar.getInstancia();
        Actividad actividad = new Actividad();
        Colaboracion colaboracion = new Colaboracion();
        actividad.setIdActividad(actividadAuxiliar.getIdActividad());
        colaboracion.setIdColaboracion(colaboracionAuxiliar.getIdColaboracion());
        ManejadorDeArchivos manejadorArchivos = new ManejadorDeArchivos();
        DAOEvidenciaImplementacion daoEvidencia = new DAOEvidenciaImplementacion();
        boolean resultadoAccesoACarpeta = manejadorArchivos.crearCarpetaDeActividad(actividad, colaboracion);
        int numeroDeEvidencias = daoEvidencia.obtenerNumeroDeEvidencia(actividad.getIdActividad()) + 1;
        if(resultadoAccesoACarpeta&&Objects.nonNull(archivoASubir)){
            if(numeroDeEvidencias>=0){
                if(!manejadorArchivos.validarExistenciaDeArchivo(actividad, colaboracion, archivoASubir, numeroDeEvidencias)){
                    String rutaArchivo = manejadorArchivos.guardarEvidenciaDeActividad(actividad, colaboracion, archivoASubir,numeroDeEvidencias);
                    if(validarDatosEvidencia()){
                        EvidenciaAuxiliar evidenciaPasada = EvidenciaAuxiliar.getEvidencia();
                        manejadorArchivos.borrarArchivoDeEvidencia(evidenciaPasada.getRutaEvidencia());
                        nuevaEvidencia.setNombre(txfd_NombreEvidenciaModificador.getText());
                        nuevaEvidencia.setRutaEvidencia(rutaArchivo);
                        nuevaEvidencia.setIdEvidencia(evidenciaPasada.getIdEvidencia());
                        int resultadoModificacion= daoEvidencia.modificarEvidencia(nuevaEvidencia);
                        switch(resultadoModificacion) {
                            case 1 -> {
                                Alertas.mostrarMensajeDatosModificados();
                                cancelarModificacion();
                            }
                            default -> {
                                Alertas.mostrarMensajeErrorEnLaConexion();
                                manejadorArchivos.borrarArchivoDeEvidencia(rutaArchivo);
                            }
                        }
                    }else{
                        manejadorArchivos.borrarArchivoDeEvidencia(rutaArchivo);
                        Alertas.mostrarMensajeDatosInvalidos();
                    }
                }else{
                    Alertas.mostrarMensajeArchivoASubirExistente();
                }
            }else{
                Alertas.mostrarMensajeErrorEnLaConexion();
            }
        }else{
            Alertas.mostrarMensajeArchivoSinSeleccionar();
        }
    }
    
    public void obtenerArchivoASubir(){
        lbl_NombreArchivo.setText("Seleccione un archivo");
        setArchivoASubir(null);
        filechooser_Evidencia.setTitle("Seleccionar Evidencia de actividad");
        restringirTiposDeArchivo();
        try{
            File archivoSeleccionado = filechooser_Evidencia.showOpenDialog(escenario);
            setArchivoASubir(archivoSeleccionado);
            lbl_NombreArchivo.setText(archivoSeleccionado.getName());
        }catch(NullPointerException excepcion){
            LOG.error(excepcion.getCause());
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
    
    public void cancelarModificacion(){
        String rutaVentanaFXML="/interfazDeUsuario/Ventana_Evidencias.fxml";
        desplegarVentanaCorrespondiente(rutaVentanaFXML); 
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
    
}
