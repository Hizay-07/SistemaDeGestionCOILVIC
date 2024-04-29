package interfazDeUsuario.Controladores;

import interfazDeUsuario.Alertas.Alertas;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logicaDeNegocio.DAOImplementacion.DAOPaisImplementacion;
import logicaDeNegocio.DAOImplementacion.DAORepresentanteInstitucionalImplementacion;
import logicaDeNegocio.clases.Pais;
import logicaDeNegocio.clases.RepresentanteInstitucional;
import org.apache.log4j.Logger;

public class Ventana_RegistroDeRepresentanteInstitucionalController implements Initializable {
    private static final Logger LOG=Logger.getLogger(Ventana_RegistroDeRepresentanteInstitucionalController.class);    
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
        daoRepresentanteInstitucional.registrarRepresentanteInstitucional(representanteInstitucional);   
        Alertas.mostrarRegistroRepresentanteInstitucionalExitoso();
        limpiarInformacionRepresentanteInstitucional();
        cerrarVentana();
    }
    
    public void limpiarInformacionRepresentanteInstitucional(){
        txfd_NombreDeInstitucion.setText("");
        txfd_ClaveInstitucional.setText("");
        txfd_Contacto.setText("");
        cmb_Pais.getSelectionModel().clearSelection();                        
    }            
}
