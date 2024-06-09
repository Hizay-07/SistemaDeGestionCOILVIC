package logicaDeNegocio.interfaces;

import logicaDeNegocio.clases.Profesor;
import logicaDeNegocio.clases.Usuario;

public interface ProfesorInterface {
    int registrarProfesor(Profesor profesor);

    int cambiarEstadoProfesor(int idProfesor, String nuevoEstado);

    int modificarNombreProfesor(String nombreActualizado, String correoProfesor);

    int modificarApellidoPaternoProfesor(String apellidoPaternoActualizado, String correoProfesor);

    int modificarApellidoMaternoProfesor(String apellidoMaternoActualizado, String correoProfesor);

    int modificarCorreoProfesor(String correoActualizado, String correoProfesor);

    public int obtenerIdProfesorPorCorreo(String correo);

    public Profesor consultarProfesorPorId(int idProfesor);

    public int asignarUsuarioDeProfesorPorCorreo(String correo);

    public Profesor obtenerProfesorPorIdUsuario(int idUsuario, Usuario logger);

    public int validarDuplicidadDeCorreo(String correo);

    public int consultarPrecondicionInicioColaboracionPorIdProfesor(int idProfesor);
}
