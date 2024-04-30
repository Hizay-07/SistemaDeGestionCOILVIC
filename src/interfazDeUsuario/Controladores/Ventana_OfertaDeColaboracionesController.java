package interfazDeUsuario.Controladores;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import logicaDeNegocio.DAOImplementacion.DAOEmisionPropuestaImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOProfesorImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOPropuestaColaboracionImplementacion;
import logicaDeNegocio.clases.Profesor;
import logicaDeNegocio.clases.PropuestaColaboracion;

public class Ventana_OfertaDeColaboracionesController implements Initializable {

    @FXML
    private TableColumn<PropuestaColaboracion,String> column_ExperienciaEducativa;

    @FXML
    private TableColumn<PropuestaColaboracion,String> column_Idioma;

    @FXML
    private TableColumn<PropuestaColaboracion,String> column_Institucion;

    @FXML
    private TableColumn<PropuestaColaboracion,String> column_ObjetivoGeneral;

    @FXML
    private TableColumn column_Profesor;

    @FXML
    private TableColumn<PropuestaColaboracion,String> column_ProgramaEducativo;

    @FXML
    private TableColumn column_TipoDeColaboracion;

    @FXML
    private TableColumn<PropuestaColaboracion,String> column_FechaCierre;

    @FXML
    private TableColumn<PropuestaColaboracion,String> column_FechaInicio;

    @FXML
    private TableView tableView_OfertaDeColaboracion;  
    
    @FXML
    private TableColumn column_Opcion;            
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        column_Idioma.setCellValueFactory(new PropertyValueFactory<>("idioma"));
        column_FechaInicio.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
        column_FechaCierre.setCellValueFactory(new PropertyValueFactory<>("fechaCierre"));
        column_ExperienciaEducativa.setCellValueFactory(new PropertyValueFactory<>("experienciaEducativa"));
        column_ProgramaEducativo.setCellValueFactory(new PropertyValueFactory<>("programaEducativoEstudiantil"));
        column_ObjetivoGeneral.setCellValueFactory(new PropertyValueFactory<>("objetivo"));
        column_TipoDeColaboracion.setCellValueFactory(new PropertyValueFactory<>("tipoColaboracion"));        
        column_Profesor.setCellValueFactory(new PropertyValueFactory<>("profesor"));        
        List<PropuestaColaboracion> propuestas=obtenerPropuestasDeColaboracion();
        tableView_OfertaDeColaboracion.getItems().addAll(propuestas);
        agregarBoton();
    }                  
       
    public List<PropuestaColaboracion> obtenerPropuestasDeColaboracion(){
        List<PropuestaColaboracion> propuestas=new ArrayList<>(); 
        DAOPropuestaColaboracionImplementacion daoPropuestas=new DAOPropuestaColaboracionImplementacion();
        propuestas=daoPropuestas.consultarPropuestasDeColaboracionAprobadas();
        List<PropuestaColaboracion> propuestasFinales=new ArrayList<>();        
        DAOEmisionPropuestaImplementacion daoEmisionPropuesta=new DAOEmisionPropuestaImplementacion();
        DAOProfesorImplementacion daoProfesor=new DAOProfesorImplementacion();
        for(PropuestaColaboracion propuesta : propuestas){                                    
            int idProfesor=daoEmisionPropuesta.consultarIdProfesorPorIdPropuestaColaboracion(propuesta.getIdPropuestaColaboracion());
            Profesor profesor=daoProfesor.consultarProfesorPorId(idProfesor);            
            propuesta.setProfesor(profesor);
            propuestasFinales.add(propuesta);
        }
        return propuestasFinales;                
    }     
    
    private void agregarBoton() {        
        Callback<TableColumn<PropuestaColaboracion, Void>, TableCell<PropuestaColaboracion, Void>> cellFactory = (final TableColumn<PropuestaColaboracion, Void> param) -> {
            final TableCell<PropuestaColaboracion, Void> cell = new TableCell<PropuestaColaboracion, Void>() {                
                private final Button btn_EnviarPeticion = new Button();                                
                {
                    btn_EnviarPeticion.setText("Enviar peticion de colaboracion");
                    btn_EnviarPeticion.setOnAction((ActionEvent event) -> {
                        PropuestaColaboracion propuestaColaboracion = getTableView().getItems().get(getIndex());
                        System.out.println("selectedData: " + propuestaColaboracion);
                    });
                }                
                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(btn_EnviarPeticion);
                    }
                }
            };
            return cell;
        };
        column_Opcion.setCellFactory(cellFactory);       
    }                
}