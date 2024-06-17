package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOPaisImplementacion;
import logicaDeNegocio.clases.Pais;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

public class PruebaDAOPaisImplementacionSinConexionExitosa {
    
    @BeforeClass
    public static void inicializar() {
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentaadmin@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        UsuarioSingleton.getInstancia(usuarioPrueba);
    }
    
    @Test
    public void pruebaObtenerNumeroDePaisSinConexionExitosa(){
        Pais paisPrueba = new Pais();
        DAOPaisImplementacion pruebaMetodo = new DAOPaisImplementacion(); 
        paisPrueba.setNombrePais("México");       
        int resultadoConsulta = pruebaMetodo.obtenerNumeroDePais(paisPrueba);
        assertEquals(-1,resultadoConsulta);                
    }
    
    @Test
    public void pruebaConsultarPaisesSinConexionExitosa(){
        DAOPaisImplementacion dao = new DAOPaisImplementacion();
        List<Pais> paisesObtenidos = dao.consultarPaises();
        Pais paisEsperado = new Pais();
        paisEsperado.setNombrePais("Excepcion");
        assertEquals(paisEsperado.getNombrePais(),paisesObtenidos.get(0).getNombrePais());        
    }
    
    @Test
    public void pruebaVerificarPaisSinConexionExitosa(){
        DAOPaisImplementacion dao = new DAOPaisImplementacion();
        int resultado = dao.verificarPais();
        assertEquals(-1,resultado);         
    }    
}
