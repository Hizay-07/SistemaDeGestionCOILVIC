package pruebaLogicaDeNegocio.DAOImplementacion;

import logicaDeNegocio.DAOImplementacion.DAOUsuarioImplementacion;
import logicaDeNegocio.clases.Usuario;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PruebaDAOUsuarioImplementacion {
    
    @Test
    public void pruebaRegistrarUsuarioExitosa(){
        Usuario usuarioPrueba = new Usuario();
        DAOUsuarioImplementacion implementacion = new DAOUsuarioImplementacion();
        usuarioPrueba.setNombreUsuario("ChrisVZ120904");
        usuarioPrueba.setContrasenia("cristianoRonaldo777");
        
        int resultado = implementacion.registrarUsuario(usuarioPrueba);
        assertEquals(1,resultado);
    }
    
    @Test
    public void pruebaValidarCredencialesExitosa(){
        Usuario usuarioPrueba = new Usuario();
        DAOUsuarioImplementacion implementacion = new DAOUsuarioImplementacion();
        usuarioPrueba.setNombreUsuario("ChrisVZ120904");
        usuarioPrueba.setContrasenia("cristianoRonaldo777");
        
        boolean resultado = implementacion.validarCredenciales(usuarioPrueba);
        assertTrue(resultado);
    }
}
