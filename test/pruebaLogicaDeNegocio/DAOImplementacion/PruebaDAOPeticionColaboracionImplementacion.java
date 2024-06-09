package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOPeticionColaboracionImplementacion;
import logicaDeNegocio.clases.PeticionColaboracion;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;
import logicaDeNegocio.enums.EnumPeticionColaboracion;
import static org.junit.Assert.assertEquals;
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
        peticion.setIdProfesor(2); 
        peticion.setIdPropuestaColaboracion(1);
        peticion.setEstado(EnumPeticionColaboracion.Registrada.toString());
        peticion.setFechaEnvio("2024-04-08");
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
    public void pruebaConsultarIdProfesoresPorIdPropuestaColaboracionExitosa(){
        int idPropuestaColaboracion=2;
        List<Integer> resultadoEsperado=new ArrayList<>();
        int idProfesorEsperado=2;
        resultadoEsperado.add(idProfesorEsperado);
        DAOPeticionColaboracionImplementacion instancia = new DAOPeticionColaboracionImplementacion();
        List<Integer> resultadoObtenido=new ArrayList<>();
        resultadoObtenido=instancia.consultarIdProfesoresPorIdPropuestaColaboracion(idPropuestaColaboracion);
        assertEquals(resultadoEsperado, resultadoObtenido);        
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
    public void pruebaRechazarPeticionColaboracionExitosa(){
        int idPropuestaColaboracion=1;
        int idProfesor=1;
        int resultadoEsperado=1;
        DAOPeticionColaboracionImplementacion daoPeticionColaboracion=new DAOPeticionColaboracionImplementacion();
        int resultadoObtenido=daoPeticionColaboracion.rechazarPeticionColaboracion(idPropuestaColaboracion, idProfesor);
        assertEquals(resultadoEsperado, resultadoObtenido);         
    }
    
}
