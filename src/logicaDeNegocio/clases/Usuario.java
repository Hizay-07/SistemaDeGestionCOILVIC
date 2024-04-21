package logicaDeNegocio.clases;

import java.util.regex.Pattern;

public class Usuario {
    
    private String nombreUsuario;
    private String contrasenia;
    private String tipoDeUsuario;
    private static final String SOLO_LETRAS_PATTERN = "^[\\p{L}\\sáéíóúÁÉÍÓÚüÜ]+$";

    public Usuario() {
    } 

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario)throws IllegalArgumentException {
        if(nombreUsuario!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, nombreUsuario)){
            this.nombreUsuario = nombreUsuario;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia)throws IllegalArgumentException {
        if(contrasenia!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, contrasenia)){
            this.contrasenia = contrasenia;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getTipoDeUsuario() {
        return tipoDeUsuario;
    }

    public void setTipoDeUsuario(String tipoDeUsuario)throws IllegalArgumentException {
        if(tipoDeUsuario!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, tipoDeUsuario)){
            this.tipoDeUsuario = tipoDeUsuario;
        }else{
            throw new IllegalArgumentException();
        }
    }
    
    @Override
    public boolean equals(Object obj){
        Usuario usuarioTemp = (Usuario)obj;
        return this.nombreUsuario.equals(usuarioTemp.getNombreUsuario())&&
                this.contrasenia.equals(usuarioTemp.getContrasenia())&&
                this.tipoDeUsuario.equals(usuarioTemp.getTipoDeUsuario()); 
    }
    
}
