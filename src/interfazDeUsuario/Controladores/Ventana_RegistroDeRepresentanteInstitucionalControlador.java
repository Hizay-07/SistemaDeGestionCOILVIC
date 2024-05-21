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
    
    private Stage stage_ventana;    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarComboBoxPais();
        
    }    
    
    public void llenarComboBoxPais(){
        DAOPaisImplementacion daoPais=new DAOPaisImplementacion();
        List<Pais> paises=daoPais.consultarPaises();
        ObservableList<String> paisesComboBox = FXCollections.observableArrayList();
        for(Pais pais : paises){
            paisesComboBox.add(pais.getNombrePais());
        }    
        cmb_Pais.setItems(paisesComboBox);
    }
    
    public void cerrarVentana(){
        stage_ventana=(Stage) anchor_RepresentanteInstitucional.getScene().getWindow();
        stage_ventana.close();
    }
    
    public RepresentanteInstitucional obtenerRepresentanteInstitucional(){        
        Pais pais=new Pais();        
        RepresentanteInstitucional representanteInstitucional=new RepresentanteInstitucional();        
        try{
            representanteInstitucional.setNombreInstitucion(txfd_NombreDeInstitucion.getText());
            representanteInstitucional.setClaveInstitucional(txfd_ClaveInstitucional.getText());
            representanteInstitucional.setContacto(txfd_Contacto.getText()); 
            String nombrePais=(String) cmb_Pais.getSelectionModel().getSelectedItem();
            pais.setNombrePais(nombrePais);
            representanteInstitucional.setPais(pais);
        }catch(IllegalArgumentException excepcion){ 
            Alertas.mostrarMensajeDatosInvalidos();  
            LOG.info(excepcion);
        } 
        return representanteInstitucional;                
    }
    
    public void registrarRepresentanteInstitucional(){
        RepresentanteInstitucional representanteInstitucional=obtenerRepresentanteInstitucional();        
        if(!representanteInstitucional.validarDatos()){
            return;
        }
        DAORepresentanteInstitucionalImplementacion daoRepresentanteInstitucional=new DAORepresentanteInstitucionalImplementacion();
        int resultadoRegistro = daoRepresentanteInstitucional.registrarRepresentanteInstitucional(representanteInstitucional);
        if(resultadoRegistro==1){
            Alertas.mostrarRegistroRepresentanteInstitucionalExitoso();
        }else if(resultadoRegistro==-1){
            Alertas.mostrarMensajeErrorEnLaConexion();
            salirAlInicioDeSesion();
        }
        limpiarInformacionRepresentanteInstitucional();
        regresarMenuPrincipal();
    }
    
    public void limpiarInformacionRepresentanteInstitucional(){
        txfd_NombreDeInstitucion.setText("");
        txfd_ClaveInstitucional.setText("");
        txfd_Contacto.setText("");
        cmb_Pais.getSelectionModel().clearSelection();                        
    }
    
    public void regresarMenuPrincipal(){
        String ruta = "/interfazDeUsuario/Ventana_MenuAdministrador.fxml";
        desplegarVentana(ruta);
    }
    
    public void desplegarVentana(String ruta){
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
    
    public boolean validarConexionEstable(){
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
