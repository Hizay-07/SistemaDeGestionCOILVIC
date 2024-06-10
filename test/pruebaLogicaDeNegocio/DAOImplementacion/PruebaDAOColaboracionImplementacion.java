package pruebaLogicaDeNegocio.DAOImplementacion;

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
        propuestaDeCOlaboracion.setIdPropuestaColaboracion(1);
        Colaboracion colaboracion=new Colaboracion();
        colaboracion.setEstadoColaboracion("Activa");
        colaboracion.setPropuestaColaboracion(propuestaDeCOlaboracion);
        colaboracion.setCantidadEstudiantes(50);
        DAOColaboracionImplementacion instancia=new DAOColaboracionImplementacion();
        int resultadoEsperado=1;
        int resultadoObtenido=instancia.registrarColaboracion(colaboracion);
        assertEquals(resultadoEsperado,resultadoObtenido);        
    }
    
    @Test
    public void pruebaRegistrarColaboracionFallida(){
        Colaboracion colaboracion=new Colaboracion();        
        DAOColaboracionImplementacion instancia=new DAOColaboracionImplementacion();
        int resultadoEsperado=-1;
        int resultadoObtenido=instancia.registrarColaboracion(colaboracion);
        assertEquals(resultadoEsperado,resultadoObtenido);        
    }
    
    @Test 
    public void pruebaRegistrarColaboracionSinConexionExitosa(){
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
    public void pruebaConsultarColaboracionesSinConexionExitosa(){
        DAOColaboracionImplementacion dao = new DAOColaboracionImplementacion();
        List<Colaboracion> colaboraciones = dao.consultarColaboraciones();
        assertEquals(0, colaboraciones.size());
    }
    
    @Test
    public void pruebaConsultarColaboracionesPorEstadoExitosa() {
        String estado = "Activa";
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
    public void pruebaConsultarColaboracionesPorEstadoSinConexionExitosa(){
        String estado = "Activa";
        DAOColaboracionImplementacion dao = new DAOColaboracionImplementacion();
        List<Colaboracion> colaboraciones = dao.consultarColaboracionesPorEstado(estado);
        assertEquals(0, colaboraciones.size());
    }
    
    @Test
    public void pruebaModificarEstadoColaboracionExitosa(){
        DAOColaboracionImplementacion instancia=new DAOColaboracionImplementacion();
        Colaboracion colaboracionPrueba = new Colaboracion();
        colaboracionPrueba.setIdColaboracion(1);
        String estadoPrueba = "Activa";
        int resultadoEsperado = 1;
        int resultadoObtenido = instancia.cambiarEstadoColaboracion(estadoPrueba, colaboracionPrueba);
        assertEquals(resultadoEsperado,resultadoObtenido);
    }
    
    @Test
    public void pruebaModificarEstadoColaboracionFallida(){
        Colaboracion colaboracion = new Colaboracion();
        DAOColaboracionImplementacion instancia = new DAOColaboracionImplementacion();
        int resultadoEsperado = 0;
        int resultadoObtenido = instancia.cambiarEstadoColaboracion("EstadoFallido", colaboracion);
        assertEquals(resultadoEsperado, resultadoObtenido);
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
    public void obtenerIdColaboracionPorIdPropuestaExitosa(){
        int idPropuestaColaboracion = 1;
        DAOColaboracionImplementacion instancia = new DAOColaboracionImplementacion();
        int resultadoObtenido = instancia.obtenerIdColaboracionPorIdPropuesta(idPropuestaColaboracion);
        assertNotEquals(0, resultadoObtenido);
    }
    
    @Test
    public void obtenerIdColaboracionPorIdPropuestaFallida(){
        int idPropuestaColaboracion = 9999;
        DAOColaboracionImplementacion instancia = new DAOColaboracionImplementacion();
        int resultadoEsperado = 0;
        int resultadoObtenido = instancia.obtenerIdColaboracionPorIdPropuesta(idPropuestaColaboracion);
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
