
package logicaDeNegocio.clases;

public class TipoColaboracion {
    private String tipo;
    private int idTipoColaboracion;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }    

    public int getIdTipoColaboracion() {
        return idTipoColaboracion;
    }

    public void setIdTipoColaboracion(int idTipoColaboracion) {
        this.idTipoColaboracion = idTipoColaboracion;
    }
    
    public boolean equals(Object obj){
        TipoColaboracion tipoColaboracionTemporal=(TipoColaboracion)obj;
        return tipo.equals(tipoColaboracionTemporal.getTipo())&&
                idTipoColaboracion==tipoColaboracionTemporal.getIdTipoColaboracion();
    }
}
