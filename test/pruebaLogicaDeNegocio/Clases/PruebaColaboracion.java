package pruebaLogicaDeNegocio.Clases;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import logicaDeNegocio.clases.Colaboracion;
import org.junit.Test;

public class PruebaColaboracion {

    @Test
    public void pruebaSetIdColaboracionExitosa(){
        Colaboracion colaboracionPrueba = new Colaboracion();
        colaboracionPrueba.setIdColaboracion(1);
        assertNotNull(colaboracionPrueba.getIdColaboracion());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaSetIdColaboracionInvalido(){
        Colaboracion colaboracionPrueba = new Colaboracion();
        colaboracionPrueba.setIdColaboracion(-1);
        assertNotNull(colaboracionPrueba.getIdColaboracion());
    }       
    
    @Test
    public void pruebaSetEstadoColaboracionExitosa(){
        Colaboracion colaboracionPrueba = new Colaboracion();
        colaboracionPrueba.setEstadoColaboracion("En progreso");
        assertNotNull(colaboracionPrueba.getEstadoColaboracion());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaSetEstadoColaboracionInvalida(){
        Colaboracion colaboracionPrueba = new Colaboracion();
        colaboracionPrueba.setEstadoColaboracion("*Estado*");
        assertNull(colaboracionPrueba.getEstadoColaboracion());
    }
    
    @Test
    public void pruebaSetRetroalimentacionColaboracionExitosa(){
        Colaboracion colaboracionPrueba = new Colaboracion();
        colaboracionPrueba.setRetroalimentacion("La colaboracion es muy buena, de las mejores que he visto");
        assertNotNull(colaboracionPrueba.getRetroalimentacion());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaSetRetroalimentacionColaboracionInvalida(){
        Colaboracion colaboracionPrueba = new Colaboracion();
        colaboracionPrueba.setRetroalimentacion("'12¿40op2kl4´p1p.;ñ+}{");
        assertNull(colaboracionPrueba.getRetroalimentacion());
    }
    
    @Test
    public void pruebaSetSyllabusExitosa(){
        Colaboracion colaboracionPrueba = new Colaboracion();
        colaboracionPrueba.setSyllabus("\\Colaboraciones\\Colaboracion1\\Syllabus");
        assertNotNull(colaboracionPrueba.getSyllabus());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaSetSyllabusInvalida(){
        Colaboracion colaboracionPrueba = new Colaboracion();
        colaboracionPrueba.setSyllabus("\\Colaboraciones.aaaa¡'?--Colaboracion1\\Syllabus");
        assertNull(colaboracionPrueba.getSyllabus());
    }
    
}
