package pruebaAccesoADatos.Clases;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import logicaDeNegocio.clases.Pais;
import logicaDeNegocio.clases.RepresentanteInstitucional;
import org.junit.Test;

public class PruebaRepresentanteInstitucional {

    @Test
    public void pruebaSetNombreInstitucionExitoso(){
        RepresentanteInstitucional representantePrueba = new RepresentanteInstitucional();
        representantePrueba.setNombreInstitucion("Nombre Institución"); 
        assertNotNull(representantePrueba.getNombreInstitucion());
    }
    
    @Test
    public void pruebaSetNombreInstitucionInvalido(){
        RepresentanteInstitucional representantePrueba = new RepresentanteInstitucional();
        representantePrueba.setNombreInstitucion("UNAM*"); 
        assertNull(representantePrueba.getNombreInstitucion()); 
    }
    
    @Test
    public void pruebaSetClaveInstitucionalExitoso(){
        RepresentanteInstitucional representantePrueba = new RepresentanteInstitucional();
        representantePrueba.setClaveInstitucional("Clave"); 
        assertNotNull(representantePrueba.getClaveInstitucional());
    }
    
    @Test
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
    
    @Test
    public void pruebaSetContactoInvalido(){
        RepresentanteInstitucional representantePrueba = new RepresentanteInstitucional();
        representantePrueba.setContacto("correo@ejemplo.cóm"); 
        assertNotNull(representantePrueba.getContacto()); 
    }
   
    @Test
    public void pruebaSetEstadoRepresentanteExitoso(){
        RepresentanteInstitucional representantePrueba = new RepresentanteInstitucional();
        representantePrueba.setEstadoRepresentante("Activo"); 
        assertNotNull(representantePrueba.getEstadoRepresentante());
    }
    
    @Test
    public void pruebaSetEstadoRepresentanteInvalido(){
        RepresentanteInstitucional representantePrueba = new RepresentanteInstitucional();
        representantePrueba.setEstadoRepresentante("12345"); 
        assertNotNull(representantePrueba.getEstadoRepresentante()); 
    }
    
    @Test
    public void pruebaSetPaisExitoso(){
        RepresentanteInstitucional representantePrueba = new RepresentanteInstitucional();
        Pais pais = new Pais();
        pais.setNombrePais("Portugal"); 
        representantePrueba.setPais(pais);
        assertNotNull(representantePrueba.getPais());
    }
    
    @Test
    public void pruebaSetPaisInvalido(){
        RepresentanteInstitucional representantePrueba = new RepresentanteInstitucional();
        Pais pais = new Pais();
        pais.setNombrePais("Portugal1"); 
        representantePrueba.setPais(pais);
        assertNotNull(representantePrueba.getPais()); 
    }
    
}
