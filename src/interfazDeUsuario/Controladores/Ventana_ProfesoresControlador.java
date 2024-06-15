package interfazDeUsuario.Controladores;

import envioDeCorreos.EnvioDeCorreo;
import interfazDeUsuario.Alertas.Alertas;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import logicaDeNegocio.ClasesAuxiliares.GeneradorDeContrasenias;
import logicaDeNegocio.ClasesAuxiliares.ProfesorAuxiliar;
import logicaDeNegocio.DAOImplementacion.DAOProfesorExternoImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOProfesorUVImplementacion;
import logicaDeNegocio.DAOImplementacion.DAORepresentanteInstitucionalImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOUsuarioImplementacion;
import logicaDeNegocio.clases.Profesor;
import logicaDeNegocio.clases.ProfesorExterno;
import logicaDeNegocio.clases.ProfesorUV;
import logicaDeNegocio.clases.UsuarioSingleton;
import logicaDeNegocio.enums.EnumTipoDeProfesor;
import org.apache.log4j.Logger;


public class Ventana_ProfesoresControlador implements Initializable {
    
    private static final Logger LOG=Logger.getLogger(Ventana_ActividadesColaboracionActivaControlador.class);
    private Stage escenario;
    @FXML
    private AnchorPane anchor_Ventana;
    @FXML
    private TableView<Profesor> tableView_Profesores;
    @FXML
    private TableColumn<Profesor, String> column_Nombre;
    @FXML
    private TableColumn<Profesor, String> column_ApellidoPaterno;
    @FXML
    private TableColumn<Profesor, String> column_ApellidoMaterno;
    @FXML
    private TableColumn<Profesor, String> column_Universidad;
    @FXML
    private TableColumn<Profesor, Void> column_Modificar;
    @FXML
    private TableColumn<Profesor, String> column_Correo;
    @FXML
    private TableColumn<Profesor, String> column_EstadoProfesor;
    @FXML
    private TableColumn<Profesor, Void> column_Credenciales;
    @FXML
    private ComboBox cmb_TipoDeProfesor;
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        mostrarProfesoresUV();
        asignarBotonesDeModificarPerfil();
        llenarComboBoxTipoDeProfesor();
        asignarBotonDeReenvioDeCredenciales();
    }    
    
    private void llenarComboBoxTipoDeProfesor(){
        ObservableList<String> tiposDeProfesor = FXCollections.observableArrayList();
        for(EnumTipoDeProfesor tipoDeProfesor : EnumTipoDeProfesor.values()){
            tiposDeProfesor.add(tipoDeProfesor.toString());
        }
        cmb_TipoDeProfesor.setItems(tiposDeProfesor);
    }
    
    public void mostrarConsultaSeleccionada(){
        String seleccion = (String) cmb_TipoDeProfesor.getSelectionModel().getSelectedItem();
        switch(seleccion){
            case "Uv":
                mostrarProfesoresUV();
                break;
            case "Externo":
                mostrarProfesoresExternos();
                break;
            default:
                Alertas.mostrarMensajeDatosInvalidos();
                break;
        }
    }
    
    private void mostrarProfesoresUV(){
        tableView_Profesores.getItems().clear();
        try{
            List<ProfesorUV> profesoresUV = obtenerProfesoresUV();
            if(profesoresUV.isEmpty()){
                Alertas.mostrarMensajeSinResultadosEncontrados("No se han encontrado profesores UV registrados");
            }else if(profesoresUV.get(0).getTipoDeContratacion().equals("Excepcion")){
                Alertas.mostrarMensajeErrorEnLaConexion();
                salirAlInicioDeSesion();
            }else{
                tableView_Profesores.getItems().addAll(profesoresUV);
                column_Nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
                column_ApellidoPaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoPaterno"));
                column_ApellidoMaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoMaterno"));
                column_Correo.setCellValueFactory(new PropertyValueFactory<>("correo"));
                column_EstadoProfesor.setCellValueFactory(new PropertyValueFactory<>("estado"));
                column_Universidad.setCellValueFactory(cellData -> {
                    Profesor profesor = cellData.getValue();
                    String valorInstitucion = obtenerValorInstitucion(profesor);
                    return new SimpleStringProperty(valorInstitucion);
                });
            }
        }catch(IllegalArgumentException excepcion){
            LOG.error(excepcion.getCause());
            Alertas.mostrarMensajeErrorAlObtenerDatos();
        }
    }
    
    private void mostrarProfesoresExternos(){
        tableView_Profesores.getItems().clear();
        try{
            List<ProfesorExterno> profesoresExternos = obtenerProfesoresExternos();
            if(profesoresExternos.isEmpty()){
                Alertas.mostrarMensajeSinResultadosEncontrados("No se han encontrado profesores UV registrados");
            }else if(profesoresExternos.get(0).getNombre().equals("Excepcion")){
                Alertas.mostrarMensajeErrorEnLaConexion();
                salirAlInicioDeSesion();
            }else{
                tableView_Profesores.getItems().addAll(profesoresExternos);
                column_Nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
                column_ApellidoPaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoPaterno"));
                column_ApellidoMaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoMaterno"));
                column_Correo.setCellValueFactory(new PropertyValueFactory<>("correo"));
                column_EstadoProfesor.setCellValueFactory(new PropertyValueFactory<>("estado"));
                column_Universidad.setCellValueFactory(cellData -> {
                    Profesor profesor = cellData.getValue();
                    String valorInstitucion = obtenerValorInstitucion(profesor);
                    return new SimpleStringProperty(valorInstitucion);
                });
            }
        }catch(IllegalArgumentException excepcion){
            LOG.error(excepcion.getCause());
            Alertas.mostrarMensajeErrorAlObtenerDatos();
        }
    }
    
    private String obtenerValorInstitucion(Profesor profesor){
        DAOProfesorExternoImplementacion daoProfesorExterno=new DAOProfesorExternoImplementacion();
        int idRepresentanteInstitucional=daoProfesorExterno.consultarIdRepresentanteInstitucionalPorIdProfesor(profesor.getIdProfesor());                
        if(idRepresentanteInstitucional==0){
            return "UV";
        }else{
            DAORepresentanteInstitucionalImplementacion daoRepresentanteInstitucional=new DAORepresentanteInstitucionalImplementacion();
            String valorInstitucion=daoRepresentanteInstitucional.consultarNombreInstitucionPorIdRepresentanteInstitucional(idRepresentanteInstitucional);            
            return valorInstitucion;
        }                        
    } 
    
    private List<ProfesorExterno> obtenerProfesoresExternos(){
        List<ProfesorExterno> profesoresExternosObtenidos = new ArrayList();
        DAOProfesorExternoImplementacion daoProfesores = new DAOProfesorExternoImplementacion();
        profesoresExternosObtenidos = daoProfesores.consultarProfesoresExternos();
        return profesoresExternosObtenidos;
    }
    
    private List<ProfesorUV> obtenerProfesoresUV(){
        List<ProfesorUV> profesoresUVObtenidos = new ArrayList();
        DAOProfesorUVImplementacion daoProfesor = new DAOProfesorUVImplementacion();
        profesoresUVObtenidos = daoProfesor.consultarProfesoresUV();
        return profesoresUVObtenidos;
    }
    
    private void asignarBotonesDeModificarPerfil(){
        Callback<TableColumn<Profesor, Void>, TableCell<Profesor, Void>> frabricaDeCelda = (final TableColumn<Profesor, Void> param) -> {
                final TableCell<Profesor, Void> celda = new TableCell<Profesor, Void>() {                
                    private final Button btn_Modificar = new Button();{
                        btn_Modificar.setText("Actualizar");
                        btn_Modificar.setOnAction((ActionEvent event) -> {
                            Profesor profesorSeleccionado = getTableView().getItems().get(getIndex());
                            ProfesorAuxiliar.setInstancia(profesorSeleccionado);
                            String ruta = "/interfazDeUsuario/Ventana_ActualizarPerfilProfesor.fxml";
                            desplegarVentanaCorrespondiente(ruta);
                        });
                    }                
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        }else{ 
                            setGraphic(btn_Modificar);
                        }
                    }
                };
            return celda;
            };
        column_Modificar.setCellFactory(frabricaDeCelda);
    }
    
    private void asignarBotonDeReenvioDeCredenciales(){
        Callback<TableColumn<Profesor, Void>, TableCell<Profesor, Void>> frabricaDeCelda = (final TableColumn<Profesor, Void> param) -> {
                final TableCell<Profesor, Void> celda = new TableCell<Profesor, Void>() {                
                    private final Button btn_Credenciales = new Button();{
                        btn_Credenciales.setText("Reenviar");
                        btn_Credenciales.setOnAction((ActionEvent event) -> {
                            if(obtenerResultadoValidacionConexion()){
                                Profesor profesorSeleccionado = getTableView().getItems().get(getIndex());
                                reenviarCredencialesProfesor(profesorSeleccionado);
                            }
                        });
                    }                
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        }else{ 
                            setGraphic(btn_Credenciales);
                        }
                    }
                };
            return celda;
            };
        column_Credenciales.setCellFactory(frabricaDeCelda);
    }

    private void reenviarCredencialesProfesor(Profesor profesorSeleccionado){
        if(Alertas.confirmarActualizarCredenciales()){
            DAOUsuarioImplementacion daoUsuario = new DAOUsuarioImplementacion();
            String contrasenia = GeneradorDeContrasenias.generarContraseña();
            int resultadoModificacion = daoUsuario.actualizarUsuarioPorIdUsuario(profesorSeleccionado, contrasenia);
            if(resultadoModificacion==1){
                int resultadoCorreo = mandarCorreo(profesorSeleccionado.getCorreo(),contrasenia);
                if(resultadoCorreo==1){
                    Alertas.mostrarMensajeDatosIngresados();
                }else{
                    Alertas.mostrarSinConexionAInternet("No hay conexión a internet, inténtelo de nuevo");
                }
            }else{
                Alertas.mostrarMensajeErrorEnLaConexion();
            }
        }  
    }
    
    private int mandarCorreo(String usuario, String contrasenia) {
        int resultadoEnvioDeCorreo;
        EnvioDeCorreo mandarCorreoCreacionDeUsuario = new EnvioDeCorreo();
        String asuntoCorreo = "Clave de acceso sistema coil vic actualizada";
        String cuerpoCorreo = "Se le han actualizado sus credenciales como profesor dentro del sistema COIL-VIC. \n\n"
                + "A continuación se le presentan sus claves de acceso para acceder al sistema: \n\n"
                + "Usuario: " + usuario + "\n\nContraseña: " + contrasenia + "\nBuen día\nAtte: Sistema de gestión COIL-VIC";
        String destinatario = usuario;
        mandarCorreoCreacionDeUsuario.setAsunto(asuntoCorreo);
        mandarCorreoCreacionDeUsuario.setContenido(cuerpoCorreo);
        mandarCorreoCreacionDeUsuario.setDestinatario(destinatario);
        resultadoEnvioDeCorreo = mandarCorreoCreacionDeUsuario.enviarCorreo();
        return resultadoEnvioDeCorreo;
    }
    
    public void regresarMenuPrincipal(){
        String rutaVentanaFXML="/interfazDeUsuario/Ventana_MenuAdministrador.fxml";
        desplegarVentanaCorrespondiente(rutaVentanaFXML);  
    }
    
    private void cerrarVentana(){
        escenario = (Stage)anchor_Ventana.getScene().getWindow();
        escenario.close();
    }
    
    public int validarConexionEstable(){
        int resultado;
        DAOUsuarioImplementacion daoUsuario = new DAOUsuarioImplementacion();
        resultado = daoUsuario.confirmarConexionDeUsuario();
        return resultado;
    }
    
    public boolean obtenerResultadoValidacionConexion(){
        boolean resultadoValidacion = true;
        int resultado = validarConexionEstable();
        switch (resultado) {
            case 1:
                resultadoValidacion = true;
                break;
            case 0:
                Alertas.mostrarMensajeUsuarioNoEncontrado();
                resultadoValidacion = false;
                break;
            case -1:
                Alertas.mostrarMensajeErrorEnLaConexion();
                resultadoValidacion = false;
                break;
            default:
                break;
        }
        return resultadoValidacion;
    }
    
    public void desplegarVentanaCorrespondiente(String rutaFxml){
        int resultadoValidacionConexion = validarConexionEstable();
        if(obtenerResultadoValidacionConexion()){
            try{
            Parent root=FXMLLoader.load(getClass().getResource(rutaFxml));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            cerrarVentana();
            }catch(IOException excepcion){
                Alertas.mostrarMensajeErrorAlDesplegarVentana();
                LOG.error(excepcion.getCause());            
            }
        }else{
            salirAlInicioDeSesion();
        }
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
