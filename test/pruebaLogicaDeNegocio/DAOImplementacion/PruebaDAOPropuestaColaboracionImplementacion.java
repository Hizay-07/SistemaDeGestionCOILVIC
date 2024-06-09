package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOPropuestaColaboracionImplementacion;
import logicaDeNegocio.clases.Profesor;
import logicaDeNegocio.clases.PropuestaColaboracion;
import logicaDeNegocio.clases.TipoColaboracion;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;

public class PruebaDAOPropuestaColaboracionImplementacion {
    
    @Before
    public void setUp() {
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentapruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        UsuarioSingleton.getInstancia(usuarioPrueba);
    }
    
    @Test
    public void pruebaRegistrarPropuestaColaboracionExitosa(){
        DAOPropuestaColaboracionImplementacion instancia = new DAOPropuestaColaboracionImplementacion();
        PropuestaColaboracion propuestaColaboracion = new PropuestaColaboracion();
        propuestaColaboracion.setFechaInicio("2024-06-02");
        propuestaColaboracion.setFechaCierre("2024-07-23");
        propuestaColaboracion.setIdioma("Español");
        propuestaColaboracion.setExperienciaEducativa("Programacion");
        propuestaColaboracion.setObjetivo("Fundamentos de la programación");
        propuestaColaboracion.setProgramaEducativoEstudiantil("Ingenieria de software");
        propuestaColaboracion.setEstadoPropuesta("Registrada");
        TipoColaboracion tipoColaboracion = new TipoColaboracion();
        tipoColaboracion.setIdTipoColaboracion(1);
        propuestaColaboracion.setTipoColaboracion(tipoColaboracion);
        int resultadoEsperado = 1;       
        int resultadoObtenido = instancia.registrarPropuestaColaboracion(propuestaColaboracion);
        assertEquals(resultadoEsperado, resultadoObtenido);                                
    }
    
    @Test(expected = AssertionError.class)
    public void pruebaRegistrarPropuestaColaboracionFallida(){
        DAOPropuestaColaboracionImplementacion instancia = new DAOPropuestaColaboracionImplementacion();
        PropuestaColaboracion propuestaColaboracion = new PropuestaColaboracion();       
        int resultadoEsperado = 0;       
        int resultadoObtenido = instancia.registrarPropuestaColaboracion(propuestaColaboracion);
        assertEquals(resultadoEsperado, resultadoObtenido);        
    }
    
    @Test
    public void pruebaRegistrarPropuestaColaboracionSinConexionExitosa(){
        DAOPropuestaColaboracionImplementacion instancia = new DAOPropuestaColaboracionImplementacion();
        PropuestaColaboracion propuestaColaboracion = new PropuestaColaboracion();
        propuestaColaboracion.setFechaInicio("2024-06-02");
        propuestaColaboracion.setFechaCierre("2024-07-23");
        propuestaColaboracion.setIdioma("Ingles");
        propuestaColaboracion.setExperienciaEducativa("Programacion");
        propuestaColaboracion.setObjetivo("Fundamentos de la programación");
        propuestaColaboracion.setProgramaEducativoEstudiantil("Ingenieria de software");
        propuestaColaboracion.setEstadoPropuesta("Registrada");
        TipoColaboracion tipoColaboracion = new TipoColaboracion();
        tipoColaboracion.setIdTipoColaboracion(1);
        propuestaColaboracion.setTipoColaboracion(tipoColaboracion);
        int resultadoEsperado = -1;       
        int resultadoObtenido = instancia.registrarPropuestaColaboracion(propuestaColaboracion);
        assertEquals(resultadoEsperado, resultadoObtenido);                                
    }
    
    @Test
    public void pruebaConsultarPropuestasColaboracionExitoso() {
        DAOPropuestaColaboracionImplementacion instancia = new DAOPropuestaColaboracionImplementacion();
        List<PropuestaColaboracion> propuestasConsultadas = instancia.consultarPropuestasColaboracion();        
        assertFalse(propuestasConsultadas.isEmpty());
    }
               
    @Test (expected = AssertionError.class)
    public void pruebaConsultarPropuestasColaboracionFallida(){
        DAOPropuestaColaboracionImplementacion instancia = new DAOPropuestaColaboracionImplementacion();
        List<PropuestaColaboracion> propuestasConsultadas = instancia.consultarPropuestasColaboracion();        
        assertTrue(propuestasConsultadas.isEmpty());
    }           
    
    @Test
    public void pruebaConsultarPropuestasColaboracionSinConexionExitoso() {
        DAOPropuestaColaboracionImplementacion instancia = new DAOPropuestaColaboracionImplementacion();
        List<PropuestaColaboracion> propuestasConsultadas = instancia.consultarPropuestasColaboracion();
        assertTrue(propuestasConsultadas.isEmpty());
    }
    
    @Test
    public void pruebaAprobarPropuestaColaboracionPorIdExitosa(){
        DAOPropuestaColaboracionImplementacion instancia = new DAOPropuestaColaboracionImplementacion();
        int resultadoEsperado = 1;
        int resultadoObtenido = instancia.aprobarPropuestaColaboracionPorId(1);
        assertEquals(resultadoEsperado, resultadoObtenido);                        
    }        
        
    @Test
    public void pruebaAprobarPropuestaColaboracionPorIdFallida(){
        DAOPropuestaColaboracionImplementacion instancia = new DAOPropuestaColaboracionImplementacion();
        int resultadoEsperado = 0;
        int resultadoObtenido = instancia.aprobarPropuestaColaboracionPorId(0);
        assertEquals(resultadoEsperado, resultadoObtenido);        
    }
    
    @Test
    public void pruebaAprobarPropuestaColaboracionPorIdSinConexionExitosa(){
        DAOPropuestaColaboracionImplementacion instancia = new DAOPropuestaColaboracionImplementacion();
        int idPropuesta = 1;
        int resultadoEsperado = -1;
        int resultadoObtenido = instancia.aprobarPropuestaColaboracionPorId(idPropuesta);
        assertEquals(resultadoEsperado, resultadoObtenido);
    }
    
    @Test
    public void pruebaRechazarPropuestaColaboracionPorIdExitosa(){
        DAOPropuestaColaboracionImplementacion instancia = new DAOPropuestaColaboracionImplementacion();
        int resultadoEsperado = 1;
        int resultadoObtenido = instancia.rechazarPropuestaColaboracionPorId(1);
        assertEquals(resultadoEsperado, resultadoObtenido);                                
    }            
    
    @Test
    public void pruebaRechazarPropuestaColaboracionPorIdFallida(){
        DAOPropuestaColaboracionImplementacion instancia = new DAOPropuestaColaboracionImplementacion();
        int resultadoEsperado = 0;
        int resultadoObtenido = instancia.rechazarPropuestaColaboracionPorId(0);
        assertEquals(resultadoEsperado, resultadoObtenido);        
    }
    
    @Test
    public void pruebaRechazarPropuestaColaboracionPorIdSinConexionExitosa(){
        DAOPropuestaColaboracionImplementacion instancia = new DAOPropuestaColaboracionImplementacion();
        int idPropuesta = 1;
        int resultadoEsperado = -1;
        int resultadoObtenido = instancia.rechazarPropuestaColaboracionPorId(idPropuesta);
        assertEquals(resultadoEsperado, resultadoObtenido);
    }
    
    @Test
    public void pruebaConsultarPropuestasDeColaboracionAprobadasExitosa(){
        DAOPropuestaColaboracionImplementacion instancia = new DAOPropuestaColaboracionImplementacion();
        PropuestaColaboracion propuestaColaboracion = new PropuestaColaboracion();
        TipoColaboracion tipoColaboracion = new TipoColaboracion();
        tipoColaboracion.setIdTipoColaboracion(1);
        propuestaColaboracion.setTipoColaboracion(tipoColaboracion);
        propuestaColaboracion.setExperienciaEducativa("Programacion");
        propuestaColaboracion.setObjetivo("Fundamentos de la programación");
        List<PropuestaColaboracion> resultadoEsperado = new ArrayList<>();
        resultadoEsperado.add(propuestaColaboracion);
        List<PropuestaColaboracion> resultadoObtenido = instancia.consultarPropuestasDeColaboracionAprobadas();
        assertEquals(resultadoEsperado, resultadoObtenido);        
    }
    
    @Test
    public void pruebaConsultarPropuestasDeColaboracionAprobadasFallida(){
        DAOPropuestaColaboracionImplementacion instancia = new DAOPropuestaColaboracionImplementacion();
        List<PropuestaColaboracion> resultadoEsperado = new ArrayList<>();
        List<PropuestaColaboracion> resultadoObtenido = instancia.consultarPropuestasDeColaboracionAprobadas();
        assertNotEquals(resultadoEsperado, resultadoObtenido);        
    }
    
    @Test
    public void pruebaConsultarPropuestasDeColaboracionAprobadasSinConexionExitosa(){
        DAOPropuestaColaboracionImplementacion instancia = new DAOPropuestaColaboracionImplementacion();
        List<PropuestaColaboracion> propuestasConsultadas = instancia.consultarPropuestasDeColaboracionAprobadas();
        assertTrue(propuestasConsultadas.isEmpty());
    }
    
    @Test
    public void pruebaConsultarPropuestasDeColaboracionRegistradasExitosa(){
        DAOPropuestaColaboracionImplementacion instancia = new DAOPropuestaColaboracionImplementacion();
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario("cuentaadmin@gmail.com");
        usuario.setContrasenia("Contrasenia123*");
        usuario.setCorreo("cuentaadmin@gmail.com");
        usuario.setTipoDeUsuario("Administrativo");
        UsuarioSingleton.getInstancia(usuario);
        PropuestaColaboracion propuestaColaboracion = new PropuestaColaboracion();
        TipoColaboracion tipoColaboracion = new TipoColaboracion();
        Profesor profesor = new Profesor();
        tipoColaboracion.setIdTipoColaboracion(1);
        tipoColaboracion.setTipo("Clase espejo");
        propuestaColaboracion.setTipoColaboracion(tipoColaboracion);
        propuestaColaboracion.setIdioma("Ingles");
        propuestaColaboracion.setExperienciaEducativa("Programación");
        propuestaColaboracion.setObjetivo("Fundamentos de la programación");
        propuestaColaboracion.setFechaInicio("2024-10-16");
        propuestaColaboracion.setFechaCierre("2024-12-12");
        propuestaColaboracion.setEstadoPropuesta("Aprobada");
        propuestaColaboracion.setIdPropuestaColaboracion(1);
        profesor.setIdProfesor(1);
        profesor.setNombre("Beto");
        profesor.setApellidoPaterno("Gómez");
        profesor.setApellidoMaterno("Pérez");
        profesor.setCorreo("profesorpruebaunocambio@gmail.com");
        profesor.setEstado("Esperando");
        propuestaColaboracion.setProfesor(profesor);
        List<PropuestaColaboracion> resultadoEsperado = new ArrayList<>();
        resultadoEsperado.add(propuestaColaboracion);
        List<PropuestaColaboracion> resultadoObtenido = instancia.consultarPropuestasDeColaboracionRegistradas();
        assertEquals(resultadoEsperado, resultadoObtenido);    
    }
    
    @Test
    public void pruebaConsultarPropuestasDeColaboracionRegistradasFallida(){
        DAOPropuestaColaboracionImplementacion instancia = new DAOPropuestaColaboracionImplementacion();
        List<PropuestaColaboracion> resultadoEsperado = new ArrayList<>();
        List<PropuestaColaboracion> resultadoObtenido = instancia.consultarPropuestasDeColaboracionRegistradas();
        assertNotEquals(resultadoEsperado, resultadoObtenido);    
    }
    
    @Test
    public void pruebaConsultarPropuestasDeColaboracionRegistradasSinConexionExitosa(){
        DAOPropuestaColaboracionImplementacion instancia = new DAOPropuestaColaboracionImplementacion();
        List<PropuestaColaboracion> propuestasConsultadas = instancia.consultarPropuestasDeColaboracionRegistradas();
        assertTrue(propuestasConsultadas.isEmpty());
    }
    
    @Test
    public void pruebaObtenerIdPropuestaColaboracionAprobadaPorIdProfesorExitosa(){
        DAOPropuestaColaboracionImplementacion instancia = new DAOPropuestaColaboracionImplementacion();
        int idProfesor = 1;
        int resultadoEsperado = 1;
        int resultadoObtenido = instancia.obtenerIdPropuestaColaboracionAprobadaPorIdProfesor(idProfesor);
        assertEquals(resultadoEsperado, resultadoObtenido);                        
    }
    
    @Test
        public void pruebaObtenerIdPropuestaColaboracionAprobadaPorIdProfesorFallida(){
        DAOPropuestaColaboracionImplementacion instancia = new DAOPropuestaColaboracionImplementacion();
        int resultadoEsperado = 0;
        int resultadoObtenido = instancia.obtenerIdPropuestaColaboracionAprobadaPorIdProfesor(2);
        assertNotEquals(resultadoEsperado, resultadoObtenido);                        
    }
        
    @Test
    public void pruebaObtenerIdPropuestaColaboracionAprobadaPorIdProfesorSinConexionExitosa(){
        DAOPropuestaColaboracionImplementacion instancia = new DAOPropuestaColaboracionImplementacion();
        int idProfesor = 1;
        int resultadoEsperado = -1;
        int resultadoObtenido = instancia.obtenerIdPropuestaColaboracionAprobadaPorIdProfesor(idProfesor);
        assertEquals(resultadoEsperado, resultadoObtenido);
    }
    
}
