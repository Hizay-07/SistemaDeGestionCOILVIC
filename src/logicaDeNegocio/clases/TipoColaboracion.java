package logicaDeNegocio.clases;

import java.util.regex.Pattern;

public class TipoColaboracion {
    private String tipo;
    private int idTipoColaboracion;
    private static final String SOLO_LETRAS_PATTERN = "^[\\p{L}\\sáéíóúÁÉÍÓÚüÜ-]+(?:\\s[\\p{L}\\sáéíóúÁÉÍÓÚüÜ-]+)*$";
    private static final String SOLO_NUMEROS_PATTERN = "\\d+";

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo)throws IllegalArgumentException {
        if(tipo!=null&&!tipo.isEmpty()&&Pattern.matches(SOLO_LETRAS_PATTERN, tipo.trim())&&tipo.trim().length()<=150){
            this.tipo = tipo.trim().replaceAll("\\s+", " ");
        }else{
            throw new IllegalArgumentException();
        }
    }    

    public int getIdTipoColaboracion() {
        return idTipoColaboracion;
    }

    public void setIdTipoColaboracion(int idTipoColaboracion)throws IllegalArgumentException {
        if(Pattern.matches(SOLO_NUMEROS_PATTERN, String.valueOf(idTipoColaboracion))){
            this.idTipoColaboracion = idTipoColaboracion;
        }else{
            throw new IllegalArgumentException();
        }
    }
    
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof TipoColaboracion)){
            return false;
        }
        TipoColaboracion tipoColaboracionTemporal=(TipoColaboracion)obj;
        if(tipo==null||tipoColaboracionTemporal.getTipo()==null){
            return false;
        }
        return tipo.equals(tipoColaboracionTemporal.getTipo())&&
                idTipoColaboracion==tipoColaboracionTemporal.getIdTipoColaboracion();
    }
    
    @Override
    public String toString(){
        return tipo;        
    }
}
