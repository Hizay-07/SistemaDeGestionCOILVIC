package pruebaAccesoADatos;

import accesoADatos.ManejadorBaseDeDatos;
import java.sql.Connection;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.BeforeClass;
import org.junit.Test;

public class PruebaManejadorBaseDeDatosSinAcceso {
    
    @BeforeClass
    public static void inicializar() {
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentaadmin@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        UsuarioSingleton.getInstancia(usuarioPrueba);
    }
    
    @Test
    public void pruebaConectarBaseDeDatosSinConexionAdministrativoExitosa(){
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentaadmin@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        UsuarioSingleton usuario = UsuarioSingleton.getInstancia(usuarioPrueba);
        ManejadorBaseDeDatos baseDeDatosPrueba = new ManejadorBaseDeDatos();
        Connection resultado = baseDeDatosPrueba.conectarBaseDeDatos();
        assertNotNull(resultado);
    }
    
    @Test
    public void pruebaConectarBaseDeDatosSinConexionProfesorExitosa(){
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentapruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Profesor");
        UsuarioSingleton usuario = UsuarioSingleton.getInstancia(usuarioPrueba);
        ManejadorBaseDeDatos baseDeDatosPrueba = new ManejadorBaseDeDatos();
        Connection resultado = baseDeDatosPrueba.conectarBaseDeDatos();
        assertNotNull(resultado);
    }
    
    @Test
    public void pruebaValidarConectarBaseDeDatosSinConexionProfesorExitosa(){
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentapruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Profesor");
        UsuarioSingleton usuario = UsuarioSingleton.getInstancia(usuarioPrueba);
        ManejadorBaseDeDatos baseDeDatosPrueba = new ManejadorBaseDeDatos();
        int resultado = baseDeDatosPrueba.validarConectarBaseDeDatos();
        int resultadoEsperado = -1;
        assertEquals(resultadoEsperado,resultado);
    }
    
    @Test
    public void pruebaValidarConectarBaseDeDatosSinConexionAdministradorExitosa(){
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentaadmin@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        UsuarioSingleton usuario = UsuarioSingleton.getInstancia(usuarioPrueba);
        ManejadorBaseDeDatos baseDeDatosPrueba = new ManejadorBaseDeDatos();
        int resultado = baseDeDatosPrueba.validarConectarBaseDeDatos();
        int resultadoEsperado = -1;
        assertEquals(resultadoEsperado,resultado);
    }
    
    @Test
    public void pruebaValidarConectarBaseDeDatosSinUsuarioProfesorExitosa(){
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentapruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Profesor");
        UsuarioSingleton usuario = UsuarioSingleton.getInstancia(usuarioPrueba);
        ManejadorBaseDeDatos baseDeDatosPrueba = new ManejadorBaseDeDatos();
        int resultado = baseDeDatosPrueba.validarConectarBaseDeDatos();
        int resultadoEsperado = 0;
        assertEquals(resultadoEsperado,resultado);
    }
    
    @Test
    public void pruebaValidarConectarBaseDeDatosSinUsuarioAdministradorExitosa(){
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentaadmin@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        UsuarioSingleton usuario = UsuarioSingleton.getInstancia(usuarioPrueba);
        ManejadorBaseDeDatos baseDeDatosPrueba = new ManejadorBaseDeDatos();
        int resultado = baseDeDatosPrueba.validarConectarBaseDeDatos();
        int resultadoEsperado = 0;
        assertEquals(resultadoEsperado,resultado);
    }
    
    @Test
    public void pruebaFalloConectarBaseDeDatosSinConexionLoggerExitosa(){
        Usuario logger = new Usuario();
        logger.setTipoDeUsuario("Logger");
        ManejadorBaseDeDatos baseDeDatosPrueba = new ManejadorBaseDeDatos();
        Connection resultado = baseDeDatosPrueba.conectarBaseDeDatosLogger(logger);
        assertNull(resultado);
    }
}
