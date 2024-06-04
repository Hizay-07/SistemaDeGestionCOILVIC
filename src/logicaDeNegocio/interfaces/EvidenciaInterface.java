package logicaDeNegocio.interfaces;

import java.io.File;
import logicaDeNegocio.clases.Evidencia;
import logicaDeNegocio.clases.Actividad;
import java.util.List;
import logicaDeNegocio.clases.Colaboracion;

public interface EvidenciaInterface {
    
    public int agregarEvidencia(Evidencia evidencia);
    public int modificarEvidencia(Evidencia evidenciaNueva);
    public List<Evidencia> obtenerEvidenciasDeActividad(int  idActividad);
    public int obtenerNumeroDeEvidencia(int idActividad);
}
