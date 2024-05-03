package logicaDeNegocio.interfaces;

import java.util.List;
import logicaDeNegocio.clases.PropuestaColaboracion;

public interface PropuestaColaboracionInterface {    
    
    public int registrarPropuestaColaboracion(PropuestaColaboracion propuestaColaboracion);
    
    public List<PropuestaColaboracion> consultarPropuestasColaboracion();
    
    public List<PropuestaColaboracion> consultarPropuestasColaboracionPorFechaDeInicio(String fecha);        
    
    public int editarFechaDeInicioDePropuestaColaboracionPorId(String fechaDeInicio,int idPropuestaColaboracion);
    
    public int editarFechaDeCierreDePropuestaColaboracionPorId(String fechaDeCierre,int idPropuestaColaboracion);
                         
    public int aprobarPropuestaColaboracionPorId(int idPropuestaColaboracion);
    
    public int rechazarPropuestaColaboracionPorId(int idPropuestaColaboracion);
    
    public List<PropuestaColaboracion> consultarPropuestasDeColaboracionAprobadas();
    
    public PropuestaColaboracion obtenerPropuestaDeColaboracionPorId(int idPropuestaColaboracion);
}
