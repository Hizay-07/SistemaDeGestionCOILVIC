package pruebaLogicaDeNegocio.Clases;

import logicaDeNegocio.clases.AreaAcademica;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class PruebaAreaAcademica {
    @Test
    public void pruebaSetIdAreaAcademicaExitosa(){
        AreaAcademica AreaAcademicaPrueba = new AreaAcademica();
        AreaAcademicaPrueba.setIdAreaAcademica(22);
        assertNotNull(AreaAcademicaPrueba.getIdAreaAcademica());
    }
        
    @Test (expected = IllegalArgumentException.class)
    public void pruebaSetIdAreaAcademicaInvalida(){
        AreaAcademica AreaAcademicaPrueba = new AreaAcademica();
        AreaAcademicaPrueba.setIdAreaAcademica(-1);
        assertNull(AreaAcademicaPrueba.getIdAreaAcademica());
    }
    
    
    @Test
    public void pruebaSetAreaAcademicaExitosa(){
        AreaAcademica AreaAcademicaPrueba = new AreaAcademica();
        AreaAcademicaPrueba.setArea("Ciencias de la Salud");
        assertNotNull(AreaAcademicaPrueba.getArea());
    }
    
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaSetAreaAcademicaInvalida(){
        AreaAcademica AreaAcademicaPrueba = new AreaAcademica();
        AreaAcademicaPrueba.setArea("Humam¿nidades22");
        assertNull(AreaAcademicaPrueba.getArea());
    }
        
}
