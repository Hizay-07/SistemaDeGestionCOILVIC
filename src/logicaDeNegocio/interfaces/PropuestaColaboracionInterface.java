package logicaDeNegocio.interfaces;

import java.util.List;
import logicaDeNegocio.clases.PropuestaColaboracion;

public interface PropuestaColaboracionInterface {        
    public int registrarPropuestaColaboracion(PropuestaColaboracion propuestaColaboracion);
    
    public List<PropuestaColaboracion> consultarPropuestasColaboracion();        
                         
    public int aprobarPropuestaColaboracionPorId(int idPropuestaColaboracion);
    
    public int rechazarPropuestaColaboracionPorId(int idPropuestaColaboracion);
    
    public List<PropuestaColaboracion> consultarPropuestasDeColaboracionAprobadas();
    
    public PropuestaColaboracion obtenerPropuestaDeColaboracionPorId(int idPropuestaColaboracion);
    
    public List<PropuestaColaboracion> consultarPropuestasDeColaboracionRegistradas();
    
    public int obtenerIdPropuestaColaboracionAprobadaPorIdProfesor(int idProfesor);
    
    public List<PropuestaColaboracion> consultarPropuestasDeColaboracionAprobadasSinPeticiones(int identificadorProfesor);
    
    public int cambiarEstadoIniciadaPropuestaColaboracionPorId(int idPropuestaColaboracion);
}
