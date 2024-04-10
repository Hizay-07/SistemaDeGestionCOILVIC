package logicaDeNegocio.interfaces;

import java.util.List;
import logicaDeNegocio.clases.PeticionColaboracion;

public interface PeticionColaboracionInterface {
    int registrarPeticionColaboracion(PeticionColaboracion peticion);
    List<PeticionColaboracion> consultarPeticiones();
    int aceptarColaboracion(int idColaboracion, String nuevoEstado);
    int rechazarColaboracion(int idColaboracion, String nuevoEstado);
}
