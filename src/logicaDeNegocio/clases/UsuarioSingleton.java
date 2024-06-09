package logicaDeNegocio.clases;

public final class UsuarioSingleton {

    private static UsuarioSingleton instancia;
    private int idUsuario;
    private String nombreUsuario;
    private String contrasenia;
    private String tipoDeUsuario;

    private UsuarioSingleton(Usuario usuario) {
        setIdUsuario(usuario.getIdUsuario());
        setNombreUsuario(usuario.getNombreUsuario());
        setContrasenia(usuario.getContrasenia());
        setTipoDeUsuario(usuario.getTipoDeUsuario());
    }

    public static UsuarioSingleton getInstancia(Usuario usuario) {
        if (instancia == null) {
            instancia = new UsuarioSingleton(usuario);
        }
        return instancia;
    }

    private void setIdUsuario(int idUsuario) throws IllegalArgumentException {
        this.idUsuario = idUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    private void setNombreUsuario(String nombreUsuario) throws IllegalArgumentException {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    private void setContrasenia(String contrasenia) throws IllegalArgumentException {
        this.contrasenia = contrasenia;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    private void setTipoDeUsuario(String tipoDeUsuario) throws IllegalArgumentException {
        this.tipoDeUsuario = tipoDeUsuario;
    }

    public String getTipoDeUsuario() {
        return tipoDeUsuario;
    }

    public static UsuarioSingleton getInstancia() {
        return UsuarioSingleton.instancia;
    }

    public static void resetSingleton() {
        UsuarioSingleton.instancia = null;
    }

}
