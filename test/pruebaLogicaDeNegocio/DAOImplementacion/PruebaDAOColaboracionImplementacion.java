package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOColaboracionImplementacion;
import logicaDeNegocio.clases.Colaboracion;
import logicaDeNegocio.clases.PropuestaColaboracion;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

public class PruebaDAOColaboracionImplementacion {
    
    @Before
    public void setUp() {
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentapruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        UsuarioSingleton.getInstancia(usuarioPrueba);
    }
    
    @Test
    public void pruebaRegistrarColaboracionExitosa(){
        PropuestaColaboracion propuestaDeCOlaboracion = new PropuestaColaboracion();
        propuestaDeCOlaboracion.setIdPropuestaColaboracion(3);
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
    public void pruebaConsultarColaboracionesExitosa() {
        DAOColaboracionImplementacion dao = new DAOColaboracionImplementacion();
        List<Colaboracion> colaboraciones = dao.consultarColaboraciones();
        assertNotNull(colaboraciones);
    }
    
    @Test
    public void pruebaConsultarColaboracionesFallida() {
        DAOColaboracionImplementacion dao = new DAOColaboracionImplementacion();
        List<Colaboracion> colaboraciones = dao.consultarColaboraciones();
        assertEquals(0, colaboraciones.size());
    }
    
    @Test
    public void pruebaConsultarColaboracionesPorEstadoExitosa() {
        String estado = "Pendiente";
        DAOColaboracionImplementacion dao = new DAOColaboracionImplementacion();
        List<Colaboracion> colaboraciones = dao.consultarColaboracionesPorEstado(estado);
        assertNotNull(colaboraciones);
    }

    @Test
    public void pruebaConsultarColaboracionesPorEstadoFallida() {
        String estado = "Pendiente";
        DAOColaboracionImplementacion dao = new DAOColaboracionImplementacion();
        List<Colaboracion> colaboraciones = dao.consultarColaboracionesPorEstado(estado);
        assertEquals(0, colaboraciones.size());
    }
    
    @Test
    public void pruebaRegistrarRetroalimentacionColaboracionPorIdExitosa(){
        DAOColaboracionImplementacion instancia=new DAOColaboracionImplementacion();
        String retroalimentacion="La colaboracion obtuvo los resultados esperados";
        int resultadoEsperado=1;
        int resultadoObtenido=instancia.registrarRetroalimentacionColaboracionPorId(2, retroalimentacion);
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
        String estadoPrueba = "Activa";
        int resultadoEsperado = 1;
        int resultadoObtenido = instancia.cambiarEstadoColaboracion(estadoPrueba, colaboracionPrueba);
        assertEquals(resultadoEsperado,resultadoObtenido);
    }
    
    @Test
    public void pruebaFlujoAlternoModificarEstadoColaboracionExitoso(){
        DAOColaboracionImplementacion instancia=new DAOColaboracionImplementacion();
        Colaboracion colaboracionPrueba = new Colaboracion();
        colaboracionPrueba.setIdColaboracion(2);
        String estadoPrueba = "Cerrada";
        int resultadoEsperado = 1;
        int resultadoObtenido = instancia.cambiarEstadoColaboracion(estadoPrueba, colaboracionPrueba);
        assertNotEquals(resultadoEsperado,resultadoObtenido);
    }
}
