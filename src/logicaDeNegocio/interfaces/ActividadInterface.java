package logicaDeNegocio.interfaces;

import logicaDeNegocio.clases.Actividad;
import java.util.List;

public interface ActividadInterface {  
    
    public int registrarActividad(Actividad actividadNueva);
    
    public int modificarActividad(Actividad actividadActualizada);
    
    public int modificarFechaActividad(Actividad actividadActualizad);
    
    public int obtenerNumeroDeActividad(Actividad actividad);
    
    public boolean validarInexistenciaDeActividad(Actividad actividad);
    
    public int actualizarEstadoActividad(Actividad actividad, String estado);
    
    public List<Actividad> obtenerActividades(int idColaboracion);
    
}
