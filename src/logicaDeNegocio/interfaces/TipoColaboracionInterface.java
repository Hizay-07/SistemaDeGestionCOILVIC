package logicaDeNegocio.interfaces;

import java.util.List;
import logicaDeNegocio.clases.TipoColaboracion;

public interface TipoColaboracionInterface {                
    public List<TipoColaboracion> consultarTiposDeColaboracion();
    
    public String consultarTipoColaboracionPorId(int idTipoColaboracion);
    
    public int consultarIdTipoColaboracionPorTipo(String tipo);
    
    public int verificarTipoColaboracion();    
}
