package pruebaLogicaDeNegocio.DAOImplementacion;

import logicaDeNegocio.DAOImplementacion.DAOUsuarioImplementacion;
import logicaDeNegocio.clases.Profesor;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;

public class PruebaDAOUsuarioImplementacion {
        
    @BeforeClass
    public static void inicializar() {
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
        usuarioPrueba.setNombreUsuario("profesorpruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Profesor");
        int resultado = implementacion.registrarUsuario(usuarioPrueba);
        assertEquals(1, resultado);
    }
    
    @Test
    public void pruebaRegistrarUsuarioFallida() {
        Usuario usuarioPrueba = new Usuario();
        DAOUsuarioImplementacion implementacion = new DAOUsuarioImplementacion();
        usuarioPrueba.setNombreUsuario("profesorpruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Profesor");
        int resultado = implementacion.registrarUsuario(usuarioPrueba);
        assertEquals(0, resultado);
    }        
    
    @Test
    public void pruebaValidarCredencialesExitosa() {
        Usuario usuarioExistente = new Usuario();
        usuarioExistente.setNombreUsuario("profesorpruebauno@gmail.com");
        usuarioExistente.setContrasenia("Contrasenia123*");
        DAOUsuarioImplementacion dao = new DAOUsuarioImplementacion();
        Usuario logger=new Usuario();
        logger.setTipoDeUsuario("Logger");
        int resultadoValidacion = dao.validarCredenciales(usuarioExistente, logger);
        assertEquals(1, resultadoValidacion);
    }
    
    @Test
    public void pruebaValidarCredencialesFallida() {
        Usuario usuarioExistente = new Usuario();
        usuarioExistente.setNombreUsuario("profesorpruebados@gmail.com");
        usuarioExistente.setContrasenia("Contrasenia123*");
        DAOUsuarioImplementacion dao = new DAOUsuarioImplementacion();
        Usuario logger=new Usuario();
        logger.setTipoDeUsuario("Logger");
        int resultadoValidacion = dao.validarCredenciales(usuarioExistente, logger);
        assertEquals(0, resultadoValidacion);
    }        
            
    @Test
    public void pruebaObtenerTipoDeUsuarioExitosa(){
        Usuario usuarioPrueba = new Usuario();
        Usuario logger = new Usuario();
        logger.setTipoDeUsuario("Logger");
        DAOUsuarioImplementacion implementacion = new DAOUsuarioImplementacion();
        usuarioPrueba.setNombreUsuario("profesorpruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");        
        String resultado = implementacion.obtenerTipoDeUsuario(usuarioPrueba,logger);
        assertEquals("Profesor", resultado);
    }
    
    @Test
    public void pruebaObtenerTipoDeUsuarioFallida() {
        Usuario logger = new Usuario();
        logger.setTipoDeUsuario("Logger");
        Usuario usuarioNoExistente = new Usuario();
        usuarioNoExistente.setNombreUsuario("profesorpruebados@gmail.com");
        usuarioNoExistente.setContrasenia("Contrasenia123*");
        DAOUsuarioImplementacion dao = new DAOUsuarioImplementacion();
        String resultadoTipoDeUsuario = dao.obtenerTipoDeUsuario(usuarioNoExistente, logger);
        assertEquals("", resultadoTipoDeUsuario);
    }    
    
    @Test
    public void pruebaObtenerIdUsuarioExitosa(){
        Usuario usuarioPrueba = new Usuario();
        Usuario logger = new Usuario();
        DAOUsuarioImplementacion implementacion = new DAOUsuarioImplementacion();
        logger.setTipoDeUsuario("Logger");
        usuarioPrueba.setNombreUsuario("cuentaadmin@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");            
        int resultado = implementacion.obtenerIdUsuario(usuarioPrueba,logger);
        assertEquals(1,resultado);
    }
    
    @Test
    public void pruebaObtenerIdUsuarioFallida() {
        Usuario usuarioNoExistente = new Usuario();
        usuarioNoExistente.setNombreUsuario("profesorpruebados@gmail.com");
        usuarioNoExistente.setContrasenia("Contrasenia123*");
        Usuario logger = new Usuario();
        logger.setTipoDeUsuario("Logger");
        DAOUsuarioImplementacion dao = new DAOUsuarioImplementacion();
        int resultadoId = dao.obtenerIdUsuario(usuarioNoExistente, logger);
        assertEquals(0, resultadoId);
    }
                
    @Test
    public void pruebaConfirmarConexionDeInicioDeSesionExitosa(){
        Usuario logger = new Usuario();
        logger.setTipoDeUsuario("Logger");
        DAOUsuarioImplementacion dao = new DAOUsuarioImplementacion();
        boolean resultadoConfirmacion = dao.confirmarConexionDeInicioDeSesion(logger);
        assertTrue(resultadoConfirmacion);        
    }            
    
    @Test
    public void pruebaConfirmarConexionDeUsuarioExitosa(){
        DAOUsuarioImplementacion daoUsuario=new DAOUsuarioImplementacion();
        int resultadoObtenido = daoUsuario.confirmarConexionDeUsuario();
        assertEquals(1,resultadoObtenido);
    }            
    
    @Test
    public void pruebaVerificarDuplicidadNombreDeUsuarioExitosa(){
        DAOUsuarioImplementacion daoUsuario=new DAOUsuarioImplementacion();
        int valorEsperado=1;
        int valorObtenido=daoUsuario.verificarDuplicidadNombreDeUsuario("profesorpruebauno@gmail.com");
        assertEquals(valorEsperado,valorObtenido);
    }
    
    @Test
    public void pruebaVerificarDuplicidadNombreDeUsuarioFallida(){
        DAOUsuarioImplementacion daoUsuario=new DAOUsuarioImplementacion();
        int valorEsperado=0;
        int valorObtenido=daoUsuario.verificarDuplicidadNombreDeUsuario("profesorpruebados@gmail.com");
        assertEquals(valorEsperado,valorObtenido);        
    }            
    
    @Test
    public void pruebaActualizarUsuarioPorIdUsuarioExitosa(){
        DAOUsuarioImplementacion daoUsuario=new DAOUsuarioImplementacion();
        Profesor profesor=new Profesor();
        Usuario usuario=new Usuario();
        usuario.setIdUsuario(1);        
        profesor.setUsuario(usuario);
        profesor.setCorreo("cuentaadmin@gmail.com");
        String contrasena="ContraseniaNueva123*";
        int resultadoEsperado=1;
        int resultadoObtenido=daoUsuario.actualizarUsuarioPorIdUsuario(profesor, contrasena);
        assertEquals(resultadoEsperado,resultadoObtenido);        
    }
    
    @Test
    public void pruebaActualizarUsuarioPorIdUsuarioFallida(){
        DAOUsuarioImplementacion daoUsuario=new DAOUsuarioImplementacion();
        Profesor profesor=new Profesor();
        Usuario usuario=new Usuario();
        usuario.setIdUsuario(0);        
        profesor.setUsuario(usuario);
        profesor.setCorreo("profesorpruebados@gmail.com");
        String contrasena="ContraseniaNueva123*";
        int resultadoEsperado=0;
        int resultadoObtenido=daoUsuario.actualizarUsuarioPorIdUsuario(profesor, contrasena);
        assertEquals(resultadoEsperado,resultadoObtenido);        
    } 
    
    @After
    public void configurarUsuarioAdministrativo(){
        DAOUsuarioImplementacion daoUsuario=new DAOUsuarioImplementacion();
        Profesor profesor=new Profesor();
        Usuario usuario=new Usuario();
        usuario.setIdUsuario(1);        
        profesor.setUsuario(usuario);
        profesor.setCorreo("cuentaadmin@gmail.com");
        String contrasena="Contrasenia123*";        
        daoUsuario.actualizarUsuarioPorIdUsuario(profesor, contrasena);        
    }  
}
