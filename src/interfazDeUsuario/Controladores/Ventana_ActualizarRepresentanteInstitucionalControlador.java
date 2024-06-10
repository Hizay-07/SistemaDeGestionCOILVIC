package interfazDeUsuario.Controladores;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logicaDeNegocio.ClasesAuxiliares.RepresentanteAuxiliar;
import logicaDeNegocio.DAOImplementacion.DAOPaisImplementacion;
import logicaDeNegocio.DAOImplementacion.DAORepresentanteInstitucionalImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOUsuarioImplementacion;
import logicaDeNegocio.clases.Pais;
import logicaDeNegocio.clases.RepresentanteInstitucional;
import logicaDeNegocio.clases.UsuarioSingleton;
import org.apache.log4j.Logger;

public class Ventana_ActualizarRepresentanteInstitucionalControlador implements Initializable {

    private static final Logger LOG=Logger.getLogger(Ventana_ActualizarRepresentanteInstitucionalControlador.class);
    private Stage escenario;
    @FXML
    private AnchorPane anchor_Ventana;
    @FXML
    private TextField txfd_NombreInstitucion;
    @FXML
    private TextField txfd_ClaveInstitucional;
    @FXML
    private TextField txfd_Contacto;
    @FXML
    private ComboBox<String> cmb_Pais;
    @FXML
    private Label lbl_ErrorNombreInstitucion;
    @FXML
    private Label lbl_ErrorClaveInstitucional;
    @FXML
    private Label lbl_ErrorContacto;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarComboBoxPais();
        cargarDatosRepresentanteInstitucional();
        ocultarLabelErrores();
    }    
    
    private void ocultarLabelErrores(){
        lbl_ErrorNombreInstitucion.setVisible(false);
        lbl_ErrorClaveInstitucional.setVisible(false);
        lbl_ErrorContacto.setVisible(false);
    }
    
    private void cargarDatosRepresentanteInstitucional(){
        RepresentanteAuxiliar representante = RepresentanteAuxiliar.getInstancia();
        txfd_NombreInstitucion.setText(representante.getNombreInstitucion());
        txfd_ClaveInstitucional.setText(representante.getClaveInstitucional());
        txfd_Contacto.setText(representante.getContacto());
        String paisRepresentante = representante.getPais().getNombrePais();
        cmb_Pais.getSelectionModel().select(paisRepresentante);
    }
    
    private void llenarComboBoxPais(){
        DAOPaisImplementacion daoPais=new DAOPaisImplementacion();
        List<Pais> paises=daoPais.consultarPaises();
        ObservableList<String> paisesComboBox = FXCollections.observableArrayList();
        for(Pais pais : paises){
            paisesComboBox.add(pais.getNombrePais());
        }    
        cmb_Pais.setItems(paisesComboBox);
    }
    
    public boolean validarDatosRepresentanteInstitucional(){
        boolean resultado = true;
        RepresentanteInstitucional representante = new RepresentanteInstitucional();
        resultado &= validarAuxiliar(()->representante.setNombreInstitucion(txfd_NombreInstitucion.getText()),lbl_ErrorNombreInstitucion);
        resultado &= validarAuxiliar(()->representante.setClaveInstitucional(txfd_ClaveInstitucional.getText().toUpperCase()),lbl_ErrorClaveInstitucional);
        resultado &= validarAuxiliar(()->representante.setContacto(txfd_Contacto.getText().toLowerCase()),lbl_ErrorContacto);
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
    
    private RepresentanteInstitucional obtenerDatosRepresentanteInstitucional(){
        RepresentanteAuxiliar representante = RepresentanteAuxiliar.getInstancia();
        RepresentanteInstitucional representanteAModificar = new RepresentanteInstitucional();
        Pais paisSeleccionado = new Pais();
        representanteAModificar.setIdRepresentanteInstitucional(representante.getIdRepresentanteInstitucional());
        representanteAModificar.setClaveInstitucional(txfd_ClaveInstitucional.getText());
        representanteAModificar.setNombreInstitucion(txfd_NombreInstitucion.getText());
        representanteAModificar.setContacto(txfd_Contacto.getText().toLowerCase());
        paisSeleccionado.setNombrePais((String) cmb_Pais.getSelectionModel().getSelectedItem());
        representanteAModificar.setPais(paisSeleccionado);
        return representanteAModificar;
    }
    
    private boolean validarDatosSimilaresRepresentante(RepresentanteInstitucional representanteActualizado){
        RepresentanteAuxiliar representante = RepresentanteAuxiliar.getInstancia();
        RepresentanteInstitucional representanteDesactualizado = new RepresentanteInstitucional();
        representanteDesactualizado.setIdRepresentanteInstitucional(representante.getIdRepresentanteInstitucional());
        representanteDesactualizado.setClaveInstitucional(representante.getClaveInstitucional());
        representanteDesactualizado.setNombreInstitucion(representante.getNombreInstitucion());
        representanteDesactualizado.setContacto(representante.getContacto());
        representanteDesactualizado.setPais(representante.getPais());
        boolean resultado = true;
        if(representanteActualizado.equals(representanteDesactualizado)){
            resultado = true;
        }else{
            resultado = false;
        }
        return resultado;
    }
    
    private int realizarModificacionNombreInstitucionRepresentanteInstitucional(RepresentanteInstitucional representanteActualizado){
        int resultadoModificacion = 0;
        DAORepresentanteInstitucionalImplementacion daoRepresentante = new DAORepresentanteInstitucionalImplementacion();
        int resultadoVerificacion = daoRepresentante.verificarExistenciaNombreInstitucionRepresentanteInstitucional(representanteActualizado);
        if(resultadoVerificacion==0){
            resultadoModificacion = daoRepresentante.modificarNombreRepresentanteInstitucional(representanteActualizado.getNombreInstitucion(), representanteActualizado);
        }else if(resultadoVerificacion==-1){
            resultadoModificacion = -1;
        }
        return resultadoModificacion;
    }
    
    private int realizarModificacionContactoRepresentanteInstitucional(RepresentanteInstitucional representanteActualizado){
        int resultadoModificacion = 0;
        DAORepresentanteInstitucionalImplementacion daoRepresentante = new DAORepresentanteInstitucionalImplementacion();
        int resultadoVerificacion = daoRepresentante.verificarExistenciaContactoInstitucionRepresentanteInstitucional(representanteActualizado);
        if(resultadoVerificacion==0){
            resultadoModificacion = daoRepresentante.modificarContactoRepresentanteInstitucional(representanteActualizado.getContacto(), representanteActualizado);
        }else if(resultadoVerificacion==-1){
            resultadoModificacion = -1;
        }
        return resultadoModificacion;
    }
    
    private int realizarModificacionClaveInstitucionalRepresentanteInstitucional(RepresentanteInstitucional representanteActualizado){
        int resultadoModificacion = 0;
        DAORepresentanteInstitucionalImplementacion daoRepresentante = new DAORepresentanteInstitucionalImplementacion();
        int resultadoVerificacion = daoRepresentante.verificarExistenciaClaveInstitucionalRepresentanteInstitucional(representanteActualizado);
        if(resultadoVerificacion==0){
            resultadoModificacion = daoRepresentante.modificarClaveRepresentanteInstitucional(representanteActualizado.getClaveInstitucional(), representanteActualizado);
        }else if(resultadoVerificacion==-1){
            resultadoModificacion = -1;
        }
        return resultadoModificacion;
    }
    
     private int realizarModificacionPaisRepresentanteInstitucional(RepresentanteInstitucional representanteActualizado){
        RepresentanteAuxiliar representante = RepresentanteAuxiliar.getInstancia();
        int resultadoModificacion = 0;
        if(!representanteActualizado.getPais().equals(representante.getPais())){
            DAORepresentanteInstitucionalImplementacion daoRepresentante = new DAORepresentanteInstitucionalImplementacion();
            resultadoModificacion = daoRepresentante.modificarPaisRepresentanteInstitucional(representanteActualizado);
        }
        return resultadoModificacion;
    }
    
    public void modificarDatosRepresentanteInstituciona(){
        ocultarLabelErrores();
        if(validarDatosRepresentanteInstitucional()){
            RepresentanteInstitucional representante =  obtenerDatosRepresentanteInstitucional();
            String nombreRepresentante = representante.getNombreInstitucion().trim().replaceAll("\\s+", "").toLowerCase();
            if(nombreRepresentante.equals("uv")||nombreRepresentante.equals("universidadveracruzana")){
                Alertas.mostrarMensajeUniversidadVeracruzana();
            }else{
                if(!validarDatosSimilaresRepresentante(representante)){
                    int resultadoAtributosModificados = realizarModificacionPaisRepresentanteInstitucional(representante);
                    resultadoAtributosModificados =  realizarModificacionClaveInstitucionalRepresentanteInstitucional(representante);
                    resultadoAtributosModificados += realizarModificacionContactoRepresentanteInstitucional(representante);
                    resultadoAtributosModificados += realizarModificacionNombreInstitucionRepresentanteInstitucional(representante);
                    if(resultadoAtributosModificados>=1&&resultadoAtributosModificados<=4){
                        Alertas.mostrarMensajeDatosModificados();
                        regresarRepresentantesInstitucionales();
                    }else if(resultadoAtributosModificados==0){
                        Alertas.mostrarMensajeDatosDuplicados();
                    }else{
                        Alertas.mostrarMensajeErrorEnLaConexion();
                        salirAlInicioDeSesion();
                    }
                }else{
                    Alertas.mostrarMensajeSinModificarDatos();
                }
            }
        }else{
            Alertas.mostrarMensajeDatosInvalidos();
        }
    }
    
     
    private void cerrarVentana(){
        escenario=(Stage) anchor_Ventana.getScene().getWindow();
        escenario.close();
    }
    
    public void regresarRepresentantesInstitucionales(){
       String ruta = "/interfazDeUsuario/Ventana_RepresentantesInstitucionales.fxml";
       desplegarVentana(ruta);
    }
    
    private void desplegarVentana(String ruta){
        if(validarConexionEstable()){
            String rutaVentanaFXML = ruta;
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
            cerrarVentana();
        } catch (IOException excepcion) {
            Alertas.mostrarMensajeErrorAlDesplegarVentana();
            LOG.error(excepcion.getCause());
        }
    }
    
}
