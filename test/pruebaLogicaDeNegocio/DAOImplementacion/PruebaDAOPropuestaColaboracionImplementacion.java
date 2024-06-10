package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOPropuestaColaboracionImplementacion;
import logicaDeNegocio.clases.PropuestaColaboracion;
import logicaDeNegocio.clases.TipoColaboracion;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
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
    public void pruebaObtenerPropuestaDeColaboracionPorIdExitosa(){
        PropuestaColaboracion propuestaEsperada=new PropuestaColaboracion();
        propuestaEsperada.setExperienciaEducativa("Programacion");
        propuestaEsperada.setObjetivo("Fundamentos de la programación");       
        TipoColaboracion tipoColaboracion = new TipoColaboracion();
        tipoColaboracion.setIdTipoColaboracion(1);
        tipoColaboracion.setTipo("Clase espejo");
        propuestaEsperada.setTipoColaboracion(tipoColaboracion);
        DAOPropuestaColaboracionImplementacion daoPropuesta=new DAOPropuestaColaboracionImplementacion();
        PropuestaColaboracion propuestaObtenida=daoPropuesta.obtenerPropuestaDeColaboracionPorId(1);
        assertEquals(propuestaEsperada,propuestaObtenida);                        
    }
    
    @Test
    public void pruebaObtenerPropuestaDeColaboracionPorIdFallida(){               
        PropuestaColaboracion propuestaEsperada=new PropuestaColaboracion();
        propuestaEsperada.setExperienciaEducativa("Programacion");
        propuestaEsperada.setObjetivo("Fundamentos de la programación");       
        TipoColaboracion tipoColaboracion = new TipoColaboracion();
        tipoColaboracion.setIdTipoColaboracion(1);
        tipoColaboracion.setTipo("Clase espejo");
        propuestaEsperada.setTipoColaboracion(tipoColaboracion);
        DAOPropuestaColaboracionImplementacion daoPropuesta=new DAOPropuestaColaboracionImplementacion();
        PropuestaColaboracion propuestaObtenida=daoPropuesta.obtenerPropuestaDeColaboracionPorId(0);
        assertNotEquals(propuestaEsperada,propuestaObtenida);         
    }
        
    @Test
    public void pruebaObtenerPropuestaDeColaboracionPorIdSinConexionExitosa(){
        DAOPropuestaColaboracionImplementacion daoPropuesta=new DAOPropuestaColaboracionImplementacion();
        PropuestaColaboracion propuestaObtenida=daoPropuesta.obtenerPropuestaDeColaboracionPorId(0);
        assertNull(propuestaObtenida);          
    } 
    
    @Test
    public void pruebaConsultarPropuestasDeColaboracionRegistradasExitosa(){
        PropuestaColaboracion propuestaEsperada=new PropuestaColaboracion();
        propuestaEsperada.setExperienciaEducativa("Programacion");
        propuestaEsperada.setObjetivo("Fundamentos de la programación");       
        TipoColaboracion tipoColaboracion = new TipoColaboracion();
        tipoColaboracion.setIdTipoColaboracion(1);
        tipoColaboracion.setTipo("Clase espejo");
        propuestaEsperada.setTipoColaboracion(tipoColaboracion);
        List<PropuestaColaboracion> propuestasEsperadas=new ArrayList<>();
        propuestasEsperadas.add(propuestaEsperada);
        DAOPropuestaColaboracionImplementacion daoPropuesta=new DAOPropuestaColaboracionImplementacion();
        List<PropuestaColaboracion> propuestasObtenidas=daoPropuesta.consultarPropuestasDeColaboracionRegistradas();
        assertEquals(propuestasEsperadas,propuestasObtenidas);        
    }
    
    @Test
    public void pruebaConsultarPropuestasDeColaboracionRegistradasFallida(){        
        List<PropuestaColaboracion> propuestasEsperadas=new ArrayList<>();        
        DAOPropuestaColaboracionImplementacion daoPropuesta=new DAOPropuestaColaboracionImplementacion();
        List<PropuestaColaboracion> propuestasObtenidas=daoPropuesta.consultarPropuestasDeColaboracionRegistradas();
        assertNotEquals(propuestasEsperadas,propuestasObtenidas);         
    }
        
    @Test
    public void pruebaConsultarPropuestasDeColaboracionRegistradasSinConexionExitosa(){
        DAOPropuestaColaboracionImplementacion daoPropuesta=new DAOPropuestaColaboracionImplementacion();
        List<PropuestaColaboracion> propuestasObtenidas=daoPropuesta.consultarPropuestasDeColaboracionRegistradas();
        assertTrue(propuestasObtenidas.isEmpty());
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
    public void pruebaCambiarEstadoIniciadaPropuestaColaboracionPorIdExitosa(){
        DAOPropuestaColaboracionImplementacion daoPropuesta=new DAOPropuestaColaboracionImplementacion();
        int resultadoObtenido=daoPropuesta.cambiarEstadoIniciadaPropuestaColaboracionPorId(1);
        assertEquals(1,resultadoObtenido);        
    }
    
    @Test
    public void pruebaCambiarEstadoIniciadaPropuestaColaboracionPorIdFallida(){
        DAOPropuestaColaboracionImplementacion daoPropuesta=new DAOPropuestaColaboracionImplementacion();
        int resultadoObtenido=daoPropuesta.cambiarEstadoIniciadaPropuestaColaboracionPorId(0);
        assertEquals(0,resultadoObtenido);                
    }
        
    @Test
    public void pruebaCambiarEstadoIniciadaPropuestaColaboracionPorIdSinConexionExitosa(){
        DAOPropuestaColaboracionImplementacion daoPropuesta=new DAOPropuestaColaboracionImplementacion();
        int resultadoObtenido=daoPropuesta.cambiarEstadoIniciadaPropuestaColaboracionPorId(0);
        assertEquals(-1,resultadoObtenido);                
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
    public void pruebaConsultarPropuestasDeColaboracionAprobadasExitosa(){
        PropuestaColaboracion propuestaEsperada=new PropuestaColaboracion();
        propuestaEsperada.setExperienciaEducativa("Programacion");
        propuestaEsperada.setObjetivo("Fundamentos de la programación");       
        TipoColaboracion tipoColaboracion = new TipoColaboracion();
        tipoColaboracion.setIdTipoColaboracion(1);
        tipoColaboracion.setTipo("Clase espejo");
        propuestaEsperada.setTipoColaboracion(tipoColaboracion);
        List<PropuestaColaboracion> propuestasEsperadas=new ArrayList<>();
        propuestasEsperadas.add(propuestaEsperada);
        DAOPropuestaColaboracionImplementacion daoPropuesta=new DAOPropuestaColaboracionImplementacion();
        List<PropuestaColaboracion> propuestasObtenidas=daoPropuesta.consultarPropuestasDeColaboracionAprobadas();
        assertEquals(propuestasEsperadas,propuestasObtenidas);      
    }
    
    @Test
    public void pruebaConsultarPropuestasDeColaboracionAprobadasFallida(){
        DAOPropuestaColaboracionImplementacion instancia = new DAOPropuestaColaboracionImplementacion();        
        List<PropuestaColaboracion> resultadoObtenido = instancia.consultarPropuestasDeColaboracionAprobadas();
        assertTrue(resultadoObtenido.isEmpty());        
    }
        
    @Test
    public void pruebaConsultarPropuestasDeColaboracionAprobadasSinConexionExitosa(){
        DAOPropuestaColaboracionImplementacion instancia = new DAOPropuestaColaboracionImplementacion();
        List<PropuestaColaboracion> propuestasConsultadas = instancia.consultarPropuestasDeColaboracionAprobadas();
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
        int resultadoObtenido = instancia.obtenerIdPropuestaColaboracionAprobadaPorIdProfesor(0);
        assertEquals(resultadoEsperado, resultadoObtenido);                        
    }
                        
    @Test
    public void pruebaObtenerIdPropuestaColaboracionAprobadaPorIdProfesorSinConexionExitosa(){
        DAOPropuestaColaboracionImplementacion instancia = new DAOPropuestaColaboracionImplementacion();
        int idProfesor = 1;
        int resultadoEsperado = -1;
        int resultadoObtenido = instancia.obtenerIdPropuestaColaboracionAprobadaPorIdProfesor(idProfesor);
        assertEquals(resultadoEsperado, resultadoObtenido);
    }
    
    @Test
    public void pruebaConsultarPropuestasDeColaboracionAprobadasSinPeticionesExitosa(){
        PropuestaColaboracion propuestaEsperada=new PropuestaColaboracion();
        propuestaEsperada.setExperienciaEducativa("Programacion");
        propuestaEsperada.setObjetivo("Fundamentos de la programación");       
        TipoColaboracion tipoColaboracion = new TipoColaboracion();
        tipoColaboracion.setIdTipoColaboracion(1);
        tipoColaboracion.setTipo("Clase espejo");
        propuestaEsperada.setTipoColaboracion(tipoColaboracion);
        List<PropuestaColaboracion> propuestasEsperadas=new ArrayList<>();
        propuestasEsperadas.add(propuestaEsperada);        
        DAOPropuestaColaboracionImplementacion instancia = new DAOPropuestaColaboracionImplementacion();
        int idProfesor = 1;        
        List<PropuestaColaboracion> propuestasObtenidas= instancia.consultarPropuestasDeColaboracionAprobadasSinPeticiones(idProfesor);
        assertEquals(propuestasEsperadas,propuestasObtenidas);                
    }
    
    @Test
    public void pruebaConsultarPropuestasDeColaboracionAprobadasSinPeticionesFallida(){
        PropuestaColaboracion propuestaEsperada=new PropuestaColaboracion();
        propuestaEsperada.setExperienciaEducativa("Programacion");
        propuestaEsperada.setObjetivo("Fundamentos de la programación");       
        TipoColaboracion tipoColaboracion = new TipoColaboracion();
        tipoColaboracion.setIdTipoColaboracion(1);
        tipoColaboracion.setTipo("Clase espejo");
        propuestaEsperada.setTipoColaboracion(tipoColaboracion);
        List<PropuestaColaboracion> propuestasEsperadas=new ArrayList<>();
        propuestasEsperadas.add(propuestaEsperada);        
        DAOPropuestaColaboracionImplementacion instancia = new DAOPropuestaColaboracionImplementacion();
        int idProfesor = 0;        
        List<PropuestaColaboracion> propuestasObtenidas= instancia.consultarPropuestasDeColaboracionAprobadasSinPeticiones(idProfesor);
        assertNotEquals(propuestasEsperadas,propuestasObtenidas);         
    }
        
    @Test
    public void pruebaConsultarPropuestasDeColaboracionAprobadasSinPeticionesSinConexionExitosa(){
        DAOPropuestaColaboracionImplementacion instancia = new DAOPropuestaColaboracionImplementacion();
        int idProfesor = 0;        
        List<PropuestaColaboracion> propuestasObtenidas= instancia.consultarPropuestasDeColaboracionAprobadasSinPeticiones(idProfesor);
        assertTrue(propuestasObtenidas.isEmpty());         
    }        
}
