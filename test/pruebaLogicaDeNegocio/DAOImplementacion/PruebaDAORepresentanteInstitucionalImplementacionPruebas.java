package pruebaLogicaDeNegocio.DAOImplementacion;

import logicaDeNegocio.clases.RepresentanteInstitucional;
import logicaDeNegocio.clases.Pais;
import logicaDeNegocio.DAOImplementacion.DAORepresentanteInstitucionalImplementacion;
import logicaDeNegocio.enums.EnumPais;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class PruebaDAORepresentanteInstitucionalImplementacionPruebas {
    
    @Test
    public void pruebaRegistrarRepresentanteInstitucionalExitosa(){
        DAORepresentanteInstitucionalImplementacion pruebaRegistro = new DAORepresentanteInstitucionalImplementacion();
        
        RepresentanteInstitucional representanteDePrueba = new RepresentanteInstitucional();
        Pais pruebaPais = new Pais();
        pruebaPais.setNombrePais(EnumPais.Mexico.toString());
        representanteDePrueba.setNombreInstitucion("UV");
        representanteDePrueba.setClaveInstitucional("1234");
        representanteDePrueba.setContacto("2281709292");
        representanteDePrueba.setPais(pruebaPais);
        
        int resultadoDePrueba =pruebaRegistro.registrarRepresentanteInstitucional(representanteDePrueba);
        System.out.println(resultadoDePrueba);
        assertEquals(1,resultadoDePrueba);
    }
    
    @Test
    public void pruebaFlujoFallidoRegistrarRepresentanteInstitucionalExitosa(){
        DAORepresentanteInstitucionalImplementacion pruebaRegistro = new DAORepresentanteInstitucionalImplementacion();
        
        RepresentanteInstitucional representanteDePrueba = new RepresentanteInstitucional();
        Pais pruebaPais = new Pais();
        pruebaPais.setNombrePais(EnumPais.Mexico.toString());
        representanteDePrueba.setContacto("2281709292");
        representanteDePrueba.setPais(pruebaPais);
        
        int resultadoDePrueba =pruebaRegistro.registrarRepresentanteInstitucional(representanteDePrueba);
        System.out.println(resultadoDePrueba);
        assertEquals(-1,resultadoDePrueba);
    }
    
 
    
   
    
 
    
    @Test
    public void pruebaModificarNombreRepresentanteInstitucionalExitosa(){
        DAORepresentanteInstitucionalImplementacion pruebaModificacion = new DAORepresentanteInstitucionalImplementacion();
        
        RepresentanteInstitucional representanteDePrueba = new RepresentanteInstitucional();
        Pais pruebaPais = new Pais();
        pruebaPais.setNombrePais(EnumPais.Mexico.toString());
        representanteDePrueba.setNombreInstitucion("UV");
        representanteDePrueba.setClaveInstitucional("1234");
        representanteDePrueba.setContacto("2281709292");
        representanteDePrueba.setPais(pruebaPais);
        String nombreActualizado = "UNAM";
        
        int resultadoDePrueba = pruebaModificacion.modificarNombreRepresentanteInstitucional(nombreActualizado, representanteDePrueba);
        assertEquals(1,resultadoDePrueba);
    }
    
    @Test
    public void pruebaModificarClaveRepresentanteInstitucionalExitosa(){
        DAORepresentanteInstitucionalImplementacion pruebaModificacion = new DAORepresentanteInstitucionalImplementacion();
        
        RepresentanteInstitucional representanteDePrueba = new RepresentanteInstitucional();
        Pais pruebaPais = new Pais();
        pruebaPais.setNombrePais(EnumPais.Mexico.toString());
        representanteDePrueba.setNombreInstitucion("UNAM");
        representanteDePrueba.setClaveInstitucional("1234");
        representanteDePrueba.setContacto("2281709292");
        representanteDePrueba.setPais(pruebaPais);
        String claveActualizada = "5678";
        
        int resultadoDePrueba = pruebaModificacion.modificarClaveRepresentanteInstitucional(claveActualizada, representanteDePrueba);
        assertEquals(1,resultadoDePrueba);
    }
    
    @Test
    public void pruebaModificarContactoRepresentanteInstitucionalExitosa(){
        DAORepresentanteInstitucionalImplementacion pruebaModificacion = new DAORepresentanteInstitucionalImplementacion();
        
        RepresentanteInstitucional representanteDePrueba = new RepresentanteInstitucional();
        Pais pruebaPais = new Pais();
        pruebaPais.setNombrePais(EnumPais.Mexico.toString());
        representanteDePrueba.setNombreInstitucion("UNAM");
        representanteDePrueba.setClaveInstitucional("5678");
        representanteDePrueba.setContacto("2281709292");
        representanteDePrueba.setPais(pruebaPais);
        String contactoActualizado = "2281024672";
        
        int resultadoDePrueba = pruebaModificacion.modificarContactoRepresentanteInstitucional(contactoActualizado, representanteDePrueba);
        assertEquals(1,resultadoDePrueba);
    }
    
    @Test
    public void pruebaObtenerEstadoRepresentanteInstitucionalEitosa(){
        DAORepresentanteInstitucionalImplementacion pruebaConsulta = new DAORepresentanteInstitucionalImplementacion();
        
        RepresentanteInstitucional representanteDePrueba = new RepresentanteInstitucional();
        representanteDePrueba.setNombreInstitucion("uv");
        representanteDePrueba.setClaveInstitucional("1234");
        representanteDePrueba.setContacto("2281709292");
        
        boolean resultadoDePrueba = pruebaConsulta.obtenerEstadoRepresentanteInstitucional(representanteDePrueba);
        assertTrue(resultadoDePrueba);
    }
    
    @Test
    public void pruebaObtenerRepresentantesInstitucionalesExitosa(){
        
        List<RepresentanteInstitucional> representantesObtenidos = new ArrayList();
        List<RepresentanteInstitucional> representantesEsperados = new ArrayList();
        
        RepresentanteInstitucional representante1 = new RepresentanteInstitucional();
        RepresentanteInstitucional representante2 = new RepresentanteInstitucional();
        RepresentanteInstitucional representante3 = new RepresentanteInstitucional();
        
        Pais pais1 = new Pais();
        Pais pais2 = new Pais();
        
        pais2.setNombrePais(EnumPais.Colombia.toString());
        pais1.setNombrePais(EnumPais.Mexico.toString());
        
        representante1.setNombreInstitucion("UV");
        representante1.setClaveInstitucional("1234");
        representante1.setContacto("2281709292");
        representante2.setNombreInstitucion("Universidad de Bogota");
        representante2.setClaveInstitucional("9999");
        representante2.setContacto("5267231821");
        representante3.setNombreInstitucion("UNAM");
        representante3.setClaveInstitucional("5678");
        representante3.setContacto("2281024672");
     
        
        representante1.setPais(pais1);
        representante2.setPais(pais2);
        representante3.setPais(pais1);
        
        representantesEsperados.add(representante1);
        representantesEsperados.add(representante2);
        representantesEsperados.add(representante3);
        
        DAORepresentanteInstitucionalImplementacion pruebaConsulta = new DAORepresentanteInstitucionalImplementacion();
        representantesObtenidos = pruebaConsulta.obtenerRepresentantesInstitucionales();
        
        assertEquals(representantesEsperados,representantesObtenidos);
        
    }
    
    @Test
    public void pruebaFlujoFallidoObtenerRepresentantesInstitucionalesExitosa(){
        
        List<RepresentanteInstitucional> representantesObtenidos = new ArrayList();
        List<RepresentanteInstitucional> representantesEsperados = new ArrayList();
        
        RepresentanteInstitucional representante1 = new RepresentanteInstitucional();
        RepresentanteInstitucional representante2 = new RepresentanteInstitucional();
        RepresentanteInstitucional representante3 = new RepresentanteInstitucional();
        
        Pais pais1 = new Pais();
        Pais pais2 = new Pais();
        
        pais2.setNombrePais(EnumPais.Colombia.toString());
        pais1.setNombrePais(EnumPais.Mexico.toString());
        
        representante1.setNombreInstitucion("ANAHUAC");
        representante1.setClaveInstitucional("2456");
        representante1.setContacto("2288024672");
        representante2.setNombreInstitucion("Universidad de Bogota");
        representante2.setClaveInstitucional("9999");
        representante2.setContacto("5267231821");
        representante3.setNombreInstitucion("UNAM");
        representante3.setClaveInstitucional("5678");
        representante3.setContacto("2281024672");
     
        
        representante1.setPais(pais1);
        representante2.setPais(pais2);
        representante3.setPais(pais1);
        
        representantesEsperados.add(representante1);
        representantesEsperados.add(representante2);
        representantesEsperados.add(representante3);
        
        DAORepresentanteInstitucionalImplementacion pruebaConsulta = new DAORepresentanteInstitucionalImplementacion();
        representantesObtenidos = pruebaConsulta.obtenerRepresentantesInstitucionales();
        
        assertNotEquals(representantesEsperados,representantesObtenidos);
        
    }
    
    @Test
    public void pruebaObtenerRepresentantesInstitucionalesPorPaisExitosa(){
        List<RepresentanteInstitucional> representantesObtenidos = new ArrayList();
        List<RepresentanteInstitucional> representantesEsperados = new ArrayList();
        RepresentanteInstitucional representante1 = new RepresentanteInstitucional();
        Pais pais1 = new Pais();
        pais1.setNombrePais(EnumPais.Colombia.toString());
        representante1.setNombreInstitucion("Universidad de Bogota");
        representante1.setClaveInstitucional("9999");
        representante1.setContacto("5267231821");
        representante1.setPais(pais1);
        representantesEsperados.add(representante1);
        
        DAORepresentanteInstitucionalImplementacion pruebaConsulta = new DAORepresentanteInstitucionalImplementacion();
        representantesObtenidos = pruebaConsulta.obtenerRepresentantesInstitucionalesPorPais(pais1);
        
        assertEquals(representantesEsperados,representantesObtenidos);
    }
    
    @Test
    public void pruebaFlujoFallidoObtenerRepresentantesInstitucionalesPorPaisExitosa(){
        List<RepresentanteInstitucional> representantesObtenidos = new ArrayList();
        List<RepresentanteInstitucional> representantesEsperados = new ArrayList();
        RepresentanteInstitucional representante1 = new RepresentanteInstitucional();
        Pais pais1 = new Pais();
        pais1.setNombrePais(EnumPais.Alemania.toString());
        representante1.setNombreInstitucion("Universidad de munich");
        representante1.setClaveInstitucional("9999");
        representante1.setContacto("5533442211");
        representante1.setPais(pais1);
        representantesEsperados.add(representante1);
        
        DAORepresentanteInstitucionalImplementacion pruebaConsulta = new DAORepresentanteInstitucionalImplementacion();
        representantesObtenidos = pruebaConsulta.obtenerRepresentantesInstitucionalesPorPais(pais1);
        
        assertNotEquals(representantesEsperados,representantesObtenidos);
    }
    
    @Test
    public void pruebaConsultarIdRepresentanteInstitucionalPorUniversidadExitosa(){
        DAORepresentanteInstitucionalImplementacion pruebaConsulta = new DAORepresentanteInstitucionalImplementacion();
        String universidad="UNAM";
        int resultadoEsperado=1;
        int resultadoObtenido=pruebaConsulta.consultarIdRepresentanteInstitucionalPorUniversidad(universidad);
        assertEquals(resultadoEsperado,resultadoObtenido);        
    }
    
    @Test
    public void pruebaConsultarNombreInstitucionPorIdRepresentanteInstitucionalExitosa(){
        DAORepresentanteInstitucionalImplementacion pruebaConsulta = new DAORepresentanteInstitucionalImplementacion();
        int idRepresentanteInstitucional=1;
        String resultadoEsperado="UNAM";
        String resultadoObtenido=pruebaConsulta.consultarNombreInstitucionPorIdRepresentanteInstitucional(idRepresentanteInstitucional);
        assertEquals(resultadoEsperado,resultadoObtenido);          
    }
    
    
}
