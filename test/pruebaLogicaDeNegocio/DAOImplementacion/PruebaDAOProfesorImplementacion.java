package pruebaLogicaDeNegocio.DAOImplementacion;

import logicaDeNegocio.DAOImplementacion.DAOProfesorImplementacion;
import logicaDeNegocio.enums.EnumProfesor;
import logicaDeNegocio.clases.Profesor;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;
import logicaDeNegocio.enums.EnumTipoDeUsuario;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;

public class PruebaDAOProfesorImplementacion {
    
    @Before
    public void setUp() {
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentapruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        UsuarioSingleton.getInstancia(usuarioPrueba);
    }

    @Test
    public void pruebaRegistrarProfesorExitosa() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        Profesor profesor = new Profesor();
        profesor.setNombre("Lizbeth");
        profesor.setApellidoPaterno("Rodriguez");
        profesor.setApellidoMaterno("Gomez");
        profesor.setCorreo("lizbeth@gmail.com");
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(13);
        profesor.setUsuario(usuario);
        int resultado = dao.registrarProfesor(profesor);
        assertEquals(1, resultado);
    }
     
   @Test(expected = IllegalArgumentException.class)
    public void pruebaFallidaRegistrarProfesor() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        Profesor profesor = new Profesor();
        profesor.setNombre("");
        int resultado = dao.registrarProfesor(profesor);
    }

    @Test
    public void pruebaCambiarEstadoProfesorExitosa() {
        DAOProfesorImplementacion daoProfesor = new DAOProfesorImplementacion();
        int idProfesor = 1; 
        String nuevoEstado = EnumProfesor.Activo.toString(); 
        int resultado = daoProfesor.cambiarEstadoProfesor(idProfesor, nuevoEstado);
        assertEquals(1, resultado);
    }
    
    @Test
    public void pruebaFallidaCambiarEstadoProfesor() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        int idProfesor = 999;
        String nuevoEstado = EnumProfesor.Activo.toString(); 
        int resultado = dao.cambiarEstadoProfesor(idProfesor, nuevoEstado);
        assertEquals(0, resultado);
    }

    @Test
    public void pruebaModificarNombreProfesorExitosa() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String nuevoNombre = "RogelioJos";
        String correoProfesor = "regelio@gmail.com";
        int resultado = dao.modificarNombreProfesor(nuevoNombre, correoProfesor);
        assertEquals(1, resultado);
    }
    
    @Test
    public void pruebaFallidaModificarNombreProfesor() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String nuevoNombre = "Pedro";
        String correoProfesor = "correoInvalido@example.com";
        int resultado = dao.modificarNombreProfesor(nuevoNombre, correoProfesor);
        assertEquals(0, resultado);
    }

    @Test
    public void pruebaModificarApellidoPaternoProfesorExitosa() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String nuevoApellidoPaterno = "Gomez";
        String correoProfesor = "jose@example.com";
        int resultado = dao.modificarApellidoPaternoProfesor(nuevoApellidoPaterno, correoProfesor);
        assertEquals(1, resultado);
    }

    
    @Test
    public void pruebaFallidaModificarApellidoPaternoProfesor() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String nuevoApellidoPaterno = "Gonzalez";
        String correoProfesor = "correoInvalido@example.com";
        int resultado = dao.modificarApellidoPaternoProfesor(nuevoApellidoPaterno, correoProfesor);
        assertEquals(0, resultado);
    }

    @Test
    public void pruebaModificarApellidoMaternoProfesorExitoso() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String apellidoMaternoActualizado = "Camacho";
        String correoProfesor = "jose@example.com";
        int resultadoEsperado = 1;
        int resultadoObtenido = dao.modificarApellidoMaternoProfesor(apellidoMaternoActualizado, correoProfesor);
        assertEquals(resultadoEsperado, resultadoObtenido);
    }

    @Test
    public void pruebaFallidaModificarApellidoMaternoProfesor() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String nuevoApellidoMaterno = "Hernandez";
        String correoProfesor = "correoInvalido@example.com";
        int resultado = dao.modificarApellidoMaternoProfesor(nuevoApellidoMaterno, correoProfesor);
        assertEquals(0, resultado);
    }
    
    @Test
    public void pruebaModificarCorreoProfesorExitosa() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String nuevoCorreo = "vera@example.com";
        String correoProfesor = "edgar@gmail.com";
        int resultado = dao.modificarCorreoProfesor(nuevoCorreo, correoProfesor);
        assertEquals(1, resultado);
    }
    
    @Test
    public void pruebaFallidaModificarCorreoProfesor() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String nuevoCorreo = "pedro@example.com";
        String correoProfesor = "correoInvalido@example.com";
        int resultado = dao.modificarCorreoProfesor(nuevoCorreo, correoProfesor);
        assertEquals(0, resultado);
    }
    
    @Test
    public void pruebaObtenerIdProfesorPorCorreoExitosa() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String correo = "regelio@gmail.com";
        int idProfesor = dao.obtenerIdProfesorPorCorreo(correo);
        assertEquals(1, idProfesor);
    }
    
   @Test
    public void pruebaFallidaObtenerIdProfesorPorCorreo() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String correo = "correoInvalido@example.com";
        int idProfesor = dao.obtenerIdProfesorPorCorreo(correo);
        assertEquals(0, idProfesor);
    }
    
    @Test
    public void pruebaAsignarUsuarioDeProfesorPorCorreoExitosa() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String correo = "oscar@example.com"; 
        int resultado = dao.asignarUsuarioDeProfesorPorCorreo(correo);
        assertEquals(1, resultado);
    }
    
    @Test
    public void pruebaFallidaAsignarUsuarioDeProfesorPorCorreo() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String correo = "correoInvalido@example.com";
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
    public void pruebaFallidaObtenerProfesorPorIdUsuario() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        int idUsuario = 999; 
        Usuario logger = new Usuario(); 
        Profesor profesor = dao.obtenerProfesorPorIdUsuario(idUsuario, logger);
        assertNotNull(profesor);
    }

    @Test
    public void pruebaValidarDuplicidadDeCorreoExitosa() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String correo = "jose@example.com";
        int coincidencias = dao.validarDuplicidadDeCorreo(correo);
        assertEquals(1, coincidencias);
    }
    
    @Test (expected = AssertionError.class)
    public void pruebaFallidaValidarDuplicidadDeCorreo() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String correo = "Correo-Invalido@example.com";
        int coincidencias = dao.validarDuplicidadDeCorreo(correo);
        assertEquals(-1, coincidencias);
    }

    @Test
    public void pruebaConsultarPrecondicionInicioColaboracionPorIdProfesorExitosa() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        int idProfesor = 2; 
        int resultado = dao.consultarPrecondicionInicioColaboracionPorIdProfesor(idProfesor);
        assertTrue(resultado > 0);
    }
    
    @Test
    public void pruebaFallidaConsultarPrecondicionInicioColaboracionPorIdProfesor() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        int idProfesor = 2; 
        int resultado = dao.consultarPrecondicionInicioColaboracionPorIdProfesor(idProfesor);
        assertTrue(resultado < 0);
    }
    
}
