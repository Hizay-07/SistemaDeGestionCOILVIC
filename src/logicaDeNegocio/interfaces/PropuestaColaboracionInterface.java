package logicaDeNegocio.interfaces;

import java.util.List;
import logicaDeNegocio.clases.PropuestaColaboracion;

public interface PropuestaColaboracionInterface {  
    
    public int registrarPropuestaColaboracion(PropuestaColaboracion propuestaColaboracion);
                                       
    public int aprobarPropuestaColaboracionPorId(int idPropuestaColaboracion);
    
    public int rechazarPropuestaColaboracionPorId(int idPropuestaColaboracion);
    
    public List<PropuestaColaboracion> consultarPropuestasDeColaboracionAprobadas();
    
    public PropuestaColaboracion obtenerPropuestaDeColaboracionPorId(int idPropuestaColaboracion);
    
    public List<PropuestaColaboracion> consultarPropuestasDeColaboracionRegistradas();
    
    public int obtenerIdPropuestaColaboracionPorEstadoPorIdProfesor(int idProfesor,String estado);
    
    public List<PropuestaColaboracion> consultarPropuestasDeColaboracionAprobadasSinPeticiones(int identificadorProfesor);
    
    public int cambiarEstadoIniciadaPropuestaColaboracionPorId(int idPropuestaColaboracion);
    
}
