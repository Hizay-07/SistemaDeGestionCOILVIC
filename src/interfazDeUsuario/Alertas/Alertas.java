package interfazDeUsuario.Alertas;

import java.util.Optional;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
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
            mensaje.showAndWait();
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
    
    public static void mostrarMensajeErrorAlObtenerDatos(){
        Platform.runLater(() -> {
            Alert mensaje = new Alert(AlertType.ERROR);
            mensaje.setTitle("Fallo en la obtención de datos");
            mensaje.setContentText("No se han podido recuperar los datos de manera correcta");
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
    
    public static void mostrarFechasInvalidas(){
        Platform.runLater(() -> {
            Alert mensaje = new Alert(AlertType.INFORMATION);
            mensaje.setTitle("Error en las fechas seleccionadas");
            mensaje.setContentText("Verifique que las fechas a ingresar sean concordantes");
            mensaje.showAndWait();
        });
    }
  
    public static void mostrarMensajeSinCerrarColaboracion(){
        Platform.runLater(() -> {
            Alert mensaje = new Alert(AlertType.INFORMATION);
            mensaje.setTitle("Colaboracion activa");
            mensaje.setContentText("No se puede cerrar la colaboración, aún no es la fecha de cierre");
            mensaje.showAndWait();
        });
    }
    
    public static void mostrarMensajeActividadInactiva(){
         Platform.runLater(() -> {
            Alert mensaje = new Alert(AlertType.INFORMATION);
            mensaje.setTitle("Actividad inactiva");
            mensaje.setContentText("La actividad se encuentra inactiva, no se pueden subir evidencias; solo visualizarlas");
            mensaje.showAndWait();
        });
    }
    
    public static void mostrarMensajeErrorAlAccederAlaCarpeta(){
         Platform.runLater(() -> {
            Alert mensaje = new Alert(AlertType.INFORMATION);
            mensaje.setTitle("Error en el guardador");
            mensaje.setContentText("No se pudo acceder a los elementos deseados");
            mensaje.showAndWait();
        });
    }
    
     public static void mostrarMensajeArchivoSinSeleccionar(){
         Platform.runLater(() -> {
            Alert mensaje = new Alert(AlertType.INFORMATION);
            mensaje.setTitle("Seleccione un archiv");
            mensaje.setContentText("No se ha seleccionado ningun archivo");
            mensaje.showAndWait();
        });
    }
     
    public static void mostrarMensajeSinResultadosEncontrados(String mensajeAlerta){
        Platform.runLater(() -> {
            Alert mensaje = new Alert(AlertType.INFORMATION);
            mensaje.setTitle("No se han encontrado resultados");
            mensaje.setContentText(mensajeAlerta);
            mensaje.showAndWait();
        });
    }
    
    public static void mostrarErrorEnLaCreacionDeInforme(){
        Platform.runLater(() -> {
            Alert mensaje = new Alert(AlertType.ERROR);
            mensaje.setTitle("Error en la creación de informe");
            mensaje.setContentText("No se ha podido generar el informe, intentelo más tarde.");
            mensaje.showAndWait();
        });
    }
    
    public static void mostrarMensajeInformeGuardadoExitosamente(){
        Platform.runLater(() -> {
            Alert mensaje = new Alert(AlertType.CONFIRMATION);
            mensaje.setTitle("Archivo guardado");
            mensaje.setContentText("El informe ha sido guardado exitosamente.");
            mensaje.showAndWait();
        });
    }
    
     public static void mostrarMensajeErrorAlGuardarInforme(){
        Platform.runLater(() -> {
            Alert mensaje = new Alert(AlertType.ERROR);
            mensaje.setTitle("Error en el guadado");
            mensaje.setContentText("No se ha podido guardar el archivo");
            mensaje.showAndWait();
        });
    }
    
    public static boolean mostrarMensajeDescargaDeArchivo() {
        Alert mensaje = new Alert(AlertType.CONFIRMATION);
        mensaje.setTitle("Elaboración de archivo exitoso");
        mensaje.setContentText("El archivo se creó correctamente.\n ¿Desea descargarlo?");
        Optional<ButtonType> resultado = mensaje.showAndWait();

        return resultado.isPresent() && resultado.get() == ButtonType.OK;
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