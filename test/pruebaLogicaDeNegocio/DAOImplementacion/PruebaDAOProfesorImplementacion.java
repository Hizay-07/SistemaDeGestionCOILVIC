package pruebaLogicaDeNegocio.DAOImplementacion;

import logicaDeNegocio.DAOImplementacion.DAOProfesorImplementacion;
import logicaDeNegocio.enums.EnumProfesor;
import logicaDeNegocio.clases.Profesor;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotEquals;
import org.junit.BeforeClass;

public class PruebaDAOProfesorImplementacion {
    
    @BeforeClass
    public static void inicializar() {
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentaadmin@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        UsuarioSingleton.getInstancia(usuarioPrueba);
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        Profesor profesor = new Profesor();
        profesor.setNombre("Jose");
        profesor.setApellidoPaterno("Morelos");
        profesor.setApellidoMaterno("Pavón");
        profesor.setCorreo("profesorpruebauno@gmail.com");                        
        dao.registrarProfesor(profesor);                
    }                   
                
    @Test
    public void pruebaObtenerIdProfesorPorCorreoExitosa() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String correo = "profesorpruebaunocambio@gmail.com";
        int idProfesor = dao.obtenerIdProfesorPorCorreo(correo);
        assertEquals(1, idProfesor);
    }
    
   @Test
    public void pruebaObtenerIdProfesorPorCorreoFallida() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String correo = "correoInvalido@example.com";
        int idProfesor = dao.obtenerIdProfesorPorCorreo(correo);
        assertEquals(0, idProfesor);
    }        
    
    @Test
    public void pruebaConsultarProfesorPorIdExitosa(){
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        Profesor profesorObtenido = dao.consultarProfesorPorId(1);
        Profesor profesorEsperado = new Profesor();
        profesorEsperado.setNombre("Jose");
        profesorEsperado.setApellidoPaterno("Morelos");
        profesorEsperado.setApellidoMaterno("Pavón");
        profesorEsperado.setCorreo("profesorpruebauno@gmail.com"); 
        profesorEsperado.setEstado("Activo");
        profesorEsperado.setIdProfesor(1);
        assertEquals(profesorEsperado,profesorObtenido);
    }
    
    @Test
    public void pruebaConsultarProfesorPorIdFallida(){
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        Profesor profesorObtenido = dao.consultarProfesorPorId(5);
        Profesor profesorEsperado = new Profesor();
        profesorEsperado.setNombre("Jose");
        profesorEsperado.setApellidoPaterno("Morelos");
        profesorEsperado.setApellidoMaterno("Pavón");
        profesorEsperado.setCorreo("profesorpruebauno@gmail.com"); 
        profesorEsperado.setEstado("Activo");
        profesorEsperado.setIdProfesor(1);
        assertNotEquals(profesorEsperado,profesorObtenido);
    }
    
    @Test
    public void pruebaAsignarUsuarioDeProfesorPorCorreoExitosa() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String correo = "profesorpruebauno@gmail.com"; 
        int resultado = dao.asignarUsuarioDeProfesorPorCorreo(correo);
        assertEquals(1, resultado);
    }
    
    @Test
    public void pruebaAsignarUsuarioDeProfesorPorCorreoFallida() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String correo = "correoinvalido@example.com";
        int resultado = dao.asignarUsuarioDeProfesorPorCorreo(correo);
        assertEquals(-1, resultado);
    }    
    
    @Test
    public void pruebaObtenerProfesorPorIdUsuarioExitosa() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        int idUsuario = 2;
        Usuario logger = new Usuario(); 
        Profesor profesor = dao.obtenerProfesorPorIdUsuario(idUsuario, logger);
        assertNotNull(profesor);
    }
    
    @Test
    public void pruebaObtenerProfesorPorIdUsuarioFallida() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        int idUsuario = 999; 
        Usuario logger = new Usuario(); 
        logger.setTipoDeUsuario("Logger");
        Profesor profesor = dao.obtenerProfesorPorIdUsuario(idUsuario, logger);
        Profesor profesorEsperado = new Profesor();
        profesorEsperado.setNombre("Sin coincidencias");
        assertEquals(profesorEsperado.getNombre(),profesor.getNombre());
    }        

    @Test
    public void pruebaValidarDuplicidadDeCorreoExitosa() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String correo = "profesorpruebauno@gmail.com";
        int coincidencias = dao.validarDuplicidadDeCorreo(correo);
        assertEquals(1, coincidencias);
    }
    
    @Test (expected = AssertionError.class)
    public void pruebaValidarDuplicidadDeCorreoFallida() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String correo = "Correo-Invalido@example.com";
        int coincidencias = dao.validarDuplicidadDeCorreo(correo);
        assertEquals(-1, coincidencias);
    }          

    @Test
    public void pruebaConsultarPrecondicionInicioColaboracionPorIdProfesorExitosa() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        int idProfesor = 1; 
        int resultado = dao.consultarPrecondicionInicioColaboracionPorIdProfesor(idProfesor);
        assertTrue(resultado > 0);
    }
    
    @Test
    public void pruebaConsultarPrecondicionInicioColaboracionPorIdProfesorFallida() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        int idProfesor = 1; 
        int resultado = dao.consultarPrecondicionInicioColaboracionPorIdProfesor(idProfesor);
        assertTrue(resultado < 0);
    } 
    
    @Test
    public void pruebaCambiarEstadoProfesorExitosa() {
        DAOProfesorImplementacion daoProfesor = new DAOProfesorImplementacion();
        int idProfesor = 1; 
        String nuevoEstado = EnumProfesor.Esperando.toString(); 
        int resultado = daoProfesor.cambiarEstadoProfesor(idProfesor, nuevoEstado);
        assertEquals(1, resultado);
    }
    
    @Test
    public void pruebaCambiarEstadoProfesorFallida() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        int idProfesor = 999;
        String nuevoEstado = EnumProfesor.Activo.toString(); 
        int resultado = dao.cambiarEstadoProfesor(idProfesor, nuevoEstado);
        assertEquals(0, resultado);
    }        
    
    @Test
    public void pruebaModificarNombreProfesorExitosa() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String nuevoNombre = "Beto";
        String correoProfesor = "profesorpruebauno@gmail.com";
        int resultado = dao.modificarNombreProfesor(nuevoNombre, correoProfesor);
        assertEquals(1, resultado);
    }
    
    @Test
    public void pruebaModificarNombreProfesorFallida() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String nuevoNombre = "Pedro";
        String correoProfesor = "correoInvalido@example.com";
        int resultado = dao.modificarNombreProfesor(nuevoNombre, correoProfesor);
        assertEquals(0, resultado);
    }        

    @Test
    public void pruebaModificarApellidoPaternoProfesorExitosa() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String nuevoApellidoPaterno = "Gómez";
        String correoProfesor = "profesorpruebauno@gmail.com";
        int resultado = dao.modificarApellidoPaternoProfesor(nuevoApellidoPaterno, correoProfesor);
        assertEquals(1, resultado);
    }
    
    @Test
    public void pruebaModificarApellidoPaternoProfesorFallida() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String nuevoApellidoPaterno = "Pérez";
        String correoProfesor = "correoInvalido@example.com";
        int resultado = dao.modificarApellidoPaternoProfesor(nuevoApellidoPaterno, correoProfesor);
        assertEquals(0, resultado);
    }        

    @Test
    public void pruebaModificarApellidoMaternoProfesorExitoso() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String apellidoMaternoActualizado = "Pérez";
        String correoProfesor = "profesorpruebaunocambio@gmail.com";
        int resultadoEsperado = 1;
        int resultadoObtenido = dao.modificarApellidoMaternoProfesor(apellidoMaternoActualizado, correoProfesor);
        assertEquals(resultadoEsperado, resultadoObtenido);
    }

    @Test
    public void pruebaModificarApellidoMaternoProfesorFallida() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String nuevoApellidoMaterno = "Hernandez";
        String correoProfesor = "correoInvalido@example.com";
        int resultado = dao.modificarApellidoMaternoProfesor(nuevoApellidoMaterno, correoProfesor);
        assertEquals(0, resultado);
    }
        
    @Test
    public void pruebaModificarCorreoProfesorExitosa() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String nuevoCorreo = "profesorpruebaunocambio@gmail.com";
        String correoProfesor = "profesorpruebauno@gmail.com";
        int resultado = dao.modificarCorreoProfesor(nuevoCorreo, correoProfesor);
        assertEquals(1, resultado);
    }
    
    @Test
    public void pruebaModificarCorreoProfesorFallida() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String nuevoCorreo = "pedro@example.com";
        String correoProfesor = "correoinvalido@example.com";
        int resultado = dao.modificarCorreoProfesor(nuevoCorreo, correoProfesor);
        assertEquals(0, resultado);
    }
    
    @Test
    public void pruebaRegistrarProfesorExitosa() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        Profesor profesor = new Profesor();
        profesor.setNombre("Miguel");
        profesor.setApellidoPaterno("Gómez");
        profesor.setApellidoMaterno("Canuas");
        profesor.setCorreo("profesorpruebados@gmail.com");                        
        int resultado = dao.registrarProfesor(profesor);
        assertEquals(1, resultado);
    }
     
   @Test(expected = IllegalArgumentException.class)
    public void pruebaRegistrarProfesorFallida() {        
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        Profesor profesor = new Profesor();
        profesor.setNombre(null);
        profesor.setApellidoPaterno(null);
        profesor.setApellidoMaterno(null);
        profesor.setCorreo(null);
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        profesor.setUsuario(usuario);
        int resultado = dao.registrarProfesor(profesor);
        assertEquals(-1, resultado);
    } 
    
    @AfterClass
    public static void configurarProfesor(){
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String nuevoCorreo="profesorpruebauno@gmail.com"; 
        String correoProfesor="profesorpruebaunocambio@gmail.com";
        dao.modificarCorreoProfesor(nuevoCorreo, correoProfesor);            
    }    
}
