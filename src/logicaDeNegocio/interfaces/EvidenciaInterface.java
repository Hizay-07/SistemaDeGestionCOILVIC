package logicaDeNegocio.interfaces;

import logicaDeNegocio.clases.Evidencia;
import java.util.List;

public interface EvidenciaInterface {    
    public int agregarEvidencia(Evidencia evidencia);

    public int modificarEvidencia(Evidencia evidenciaNueva);

    public List<Evidencia> obtenerEvidenciasDeActividad(int idActividad);

    public int obtenerNumeroDeEvidencia(int idActividad);
}
