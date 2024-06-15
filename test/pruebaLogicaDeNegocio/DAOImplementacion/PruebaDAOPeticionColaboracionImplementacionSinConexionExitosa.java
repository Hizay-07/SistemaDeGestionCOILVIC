package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOPeticionColaboracionImplementacion;
import logicaDeNegocio.clases.PeticionColaboracion;
import logicaDeNegocio.enums.EnumPeticionColaboracion;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class PruebaDAOPeticionColaboracionImplementacionSinConexionExitosa {
    
    @Test
    public void pruebaRegistrarPeticionColaboracionSinConexionExitosa(){
        PeticionColaboracion peticion = new PeticionColaboracion();
        peticion.setIdProfesor(3);
        peticion.setIdPropuestaColaboracion(1);
        peticion.setEstado(EnumPeticionColaboracion.Registrada.toString());
        peticion.setFechaEnvio("2024-06-08");
        DAOPeticionColaboracionImplementacion instancia = new DAOPeticionColaboracionImplementacion();
        int resultadoObtenido = instancia.registrarPeticionColaboracion(peticion);
        assertEquals(0, resultadoObtenido);
    }
    
    @Test
    public void pruebaConsultarPeticionesSinConexionExitosa(){
        DAOPeticionColaboracionImplementacion instancia = new DAOPeticionColaboracionImplementacion();
        List<PeticionColaboracion> peticiones = instancia.consultarPeticiones();
        assertTrue(peticiones.isEmpty());
    }
    
    @Test 
    public void pruebaConsultarIdPropuestaDeColaboracionPorIdProfesorSinConexionExitosa(){
        DAOPeticionColaboracionImplementacion instancia = new DAOPeticionColaboracionImplementacion();
        int resultadoObtenido = instancia.consultarIdPropuestaDeColaboracionPorIdProfesor(3);
        assertEquals(-1, resultadoObtenido);
    }
    
    @Test 
    public void pruebaConsultarIdProfesoresPorIdPropuestaColaboracionSincConexionExitosa(){
        int idPropuestaColaboracion = 1;
        DAOPeticionColaboracionImplementacion instancia = new DAOPeticionColaboracionImplementacion();
        List<Integer> resultadoObtenido = instancia.consultarIdProfesoresPorIdPropuestaColaboracion(idPropuestaColaboracion);
        assertTrue(resultadoObtenido.isEmpty());
    }
    
    @Test 
    public void pruebaAceptarPeticionColaboracionSinConexionExitosa(){
        int idPropuestaColaboracion = 1;
        int idProfesor = 1;
        DAOPeticionColaboracionImplementacion daoPeticionColaboracion = new DAOPeticionColaboracionImplementacion();
        int resultadoObtenido = daoPeticionColaboracion.aceptarPeticionColaboracion(idPropuestaColaboracion, idProfesor);
        assertEquals(0, resultadoObtenido);
    }
    
    @Test
    public void pruebaRechazarPeticionColaboracionSinConexionExitosa(){
        int idPropuestaColaboracion = 1;
        int idProfesor = 1;
        DAOPeticionColaboracionImplementacion daoPeticionColaboracion = new DAOPeticionColaboracionImplementacion();
        int resultadoObtenido = daoPeticionColaboracion.rechazarPeticionColaboracion(idPropuestaColaboracion, idProfesor);
        assertEquals(0, resultadoObtenido);         
    }
    
    @Test
    public void consultarIdProfesoresPorIdPropuestaColaboracionAceptadasSinConexionExitosa(){
        int idPropuestaColaboracion = 1;
        DAOPeticionColaboracionImplementacion instancia = new DAOPeticionColaboracionImplementacion();
        List<Integer> resultadoObtenido = instancia.consultarIdProfesoresPorIdPropuestaColaboracionAceptadas(idPropuestaColaboracion);
        assertTrue(resultadoObtenido.isEmpty());
    }
    
    @Test
    public void revisarPrecondicionEvaluarPeticionesPorIdProfesorSinConexionExitosa() {
        int idProfesor = 1;
        DAOPeticionColaboracionImplementacion instancia = new DAOPeticionColaboracionImplementacion();
        int resultadoObtenido = instancia.revisarPrecondicionEvaluarPeticionesPorIdProfesor(idProfesor);
        assertEquals(-1, resultadoObtenido);
    }
    
    @Test
    public void cambiarEstadoPeticionesRegistradasPorIdPropuestaSinConexionExitosa() {
        int idPropuesta = 1;
        DAOPeticionColaboracionImplementacion instancia = new DAOPeticionColaboracionImplementacion();
        int resultadoObtenido = instancia.cambiarEstadoPeticionesRegistradasPorIdPropuesta(idPropuesta);
        assertEquals(-1, resultadoObtenido);
    }        
}
