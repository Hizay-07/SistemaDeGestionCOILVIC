package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOColaboracionImplementacion;
import logicaDeNegocio.clases.Colaboracion;
import logicaDeNegocio.clases.PropuestaColaboracion;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

public class PruebaDAOColaboracionImplementacionSinConexionExitosa {
    
    @BeforeClass
    public static void inicializar() {
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentaadmin@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        UsuarioSingleton.getInstancia(usuarioPrueba);
    }
    
    @Test 
    public void pruebaRegistrarColaboracionSinConexionExitosa(){
        PropuestaColaboracion propuestaDeCOlaboracion = new PropuestaColaboracion();
        propuestaDeCOlaboracion.setIdPropuestaColaboracion(2);
        Colaboracion colaboracion=new Colaboracion();
        colaboracion.setEstadoColaboracion("Activa");
        colaboracion.setPropuestaColaboracion(propuestaDeCOlaboracion);
        colaboracion.setCantidadEstudiantes(50);
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
    
     @Test
    public void pruebaRealizarRetroalimentacionColaboracionSinConexionExitosa(){
        DAOColaboracionImplementacion instancia = new DAOColaboracionImplementacion();
        Colaboracion colaboracion = new Colaboracion();
        colaboracion.setIdColaboracion(1);
        colaboracion.setRetroalimentacion("La colaboracion es muy buena, de las mejores que he visto");
        int resultadoObtenido = instancia.realizarRetroalimentacionColaboracion(colaboracion);
        int resultadoEsperado = -1;
        assertEquals(resultadoEsperado,resultadoObtenido);
    }
    
    @Test
    public void pruebaSubirSyllabusColaboracionSinConexionExitosa(){
        DAOColaboracionImplementacion instancia = new DAOColaboracionImplementacion();
        Colaboracion colaboracion = new Colaboracion();
        colaboracion.setIdColaboracion(1);
        colaboracion.setSyllabus("\\Colaboraciones\\Colaboracion1\\Syllabus");
        int resultadoObtenido = instancia.subirSyllabusColaboracion(colaboracion);
        int resultadoEsperado = -1;
        assertEquals(resultadoEsperado,resultadoObtenido);
    }
    
    @Test
    public void pruebaObtenerSyllabusColaboracionSinConexionExitosa(){
        DAOColaboracionImplementacion instancia = new DAOColaboracionImplementacion();
        Colaboracion colaboracion = new Colaboracion();
        colaboracion.setIdColaboracion(1);
        String rutaObtenida = instancia.obtenerSyllabusColaboracion(colaboracion);
        String rutaEsperada = "Excepcion";
        assertEquals(rutaEsperada,rutaObtenida);
    }
    
}
