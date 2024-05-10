package logicaDeNegocio.interfaces;
//
import java.util.List;
import logicaDeNegocio.clases.Colaboracion;

public interface ColaboracionInterface {
    public int registrarColaboracion(Colaboracion colaboracion);
    public List<Colaboracion> consultarColaboraciones();
    public List<Colaboracion> consultarColaboracionesPorEstado(String estado);
    public int registrarRetroalimentacionColaboracionPorId(int idColaboracion,String retroalimentacion);
    public int cambiarEstadoColaboracion(String estado,Colaboracion colaboracion);
    
}
