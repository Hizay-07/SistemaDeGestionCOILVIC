package pruebaLogicaDeNegocio.DAOImplementacion;

import logicaDeNegocio.clases.RepresentanteInstitucional;
import logicaDeNegocio.clases.Pais;
import logicaDeNegocio.DAOImplementacion.DAORepresentanteInstitucionalImplementacion;
import logicaDeNegocio.enums.EnumPais;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;


public class PruebaDAORepresentanteInstitucionalImplementacionPruebas {
    
    @Before
    public void setUp() {
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentapruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        UsuarioSingleton.getInstancia(usuarioPrueba);
    }
    
    @Test
    public void pruebaRegistrarRepresentanteInstitucionalExitosa(){
        DAORepresentanteInstitucionalImplementacion pruebaRegistro = new DAORepresentanteInstitucionalImplementacion();
        
        RepresentanteInstitucional representanteDePrueba = new RepresentanteInstitucional();
        Pais pruebaPais = new Pais();
        pruebaPais.setNombrePais(EnumPais.Mexico.toString());
        representanteDePrueba.setNombreInstitucion("UDAL");
        representanteDePrueba.setClaveInstitucional("123AS");
        representanteDePrueba.setContacto("udal@gmail.com");
        representanteDePrueba.setPais(pruebaPais);        
        int resultadoDePrueba =pruebaRegistro.registrarRepresentanteInstitucional(representanteDePrueba);
        System.out.println(resultadoDePrueba);
        assertEquals(1,resultadoDePrueba);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaFlujoFallidoRegistrarRepresentanteInstitucionalExitosa(){
        DAORepresentanteInstitucionalImplementacion pruebaRegistro = new DAORepresentanteInstitucionalImplementacion();        
        RepresentanteInstitucional representanteDePrueba = new RepresentanteInstitucional();
        Pais pruebaPais = new Pais();
        pruebaPais.setNombrePais(EnumPais.Mexico.toString());
        representanteDePrueba.setNombreInstitucion("IUV");
        representanteDePrueba.setClaveInstitucional("17Y5FSS-A");
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
        representanteDePrueba.setIdRepresentanteInstitucional(4);    
        pruebaPais.setNombrePais(EnumPais.Mexico.toString());
        representanteDePrueba.setNombreInstitucion("UANL");
        representanteDePrueba.setClaveInstitucional("123A");
        representanteDePrueba.setContacto("upav@gmail.com");
        representanteDePrueba.setPais(pruebaPais);
        String nombreActualizado = "UPAV";        
        int resultadoDePrueba = pruebaModificacion.modificarNombreRepresentanteInstitucional(nombreActualizado, representanteDePrueba);
        assertEquals(1,resultadoDePrueba);
    }
        
    @Test (expected = AssertionError.class)
    public void pruebaFlujoIdFallidaModificarNombreRepresentanteInstitucionalExitosa(){
        DAORepresentanteInstitucionalImplementacion pruebaModificacion = new DAORepresentanteInstitucionalImplementacion();        
        RepresentanteInstitucional representanteDePrueba = new RepresentanteInstitucional();
        Pais pruebaPais = new Pais();
        representanteDePrueba.setIdRepresentanteInstitucional(6);    
        pruebaPais.setNombrePais(EnumPais.Mexico.toString());
        representanteDePrueba.setNombreInstitucion("UTEC");
        representanteDePrueba.setClaveInstitucional("123Aaa");
        representanteDePrueba.setContacto("unite@gmail.com");
        representanteDePrueba.setPais(pruebaPais);
        String nombreActualizado = "UNIG";        
        int resultadoDePrueba = pruebaModificacion.modificarNombreRepresentanteInstitucional(nombreActualizado, representanteDePrueba);
        assertEquals(1,resultadoDePrueba);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaFlujoDatosFallidaModificarNombreRepresentanteInstitucionalExitosa(){
        DAORepresentanteInstitucionalImplementacion pruebaModificacion = new DAORepresentanteInstitucionalImplementacion();        
        RepresentanteInstitucional representanteDePrueba = new RepresentanteInstitucional();
        Pais pruebaPais = new Pais();
        representanteDePrueba.setIdRepresentanteInstitucional(4);    
        pruebaPais.setNombrePais(EnumPais.Mexico.toString());
        representanteDePrueba.setNombreInstitucion("UA-NL");
        representanteDePrueba.setClaveInstitucional("17Y5FSSA");
        representanteDePrueba.setContacto("buap@gmail.com");
        representanteDePrueba.setPais(pruebaPais);
        String nombreActualizado = "UNIGDL1890";        
        int resultadoDePrueba = pruebaModificacion.modificarNombreRepresentanteInstitucional(nombreActualizado, representanteDePrueba);
        assertEquals(1,resultadoDePrueba);
    }
            
    @Test 
    public void pruebaModificarClaveRepresentanteInstitucionalExitosa(){
        DAORepresentanteInstitucionalImplementacion pruebaModificacion = new DAORepresentanteInstitucionalImplementacion();        
        RepresentanteInstitucional representanteDePrueba = new RepresentanteInstitucional();
        Pais pruebaPais = new Pais();
        pruebaPais.setNombrePais(EnumPais.Mexico.toString());
                representanteDePrueba.setIdRepresentanteInstitucional(1);  
        representanteDePrueba.setNombreInstitucion("UNAM");
        representanteDePrueba.setClaveInstitucional("5678A");
        representanteDePrueba.setContacto("contacto@unam.mx");
        representanteDePrueba.setPais(pruebaPais);
        String claveActualizada = "20DTV645GFGS";        
        int resultadoDePrueba = pruebaModificacion.modificarClaveRepresentanteInstitucional(claveActualizada, representanteDePrueba);
        assertEquals(1,resultadoDePrueba);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaFallidaModificarClaveRepresentanteInstitucionalExitosa(){
        DAORepresentanteInstitucionalImplementacion pruebaModificacion = new DAORepresentanteInstitucionalImplementacion();        
        RepresentanteInstitucional representanteDePrueba = new RepresentanteInstitucional();
        Pais pruebaPais = new Pais();
        pruebaPais.setNombrePais(EnumPais.Mexico.toString());
        representanteDePrueba.setIdRepresentanteInstitucional(1);  
        representanteDePrueba.setNombreInstitucion("UNAM-MX");
        representanteDePrueba.setClaveInstitucional("5678A");
        representanteDePrueba.setContacto("contacto@unam.mx");
        representanteDePrueba.setPais(pruebaPais);
        String claveActualizada = "20DTV6-45GFGS";        
        int resultadoDePrueba = pruebaModificacion.modificarClaveRepresentanteInstitucional(claveActualizada, representanteDePrueba);
        assertEquals(1,resultadoDePrueba);
    }
    
    @Test (expected = AssertionError.class)
    public void pruebaFallidaNoEscontradoModificarClaveRepresentanteInstitucionalExitosa(){
        DAORepresentanteInstitucionalImplementacion pruebaModificacion = new DAORepresentanteInstitucionalImplementacion();        
        RepresentanteInstitucional representanteDePrueba = new RepresentanteInstitucional();
        Pais pruebaPais = new Pais();
        pruebaPais.setNombrePais(EnumPais.Mexico.toString());
                representanteDePrueba.setIdRepresentanteInstitucional(8);  
        representanteDePrueba.setNombreInstitucion("UNAM");
        representanteDePrueba.setClaveInstitucional("5678A");
        representanteDePrueba.setContacto("contacto@unam.mx");
        representanteDePrueba.setPais(pruebaPais);
        String claveActualizada = "20DTV645GFGS";        
        int resultadoDePrueba = pruebaModificacion.modificarClaveRepresentanteInstitucional(claveActualizada, representanteDePrueba);
        assertEquals(1,resultadoDePrueba);
    }
    
    @Test
    public void pruebaModificarContactoRepresentanteInstitucionalExitosa(){
        DAORepresentanteInstitucionalImplementacion pruebaModificacion = new DAORepresentanteInstitucionalImplementacion();        
        RepresentanteInstitucional representanteDePrueba = new RepresentanteInstitucional();
        Pais pruebaPais = new Pais();
        pruebaPais.setNombrePais(EnumPais.Mexico.toString());
        representanteDePrueba.setIdRepresentanteInstitucional(1);  
        representanteDePrueba.setNombreInstitucion("UNAM");
        representanteDePrueba.setClaveInstitucional("20DTV645GFGS");
        representanteDePrueba.setContacto("contacto@unam.mx");
        representanteDePrueba.setPais(pruebaPais);
        String contactoActualizado = "pruebacontacto@unam.mx";        
        int resultadoDePrueba = pruebaModificacion.modificarContactoRepresentanteInstitucional(contactoActualizado, representanteDePrueba);
        assertEquals(1,resultadoDePrueba);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaFallidaDatosModificarContactoRepresentanteInstitucionalExitosa(){
        DAORepresentanteInstitucionalImplementacion pruebaModificacion = new DAORepresentanteInstitucionalImplementacion();        
        RepresentanteInstitucional representanteDePrueba = new RepresentanteInstitucional();
        Pais pruebaPais = new Pais();
        pruebaPais.setNombrePais(EnumPais.Mexico.toString());
        representanteDePrueba.setIdRepresentanteInstitucional(1);  
        representanteDePrueba.setNombreInstitucion("UNAM");
        representanteDePrueba.setClaveInstitucional("20DTV-645GFGS");
        representanteDePrueba.setContacto("pruebacontacto@unam.mx");
        representanteDePrueba.setPais(pruebaPais);
        String contactoActualizado = "pruebaPumas@unam.mx";        
        int resultadoDePrueba = pruebaModificacion.modificarContactoRepresentanteInstitucional(contactoActualizado, representanteDePrueba);
        assertEquals(1,resultadoDePrueba);
    }
    
    @Test (expected = AssertionError.class)
    public void pruebaFallidaNoEncontradoModificarContactoRepresentanteInstitucionalExitosa(){
        DAORepresentanteInstitucionalImplementacion pruebaModificacion = new DAORepresentanteInstitucionalImplementacion();        
        RepresentanteInstitucional representanteDePrueba = new RepresentanteInstitucional();
        Pais pruebaPais = new Pais();
        pruebaPais.setNombrePais(EnumPais.Mexico.toString());
        representanteDePrueba.setIdRepresentanteInstitucional(10);  
        representanteDePrueba.setNombreInstitucion("UNAMM");
        representanteDePrueba.setClaveInstitucional("20DTV645GFGS");
        representanteDePrueba.setContacto("pruebacontacto@unam.mx");
        representanteDePrueba.setPais(pruebaPais);
        String contactoActualizado = "pruebaPumas@unam.mx";        
        int resultadoDePrueba = pruebaModificacion.modificarContactoRepresentanteInstitucional(contactoActualizado, representanteDePrueba);
        assertEquals(1,resultadoDePrueba);
    }
      
    @Test
    public void pruebaModificarPaisRepresentanteInstitucionalExitosa() {
        DAORepresentanteInstitucionalImplementacion dao = new DAORepresentanteInstitucionalImplementacion();
        RepresentanteInstitucional representante = new RepresentanteInstitucional();
        representante.setIdRepresentanteInstitucional(1);
        Pais nuevoPais = new Pais();
        nuevoPais.setNombrePais("Colombia");
        representante.setPais(nuevoPais);
        int resultado = dao.modificarPaisRepresentanteInstitucional(representante);
        assertEquals(1, resultado);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaFallidaDatosModificarPaisRepresentanteInstitucionalExitosa() {
        DAORepresentanteInstitucionalImplementacion dao = new DAORepresentanteInstitucionalImplementacion();
        RepresentanteInstitucional representante = new RepresentanteInstitucional();
        representante.setIdRepresentanteInstitucional(1);
        Pais nuevoPais = new Pais();
        nuevoPais.setNombrePais("Canadá_");
        representante.setPais(nuevoPais);
        int resultado = dao.modificarPaisRepresentanteInstitucional(representante);
        assertEquals(1, resultado);
    }
    
    @Test (expected = AssertionError.class)
    public void pruebaFallidaNoEncontradoModificarPaisRepresentanteInstitucionalExitosa() {
        DAORepresentanteInstitucionalImplementacion dao = new DAORepresentanteInstitucionalImplementacion();
        RepresentanteInstitucional representante = new RepresentanteInstitucional();
        representante.setIdRepresentanteInstitucional(11);
        Pais nuevoPais = new Pais();
        nuevoPais.setNombrePais("Canadá");
        representante.setPais(nuevoPais);
        int resultado = dao.modificarPaisRepresentanteInstitucional(representante);
        assertEquals(1, resultado);
    }
       
    @Test
    public void pruebaVerificarExistenciaClaveInstitucionalRepresentanteInstitucional_Exito() {
        DAORepresentanteInstitucionalImplementacion dao = new DAORepresentanteInstitucionalImplementacion();
        RepresentanteInstitucional representante = new RepresentanteInstitucional();
        representante.setClaveInstitucional("123A");
        int resultado = dao.verificarExistenciaClaveInstitucionalRepresentanteInstitucional(representante);
        assertEquals(1, resultado); 
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaFallidaDatosVerificarExistenciaClaveInstitucionalRepresentanteInstitucional_Exito() {
        DAORepresentanteInstitucionalImplementacion dao = new DAORepresentanteInstitucionalImplementacion();
        RepresentanteInstitucional representante = new RepresentanteInstitucional();
        representante.setClaveInstitucional("123A-*/");
        int resultado = dao.verificarExistenciaClaveInstitucionalRepresentanteInstitucional(representante);
        assertEquals(1, resultado);
    }
    
    @Test (expected = AssertionError.class)
    public void pruebaFallidaNoEncontradaVerificarExistenciaClaveInstitucionalRepresentanteInstitucional_Exito() {
        DAORepresentanteInstitucionalImplementacion dao = new DAORepresentanteInstitucionalImplementacion();
        RepresentanteInstitucional representante = new RepresentanteInstitucional();
        representante.setClaveInstitucional("123asds");
        int resultado = dao.verificarExistenciaClaveInstitucionalRepresentanteInstitucional(representante);
        assertEquals(1, resultado); 
    }
      
    @Test
    public void testVerificarExistenciaNombreInstitucionRepresentanteInstitucional_Exito() {
        DAORepresentanteInstitucionalImplementacion dao = new DAORepresentanteInstitucionalImplementacion();
        RepresentanteInstitucional representante = new RepresentanteInstitucional();
        representante.setNombreInstitucion("UNAM");
        int resultado = dao.verificarExistenciaNombreInstitucionRepresentanteInstitucional(representante);
        assertEquals(1, resultado);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaFallidaDatosVerificarExistenciaNombreInstitucionalRepresentanteInstitucional_Exito() {
        DAORepresentanteInstitucionalImplementacion dao = new DAORepresentanteInstitucionalImplementacion();
        RepresentanteInstitucional representante = new RepresentanteInstitucional();
        representante.setNombreInstitucion("UNAM_2024");
        int resultado = dao.verificarExistenciaNombreInstitucionRepresentanteInstitucional(representante);
        assertEquals(1, resultado);
    }
    
    @Test (expected = AssertionError.class)
    public void pruebaFallidaNoEncontradaVerificarExistenciaNombreInstitucionalRepresentanteInstitucional_Exito() {
        DAORepresentanteInstitucionalImplementacion dao = new DAORepresentanteInstitucionalImplementacion();
        RepresentanteInstitucional representante = new RepresentanteInstitucional();
        representante.setClaveInstitucional("UX");
        int resultado = dao.verificarExistenciaNombreInstitucionRepresentanteInstitucional(representante);
        assertEquals(1, resultado); 
    }
    
    @Test
    public void pruebaVerificarExistenciaContactoInstitucionalRepresentanteInstitucional_Exito() {
        DAORepresentanteInstitucionalImplementacion dao = new DAORepresentanteInstitucionalImplementacion();
        RepresentanteInstitucional representante = new RepresentanteInstitucional();
        representante.setContacto("buap@gmail.com");
        int resultado = dao.verificarExistenciaContactoInstitucionRepresentanteInstitucional(representante);
        assertEquals(1, resultado); 
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaFallidaDatosVerificarExistenciaContactoInstitucionalRepresentanteInstitucional_Exito() {
        DAORepresentanteInstitucionalImplementacion dao = new DAORepresentanteInstitucionalImplementacion();
        RepresentanteInstitucional representante = new RepresentanteInstitucional();
        representante.setContacto("|Upav@gmail.com");
        int resultado = dao.verificarExistenciaContactoInstitucionRepresentanteInstitucional(representante);
        assertEquals(1, resultado); 
    }
    
    @Test (expected = AssertionError.class)
    public void pruebaFallidaNoEncontradaVerificarExistenciaContactoInstitucionalRepresentanteInstitucional_Exito() {
        DAORepresentanteInstitucionalImplementacion dao = new DAORepresentanteInstitucionalImplementacion();
        RepresentanteInstitucional representante = new RepresentanteInstitucional();
        representante.setContacto("cuautemoc@gmail.com");
        int resultado = dao.verificarExistenciaContactoInstitucionRepresentanteInstitucional(representante);
        assertEquals(1, resultado); 
    }
     
    @Test
    public void testObtenerRepresentantesInstitucionalesExitoso() {
        DAORepresentanteInstitucionalImplementacion dao = new DAORepresentanteInstitucionalImplementacion();
        List<RepresentanteInstitucional> representantes = dao.obtenerRepresentantesInstitucionales();
        assertEquals(4, representantes.size()); // Comprobar que se obtuvieron 4 representantes
        RepresentanteInstitucional representante1 = representantes.get(0);
        assertEquals(1, representante1.getIdRepresentanteInstitucional());
        assertEquals("UNAM", representante1.getNombreInstitucion());
        assertEquals("20DTV645GFGS", representante1.getClaveInstitucional());
        assertEquals("pruebacontacto@unam.mx", representante1.getContacto());
        assertEquals("Colombia", representante1.getPais().getNombrePais());
    }
    
    @Test (expected = AssertionError.class)
    public void pruebaFlujoFallidoObtenerRepresentantesInstitucionalesExitosa() throws AssertionError{
        DAORepresentanteInstitucionalImplementacion dao = new DAORepresentanteInstitucionalImplementacion();
        List<RepresentanteInstitucional> representantes = dao.obtenerRepresentantesInstitucionales();
        assertEquals(0, representantes.size());
    }

    @Test
    public void pruebaConsultarIdRepresentanteInstitucionalPorUniversidadExitosa(){
        DAORepresentanteInstitucionalImplementacion pruebaConsulta = new DAORepresentanteInstitucionalImplementacion();
        String universidad="UNAM";
        int resultadoEsperado=1;
        int resultadoObtenido=pruebaConsulta.consultarIdRepresentanteInstitucionalPorUniversidad(universidad);
        assertEquals(resultadoEsperado,resultadoObtenido);        
    }
    
    @Test(expected = AssertionError.class)
    public void pruebaConsultarIdRepresentanteInstitucionalPorUniversidadFallida() {
        DAORepresentanteInstitucionalImplementacion pruebaConsulta = new DAORepresentanteInstitucionalImplementacion();
        String universidad = "UNAM";
        int resultadoObtenido = pruebaConsulta.consultarIdRepresentanteInstitucionalPorUniversidad(universidad);
        assertEquals(0, resultadoObtenido);
    }
    
    @Test
    public void pruebaConsultarNombreInstitucionPorIdRepresentanteInstitucionalExitosa(){
        DAORepresentanteInstitucionalImplementacion dao = new DAORepresentanteInstitucionalImplementacion();
        String nombreInstitucion = dao.consultarNombreInstitucionPorIdRepresentanteInstitucional(1);   
        assertEquals("UNAM", nombreInstitucion);          
    }
    
    @Test(expected = AssertionError.class)
    public void pruebaConsultarNombreInstitucionPorIdRepresentanteInstitucionalFallida() {
        DAORepresentanteInstitucionalImplementacion dao = new DAORepresentanteInstitucionalImplementacion();        
        int idRepresentanteInstitucional = 1;
        String nombreInstitucion = dao.consultarNombreInstitucionPorIdRepresentanteInstitucional(idRepresentanteInstitucional);
        assertEquals("UANL", nombreInstitucion);
    }    
    
}
