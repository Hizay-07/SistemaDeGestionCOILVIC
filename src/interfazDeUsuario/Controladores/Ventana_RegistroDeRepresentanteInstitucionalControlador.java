package interfazDeUsuario.Controladores;

import interfazDeUsuario.Alertas.Alertas;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Supplier;
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
import logicaDeNegocio.DAOImplementacion.DAOPaisImplementacion;
import logicaDeNegocio.DAOImplementacion.DAORepresentanteInstitucionalImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOUsuarioImplementacion;
import logicaDeNegocio.clases.Pais;
import logicaDeNegocio.clases.RepresentanteInstitucional;
import logicaDeNegocio.clases.UsuarioSingleton;
import org.apache.log4j.Logger;

public class Ventana_RegistroDeRepresentanteInstitucionalControlador implements Initializable {
    private static final Logger LOG=Logger.getLogger(Ventana_RegistroDeRepresentanteInstitucionalControlador.class);    
    @FXML
    private TextField txfd_NombreDeInstitucion;
    @FXML
    private TextField txfd_ClaveInstitucional;
    @FXML
    private TextField txfd_Contacto;
    @FXML
    private ComboBox cmb_Pais;   
    @FXML
    private AnchorPane anchor_RepresentanteInstitucional;
    @FXML
    private Label lbl_ErrorNombreInstitucion;
    @FXML
    private Label lbl_ErrorClaveInstitucional;
    @FXML
    private Label lbl_ErrorContacto;
    @FXML
    private Label lbl_ErrorPais;
    
    private Stage stage_ventana;    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarComboBoxPais();
        ocultarLabelErrores();
    }    
    
    private void ocultarLabelErrores(){
        lbl_ErrorNombreInstitucion.setVisible(false);
        lbl_ErrorClaveInstitucional.setVisible(false);
        lbl_ErrorContacto.setVisible(false);
        lbl_ErrorPais.setVisible(false);
    }
    
    private void llenarComboBoxPais(){
        DAOPaisImplementacion daoPais=new DAOPaisImplementacion();
        List<Pais> paises=daoPais.consultarPaises();
        if(!paises.isEmpty()){
            ObservableList<String> paisesComboBox = FXCollections.observableArrayList();
            for(Pais pais : paises){
                paisesComboBox.add(pais.getNombrePais());
            }    
            cmb_Pais.setItems(paisesComboBox);            
        }else{
            Alertas.mostrarMensajeErrorEnLaConexion();
            regresarMenuPrincipal();
        }
        
    }
    
    private void cerrarVentana(){
        stage_ventana=(Stage) anchor_RepresentanteInstitucional.getScene().getWindow();
        stage_ventana.close();
    }
    
    public boolean validarDatosRepresentanteInstitucional(){
        boolean resultado = true;
        RepresentanteInstitucional representante = new RepresentanteInstitucional();
        resultado &= validarAuxiliar(()->representante.setNombreInstitucion(txfd_NombreDeInstitucion.getText()),lbl_ErrorNombreInstitucion);
        resultado &= validarAuxiliar(()->representante.setClaveInstitucional(txfd_ClaveInstitucional.getText().toUpperCase()),lbl_ErrorClaveInstitucional);
        resultado &= validarAuxiliar(()->representante.setContacto(txfd_Contacto.getText()),lbl_ErrorContacto);
        resultado &= validarSeleccion(()->(String) cmb_Pais.getSelectionModel().getSelectedItem(),lbl_ErrorPais);
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
    
    private RepresentanteInstitucional obtenerRepresentanteInstitucional(){        
        Pais pais=new Pais();        
        RepresentanteInstitucional representanteInstitucional=new RepresentanteInstitucional();        
        representanteInstitucional.setNombreInstitucion(txfd_NombreDeInstitucion.getText());
        representanteInstitucional.setClaveInstitucional(txfd_ClaveInstitucional.getText().toUpperCase());
        representanteInstitucional.setContacto(txfd_Contacto.getText());
        String nombrePais = (String) cmb_Pais.getSelectionModel().getSelectedItem();
        pais.setNombrePais(nombrePais);
        representanteInstitucional.setPais(pais);
        return representanteInstitucional;                
    }
    
    public void registrarRepresentanteInstitucional(){
        ocultarLabelErrores();
        if(validarDatosRepresentanteInstitucional()){
            RepresentanteInstitucional representanteInstitucional=obtenerRepresentanteInstitucional();
            String nombreInstitucion = representanteInstitucional.getNombreInstitucion().toLowerCase().trim().replaceAll("\\s+", "");
                if(nombreInstitucion.equals("uv")||nombreInstitucion.equals("universidadveracruzana")){
                    Alertas.mostrarMensajeUniversidadVeracruzana();
                }else{
                    DAORepresentanteInstitucionalImplementacion daoRepresentanteInstitucional=new DAORepresentanteInstitucionalImplementacion();
                    int resultadoRegistro = daoRepresentanteInstitucional.registrarRepresentanteInstitucional(representanteInstitucional);
                    switch (resultadoRegistro) {
                        case 1:
                            Alertas.mostrarRegistroRepresentanteInstitucionalExitoso();
                            regresarMenuPrincipal();
                            break;
                        case 0:
                            Alertas.mostrarMensajeDatosDuplicados();
                            regresarMenuPrincipal();
                            break;
                        case -1:
                            Alertas.mostrarMensajeErrorEnLaConexion();              
                            salirAlInicioDeSesion();
                            break;
                        default:
                            Alertas.mostrarMensajeErrorEnLaConexion();
                            salirAlInicioDeSesion();
                            break;
                    }
                }
        }else{
            Alertas.mostrarMensajeDatosInvalidos();
        }
    }
    
    public void regresarMenuPrincipal(){
        String ruta = "/interfazDeUsuario/Ventana_MenuAdministrador.fxml";
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
            }catch(IOException excepcion){
                LOG.error(excepcion);
            }
            cerrarVentana();
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
