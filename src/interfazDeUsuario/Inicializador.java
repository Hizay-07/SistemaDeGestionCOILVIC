package interfazDeUsuario;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger;


public class Inicializador extends Application {
    private static final Logger LOG = Logger.getLogger(Inicializador.class);
    @Override
    public void start(Stage stage) throws IOException {
        Parent root=FXMLLoader.load(getClass().getResource("/interfazDeUsuario/Ventan                                                                           a_InicioDeSesion.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);  
        stage.show();                          
    }

    public static void main(String[] args) {
        launch();
    }
    
}