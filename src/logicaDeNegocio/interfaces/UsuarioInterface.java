package logicaDeNegocio.interfaces;

import logicaDeNegocio.clases.Usuario;

public interface UsuarioInterface {
    
    public boolean validarCredenciales(Usuario usuario, Usuario logger);
    public int registrarUsuario(Usuario usuario);
    public String obtenerTipoDeUsuario(Usuario usuario);
    public int obtenerIdUsuario(Usuario usuario);
}
