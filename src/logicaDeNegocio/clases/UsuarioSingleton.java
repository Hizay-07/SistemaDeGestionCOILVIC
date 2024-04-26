package logicaDeNegocio.clases;

import java.util.regex.Pattern;

public final class UsuarioSingleton {
    
    private static UsuarioSingleton instancia;
    private int idUsuario;
    private String nombreUsuario;
    private String contrasenia;
    private String tipoDeUsuario;
    private static final String SOLO_LETRAS_PATTERN = "^[\\p{L}\\sáéíóúÁÉÍÓÚüÜ]+$";
    private static final String CONTRASENA_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    private static final String SOLO_NUMEROS_PATTERN = "\\d+";


    private UsuarioSingleton(Usuario usuario) {
        setNombreUsuario(usuario.getNombreUsuario());
        setContrasenia(usuario.getContrasenia());
        setTipoDeUsuario(usuario.getTipoDeUsuario());
    } 
    
    public static UsuarioSingleton getInstancia(Usuario usuario)throws IllegalArgumentException{
        if(instancia==null){
            instancia = new UsuarioSingleton(usuario);
        }
        return instancia;
    }
    
    private void setIdUsuario(int idUsuario)throws IllegalArgumentException{
        if(Pattern.matches(SOLO_NUMEROS_PATTERN, String.valueOf(idUsuario))){
            this.idUsuario = idUsuario;
        }else{
            throw new IllegalArgumentException();
        }
    }
    
    public int getIdUsuario(){
        return idUsuario;
    }
    
    private void setNombreUsuario(String nombreUsuario)throws IllegalArgumentException{
        if(nombreUsuario!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, nombreUsuario)){
            this.nombreUsuario = nombreUsuario;
        }else{
            throw new IllegalArgumentException();
        }
    }
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    
    private void setContrasenia(String contrasenia)throws IllegalArgumentException{
        if(contrasenia!=null&&Pattern.matches(CONTRASENA_PATTERN, contrasenia)){
            this.contrasenia = contrasenia;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getContrasenia() {
        return contrasenia;
    }
    
    private void setTipoDeUsuario(String tipoDeUsuario)throws IllegalArgumentException{
        if(tipoDeUsuario!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, tipoDeUsuario)){
            this.tipoDeUsuario = tipoDeUsuario;
        }else{
            throw new IllegalArgumentException();
        }
    }
    
    public String getTipoDeUsuario() {
        return tipoDeUsuario;
    }
    
    public static UsuarioSingleton getInstancia(){
        return instancia;
    }
}