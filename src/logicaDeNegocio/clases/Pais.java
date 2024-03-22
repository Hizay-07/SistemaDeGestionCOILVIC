
package logicaDeNegocio.clases;


public class Pais{
    private String nombrePais;

    public Pais(){
    }

    
    public String getNombrePais(){
        return nombrePais;
    }

    public void setNombrePais(String nombrePais){
        this.nombrePais = nombrePais;
    }
    
    @Override
    public boolean equals(Object obj){
        Pais paisTemporal = (Pais)obj;
        return nombrePais.equals(paisTemporal.getNombrePais());
    }
    
}
