package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOColaboracionImplementacion;
import logicaDeNegocio.clases.Colaboracion;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PruebaDAOColaboracionImplementacionSinConexionExitosa {
    @Test 
    public void pruebaRegistrarColaboracionSinConexionExitosa(){
        Colaboracion colaboracion=new Colaboracion();        
        DAOColaboracionImplementacion instancia=new DAOColaboracionImplementacion();
        int resultadoEsperado=-1;
        int resultadoObtenido=instancia.registrarColaboracion(colaboracion);
        assertEquals(resultadoEsperado,resultadoObtenido);  
    }
    
    @Test 
    public void pruebaConsultarColaboracionesSinConexionExitosa(){
        DAOColaboracionImplementacion dao = new DAOColaboracionImplementacion();
        Colaboracion colaboracionEsperada = new Colaboracion();
        colaboracionEsperada.setEstadoColaboracion("Excepcion");
        List<Colaboracion> colaboraciones = dao.consultarColaboraciones();
        assertEquals(colaboracionEsperada.getEstadoColaboracion(),colaboraciones.get(0).getEstadoColaboracion());
    }
    
    @Test 
    public void pruebaConsultarColaboracionesPorEstadoSinConexionExitosa(){
        String estado = "Activa";
        DAOColaboracionImplementacion dao = new DAOColaboracionImplementacion();
        List<Colaboracion> colaboraciones = dao.consultarColaboracionesPorEstado(estado);
        Colaboracion colaboracionEsperada = new Colaboracion();
        colaboracionEsperada.setEstadoColaboracion("Excepcion");
        assertEquals(colaboracionEsperada.getEstadoColaboracion(),colaboraciones.get(0).getEstadoColaboracion());
    }
    
    @Test 
    public void pruebaModificarEstadoColaboracionSinConexionExitosa(){
        Colaboracion colaboracion = new Colaboracion(); 
        colaboracion.setIdColaboracion(1);
        DAOColaboracionImplementacion instancia = new DAOColaboracionImplementacion();
        int resultadoEsperado = -1;
        int resultadoObtenido = instancia.cambiarEstadoColaboracion("NuevoEstado", colaboracion);
        assertEquals(resultadoEsperado, resultadoObtenido);
    }
    
    @Test
    public void obtenerIdColaboracionPorIdPropuestaSinConexionExitosa(){
       int idPropuestaColaboracion = 1;
       DAOColaboracionImplementacion instancia = new DAOColaboracionImplementacion();
       int resultadoEsperado = -1;
       int resultadoObtenido = instancia.obtenerIdColaboracionPorIdPropuesta(idPropuestaColaboracion);
       assertEquals(resultadoEsperado, resultadoObtenido);
    }
    
}
