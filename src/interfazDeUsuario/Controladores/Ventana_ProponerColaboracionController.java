package interfazDeUsuario.Controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import interfazDeUsuario.Alertas.Alertas;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logicaDeNegocio.clases.PropuestaColaboracion;
import logicaDeNegocio.DAOImplementacion.DAOPropuestaColaboracionImplementacion;

public class Ventana_ProponerColaboracionController implements Initializable {

    @FXML
    private TextField txfd_FechaInicio;
    @FXML
    private TextField txfd_FechaDeInicio;
    @FXML
    private TextField txfd_ExperienciaEducativa;
    @FXML
    private TextField txfd_ProgramaEducativo;
    @FXML
    private TextField txfd_ObjetivoGeneral;
    @FXML
    private ComboBox<String> cmb_TipoColaboracion;
    @FXML
    private Button btn_Aceptar;
    @FXML
    private Button btn_Cancelar;
    @FXML
    private AnchorPane anchor_ventana;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarTiposColaboracion();
        
        btn_Aceptar.setOnAction(event -> {
            registrarPropuestaColaboracion();
        });

        btn_Cancelar.setOnAction(event -> {
            Stage escenario = (Stage) anchor_ventana.getScene().getWindow();
            escenario.close();
        });
    }

    private void cargarTiposColaboracion() {
        cmb_TipoColaboracion.getItems().addAll("Aceptado", "Rechazado", "En Colaboración", "En Curso");
    }

    private void registrarPropuestaColaboracion() {
        String fechaInicio = txfd_FechaInicio.getText();
        String fechaDeInicio = txfd_FechaDeInicio.getText();
        String experienciaEducativa = txfd_ExperienciaEducativa.getText();
        String programaEducativo = txfd_ProgramaEducativo.getText();
        String objetivoGeneral = txfd_ObjetivoGeneral.getText();
        String tipoColaboracion = cmb_TipoColaboracion.getValue();

        if (fechaInicio.isEmpty() || fechaDeInicio.isEmpty() || experienciaEducativa.isEmpty() ||
                programaEducativo.isEmpty() || objetivoGeneral.isEmpty() || tipoColaboracion == null) {
            Alertas.mostrarMensajeDatosIncompletos();
            return;
        }

        PropuestaColaboracion propuestaColaboracion = new PropuestaColaboracion();
        propuestaColaboracion.setFechaInicio(fechaInicio);
        propuestaColaboracion.setFechaCierre(fechaDeInicio);
        propuestaColaboracion.setExperienciaEducativa(experienciaEducativa);
        propuestaColaboracion.setProgramaEducativoEstudiantil(programaEducativo);
        propuestaColaboracion.setObjetivo(objetivoGeneral);
        //obtener el id del tipo de colaboración seleccionado y establecerlo en propuestaColaboracion

        DAOPropuestaColaboracionImplementacion daoPropuestaColaboracion = new DAOPropuestaColaboracionImplementacion();
        int filasAfectadas = daoPropuestaColaboracion.registrarPropuestaColaboracion(propuestaColaboracion);

        if (filasAfectadas > 0) {
            Alertas.mostrarMensajeDatosIngresados();
        } else {
            Alertas.mostrarMensajeRegistroExitoso();
        }
    }
}
