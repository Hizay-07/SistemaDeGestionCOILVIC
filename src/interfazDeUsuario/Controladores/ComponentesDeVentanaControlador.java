package interfazDeUsuario.Controladores;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;


public final class ComponentesDeVentanaControlador {
    
    public static void limitarTextfield(TextField txtf_CampoDeTexto, int tamanioMaximoCaracteres) {
        TextFormatter<String> textFormatter = new TextFormatter<>(cambio -> {
            boolean dentroDelLimite = cambio.getControlNewText().length() <= tamanioMaximoCaracteres;
            return dentroDelLimite ? cambio : null;
        });
        txtf_CampoDeTexto.setTextFormatter(textFormatter);
    }
    
    public static void limitarTextArea(TextArea txa_CampoDeTexto, int tamanioMaximoCaracteres) {
        TextFormatter<String> textFormatter = new TextFormatter<>(cambio -> {
            boolean dentroDelLimite = cambio.getControlNewText().length() <= tamanioMaximoCaracteres;
            return dentroDelLimite ? cambio : null;
        });
        txa_CampoDeTexto.setTextFormatter(textFormatter);
    }
    
}
