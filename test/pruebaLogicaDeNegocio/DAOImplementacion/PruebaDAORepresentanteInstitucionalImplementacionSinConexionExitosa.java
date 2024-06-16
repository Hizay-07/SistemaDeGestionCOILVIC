package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAORepresentanteInstitucionalImplementacion;
import logicaDeNegocio.clases.Pais;
import logicaDeNegocio.clases.RepresentanteInstitucional;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

public class PruebaDAORepresentanteInstitucionalImplementacionSinConexionExitosa {
    
    @BeforeClass
    public static void inicializar() {
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentaadmin@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        UsuarioSingleton.getInstancia(usuarioPrueba);
    }
    
    @Test
    public void pruebaRegistrarRepresentanteInstitucionalSinConexionExitosa(){
        DAORepresentanteInstitucionalImplementacion pruebaRegistro = new DAORepresentanteInstitucionalImplementacion();        
        RepresentanteInstitucional representanteDePrueba = new RepresentanteInstitucional();
        Pais pais=new Pais();
        pais.setNombrePais("México");
        representanteDePrueba.setPais(pais);
        representanteDePrueba.setNombreInstitucion("UDAL");
        representanteDePrueba.setClaveInstitucional("123AS");
        representanteDePrueba.setContacto("udal@gmail.com");             
        int resultadoDePrueba =pruebaRegistro.registrarRepresentanteInstitucional(representanteDePrueba);        
        assertEquals(-1,resultadoDePrueba);        
    }
    
    @Test
    public void pruebaObtenerRepresentanteInstitucionalSinConexionExitosa(){
        DAORepresentanteInstitucionalImplementacion dao = new DAORepresentanteInstitucionalImplementacion();
        List<RepresentanteInstitucional> representantesObtenidos = dao.obtenerRepresentantesInstitucionales();
        RepresentanteInstitucional representanteEsperado = new RepresentanteInstitucional();
        representanteEsperado.setNombreInstitucion("Excepcion");
        assertEquals(representanteEsperado.getNombreInstitucion(),representantesObtenidos.get(0).getNombreInstitucion());   
    }
    
    @Test
    public void pruebaConsultarIdRepresentanteInstitucionalPorUniversidadSinConexionExitosa(){
        DAORepresentanteInstitucionalImplementacion pruebaConsulta = new DAORepresentanteInstitucionalImplementacion();
        String universidad="UDAL";
        int resultadoEsperado=-1;
        int resultadoObtenido=pruebaConsulta.consultarIdRepresentanteInstitucionalPorUniversidad(universidad);
        assertEquals(resultadoEsperado,resultadoObtenido);        
    }
    
    @Test
    public void pruebaConsultarNombreInstitucionPorIdRepresentanteInstitucionalSinConexionExitosa(){
        DAORepresentanteInstitucionalImplementacion dao = new DAORepresentanteInstitucionalImplementacion();        
        int idRepresentanteInstitucional = 1;
        String nombreInstitucion = dao.consultarNombreInstitucionPorIdRepresentanteInstitucional(idRepresentanteInstitucional);
        assertEquals("Excepcion", nombreInstitucion);                
    }
    
    @Test
    public void pruebaVerificarRepresentanteInstitucionalSinConexionExitosa(){
        DAORepresentanteInstitucionalImplementacion dao = new DAORepresentanteInstitucionalImplementacion();
        int resultado = dao.verificarRepresentanteInstitucional();
        assertEquals(-1,resultado);         
    }
    
    @Test
    public void pruebaVerificarExistenciaClaveInstitucionalRepresentanteInstitucionalSinConexionExitosa(){
        DAORepresentanteInstitucionalImplementacion dao = new DAORepresentanteInstitucionalImplementacion();
        RepresentanteInstitucional representante = new RepresentanteInstitucional();
        representante.setClaveInstitucional("123AS");
        int resultado = dao.verificarExistenciaClaveInstitucionalRepresentanteInstitucional(representante);
        assertEquals(-1, resultado); 
    }
    
    @Test
    public void pruebaVerificarExistenciaNombreInstitucionRepresentanteInstitucionalSinConexionExitosa(){
        DAORepresentanteInstitucionalImplementacion dao = new DAORepresentanteInstitucionalImplementacion();
        RepresentanteInstitucional representante = new RepresentanteInstitucional();
        representante.setNombreInstitucion("UDAL");
        int resultado = dao.verificarExistenciaNombreInstitucionRepresentanteInstitucional(representante);
        assertEquals(-1, resultado);        
    }
    
    @Test
    public void pruebaVerificarExistenciaContactoInstitucionalRepresentanteInstitucionalSinConexionExitosa(){
        DAORepresentanteInstitucionalImplementacion dao = new DAORepresentanteInstitucionalImplementacion();
        RepresentanteInstitucional representante = new RepresentanteInstitucional();
        representante.setContacto("udal@gmail.com");
        int resultado = dao.verificarExistenciaContactoInstitucionRepresentanteInstitucional(representante);
        assertEquals(-1, resultado);                 
    }
    
    @Test
    public void pruebaModificarNombreRepresentanteInstitucionalSinConexionExitosa(){
        DAORepresentanteInstitucionalImplementacion pruebaModificacion = new DAORepresentanteInstitucionalImplementacion();        
        RepresentanteInstitucional representanteDePrueba = new RepresentanteInstitucional();        
        representanteDePrueba.setIdRepresentanteInstitucional(1);                                            
        String nombreActualizado = "BUAP";        
        int resultadoDePrueba = pruebaModificacion.modificarNombreRepresentanteInstitucional(nombreActualizado, representanteDePrueba);
        assertEquals(-1,resultadoDePrueba);    
    }
    
    @Test
    public void pruebaModificarClaveRepresentanteInstitucionalSinConexionExitosa(){
        DAORepresentanteInstitucionalImplementacion pruebaModificacion = new DAORepresentanteInstitucionalImplementacion();        
        RepresentanteInstitucional representanteDePrueba = new RepresentanteInstitucional();                
        representanteDePrueba.setIdRepresentanteInstitucional(1);                                  
        String claveActualizada = "30AGM8";        
        int resultadoDePrueba = pruebaModificacion.modificarClaveRepresentanteInstitucional(claveActualizada, representanteDePrueba);
        assertEquals(-1,resultadoDePrueba);                    
    }
    
    @Test
    public void pruebaModificarContactoRepresentanteInstitucionalSinConexionExitosa(){
        DAORepresentanteInstitucionalImplementacion pruebaModificacion = new DAORepresentanteInstitucionalImplementacion();        
        RepresentanteInstitucional representanteDePrueba = new RepresentanteInstitucional();                
        representanteDePrueba.setIdRepresentanteInstitucional(1);                                  
        String contactoActualizado = "prueba@gmail.com";        
        int resultadoDePrueba = pruebaModificacion.modificarContactoRepresentanteInstitucional(contactoActualizado, representanteDePrueba);
        assertEquals(-1,resultadoDePrueba);        
    }
    
    @Test 
    public void pruebaModificarPaisRepresentanteInstitucionalSinConexionExitosa(){
        DAORepresentanteInstitucionalImplementacion dao = new DAORepresentanteInstitucionalImplementacion();
        RepresentanteInstitucional representante = new RepresentanteInstitucional();
        representante.setIdRepresentanteInstitucional(1);
        Pais nuevoPais = new Pais();
        nuevoPais.setNombrePais("Brasil");
        representante.setPais(nuevoPais);
        int resultado = dao.modificarPaisRepresentanteInstitucional(representante);
        assertEquals(-1, resultado);        
    }         
}
