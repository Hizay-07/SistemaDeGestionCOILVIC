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
        usuarioPrueba.setNombreUsuario("Equipo2");
        usuarioPrueba.setContrasenia("contrasena123");
        
        int resultado = implementacion.registrarUsuario(usuarioPrueba);
        assertEquals(1,resultado);
    }
    
    @Test
    public void pruebaFlujoFallidoRegistrarUsuarioExitosa(){
        Usuario usuarioPrueba = new Usuario();
        DAOUsuarioImplementacion implementacion = new DAOUsuarioImplementacion();
        usuarioPrueba.setNombreUsuario("Equipo2");
        usuarioPrueba.setContrasenia("contrasena123");
        
        int resultado = implementacion.registrarUsuario(usuarioPrueba);
        assertEquals(0, resultado);
    }
    
    @Test
    public void pruebaValidarCredencialesExitosa(){
        Usuario usuarioPrueba = new Usuario();
        DAOUsuarioImplementacion implementacion = new DAOUsuarioImplementacion();
        usuarioPrueba.setNombreUsuario("ChrisVZ120904");
        usuarioPrueba.setContrasenia("contrasena123");
        
        boolean resultado = implementacion.validarCredenciales(usuarioPrueba);
        assertTrue(resultado);
    }
    
    @Test
    public void pruebaFlujoFallidoValidarCredencialesExitosa(){
        Usuario usuarioPrueba = new Usuario();
        DAOUsuarioImplementacion implementacion = new DAOUsuarioImplementacion();
        usuarioPrueba.setNombreUsuario("pepito123");
        usuarioPrueba.setContrasenia("contrasena123");
        
        boolean resultado = implementacion.validarCredenciales(usuarioPrueba);
        assertFalse(resultado);
    }
}
