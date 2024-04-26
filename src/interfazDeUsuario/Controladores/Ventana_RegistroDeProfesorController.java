package interfazDeUsuario.Controladores;

import interfazDeUsuario.Alertas.Alertas;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logicaDeNegocio.DAOImplementacion.DAOAreaAcademicaImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOProfesorExternoImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOProfesorImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOProfesorUVImplementacion;
import logicaDeNegocio.DAOImplementacion.DAORegionAcademicaImplementacion;
import logicaDeNegocio.DAOImplementacion.DAORepresentanteInstitucionalImplementacion;
import logicaDeNegocio.clases.AreaAcademica;
import logicaDeNegocio.clases.Profesor;
import logicaDeNegocio.clases.ProfesorExterno;
import logicaDeNegocio.clases.ProfesorUV;
import logicaDeNegocio.clases.RegionAcademica;
import logicaDeNegocio.clases.RepresentanteInstitucional;
import org.apache.log4j.Logger;

public class Ventana_RegistroDeProfesorController implements Initializable {
    private static final Logger LOG=Logger.getLogger(Ventana_RegistroDeProfesorController.class);    

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
        // TODO
    }   
    public void mostrarPanelProfesorUV(){
        pane_ProfesorUV.setVisible(true);    
        pane_Profesores.setVisible(false);
        llenarComboBoxAreaAcademica();
        llenarComboBoxRegionAcademica();
        btn_Aceptar.setOnAction(event->registrarProfesorUV());
    }
    
    public void mostrarPanelProfesorExterno(){
        pane_ProfesorExterno.setVisible(true);
        pane_Profesores.setVisible(false);  
        llenarComboBoxUniversidad();
        btn_Aceptar.setOnAction(event->registrarProfesorExterno());
    }
    
    public void llenarComboBoxAreaAcademica(){
        DAOAreaAcademicaImplementacion instancia=new DAOAreaAcademicaImplementacion();
        List<AreaAcademica> areasAcademicas = instancia.consultarAreasAcademicas();
        ObservableList<String> areas = FXCollections.observableArrayList();
        for (AreaAcademica area : areasAcademicas) {
            areas.add(area.getArea());
        }
        cmb_AreaAcademica.setItems(areas);                  
    }
    
    public void llenarComboBoxRegionAcademica(){
        DAORegionAcademicaImplementacion instancia=new DAORegionAcademicaImplementacion();
        List<RegionAcademica> regionesAcademicas=instancia.consultarRegionesAcademicas();
        ObservableList<String> regiones = FXCollections.observableArrayList();
        for(RegionAcademica region : regionesAcademicas){
            regiones.add(region.getRegion());
        }
        cmb_RegionAcademica.setItems(regiones);        
    }
    
    public void llenarComboBoxUniversidad(){
        DAORepresentanteInstitucionalImplementacion instancia=new DAORepresentanteInstitucionalImplementacion();
        List<RepresentanteInstitucional>  representantesInstitucionales=instancia.obtenerRepresentantesInstitucionales();
        ObservableList<String> universidades = FXCollections.observableArrayList();
        for(RepresentanteInstitucional representanteInstitucional : representantesInstitucionales){
            universidades.add(representanteInstitucional.getNombreInstitucion());
        }
        cmb_Universidad.setItems(universidades);        
    }
    
    public void cerrarVentana(){
        escenario=(Stage) anchor_RegistoProfesor.getScene().getWindow(); 
        escenario.close();        
    }
    
    public Profesor obtenerProfesor(){
        Profesor profesor=new Profesor();
        try{
            profesor.setNombre(txfd_Nombre.getText());
            profesor.setApellidoPaterno(txfd_ApellidoPaterno.getText());
            profesor.setApellidoMaterno(txfd_ApellidoMaterno.getText());
            profesor.setCorreo(txfd_Correo.getText());
        }catch(IllegalArgumentException excepcion){ 
            Alertas.mostrarMensajeDatosInvalidos();   
            LOG.info(excepcion);
        }
        return profesor;
    }
    
    public ProfesorUV obtenerProfesorUV(){
        ProfesorUV profesorUV=new ProfesorUV();
        String areaAcademica=(String) cmb_AreaAcademica.getSelectionModel().getSelectedItem();
        DAOAreaAcademicaImplementacion daoAreaAcademica=new DAOAreaAcademicaImplementacion();
        int idAreaAcademica=daoAreaAcademica.consultarIdDeAreaAcademicaPorArea(areaAcademica);
        String regionAcademica=(String) cmb_RegionAcademica.getSelectionModel().getSelectedItem();
        DAORegionAcademicaImplementacion daoRegionAcademica=new DAORegionAcademicaImplementacion();
        int idRegionAcademica=daoRegionAcademica.consultarIdDeRegionPorRegion(regionAcademica);
        try{
            profesorUV.setNumeroDePersonal(txfd_NumeroDePersonal.getText());
            profesorUV.setTipoDeContratacion(txfd_TipoDeContratacion.getText());
            profesorUV.setCategoriaDeContratacion(txfd_CategoriaDeContratacion.getText());
            profesorUV.setIdAreaAcademica(idAreaAcademica);                
            profesorUV.setIdRegion(idRegionAcademica);
        }catch(IllegalArgumentException excepcion){ 
            Alertas.mostrarMensajeDatosInvalidos();  
            LOG.info(excepcion);
        }               
        return profesorUV;                        
    }
    
    public void registrarProfesorUV(){ 
        Profesor profesor=obtenerProfesor();
        ProfesorUV profesorUV=obtenerProfesorUV();
        if(!profesor.validarAtributos()||!profesorUV.validarAtributos()){              
            return;
        }
        DAOProfesorImplementacion daoProfesor=new DAOProfesorImplementacion();               
        daoProfesor.registrarProfesor(profesor);
        int idProfesor=daoProfesor.obtenerIdProfesorPorCorreo(profesor.getCorreo());                
        profesorUV.setIdProfesor(idProfesor);
        DAOProfesorUVImplementacion daoProfesorUV=new DAOProfesorUVImplementacion();
        daoProfesorUV.registrarProfesorUV(profesorUV);  
        limpiarInformacionProfesor();
        limpiarInformacionProfesorUV();
        Alertas.mostrarRegistroProfesorExitoso();
        cerrarVentana();
    }
    
    public ProfesorExterno obtenerProfesorExterno(){
        ProfesorExterno profesorExterno=new ProfesorExterno();
        String universidad=(String) cmb_Universidad.getSelectionModel().getSelectedItem();
        DAORepresentanteInstitucionalImplementacion daoRepresentanteInstitucional=new DAORepresentanteInstitucionalImplementacion();
        int idRepresentanteInstitucional=daoRepresentanteInstitucional.consultarIdRepresentanteInstitucionalPorUniversidad(universidad);
        try{
            profesorExterno.setIdRepresentanteInstitucional(idRepresentanteInstitucional);
        }catch(IllegalArgumentException excepcion){ 
            Alertas.mostrarMensajeDatosInvalidos();  
            LOG.info(excepcion);
        } 
        return profesorExterno;
    }
    
    public void registrarProfesorExterno(){
        Profesor profesor=obtenerProfesor();
        ProfesorExterno profesorExterno=obtenerProfesorExterno();
        if(!profesor.validarAtributos()&&!profesorExterno.validarAtributos()){
            return;
        }
        DAOProfesorImplementacion daoProfesor=new DAOProfesorImplementacion();               
        daoProfesor.registrarProfesor(profesor);
        int idProfesor=daoProfesor.obtenerIdProfesorPorCorreo(profesor.getCorreo());                
        profesorExterno.setIdProfesor(idProfesor);
        DAOProfesorExternoImplementacion daoProfesorExterno=new DAOProfesorExternoImplementacion();
        daoProfesorExterno.registrarProfesorExterno(profesorExterno);     
        limpiarInformacionProfesor();
        limpiarInformacionProfesorExterno();
        Alertas.mostrarRegistroProfesorExitoso();
        cerrarVentana();
    }
    
    public void limpiarInformacionProfesor(){
        txfd_Nombre.setText("");    
        txfd_ApellidoPaterno.setText("");    
        txfd_ApellidoMaterno.setText("");   
        txfd_Correo.setText("");                                
    }
    
    public void limpiarInformacionProfesorUV(){
        txfd_NumeroDePersonal.setText("");
        txfd_TipoDeContratacion.setText("");
        txfd_CategoriaDeContratacion.setText("");
        String prompText_AreaAcademica=cmb_AreaAcademica.getPromptText();
        cmb_AreaAcademica.getSelectionModel().clearSelection();        
        cmb_AreaAcademica.setPromptText(prompText_AreaAcademica);
        String prompText_RegionAcademica=cmb_RegionAcademica.getPromptText();
        cmb_RegionAcademica.getSelectionModel().clearSelection();            
        cmb_RegionAcademica.setPromptText(prompText_RegionAcademica);
    }
    
    public void limpiarInformacionProfesorExterno(){
        String prompText_Universidad=cmb_Universidad.getPromptText();
        cmb_Universidad.getSelectionModel().clearSelection();                
        cmb_Universidad.setPromptText(prompText_Universidad);
    }
                               
}
