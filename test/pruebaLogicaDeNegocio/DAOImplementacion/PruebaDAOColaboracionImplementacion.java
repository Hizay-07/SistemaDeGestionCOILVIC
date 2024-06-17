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
import org.junit.BeforeClass;
import org.junit.Test;

public class PruebaDAOColaboracionImplementacion {
    
    @BeforeClass
    public static void inicializar() {
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentapruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        UsuarioSingleton.getInstancia(usuarioPrueba);
        PropuestaColaboracion propuestaDeCOlaboracion = new PropuestaColaboracion();
        propuestaDeCOlaboracion.setIdPropuestaColaboracion(1);
        Colaboracion colaboracion=new Colaboracion();
        colaboracion.setEstadoColaboracion("Activa");
        colaboracion.setPropuestaColaboracion(propuestaDeCOlaboracion);
        colaboracion.setCantidadEstudiantes(50);
        DAOColaboracionImplementacion instancia=new DAOColaboracionImplementacion();        
        instancia.registrarColaboracion(colaboracion);
    }
    
    @Test
    public void pruebaRegistrarColaboracionExitosa(){
        PropuestaColaboracion propuestaDeCOlaboracion = new PropuestaColaboracion();
        propuestaDeCOlaboracion.setIdPropuestaColaboracion(2);
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
        PropuestaColaboracion propuestaDeCOlaboracion = new PropuestaColaboracion();
        propuestaDeCOlaboracion.setIdPropuestaColaboracion(1);
        colaboracion.setEstadoColaboracion("Activa");
        colaboracion.setPropuestaColaboracion(propuestaDeCOlaboracion);
        colaboracion.setCantidadEstudiantes(50);
        int resultadoEsperado=0;
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
    public void pruebaObtenerIdColaboracionPorIdPropuestaExitosa(){
        int idPropuestaColaboracion = 1;
        DAOColaboracionImplementacion instancia = new DAOColaboracionImplementacion();
        int resultadoObtenido = instancia.obtenerIdColaboracionPorIdPropuesta(idPropuestaColaboracion);
        assertEquals(1, resultadoObtenido);
    }
    
    @Test
    public void pruebaObtenerIdColaboracionPorIdPropuestaFallida(){
        int idPropuestaColaboracion = 9999;
        DAOColaboracionImplementacion instancia = new DAOColaboracionImplementacion();
        int resultadoEsperado = 0;
        int resultadoObtenido = instancia.obtenerIdColaboracionPorIdPropuesta(idPropuestaColaboracion);
        assertEquals(resultadoEsperado, resultadoObtenido);    
    }        
    
    @Test
    public void pruebaRealizarRetroalimentacionColaboracionExitosa(){
        DAOColaboracionImplementacion instancia = new DAOColaboracionImplementacion();
        Colaboracion colaboracion = new Colaboracion();
        colaboracion.setIdColaboracion(1);
        colaboracion.setRetroalimentacion("La colaboracion es muy buena, de las mejores que he visto");
        int resultadoObtenido = instancia.realizarRetroalimentacionColaboracion(colaboracion);
        int resultadoEsperado = 1;
        assertEquals(resultadoEsperado,resultadoObtenido);
    }
    
    @Test
    public void pruebaRealizarRetroalimentacionColaboracionFallida(){
        DAOColaboracionImplementacion instancia = new DAOColaboracionImplementacion();
        Colaboracion colaboracion = new Colaboracion();
        colaboracion.setIdColaboracion(0);
        colaboracion.setRetroalimentacion("La colaboración no existe papu");
        int resultadoObtenido = instancia.realizarRetroalimentacionColaboracion(colaboracion);
        int resultadoEsperado = 0;
        assertEquals(resultadoEsperado,resultadoObtenido);
    }
    
    @Test
    public void pruebaSubirSyllabusColaboracionExitosa(){
        DAOColaboracionImplementacion instancia = new DAOColaboracionImplementacion();
        Colaboracion colaboracion = new Colaboracion();
        colaboracion.setIdColaboracion(1);
        colaboracion.setSyllabus("\\Colaboraciones\\Colaboracion1\\Syllabus");
        int resultadoObtenido = instancia.subirSyllabusColaboracion(colaboracion);
        int resultadoEsperado = 1;
        assertEquals(resultadoEsperado,resultadoObtenido);
    }
    
    @Test
    public void pruebaSubirSyllabusColaboracionFallida(){
        DAOColaboracionImplementacion instancia = new DAOColaboracionImplementacion();
        Colaboracion colaboracion = new Colaboracion();
        colaboracion.setIdColaboracion(25);
        colaboracion.setSyllabus("\\Colaboraciones\\Colaboracion1\\Syllabus");
        int resultadoObtenido = instancia.subirSyllabusColaboracion(colaboracion);
        int resultadoEsperado = 0;
        assertEquals(resultadoEsperado,resultadoObtenido);
    }
    
    @Test
    public void pruebaObtenerSyllabusColaboracionExitosa(){
        DAOColaboracionImplementacion instancia = new DAOColaboracionImplementacion();
        Colaboracion colaboracion = new Colaboracion();
        colaboracion.setIdColaboracion(1);
        String rutaObtenida = instancia.obtenerSyllabusColaboracion(colaboracion);
        String rutaEsperada = "\\Colaboraciones\\Colaboracion1\\Syllabus";
        assertEquals(rutaEsperada,rutaObtenida);
    }
    
    @Test
    public void pruebaObtenerSyllabusColaboracionFallida(){
        DAOColaboracionImplementacion instancia = new DAOColaboracionImplementacion();
        Colaboracion colaboracion = new Colaboracion();
        colaboracion.setIdColaboracion(2);
        String rutaObtenida = instancia.obtenerSyllabusColaboracion(colaboracion);
        String rutaEsperada = "\\Colaboraciones\\Colaboracion1\\Syllabus";
        assertNotEquals(rutaEsperada,rutaObtenida);
    }
}
