package pruebaAccesoADatos.Clases;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import logicaDeNegocio.clases.Evidencia;
import org.junit.Test;

public class PruebaEvidencia {

    @Test
    public void pruebaSetIdEvidenciaExitosa(){
        Evidencia evidenciaPrueba = new Evidencia();
        evidenciaPrueba.setIdEvidencia(1);
        assertNotNull(evidenciaPrueba.getIdEvidencia());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaSetIdEvidenciaInvalida(){
        Evidencia evidenciaPrueba = new Evidencia();
        evidenciaPrueba.setIdEvidencia(-1);
        assertNotNull(evidenciaPrueba.getIdEvidencia());
    }
    
    @Test
    public void pruebaSetNombreExitosa(){
        Evidencia evidenciaPrueba = new Evidencia();
        evidenciaPrueba.setNombre("Evidencia1_PruebaDao");
        assertNotNull(evidenciaPrueba.getNombre());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaSetNombreInvalida(){
        Evidencia evidenciaPrueba = new Evidencia();
        evidenciaPrueba.setNombre("¿Evidencia1_PruebaDao?");
        assertNull(evidenciaPrueba.getNombre());
    }
    
    @Test
    public void pruebaSetRutaEvidenciaExitosa(){
        Evidencia evidenciaPrueba = new Evidencia();
        evidenciaPrueba.setRutaEvidencia("Colaboraciones/Colaboracion1/Actividad1/ModeloDeEstandarizacion.docx");
        assertNotNull(evidenciaPrueba.getRutaEvidencia());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaSetRutaEvidenciaInvalida(){
        Evidencia evidenciaPrueba = new Evidencia();
        evidenciaPrueba.setRutaEvidencia(null);
        assertNotNull(evidenciaPrueba.getRutaEvidencia());
    }
    
    @Test
    public void pruebaSetIdActividadExitosa(){
        Evidencia evidenciaPrueba = new Evidencia();
        evidenciaPrueba.setIdActividad(1);
        assertNotNull(evidenciaPrueba.getIdActividad());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaSetIdActividadInvalida(){
        Evidencia evidenciaPrueba = new Evidencia();
        evidenciaPrueba.setIdActividad(-1);
        assertNotNull(evidenciaPrueba.getIdActividad());
    }
}
