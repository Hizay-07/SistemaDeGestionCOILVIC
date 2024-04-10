package pruebaLogicaDeNegocio.DAOImplementacion;

import logicaDeNegocio.DAOImplementacion.DAOProfesorImplementacion;
import logicaDeNegocio.enums.EnumEstados;
import logicaDeNegocio.clases.Profesor;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PruebaDAOProfesorImplementacion {

    @Test
    public void pruebaRegistrarProfesorExitoso() {
        DAOProfesorImplementacion daoProfesor = new DAOProfesorImplementacion();
        Profesor profesor = new Profesor();
        profesor.setNombre("Martin");
        profesor.setApellidoPaterno("Garza");
        profesor.setApellidoMaterno("Garcia");
        profesor.setCorreo("Martin@example.com");
        profesor.setEstado(EnumEstados.Activo.toString()); 
        int resultadoEsperado = 1;
        int resultadoObtenido = daoProfesor.registrarProfesor(profesor);
        assertEquals(resultadoEsperado, resultadoObtenido);
    }

    @Test
    public void pruebaCambiarEstadoProfesorExitosa() {
        DAOProfesorImplementacion instancia = new DAOProfesorImplementacion();
        int idProfesor = 1; 
        String nuevoEstado = EnumEstados.Desactivado.toString(); 
        int resultado = instancia.cambiarEstadoProfesor(idProfesor, nuevoEstado);
        assertEquals(1, resultado); 
    }

    @Test
    public void pruebaModificarNombreProfesorExitoso() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String nombreActualizado = "martin";
        String correoProfesor = "Martin@example.com";
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
    public void pruebaModificarApellidoMaternoProfesorExitoso() {
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String apellidoMaternoActualizado = "Garcia";
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
}
