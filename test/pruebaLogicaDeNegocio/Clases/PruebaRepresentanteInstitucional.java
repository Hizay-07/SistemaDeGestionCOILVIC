package pruebaLogicaDeNegocio.Clases;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import logicaDeNegocio.clases.Pais;
import logicaDeNegocio.clases.RepresentanteInstitucional;
import org.junit.Test;

public class PruebaRepresentanteInstitucional {

    @Test
    public void pruebaSetNombreInstitucionExitoso(){
        RepresentanteInstitucional representantePrueba = new RepresentanteInstitucional();
        representantePrueba.setNombreInstitucion("Ruprecht-Karls-Universität Heidelberg"); 
        assertNotNull(representantePrueba.getNombreInstitucion());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaSetNombreInstitucionInvalido(){
        RepresentanteInstitucional representantePrueba = new RepresentanteInstitucional();
        representantePrueba.setNombreInstitucion("-1Unam*"); 
        assertNotNull(representantePrueba.getNombreInstitucion()); 
    }
    
    @Test
    public void pruebaSetClaveInstitucionalExitoso(){
        RepresentanteInstitucional representantePrueba = new RepresentanteInstitucional();
        representantePrueba.setClaveInstitucional("CHPON123"); 
        assertNotNull(representantePrueba.getClaveInstitucional());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaSetClaveInstitucionalInvalido(){
        RepresentanteInstitucional representantePrueba = new RepresentanteInstitucional();
        representantePrueba.setClaveInstitucional("Clave$123"); 
        assertNull(representantePrueba.getClaveInstitucional()); 
    }
    
    @Test
    public void pruebaSetContactoExitoso(){
        RepresentanteInstitucional representantePrueba = new RepresentanteInstitucional();
        representantePrueba.setContacto("correo@ejemplo.com");
        assertNotNull(representantePrueba.getContacto());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaSetContactoInvalido(){
        RepresentanteInstitucional representantePrueba = new RepresentanteInstitucional();
        representantePrueba.setContacto("****correo@ejemplo.cóm"); 
        assertNotNull(representantePrueba.getContacto()); 
    }
    
    @Test
    public void pruebaSetPaisExitoso(){
        RepresentanteInstitucional representantePrueba = new RepresentanteInstitucional();
        Pais pais = new Pais();
        pais.setNombrePais("Portugal"); 
        representantePrueba.setPais(pais);
        assertNotNull(representantePrueba.getPais());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaSetPaisInvalido(){
        RepresentanteInstitucional representantePrueba = new RepresentanteInstitucional();
        Pais pais = new Pais();
        pais.setNombrePais("Portugal1"); 
        representantePrueba.setPais(pais);
        assertNotNull(representantePrueba.getPais()); 
    }
    
}
