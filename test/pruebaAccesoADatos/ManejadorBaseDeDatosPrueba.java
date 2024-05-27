package pruebaAccesoADatos;

import accesoADatos.ManejadorBaseDeDatos;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import org.junit.Test;
import logicaDeNegocio.clases.UsuarioSingleton;
import logicaDeNegocio.clases.Usuario;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class ManejadorBaseDeDatosPrueba{
    
    @Test
    public void pruebaConectarBaseDeDatosExitosa()throws SQLException{
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentapruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        UsuarioSingleton usuario = UsuarioSingleton.getInstancia(usuarioPrueba);
        ManejadorBaseDeDatos baseDeDatosPrueba = new ManejadorBaseDeDatos();
        Connection resultado = baseDeDatosPrueba.conectarBaseDeDatos();
        assertNotNull(resultado);
        resultado.close();
    }
    
    @Test (expected = SQLSyntaxErrorException.class)
    public void pruebaFalloConectarBaseDeDatosExitosa()throws SQLException{
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("CuentaPruebaUno");
        usuarioPrueba.setContrasenia("Contrasena123*");
        usuarioPrueba.setTipoDeUsuario("Profesor");
        UsuarioSingleton usuario = UsuarioSingleton.getInstancia(usuarioPrueba);
        ManejadorBaseDeDatos baseDeDatosPrueba = new ManejadorBaseDeDatos();
        Connection resultado = baseDeDatosPrueba.conectarBaseDeDatos();
        assertNotNull(resultado);
        resultado.close();
    }
    
    @Test
    public void pruebaConectarBaseDeDatosLoggerExitosa(){
        Usuario logger = new Usuario();
        logger.setTipoDeUsuario("Logger");
        ManejadorBaseDeDatos baseDeDatosPrueba = new ManejadorBaseDeDatos();
        Connection resultado = baseDeDatosPrueba.conectarBaseDeDatosLogger(logger);
        assertNotNull(resultado);
    }
    
    @Test
    public void pruebaFalloConectarBaseDeDatosLoggerExitosa(){
        Usuario logger = new Usuario();
        logger.setTipoDeUsuario("Administrativo");
        ManejadorBaseDeDatos baseDeDatosPrueba = new ManejadorBaseDeDatos();
        Connection resultado = baseDeDatosPrueba.conectarBaseDeDatosLogger(logger);
        assertNull(resultado);
    }
    
    @Test
    public void pruebaGetConexionExitosa() throws SQLException{
        ManejadorBaseDeDatos baseDeDatosPrueba = new ManejadorBaseDeDatos();
        Connection resultado = baseDeDatosPrueba.getConexion();
        assertNotNull(resultado);
        resultado.close();
    }
}
