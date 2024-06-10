package logicaDeNegocio.interfaces;

import java.util.List;
import logicaDeNegocio.clases.EmisionPropuesta;
import logicaDeNegocio.clases.Profesor;

public interface EmisionPropuestaInterface {
    
    public int registrarEmisionPropuesta(EmisionPropuesta emisionPropuesta);
    
    public List<EmisionPropuesta> consultarEmisionesDePropuestas();
    
    public int consultarIdProfesorPorIdPropuestaColaboracion(int idPropuestaColaboracion);
    
    public List<Integer> consultarIdPropuestaDeColaboracionPorIdProfesor(Profesor profesor);
    
}
