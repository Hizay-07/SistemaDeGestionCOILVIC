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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logicaDeNegocio.ClasesAuxiliares.RepresentanteAuxiliar;
import logicaDeNegocio.DAOImplementacion.DAOPaisImplementacion;
import logicaDeNegocio.DAOImplementacion.DAORepresentanteInstitucionalImplementacion;
import logicaDeNegocio.clases.Pais;
import logicaDeNegocio.clases.RepresentanteInstitucional;
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarComboBoxPais();
        cargarDatosRepresentanteInstitucional();
    }    
    
    public void cargarDatosRepresentanteInstitucional(){
        RepresentanteAuxiliar representante = RepresentanteAuxiliar.getInstancia();
        txfd_NombreInstitucion.setText(representante.getNombreInstitucion());
        txfd_ClaveInstitucional.setText(representante.getClaveInstitucional());
        txfd_Contacto.setText(representante.getContacto());
        String paisRepresentante = representante.getPais().getNombrePais();
        cmb_Pais.getSelectionModel().select(paisRepresentante);
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
    
    public void modificarRepresentanteInstitucional(RepresentanteInstitucional representante){
        DAORepresentanteInstitucionalImplementacion daoRepresentante = new DAORepresentanteInstitucionalImplementacion();
        String nombrePais = (String) cmb_Pais.getSelectionModel().getSelectedItem();
        Pais pais = new Pais();
        pais.setNombrePais(nombrePais);
        representante.setPais(pais);
        int numeroFilasAfectadas = daoRepresentante.modificarClaveRepresentanteInstitucional(representante.getClaveInstitucional(), representante);
        numeroFilasAfectadas += daoRepresentante.modificarContactoRepresentanteInstitucional(representante.getContacto(), representante);
        numeroFilasAfectadas += daoRepresentante.modificarNombreRepresentanteInstitucional(representante.getNombreInstitucion(), representante);
        numeroFilasAfectadas += daoRepresentante.modificarPaisRepresentanteInstitucional(representante);
        if(numeroFilasAfectadas==4){
            Alertas.mostrarMensajeDatosModificados();
        }else{
            Alertas.mostrarMensajeErrorEnLaConexion();
        }
    }
    
    public void validarDatosRepresentanteInstitucional(){
        String nombreInstitucionNuevo = txfd_NombreInstitucion.getText();
        String claveInstitucionalNuevo = txfd_ClaveInstitucional.getText();
        String contactoNuevo = txfd_Contacto.getText();
        RepresentanteAuxiliar representante = RepresentanteAuxiliar.getInstancia();
        DAORepresentanteInstitucionalImplementacion daoRepresentante = new DAORepresentanteInstitucionalImplementacion();
        try{
            if(!claveInstitucionalNuevo.equals(representante.getClaveInstitucional())){
                RepresentanteInstitucional representanteNuevo = new RepresentanteInstitucional();
                representanteNuevo.setIdRepresentanteInstitucional(representante.getIdRepresentanteInstitucional());
                representanteNuevo.setNombreInstitucion(nombreInstitucionNuevo);
                representanteNuevo.setClaveInstitucional(claveInstitucionalNuevo);
                representanteNuevo.setContacto(contactoNuevo);
                int resultadoVerificacion = daoRepresentante.verificarExistenciaRepresentanteInstitucional(representanteNuevo);
                if(resultadoVerificacion>=1){
                    Alertas.mostrarMensajeDatosDuplicados();
                }else if(resultadoVerificacion==0){
                    modificarRepresentanteInstitucional(representanteNuevo);
                }else{
                    Alertas.mostrarMensajeErrorEnLaConexion();
                }
            }else{
                RepresentanteInstitucional representanteNuevo = new RepresentanteInstitucional();
                representanteNuevo.setIdRepresentanteInstitucional(representante.getIdRepresentanteInstitucional());
                representanteNuevo.setNombreInstitucion(nombreInstitucionNuevo);
                representanteNuevo.setClaveInstitucional(claveInstitucionalNuevo);
                representanteNuevo.setContacto(contactoNuevo);
                modificarRepresentanteInstitucional(representanteNuevo);
            }
        }catch(IllegalArgumentException excepcion){
            LOG.info(excepcion.getCause());
            Alertas.mostrarMensajeDatosInvalidos();
        }
    }
    
     
    public void cerrarVentana(){
        escenario=(Stage) anchor_Ventana.getScene().getWindow();
        escenario.close();
    }
    
    public void regresarRepresentantesInstitucionales(){
       String ruta = "/interfazDeUsuario/Ventana_RepresentantesInstitucionales.fxml";
       desplegarVentana(ruta);
    }
    
    public void desplegarVentana(String ruta){
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
    }
    
}
