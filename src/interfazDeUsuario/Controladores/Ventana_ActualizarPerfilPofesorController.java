package interfazDeUsuario.Controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import interfazDeUsuario.Alertas.Alertas;
import java.io.IOException;
import logicaDeNegocio.DAOImplementacion.DAOProfesorImplementacion;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logicaDeNegocio.ClasesAuxiliares.ProfesorAuxiliar;
import logicaDeNegocio.DAOImplementacion.DAOUsuarioImplementacion;
import logicaDeNegocio.clases.Profesor;
import logicaDeNegocio.clases.UsuarioSingleton;
import logicaDeNegocio.enums.EnumProfesor;
import org.apache.log4j.Logger;

public class Ventana_ActualizarPerfilPofesorController implements Initializable {
    
    private static final Logger LOG=Logger.getLogger(Ventana_ActualizarPerfilPofesorController.class);
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
    
    private Stage escenario;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn_Confirmar.setOnAction(event -> {
            actualizarPerfilProfesor();
        });
        btn_Cancelar.setOnAction(event -> {
           regresarDeVentana();
        });
        cargarDatosComboBoxEstadoProfesor();
        cargarDatosProfesor();
    }
    
    public void cerrarVentana(){
        escenario = (Stage) anchor_Ventana.getScene().getWindow();
        escenario.close();
    }
    
    private void cargarDatosProfesor() {
        ProfesorAuxiliar profesor = ProfesorAuxiliar.getInstancia();
        txfd_Nombre.setText(profesor.getNombre());
        txfd_ApellidoPaterno.setText(profesor.getApellidoPaterno());
        txfd_ApellidoMaterno.setText(profesor.getApellidoMaterno());
        txfd_Correo.setText(profesor.getCorreo());
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
    }
    
    private void actualizarPerfilProfesor(){
        String estadoProfesor = ProfesorAuxiliar.getInstancia().getEstado();
        if(estadoProfesor.equals(EnumProfesor.Activo.toString())||estadoProfesor.equals(EnumProfesor.Archivado.toString())){
            actualizarPerfilProfesorActivoOArchivado();
        }else{
            actualizarPerfilProfesorEsperandoUOcupado();
        }
    }
    
    private void actualizarPerfilProfesorActivoOArchivado() {
        DAOProfesorImplementacion daoProfesor = new DAOProfesorImplementacion();
        Profesor profesorAActualizar = new Profesor();
        String correoProfesor = ProfesorAuxiliar.getInstancia().getCorreo();
        String correoProfesorNuevo = txfd_Correo.getText();
        int correosEncontradosSimilares = 0;
        if(!correoProfesorNuevo.equals(correoProfesor)){
            correosEncontradosSimilares = daoProfesor.validarDuplicidadDeCorreo(correoProfesorNuevo);
        }
        if(correosEncontradosSimilares==0){
            try {
                profesorAActualizar.setNombre(txfd_Nombre.getText());
                profesorAActualizar.setApellidoMaterno(txfd_ApellidoMaterno.getText());
                profesorAActualizar.setApellidoPaterno(txfd_ApellidoPaterno.getText());
                profesorAActualizar.setCorreo(txfd_Correo.getText());
                profesorAActualizar.setEstado((String) cmb_EstadosProfesor.getSelectionModel().getSelectedItem());
                int filasAfectadas = daoProfesor.modificarNombreProfesor(profesorAActualizar.getNombre(), correoProfesor);
                filasAfectadas += daoProfesor.modificarApellidoPaternoProfesor(profesorAActualizar.getApellidoPaterno(), correoProfesor);
                filasAfectadas += daoProfesor.modificarApellidoMaternoProfesor(profesorAActualizar.getApellidoMaterno(), correoProfesor);
                filasAfectadas += daoProfesor.modificarCorreoProfesor(profesorAActualizar.getCorreo(), correoProfesor);
                filasAfectadas += daoProfesor.cambiarEstadoProfesor(ProfesorAuxiliar.getInstancia().getIdProfesor(),profesorAActualizar.getEstado());
                if(filasAfectadas >= 4){
                    Alertas.mostrarMensajeDatosModificados();
                    regresarDeVentana();
                }else{
                    Alertas.mostrarMensajeErrorEnLaConexion();
                    salirAlInicioDeSesion();
                }
            } catch (IllegalArgumentException | NullPointerException excepcion) {
                LOG.error(excepcion);
                Alertas.mostrarMensajeDatosInvalidos();
            }
        }else{
            Alertas.mostrarMensajeDatosDuplicados();
        }
    }
    
    private void actualizarPerfilProfesorEsperandoUOcupado() {
        DAOProfesorImplementacion daoProfesor = new DAOProfesorImplementacion();
        Profesor profesorAActualizar = new Profesor();
        String correoProfesor = ProfesorAuxiliar.getInstancia().getCorreo();
        String correoProfesorNuevo = txfd_Correo.getText();
        int correosEncontradosSimilares = 0;
        if(!correoProfesorNuevo.equals(correoProfesor)){
            correosEncontradosSimilares = daoProfesor.validarDuplicidadDeCorreo(correoProfesorNuevo);
        }
        if(correosEncontradosSimilares==0){
            try {
                profesorAActualizar.setNombre(txfd_Nombre.getText());
                profesorAActualizar.setApellidoMaterno(txfd_ApellidoMaterno.getText());
                profesorAActualizar.setApellidoPaterno(txfd_ApellidoPaterno.getText());
                profesorAActualizar.setCorreo(txfd_Correo.getText());
                int filasAfectadas = daoProfesor.modificarNombreProfesor(profesorAActualizar.getNombre(), correoProfesor);
                filasAfectadas += daoProfesor.modificarApellidoPaternoProfesor(profesorAActualizar.getApellidoPaterno(), correoProfesor);
                filasAfectadas += daoProfesor.modificarApellidoMaternoProfesor(profesorAActualizar.getApellidoMaterno(), correoProfesor);
                filasAfectadas += daoProfesor.modificarCorreoProfesor(profesorAActualizar.getCorreo(), correoProfesor);
                if(filasAfectadas >= 4){
                    Alertas.mostrarMensajeDatosModificados();
                    regresarDeVentana();
                }else{
                    Alertas.mostrarMensajeErrorEnLaConexion();
                    salirAlInicioDeSesion();
                }
            } catch (IllegalArgumentException excepcion) {
                LOG.error(excepcion);
                Alertas.mostrarMensajeDatosInvalidos();
            }
        }else{
            Alertas.mostrarMensajeDatosDuplicados();
        }
    }
    
    public void regresarDeVentana(){
        if(validarConexionEstable()){
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
