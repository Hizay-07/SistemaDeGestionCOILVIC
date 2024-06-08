package logicaDeNegocio.interfaces;

import logicaDeNegocio.clases.Profesor;
import logicaDeNegocio.clases.Usuario;

public interface UsuarioInterface {    
    public int validarCredenciales(Usuario usuario, Usuario logger);

    public int registrarUsuario(Usuario usuario);

    public String obtenerTipoDeUsuario(Usuario usuario, Usuario logger);

    public int obtenerIdUsuario(Usuario usuario, Usuario logger);

    public boolean confirmarConexionDeInicioDeSesion(Usuario logger);

    public boolean confirmarConexionDeUsuario();

    public int eliminarUsuario(String nombreDeUsuario);
    
    public int verificarDuplicidadNombreDeUsuario(String nombre);
    
    public int actualizarUsuarioPorIdUsuario(Profesor profesor, String contrasenia);
}
