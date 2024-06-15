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
        List<Colaboracion> colaboraciones = dao.consultarColaboraciones();
        assertEquals(0, colaboraciones.size());
    }
    
    @Test 
    public void pruebaConsultarColaboracionesPorEstadoSinConexionExitosa(){
        String estado = "Activa";
        DAOColaboracionImplementacion dao = new DAOColaboracionImplementacion();
        List<Colaboracion> colaboraciones = dao.consultarColaboracionesPorEstado(estado);
        assertEquals(0, colaboraciones.size());
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
       int resultadoEsperado = 0;
       int resultadoObtenido = instancia.obtenerIdColaboracionPorIdPropuesta(idPropuestaColaboracion);
       assertEquals(resultadoEsperado, resultadoObtenido);
    }
    
}
