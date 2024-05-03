package pruebaLogicaDeNegocio.DAOImplementacion;

import logicaDeNegocio.DAOImplementacion.DAOUsuarioImplementacion;
import logicaDeNegocio.clases.Usuario;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class PruebaDAOUsuarioImplementacion {
    
    @Test
    public void pruebaRegistrarUsuarioExitosa(){
        Usuario usuarioPrueba = new Usuario();
        DAOUsuarioImplementacion implementacion = new DAOUsuarioImplementacion();
        usuarioPrueba.setNombreUsuario("CuentaPruebaUno");
        usuarioPrueba.setContrasenia("Contrasena123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        
        int resultado = implementacion.registrarUsuario(usuarioPrueba);
        assertEquals(1,resultado);
    }
    
    @Test
    public void pruebaFlujoFallidoRegistrarUsuarioExitosa(){
        Usuario usuarioPrueba = new Usuario();
        DAOUsuarioImplementacion implementacion = new DAOUsuarioImplementacion();
        usuarioPrueba.setNombreUsuario("Equipo2");
        usuarioPrueba.setContrasenia("contrasena123");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        
        int resultado = implementacion.registrarUsuario(usuarioPrueba);
        assertEquals(resultado, resultado);
    }
    
    @Test
    public void pruebaValidarCredencialesExitosa(){
        Usuario usuarioPrueba = new Usuario();
        Usuario logger = new Usuario();
        logger.setTipoDeUsuario("Logger");
        DAOUsuarioImplementacion implementacion = new DAOUsuarioImplementacion();
        usuarioPrueba.setNombreUsuario("Equipo2");
        usuarioPrueba.setContrasenia("contrasena123");
        
        boolean resultado = implementacion.validarCredenciales(usuarioPrueba,logger);
        assertTrue(resultado);
    }
    
    @Test
    public void pruebaFlujoFallidoValidarCredencialesExitosa(){
        Usuario usuarioPrueba = new Usuario();
        Usuario logger = new Usuario();
        logger.setTipoDeUsuario("Logger");
        DAOUsuarioImplementacion implementacion = new DAOUsuarioImplementacion();
        usuarioPrueba.setNombreUsuario("Equipo2");
        usuarioPrueba.setContrasenia("contrasena1234");
        
        boolean resultado = implementacion.validarCredenciales(usuarioPrueba,logger);
        assertFalse(resultado);
    }
    
    @Test
    public void pruebaObtenerTipoDeUsuario(){
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
    public void pruebaFlujoFallidoObtenerTipoDeUsuario(){
        Usuario usuarioPrueba = new Usuario();
        Usuario logger = new Usuario();
        logger.setTipoDeUsuario("Logger");
        DAOUsuarioImplementacion implementacion = new DAOUsuarioImplementacion();
        usuarioPrueba.setNombreUsuario("Equipo2");
        usuarioPrueba.setContrasenia("contrasena1234");
        
        String resultado = implementacion.obtenerTipoDeUsuario(usuarioPrueba,logger);
        assertEquals("",resultado);
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
}
