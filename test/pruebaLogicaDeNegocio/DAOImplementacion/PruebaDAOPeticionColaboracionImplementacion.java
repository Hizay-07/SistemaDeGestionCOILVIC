package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOPeticionColaboracionImplementacion;
import logicaDeNegocio.clases.PeticionColaboracion;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;
import logicaDeNegocio.enums.EnumPeticionColaboracion;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class PruebaDAOPeticionColaboracionImplementacion {
    
    @Before
    public void setUp() {
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentapruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        UsuarioSingleton.getInstancia(usuarioPrueba);
    }
    
    @Test
    public void pruebaRegistrarPeticionColaboracionExitosa(){
        PeticionColaboracion peticion = new PeticionColaboracion();
        peticion.setIdProfesor(3); 
        peticion.setIdPropuestaColaboracion(1);
        peticion.setEstado(EnumPeticionColaboracion.Registrada.toString());
        peticion.setFechaEnvio("2024-06-08");
        DAOPeticionColaboracionImplementacion instancia = new DAOPeticionColaboracionImplementacion();
        int resultadoEsperado = 1; 
        int resultadoObtenido = instancia.registrarPeticionColaboracion(peticion);
        assertEquals(resultadoEsperado, resultadoObtenido);
    }
    
    @Test
    public void pruebaRegistrarPeticionColaboracionFracaso(){
        PeticionColaboracion peticion = new PeticionColaboracion();        
        DAOPeticionColaboracionImplementacion instancia = new DAOPeticionColaboracionImplementacion();
        int resultadoEsperado = 0; 
        int resultadoObtenido = instancia.registrarPeticionColaboracion(peticion);
        assertEquals(resultadoEsperado, resultadoObtenido);
    }
    
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
    public void pruebaConsultarPeticionesExitosa() {
        DAOPeticionColaboracionImplementacion instancia = new DAOPeticionColaboracionImplementacion();
        List<PeticionColaboracion> peticiones = instancia.consultarPeticiones();
        assertEquals(false, peticiones.isEmpty());
    }
    
    @Test (expected = AssertionError.class)
    public void pruebaConsultarPeticionesFracaso() {
        DAOPeticionColaboracionImplementacion instancia = new DAOPeticionColaboracionImplementacion();
        List<PeticionColaboracion> peticiones = instancia.consultarPeticiones();
        assertEquals(true, peticiones.isEmpty());
    }
    
    @Test
    public void pruebaConsultarPeticionesSinConexionExitosa(){
        DAOPeticionColaboracionImplementacion instancia = new DAOPeticionColaboracionImplementacion();
        List<PeticionColaboracion> peticiones = instancia.consultarPeticiones();
        assertTrue(peticiones.isEmpty());
    }
    
    @Test 
    public void pruebaConsultarIdPropuestaDeColaboracionPorIdProfesorExitosa(){
        DAOPeticionColaboracionImplementacion instancia = new DAOPeticionColaboracionImplementacion();
        int resultadoEsperado = 1;
        int resultadoObtenido = instancia.consultarIdPropuestaDeColaboracionPorIdProfesor(3);
        assertEquals(resultadoEsperado, resultadoObtenido);
    }
    
    @Test 
    public void pruebaConsultarIdPropuestaDeColaboracionPorIdProfesorFallida(){
        DAOPeticionColaboracionImplementacion instancia = new DAOPeticionColaboracionImplementacion();
        int resultadoEsperado = 0;
        int resultadoObtenido = instancia.consultarIdPropuestaDeColaboracionPorIdProfesor(-1);
        assertEquals(resultadoEsperado, resultadoObtenido);
    }
    
    @Test 
    public void pruebaConsultarIdPropuestaDeColaboracionPorIdProfesorSinConexionExitosa(){
        DAOPeticionColaboracionImplementacion instancia = new DAOPeticionColaboracionImplementacion();
        int resultadoObtenido = instancia.consultarIdPropuestaDeColaboracionPorIdProfesor(3);
        assertEquals(-1, resultadoObtenido);
    }
    
    @Test
    public void pruebaConsultarIdProfesoresPorIdPropuestaColaboracionExitosa(){
        int idPropuestaColaboracion = 1;
        List<Integer> resultadoEsperado = new ArrayList<>();
        int idProfesorEsperado = 3;
        resultadoEsperado.add(idProfesorEsperado);
        DAOPeticionColaboracionImplementacion instancia = new DAOPeticionColaboracionImplementacion();
        List<Integer> resultadoObtenido = instancia.consultarIdProfesoresPorIdPropuestaColaboracion(idPropuestaColaboracion);
        assertEquals(resultadoEsperado, resultadoObtenido);          
    }
    
    @Test 
    public void pruebaConsultarIdProfesoresPorIdPropuestaColaboracionFallida(){
        int idPropuestaColaboracion = -1;
        DAOPeticionColaboracionImplementacion instancia = new DAOPeticionColaboracionImplementacion();
        List<Integer> resultadoObtenido = instancia.consultarIdProfesoresPorIdPropuestaColaboracion(idPropuestaColaboracion);
        assertTrue(resultadoObtenido.isEmpty());
    }
    
    @Test 
    public void pruebaConsultarIdProfesoresPorIdPropuestaColaboracionSincConexionExitosa(){
        int idPropuestaColaboracion = 1;
        DAOPeticionColaboracionImplementacion instancia = new DAOPeticionColaboracionImplementacion();
        List<Integer> resultadoObtenido = instancia.consultarIdProfesoresPorIdPropuestaColaboracion(idPropuestaColaboracion);
        assertTrue(resultadoObtenido.isEmpty());
    }
    
    @Test
    public void pruebaAceptarPeticionColaboracionExitosa(){
        int idPropuestaColaboracion=1;
        int idProfesor=1;
        int resultadoEsperado=1;
        DAOPeticionColaboracionImplementacion daoPeticionColaboracion=new DAOPeticionColaboracionImplementacion();
        int resultadoObtenido=daoPeticionColaboracion.aceptarPeticionColaboracion(idPropuestaColaboracion, idProfesor);
        assertEquals(resultadoEsperado, resultadoObtenido);        
    }   
    
    @Test 
    public void pruebaAceptarPeticionColaboracionFallida(){
        int idPropuestaColaboracion = -1;
        int idProfesor = -1;
        int resultadoEsperado = 0;
        DAOPeticionColaboracionImplementacion daoPeticionColaboracion = new DAOPeticionColaboracionImplementacion();
        int resultadoObtenido = daoPeticionColaboracion.aceptarPeticionColaboracion(idPropuestaColaboracion, idProfesor);
        assertEquals(resultadoEsperado, resultadoObtenido);        
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
    public void pruebaRechazarPeticionColaboracionExitosa(){
        int idPropuestaColaboracion=1;
        int idProfesor=1;
        int resultadoEsperado=1;
        DAOPeticionColaboracionImplementacion daoPeticionColaboracion=new DAOPeticionColaboracionImplementacion();
        int resultadoObtenido=daoPeticionColaboracion.rechazarPeticionColaboracion(idPropuestaColaboracion, idProfesor);
        assertEquals(resultadoEsperado, resultadoObtenido);         
    }
    
    @Test
    public void pruebaRechazarPeticionColaboracionFallida(){
        int idPropuestaColaboracion = -1;
        int idProfesor = -1;
        int resultadoEsperado = 0;
        DAOPeticionColaboracionImplementacion daoPeticionColaboracion = new DAOPeticionColaboracionImplementacion();
        int resultadoObtenido = daoPeticionColaboracion.rechazarPeticionColaboracion(idPropuestaColaboracion, idProfesor);
        assertEquals(resultadoEsperado, resultadoObtenido);         
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
    public void consultarIdProfesoresPorIdPropuestaColaboracionAceptadasExitosa(){
        int idPropuestaColaboracion = 1;
        DAOPeticionColaboracionImplementacion instancia = new DAOPeticionColaboracionImplementacion();
        List<Integer> resultadoObtenido = instancia.consultarIdProfesoresPorIdPropuestaColaboracionAceptadas(idPropuestaColaboracion);
        assert(!resultadoObtenido.isEmpty()); //falla
    }
    
    @Test
    public void consultarIdProfesoresPorIdPropuestaColaboracionAceptadasFallida(){
        int idPropuestaColaboracion = -1;
        DAOPeticionColaboracionImplementacion instancia = new DAOPeticionColaboracionImplementacion();
        List<Integer> resultadoObtenido = instancia.consultarIdProfesoresPorIdPropuestaColaboracionAceptadas(idPropuestaColaboracion);
        assertTrue(resultadoObtenido.isEmpty());
    }
    
    @Test
    public void consultarIdProfesoresPorIdPropuestaColaboracionAceptadasSinConexionExitosa(){
        int idPropuestaColaboracion = 1;
        DAOPeticionColaboracionImplementacion instancia = new DAOPeticionColaboracionImplementacion();
        List<Integer> resultadoObtenido = instancia.consultarIdProfesoresPorIdPropuestaColaboracionAceptadas(idPropuestaColaboracion);
        assertTrue(resultadoObtenido.isEmpty());
    }
    
    @Test
    public void revisarPrecondicionEvaluarPeticionesPorIdProfesorExitosa() {
        int idProfesor = 3;
        DAOPeticionColaboracionImplementacion instancia = new DAOPeticionColaboracionImplementacion();
        int resultadoObtenido = instancia.revisarPrecondicionEvaluarPeticionesPorIdProfesor(idProfesor);
        assertEquals(1, resultadoObtenido);
    }

    
    @Test
    public void revisarPrecondicionEvaluarPeticionesPorIdProfesorFallida() {
        int idProfesor = -1;
        DAOPeticionColaboracionImplementacion instancia = new DAOPeticionColaboracionImplementacion();
        int resultadoObtenido = instancia.revisarPrecondicionEvaluarPeticionesPorIdProfesor(idProfesor);
        assertEquals(0, resultadoObtenido);
    }
    
    @Test
    public void revisarPrecondicionEvaluarPeticionesPorIdProfesorSinConexionExitosa() {
        int idProfesor = 1;
        DAOPeticionColaboracionImplementacion instancia = new DAOPeticionColaboracionImplementacion();
        int resultadoObtenido = instancia.revisarPrecondicionEvaluarPeticionesPorIdProfesor(idProfesor);
        assertEquals(-1, resultadoObtenido);
    }
    
    @Test
    public void cambiarEstadoPeticionesRegistradasPorIdPropuestaExitosa() {
        int idPropuesta = 1;
        DAOPeticionColaboracionImplementacion instancia = new DAOPeticionColaboracionImplementacion();
        int resultadoObtenido = instancia.cambiarEstadoPeticionesRegistradasPorIdPropuesta(idPropuesta);
        assertEquals(1, resultadoObtenido);
    }

    
    @Test
    public void cambiarEstadoPeticionesRegistradasPorIdPropuestaFallida() {
        int idPropuesta = -1;
        DAOPeticionColaboracionImplementacion instancia = new DAOPeticionColaboracionImplementacion();
        int resultadoObtenido = instancia.cambiarEstadoPeticionesRegistradasPorIdPropuesta(idPropuesta);
        assertEquals(0, resultadoObtenido);
    }
    
    @Test
    public void cambiarEstadoPeticionesRegistradasPorIdPropuestaSinConexionExitosa() {
        int idPropuesta = 1;
        DAOPeticionColaboracionImplementacion instancia = new DAOPeticionColaboracionImplementacion();
        int resultadoObtenido = instancia.cambiarEstadoPeticionesRegistradasPorIdPropuesta(idPropuesta);
        assertEquals(-1, resultadoObtenido);
    }
    
}
