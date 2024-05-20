package interfazDeUsuario.Controladores;

import envioDeCorreos.EnvioDeCorreo;
import interfazDeUsuario.Alertas.Alertas;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void mostrarPanelProfesorUV() {
        pane_ProfesorUV.setVisible(true);
        pane_Profesores.setVisible(false);
        llenarComboBoxAreaAcademica();
        llenarComboBoxRegionAcademica();
        btn_Aceptar.setOnAction(event -> registrarProfesorUV());
    }

    public void mostrarPanelProfesorExterno() {
        pane_ProfesorExterno.setVisible(true);
        pane_Profesores.setVisible(false);
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
        try {
            profesor.setNombre(txfd_Nombre.getText());
            profesor.setApellidoPaterno(txfd_ApellidoPaterno.getText());
            profesor.setApellidoMaterno(txfd_ApellidoMaterno.getText());
            profesor.setCorreo(txfd_Correo.getText());
        } catch (IllegalArgumentException excepcion) {
            Alertas.mostrarMensajeDatosInvalidos();
            LOG.info(excepcion);
        }
        return profesor;
    }

    public ProfesorUV obtenerProfesorUV() {
        ProfesorUV profesorUV = new ProfesorUV();
        String areaAcademica = (String) cmb_AreaAcademica.getSelectionModel().getSelectedItem();
        DAOAreaAcademicaImplementacion daoAreaAcademica = new DAOAreaAcademicaImplementacion();
        int idAreaAcademica = daoAreaAcademica.consultarIdDeAreaAcademicaPorArea(areaAcademica);
        String regionAcademica = (String) cmb_RegionAcademica.getSelectionModel().getSelectedItem();
        DAORegionAcademicaImplementacion daoRegionAcademica = new DAORegionAcademicaImplementacion();
        int idRegionAcademica = daoRegionAcademica.consultarIdDeRegionPorRegion(regionAcademica);
        try {
            profesorUV.setNumeroDePersonal(txfd_NumeroDePersonal.getText());
            profesorUV.setTipoDeContratacion(txfd_TipoDeContratacion.getText());
            profesorUV.setCategoriaDeContratacion(txfd_CategoriaDeContratacion.getText());
            profesorUV.setIdAreaAcademica(idAreaAcademica);
            profesorUV.setIdRegion(idRegionAcademica);
        } catch (IllegalArgumentException excepcion) {
            Alertas.mostrarMensajeDatosInvalidos();
            LOG.info(excepcion);
        }
        return profesorUV;
    }

    public void registrarProfesorUV() {
        Profesor profesor = obtenerProfesor();
        ProfesorUV profesorUV = obtenerProfesorUV();
        if (!profesor.validarAtributos() || !profesorUV.validarAtributos()) {
            return;
        }
        if(validarInexistenciaDeProfesor(profesor)){
            DAOProfesorImplementacion daoProfesor = new DAOProfesorImplementacion();
            daoProfesor.registrarProfesor(profesor);
            int idProfesor = daoProfesor.obtenerIdProfesorPorCorreo(profesor.getCorreo());
            profesorUV.setIdProfesor(idProfesor);
            DAOProfesorUVImplementacion daoProfesorUV = new DAOProfesorUVImplementacion();
            daoProfesorUV.registrarProfesorUV(profesorUV);
            obtenerDatosCuentaProfesor(profesor,"UV");
            limpiarInformacionProfesor();
            limpiarInformacionProfesorUV();
        }else{
            Alertas.mostrarMensajeUsuarioDuplicado();
        }
    }

    public ProfesorExterno obtenerProfesorExterno() {
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
        Profesor profesor = obtenerProfesor();
        ProfesorExterno profesorExterno = obtenerProfesorExterno();
        if (!profesor.validarAtributos() && !profesorExterno.validarAtributos()) {
            return;
        }
        if(validarInexistenciaDeProfesor(profesor)){
            DAOProfesorImplementacion daoProfesor = new DAOProfesorImplementacion();
            daoProfesor.registrarProfesor(profesor);
            int idProfesor = daoProfesor.obtenerIdProfesorPorCorreo(profesor.getCorreo());
            profesorExterno.setIdProfesor(idProfesor);
            DAOProfesorExternoImplementacion daoProfesorExterno = new DAOProfesorExternoImplementacion();
            daoProfesorExterno.registrarProfesorExterno(profesorExterno);
            obtenerDatosCuentaProfesor(profesor,"Externo");
            limpiarInformacionProfesor();
            limpiarInformacionProfesorExterno();
        }else{
            Alertas.mostrarMensajeUsuarioDuplicado();
        }
    }
    
    public boolean validarInexistenciaDeProfesor(Profesor profesor){
        boolean resultadoValidacion = true;
        DAOProfesorImplementacion daoProfesor = new DAOProfesorImplementacion();
        DAOUsuarioImplementacion daoUsuario = new DAOUsuarioImplementacion();
        int resultadoDuplicidadCorreoProfesor = daoProfesor.validarDuplicidadDeCorreo(profesor.getCorreo());
        int resultadoDuplicidadCorreoUsuario = daoUsuario.verificarDuplicidadNombreDeUsuario(profesor.getCorreo());
        if(resultadoDuplicidadCorreoProfesor>=1||resultadoDuplicidadCorreoUsuario>=1){
            resultadoValidacion = false;
        }else{
            resultadoValidacion = true;
        }
        return resultadoValidacion;
    }

    public void limpiarInformacionProfesor() {
        txfd_Nombre.setText("");
        txfd_ApellidoPaterno.setText("");
        txfd_ApellidoMaterno.setText("");
        txfd_Correo.setText("");
    }

    public void limpiarInformacionProfesorUV() {
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

    public void limpiarInformacionProfesorExterno() {
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
                int resultadoModificacion = daoProfesor.asignarUsuarioDeProfesorPorCorreo(usuario.getCorreo());
                if (resultadoModificacion == 0) {
                    eliminarUsuarioProfesor(usuario,tipoProfesor);
                    Alertas.mostrarMensajeProfesorConUsuario();
                } else {
                    int resultadoCorreo = mandarCorreo(usuario.getNombreUsuario(), usuario.getContrasenia());
                    if (resultadoCorreo == 1) {
                        Alertas.mostrarMensajeDatosIngresados();
                        salirDeLaVentana();
                    } else if (resultadoCorreo == -1) {
                        eliminarUsuarioProfesor(usuario,tipoProfesor);
                        Alertas.mostrarSinConexionAInternet("Por favor verifique su conexion a internet o el correo proporcionado antes de registrar un usuario de profesor");
                    }
                }
            }
            case -1 ->
                Alertas.mostrarMensajeErrorEnLaConexion();
            default ->
                Alertas.mostrarMensajeDatosDuplicados();
        }
    }
    
    public void eliminarUsuarioProfesor(Usuario usuario,String tipoProfesor){
        DAOUsuarioImplementacion daoUsuario = new DAOUsuarioImplementacion();
        DAOProfesorImplementacion daoProfesor = new DAOProfesorImplementacion();
        DAOProfesorExternoImplementacion daoProfesorExterno = new DAOProfesorExternoImplementacion();
        DAOProfesorUVImplementacion daoProfesorUV = new DAOProfesorUVImplementacion();
        if(tipoProfesor.equals("Externo")) {
            daoProfesorExterno.eliminarProfesorExterno(usuario.getNombreUsuario());
        }else if (tipoProfesor.equals("UV")) {
            daoProfesorUV.eliminarProfesorUV(usuario.getNombreUsuario());
        }
        daoProfesor.eliminarCuentaAsignadaAProfesor(usuario.getNombreUsuario());
        daoProfesor.eliminarProfesor(usuario.getNombreUsuario());
        daoUsuario.eliminarUsuario(usuario.getNombreUsuario());
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

    public int mandarCorreo(String usuario, String contrasenia) {
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
        String rutaVentanaFXML = null;
        try {
            rutaVentanaFXML = "/interfazDeUsuario/Ventana_MenuAdministrador.fxml";
            Parent root = FXMLLoader.load(getClass().getResource(rutaVentanaFXML));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException excepcion) {
            LOG.error(excepcion);
        }
        cerrarVentana();
    }

}
