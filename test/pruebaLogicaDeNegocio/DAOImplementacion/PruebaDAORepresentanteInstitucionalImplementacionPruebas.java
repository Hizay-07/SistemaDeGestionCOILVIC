package pruebaLogicaDeNegocio.DAOImplementacion;

import logicaDeNegocio.clases.RepresentanteInstitucional;
import logicaDeNegocio.clases.Pais;
import logicaDeNegocio.DAOImplementacion.DAORepresentanteInstitucionalImplementacion;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;


public class PruebaDAORepresentanteInstitucionalImplementacionPruebas {
    
    @Test
    public void pruebaRegistrarRepresentanteInstitucionalExitosa(){
        DAORepresentanteInstitucionalImplementacion pruebaRegistro = new DAORepresentanteInstitucionalImplementacion();
        
        RepresentanteInstitucional representanteDePrueba = new RepresentanteInstitucional();
        Pais pruebaPais = new Pais();
        pruebaPais.setNombrePais("Mexico");
        representanteDePrueba.setNombreInstitucion("UV");
        representanteDePrueba.setClaveInstitucional("1234");
        representanteDePrueba.setContacto("2281709292");
        representanteDePrueba.setPais(pruebaPais);
        
        int resultadoDePrueba =pruebaRegistro.registrarRepresentanteInstitucional(representanteDePrueba);
        System.out.println(resultadoDePrueba);
        assertEquals(1,resultadoDePrueba);
    }
    
    @Test
    public void pruebaModificarNombreRepresentanteInstitucionalExitosa(){
        DAORepresentanteInstitucionalImplementacion pruebaModificacion = new DAORepresentanteInstitucionalImplementacion();
        
        RepresentanteInstitucional representanteDePrueba = new RepresentanteInstitucional();
        Pais pruebaPais = new Pais();
        pruebaPais.setNombrePais("Mexico");
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
        pruebaPais.setNombrePais("Mexico");
        representanteDePrueba.setNombreInstitucion("UNAM");
        representanteDePrueba.setClaveInstitucional("1234");
        representanteDePrueba.setContacto("2281709292");
        representanteDePrueba.setPais(pruebaPais);
        String claveActualizada = "5678";
        
        int resultadoDePrueba = pruebaModificacion.modificarClaveRepresentanteInstitucional(claveActualizada, representanteDePrueba);
        assertEquals(1,resultadoDePrueba);
    }
    
    @Test
    public void pruebaModificarContactoRepresentanteInstitucional(){
        DAORepresentanteInstitucionalImplementacion pruebaModificacion = new DAORepresentanteInstitucionalImplementacion();
        
        RepresentanteInstitucional representanteDePrueba = new RepresentanteInstitucional();
        Pais pruebaPais = new Pais();
        pruebaPais.setNombrePais("Mexico");
        representanteDePrueba.setNombreInstitucion("UNAM");
        representanteDePrueba.setClaveInstitucional("5678");
        representanteDePrueba.setContacto("2281709292");
        representanteDePrueba.setPais(pruebaPais);
        String contactoActualizado = "2281024672";
        
        int resultadoDePrueba = pruebaModificacion.modificarContactoRepresentanteInstitucional(contactoActualizado, representanteDePrueba);
        assertEquals(1,resultadoDePrueba);
    }
    
    @Test
    public void pruebaValidarExistenciaRepresentanteInstitucionalExitosa(){
        DAORepresentanteInstitucionalImplementacion pruebaConsulta = new DAORepresentanteInstitucionalImplementacion();
        
        RepresentanteInstitucional representanteDePrueba = new RepresentanteInstitucional();
        representanteDePrueba.setNombreInstitucion("uv");
        representanteDePrueba.setClaveInstitucional("1234");
        representanteDePrueba.setContacto("2281709292");
        
        int resultadoDePrueba = pruebaConsulta.verificarExistenciaRepresentanteInstitucional(representanteDePrueba);
        assertEquals(1,resultadoDePrueba);
    }
    
}
