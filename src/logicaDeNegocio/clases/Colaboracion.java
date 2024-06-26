package logicaDeNegocio.clases;

import java.util.regex.Pattern;

public class Colaboracion {
    
    private int idColaboracion;
    private String retroalimentacion;
    private String syllabus;
    private String estadoColaboracion;
    private int cantidadEstudiantes;
    private PropuestaColaboracion propuestaColaboracion;
    private static final String SOLO_LETRAS_PATTERN = "^[\\p{L}\\sáéíóúÁÉÍÓÚüÜ']+(?:\\s[\\p{L}\\sáéíóúÁÉÍÓÚüÜ']+)*$";
    private static final String RETROALIMENTACION = "^[\\p{L}\\d\\sáéíóúÁÉÍÓÚüÜ.,;'-_?¿()]+(?:\\s[\\p{L}\\d\\sáéíóúÁÉÍÓÚüÜ.,;'-_?¿()]+)*$";
    private static final String SOLO_NUMEROS_PATTERN = "\\d+";
    private static final String RUTA = "^[\\p{L}\\sáéíóúÁÉÍÓÚüÜ.,'-_()0-9]+(?:\\s[\\p{L}\\sáéíóúÁÉÍÓÚüÜ.,'-_()0-9]+)*$";

    public Colaboracion() {
        
    }

    public int getIdColaboracion() {
        return idColaboracion;
    }

    public void setIdColaboracion(int idColaboracion)throws IllegalArgumentException {
        if(Pattern.matches(SOLO_NUMEROS_PATTERN, String.valueOf(idColaboracion))){
            this.idColaboracion = idColaboracion;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getEstadoColaboracion() {
        return estadoColaboracion;
    }

    public void setEstadoColaboracion(String estadoColaboracion)throws IllegalArgumentException {
        if(estadoColaboracion!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, estadoColaboracion.trim())&&estadoColaboracion.trim().length()<=45){
            this.estadoColaboracion = estadoColaboracion.trim().replaceAll("\\s+", " ");
        }else{
            throw new IllegalArgumentException();
        }
    }

    public PropuestaColaboracion getPropuestaColaboracion() {
        return propuestaColaboracion;
    }

    public void setPropuestaColaboracion(PropuestaColaboracion propuestaColaboracion) {
        this.propuestaColaboracion = propuestaColaboracion;
    }

    public int getCantidadEstudiantes() {
        return cantidadEstudiantes;
    }


    public void setCantidadEstudiantes(int cantidadEstudiantes)throws IllegalArgumentException {
        if(Pattern.matches(SOLO_NUMEROS_PATTERN, String.valueOf(cantidadEstudiantes)) && cantidadEstudiantes >= 0 && cantidadEstudiantes <= 150){
            this.cantidadEstudiantes = cantidadEstudiantes;
        }else{
            throw new IllegalArgumentException();
        }
    }
    
    public String getRetroalimentacion(){
        return retroalimentacion;
    }
    
    public void setRetroalimentacion(String retroalimentacion)throws IllegalArgumentException{
        if(Pattern.matches(RETROALIMENTACION, retroalimentacion) && retroalimentacion.length()>=1&&retroalimentacion.length()<=255||retroalimentacion.isEmpty()){
            this.retroalimentacion = retroalimentacion;
        }else{
            throw new IllegalArgumentException();
        }
    }
    
    public String getSyllabus(){
        return syllabus;
    }
    
    public void setSyllabus(String rutaSyllabus)throws IllegalArgumentException{
        if(Pattern.matches(RUTA, rutaSyllabus) && rutaSyllabus.length()>=10&&rutaSyllabus.length()<=255){
            this.syllabus = rutaSyllabus;
        }else{
            throw new IllegalArgumentException();
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Colaboracion)){
            return false;
        }
        Colaboracion colaboracionTemporal=(Colaboracion)obj;
        return idColaboracion==colaboracionTemporal.getIdColaboracion();                      
    }
      
}
