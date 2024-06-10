package interfazDeUsuario.Controladores;

import envioDeCorreos.EnvioDeCorreo;
import interfazDeUsuario.Alertas.Alertas;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.function.Supplier;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logicaDeNegocio.ClasesAuxiliares.GeneradorDeContrasenias;
import logicaDeNegocio.DAOImplementacion.DAOAreaAcademicaImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOProfesorExternoImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOProfesorImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOProfesorUVImplementacion;
import logicaDeNegocio.DAOImplementacion.DAORegionAcademicaImplementacion;
import logicaDeNegocio.DAOImplementacion.DAORepresentanteInstitucionalImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOUsuarioImplementacion;
import logicaDeNegocio.clases.AreaAcademica;
import logicaDeNegocio.clases.Profesor;
import logicaDeNegocio.clases.ProfesorExterno;
import logicaDeNegocio.clases.ProfesorUV;
import logicaDeNegocio.clases.RegionAcademica;
import logicaDeNegocio.clases.RepresentanteInstitucional;
import logicaDeNegocio.clases.UsuarioSingleton;
import logicaDeNegocio.clases.Usuario;
import org.apache.log4j.Logger;

public class Ventana_RegistroDeProfesorControlador implements Initializable {

    private static final Logger LOG = Logger.getLogger(Ventana_RegistroDeProfesorControlador.class);
    @FXML
    private Pane pane_ProfesorUV;
    @FXML
    private Pane pane_Profesores;
    @FXML
    private Pane pane_ProfesorExterno;
    @FXML
    private ComboBox cmb_AreaAcademica;
    @FXML
    private ComboBox cmb_RegionAcademica;
    @FXML
    private ComboBox cmb_Universidad;
    @FXML
    private AnchorPane anchor_RegistoProfesor;
    private Stage escenario;
    @FXML
    private TextField txfd_Nombre;
    @FXML
    private TextField txfd_ApellidoPaterno;
    @FXML
    private TextField txfd_ApellidoMaterno;
    @FXML
    private TextField txfd_Correo;
    @FXML
    private TextField txfd_NumeroDePersonal;
    @FXML
    private TextField txfd_TipoDeContratacion;
    @FXML
    private TextField txfd_CategoriaDeContratacion;
    @FXML
    private Button btn_Aceptar;
    @FXML
    private Label lbl_ErrorUniversidad;
    @FXML
    private Label lbl_ErrorNoPersonal;
    @FXML
    private Label lbl_ErrorTipoDeContratacion;
    @FXML
    private Label lbl_ErrorCategoriaDeContratacion;
    @FXML
    private Label lbl_ErrorAreaAcademica;
    @FXML
    private Label lbl_ErrorrRegion;
    @FXML
    private Label lbl_ErrorNombre;
    @FXML
    private Label lbl_ErrorApellidoPaterno;
    @FXML
    private Label lbl_ErrorApellidoMaterno;
    @FXML
    private Label lbl_ErrorCorreo;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    private void ocultarLabelErrores(){
        lbl_ErrorCorreo.setVisible(false);
        lbl_ErrorApellidoMaterno.setVisible(false);
        lbl_ErrorApellidoPaterno.setVisible(false);
        lbl_ErrorNombre.setVisible(false);
        lbl_ErrorrRegion.setVisible(false);
        lbl_ErrorAreaAcademica.setVisible(false);
        lbl_ErrorCategoriaDeContratacion.setVisible(false);
        lbl_ErrorTipoDeContratacion.setVisible(false);
        lbl_ErrorNoPersonal.setVisible(false);
        lbl_ErrorUniversidad.setVisible(false);
    }
    
    private boolean validarDatosProfesor(){
        boolean resultado = true;
        Profesor profesor = new Profesor();
        resultado &= validarAuxiliar(()->profesor.setNombre(txfd_Nombre.getText()),lbl_ErrorNombre);
        resultado &= validarAuxiliar(()->profesor.setApellidoPaterno(txfd_ApellidoPaterno.getText()),lbl_ErrorApellidoPaterno);
        resultado &= validarAuxiliar(()->profesor.setApellidoMaterno(txfd_ApellidoMaterno.getText()),lbl_ErrorApellidoMaterno);
        resultado &= validarAuxiliar(()->profesor.setCorreo(txfd_Correo.getText().toLowerCase()),lbl_ErrorCorreo);
        return resultado;
    }
    
    private boolean validarDatosProfesorUV(){
        boolean resultado = true;
        ProfesorUV profesor = new ProfesorUV();
        resultado &= validarAuxiliar(()->profesor.setCategoriaDeContratacion(txfd_CategoriaDeContratacion.getText()),lbl_ErrorCategoriaDeContratacion);
        resultado &= validarAuxiliar(()->profesor.setNumeroDePersonal(txfd_NumeroDePersonal.getText()),lbl_ErrorNoPersonal);
        resultado &= validarAuxiliar(()->profesor.setTipoDeContratacion(txfd_TipoDeContratacion.getText()),lbl_ErrorTipoDeContratacion);
        resultado &= validarSeleccion(()->(String) cmb_AreaAcademica.getSelectionModel().getSelectedItem(),lbl_ErrorAreaAcademica);
        resultado &= validarSeleccion(()->(String) cmb_RegionAcademica.getSelectionModel().getSelectedItem(),lbl_ErrorrRegion);
        return resultado;
    }
    
    private boolean validarDatosProfesorExterno(){
        boolean resultado = true;
        resultado &= validarSeleccion(()->(String) cmb_Universidad.getSelectionModel().getSelectedItem(),lbl_ErrorUniversidad);
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
    
    private boolean validarSeleccion(Supplier<String> selector,Label errorLabel){
        boolean resultado = true;
        try{
            String seleccion = selector.get();
            if(!seleccion.isEmpty()){
                resultado = true;
            }
        }catch(IllegalArgumentException | NullPointerException excepcion){
            LOG.info(excepcion);
            errorLabel.setVisible(true);
            resultado = false;
        }
        return resultado;
    }

    public void mostrarPanelProfesorUV() {
        pane_ProfesorUV.setVisible(true);
        pane_ProfesorExterno.setVisible(false);
        llenarComboBoxAreaAcademica();
        llenarComboBoxRegionAcademica();
        btn_Aceptar.setOnAction(event -> registrarProfesorUV());
    }

    public void mostrarPanelProfesorExterno() {
        pane_ProfesorExterno.setVisible(true);
        pane_ProfesorUV.setVisible(false);
        llenarComboBoxUniversidad();
        btn_Aceptar.setOnAction(event -> registrarProfesorExterno());
    }

    public void llenarComboBoxAreaAcademica() {
        DAOAreaAcademicaImplementacion instancia = new DAOAreaAcademicaImplementacion();
        List<AreaAcademica> areasAcademicas = instancia.consultarAreasAcademicas();
        ObservableList<String> areas = FXCollections.observableArrayList();
        for (AreaAcademica area : areasAcademicas) {
            areas.add(area.getArea());
        }
        cmb_AreaAcademica.setItems(areas);
    }

    public void llenarComboBoxRegionAcademica() {
        DAORegionAcademicaImplementacion instancia = new DAORegionAcademicaImplementacion();
        List<RegionAcademica> regionesAcademicas = instancia.consultarRegionesAcademicas();
        ObservableList<String> regiones = FXCollections.observableArrayList();
        for (RegionAcademica region : regionesAcademicas) {
            regiones.add(region.getRegion());
        }
        cmb_RegionAcademica.setItems(regiones);
    }

    public void llenarComboBoxUniversidad() {
        DAORepresentanteInstitucionalImplementacion instancia = new DAORepresentanteInstitucionalImplementacion();
        List<RepresentanteInstitucional> representantesInstitucionales = instancia.obtenerRepresentantesInstitucionales();
        ObservableList<String> universidades = FXCollections.observableArrayList();
        for (RepresentanteInstitucional representanteInstitucional : representantesInstitucionales) {
            universidades.add(representanteInstitucional.getNombreInstitucion());
        }
        cmb_Universidad.setItems(universidades);
    }

    public void cerrarVentana() {
        escenario = (Stage) anchor_RegistoProfesor.getScene().getWindow();
        escenario.close();

    }

    public Profesor obtenerProfesor() {
        Profesor profesor = new Profesor();
        profesor.setNombre(txfd_Nombre.getText());
        profesor.setApellidoPaterno(txfd_ApellidoPaterno.getText());
        profesor.setApellidoMaterno(txfd_ApellidoMaterno.getText());
        profesor.setCorreo(txfd_Correo.getText().toLowerCase());
        return profesor;
    }

    private ProfesorUV obtenerProfesorUV() {
        ProfesorUV profesorUV = new ProfesorUV();
        String areaAcademica = (String) cmb_AreaAcademica.getSelectionModel().getSelectedItem();
        DAOAreaAcademicaImplementacion daoAreaAcademica = new DAOAreaAcademicaImplementacion();
        int idAreaAcademica = daoAreaAcademica.consultarIdDeAreaAcademicaPorArea(areaAcademica);
        String regionAcademica = (String) cmb_RegionAcademica.getSelectionModel().getSelectedItem();
        DAORegionAcademicaImplementacion daoRegionAcademica = new DAORegionAcademicaImplementacion();
        int idRegionAcademica = daoRegionAcademica.consultarIdDeRegionPorRegion(regionAcademica);
        profesorUV.setNumeroDePersonal(txfd_NumeroDePersonal.getText());
        profesorUV.setTipoDeContratacion(txfd_TipoDeContratacion.getText());
        profesorUV.setCategoriaDeContratacion(txfd_CategoriaDeContratacion.getText());
        profesorUV.setIdAreaAcademica(idAreaAcademica);
        profesorUV.setIdRegion(idRegionAcademica);
        return profesorUV;
    }

    public void registrarProfesorUV() {
        ocultarLabelErrores();
        if(validarDatosProfesor()&&validarDatosProfesorUV()){
            Profesor profesor = obtenerProfesor();
            ProfesorUV profesorUV = obtenerProfesorUV();
            if(validarInexistenciaDeProfesorUV(profesorUV)){
                if(validarInexistenciaDeProfesor(profesor)){
                    DAOProfesorImplementacion daoProfesor = new DAOProfesorImplementacion();
                    int resultadoRegistro = daoProfesor.registrarProfesor(profesor);
                    if(resultadoRegistro==1){
                        int idProfesor = daoProfesor.obtenerIdProfesorPorCorreo(profesor.getCorreo());
                        profesorUV.setIdProfesor(idProfesor);
                        DAOProfesorUVImplementacion daoProfesorUV = new DAOProfesorUVImplementacion();
                        daoProfesorUV.registrarProfesorUV(profesorUV);
                        obtenerDatosCuentaProfesor(profesor,"UV");
                        limpiarInformacionProfesor();
                        limpiarInformacionProfesorUV();
                    }else{
                        salirAlInicioDeSesion();
                        Alertas.mostrarMensajeErrorEnLaConexion();
                    }
                }
            }
        }else{
            Alertas.mostrarMensajeDatosInvalidos();
        }
        
    }

    private ProfesorExterno obtenerProfesorExterno() {
        ProfesorExterno profesorExterno = new ProfesorExterno();
        String universidad = (String) cmb_Universidad.getSelectionModel().getSelectedItem();
        DAORepresentanteInstitucionalImplementacion daoRepresentanteInstitucional = new DAORepresentanteInstitucionalImplementacion();
        int idRepresentanteInstitucional = daoRepresentanteInstitucional.consultarIdRepresentanteInstitucionalPorUniversidad(universidad);
        try {
            profesorExterno.setIdRepresentanteInstitucional(idRepresentanteInstitucional);
        } catch (IllegalArgumentException excepcion) {
            Alertas.mostrarMensajeDatosInvalidos();
            LOG.info(excepcion);
        }
        return profesorExterno;
    }

    public void registrarProfesorExterno() {
        ocultarLabelErrores();
        if(validarDatosProfesor()&&validarDatosProfesorExterno()){
            Profesor profesor = obtenerProfesor();
            ProfesorExterno profesorExterno = obtenerProfesorExterno();
            if(validarInexistenciaDeProfesor(profesor)){
                DAOProfesorImplementacion daoProfesor = new DAOProfesorImplementacion();
                int resultadoRegistro = daoProfesor.registrarProfesor(profesor);
                if(resultadoRegistro==1){
                    int idProfesor = daoProfesor.obtenerIdProfesorPorCorreo(profesor.getCorreo());
                    profesorExterno.setIdProfesor(idProfesor);
                    DAOProfesorExternoImplementacion daoProfesorExterno = new DAOProfesorExternoImplementacion();
                    daoProfesorExterno.registrarProfesorExterno(profesorExterno);
                    obtenerDatosCuentaProfesor(profesor,"Externo");
                    limpiarInformacionProfesor();
                    limpiarInformacionProfesorExterno();
                }else{
                    salirAlInicioDeSesion();
                    Alertas.mostrarMensajeErrorEnLaConexion();
                }
            }
        }else{
            Alertas.mostrarMensajeDatosInvalidos();
        }
    }
    
    private boolean validarInexistenciaDeProfesor(Profesor profesor){
        boolean resultadoValidacion = true;
        DAOProfesorImplementacion daoProfesor = new DAOProfesorImplementacion();
        DAOUsuarioImplementacion daoUsuario = new DAOUsuarioImplementacion();
        int resultadoDuplicidadCorreoProfesor = daoProfesor.validarDuplicidadDeCorreo(profesor.getCorreo());
        int resultadoDuplicidadCorreoUsuario = daoUsuario.verificarDuplicidadNombreDeUsuario(profesor.getCorreo());
        if(resultadoDuplicidadCorreoProfesor>=1||resultadoDuplicidadCorreoUsuario>=1){
            resultadoValidacion = false;
            Alertas.mostrarMensajeUsuarioDuplicado();
        }else if(resultadoDuplicidadCorreoProfesor==-1||resultadoDuplicidadCorreoUsuario==-1){
            resultadoValidacion = false;
            Alertas.mostrarMensajeErrorEnLaConexion();
        }else{
            resultadoValidacion = true;   
        }
        return resultadoValidacion;
    }
    
    private boolean validarInexistenciaDeProfesorUV(ProfesorUV profesor){
        boolean resultadoValidacion = true;
        DAOProfesorUVImplementacion daoProfesor = new DAOProfesorUVImplementacion();
        int resultadoValidacionInexistencia = daoProfesor.validarInexistenciaProfesorUV(profesor.getNumeroDePersonal());
        switch (resultadoValidacionInexistencia) {
            case 1 -> {
                resultadoValidacion = false;
                Alertas.mostrarMensajeNumeroDePersonalDuplicado();
            }
            case -1 -> {
                resultadoValidacion = false;
                Alertas.mostrarMensajeErrorEnLaConexion();
            }
            default -> resultadoValidacion=true;
        }
        return resultadoValidacion;
    }

    private void limpiarInformacionProfesor() {
        txfd_Nombre.setText("");
        txfd_ApellidoPaterno.setText("");
        txfd_ApellidoMaterno.setText("");
        txfd_Correo.setText("");
    }

    private void limpiarInformacionProfesorUV() {
        txfd_NumeroDePersonal.setText("");
        txfd_TipoDeContratacion.setText("");
        txfd_CategoriaDeContratacion.setText("");
        String prompText_AreaAcademica = cmb_AreaAcademica.getPromptText();
        cmb_AreaAcademica.getSelectionModel().clearSelection();
        cmb_AreaAcademica.setPromptText(prompText_AreaAcademica);
        String prompText_RegionAcademica = cmb_RegionAcademica.getPromptText();
        cmb_RegionAcademica.getSelectionModel().clearSelection();
        cmb_RegionAcademica.setPromptText(prompText_RegionAcademica);
    }

    private void limpiarInformacionProfesorExterno() {
        String prompText_Universidad = cmb_Universidad.getPromptText();
        cmb_Universidad.getSelectionModel().clearSelection();
        cmb_Universidad.setPromptText(prompText_Universidad);
    }

    public void registrarUsuarioProfesor(Usuario usuario, String tipoProfesor) {
        DAOUsuarioImplementacion daoUsuario = new DAOUsuarioImplementacion();
        DAOProfesorImplementacion daoProfesor = new DAOProfesorImplementacion();
        int resultadoRegistro = daoUsuario.registrarUsuario(usuario);
        switch (resultadoRegistro) {
            case 1 -> {
                int resultadoModificacion = daoProfesor.asignarUsuarioDeProfesorPorCorreo(usuario.getNombreUsuario());
                if (resultadoModificacion == 0) {
                    Alertas.mostrarMensajeProfesorConUsuario();
                } else {
                    int resultadoCorreo = mandarCorreo(usuario.getNombreUsuario(), usuario.getContrasenia());
                    if (resultadoCorreo == 1) {
                        Alertas.mostrarMensajeDatosIngresados();
                        salirDeLaVentana();
                    } else if (resultadoCorreo == -1) {
                        Alertas.mostrarSinConexionAInternet("No se mandó el correo por la falta de internet.\n Si desea mandar nuevamente la cuenta"
                                + " asignada al profesor registrado, consulte los profesores y presione el botón 'Reenviar Datos'.");
                        salirDeLaVentana();
                    }
                }
            }
            case -1 ->
                Alertas.mostrarMensajeErrorEnLaConexion();
            default ->
                Alertas.mostrarMensajeDatosDuplicados();
        }
    }
    
    
    public void obtenerDatosCuentaProfesor(Profesor profesor,String tipo){
        Usuario usuario = new Usuario();
        String contrasenia = GeneradorDeContrasenias.generarContraseña();
        try{
            usuario.setContrasenia(contrasenia);
            usuario.setNombreUsuario(profesor.getCorreo());
            usuario.setTipoDeUsuario("Profesor");
            usuario.setCorreo(profesor.getCorreo());
            registrarUsuarioProfesor(usuario,tipo);
        }catch(IllegalArgumentException excepcion){
            LOG.error(excepcion.getMessage());
        }          
    }

    private int mandarCorreo(String usuario, String contrasenia) {
        int resultadoEnvioDeCorreo;
        EnvioDeCorreo mandarCorreoCreacionDeUsuario = new EnvioDeCorreo();
        String asuntoCorreo = "Clave de acceso sistema coil vic";
        String cuerpoCorreo = "Se le ha registrado como profesor dentro del sistema COIL-VIC. \n\n"
                + "A continuación se le presentan sus claves de acceso para acceder al sistema: \n\n"
                + "Usuario: " + usuario + "\n\nContraseña: " + contrasenia + "\nBuen día\nAtte: Sistema de gestión COIL-VIC";
        String destinatario = usuario;
        mandarCorreoCreacionDeUsuario.setAsunto(asuntoCorreo);
        mandarCorreoCreacionDeUsuario.setContenido(cuerpoCorreo);
        mandarCorreoCreacionDeUsuario.setDestinatario(destinatario);
        resultadoEnvioDeCorreo = mandarCorreoCreacionDeUsuario.enviarCorreo();
        return resultadoEnvioDeCorreo;
    }

    public void salirDeLaVentana() {
        int resultadoValidacionConexion = validarConexionEstable();
        if(resultadoValidacionConexion==1){
            String rutaVentanaFXML = null;
            try {
                rutaVentanaFXML = "/interfazDeUsuario/Ventana_MenuAdministrador.fxml";
                Parent root = FXMLLoader.load(getClass().getResource(rutaVentanaFXML));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            } catch (IOException excepcion) {
                Alertas.mostrarMensajeErrorAlDesplegarVentana();
                LOG.error(excepcion);
            }
            cerrarVentana();
        }else if(resultadoValidacionConexion == 0){
            Alertas.mostrarMensajeUsuarioNoEncontrado();
        }else if(resultadoValidacionConexion == -1){
             Alertas.mostrarMensajeErrorEnLaConexion();
             salirAlInicioDeSesion();
        }
        
    }
    
    public int validarConexionEstable(){
        int resultado;
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
            cerrarVentana();
        } catch (IOException excepcion) {
            Alertas.mostrarMensajeErrorAlDesplegarVentana();
            LOG.error(excepcion.getCause());
        }
    }

}
