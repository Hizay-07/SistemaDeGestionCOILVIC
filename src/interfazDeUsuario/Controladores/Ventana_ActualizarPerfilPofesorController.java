package interfazDeUsuario.Controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import interfazDeUsuario.Alertas.Alertas;
import logicaDeNegocio.DAOImplementacion.DAOProfesorImplementacion;

import java.net.URL;
import java.util.ResourceBundle;

public class Ventana_ActualizarPerfilPofesorController implements Initializable {

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
    private Label lbl_Nombre;

    @FXML
    private Label lbl_ApellidoMaterno_;

    @FXML
    private Label lbl_ApellidoMaterno;

    @FXML
    private Label lbl_Correo;

    private String correoProfesor;

    private DAOProfesorImplementacion daoProfesor;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        daoProfesor = new DAOProfesorImplementacion();

        // Acción del botón Confirmar
        btn_Confirmar.setOnAction(event -> {
            actualizarPerfilProfesor();
        });

        // Acción del botón Cancelar
        btn_Cancelar.setOnAction(event -> {
            // Aquí puedes implementar la lógica para cerrar la ventana o limpiar los campos
        });
    }

    public void initData(String correo) {
        correoProfesor = correo;
        cargarDatosProfesor();
    }

    private void cargarDatosProfesor() {
        // Aquí debes cargar los datos del profesor utilizando el correo proporcionado
        // Por ejemplo:
        // Profesor profesor = daoProfesor.obtenerProfesorPorCorreo(correoProfesor);
        // Luego, establecer los valores en los campos de texto
        // txfd_Nombre.setText(profesor.getNombre());
        // txfd_ApellidoPaterno.setText(profesor.getApellidoPaterno());
        // txfd_ApellidoMaterno.setText(profesor.getApellidoMaterno());
        // txfd_Correo.setText(profesor.getCorreo());
    }

    private void actualizarPerfilProfesor() {
        // Obtener los nuevos valores de los campos de texto
        String nombre = txfd_Nombre.getText();
        String apellidoPaterno = txfd_ApellidoPaterno.getText();
        String apellidoMaterno = txfd_ApellidoMaterno.getText();
        String correo = txfd_Correo.getText();

        // Verificar si se ingresaron todos los datos
        if (nombre.isEmpty() || apellidoPaterno.isEmpty() || apellidoMaterno.isEmpty() || correo.isEmpty()) {
            Alertas.mostrarMensajeDatosIncompletos();
            return;
        }

        // Actualizar el perfil del profesor en la base de datos
        int filasAfectadas = daoProfesor.modificarNombreProfesor(nombre, correoProfesor);
        filasAfectadas += daoProfesor.modificarApellidoPaternoProfesor(apellidoPaterno, correoProfesor);
        filasAfectadas += daoProfesor.modificarApellidoMaternoProfesor(apellidoMaterno, correoProfesor);
        filasAfectadas += daoProfesor.modificarCorreoProfesor(correo, correoProfesor);

        // Verificar si se actualizaron los datos correctamente
        if (filasAfectadas > 0) {
            Alertas.mostrarMensajeActualizacionExitoso();
        } else {
            Alertas.mostrarMensajeErrorEnLaConexion();
        }
    }
}
