package pruebaLogicaDeNegocio.DAOImplementacion;

import logicaDeNegocio.DAOImplementacion.DAOUsuarioImplementacion;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Before;

public class PruebaDAOUsuarioImplementacion {
        
    @Before
    public void setUp() {
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentapruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        UsuarioSingleton.getInstancia(usuarioPrueba);
    }
     
    @Test
    public void pruebaRegistrarUsuarioExitosa() {
        Usuario usuarioPrueba = new Usuario();
        DAOUsuarioImplementacion implementacion = new DAOUsuarioImplementacion();
        usuarioPrueba.setNombreUsuario("profesoruvtres@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Profesor");
        int resultado = implementacion.registrarUsuario(usuarioPrueba);
        assertEquals(1, resultado);
    }
    
    @Test
    public void pruebaRegistrarUsuarioFallida() {
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario(null);
        usuarioPrueba.setContrasenia(null);
        usuarioPrueba.setTipoDeUsuario(null);
        DAOUsuarioImplementacion dao = new DAOUsuarioImplementacion();
        int resultadoInsercion = dao.registrarUsuario(usuarioPrueba);
        assertEquals(-1, resultadoInsercion);
    }
    
    @Test
    public void pruebaValidarCredencialesExitosa() {
        Usuario usuarioExistente = new Usuario();
        usuarioExistente.setNombreUsuario("cuentaadmin@gmail.com");
        usuarioExistente.setContrasenia("contrasenia123*");
        DAOUsuarioImplementacion dao = new DAOUsuarioImplementacion();
        int resultadoValidacion = dao.validarCredenciales(usuarioExistente, null);
        assertEquals(1, resultadoValidacion);
    }
    
    @Test
    public void pruebaValidarCredencialesFallida() {
        Usuario usuarioNoExistente = new Usuario();
        usuarioNoExistente.setNombreUsuario("usuarioinexistente@gmail.com");
        usuarioNoExistente.setContrasenia("contrasenia123");
        DAOUsuarioImplementacion dao = new DAOUsuarioImplementacion();
        int resultadoValidacion = dao.validarCredenciales(usuarioNoExistente, null);
        assertEquals(0, resultadoValidacion);
    }
    
    @Test
    public void pruebaObtenerTipoDeUsuarioExitosa(){
        Usuario usuarioPrueba = new Usuario();
        Usuario logger = new Usuario();
        logger.setTipoDeUsuario("Logger");
        DAOUsuarioImplementacion implementacion = new DAOUsuarioImplementacion();
        usuarioPrueba.setNombreUsuario("Equipo2");
        usuarioPrueba.setContrasenia("contrasena123");        
        String resultado = implementacion.obtenerTipoDeUsuario(usuarioPrueba,logger);
        assertEquals("Administrativo", resultado);
    }
    
    @Test
    public void pruebaObtenerTipoDeUsuarioFallida() {
        Usuario usuarioNoExistente = new Usuario();
        usuarioNoExistente.setNombreUsuario("usuarioinexistente@gmail.com");
        usuarioNoExistente.setContrasenia("contrasenia123");
        DAOUsuarioImplementacion dao = new DAOUsuarioImplementacion();
        String resultadoTipoDeUsuario = dao.obtenerTipoDeUsuario(usuarioNoExistente, null);
        assertEquals("", resultadoTipoDeUsuario);
    }    
    
    @Test
    public void pruebaObtenerIdUsuario(){
        Usuario usuarioPrueba = new Usuario();
        Usuario logger = new Usuario();
        DAOUsuarioImplementacion implementacion = new DAOUsuarioImplementacion();
        logger.setTipoDeUsuario("Logger");
        usuarioPrueba.setNombreUsuario("CuentaPruebaDos");
        usuarioPrueba.setContrasenia("Contrasena123*");
        usuarioPrueba.setTipoDeUsuario("Profesor");        
        int resultado = implementacion.obtenerIdUsuario(usuarioPrueba,logger);
        assertEquals(2,resultado);
    }
    
    @Test
    public void pruebaObtenerIdUsuarioFallida() {
        Usuario usuarioNoExistente = new Usuario();
        usuarioNoExistente.setNombreUsuario("usuarioinexistente@gmail.com");
        usuarioNoExistente.setContrasenia("contrasenia123");
        DAOUsuarioImplementacion dao = new DAOUsuarioImplementacion();
        int resultadoId = dao.obtenerIdUsuario(usuarioNoExistente, null);
        assertEquals(-1, resultadoId);
    }
    
    @Test
    public void pruebaConfirmarConexionDeInicioDeSesionFallida() {
        Usuario logger = new Usuario();
        DAOUsuarioImplementacion dao = new DAOUsuarioImplementacion();
        boolean resultadoConfirmacion = dao.confirmarConexionDeInicioDeSesion(logger);
        assertFalse(resultadoConfirmacion);
    }
   
}
