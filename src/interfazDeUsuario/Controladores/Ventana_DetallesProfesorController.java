package interfazDeUsuario.Controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import interfazDeUsuario.Alertas.Alertas;
import logicaDeNegocio.DAOImplementacion.DAOProfesorImplementacion;
import logicaDeNegocio.clases.Profesor;

import java.net.URL;
import java.util.ResourceBundle;

public class Ventana_DetallesProfesorController implements Initializable {

    @FXML
    private TextField txfd_Nombre;

    @FXML
    private TextField txfd_ApellidoPaterno;

    @FXML
    private TextField txfd_ApellidoMaterno;

    @FXML
    private TextField txfd_Correo;

    @FXML
    private Button btn_ActualizarPerfil;

    @FXML
    private Button btn_Cancelar;

    @FXML
    private Label lbl_Nombre;

    @FXML
    private Label lbl_ApellidoPaterno;

    @FXML
    private Label lbl_ApellidoMaterno;

    @FXML
    private Label lbl_Correo;

    private DAOProfesorImplementacion daoProfesor;

    private int idProfesor;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        daoProfesor = new DAOProfesorImplementacion();

        // Acción del botón Actualizar Perfil
        btn_ActualizarPerfil.setOnAction(event -> {
            // Aquí puedes implementar la lógica para abrir la ventana de actualizar perfil del profesor
            // Puedes pasar el ID del profesor a la ventana de actualización si es necesario
        });

        // Acción del botón Cancelar
        btn_Cancelar.setOnAction(event -> {
            // Aquí puedes implementar la lógica para cerrar la ventana
        });
    }

    public void initData(int id) {
        idProfesor = id;
        cargarDetallesProfesor();
    }

    private void cargarDetallesProfesor() {
        // Aquí debes cargar los detalles del profesor utilizando el ID proporcionado
        // Por ejemplo:
        // Profesor profesor = daoProfesor.obtenerProfesorPorId(idProfesor);
        // Luego, establecer los valores en los campos de texto
        // txfd_Nombre.setText(profesor.getNombre());
        // txfd_ApellidoPaterno.setText(profesor.getApellidoPaterno());
        // txfd_ApellidoMaterno.setText(profesor.getApellidoMaterno());
        // txfd_Correo.setText(profesor.getCorreo());
    }
}
