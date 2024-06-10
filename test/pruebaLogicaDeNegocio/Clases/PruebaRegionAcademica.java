package pruebaLogicaDeNegocio.Clases;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import logicaDeNegocio.clases.RegionAcademica;
import org.junit.Test;

public class PruebaRegionAcademica {

    @Test
    public void pruebaSetIdRegionAcademicaExitoso(){
        RegionAcademica regionPrueba = new RegionAcademica();
        regionPrueba.setIdRegionAcademica(1);
        assertNotNull(regionPrueba.getIdRegionAcademica());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaSetIdRegionAcademicaInvalido(){
        RegionAcademica regionPrueba = new RegionAcademica();
        regionPrueba.setIdRegionAcademica(-1); 
        assertNotNull(regionPrueba.getIdRegionAcademica());
    }
    
    @Test
    public void pruebaSetRegionExitoso(){
        RegionAcademica regionPrueba = new RegionAcademica();
        regionPrueba.setRegion("Veracruz"); 
        assertNotNull(regionPrueba.getRegion());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaSetRegionInvalido(){
        RegionAcademica regionPrueba = new RegionAcademica();
        regionPrueba.setRegion("Xalapa 2000");
        assertNull(regionPrueba.getRegion());
    }
    
    
}
