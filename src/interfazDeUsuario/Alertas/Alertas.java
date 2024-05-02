package interfazDeUsuario.Alertas;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Alertas extends Application {
    
    public static void mostrarMensajeUsuarioNoEncontrado(){
        Platform.runLater(() -> {
            Alert mensaje = new Alert(AlertType.ERROR);
            mensaje.setTitle("Usuario no encontrado");
            mensaje.setContentText("El usuario que ha ingresado no se encuentra registrado");
            mensaje.showAndWait();
        });
    }
    
    public static void mostrarMensajeDatosInvalidos(){
        Platform.runLater(() ->{
            Alert mensaje = new Alert(AlertType.WARNING);
            mensaje.setTitle("Datos inválidos");
            mensaje.setContentText("Verifique que los datos ingresados sean los correctos");
            mensaje.showAndWait();
        
        });
    }
    
    public static void mostrarMensajeDatosDuplicados(){
        Platform.runLater(() ->{
            Alert mensaje = new Alert(AlertType.WARNING);
            mensaje.setTitle("Duplicado de datos");
            mensaje.setContentText("Los datos que desea ingresar ya han sido previamente insertados");
            mensaje.showAndWait();
        
        });
    }
    
    public static void mostrarMensajeProfesorConUsuario(){
        Platform.runLater(() ->{
            Alert mensaje = new Alert(AlertType.ERROR);
            mensaje.setTitle("Profesor con usuario");
            mensaje.setContentText("El profesor al que desea asignar un usuario, ya cuenta con una cuenta");
            mensaje.showAndWait();
        
        });
    }
    
    public static void mostrarMensajeDatosIngresados(){
         Platform.runLater(() ->{
            Alert mensaje = new Alert(AlertType.CONFIRMATION);
            mensaje.setTitle("Datos correctos");
            mensaje.setContentText("Los datos han sido insertados correctamente");
            mensaje.showAndWait();
        
        });
    }
    public static void mostrarMensajeDatosIncompletos(){
        Platform.runLater(() ->{
        Alert mensaje = new Alert (AlertType.ERROR);
        mensaje.setTitle("Información invalida");
        mensaje.setContentText("Asegúrese de llenar correctamente todos los campos");
        mensaje.showAndWait();
        });
    }
    
    public static void mostrarMensajeDatosModificados(){
        Platform.runLater(() ->{
            Alert mensaje = new Alert(AlertType.CONFIRMATION);
            mensaje.setTitle("Datos modificados");
            mensaje.setContentText("Los datos han sido modificados correctamente");
            mensaje.showAndWait();
        
        });
    }
    
    public static void mostrarMensajeFechaInvalida(){
        Platform.runLater(() ->{
            Alert mensaje = new Alert(AlertType.WARNING);
            mensaje.setTitle("Fecha inválida");
            mensaje.setContentText("Verifique que la fecha ingresada sea correcta");
            mensaje.showAndWait();
        
        });
    }
    
    public static void mostrarMensajeErrorEnLaConexion(){
        Platform.runLater(() ->{
            Alert mensaje = new Alert(AlertType.ERROR);
            mensaje.setTitle("Error en la conexion");
            mensaje.setContentText("Se ha perdido la conexión en la base de datos");
            mensaje.showAndWait();
        });
    }
    
    public static void mostrarCorreoDeProfesorInexistente(){
        Platform.runLater(() ->{
            Alert mensaje = new Alert(AlertType.ERROR);
            mensaje.setTitle("Correo inválido");
            mensaje.setContentText("El correo asociado no pertenece a ningún profesor registrado");
        });
    }
    
    public static void mostrarRegistroProfesorExitoso(){
        Platform.runLater(() -> {
            Alert mensaje = new Alert(AlertType.INFORMATION);
            mensaje.setTitle("Registro de profesor correcto");
            mensaje.setContentText("El profesor fue registrado correctamente");
            mensaje.showAndWait();
        });
    }
    
    public static void mostrarRegistroRepresentanteInstitucionalExitoso(){
        Platform.runLater(() -> {
            Alert mensaje = new Alert(AlertType.INFORMATION);
            mensaje.setTitle("Representante institucional registrado");
            mensaje.setContentText("Representante institucional registrado correctamente");
            mensaje.showAndWait();
        });
    }
    
    public static void mostrarMensajeColaboracionActiva(String mensajeVentana){
        Platform.runLater(() -> {
            Alert mensaje = new Alert(AlertType.WARNING);
            mensaje.setTitle("Colaboracion activa");
            mensaje.setContentText(mensajeVentana);
            mensaje.showAndWait();
        });
    }
    
    public static void mostrarColaboracionInactiva(){
        Platform.runLater(() -> {
            Alert mensaje = new Alert(AlertType.INFORMATION);
            mensaje.setTitle("Sin colaboracion");
            mensaje.setContentText("Por el momento no hay una colaboracion activa la cual se pueda visualizar");
            mensaje.showAndWait();
        });
    }
    
    public static void mostrarPeticionColaboracionRegistrada(){
        Platform.runLater(() -> {
            Alert mensaje = new Alert(AlertType.INFORMATION);
            mensaje.setTitle("Petición colaboración");
            mensaje.setContentText("La petición de colaboración ha sido registrada");
            mensaje.showAndWait();
        });
    }
    
    
    @Override
    public void start(Stage stage){
        try{
            stage.show();
        }catch(Exception excepcion){
            System.out.println("Hola");
        }
     
    }
    
}