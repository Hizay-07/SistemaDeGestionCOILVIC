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
        Pais pais=new Pais();
        pais.setNombrePais("México");
        representanteDePrueba.setPais(pais);
        representanteDePrueba.setNombreInstitucion("UDAL");
        representanteDePrueba.setClaveInstitucional("123AS");
        representanteDePrueba.setContacto("udal@gmail.com");             
        int resultadoDePrueba =pruebaRegistro.registrarRepresentanteInstitucional(representanteDePrueba);        
        assertEquals(1,resultadoDePrueba);
    }
    
    @Test
    public void pruebaRegistrarRepresentanteInstitucionalFallida(){
        DAORepresentanteInstitucionalImplementacion pruebaRegistro = new DAORepresentanteInstitucionalImplementacion();        
        RepresentanteInstitucional representanteDePrueba = new RepresentanteInstitucional();
        Pais pruebaPais = new Pais();
        pruebaPais.setNombrePais("México");        
        representanteDePrueba.setClaveInstitucional("17Y5FSSA");
        representanteDePrueba.setContacto("uni@gmail.com");
        representanteDePrueba.setPais(pruebaPais);        
        int resultadoDePrueba =pruebaRegistro.registrarRepresentanteInstitucional(representanteDePrueba);        
        assertEquals(-1,resultadoDePrueba);
    }
    
    @Test
    public void pruebaObtenerRepresentantesInstitucionalesExitosa() {
        DAORepresentanteInstitucionalImplementacion dao = new DAORepresentanteInstitucionalImplementacion();
        RepresentanteInstitucional representanteDePrueba = new RepresentanteInstitucional();
        Pais pais=new Pais();
        pais.setNombrePais("México");
        representanteDePrueba.setPais(pais);
        representanteDePrueba.setNombreInstitucion("UDAL");
        representanteDePrueba.setClaveInstitucional("123AS");
        representanteDePrueba.setContacto("udal@gmail.com"); 
        List<RepresentanteInstitucional> representantesEsperados=new ArrayList<>();
        representantesEsperados.add(representanteDePrueba);
        List<RepresentanteInstitucional> representantesObtenidos = dao.obtenerRepresentantesInstitucionales();
        assertEquals(representantesEsperados,representantesObtenidos);
    }
    
    @Test (expected = AssertionError.class)
    public void pruebaObtenerRepresentantesInstitucionalesFallida() throws AssertionError{
        DAORepresentanteInstitucionalImplementacion dao = new DAORepresentanteInstitucionalImplementacion();
        List<RepresentanteInstitucional> representantes = dao.obtenerRepresentantesInstitucionales();
        assertEquals(0, representantes.size());
    }

    @Test
    public void pruebaConsultarIdRepresentanteInstitucionalPorUniversidadExitosa(){
        DAORepresentanteInstitucionalImplementacion pruebaConsulta = new DAORepresentanteInstitucionalImplementacion();
        String universidad="UDAL";
        int resultadoEsperado=1;
        int resultadoObtenido=pruebaConsulta.consultarIdRepresentanteInstitucionalPorUniversidad(universidad);
        assertEquals(resultadoEsperado,resultadoObtenido);        
    }
    
    @Test
    public void pruebaConsultarIdRepresentanteInstitucionalPorUniversidadFallida() {
        DAORepresentanteInstitucionalImplementacion pruebaConsulta = new DAORepresentanteInstitucionalImplementacion();
        String universidad = "BUAP";
        int resultadoObtenido = pruebaConsulta.consultarIdRepresentanteInstitucionalPorUniversidad(universidad);
        assertEquals(0, resultadoObtenido);
    }
    
    @Test
    public void pruebaConsultarNombreInstitucionPorIdRepresentanteInstitucionalExitosa(){
        DAORepresentanteInstitucionalImplementacion dao = new DAORepresentanteInstitucionalImplementacion();
        String nombreInstitucion = dao.consultarNombreInstitucionPorIdRepresentanteInstitucional(1);   
        assertEquals("UDAL", nombreInstitucion);          
    }
    
    @Test
    public void pruebaConsultarNombreInstitucionPorIdRepresentanteInstitucionalFallida() {
        DAORepresentanteInstitucionalImplementacion dao = new DAORepresentanteInstitucionalImplementacion();        
        int idRepresentanteInstitucional = 0;
        String nombreInstitucion = dao.consultarNombreInstitucionPorIdRepresentanteInstitucional(idRepresentanteInstitucional);
        assertEquals("", nombreInstitucion);
    } 
    
    @Test
    public void pruebaVerificarRepresentanteInstitucionalExitosa() {
        DAORepresentanteInstitucionalImplementacion dao = new DAORepresentanteInstitucionalImplementacion();
        int resultado = dao.verificarRepresentanteInstitucional();
        assertTrue(resultado > 1);
    }

    @Test
    public void pruebaVerificarRepresentanteInstitucionalSinValores() {
        DAORepresentanteInstitucionalImplementacion dao = new DAORepresentanteInstitucionalImplementacion() {
            @Override
            public int verificarRepresentanteInstitucional() {
                return 0;
            }
        };
        int resultado = dao.verificarRepresentanteInstitucional();
        assertEquals(0, resultado);
    }
    
     @Test
    public void pruebaVerificarExistenciaClaveInstitucionalRepresentanteInstitucionalExitosa() {
        DAORepresentanteInstitucionalImplementacion dao = new DAORepresentanteInstitucionalImplementacion();
        RepresentanteInstitucional representante = new RepresentanteInstitucional();
        representante.setClaveInstitucional("123AS");
        int resultado = dao.verificarExistenciaClaveInstitucionalRepresentanteInstitucional(representante);
        assertEquals(1, resultado); 
    }
    
    @Test
    public void pruebaVerificarExistenciaClaveInstitucionalRepresentanteInstitucionalFallida() {
        DAORepresentanteInstitucionalImplementacion dao = new DAORepresentanteInstitucionalImplementacion();
        RepresentanteInstitucional representante = new RepresentanteInstitucional();
        representante.setClaveInstitucional("XHDB");
        int resultado = dao.verificarExistenciaClaveInstitucionalRepresentanteInstitucional(representante);
        assertEquals(0, resultado);
    }
    
        @Test
    public void pruebaVerificarExistenciaNombreInstitucionRepresentanteInstitucionalExitosa() {
        DAORepresentanteInstitucionalImplementacion dao = new DAORepresentanteInstitucionalImplementacion();
        RepresentanteInstitucional representante = new RepresentanteInstitucional();
        representante.setNombreInstitucion("UDAL");
        int resultado = dao.verificarExistenciaNombreInstitucionRepresentanteInstitucional(representante);
        assertEquals(1, resultado);
    }
    
    @Test
    public void pruebaVerificarExistenciaNombreInstitucionalRepresentanteInstitucionalFallida() {
        DAORepresentanteInstitucionalImplementacion dao = new DAORepresentanteInstitucionalImplementacion();
        RepresentanteInstitucional representante = new RepresentanteInstitucional();
        representante.setNombreInstitucion("BUAP");
        int resultado = dao.verificarExistenciaNombreInstitucionRepresentanteInstitucional(representante);
        assertEquals(0, resultado);
    }
    
        @Test
    public void pruebaVerificarExistenciaContactoInstitucionalRepresentanteInstitucionalExitosa() {
        DAORepresentanteInstitucionalImplementacion dao = new DAORepresentanteInstitucionalImplementacion();
        RepresentanteInstitucional representante = new RepresentanteInstitucional();
        representante.setContacto("buap@gmail.com");
        int resultado = dao.verificarExistenciaContactoInstitucionRepresentanteInstitucional(representante);
        assertEquals(1, resultado); 
    }
    
    @Test
    public void pruebaVerificarExistenciaContactoInstitucionalRepresentanteInstitucionalFallida() {
        DAORepresentanteInstitucionalImplementacion dao = new DAORepresentanteInstitucionalImplementacion();
        RepresentanteInstitucional representante = new RepresentanteInstitucional();
        representante.setContacto("upav@gmail.com");
        int resultado = dao.verificarExistenciaContactoInstitucionRepresentanteInstitucional(representante);
        assertEquals(0, resultado); 
    }
    
    
    @Test
    public void pruebaModificarNombreRepresentanteInstitucionalExitosa(){
        DAORepresentanteInstitucionalImplementacion pruebaModificacion = new DAORepresentanteInstitucionalImplementacion();        
        RepresentanteInstitucional representanteDePrueba = new RepresentanteInstitucional();        
        representanteDePrueba.setIdRepresentanteInstitucional(1);                                            
        String nombreActualizado = "UPAV";        
        int resultadoDePrueba = pruebaModificacion.modificarNombreRepresentanteInstitucional(nombreActualizado, representanteDePrueba);
        assertEquals(1,resultadoDePrueba);
    }
        
    @Test
    public void pruebaModificarNombreRepresentanteInstitucionalFallida(){
        DAORepresentanteInstitucionalImplementacion pruebaModificacion = new DAORepresentanteInstitucionalImplementacion();        
        RepresentanteInstitucional representanteDePrueba = new RepresentanteInstitucional();        
        representanteDePrueba.setIdRepresentanteInstitucional(0);                                            
        String nombreActualizado = "BUAP";        
        int resultadoDePrueba = pruebaModificacion.modificarNombreRepresentanteInstitucional(nombreActualizado, representanteDePrueba);
        assertEquals(0,resultadoDePrueba);
    }        
            
    @Test 
    public void pruebaModificarClaveRepresentanteInstitucionalExitosa(){
        DAORepresentanteInstitucionalImplementacion pruebaModificacion = new DAORepresentanteInstitucionalImplementacion();        
        RepresentanteInstitucional representanteDePrueba = new RepresentanteInstitucional();                
        representanteDePrueba.setIdRepresentanteInstitucional(1);                                  
        String claveActualizada = "20DTV";        
        int resultadoDePrueba = pruebaModificacion.modificarClaveRepresentanteInstitucional(claveActualizada, representanteDePrueba);
        assertEquals(1,resultadoDePrueba);
    }
        
    @Test 
    public void pruebaModificarClaveRepresentanteInstitucionalFallida(){
        DAORepresentanteInstitucionalImplementacion pruebaModificacion = new DAORepresentanteInstitucionalImplementacion();        
        RepresentanteInstitucional representanteDePrueba = new RepresentanteInstitucional();                
        representanteDePrueba.setIdRepresentanteInstitucional(0);                                  
        String claveActualizada = "30AGM8";        
        int resultadoDePrueba = pruebaModificacion.modificarClaveRepresentanteInstitucional(claveActualizada, representanteDePrueba);
        assertEquals(0,resultadoDePrueba);
    }
    
    @Test
    public void pruebaModificarContactoRepresentanteInstitucionalExitosa(){
        DAORepresentanteInstitucionalImplementacion pruebaModificacion = new DAORepresentanteInstitucionalImplementacion();        
        RepresentanteInstitucional representanteDePrueba = new RepresentanteInstitucional();                
        representanteDePrueba.setIdRepresentanteInstitucional(1);                                  
        String contactoActualizado = "upav@gmail.com";        
        int resultadoDePrueba = pruebaModificacion.modificarContactoRepresentanteInstitucional(contactoActualizado, representanteDePrueba);
        assertEquals(1,resultadoDePrueba);
    }
    
    @Test
    public void pruebaModificarContactoRepresentanteInstitucionalFallida(){
        DAORepresentanteInstitucionalImplementacion pruebaModificacion = new DAORepresentanteInstitucionalImplementacion();        
        RepresentanteInstitucional representanteDePrueba = new RepresentanteInstitucional();                
        representanteDePrueba.setIdRepresentanteInstitucional(0);                                  
        String contactoActualizado = "prueba@gmail.com";        
        int resultadoDePrueba = pruebaModificacion.modificarContactoRepresentanteInstitucional(contactoActualizado, representanteDePrueba);
        assertEquals(0,resultadoDePrueba);
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
    
    @Test
    public void pruebaModificarPaisRepresentanteInstitucionalFallida() {
        DAORepresentanteInstitucionalImplementacion dao = new DAORepresentanteInstitucionalImplementacion();
        RepresentanteInstitucional representante = new RepresentanteInstitucional();
        representante.setIdRepresentanteInstitucional(0);
        Pais nuevoPais = new Pais();
        nuevoPais.setNombrePais("Brasil");
        representante.setPais(nuevoPais);
        int resultado = dao.modificarPaisRepresentanteInstitucional(representante);
        assertEquals(0, resultado);
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
       
   
    

    
  
    
    
}
