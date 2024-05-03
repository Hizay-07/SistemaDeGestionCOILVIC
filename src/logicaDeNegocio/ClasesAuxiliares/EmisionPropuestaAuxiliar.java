package logicaDeNegocio.ClasesAuxiliares;

import logicaDeNegocio.clases.EmisionPropuesta;


public final class EmisionPropuestaAuxiliar {
    
    private static EmisionPropuestaAuxiliar instancia;
    private int idProfesor;
    private int idPropuestaColaboracion;
    private String fechaEmision;

    public EmisionPropuestaAuxiliar(EmisionPropuesta emision) {
        setIdProfesor(emision.getIdProfesor());
        setIdPropuestaColaboracion(emision.getIdPropuestaColaboracion());
        setFechaEmision(emision.getFechaEmision());
    }
    
    public static void setInstancia(EmisionPropuesta emision){
        instancia = new EmisionPropuestaAuxiliar(emision);
    }
    
    public static EmisionPropuestaAuxiliar getInstancia(){
        return instancia;
    }
    
    public void resetInstancia(){
        instancia = null;
    }
    
    public int getIdProfesor() {
        return idProfesor;
    }

    private void setIdProfesor(int idProfesor){
        this.idProfesor = idProfesor;
    }

    public int getIdPropuestaColaboracion(){
        return idPropuestaColaboracion;
    }

    private void setIdPropuestaColaboracion(int idPropuestaColaboracion){
        this.idPropuestaColaboracion = idPropuestaColaboracion;
    }

    public String getFechaEmision(){
        return fechaEmision;
    }

    private void setFechaEmision(String fechaEmision){
        this.fechaEmision = fechaEmision;
    }        
    
}
