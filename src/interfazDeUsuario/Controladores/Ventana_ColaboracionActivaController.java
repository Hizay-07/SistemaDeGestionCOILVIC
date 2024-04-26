package interfazDeUsuario.Controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger;


public class Ventana_ColaboracionActivaController implements Initializable {
    
    private static final Logger LOG=Logger.getLogger(Ventana_ColaboracionActivaController.class);
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void inicializarVentanaIniciarActividad(ActionEvent event){
        try{
            String rutaVentanaFXML="/interfazDeUsuario/Ventana_IniciarActividad.fxml";
            Parent root=FXMLLoader.load(getClass().getResource(rutaVentanaFXML));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show(); 
        }catch(IOException excepcion){
            LOG.error(excepcion);
        }
    }
}
