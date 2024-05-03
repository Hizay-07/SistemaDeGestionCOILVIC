package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOColaboracionImplementacion;
import logicaDeNegocio.clases.Colaboracion;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;

public class PruebaDAOColaboracionImplementacion {
    
    @Test
    public void pruebaRegistrarColaboracionExitosa(){
        Colaboracion colaboracion=new Colaboracion();
        colaboracion.setEstadoColaboracion("Activa");
        colaboracion.setIdPropuestaColaboracion(3);
        colaboracion.setCantidadEstudiantes(24);
        DAOColaboracionImplementacion instancia=new DAOColaboracionImplementacion();
        int resultadoEsperado=1;
        int resultadoObtenido=instancia.registrarColaboracion(colaboracion);
        assertEquals(resultadoEsperado,resultadoObtenido);        
    }
    
    @Test
    public void pruebaRegistrarColaboracionFracaso(){
        Colaboracion colaboracion=new Colaboracion();        
        DAOColaboracionImplementacion instancia=new DAOColaboracionImplementacion();
        int resultadoEsperado=-1;
        int resultadoObtenido=instancia.registrarColaboracion(colaboracion);
        assertEquals(resultadoEsperado,resultadoObtenido);        
    }
    
    @Test
    public void pruebaConsultarColaboracionesExitosa(){
        Colaboracion colaboracion1=new Colaboracion();
        Colaboracion colaboracion2=new Colaboracion();
        colaboracion1.setIdColaboracion(1);
        colaboracion1.setIdPropuestaColaboracion(1);
        colaboracion1.setCantidadEstudiantes(32);
        colaboracion1.setEstadoColaboracion("Activa");
        colaboracion1.setRetroalimentacion(null);
        colaboracion2.setIdColaboracion(2);
        colaboracion2.setIdPropuestaColaboracion(2);
        colaboracion2.setCantidadEstudiantes(32);
        colaboracion2.setEstadoColaboracion("Activa");
        colaboracion2.setRetroalimentacion(null);
        List<Colaboracion> listaEsperada=new ArrayList<>();
        listaEsperada.add(colaboracion1);
        listaEsperada.add(colaboracion2);
        List<Colaboracion> listaObtenida=new ArrayList<>();
        DAOColaboracionImplementacion instancia=new DAOColaboracionImplementacion();
        listaObtenida=instancia.consultarColaboraciones();
        assertEquals(listaEsperada,listaObtenida);                
    }
    
    @Test
    public void pruebaConsultarColaboracionesFracaso(){
        Colaboracion colaboracion=new Colaboracion();
        List<Colaboracion> listaEsperada=new ArrayList<>();
        listaEsperada.add(colaboracion);
        List<Colaboracion> listaObtenida=new ArrayList<>();
        DAOColaboracionImplementacion instancia=new DAOColaboracionImplementacion();
        listaObtenida=instancia.consultarColaboraciones();
        assertNotEquals(listaEsperada,listaObtenida);        
    }
    
    @Test
    public void pruebaRegistrarRetroalimentacionColaboracionPorIdExitosa(){
        DAOColaboracionImplementacion instancia=new DAOColaboracionImplementacion();
        String retroalimentacion="La colaboracion obtuvo los resultados esperados";
        int resultadoEsperado=1;
        int resultadoObtenido=instancia.registrarRetroalimentacionColaboracionPorId(1, retroalimentacion);
        assertEquals(resultadoEsperado,resultadoObtenido);                 
    }        
    
    @Test
    public void pruebaRegistrarRetroalimentacionColaboracionPorIdFracaso(){
        DAOColaboracionImplementacion instancia=new DAOColaboracionImplementacion();
        String retroalimentacion="La colaboracion obtuvo los resultados esperados";
        int resultadoEsperado=0;
        int resultadoObtenido=instancia.registrarRetroalimentacionColaboracionPorId(0, retroalimentacion);
        assertEquals(resultadoEsperado,resultadoObtenido);        
    }
    
}
