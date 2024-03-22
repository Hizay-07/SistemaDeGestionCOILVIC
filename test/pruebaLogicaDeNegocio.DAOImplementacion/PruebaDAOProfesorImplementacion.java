package pruebaLogicaDeNegocio.DAOImplementacion;

import logicaDeNegocio.DAOImplementacion.DAOProfesorImplementacion;
import logicaDeNegocio.clases.Profesor;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PruebaDAOProfesorImplementacion {

    @Test
    public void pruebaRegistrarProfesorExitoso() {
        DAOProfesorImplementacion daoProfesor = new DAOProfesorImplementacion();
        Profesor profesor = new Profesor();
        profesor.setNombre("Juan");
        profesor.setApellidoPaterno("Perez");
        profesor.setApellidoMaterno("Gomez");
        profesor.setCorreo("juan@example.com");
        int resultadoEsperado = 1;
        int resultadoObtenido = daoProfesor.registrarProfesor(profesor);
        assertEquals(resultadoEsperado, resultadoObtenido);
    }

    @Test
    public void pruebaEliminarProfesorExitoso() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String correo = "juan@example.com";
        int resultadoEsperado = 1;
        int resultadoObtenido = dao.eliminarProfesor(correo);
        assertEquals(resultadoEsperado, resultadoObtenido);
    }

    @Test
    public void pruebaModificarNombreProfesorExitoso() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String nombreActualizado = "Juan";
        String correoProfesor = "pedro@example.com";
        int resultadoEsperado = 1;
        int resultadoObtenido = dao.modificarNombreProfesor(nombreActualizado, correoProfesor);
        assertEquals(resultadoEsperado, resultadoObtenido);
    }

    @Test
    public void pruebaModificarApellidoPaternoProfesorExitoso() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String apellidoPaternoActualizado = "Lopez";
        String correoProfesor = "pedro@example.com";
        int resultadoEsperado = 1;
        int resultadoObtenido = dao.modificarApellidoPaternoProfesor(apellidoPaternoActualizado, correoProfesor);
        assertEquals(resultadoEsperado, resultadoObtenido);
    }

    @Test
    public void pruebaModificarApellidoMaternoProfesorExitoso() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String apellidoMaternoActualizado = "Gomez";
        String correoProfesor = "pedro@example.com";
        int resultadoEsperado = 1;
        int resultadoObtenido = dao.modificarApellidoMaternoProfesor(apellidoMaternoActualizado, correoProfesor);
        assertEquals(resultadoEsperado, resultadoObtenido);
    }

    @Test
    public void pruebaModificarCorreoProfesorExitoso() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String correoActualizado = "juann@example.com";
        String correoProfesor = "pedro@example.com";
        int resultadoEsperado = 1;
        int resultadoObtenido = dao.modificarCorreoProfesor(correoActualizado, correoProfesor);
        assertEquals(resultadoEsperado, resultadoObtenido);
    }
}
