package pruebaLogicaDeNegocio.DAOImplementacion;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOPaisImplementacion;
import logicaDeNegocio.clases.Pais;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;
import logicaDeNegocio.enums.EnumPais;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.ComparisonFailure;

public class pruebaDAOPaisImplementacion {
    
    @Before
    public void setUp() {
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentapruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        UsuarioSingleton.getInstancia(usuarioPrueba);
    }
    
    @Test
    public void pruebaRegistrarPaisExitosa(){
        Pais paisPrueba = new Pais();
        DAOPaisImplementacion pruebaMetodo = new DAOPaisImplementacion(); 
        paisPrueba.setNombrePais(EnumPais.Ecuador.toString());       
        int resultadoInsercion = pruebaMetodo.registrarPais(paisPrueba);        
        assertEquals(1,resultadoInsercion);        
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaRegistrarPaisFallida() {
        Pais paisPrueba = new Pais();
        DAOPaisImplementacion pruebaMetodo = new DAOPaisImplementacion();
        paisPrueba.setNombrePais(null);
        try {
            int resultadoInsercion = pruebaMetodo.registrarPais(paisPrueba);
            assertEquals(-1, resultadoInsercion);
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException|| e instanceof NullPointerException);
        }
    }    
    
    @Test(expected = IllegalArgumentException.class)
    public void pruebaFallidaRegistrarPais() {
        DAOPaisImplementacion dao = new DAOPaisImplementacion();
        Pais pais = new Pais();
        pais.setNombrePais("Corea_Sur");
        int resultado = dao.registrarPais(pais);
    }
    
    @Test
    public void pruebaObtenerNumeroDePaisExitosa(){
        Pais paisPrueba = new Pais();
        DAOPaisImplementacion pruebaMetodo = new DAOPaisImplementacion(); 
        paisPrueba.setNombrePais(EnumPais.Colombia.toString());       
        int resultadoConsulta = pruebaMetodo.obtenerNumeroDePais(paisPrueba);
        assertEquals(2,resultadoConsulta);
    }
    
    @Test (expected = AssertionError.class)
    public void pruebaFlujoFallidoObtenerNumeroDePaisExitoso(){
        Pais paisPrueba = new Pais();
        DAOPaisImplementacion pruebaMetodo = new DAOPaisImplementacion();         
        int resultadoConsulta = pruebaMetodo.obtenerNumeroDePais(paisPrueba);
        assertEquals(3,resultadoConsulta);
    }
    
    @Test
    public void pruebaConsultarPaisesExitoso() {
        DAOPaisImplementacion dao = new DAOPaisImplementacion();
        List<Pais> paises = dao.consultarPaises();
        assertEquals(5, paises.size());
    }
    
    @Test(expected = AssertionError.class)
    public void pruebaFallidaConsultarPaisesExitosa() {
        DAOPaisImplementacion dao = new DAOPaisImplementacion();
        List<Pais> paises = dao.consultarPaises();
        assertEquals(4, paises.size());
    }
    
    @Test
    public void pruebaVerificarPaisExitosa() {
        DAOPaisImplementacion dao = new DAOPaisImplementacion();
        int resultado = dao.verificarPais();
        assertTrue(resultado > 1);
    }

    @Test(expected = RuntimeException.class)
    public void pruebaVerificarPaisFallida() {
        DAOPaisImplementacion dao = new DAOPaisImplementacion();        
        dao = new DAOPaisImplementacion() {
            @Override
            public int verificarPais() {
                throw new RuntimeException();
            }
        };
        dao.verificarPais();
    }
    
    @Test
    public void pruebaVerificarPaisSinValores() {
        DAOPaisImplementacion daoPais = new DAOPaisImplementacion() {
            @Override
            public int verificarPais() {           
                return 0;
            }
        };
        int resultado = daoPais.verificarPais();
        assertEquals(0, resultado);
    }
}

