package pruebaLogicaDeNegocio.DAOImplementacion;

import logicaDeNegocio.clases.Profesor;
import logicaDeNegocio.DAOImplementacion.DAOProfesorImplementacion;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PruebaDAOProfesorImplementacion {

    @Test
    public void pruebaRegistrarProfesorExitoso() {
        DAOProfesorImplementacion instancia = new DAOProfesorImplementacion();
        Profesor profesor = new Profesor();
        profesor.setNombre("Juan");
        profesor.setApellidoPaterno("Perez");
        profesor.setApellidoMaterno("Gomez");
        profesor.setCorreo("juan.perez@example.com");
        int resultadoEsperado = 1;
        int resultadoObtenido = instancia.registrarProfesor(profesor);
        assertEquals(resultadoEsperado, resultadoObtenido);
    }


    @Test
    public void pruebaEliminarProfesorExitoso() {
        DAOProfesorImplementacion instancia = new DAOProfesorImplementacion();
        String correo = "juan.perez@example.com";
        String resultadoEsperado = "Profesor eliminado correctamente";
        String resultadoObtenido = instancia.eliminarProfesor(correo);
        assertEquals(resultadoEsperado, resultadoObtenido);
    }

    @Test
    public void pruebaModificarNombreProfesorExitoso() {
        DAOProfesorImplementacion instancia = new DAOProfesorImplementacion();
        String nombreActualizado = "Pedro";
        String correoProfesor = "juan.perez@example.com";
        String resultadoEsperado = "Nombre del profesor actualizado correctamente";
        String resultadoObtenido = instancia.modificarNombreProfesor(nombreActualizado, correoProfesor);
        assertEquals(resultadoEsperado, resultadoObtenido);
    }

    @Test
    public void pruebaModificarApellidoPaternoProfesorExitoso() {
        DAOProfesorImplementacion instancia = new DAOProfesorImplementacion();
        String apellidoPaternoActualizado = "Lopez";
        String correoProfesor = "juan.perez@example.com";
        String resultadoEsperado = "Apellido paterno del profesor actualizado correctamente";
        String resultadoObtenido = instancia.modificarApellidoPaternoProfesor(apellidoPaternoActualizado, correoProfesor);
        assertEquals(resultadoEsperado, resultadoObtenido);
    }

    @Test
    public void pruebaModificarApellidoMaternoProfesorExitoso() {
        DAOProfesorImplementacion instancia = new DAOProfesorImplementacion();
        String apellidoMaternoActualizado = "Gonzalez";
        String correoProfesor = "juan.perez@example.com";
        String resultadoEsperado = "Apellido materno del profesor actualizado correctamente";
        String resultadoObtenido = instancia.modificarApellidoMaternoProfesor(apellidoMaternoActualizado, correoProfesor);
        assertEquals(resultadoEsperado, resultadoObtenido);
    }

    @Test
    public void pruebaModificarCorreoProfesorExitoso() {
        DAOProfesorImplementacion instancia = new DAOProfesorImplementacion();
        String correoActualizado = "pedro.lopez@example.com";
        String correoProfesor = "juan.perez@example.com";
        String resultadoEsperado = "Correo del profesor actualizado correctamente";
        String resultadoObtenido = instancia.modificarCorreoProfesor(correoActualizado, correoProfesor);
        assertEquals(resultadoEsperado, resultadoObtenido);
    }

    @Test
    public void pruebaConsultarProfesorPorCorreoExitoso() {
        DAOProfesorImplementacion instancia = new DAOProfesorImplementacion();
        String correo = "juan.perez@example.com";
        Profesor profesor = instancia.consultarProfesorPorCorreo(correo);
        assertNotNull(profesor);
        assertEquals("Pedro", profesor.getNombre());
        assertEquals("Lopez", profesor.getApellidoPaterno());
        assertEquals("Gonzalez", profesor.getApellidoMaterno());
        assertEquals("juan.perez@example.com", profesor.getCorreo());
    }
}
