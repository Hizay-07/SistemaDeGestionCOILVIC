package pruebaAccesoADatos.Clases;

import static org.junit.Assert.*;
import logicaDeNegocio.clases.ProfesorUV;
import org.junit.Test;

public class PruebaProfesorUV {
    
    @Test
    public void pruebaSetNumeroDePersonalProfesorExitosa() {
        ProfesorUV profesorPrueba = new ProfesorUV();
        profesorPrueba.setNumeroDePersonal("9999");
        assertNotNull(profesorPrueba.getNumeroDePersonal());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaSetNumeroDePersonalInvalidoProfesor() {
        ProfesorUV profesorPrueba = new ProfesorUV();
        profesorPrueba.setNumeroDePersonal("10000000");
        assertNotNull(profesorPrueba.getNumeroDePersonal());
    }
    
    @Test
    public void pruebaSetTipoDeContratacionProfesorExitosa() {
        ProfesorUV profesorPrueba = new ProfesorUV();
        profesorPrueba.setTipoDeContratacion("Tiempo Completo");
        assertNotNull(profesorPrueba.getTipoDeContratacion());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaSetTipoDeContratacionInvalidoProfesor() {
        ProfesorUV profesorPrueba = new ProfesorUV();
        profesorPrueba.setTipoDeContratacion("1/2 tiempo");
        assertNull(profesorPrueba.getTipoDeContratacion());
    }
    
    @Test
    public void pruebaSetCategoriaDeContratacionProfesorExitosa() {
        ProfesorUV profesorPrueba = new ProfesorUV();
        profesorPrueba.setCategoriaDeContratacion("Profesor");
        assertNotNull(profesorPrueba.getCategoriaDeContratacion());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaSetCategoriaDeContratacionInvalidoProfesor() {
        ProfesorUV profesorPrueba = new ProfesorUV();
        profesorPrueba.setCategoriaDeContratacion("Profesor & Coordinador");
        assertNotNull(profesorPrueba.getCategoriaDeContratacion());
    }
    
    @Test
    public void pruebaSetIdAreaAcademicaProfesorExitosa() {
        ProfesorUV profesorPrueba = new ProfesorUV();
        profesorPrueba.setIdAreaAcademica(1);
        assertNotNull(profesorPrueba.getIdAreaAcademica());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaSetIdAreaAcademicaInvalidoProfesor() {
        ProfesorUV profesorPrueba = new ProfesorUV();
        profesorPrueba.setIdAreaAcademica(-1);
        assertNull(profesorPrueba.getIdAreaAcademica());
    }
    
    @Test
    public void pruebaSetIdRegionProfesorExitosa() {
        ProfesorUV profesorPrueba = new ProfesorUV();
        profesorPrueba.setIdRegion(1);
        assertNotNull(profesorPrueba.getIdRegion());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaSetIdRegionInvalidoProfesor() {
        ProfesorUV profesorPrueba = new ProfesorUV();
        profesorPrueba.setIdRegion(-1);
        assertNull(profesorPrueba.getIdRegion());
    }
    
    @Test
    public void pruebaSetIdProfesorUVExitosa() {
        ProfesorUV profesorPrueba = new ProfesorUV();
        profesorPrueba.setIdProfesorUV(1);
        assertNotNull(profesorPrueba.getIdProfesorUV());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaSetIdProfesorUVInvalido() {
        ProfesorUV profesorPrueba = new ProfesorUV();
        profesorPrueba.setIdProfesorUV(-1);
        assertNotNull(profesorPrueba.getIdProfesorUV());
    }
}
