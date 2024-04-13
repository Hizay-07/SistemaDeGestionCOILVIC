package logicaDeNegocio.clases;


public class Usuario {
    
    private String nombreUsuario;
    private String contrasenia;
    private int tipoDeUsuario;

    public Usuario() {
    } 

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public int getTipoDeUsuario() {
        return tipoDeUsuario;
    }

    public void setTipoDeUsuario(int tipoDeUsuario) {
        this.tipoDeUsuario = tipoDeUsuario;
    }
    
    @Override
    public boolean equals(Object obj){
        Usuario usuarioTemp = (Usuario)obj;
        return this.nombreUsuario.equals(usuarioTemp.getNombreUsuario())&&
                this.contrasenia.equals(usuarioTemp.getContrasenia())&&
                this.tipoDeUsuario == usuarioTemp.getTipoDeUsuario(); 
    }
    
}
