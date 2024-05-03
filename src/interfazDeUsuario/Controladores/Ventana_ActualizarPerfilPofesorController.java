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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logicaDeNegocio.DAOImplementacion.DAOAreaAcademicaImplementacion;
import logicaDeNegocio.clases.Profesor;
import logicaDeNegocio.clases.ProfesorSingleton;
import org.apache.log4j.Logger;

public class Ventana_ActualizarPerfilPofesorController implements Initializable {
    
    private static final Logger LOG=Logger.getLogger(DAOAreaAcademicaImplementacion.class);

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

    private String correoProfesor;
    
    private Stage escenario;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn_Confirmar.setOnAction(event -> {
            actualizarPerfilProfesor();
        });
        btn_Cancelar.setOnAction(event -> {
           regresarDeVentana();
        });
    }

    public void initData(String correo) {
        cargarDatosProfesor();
    }
    
    public void regresarDeVentana(){
        String rutaVentanaFXML="/interfazDeUsuario/Ventana_DetallesProfesor.fxml";
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
    
    public void cerrarVentana(){
        escenario = (Stage) anchor_Ventana.getScene().getWindow();
        escenario.close();
    }
    
    private void cargarDatosProfesor() {
        ProfesorSingleton profesor = ProfesorSingleton.getInstancia();
        txfd_Nombre.setText(profesor.getNombre());
        txfd_ApellidoPaterno.setText(profesor.getApellidoPaterno());
        txfd_ApellidoMaterno.setText(profesor.getApellidoMaterno());
        txfd_Correo.setText(profesor.getCorreo());
    }

    private void actualizarPerfilProfesor() {
        DAOProfesorImplementacion daoProfesor = new DAOProfesorImplementacion();
        Profesor profesorAActualizar = new Profesor();
        correoProfesor = ProfesorSingleton.getInstancia().getCorreo();
        try{
            profesorAActualizar.setNombre(txfd_Nombre.getText());
            profesorAActualizar.setApellidoMaterno(txfd_ApellidoMaterno.getText());
            profesorAActualizar.setApellidoPaterno(txfd_ApellidoPaterno.getText());
            profesorAActualizar.setCorreo(txfd_Correo.getText());
        }catch(IllegalArgumentException excepcion){
            LOG.error(excepcion);
        }

        
        int filasAfectadas = daoProfesor.modificarNombreProfesor(profesorAActualizar.getNombre(), correoProfesor);
        filasAfectadas += daoProfesor.modificarApellidoPaternoProfesor(profesorAActualizar.getApellidoPaterno(), correoProfesor);
        filasAfectadas += daoProfesor.modificarApellidoMaternoProfesor(profesorAActualizar.getApellidoMaterno(), correoProfesor);
        filasAfectadas += daoProfesor.modificarCorreoProfesor(profesorAActualizar.getCorreo(), correoProfesor);

        if (filasAfectadas == 4) {

            Alertas.mostrarMensajeDatosModificados();
        } else {
            Alertas.mostrarMensajeErrorEnLaConexion();
        }
    }
}
