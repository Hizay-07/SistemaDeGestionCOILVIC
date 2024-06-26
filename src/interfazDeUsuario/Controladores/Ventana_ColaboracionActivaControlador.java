package interfazDeUsuario.Controladores;

import interfazDeUsuario.Alertas.Alertas;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOColaboracionProfesorImplementacion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.util.Objects;
import logicaDeNegocio.ClasesAuxiliares.ColaboracionAuxiliar;
import logicaDeNegocio.ClasesAuxiliares.PropuestaColaboracionAuxiliar;
import logicaDeNegocio.DAOImplementacion.DAOProfesorImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOPropuestaColaboracionImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOUsuarioImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOActividadImplementacion;
import logicaDeNegocio.clases.Colaboracion;
import logicaDeNegocio.clases.Profesor;
import logicaDeNegocio.clases.ProfesorSingleton;
import logicaDeNegocio.clases.PropuestaColaboracion;
import logicaDeNegocio.clases.UsuarioSingleton;
import logicaDeNegocio.clases.Actividad;
import logicaDeNegocio.enums.EnumPropuestaColaboracion;
import org.apache.log4j.Logger;

public class Ventana_ColaboracionActivaControlador implements Initializable {
    
    private static final Logger LOG=Logger.getLogger(Ventana_ColaboracionActivaControlador.class);
    private Stage escenario;
    @FXML 
    private AnchorPane anchor_Ventana;
    @FXML 
    private Label lbl_FechaDeInicioDato;
    @FXML 
    private Label lbl_FechaDeCierreDato;
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
        asignarDatosDeColaboracion();
    }   
    
    private void cerrarVentana(){
        escenario = (Stage)anchor_Ventana.getScene().getWindow();
        escenario.close();
    }
    
    public void cerrarColaboracion(){
        if(obtenerResultadoValidacionConexion()){
            if(validarAutoridadDeColaboracion()){
                PropuestaColaboracionAuxiliar propuesta = PropuestaColaboracionAuxiliar.getInstnacia();
                LocalDate fechaActual = LocalDate.now();
                LocalDate fechaCierrePropuesta = LocalDate.parse(propuesta.getFechaCierre());
                DAOActividadImplementacion daoActividad = new DAOActividadImplementacion();
                DAOColaboracionProfesorImplementacion daoColaboracionProfesor = new DAOColaboracionProfesorImplementacion();
                Profesor profesor = new Profesor();
                profesor.setIdProfesor(ProfesorSingleton.getInstancia().getIdProfesor());
                Colaboracion colaboracionObtenida = daoColaboracionProfesor.obtenerColaboracionPorIdProfesor(profesor,"Activa");
                if(colaboracionObtenida.getIdColaboracion()==0){
                   colaboracionObtenida = daoColaboracionProfesor.obtenerColaboracionPorIdProfesor(profesor,"Cerrada");
                }
                List<Actividad> actividades = daoActividad.obtenerActividades(colaboracionObtenida.getIdColaboracion());
                if(fechaActual.isEqual(fechaCierrePropuesta)||fechaActual.isAfter(fechaCierrePropuesta)){
                    if(actividades.size()>=3){
                        inicializarVentanaCerrarColaboracion();
                    }else{
                        Alertas.mostrarMensajeNoSePuedeCerrarColaboracion("Para cerrar la colaboración debe"
                                + " haber como mínimo 3 actividades registradas");
                    }
                }else{
                    Alertas.mostrarMensajeSinCerrarColaboracion();
                }
            }else{
                Alertas.mostrarMensajeNoSePuedeCerrarColaboracion("Solo el propietario de la colaboración"
                                + " puede cerrar la colaboracion");
            }
        }
    }
    
    public boolean validarAutoridadDeColaboracion(){
        boolean resultadoValidacion=false;
        ProfesorSingleton profesor = ProfesorSingleton.getInstancia();
        DAOPropuestaColaboracionImplementacion daoPropuesta = new DAOPropuestaColaboracionImplementacion();
        int idPropuestaColaboracion = daoPropuesta.obtenerIdPropuestaColaboracionPorEstadoPorIdProfesor(profesor.getIdProfesor(),EnumPropuestaColaboracion.Iniciada.toString());
        PropuestaColaboracion propuestaObtenida = daoPropuesta.obtenerPropuestaDeColaboracionPorId(idPropuestaColaboracion);
        if(Objects.nonNull(propuestaObtenida.getTipoColaboracion())&&propuestaObtenida.getEstadoPropuesta().equals(EnumPropuestaColaboracion.Iniciada.toString())){
            resultadoValidacion = true;
        }else{
            resultadoValidacion = false;
        }
        return resultadoValidacion;
    }
    
    public void cambiarEstadoProfesor(List<Profesor> profesoresColaboracion){
        DAOProfesorImplementacion daoProfesor = new DAOProfesorImplementacion();
        for(int numeroProfesor=0;numeroProfesor<profesoresColaboracion.size();numeroProfesor++){
            Profesor profesorObtenido = profesoresColaboracion.get(numeroProfesor);
            daoProfesor.cambiarEstadoProfesor(profesorObtenido.getIdProfesor(), "Activo");
        }
    }
     
    private Profesor obtenerDatosDeProfesorSingleton(){
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
    
    private List<Profesor> obtenerProfesoresColaboracion(Colaboracion colaboracion){
        List<Profesor> profesoresObtenidos = new ArrayList();
        DAOColaboracionProfesorImplementacion daoColaboracionProfesor = new DAOColaboracionProfesorImplementacion();
        profesoresObtenidos = daoColaboracionProfesor.obtenerProfesoresPorIdColaboracion(colaboracion);
        return profesoresObtenidos;
    }
    
    private Colaboracion obtenerDatosColaboracion(Profesor profesor){
        Colaboracion colaboracionObtenida = new Colaboracion();
        DAOColaboracionProfesorImplementacion daoColaboracionImplementacion = new DAOColaboracionProfesorImplementacion();
        colaboracionObtenida = daoColaboracionImplementacion.obtenerColaboracionPorIdProfesor(profesor,"Activa");
        if(Objects.isNull(colaboracionObtenida.getPropuestaColaboracion())){
            colaboracionObtenida = daoColaboracionImplementacion.obtenerColaboracionPorIdProfesor(profesor,"Cerrada");
        }
        return colaboracionObtenida;
    }
    
    
    private PropuestaColaboracion obtenerDatosPropuestaColaboracion(Colaboracion colaboracion){
        PropuestaColaboracion propuestaObtenida = new PropuestaColaboracion();
        DAOPropuestaColaboracionImplementacion daoPropuestaColaboracion = new DAOPropuestaColaboracionImplementacion();
        propuestaObtenida = daoPropuestaColaboracion.obtenerPropuestaDeColaboracionPorId(colaboracion.getPropuestaColaboracion().getIdPropuestaColaboracion());
        return propuestaObtenida;
    }
    
    public void cargarDatosColaboracion(PropuestaColaboracion propuestaActiva,List<Profesor> profesoresObtenidos, Colaboracion colaboracionObtenida){
        Profesor[] profesoresDeColaboracion = new Profesor[4];
        int numeroDeProfesor = 0;
        while(numeroDeProfesor<profesoresObtenidos.size()){
            profesoresDeColaboracion[numeroDeProfesor] = profesoresObtenidos.get(numeroDeProfesor);
            numeroDeProfesor++;
        }   
        lbl_NombreColaboracion.setText(propuestaActiva.getExperienciaEducativa());
        lbl_TipoDeColaboracionDato.setText(propuestaActiva.getTipoColaboracion().getTipo());
        lbl_ObjetivoGeneralDato.setText(propuestaActiva.getObjetivo());
        lbl_FechaDeInicioDato.setText(propuestaActiva.getFechaInicio());
        lbl_FechaDeCierreDato.setText(propuestaActiva.getFechaCierre());
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
    
    private void asignarDatosDeColaboracion(){
        PropuestaColaboracion propuestaActiva = new PropuestaColaboracion();
        Colaboracion colaboracionActiva = new Colaboracion();
        List<Profesor> profesoresObtenidos = new ArrayList();
        try{
            Profesor profesorActivo = obtenerDatosDeProfesorSingleton();
            colaboracionActiva = obtenerDatosColaboracion(profesorActivo);
            if(Objects.isNull(colaboracionActiva.getPropuestaColaboracion())&&Objects.isNull(colaboracionActiva)){
                Alertas.mostrarMensajeColaboracionActiva("Por el momento no hay ninguna colaboracion activa");
                regresarAMenuPrincipal();
            }else if(colaboracionActiva.getEstadoColaboracion().equals("Excepcion")){
                Alertas.mostrarMensajeErrorEnLaConexion();
                salirAlInicioDeSesion();
            }else{
                profesoresObtenidos = obtenerProfesoresColaboracion(colaboracionActiva);
                propuestaActiva = obtenerDatosPropuestaColaboracion(colaboracionActiva);
                if(profesoresObtenidos.get(0).getNombre().equals("Excepcion")||propuestaActiva.getEstadoPropuesta().equals("Excepcion")){
                    Alertas.mostrarMensajeErrorEnLaConexion();
                    salirAlInicioDeSesion();
                }else{
                    cargarDatosColaboracion(propuestaActiva,profesoresObtenidos,colaboracionActiva);
                    ColaboracionAuxiliar.setInstancia(colaboracionActiva);
                    PropuestaColaboracionAuxiliar.setInstancia(propuestaActiva);
                } 
            }
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
        String rutaVentanaFXML="/interfazDeUsuario/Ventana_ActividadesColaboracionActiva.fxml";
        desplegarVentanaCorrespondiente(rutaVentanaFXML);  
    }
    
    public void inicializarVentanaIniciarActividad(){
        PropuestaColaboracion propuesta = ColaboracionAuxiliar.getInstancia().getPropuestaColaboracion();
        LocalDate fechaCierreColaboracion = LocalDate.parse(propuesta.getFechaCierre());
        LocalDate fechaActual = LocalDate.now();
        if(fechaActual.equals(fechaCierreColaboracion)||fechaActual.isAfter(fechaCierreColaboracion)){
            Alertas.mostrarMensajeFechaDeCierreColaboracion();
        }else{
            String rutaVentanaFXML="/interfazDeUsuario/Ventana_IniciarActividad.fxml";
            desplegarVentanaCorrespondiente(rutaVentanaFXML);  
        } 
    }
    
    public void inicializarVentanaCerrarColaboracion(){    
        String rutaVentanaFXML="/interfazDeUsuario/Ventana_CerrarColaboracion.fxml";
        desplegarVentanaCorrespondiente(rutaVentanaFXML);   
    }
    
    public int validarConexionEstable(){
        int resultado;
        DAOUsuarioImplementacion daoUsuario = new DAOUsuarioImplementacion();
        resultado = daoUsuario.confirmarConexionDeUsuario();
        return resultado;
    }
    
    public void desplegarVentanaCorrespondiente(String rutaFxml){
        if(obtenerResultadoValidacionConexion()){
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
            salirAlInicioDeSesion();
        }
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
            LOG.error(excepcion.getCause());
            Alertas.mostrarMensajeErrorAlDesplegarVentana();
        }
    }
    
}
