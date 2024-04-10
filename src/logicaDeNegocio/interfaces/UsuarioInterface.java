package logicaDeNegocio.interfaces;

import logicaDeNegocio.clases.Usuario;

public interface UsuarioInterface {
    
    public boolean validarCredenciales(Usuario usuario);
    public int registrarUsuario(Usuario usuario);
}
