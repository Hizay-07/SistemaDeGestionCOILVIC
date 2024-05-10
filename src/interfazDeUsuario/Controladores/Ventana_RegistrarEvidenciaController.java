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
import logicaDeNegocio.DAOImplementacion.DAOEvidenciaImplementacion;
import logicaDeNegocio.clases.Actividad;
import logicaDeNegocio.clases.Colaboracion;
import logicaDeNegocio.clases.Evidencia;
import org.apache.log4j.Logger;


public class Ventana_RegistrarEvidenciaController implements Initializable {
    
    private static final Logger LOG=Logger.getLogger(Ventana_RegistrarEvidenciaController.class);
    private Stage escenario;
    private File archivoASubir;
    @FXML
    private AnchorPane anchor_Ventana;
    @FXML
    private Pane pane_ColorSuperior;
    @FXML
    private Label lbl_TituloVentana;
    @FXML
    private Pane pane_ColorInferior;
    @FXML
    private Button btn_EscogerArchivo;
    @FXML
    private Label lbl_NombreEvidencia;
    @FXML
    private Label lbl_ArchivoSeleccionado;
    @FXML
    private TextField textField_NombreEvidencia;
    @FXML
    private Label lbl_NombreArchivo;
    @FXML
    private Button btn_SubirEvidencia;
    @FXML
    private Button btn_Cancelar;
    @FXML
    private TextField txfd_NombreEvidencia;
    @FXML
    private FileChooser filechooser_Evidencia = new FileChooser();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
    } 
    
    public void restringirTiposDeArchivo(){
        FileChooser.ExtensionFilter archivosWord2007 = new FileChooser.ExtensionFilter("Archivos de Word 2007 (.doc)", "*.doc");
        FileChooser.ExtensionFilter archivosWordActual = new FileChooser.ExtensionFilter("Archivos de Word (.docx)", "*.docx");
        FileChooser.ExtensionFilter archivosPDF = new FileChooser.ExtensionFilter("Archivos PDF (.pdf)", "*.pdf");
        FileChooser.ExtensionFilter archivosExcel = new FileChooser.ExtensionFilter("Archivos de Excel (.xlsx)", "*.xlsx");
        filechooser_Evidencia.getExtensionFilters().addAll(archivosWord2007,archivosWordActual,archivosPDF,archivosExcel);
    }
    public void obtenerArchivoASubir(){
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
    
    public void subirEvidencia(){
        Evidencia nuevaEvidencia = new Evidencia();
        File archivoASubir = getArchivoASubir();
        ColaboracionAuxiliar colaboracionAuxiliar = ColaboracionAuxiliar.getInstancia();
        ActividadAuxiliar actividadAuxiliar = ActividadAuxiliar.getInstancia();
        Actividad actividad = new Actividad();
        Colaboracion colaboracion = new Colaboracion();
        actividad.setIdActividad(actividadAuxiliar.getIdActividad());
        colaboracion.setIdColaboracion(colaboracionAuxiliar.getIdColaboracion());
        DAOEvidenciaImplementacion daoEvidencia = new DAOEvidenciaImplementacion();
        boolean resultadoAccesoACarpeta = daoEvidencia.crearCarpetaDeActividad(actividad, colaboracion);
        if(resultadoAccesoACarpeta&&Objects.nonNull(archivoASubir)){
            String rutaArchivo = daoEvidencia.guardarEvidenciaDeActividad(actividad, colaboracion, archivoASubir);
            try{
                nuevaEvidencia.setNombre(txfd_NombreEvidencia.getText());
                nuevaEvidencia.setRutaEvidencia(rutaArchivo);
                nuevaEvidencia.setIdActividad(actividad.getIdActividad());
                int resultadoInsercion = daoEvidencia.agregarEvidencia(nuevaEvidencia);
                switch(resultadoInsercion) {
                    case 1 -> Alertas.mostrarMensajeDatosIngresados();
                    case 0 -> Alertas.mostrarMensajeDatosDuplicados();
                    default -> {
                        Alertas.mostrarMensajeErrorEnLaConexion();
                        daoEvidencia.borrarArchivoDeEvidencia(rutaArchivo);
                    }
                }
            }catch(IllegalArgumentException excepcion){
                LOG.error(excepcion.getCause());
                daoEvidencia.borrarArchivoDeEvidencia(rutaArchivo);
                Alertas.mostrarMensajeDatosInvalidos();
            }
        }else{
            Alertas.mostrarMensajeErrorAlAccederAlaCarpeta();
        }
    }
    
    public File getArchivoASubir(){
        return this.archivoASubir;
    }
    public void setArchivoASubir(File archivo){
        archivoASubir = archivo;
    }
    public void cerrarVentana(){
        escenario = (Stage)anchor_Ventana.getScene().getWindow();
        escenario.close();
    }
    
    public void cancelarRegistro(){
        String rutaVentanaFXML="/interfazDeUsuario/Ventana_Evidencias.fxml";
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
            LOG.error(excepcion.getCause());
        }
        cerrarVentana();
    }
}