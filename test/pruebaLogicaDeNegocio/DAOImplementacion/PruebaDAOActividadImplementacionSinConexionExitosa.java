package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOActividadImplementacion;
import logicaDeNegocio.clases.Actividad;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;
import logicaDeNegocio.enums.EnumActividades;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;

public class PruebaDAOActividadImplementacionSinConexionExitosa {
    
    @BeforeClass
    public static void inicializar() {
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentaadmin@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        UsuarioSingleton.getInstancia(usuarioPrueba);
    }
    
    @Test
    public void pruebaRegistrarActividadSinConexionExitosa() {
        DAOActividadImplementacion implementacion = new DAOActividadImplementacion();
        Actividad actividadPrueba = new Actividad();
        actividadPrueba.setNombre("SO móviles");
        actividadPrueba.setDescripcion("Conocer los distintos SO móviles");
        actividadPrueba.setFechaDeInicio("2024-04-04");
        actividadPrueba.setFechaDeCierre("2024-05-05");
        actividadPrueba.setIdColaboracion(1);
        actividadPrueba.setNumeroActividad(1);
        actividadPrueba.setEstado(EnumActividades.Inactiva.toString());
        int resultadoInsercion = implementacion.registrarActividad(actividadPrueba);
        assertEquals(-1, resultadoInsercion);
    }
    
    @Test
    public void pruebaObtenerActividadesSinConexionExitosa(){
        DAOActividadImplementacion implementacion = new DAOActividadImplementacion();   
        Actividad actividadEsperada = new Actividad();
        actividadEsperada.setNombre("Excepcion");
        List<Actividad> actividadesObtenidas = implementacion.obtenerActividades(1);
        assertEquals(actividadEsperada.getNombre(),actividadesObtenidas.get(0).getNombre());
    }
    
    @Test
    public void pruebaObtenerNumeroDeActividadSinConexionExitosa(){
        DAOActividadImplementacion implementacion = new DAOActividadImplementacion();
        Actividad actividadPrueba = new Actividad();
        actividadPrueba.setNombre("SO móviles");        
        actividadPrueba.setDescripcion("Conocer los distintos SO móviles");
        int resultadoObtenido=implementacion.obtenerNumeroDeActividad(actividadPrueba);                                 
        assertEquals(-1,resultadoObtenido);
    }
    
    @Test
    public void pruebaValidarInexistenciaActividadSinConexionExitosa(){
        DAOActividadImplementacion implementacion = new DAOActividadImplementacion();
        Actividad actividadPrueba = new Actividad();
        actividadPrueba.setNombre("SO móviles");                                
        actividadPrueba.setIdColaboracion(1);
        actividadPrueba.setNumeroActividad(1);         
        boolean resultado = implementacion.validarInexistenciaDeActividad(actividadPrueba);
        assertTrue(resultado);
    }
    
    @Test
    public void pruebaActualizarEstadoActividadSinConexionExitosa(){
        DAOActividadImplementacion implementacion = new DAOActividadImplementacion();
        Actividad actividadPrueba = new Actividad();                                
        actividadPrueba.setNumeroActividad(1);
        actividadPrueba.setIdActividad(1);        
        int resultado = implementacion.actualizarEstadoActividad(actividadPrueba, EnumActividades.Finalizada.toString());
        assertEquals(-1, resultado);
    }
    
    @Test
    public void pruebaModificarActividadSinConexionExitosa(){
        DAOActividadImplementacion implementacion = new DAOActividadImplementacion();
        Actividad actividadPrueba = new Actividad();
        actividadPrueba.setNombre("SO");
        actividadPrueba.setDescripcion("Conocer los distintos SO móviles");                        
        actividadPrueba.setIdActividad(0);
        actividadPrueba.setEstado(EnumActividades.Activa.toString());      
        int resultadoInsercion = implementacion.modificarActividad(actividadPrueba);
        assertEquals(-1,resultadoInsercion);
    }
    
    @Test
    public void pruebaModificarFechaActividadSinConexionExitosa(){
        DAOActividadImplementacion implementacion = new DAOActividadImplementacion();
        Actividad actividadPrueba = new Actividad();                
        actividadPrueba.setFechaDeInicio("2024-06-09");
        actividadPrueba.setFechaDeCierre("2024-06-11");                
        actividadPrueba.setIdActividad(0);        
        int resultadoInsercion = implementacion.modificarFechaActividad(actividadPrueba);
        assertEquals(-1,resultadoInsercion);
    }
}
