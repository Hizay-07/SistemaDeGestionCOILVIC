package interfazDeUsuario.Controladores;

import com.itextpdf.text.Document;
import interfazDeUsuario.Alertas.Alertas;
import java.awt.Desktop;
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
import logicaDeNegocio.DAOImplementacion.DAOActividadImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOColaboracionProfesorImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOUsuarioImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOColaboracionImplementacion;
import logicaDeNegocio.manejadorDeArchivos.InformeImplementacion;
import logicaDeNegocio.clases.Actividad;
import logicaDeNegocio.clases.Colaboracion;
import logicaDeNegocio.clases.Profesor;
import logicaDeNegocio.clases.ProfesorSingleton;
import logicaDeNegocio.clases.PropuestaColaboracion;
import logicaDeNegocio.clases.UsuarioSingleton;
import logicaDeNegocio.enums.EnumColaboracion;
import org.apache.log4j.Logger;

public class Ventana_DetallesDeColaboracionControlador implements Initializable {

    private static final Logger LOG=Logger.getLogger(Ventana_ColaboracionesControlador.class);
    private Stage escenario;
    @FXML
    private AnchorPane anchor_Ventana;
    @FXML
    private Button btn_GenerarInforme;
    @FXML
    private Button btn_Syllabus;
    @FXML
    private Label lbl_IdColaboracionDato;
    @FXML
    private Label lbl_ProgramaEducativoDato;
    @FXML
    private Label lbl_FechaDeInicioDato;
    @FXML
    private Label lbl_FechaDeCierreDato;
    @FXML
    private Label lbl_EstadoDato;;
    @FXML
    private Label lbl_IdiomaDato;
    @FXML
    private Label lbl_ObjetivoDato;
    @FXML
    private Label lbl_Profesor1;
    @FXML
    private Label lbl_Profesor2;
    @FXML
    private Label lbl_Profesor3;
    @FXML
    private Label lbl_Profesor4;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UsuarioSingleton usuario = UsuarioSingleton.getInstancia();
        ColaboracionAuxiliar colaboracion = ColaboracionAuxiliar.getInstancia();
        if(usuario.getTipoDeUsuario().equals("Administrativo")&&colaboracion.getEstadoColaboracion().equals(EnumColaboracion.Finalizada.toString())){
            btn_GenerarInforme.setVisible(true);
        }else{
            btn_GenerarInforme.setVisible(false);
        }
        if(usuario.getTipoDeUsuario().equals("Administrativo")&&!colaboracion.getEstadoColaboracion().equals(EnumColaboracion.Activa.toString())){
            btn_Syllabus.setVisible(true);
        }else{
            btn_Syllabus.setVisible(false);
        }
        inicializarDatosColaboracion();
    } 
    
    private List<Profesor> obtenerProfesoresColaboracion(Colaboracion colaboracion){
        List<Profesor> profesoresObtenidos = new ArrayList();
        DAOColaboracionProfesorImplementacion daoColaboracionProfesor = new DAOColaboracionProfesorImplementacion();
        profesoresObtenidos = daoColaboracionProfesor.obtenerProfesoresPorIdColaboracion(colaboracion);
        return profesoresObtenidos;
    }
    
    private List<Actividad> obtenerActividadesColaboracion(Colaboracion colaboracion){
        List<Actividad> actividadesObtenidas = new ArrayList();
        DAOActividadImplementacion daoActividades = new DAOActividadImplementacion();
        actividadesObtenidas = daoActividades.obtenerActividades(colaboracion.getIdColaboracion());
        return actividadesObtenidas;
    }
    
    private void inicializarDatosColaboracion(){
        ColaboracionAuxiliar colaboracion = ColaboracionAuxiliar.getInstancia();
        PropuestaColaboracion propuestaDeColaboracion = colaboracion.getPropuestaColaboracion();
        Colaboracion colaboracionProfesores = new Colaboracion();
        colaboracionProfesores.setIdColaboracion(colaboracion.getIdColaboracion());
        List<Profesor> profesoresObtenidos = obtenerProfesoresColaboracion(colaboracionProfesores);
        Profesor[] profesoresDeColaboracion = new Profesor[4];
        int numeroDeProfesor = 0;
        while(numeroDeProfesor<profesoresObtenidos.size()){
            profesoresDeColaboracion[numeroDeProfesor] = profesoresObtenidos.get(numeroDeProfesor);
            numeroDeProfesor++;
        }  
        lbl_IdColaboracionDato.setText(Integer.toString(colaboracion.getIdColaboracion()));
        lbl_EstadoDato.setText(colaboracion.getEstadoColaboracion());
        lbl_ObjetivoDato.setText(propuestaDeColaboracion.getObjetivo());
        lbl_IdiomaDato.setText(propuestaDeColaboracion.getIdioma());
        lbl_ProgramaEducativoDato.setText(propuestaDeColaboracion.getProgramaEducativoEstudiantil());
        lbl_FechaDeCierreDato.setText(propuestaDeColaboracion.getFechaCierre());
        lbl_FechaDeInicioDato.setText(propuestaDeColaboracion.getFechaInicio());
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
    
    private Document obtenerInformeDeColaboracion(){
        Document informeGenerado = new Document();
        ColaboracionAuxiliar colaboracion = ColaboracionAuxiliar.getInstancia();
        Colaboracion colaboracionActual = new Colaboracion();
        colaboracionActual.setPropuestaColaboracion(colaboracion.getPropuestaColaboracion());
        colaboracionActual.setEstadoColaboracion(colaboracion.getEstadoColaboracion());
        colaboracionActual.setIdColaboracion(colaboracion.getIdColaboracion());
        colaboracionActual.setCantidadEstudiantes(colaboracion.getCantidadEstudiantes());
        List<Actividad> actividades = obtenerActividadesColaboracion(colaboracionActual);
        List<Profesor> profesores = obtenerProfesoresColaboracion(colaboracionActual);
        if(!actividades.get(0).getNombre().equals("Excepcion")&&!profesores.get(0).getNombre().equals("Excepcion")){
            InformeImplementacion creacionDeInforme = new InformeImplementacion();
            informeGenerado = creacionDeInforme.crearInformeDeColaboracion(colaboracionActual, actividades, profesores);
        }
        return informeGenerado;
    }
    
    private void guardarInforme(){
        FileChooser escogerRutaDeGuardado = new FileChooser();
        escogerRutaDeGuardado.setTitle("Seleccione el lugar donde desea guardar el informe");
        escogerRutaDeGuardado.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos PDF", "*.pdf"));
        escogerRutaDeGuardado.setInitialDirectory(new File(System.getProperty("user.home")));
        try{
            File archivoAGuardar = escogerRutaDeGuardado.showSaveDialog(escenario);
            InformeImplementacion guardadoImplementacion = new InformeImplementacion();
            ColaboracionAuxiliar colaboracion = ColaboracionAuxiliar.getInstancia();
            int resultadoGuardado = guardadoImplementacion.guardarArchivoDeInforme(archivoAGuardar,colaboracion.getIdColaboracion());
            if(resultadoGuardado==1){
                Alertas.mostrarMensajeInformeGuardadoExitosamente();
            }else{
                Alertas.mostrarMensajeErrorAlGuardarInforme();
            }
        }catch(NullPointerException excepcion){
            LOG.info(excepcion);
        }
    }   
    
    public void generarInforme(){
        InformeImplementacion implementacionInforme = new InformeImplementacion(); 
        if (!implementacionInforme.validarExistenciaDeInforme(ColaboracionAuxiliar.getInstancia().getIdColaboracion())) {
            if (obtenerResultadoValidacionConexion()) {
                Document informeGenerado = obtenerInformeDeColaboracion();
                if (Objects.nonNull(informeGenerado)) {
                    boolean resultadoAlerta = Alertas.mostrarMensajeDescargaDeArchivo();
                    if (resultadoAlerta) {
                        guardarInforme();
                    }
                } else {
                    Alertas.mostrarErrorEnLaCreacionDeInforme();
                }
            }else{
                salirAlInicioDeSesion();
            }
        } else {
            if (Alertas.mostrarMensajeArchivoGeneradoPreviamente()) {
                guardarInforme();
            }
        }
    }
    
    public void abrirSyllabusColaboracion(){
        if(obtenerResultadoValidacionConexion()){
            DAOColaboracionImplementacion daoColaboracion = new DAOColaboracionImplementacion();
            Colaboracion colaboracionActual = new Colaboracion();
            colaboracionActual.setIdColaboracion(ColaboracionAuxiliar.getInstancia().getIdColaboracion());
            String rutaArchivoObtenido = daoColaboracion.obtenerSyllabusColaboracion(colaboracionActual);
            if(rutaArchivoObtenido.isEmpty()){
                Alertas.mostrarMensajeErrorAlObtenerDatos();
            }else if(rutaArchivoObtenido.equals("Excepcion")){
                Alertas.mostrarMensajeErrorEnLaConexion();
                salirAlInicioDeSesion();
            }else{
                File archivoSyllabus = new File(rutaArchivoObtenido);
                if(archivoSyllabus.exists()&&archivoSyllabus.isFile()){
                    try{
                        Desktop.getDesktop().open(archivoSyllabus);
                    }catch(IOException excepcion){
                        LOG.error(excepcion);
                        Alertas.mostrarMensajeErrorAlObtenerDatos();
                    }
                }else{
                    Alertas.mostrarMensajeErrorAlAccederAlaCarpeta();
                }
            }
        }else{
            salirAlInicioDeSesion();
        }
    }
    
    public void regresarAVentanaCorrespondiente(){
        String rutaVentanaFXML="/interfazDeUsuario/Ventana_Colaboraciones.fxml";
        desplegarVentanaCorrespondiente(rutaVentanaFXML);  
    }
    
    public void visualizarActividades(){
        String rutaVentanaFXML="/interfazDeUsuario/Ventana_ActividadesColaboracion.fxml";
        desplegarVentanaCorrespondiente(rutaVentanaFXML);  
    }
    
    private void cerrarVentana(){
        escenario = (Stage)anchor_Ventana.getScene().getWindow();
        escenario.close();
    }
    
    private void desplegarVentanaCorrespondiente(String rutaVentanaFXML){
        if(obtenerResultadoValidacionConexion()){
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
            salirAlInicioDeSesion();
        }  
    }
    
    public int validarConexionEstable(){
        int resultado;
        DAOUsuarioImplementacion daoUsuario = new DAOUsuarioImplementacion();
        resultado = daoUsuario.confirmarConexionDeUsuario();
        return resultado;
    }
    
    public boolean obtenerResultadoValidacionConexion(){
        boolean resultadoValidacion = true;
        int resultado = validarConexionEstable();
        switch (resultado) {
            case 1:
                resultadoValidacion = true;
                break;
            case 0:
                Alertas.mostrarMensajeUsuarioNoEncontrado();
                resultadoValidacion = false;
                break;
            case -1:
                Alertas.mostrarMensajeErrorEnLaConexion();
                resultadoValidacion = false;
                break;
            default:
                break;
        }
        return resultadoValidacion;
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
