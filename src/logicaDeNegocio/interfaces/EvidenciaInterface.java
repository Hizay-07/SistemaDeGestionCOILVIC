package logicaDeNegocio.interfaces;

import logicaDeNegocio.clases.Evidencia;
import logicaDeNegocio.clases.Actividad;
import java.util.List;

public interface EvidenciaInterface {
    
    public int agregarEvidencia(Evidencia evidencia);
    public int modificarEvidencia(Evidencia evidenciaNueva);
    public List<Evidencia> obtenerEvidenciasDeActividad(Actividad actividad);
}
