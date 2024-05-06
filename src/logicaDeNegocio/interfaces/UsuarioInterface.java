package logicaDeNegocio.interfaces;

import logicaDeNegocio.clases.Usuario;

public interface UsuarioInterface {
    
    public boolean validarCredenciales(Usuario usuario, Usuario logger);
    public int registrarUsuario(Usuario usuario);
    public String obtenerTipoDeUsuario(Usuario usuario,Usuario logger);
    public int obtenerIdUsuario(Usuario usuario,Usuario logger);
    public boolean confirmarConexionDeInicioDeSesion(Usuario logger);
}
