package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOTipoColaboracionImplementacion;
import logicaDeNegocio.clases.TipoColaboracion;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

public class PruebaDAOTipoColaboracionImplementacionSinConexionExitosa {
            
    @BeforeClass
    public static void inicializar() {
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentaadmin@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        UsuarioSingleton.getInstancia(usuarioPrueba);
    }
    
    @Test
    public void pruebaConsultarTiposDeColaboracionSinConexionExitosa(){
        DAOTipoColaboracionImplementacion dao = new DAOTipoColaboracionImplementacion();   
        List<TipoColaboracion> tiposColaboracionObtenidas = dao.consultarTiposDeColaboracion();
        TipoColaboracion tipoColaboracionEsperada = new TipoColaboracion();
        tipoColaboracionEsperada.setTipo("Excepcion");
        assertEquals(tipoColaboracionEsperada.getTipo(), tiposColaboracionObtenidas.get(0).getTipo());         
    }
    
    @Test
    public void pruebaConsultarTipoColaboracionPorIdSinConexionExitosa(){
        DAOTipoColaboracionImplementacion dao = new DAOTipoColaboracionImplementacion();
        String tipo = dao.consultarTipoColaboracionPorId(1);
        assertEquals("Excepcion", tipo);        
    }
    
    @Test
    public void pruebaConsultarIdTipoColaboracionPorTipoSinConexionExitosa(){
        DAOTipoColaboracionImplementacion dao = new DAOTipoColaboracionImplementacion();
        int idTipo = dao.consultarIdTipoColaboracionPorTipo("Implementación COIL-VIC");
        assertEquals(-1, idTipo);        
    }
    
    @Test
    public void pruebaVerificarTipoColaboracionSinConexionExitosa(){
        DAOTipoColaboracionImplementacion dao = new DAOTipoColaboracionImplementacion();
        int resultado = dao.verificarTipoColaboracion();
        assertEquals(-1,resultado);         
    }
    
}
