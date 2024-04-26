package logicaDeNegocio.clases;

import java.util.regex.Pattern;

public final class Usuario {
    
    private int idUsuario;
    private String nombreUsuario;
    private String contrasenia;
    private String tipoDeUsuario;
    private static final String SOLO_LETRAS_PATTERN = "^[\\p{L}\\sáéíóúÁÉÍÓÚüÜ]+$";
    private static final String CONTRASENA_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    private static final String SOLO_NUMEROS_PATTERN = "\\d+";

    public Usuario(){
        
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario)throws IllegalArgumentException{
        if(Pattern.matches(SOLO_NUMEROS_PATTERN, String.valueOf(idUsuario))){
            this.idUsuario = idUsuario;
        }else{
            throw new IllegalArgumentException();
        }
    }
    
    public void setNombreUsuario(String nombreUsuario)throws IllegalArgumentException{
        if(nombreUsuario!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, nombreUsuario)){
            this.nombreUsuario = nombreUsuario;
        }else{
            throw new IllegalArgumentException();
        }
    }
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    
    public void setContrasenia(String contrasenia)throws IllegalArgumentException{
        if(contrasenia!=null&&Pattern.matches(CONTRASENA_PATTERN, contrasenia)){
            this.contrasenia = contrasenia;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getContrasenia() {
        return contrasenia;
    }
    
    public void setTipoDeUsuario(String tipoDeUsuario)throws IllegalArgumentException{
        if(tipoDeUsuario!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, tipoDeUsuario)){
            this.tipoDeUsuario = tipoDeUsuario;
        }else{
            throw new IllegalArgumentException();
        }
    }
    
    public String getTipoDeUsuario() {
        return tipoDeUsuario;
    }
    
    @Override
    public boolean equals(Object obj){
        Usuario usuarioTemp = (Usuario)obj;
        return this.nombreUsuario.equals(usuarioTemp.getNombreUsuario())&&
                this.contrasenia.equals(usuarioTemp.getContrasenia())&&
                this.tipoDeUsuario.equals(usuarioTemp.getTipoDeUsuario()); 
    }
    
}
