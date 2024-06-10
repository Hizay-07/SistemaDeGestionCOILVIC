package interfazDeUsuario.Controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import interfazDeUsuario.Alertas.Alertas;
import java.io.IOException;
import logicaDeNegocio.DAOImplementacion.DAOProfesorImplementacion;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logicaDeNegocio.ClasesAuxiliares.ProfesorAuxiliar;
import logicaDeNegocio.DAOImplementacion.DAOAreaAcademicaImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOProfesorExternoImplementacion;
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
import logicaDeNegocio.enums.EnumProfesor;
import org.apache.log4j.Logger;

public class Ventana_ActualizarPerfilPofesorControlador implements Initializable {
    
    private static final Logger LOG=Logger.getLogger(Ventana_ActualizarPerfilPofesorControlador.class);
    @FXML
    private AnchorPane anchor_Ventana;
    @FXML
    private TextField txfd_Nombre;
    @FXML
    private TextField txfd_ApellidoPaterno;
    @FXML
    private TextField txfd_ApellidoMaterno;
    @FXML
    private TextField txfd_Correo;
    @FXML
    private Button btn_Confirmar;
    @FXML
    private Button btn_Cancelar;
    @FXML
    private ComboBox cmb_EstadosProfesor;
    @FXML
    private Pane pane_ProfesorUV;
    @FXML
    private ComboBox cmb_AreaAcademica;
    @FXML
    private ComboBox cmb_RegionAcademica;
    @FXML
    private ComboBox cmb_Universidad;
    @FXML
    private TextField txfd_NumeroDePersonal;
    @FXML
    private TextField txfd_TipoDeContratacion;
    @FXML
    private TextField txfd_CategoriaDeContratacion;
    @FXML
    private Label lbl_ErrorNumeroPersonal;
    @FXML
    private Label lbl_ErrorTipoDeContratacion;
    @FXML
    private Label lbl_ErrorCategoriaDeContratacion;
    @FXML
    private Label lbl_ErrorNombre;
    @FXML
    private Label lbl_ErrorApellidoPaterno;
    @FXML
    private Label lbl_ErrorApellidoMaterno;
    @FXML
    private Label lbl_ErrorCorreo;
    private Stage escenario;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn_Cancelar.setOnAction(event -> {
           regresarDeVentana();
        });
        llenarComboBoxAreaAcademica();
        llenarComboBoxRegionAcademica();
        llenarComboBoxUniversidad();
        cargarDatosComboBoxEstadoProfesor();
        cargarDatosProfesor(); 
        ocultarLabelErrores();
    }
    
    private void ocultarLabelErrores(){
        lbl_ErrorCorreo.setVisible(false);
        lbl_ErrorApellidoMaterno.setVisible(false);
        lbl_ErrorApellidoPaterno.setVisible(false);
        lbl_ErrorNombre.setVisible(false);
        lbl_ErrorCategoriaDeContratacion.setVisible(false);
        lbl_ErrorTipoDeContratacion.setVisible(false);
        lbl_ErrorNumeroPersonal.setVisible(false);
    }
    
    private void cerrarVentana(){
        escenario = (Stage) anchor_Ventana.getScene().getWindow();
        escenario.close();
    }
    
    private void cargarDatosProfesor() {
        ProfesorAuxiliar profesor = ProfesorAuxiliar.getInstancia();
        txfd_Nombre.setText(profesor.getNombre());
        txfd_ApellidoPaterno.setText(profesor.getApellidoPaterno());
        txfd_ApellidoMaterno.setText(profesor.getApellidoMaterno());
        txfd_Correo.setText(profesor.getCorreo());
        ProfesorUV profesorUv = obtenerdatosProfesorUV();
        ProfesorExterno profesorExterno = obtenerDatosProfesorExterno();
        if(Objects.nonNull(profesorUv)){
            pane_ProfesorUV.setVisible(true);
            cmb_Universidad.setVisible(false);
            txfd_NumeroDePersonal.setText(profesorUv.getNumeroDePersonal());
            txfd_TipoDeContratacion.setText(profesorUv.getTipoDeContratacion());
            txfd_CategoriaDeContratacion.setText(profesorUv.getCategoriaDeContratacion());
            cargarDatosComboboxProfesorUV(profesorUv);
            btn_Confirmar.setOnAction(event->{validarDuplicidadDeCamposNuevosProfesorUV(profesorUv);});
        }else if(Objects.nonNull(profesorExterno)){
            pane_ProfesorUV.setVisible(false);
            cmb_Universidad.setVisible(true);
            cargarDatosComboboxProfesorExterno(profesorExterno);
            btn_Confirmar.setOnAction(event->validarDuplicidadDeCamposNuevosProfesorExterno(profesorExterno));
        }
    }
    
    private void cargarDatosComboboxProfesorUV(ProfesorUV profesor){
        DAOAreaAcademicaImplementacion instanciaArea = new DAOAreaAcademicaImplementacion();
        List<AreaAcademica> areasAcademicas = instanciaArea.consultarAreasAcademicas();
        DAORegionAcademicaImplementacion instanciaRegion = new DAORegionAcademicaImplementacion();
        List<RegionAcademica> regionesAcademicas = instanciaRegion.consultarRegionesAcademicas();
        for(int numeroAreaAcademica=0;numeroAreaAcademica<areasAcademicas.size();numeroAreaAcademica++){
            AreaAcademica area = areasAcademicas.get(numeroAreaAcademica);
            if(area.getIdAreaAcademica()==profesor.getIdAreaAcademica()){
                cmb_AreaAcademica.setValue(area.getArea());
            }
        }
        for(int numeroRegion=0;numeroRegion<regionesAcademicas.size();numeroRegion++){
            RegionAcademica region = regionesAcademicas.get(numeroRegion);
            if(region.getIdRegionAcademica()==profesor.getIdRegion()){
                cmb_RegionAcademica.setValue(region.getRegion());
            }
        }
    }
    
    private void cargarDatosComboboxProfesorExterno(ProfesorExterno profesor){
        DAORepresentanteInstitucionalImplementacion instancia = new DAORepresentanteInstitucionalImplementacion();
        List<RepresentanteInstitucional> representantesInstitucionales = instancia.obtenerRepresentantesInstitucionales();
        for(int numeroUniversidad=0;numeroUniversidad<representantesInstitucionales.size();numeroUniversidad++){
            RepresentanteInstitucional representante = representantesInstitucionales.get(numeroUniversidad);
            if(representante.getIdRepresentanteInstitucional()==profesor.getIdRepresentanteInstitucional()){
                cmb_Universidad.setValue(representante.getNombreInstitucion());
            }
        }
    }
    
    private ProfesorUV obtenerdatosProfesorUV(){
        ProfesorAuxiliar profesor = ProfesorAuxiliar.getInstancia();
        ProfesorUV profesorUv = new ProfesorUV();
        DAOProfesorUVImplementacion daoProfesorUv = new DAOProfesorUVImplementacion();
        profesorUv = daoProfesorUv.obtenerProfesorUVPorIDProfesor(profesor.getIdProfesor());
        return profesorUv;
    }
    
    private ProfesorExterno obtenerDatosProfesorExterno(){
        ProfesorAuxiliar profesor = ProfesorAuxiliar.getInstancia();
        ProfesorExterno profesorExterno = new ProfesorExterno();
        DAOProfesorExternoImplementacion daoProfesorExterno = new DAOProfesorExternoImplementacion();
        profesorExterno = daoProfesorExterno.obtenerProfesorExternoPorIDProfesor(profesor.getIdProfesor());
        return profesorExterno;
    }
    
    private void cargarDatosComboBoxEstadoProfesor(){
        String estadoProfesor = ProfesorAuxiliar.getInstancia().getEstado();
        if(estadoProfesor.equals(EnumProfesor.Activo.toString())||estadoProfesor.equals(EnumProfesor.Archivado.toString())){
            ObservableList<String> EstadoDeProfesor = FXCollections.observableArrayList();
            for(EnumProfesor estadosProfesor : EnumProfesor.values()){
                if(estadosProfesor == EnumProfesor.Activo || estadosProfesor == EnumProfesor.Archivado){
                EstadoDeProfesor.add(estadosProfesor.toString());
                }
            }
            cmb_EstadosProfesor.setItems(EstadoDeProfesor);
        }else{
            cmb_EstadosProfesor.setVisible(false);
        }
        cmb_EstadosProfesor.setValue(estadoProfesor);
    }
    
    private void llenarComboBoxAreaAcademica() {
        DAOAreaAcademicaImplementacion instancia = new DAOAreaAcademicaImplementacion();
        List<AreaAcademica> areasAcademicas = instancia.consultarAreasAcademicas();
        ObservableList<String> areas = FXCollections.observableArrayList();
        for (AreaAcademica area : areasAcademicas) {
            areas.add(area.getArea());
        }
        cmb_AreaAcademica.setItems(areas);
    }

    private void llenarComboBoxRegionAcademica() {
        DAORegionAcademicaImplementacion instancia = new DAORegionAcademicaImplementacion();
        List<RegionAcademica> regionesAcademicas = instancia.consultarRegionesAcademicas();
        ObservableList<String> regiones = FXCollections.observableArrayList();
        for (RegionAcademica region : regionesAcademicas) {
            regiones.add(region.getRegion());
        }
        cmb_RegionAcademica.setItems(regiones);
    }

    private void llenarComboBoxUniversidad() {
        DAORepresentanteInstitucionalImplementacion instancia = new DAORepresentanteInstitucionalImplementacion();
        List<RepresentanteInstitucional> representantesInstitucionales = instancia.obtenerRepresentantesInstitucionales();
        ObservableList<String> universidades = FXCollections.observableArrayList();
        for (RepresentanteInstitucional representanteInstitucional : representantesInstitucionales) {
            universidades.add(representanteInstitucional.getNombreInstitucion());
        }
        cmb_Universidad.setItems(universidades);
    }
    
    private void actualizarPerfilProfesorUv(Profesor profesorAActualizar, ProfesorUV profesorUV) {
        DAOProfesorImplementacion daoProfesor = new DAOProfesorImplementacion();
        DAOProfesorUVImplementacion daoProfesorUV = new DAOProfesorUVImplementacion();
        String correoProfesor = ProfesorAuxiliar.getInstancia().getCorreo();
        int filasAfectadas = daoProfesor.modificarNombreProfesor(profesorAActualizar.getNombre(), correoProfesor);
        filasAfectadas += daoProfesor.modificarApellidoPaternoProfesor(profesorAActualizar.getApellidoPaterno(), correoProfesor);
        filasAfectadas += daoProfesor.modificarApellidoMaternoProfesor(profesorAActualizar.getApellidoMaterno(), correoProfesor);
        filasAfectadas += daoProfesor.modificarCorreoProfesor(profesorAActualizar.getCorreo(), correoProfesor);
        filasAfectadas += daoProfesor.cambiarEstadoProfesor(ProfesorAuxiliar.getInstancia().getIdProfesor(), profesorAActualizar.getEstado());
        filasAfectadas += daoProfesorUV.editarCategoriaDeContratacionDeProfesorUVPorIdProfesorUV(profesorUV.getCategoriaDeContratacion(), profesorUV.getIdProfesorUV());
        filasAfectadas += daoProfesorUV.editarRegionDeProfesorUVPorIdProfesorUV(profesorUV.getIdRegion(), profesorUV.getIdProfesorUV());
        filasAfectadas += daoProfesorUV.editarAreaAcademicaDeProfesorUVPorIdProfesorUV(profesorUV.getIdAreaAcademica(), profesorUV.getIdProfesorUV());
        filasAfectadas += daoProfesorUV.editarTipoDeContratacionDeProfesorUVPorIdProfesorUV(profesorUV.getTipoDeContratacion(), profesorUV.getIdProfesorUV());
        filasAfectadas += daoProfesorUV.editarNumeroDePersonalPorIdProfesorUV(profesorUV.getNumeroDePersonal(), profesorUV.getIdProfesorUV());
        if (filasAfectadas >= 9) {
            Alertas.mostrarMensajeDatosModificados();
            regresarDeVentana();
        } else {
            Alertas.mostrarMensajeErrorEnLaConexion();
        }
    }
    
    private void actualizarPerfilProfesorExterno(Profesor profesorNuevo,ProfesorExterno profesorExterno){
        DAOProfesorImplementacion daoProfesor = new DAOProfesorImplementacion();
        DAOProfesorExternoImplementacion daoProfesorExterno = new DAOProfesorExternoImplementacion();
        String correoProfesor = ProfesorAuxiliar.getInstancia().getCorreo();
        int filasAfectadas = daoProfesor.modificarNombreProfesor(profesorNuevo.getNombre(), correoProfesor);
        filasAfectadas += daoProfesor.modificarApellidoPaternoProfesor(profesorNuevo.getApellidoPaterno(), correoProfesor);
        filasAfectadas += daoProfesor.modificarApellidoMaternoProfesor(profesorNuevo.getApellidoMaterno(), correoProfesor);
        filasAfectadas += daoProfesor.modificarCorreoProfesor(profesorNuevo.getCorreo(), correoProfesor);
        filasAfectadas += daoProfesor.cambiarEstadoProfesor(ProfesorAuxiliar.getInstancia().getIdProfesor(), profesorNuevo.getEstado());
        filasAfectadas += daoProfesorExterno.editarInstitucionProfesorExternoPorIdProfesor(profesorExterno.getIdRepresentanteInstitucional(), profesorExterno.getIdProfesorExterno());
        if(filasAfectadas==6){
            Alertas.mostrarMensajeDatosModificados();
            regresarDeVentana();
        }else{
            Alertas.mostrarMensajeErrorEnLaConexion();
        }
    }
    
    private Profesor obtenerDatosProfesorAModificar(){
        Profesor profesorAActualizar = new Profesor();
        profesorAActualizar.setNombre(txfd_Nombre.getText());
        profesorAActualizar.setApellidoMaterno(txfd_ApellidoMaterno.getText());
        profesorAActualizar.setApellidoPaterno(txfd_ApellidoPaterno.getText());
        profesorAActualizar.setCorreo(txfd_Correo.getText().toLowerCase());
        profesorAActualizar.setEstado((String) cmb_EstadosProfesor.getSelectionModel().getSelectedItem());
        return profesorAActualizar;
    }
    
    private ProfesorUV obtenerDatosProfesorUVAModificar(){
        ProfesorUV profesorAActualizar = new ProfesorUV();
        profesorAActualizar.setCategoriaDeContratacion(txfd_CategoriaDeContratacion.getText());
        profesorAActualizar.setNumeroDePersonal(txfd_NumeroDePersonal.getText());
        profesorAActualizar.setTipoDeContratacion(txfd_TipoDeContratacion.getText());
        String areaAcademica = (String) cmb_AreaAcademica.getSelectionModel().getSelectedItem();
        DAOAreaAcademicaImplementacion daoAreaAcademica = new DAOAreaAcademicaImplementacion();
        int idAreaAcademica = daoAreaAcademica.consultarIdDeAreaAcademicaPorArea(areaAcademica);
        String regionAcademica = (String) cmb_RegionAcademica.getSelectionModel().getSelectedItem();
        DAORegionAcademicaImplementacion daoRegionAcademica = new DAORegionAcademicaImplementacion();
        int idRegionAcademica = daoRegionAcademica.consultarIdDeRegionPorRegion(regionAcademica);
        profesorAActualizar.setIdAreaAcademica(idAreaAcademica);
        profesorAActualizar.setIdRegion(idRegionAcademica);
        return profesorAActualizar;
    }
    
    private ProfesorExterno obtenerDatosProfesorExternoAModificar(){
        ProfesorExterno profesorExternoAActualizar = new ProfesorExterno();
        DAORepresentanteInstitucionalImplementacion daoProfesorExterno = new DAORepresentanteInstitucionalImplementacion();
        String universidad = (String) cmb_Universidad.getSelectionModel().getSelectedItem();
        int idUniversidad = daoProfesorExterno.consultarIdRepresentanteInstitucionalPorUniversidad(universidad);
        profesorExternoAActualizar.setIdRepresentanteInstitucional(idUniversidad);
        return profesorExternoAActualizar; 
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
        resultado &= validarAuxiliar(()->profesor.setNumeroDePersonal(txfd_NumeroDePersonal.getText()),lbl_ErrorNumeroPersonal);
        resultado &= validarAuxiliar(()->profesor.setTipoDeContratacion(txfd_TipoDeContratacion.getText()),lbl_ErrorTipoDeContratacion);
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
    
    
    private boolean validarCamposModificadosProfesorUV(Profesor profesor,ProfesorUV profesorUVViejo,ProfesorUV profesorUVNuevo){
        boolean resultado;
        ProfesorAuxiliar profesorViejoSingleton = ProfesorAuxiliar.getInstancia();
        Profesor profesorViejo = new Profesor();
        profesorViejo.setNombre(profesorViejoSingleton.getNombre());
        profesorViejo.setApellidoMaterno(profesorViejoSingleton.getApellidoMaterno());
        profesorViejo.setApellidoPaterno(profesorViejoSingleton.getApellidoPaterno());
        profesorViejo.setCorreo(profesorViejoSingleton.getCorreo());
        if(profesorViejo.getNombre().equals(profesor.getNombre())&&profesorViejo.getApellidoPaterno().equals(profesor.getApellidoPaterno())&&
                profesorViejo.getApellidoMaterno().equals(profesor.getApellidoMaterno())&&profesorViejo.getCorreo().equals(profesor.getCorreo())
                &&profesorUVViejo.getIdAreaAcademica()==profesorUVNuevo.getIdAreaAcademica()&&profesorUVViejo.getIdRegion()==profesorUVNuevo.getIdRegion()&&
                profesorUVViejo.getCategoriaDeContratacion().equals(profesorUVNuevo.getCategoriaDeContratacion())&&profesorUVViejo.getNumeroDePersonal().equals(profesorUVNuevo.getNumeroDePersonal())&&
                profesorUVViejo.getTipoDeContratacion().equals(profesorUVNuevo.getTipoDeContratacion())){
            resultado = false;
        }else{
            resultado = true;
        } 
        return resultado;
    }
    
    private boolean validarCamposModificadosProfesorExterno(Profesor profesor,ProfesorExterno profesorExternoViejo,ProfesorExterno profesorExternoNuevo){
        boolean resultado;
        ProfesorAuxiliar profesorViejoSingleton = ProfesorAuxiliar.getInstancia();
        Profesor profesorViejo = new Profesor();
        profesorViejo.setNombre(profesorViejoSingleton.getNombre());
        profesorViejo.setApellidoMaterno(profesorViejoSingleton.getApellidoMaterno());
        profesorViejo.setApellidoPaterno(profesorViejoSingleton.getApellidoPaterno());
        profesorViejo.setCorreo(profesorViejoSingleton.getCorreo());
        if(profesorViejo.getNombre().equals(profesor.getNombre())&&profesorViejo.getApellidoPaterno().equals(profesor.getApellidoPaterno())&&
                profesorViejo.getApellidoMaterno().equals(profesor.getApellidoMaterno())&&profesorViejo.getCorreo().equals(profesor.getCorreo())
                &&profesorExternoViejo.getIdRepresentanteInstitucional()==profesorExternoNuevo.getIdRepresentanteInstitucional()){
            resultado = false;
        }else{
            resultado = true;
        } 
        return resultado;
    }
    
    public void validarDuplicidadDeCamposNuevosProfesorUV(ProfesorUV profesorUv){
        ocultarLabelErrores();
        if(validarDatosProfesor()&&validarDatosProfesorUV()){
            Profesor profesorNuevo = obtenerDatosProfesorAModificar();
            ProfesorUV profesorUVNuevo = obtenerDatosProfesorUVAModificar();
            String correoProfesor = ProfesorAuxiliar.getInstancia().getCorreo();
            DAOProfesorImplementacion daoProfesor = new DAOProfesorImplementacion();
            DAOProfesorUVImplementacion daoProfesorUv = new DAOProfesorUVImplementacion();
            int resultadoCoincidenciasCorreo = 0;
            int resultadoCoincidenciasNumeroPersonal = 0;
            if (validarCamposModificadosProfesorUV(profesorNuevo, profesorUv, profesorUVNuevo)) {
                profesorUVNuevo.setIdProfesorUV(profesorUv.getIdProfesorUV());
                String numeroDePersonal = profesorUVNuevo.getNumeroDePersonal();
                if (!numeroDePersonal.equals(profesorUv.getNumeroDePersonal())) {
                    resultadoCoincidenciasNumeroPersonal = daoProfesorUv.validarInexistenciaProfesorUV(numeroDePersonal);
                }
                if (!correoProfesor.equals(profesorNuevo.getCorreo())) {
                    resultadoCoincidenciasCorreo = daoProfesor.validarDuplicidadDeCorreo(profesorNuevo.getCorreo());
                }
                if (resultadoCoincidenciasNumeroPersonal == 0 && resultadoCoincidenciasCorreo == 0) {
                    actualizarPerfilProfesorUv(profesorNuevo, profesorUVNuevo);
                } else if (resultadoCoincidenciasNumeroPersonal >= 1 || resultadoCoincidenciasCorreo >= 1) {
                    Alertas.mostrarMensajeDatosDuplicados();
                } else {
                    Alertas.mostrarMensajeErrorEnLaConexion();
                }
            } else {
                Alertas.mostrarMensajeSinModificarDatos();
            }
        }else{
            Alertas.mostrarMensajeDatosInvalidos();
        }
    }
    
    public void validarDuplicidadDeCamposNuevosProfesorExterno(ProfesorExterno profesorExternoViejo){
        ocultarLabelErrores();
        if(validarDatosProfesor()){
            Profesor profesorNuevo = obtenerDatosProfesorAModificar();
            ProfesorExterno profesorExternoNuevo = obtenerDatosProfesorExternoAModificar();
            String correoProfesor = ProfesorAuxiliar.getInstancia().getCorreo();
            DAOProfesorImplementacion daoProfesor = new DAOProfesorImplementacion();
            DAORepresentanteInstitucionalImplementacion daoProfesorUv = new DAORepresentanteInstitucionalImplementacion();
            int resultadoCoincidenciasCorreo=0;
            if (validarCamposModificadosProfesorExterno(profesorNuevo, profesorExternoViejo, profesorExternoNuevo)) {
                profesorExternoNuevo.setIdProfesorExterno(profesorExternoViejo.getIdProfesorExterno());
                if (!correoProfesor.equals(profesorNuevo.getCorreo())) {
                    resultadoCoincidenciasCorreo = daoProfesor.validarDuplicidadDeCorreo(profesorNuevo.getCorreo());
                }
                if (resultadoCoincidenciasCorreo == 0) {
                    actualizarPerfilProfesorExterno(profesorNuevo, profesorExternoNuevo);
                } else if (resultadoCoincidenciasCorreo >= 1) {
                    Alertas.mostrarMensajeDatosDuplicados();
                } else {
                    Alertas.mostrarMensajeErrorEnLaConexion();
                }
            }else{
                Alertas.mostrarMensajeSinModificarDatos();
            }  
        }else{
            Alertas.mostrarMensajeDatosInvalidos();
        }
    }
    
    
    public void regresarDeVentana(){
        int resultadoValidacion = validarConexionEstable();
        if(resultadoValidacion==1){
            String rutaVentanaFXML="/interfazDeUsuario/Ventana_Profesores.fxml";
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
        }else if(resultadoValidacion==-1){
            Alertas.mostrarMensajeSinConexion();
            salirAlInicioDeSesion();
        }else{
            Alertas.mostrarMensajeUsuarioNoEncontrado();
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
