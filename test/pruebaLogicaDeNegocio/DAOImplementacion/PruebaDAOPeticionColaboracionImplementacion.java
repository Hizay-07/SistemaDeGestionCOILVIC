package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOPeticionColaboracionImplementacion;
import logicaDeNegocio.clases.PeticionColaboracion;
import logicaDeNegocio.enums.EnumEstados;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PruebaDAOPeticionColaboracionImplementacion {
    
    @Test
    public void pruebaRegistrarPeticionColaboracionExitosa(){
        PeticionColaboracion peticion = new PeticionColaboracion();
        peticion.setIdProfesor(2); 
        peticion.setIdPropuestaColaboracion(1);
        peticion.setEstado(EnumEstados.Activo.toString());
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
    
    @Test
    public void pruebaConsultarPeticionesFracaso() {
        DAOPeticionColaboracionImplementacion instancia = new DAOPeticionColaboracionImplementacion();
        List<PeticionColaboracion> peticiones = instancia.consultarPeticiones();
        assertEquals(true, peticiones.isEmpty());
    }
    
    @Test
    public void pruebaAceptarColaboracionExitosa() {
        DAOPeticionColaboracionImplementacion instancia = new DAOPeticionColaboracionImplementacion();
        int idColaboracion = 1; 
        String nuevoEstado = EnumEstados.Aceptado.toString();
        int resultado = instancia.aceptarColaboracion(idColaboracion, nuevoEstado);
        assertEquals(1, resultado); 
    }
    
    @Test
    public void pruebaAceptarColaboracionFracaso() {
        DAOPeticionColaboracionImplementacion instancia = new DAOPeticionColaboracionImplementacion();
        int idColaboracion = 2; 
        String nuevoEstado = EnumEstados.Aceptado.toString();
        int resultado = instancia.aceptarColaboracion(idColaboracion, nuevoEstado);
        assertEquals(0, resultado); 
    }
 

    @Test
    public void pruebaRechazarColaboracionExitosa() {
        DAOPeticionColaboracionImplementacion instancia = new DAOPeticionColaboracionImplementacion();
        int idColaboracion = 2; 
        String nuevoEstado = EnumEstados.Rechazado.toString();
        int resultado = instancia.rechazarColaboracion(idColaboracion, nuevoEstado);
        assertEquals(1, resultado); 
    }
    
    @Test
    public void pruebaRechazarColaboracionFracaso() {
        DAOPeticionColaboracionImplementacion instancia = new DAOPeticionColaboracionImplementacion();
        int idColaboracion = 2;
        String nuevoEstado = EnumEstados.Rechazado.toString();
        int resultado = instancia.rechazarColaboracion(idColaboracion, nuevoEstado);
        assertEquals(0, resultado);
    }
    
    
    
    
    
    
    
    

    
    

}
