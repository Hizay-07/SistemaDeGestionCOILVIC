package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOActividadImplementacion;
import logicaDeNegocio.clases.Actividad;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;
import logicaDeNegocio.enums.EnumActividades;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class PruebaDAOActividadImplementacion {
    
    @Before
    public void setUp() {
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentapruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        UsuarioSingleton.getInstancia(usuarioPrueba);
    }
    
    @Test
    public void pruebaRegistrarActividadExitosa() {
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
        assertEquals(1, resultadoInsercion);
    }
    
    @Test
    public void pruebaRegistrarActividadFallida(){
        DAOActividadImplementacion implementacion = new DAOActividadImplementacion();
        Actividad actividadPrueba = new Actividad();
        actividadPrueba.setNombre("SO móviles");
        actividadPrueba.setDescripcion("Conocer los distintos SO móviles");
        actividadPrueba.setFechaDeInicio("2024-04-04");
        actividadPrueba.setFechaDeCierre("2024-05-05");
        actividadPrueba.setIdColaboracion(0);
        actividadPrueba.setNumeroActividad(2);
        actividadPrueba.setEstado(EnumActividades.Inactiva.toString());
        int resultadoInsercion = implementacion.registrarActividad(actividadPrueba);
        assertEquals(-1, resultadoInsercion);
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
    public void pruebaObtenerActividadesExitosa(){
        DAOActividadImplementacion implementacion = new DAOActividadImplementacion();         
        List<Actividad> actividadesEsperadas = new ArrayList();
        Actividad actividadPrueba = new Actividad();
        actividadPrueba.setNombre("SO móviles");        
        actividadPrueba.setDescripcion("Conocer los distintos SO móviles");
        actividadPrueba.setFechaDeInicio("2024-04-04");
        actividadPrueba.setFechaDeCierre("2024-05-05");
        actividadPrueba.setIdColaboracion(1);
        actividadPrueba.setNumeroActividad(1);
        actividadPrueba.setIdActividad(1);
        actividadPrueba.setEstado(EnumActividades.Inactiva.toString());
        actividadesEsperadas.add(actividadPrueba);        
        List<Actividad> actividadesObtenidas = implementacion.obtenerActividades(1);
        assertEquals(actividadesEsperadas,actividadesObtenidas);
    }
    
    @Test
    public void pruebaObtenerActividadesFallida(){
        DAOActividadImplementacion implementacion = new DAOActividadImplementacion();         
        List<Actividad> actividadesEsperadas = new ArrayList();
        Actividad actividadPrueba = new Actividad();
        actividadPrueba.setNombre("SO móviles");        
        actividadPrueba.setDescripcion("Conocer los distintos SO móviles");
        actividadPrueba.setFechaDeInicio("2024-04-04");
        actividadPrueba.setFechaDeCierre("2024-05-05");
        actividadPrueba.setIdColaboracion(1);
        actividadPrueba.setNumeroActividad(1);
        actividadPrueba.setIdActividad(1);
        actividadPrueba.setEstado(EnumActividades.Inactiva.toString());
        actividadesEsperadas.add(actividadPrueba);        
        List<Actividad> actividadesObtenidas = implementacion.obtenerActividades(0);
        assertNotEquals(actividadesEsperadas,actividadesObtenidas);
    }
        
    @Test
    public void pruebaObtenerActividadesSinConexionExitosa(){
        DAOActividadImplementacion implementacion = new DAOActividadImplementacion();                                        
        List<Actividad> actividadesObtenidas = implementacion.obtenerActividades(1);
        assertTrue(actividadesObtenidas.isEmpty());
    }
    
    @Test
    public void pruebaObtenerNumeroDeActividadExitosa(){
        DAOActividadImplementacion implementacion = new DAOActividadImplementacion();
        Actividad actividadPrueba = new Actividad();
        actividadPrueba.setNombre("SO móviles");        
        actividadPrueba.setDescripcion("Conocer los distintos SO móviles");
        int resultadoObtenido=implementacion.obtenerNumeroDeActividad(actividadPrueba);                                 
        assertEquals(1,resultadoObtenido);
    }
    
    @Test
    public void pruebaObtenerNumeroDeActividadFallida(){
        DAOActividadImplementacion implementacion = new DAOActividadImplementacion();
        Actividad actividadPrueba = new Actividad();
        actividadPrueba.setNombre("Actividad de SO");        
        actividadPrueba.setDescripcion("Historia de los SO");
        int resultadoObtenido=implementacion.obtenerNumeroDeActividad(actividadPrueba);                                 
        assertEquals(0,resultadoObtenido);        
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
    public void pruebaValidarInexistenciaActividadExitosa(){
        DAOActividadImplementacion implementacion = new DAOActividadImplementacion();
        Actividad actividadPrueba = new Actividad();
        actividadPrueba.setNombre("SO");                                
        actividadPrueba.setIdColaboracion(2);
        actividadPrueba.setNumeroActividad(2);         
        boolean resultado = implementacion.validarInexistenciaDeActividad(actividadPrueba);
        assertFalse(resultado);
    }
    
    @Test
    public void pruebaValidarInexistenciaActividadFallida(){
        DAOActividadImplementacion implementacion = new DAOActividadImplementacion();
        Actividad actividadPrueba = new Actividad();
        actividadPrueba.setNombre("SO móviles");                                
        actividadPrueba.setIdColaboracion(1);
        actividadPrueba.setNumeroActividad(1);         
        boolean resultado = implementacion.validarInexistenciaDeActividad(actividadPrueba);
        assertTrue(resultado);
    }
        
    @Test
    public void pruebaValidarInexistenciaActividadSinConexionExitosa(){
        DAOActividadImplementacion implementacion = new DAOActividadImplementacion();
        Actividad actividadPrueba = new Actividad();
        actividadPrueba.setNombre("SO móviles");                                
        actividadPrueba.setIdColaboracion(1);
        actividadPrueba.setNumeroActividad(1);         
        boolean resultado = implementacion.validarInexistenciaDeActividad(actividadPrueba);
        assertFalse(resultado);
    }
        
    @Test
    public void pruebaActualizarEstadoActividadExitosa(){
        DAOActividadImplementacion implementacion = new DAOActividadImplementacion();
        Actividad actividadPrueba = new Actividad();                                
        actividadPrueba.setNumeroActividad(1);
        actividadPrueba.setIdActividad(1);        
        int resultado = implementacion.actualizarEstadoActividad(actividadPrueba, EnumActividades.Activa.toString());
        assertEquals(1, resultado);
    }
    
    @Test
    public void pruebaActualizarEstadoActividadFallida(){
        DAOActividadImplementacion implementacion = new DAOActividadImplementacion();
        Actividad actividadPrueba = new Actividad();        
        actividadPrueba.setIdActividad(0);
        actividadPrueba.setIdColaboracion(0);        
        int resultado = implementacion.actualizarEstadoActividad(actividadPrueba, EnumActividades.Activa.toString());
        assertEquals(0, resultado);
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
    public void pruebaModificarActividadExitosa(){
        DAOActividadImplementacion implementacion = new DAOActividadImplementacion();
       Actividad actividadPrueba = new Actividad();
        actividadPrueba.setNombre("SO móviles nuevos");
        actividadPrueba.setDescripcion("Conocer los distintos SO móviles");                        
        actividadPrueba.setIdActividad(1);
        actividadPrueba.setEstado(EnumActividades.Activa.toString());      
        int resultadoInsercion = implementacion.modificarActividad(actividadPrueba);
        assertEquals(1,resultadoInsercion);
    }
    
    @Test
    public void pruebaModificarActividadFallida(){
        DAOActividadImplementacion implementacion = new DAOActividadImplementacion();
        Actividad actividadPrueba = new Actividad();
        actividadPrueba.setNombre("SO");
        actividadPrueba.setDescripcion("Conocer los distintos SO móviles");                        
        actividadPrueba.setIdActividad(0);
        actividadPrueba.setEstado(EnumActividades.Activa.toString());      
        int resultadoInsercion = implementacion.modificarActividad(actividadPrueba);
        assertEquals(0,resultadoInsercion);
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
    public void pruebaModificarFechaActividadExitosa(){
        DAOActividadImplementacion implementacion = new DAOActividadImplementacion();
        Actividad actividadPrueba = new Actividad();                
        actividadPrueba.setFechaDeInicio("2024-06-09");
        actividadPrueba.setFechaDeCierre("2024-06-11");                
        actividadPrueba.setIdActividad(1);        
        int resultadoInsercion = implementacion.modificarFechaActividad(actividadPrueba);
        assertEquals(1,resultadoInsercion);
    }
    
    @Test
    public void pruebaModificarFechaActividadFallida(){
        DAOActividadImplementacion implementacion = new DAOActividadImplementacion();
        Actividad actividadPrueba = new Actividad();                
        actividadPrueba.setFechaDeInicio("2024-06-09");
        actividadPrueba.setFechaDeCierre("2024-06-11");                
        actividadPrueba.setIdActividad(0);        
        int resultadoInsercion = implementacion.modificarFechaActividad(actividadPrueba);
        assertEquals(0,resultadoInsercion);
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
