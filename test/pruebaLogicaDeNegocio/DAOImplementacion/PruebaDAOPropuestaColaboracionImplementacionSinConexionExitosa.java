package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOPropuestaColaboracionImplementacion;
import logicaDeNegocio.clases.PropuestaColaboracion;
import logicaDeNegocio.clases.TipoColaboracion;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class PruebaDAOPropuestaColaboracionImplementacionSinConexionExitosa {
    
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
    public void pruebaObtenerPropuestaDeColaboracionPorIdSinConexionExitosa(){
        DAOPropuestaColaboracionImplementacion daoPropuesta=new DAOPropuestaColaboracionImplementacion();
        PropuestaColaboracion propuestaObtenida=daoPropuesta.obtenerPropuestaDeColaboracionPorId(0);
        assertNull(propuestaObtenida);          
    } 
    
    @Test
    public void pruebaConsultarPropuestasDeColaboracionRegistradasSinConexionExitosa(){
        DAOPropuestaColaboracionImplementacion daoPropuesta=new DAOPropuestaColaboracionImplementacion();
        List<PropuestaColaboracion> propuestasObtenidas=daoPropuesta.consultarPropuestasDeColaboracionRegistradas();
        assertTrue(propuestasObtenidas.isEmpty());
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
    public void pruebaCambiarEstadoIniciadaPropuestaColaboracionPorIdSinConexionExitosa(){
        DAOPropuestaColaboracionImplementacion daoPropuesta=new DAOPropuestaColaboracionImplementacion();
        int resultadoObtenido=daoPropuesta.cambiarEstadoIniciadaPropuestaColaboracionPorId(0);
        assertEquals(-1,resultadoObtenido);                
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
    public void pruebaConsultarPropuestasDeColaboracionAprobadasSinConexionExitosa(){
        DAOPropuestaColaboracionImplementacion instancia = new DAOPropuestaColaboracionImplementacion();
        List<PropuestaColaboracion> propuestasConsultadas = instancia.consultarPropuestasDeColaboracionAprobadas();
        assertTrue(propuestasConsultadas.isEmpty());
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
    public void pruebaConsultarPropuestasDeColaboracionAprobadasSinPeticionesSinConexionExitosa(){
        DAOPropuestaColaboracionImplementacion instancia = new DAOPropuestaColaboracionImplementacion();
        int idProfesor = 0;        
        List<PropuestaColaboracion> propuestasObtenidas= instancia.consultarPropuestasDeColaboracionAprobadasSinPeticiones(idProfesor);
        assertTrue(propuestasObtenidas.isEmpty());         
    } 
    
}