package logicaDeNegocio.interfaces;
//
import java.util.List;
import logicaDeNegocio.clases.Colaboracion;

public interface ColaboracionInterface {
    public int registrarColaboracion(Colaboracion colaboracion);
    
    public List<Colaboracion> consultarColaboraciones();
    
    public int registrarRetroalimentacionColaboracionPorId(int idColaboracion,String retroalimentacion);    
    
}
