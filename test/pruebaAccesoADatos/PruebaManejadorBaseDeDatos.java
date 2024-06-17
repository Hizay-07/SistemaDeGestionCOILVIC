package pruebaAccesoADatos;

import accesoADatos.ManejadorBaseDeDatos;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.Test;
import logicaDeNegocio.clases.UsuarioSingleton;
import logicaDeNegocio.clases.Usuario;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class PruebaManejadorBaseDeDatos{
    
    @Test
    public void pruebaConectarBaseDeDatosAdministrativoExitosa() throws SQLException{
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentaadmin@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        UsuarioSingleton usuario = UsuarioSingleton.getInstancia(usuarioPrueba);
        ManejadorBaseDeDatos baseDeDatosPrueba = new ManejadorBaseDeDatos();
        Connection resultado = baseDeDatosPrueba.conectarBaseDeDatos();
        assertNull(resultado);
        resultado.close();
    }
    
    @Test
    public void pruebaConectarBaseDeDatosProfesorExitosa() throws SQLException{
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentapruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Profesor");
        UsuarioSingleton usuario = UsuarioSingleton.getInstancia(usuarioPrueba);
        ManejadorBaseDeDatos baseDeDatosPrueba = new ManejadorBaseDeDatos();
        Connection resultado = baseDeDatosPrueba.conectarBaseDeDatos();
        assertNull(resultado);
        resultado.close();
    }
    
    @Test
    public void pruebaValidarConectarBaseDeDatosProfesorExitosa(){
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentapruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Profesor");
        UsuarioSingleton usuario = UsuarioSingleton.getInstancia(usuarioPrueba);
        ManejadorBaseDeDatos baseDeDatosPrueba = new ManejadorBaseDeDatos();
        int resultado = baseDeDatosPrueba.validarConectarBaseDeDatos();
        int resultadoEsperado = 1;
        assertEquals(resultadoEsperado,resultado);
    }
    
    @Test
    public void pruebaValidarConectarBaseDeDatosAdministradorExitosa(){
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentaadmin@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        UsuarioSingleton usuario = UsuarioSingleton.getInstancia(usuarioPrueba);
        ManejadorBaseDeDatos baseDeDatosPrueba = new ManejadorBaseDeDatos();
        int resultado = baseDeDatosPrueba.validarConectarBaseDeDatos();
        int resultadoEsperado = 1;
        assertEquals(resultadoEsperado,resultado);
    }
    
    @Test
    public void pruebaConectarBaseDeDatosLoggerExitosa(){
        Usuario logger = new Usuario();
        logger.setTipoDeUsuario("Logger");
        ManejadorBaseDeDatos baseDeDatosPrueba = new ManejadorBaseDeDatos();
        Connection resultado = baseDeDatosPrueba.conectarBaseDeDatosLogger(logger);
        assertNull(resultado);
    }
   
}
