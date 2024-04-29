package logicaDeNegocio.interfaces;

import java.util.List;
import logicaDeNegocio.clases.EmisionPropuesta;

public interface EmisionPropuestaInterface {
    public int registrarEmisionPropuesta(EmisionPropuesta emisionPropuesta);
    
    public List<EmisionPropuesta> consultarEmisionesDePropuestas();
    
    public int consultarIdProfesorPorIdPropuestaColaboracion(int idPropuestaColaboracion);
    
    
}
