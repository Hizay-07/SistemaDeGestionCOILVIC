package pruebaLogicaDeNegocio.DAOImplementacion;

import logicaDeNegocio.DAOImplementacion.DAOProfesorImplementacion;
import logicaDeNegocio.enums.EnumProfesor;
import logicaDeNegocio.clases.Profesor;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.enums.EnumTipoDeUsuario;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class PruebaDAOProfesorImplementacion {

    @Test
    public void pruebaRegistrarProfesorExitoso() {
        DAOProfesorImplementacion daoProfesor = new DAOProfesorImplementacion();
        Profesor profesor = new Profesor();
        profesor.setNombre("Martin");
        profesor.setApellidoPaterno("Garza");
        profesor.setApellidoMaterno("Garcia");
        profesor.setCorreo("Martin@example.com");
        profesor.setEstado(EnumProfesor.Activo.toString()); 
        int resultadoEsperado = 1;
        int resultadoObtenido = daoProfesor.registrarProfesor(profesor);
        assertEquals(resultadoEsperado, resultadoObtenido);
    }
    
   @Test
    public void pruebaRegistrarProfesorFracaso() {
        DAOProfesorImplementacion daoProfesor = new DAOProfesorImplementacion();
        Profesor profesor = new Profesor();
        int resultadoEsperado = 0; 
        int resultadoObtenido = daoProfesor.registrarProfesor(profesor);
        assertEquals(resultadoEsperado, resultadoObtenido);
    }

    @Test
    public void pruebaCambiarEstadoProfesorExitosa() {
        DAOProfesorImplementacion instancia = new DAOProfesorImplementacion();
        int idProfesor = 1; 
        String nuevoEstado = EnumProfesor.Archivado.toString(); 
        int resultado = instancia.cambiarEstadoProfesor(idProfesor, nuevoEstado);
        assertEquals(1, resultado); 
    }
    
    @Test
    public void pruebaCambiarEstadoProfesorFracaso() {
        DAOProfesorImplementacion instancia = new DAOProfesorImplementacion();
        int idProfesor = 2; 
        String nuevoEstado = EnumProfesor.Archivado.toString(); 
        int resultadoEsperado = 0; 
        int resultado = instancia.cambiarEstadoProfesor(idProfesor, nuevoEstado);
        assertEquals(resultadoEsperado, resultado); 
    }

    @Test
    public void pruebaModificarNombreProfesorExitoso() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String nombreActualizado = "martinn";
        String correoProfesor = "Martin@example.com";
        int resultadoEsperado = 1;
        int resultadoObtenido = dao.modificarNombreProfesor(nombreActualizado, correoProfesor);
        assertEquals(resultadoEsperado, resultadoObtenido);
    }
    
    @Test
    public void pruebaModificarNombreProfesorFracaso() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String nombreActualizado = ""; 
        String correoProfesor = "jose@example.com";
        int resultadoEsperado = 1; 
        int resultadoObtenido = dao.modificarNombreProfesor(nombreActualizado, correoProfesor);
        assertEquals(resultadoEsperado, resultadoObtenido);
    }

    @Test
    public void pruebaModificarApellidoPaternoProfesorExitoso() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String apellidoPaternoActualizado = "Garza";
        String correoProfesor = "Martin@example.com";
        int resultadoEsperado = 1;
        int resultadoObtenido = dao.modificarApellidoPaternoProfesor(apellidoPaternoActualizado, correoProfesor);
        assertEquals(resultadoEsperado, resultadoObtenido);
    }
    
     @Test
    public void pruebaModificarApellidoPaternoProfesorFracaso() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String apellidoPaternoActualizado = "Perez";
        String correoProfesor = "juan@example.com";
        int resultadoEsperado = 0; 
        int resultadoObtenido = dao.modificarApellidoPaternoProfesor(apellidoPaternoActualizado, correoProfesor);
        assertEquals(resultadoEsperado, resultadoObtenido);
    }

    @Test
    public void pruebaModificarApellidoMaternoProfesorExitoso() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String apellidoMaternoActualizado = "Cobos";
        String correoProfesor = "joseJuan@example.com";
        int resultadoEsperado = 1;
        int resultadoObtenido = dao.modificarApellidoMaternoProfesor(apellidoMaternoActualizado, correoProfesor);
        assertEquals(resultadoEsperado, resultadoObtenido);
    }

    @Test
    public void pruebaModificarApellidoMaternoProfesorFracaso() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String apellidoMaternoActualizado = "";
        String correoProfesor = "Martin@example.com";
        int resultadoEsperado = 1; 
        int resultadoObtenido = dao.modificarApellidoMaternoProfesor(apellidoMaternoActualizado, correoProfesor);
        assertEquals(resultadoEsperado, resultadoObtenido);
    }
    
    @Test
    public void pruebaModificarCorreoProfesorExitoso() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String correoActualizado = "martinnn@example.com";
        String correoProfesor = "Martin@example.com";   
        int resultadoEsperado = 1;
        int resultadoObtenido = dao.modificarCorreoProfesor(correoActualizado, correoProfesor);
        assertEquals(resultadoEsperado, resultadoObtenido);
    }
    
    @Test
    public void pruebaModificarCorreoProfesorFracaso() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String correoActualizado = "jose@examplo.com";
        String correoProfesor = "jose@example.com";   
        int resultadoEsperado = 1;
        int resultadoObtenido = dao.modificarCorreoProfesor(correoActualizado, correoProfesor);
        assertEquals(resultadoEsperado, resultadoObtenido);
    }
    
    @Test
    public void pruebaObtenerIdProfesorPorCorreoExitosa(){
        DAOProfesorImplementacion instancia = new DAOProfesorImplementacion();
        String correo="Juan@gmail.com";
        int resultadoEsperado=1;
        int resultadoObtenido=instancia.obtenerIdProfesorPorCorreo(correo);
        assertEquals(resultadoEsperado,resultadoObtenido);
    }
    
    @Test
    public void pruebaFlujoFallidoObtenerIdProfesorPorCorreoExitosa(){
        DAOProfesorImplementacion instancia = new DAOProfesorImplementacion();
        String correo="eduardo@gmail.com";
        int resultadoEsperado=1;
        int resultadoObtenido=instancia.obtenerIdProfesorPorCorreo(correo);
        assertEquals(resultadoEsperado,resultadoObtenido);
    }    
    
    @Test
    public void pruebaConsultarProfesorPorIdExitosa(){
        Profesor profesorEsperado=new Profesor();
        profesorEsperado.setNombre("Eduardo");
        profesorEsperado.setApellidoPaterno("Aguilar");
        profesorEsperado.setApellidoMaterno("Garcia");
        profesorEsperado.setCorreo("eduardo@gmail.com");
        int idProfesor=1;
        Profesor profesorObtenido=new Profesor();
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        profesorObtenido=dao.consultarProfesorPorId(idProfesor);
        assertEquals(profesorEsperado,profesorObtenido);        
    }
    
    @Test
    public void pruebaObtenerProfesorPorIdExitosa(){
        Usuario logger = new Usuario();
        logger.setTipoDeUsuario(EnumTipoDeUsuario.Logger.toString());
        DAOProfesorImplementacion instancia = new DAOProfesorImplementacion();
        Profesor profesorEsperado = new Profesor();
        profesorEsperado.setIdProfesor(3);
        profesorEsperado.setNombre("Juan");
        profesorEsperado.setApellidoPaterno("Cumplido");
        profesorEsperado.setApellidoMaterno("Negrete");
        profesorEsperado.setCorreo("juan@gmail.com"); 
        profesorEsperado.setEstado("Activo");
        Profesor profesorObtenido = instancia.obtenerProfesorPorIdUsuario(3,logger);
        assertEquals(profesorEsperado,profesorObtenido);
    }
    
     @Test
    public void pruebaFlujoFallidoObtenerProfesorPorIdExitosa(){
        Usuario logger = new Usuario();
        logger.setTipoDeUsuario(EnumTipoDeUsuario.Logger.toString());
        DAOProfesorImplementacion instancia = new DAOProfesorImplementacion();
        Profesor profesorEsperado = new Profesor();
        profesorEsperado.setIdProfesor(3);
        profesorEsperado.setNombre("Chris");
        profesorEsperado.setApellidoPaterno("Vasquez");
        profesorEsperado.setApellidoMaterno("Zapata");
        profesorEsperado.setCorreo("chris@gmail.com"); 
        profesorEsperado.setEstado("Activo");
        Profesor profesorObtenido = instancia.obtenerProfesorPorIdUsuario(4,logger);
        assertNotEquals(profesorEsperado,profesorObtenido);
    }
    
    
    @Test
    public void pruebaObtenerProfesorPorIdUsarioExitosa(){
        Usuario logger = new Usuario();
        logger.setTipoDeUsuario(EnumTipoDeUsuario.Logger.toString());
        DAOProfesorImplementacion instancia = new DAOProfesorImplementacion();
        Profesor profesorEsperado = new Profesor();
        profesorEsperado.setIdProfesor(3);
        profesorEsperado.setNombre("Juan");
        profesorEsperado.setApellidoPaterno("Cumplido");
        profesorEsperado.setApellidoMaterno("Negrete");
        profesorEsperado.setCorreo("juan@gmail.com"); 
        profesorEsperado.setEstado("Activo");
        Profesor profesorObtenido = instancia.obtenerProfesorPorIdUsuario(13,logger);
        assertEquals(profesorEsperado,profesorObtenido);
    }
    
    @Test
    public void pruebaFallidaObtenerProfesorPorIdUsarioExitosa(){
        Usuario logger = new Usuario();
        logger.setTipoDeUsuario(EnumTipoDeUsuario.Logger.toString());
        DAOProfesorImplementacion instancia = new DAOProfesorImplementacion();
        Profesor profesorEsperado = new Profesor();
        profesorEsperado.setIdProfesor(3);
        profesorEsperado.setNombre("Chris");
        profesorEsperado.setApellidoPaterno("Vasquez");
        profesorEsperado.setApellidoMaterno("Zapata");
        profesorEsperado.setCorreo("chris@gmail.com"); 
        profesorEsperado.setEstado("Activo");
        Profesor profesorObtenido = instancia.obtenerProfesorPorIdUsuario(13,logger);
        assertEquals(profesorEsperado,profesorObtenido);
    }
}
