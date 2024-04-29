package logicaDeNegocio.interfaces;

import java.util.List;
import logicaDeNegocio.clases.TipoColaboracion;

public interface TipoColaboracionInterface {
    public int registrarTipoColaboracion(TipoColaboracion tipoColaboracion);
            
    public List<TipoColaboracion> consultarTiposDeColaboracion();
    
    public String consultarTipoColaboracionPorId(int idTipoColaboracion);
    
}
