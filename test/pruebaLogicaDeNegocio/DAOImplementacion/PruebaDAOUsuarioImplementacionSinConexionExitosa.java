package pruebaLogicaDeNegocio.DAOImplementacion;

import logicaDeNegocio.DAOImplementacion.DAOUsuarioImplementacion;
import logicaDeNegocio.clases.Profesor;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.BeforeClass;
import org.junit.Test;

public class PruebaDAOUsuarioImplementacionSinConexionExitosa {
    
    @BeforeClass
    public static void inicializar() {
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentapruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        UsuarioSingleton.getInstancia(usuarioPrueba);
    }
         
    @Test
    public void pruebaRegistrarUsuarioSinConexionExitosa(){
        Usuario usuarioPrueba = new Usuario();
        DAOUsuarioImplementacion implementacion = new DAOUsuarioImplementacion();
        usuarioPrueba.setNombreUsuario("profesorpruebados@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Profesor");
        int resultado = implementacion.registrarUsuario(usuarioPrueba);
        assertEquals(-1, resultado);                            
    }
    
    @Test
    public void pruebaValidarCredencialesSinConexionExitosa(){
        Usuario usuarioExistente = new Usuario();
        usuarioExistente.setNombreUsuario("profesorpruebauno@gmail.com");
        usuarioExistente.setContrasenia("Contrasenia123*");
        DAOUsuarioImplementacion dao = new DAOUsuarioImplementacion();
        Usuario logger=new Usuario();
        logger.setTipoDeUsuario("Logger");
        int resultadoValidacion = dao.validarCredenciales(usuarioExistente, logger);
        assertEquals(-1, resultadoValidacion);    
    }
    
    @Test
    public void pruebaObtenerTipoDeUsuarioSinConexionExitosa(){
        Usuario usuarioPrueba = new Usuario();
        Usuario logger = new Usuario();
        logger.setTipoDeUsuario("Logger");
        DAOUsuarioImplementacion implementacion = new DAOUsuarioImplementacion();
        usuarioPrueba.setNombreUsuario("profesorpruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");        
        String resultado = implementacion.obtenerTipoDeUsuario(usuarioPrueba,logger);
        assertEquals("Excepcion", resultado);        
    }
    
    @Test
    public void pruebaObtenerIdUsuarioSinConexionExitosa(){
        Usuario usuarioPrueba = new Usuario();
        Usuario logger = new Usuario();
        DAOUsuarioImplementacion implementacion = new DAOUsuarioImplementacion();
        logger.setTipoDeUsuario("Logger");
        usuarioPrueba.setNombreUsuario("profesorpruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");            
        int resultado = implementacion.obtenerIdUsuario(usuarioPrueba,logger);
        assertEquals(-1,resultado);        
    }
    
    @Test
    public void pruebaConfirmarConexionDeInicioDeSesionFallida() {
        Usuario logger = new Usuario();
        DAOUsuarioImplementacion dao = new DAOUsuarioImplementacion();
        boolean resultadoConfirmacion = dao.confirmarConexionDeInicioDeSesion(logger);
        assertFalse(resultadoConfirmacion);
    }
    
    @Test
    public void pruebaConfirmarConexionDeUsuarioFallida(){
        DAOUsuarioImplementacion daoUsuario=new DAOUsuarioImplementacion();
        int resultadoObtenido = daoUsuario.confirmarConexionDeUsuario();
        assertEquals(-1,resultadoObtenido);       
    }
    
    @Test
    public void pruebaVerificarDuplicidadNombreDeUsuarioSinConexionExitosa(){
        DAOUsuarioImplementacion daoUsuario=new DAOUsuarioImplementacion();
        int valorEsperado=-1;
        int valorObtenido=daoUsuario.verificarDuplicidadNombreDeUsuario("profesorpruebados@gmail.com");
        assertEquals(valorEsperado,valorObtenido);          
    }
    
    @Test
    public void pruebaActualizarUsuarioPorIdUsuarioSinConexionExitosa(){
        DAOUsuarioImplementacion daoUsuario=new DAOUsuarioImplementacion();
        Profesor profesor=new Profesor();
        Usuario usuario=new Usuario();
        usuario.setIdUsuario(1);        
        profesor.setUsuario(usuario);
        profesor.setCorreo("profesorpruebados@gmail.com");
        String contrasena="ContraseniaNueva123*";
        int resultadoEsperado=-1;
        int resultadoObtenido=daoUsuario.actualizarUsuarioPorIdUsuario(profesor, contrasena);
        assertEquals(resultadoEsperado,resultadoObtenido);          
    }  
    
}
