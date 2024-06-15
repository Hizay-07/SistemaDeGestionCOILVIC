package pruebaLogicaDeNegocio.DAOImplementacion;

import logicaDeNegocio.DAOImplementacion.DAOProfesorImplementacion;
import logicaDeNegocio.clases.Profesor;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.enums.EnumProfesor;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class PruebaDAOProfesorImplementacionSinConexionExitosa {
    
    @Test
    public void pruebaRegistrarProfesorSinConexionExitosa() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        Profesor profesor = new Profesor();
        profesor.setNombre("Alberto");
        profesor.setApellidoPaterno("Cruz");
        profesor.setApellidoMaterno("Nava");
        profesor.setCorreo("profesorpruebauno@gmail.com");
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(2);
        profesor.setUsuario(usuario);
        int resultado = dao.registrarProfesor(profesor);
        assertEquals(-1, resultado);
    }
    
    @Test
    public void pruebaCambiarEstadoProfesorSinConexionExitosa(){
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        int idProfesor = 1;
        String nuevoEstado = EnumProfesor.Activo.toString();
        int resultado = dao.cambiarEstadoProfesor(idProfesor, nuevoEstado);
        assertEquals(-1, resultado);
    }
    
    @Test
    public void pruebaModificarNombreProfesorSinConexionExitosa(){
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();       
        String nuevoNombre = "Beto";
        String correoProfesor = "profesorpruebauno@gmail.com";
        int resultado = dao.modificarNombreProfesor(nuevoNombre, correoProfesor);
        assertEquals(-1, resultado);
    }
    
    @Test
    public void pruebaModificarApellidoPaternoProfesorSinConexionExitosa(){
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String nuevoApellidoPaterno = "Gómez";
        String correoProfesor = "profesorpruebauno@gmail.com";
        int resultado = dao.modificarApellidoPaternoProfesor(nuevoApellidoPaterno, correoProfesor);
        assertEquals(-1, resultado);
    }
    
    @Test
    public void pruebaModificarApellidoMaternoProfesorSinConexionExitosa(){
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String nuevoApellidoMaterno = "Pérez";
        String correoProfesor = "profesorpruebauno@gmail.com";
        int resultado = dao.modificarApellidoMaternoProfesor(nuevoApellidoMaterno, correoProfesor);
        assertEquals(-1, resultado);
    }
    
    @Test
    public void pruebaModificarCorreoProfesorSinConexionExitosa(){
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String nuevoCorreo = "profesorpruebaunocambio@gmail.com";
        String correoProfesor = "profesorpruebauno@gmail.com";
        int resultado = dao.modificarCorreoProfesor(nuevoCorreo, correoProfesor);
        assertEquals(-1, resultado);
    }
    
    @Test
    public void pruebaObtenerIdProfesorPorCorreoSinConexionExitosa(){
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String correo = "profesorpruebaunocambio@gmail.com";
        int idProfesor = dao.obtenerIdProfesorPorCorreo(correo);
        assertEquals(-1, idProfesor);
    }
    
    @Test
    public void pruebaAsignarUsuarioDeProfesorSinConexionExitosa(){
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String correo = "profesorpruebaunocambio@gmail.com"; 
        int resultado = dao.asignarUsuarioDeProfesorPorCorreo(correo);
        assertEquals(-1, resultado);
    }
    
    @Test
    public void pruebaObetenerProfesorPorIdUsuarioSinConexionExitosa(){
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        int idUsuario = 2;
        Usuario logger = new Usuario();
        Profesor profesor = dao.obtenerProfesorPorIdUsuario(idUsuario, logger);
        assertNull(profesor);
    }
    
    @Test
    public void pruebaValidarDuplicidadDeCorreoSinConexionExitosa(){
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String correo = "profesorpruebaunocambio@gmail.com";
        int coincidencias = dao.validarDuplicidadDeCorreo(correo);
        assertEquals(-1, coincidencias);
    }
    
    @Test
    public void pruebaConsultarPrecondicionInicioColaboracionPorIdProfesorSinConexionExitosa(){
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        int idProfesor = 1;
        int resultado = dao.consultarPrecondicionInicioColaboracionPorIdProfesor(idProfesor);
        assertEquals(-1, resultado);
    }
              
}
