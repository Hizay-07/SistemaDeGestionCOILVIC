package logicaDeNegocio.interfaces;

import java.util.List;
import logicaDeNegocio.clases.TipoColaboracion;

public interface TipoColaboracionInterface {
    public int registrarTipoColaboracion(TipoColaboracion tipoColaboracion);
    
    public int editarTipoPorId(int idTipoColaboracion,String tipo);
    
    public List<TipoColaboracion> consultarTiposDeColaboracion();
    
}
