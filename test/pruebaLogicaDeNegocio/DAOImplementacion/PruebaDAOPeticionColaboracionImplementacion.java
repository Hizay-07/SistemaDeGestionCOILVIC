package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOPeticionColaboracionImplementacion;
import logicaDeNegocio.clases.PeticionColaboracion;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;
import logicaDeNegocio.enums.EnumPeticionColaboracion;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;

public class PruebaDAOPeticionColaboracionImplementacion {
    
    @BeforeClass
    public static void inicializar() {
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentapruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        UsuarioSingleton.getInstancia(usuarioPrueba);
        PeticionColaboracion peticion = new PeticionColaboracion();
        peticion.setIdProfesor(2); 
        peticion.setIdPropuestaColaboracion(1);
        peticion.setEstado(EnumPeticionColaboracion.Registrada.toString());
        peticion.setFechaEnvio("2024-06-08");
        DAOPeticionColaboracionImplementacion instancia = new DAOPeticionColaboracionImplementacion();        
        instancia.registrarPeticionColaboracion(peticion);
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
    public void pruebaRegistrarPeticionColaboracionFallida(){
        PeticionColaboracion peticion = new PeticionColaboracion();        
        DAOPeticionColaboracionImplementacion instancia = new DAOPeticionColaboracionImplementacion();
        int resultadoEsperado = 0; 
        int resultadoObtenido = instancia.registrarPeticionColaboracion(peticion);
        assertEquals(resultadoEsperado, resultadoObtenido);
    }
                            
    @Test
    public void pruebaConsultarPeticionesExitosa() {
        DAOPeticionColaboracionImplementacion instancia = new DAOPeticionColaboracionImplementacion();
        List<PeticionColaboracion> peticiones = instancia.consultarPeticiones();
        assertEquals(false, peticiones.isEmpty());
    }
    
    @Test (expected = AssertionError.class)
    public void pruebaConsultarPeticionesFallida() {
        DAOPeticionColaboracionImplementacion instancia = new DAOPeticionColaboracionImplementacion();
        List<PeticionColaboracion> peticiones = instancia.consultarPeticiones();
        assertEquals(true, peticiones.isEmpty());
    }
        
    @Test 
    public void pruebaConsultarIdPropuestaDeColaboracionPorIdProfesorExitosa(){
        DAOPeticionColaboracionImplementacion instancia = new DAOPeticionColaboracionImplementacion();
        int resultadoEsperado = 1;
        int resultadoObtenido = instancia.consultarIdPropuestaDeColaboracionPorIdProfesor(2);
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
    public void pruebaAceptarPeticionColaboracionExitosa(){
        int idPropuestaColaboracion=1;
        int idProfesor=2;
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
    public void consultarIdProfesoresPorIdPropuestaColaboracionAceptadasExitosa(){
        int idPropuestaColaboracion = 1;
        DAOPeticionColaboracionImplementacion instancia = new DAOPeticionColaboracionImplementacion();
        List<Integer> resultadoObtenido = instancia.consultarIdProfesoresPorIdPropuestaColaboracionAceptadas(idPropuestaColaboracion);
        assertFalse(resultadoObtenido.isEmpty()); 
    }
    
    @Test
    public void consultarIdProfesoresPorIdPropuestaColaboracionAceptadasFallida(){
        int idPropuestaColaboracion = -1;
        DAOPeticionColaboracionImplementacion instancia = new DAOPeticionColaboracionImplementacion();
        List<Integer> resultadoObtenido = instancia.consultarIdProfesoresPorIdPropuestaColaboracionAceptadas(idPropuestaColaboracion);
        assertTrue(resultadoObtenido.isEmpty());
    } 
        
    @Test
    public void pruebaRechazarPeticionColaboracionExitosa(){
        int idPropuestaColaboracion=1;
        int idProfesor=2;
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
    public void revisarPrecondicionEvaluarPeticionesPorIdProfesorExitosa() {
        int idProfesor = 1;
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
}
