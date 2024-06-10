package pruebaLogicaDeNegocio.Clases;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import logicaDeNegocio.clases.Usuario;
import org.junit.Test;

public class PruebaUsuario {

    @Test
    public void pruebaSetIdUsuarioExitoso(){
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setIdUsuario(123); 
        assertNotNull(usuarioPrueba.getIdUsuario());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaSetIdUsuarioInvalido(){
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setIdUsuario(-1); 
        assertNull(usuarioPrueba.getIdUsuario()); 
    }
    
    @Test
    public void pruebaSetNombreUsuarioExitoso(){
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("usuario.prueba@gmail.com");
        assertNotNull(usuarioPrueba.getNombreUsuario());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaSetNombreUsuarioInvalido(){
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("usuaeio22.mx"); 
        assertNull(usuarioPrueba.getNombreUsuario()); 
    }
    
    @Test 
    public void pruebaSetContraseniaExitoso(){
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setContrasenia("P@ssw0rd!?");
        assertNotNull(usuarioPrueba.getContrasenia());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaSetContraseniaInvalido(){
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setContrasenia("password"); 
        assertNotNull(usuarioPrueba.getContrasenia()); 
    }
    
    @Test
    public void pruebaSetTipoDeUsuarioExitoso(){
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setTipoDeUsuario("Administrador"); 
        assertNotNull(usuarioPrueba.getTipoDeUsuario());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaSetTipoDeUsuarioInvalido(){
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setTipoDeUsuario("Admin*"); 
        assertNull(usuarioPrueba.getTipoDeUsuario()); 
    }   
}
