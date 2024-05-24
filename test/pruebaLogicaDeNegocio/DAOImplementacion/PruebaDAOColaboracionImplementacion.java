package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOColaboracionImplementacion;
import logicaDeNegocio.clases.Colaboracion;
import logicaDeNegocio.clases.PropuestaColaboracion;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;

public class PruebaDAOColaboracionImplementacion {
    
    @Test
    public void pruebaRegistrarColaboracionExitosa(){
        PropuestaColaboracion propuestaDeCOlaboracion = new PropuestaColaboracion();
        propuestaDeCOlaboracion.setIdPropuestaColaboracion(1);
        Colaboracion colaboracion=new Colaboracion();
        colaboracion.setEstadoColaboracion("Activa");
        colaboracion.setPropuestaColaboracion(propuestaDeCOlaboracion);
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
        PropuestaColaboracion propuestaDeColaboracion1 = new PropuestaColaboracion();
        PropuestaColaboracion propuestaDeColaboracion2 = new PropuestaColaboracion();
        propuestaDeColaboracion1.setIdPropuestaColaboracion(1);
        propuestaDeColaboracion2.setIdPropuestaColaboracion(2);
        Colaboracion colaboracion1=new Colaboracion();
        Colaboracion colaboracion2=new Colaboracion();
        colaboracion1.setIdColaboracion(1);
        colaboracion1.setPropuestaColaboracion(propuestaDeColaboracion1);
        colaboracion1.setCantidadEstudiantes(32);
        colaboracion1.setEstadoColaboracion("Activa");
        colaboracion2.setIdColaboracion(2);
        colaboracion2.setPropuestaColaboracion(propuestaDeColaboracion2);
        colaboracion2.setCantidadEstudiantes(32);
        colaboracion2.setEstadoColaboracion("Activa");
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
    
    @Test
    public void pruebaModificarEstadoColaboracionExitosa(){
        DAOColaboracionImplementacion instancia=new DAOColaboracionImplementacion();
        Colaboracion colaboracionPrueba = new Colaboracion();
        colaboracionPrueba.setIdColaboracion(2);
        String estadoPrueba = "Cerrada";
        int resultadoEsperado = 1;
        int resultadoObtenido = instancia.cambiarEstadoColaboracion(estadoPrueba, colaboracionPrueba);
        assertEquals(resultadoEsperado,resultadoObtenido);
    }
    
    @Test
    public void pruebaFlujoAlternoModificarEstadoColaboracionExitoso(){
        DAOColaboracionImplementacion instancia=new DAOColaboracionImplementacion();
        Colaboracion colaboracionPrueba = new Colaboracion();
        colaboracionPrueba.setIdColaboracion(1);
        String estadoPrueba = "Cerrada";
        int resultadoEsperado = 1;
        int resultadoObtenido = instancia.cambiarEstadoColaboracion(estadoPrueba, colaboracionPrueba);
        assertNotEquals(resultadoEsperado,resultadoObtenido);
    }
}
