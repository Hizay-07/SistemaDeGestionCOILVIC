package logicaDeNegocio.interfaces;

import logicaDeNegocio.clases.Profesor;

public interface ProfesorInterface {

    int registrarProfesor(Profesor profesor);
    
    int eliminarProfesor(String correo);

    int modificarNombreProfesor(String nombreActualizado, String correoProfesor);

    int modificarApellidoPaternoProfesor(String apellidoPaternoActualizado, String correoProfesor);

    int modificarApellidoMaternoProfesor(String apellidoMaternoActualizado, String correoProfesor);

    int modificarCorreoProfesor(String correoActualizado, String correoProfesor);
}
