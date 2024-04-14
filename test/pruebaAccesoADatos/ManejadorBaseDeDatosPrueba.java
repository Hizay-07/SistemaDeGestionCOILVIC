package pruebaAccesoADatos;

import accesoADatos.ManejadorBaseDeDatos;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.Test;
import logicaDeNegocio.clases.Usuario;
import static org.junit.Assert.assertNotNull;

public class ManejadorBaseDeDatosPrueba{
    
    @Test
    public void pruebaConectarBaseDeDatos()throws SQLException{
        Usuario usuario = new Usuario();
        ManejadorBaseDeDatos baseDeDatosPrueba = new ManejadorBaseDeDatos();
        usuario.setNombreUsuario("Equipo2");
        usuario.setContrasenia("contrasena123");
        usuario.setTipoDeUsuario("Administrativo");
        Connection resultado = baseDeDatosPrueba.conectarBaseDeDatos(usuario);
        assertNotNull(resultado);
        resultado.close();
    }
    
    @Test
    public void pruebaFalloConectarBaseDeDatos()throws SQLException{
        Usuario usuario = new Usuario();
        ManejadorBaseDeDatos baseDeDatosPrueba = new ManejadorBaseDeDatos();
        usuario.setNombreUsuario("Equipo4");
        usuario.setContrasenia("contrasena123");
        usuario.setTipoDeUsuario("Administrativo");
        Connection resultado = baseDeDatosPrueba.conectarBaseDeDatos(usuario);
        assertNotNull(resultado);
        resultado.close();
    }
    
    @Test
    public void pruebaGetConexionExitosa() throws SQLException{
        ManejadorBaseDeDatos baseDeDatosPrueba = new ManejadorBaseDeDatos();
        Connection resultado = baseDeDatosPrueba.getConexion();
        assertNotNull(resultado);
        resultado.close();
    }
}
