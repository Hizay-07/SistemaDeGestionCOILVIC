package logicaDeNegocio.interfaces;

import java.util.List;
import logicaDeNegocio.clases.Colaboracion;

public interface ColaboracionInterface {    
    public int registrarColaboracion();
    
    public List<Colaboracion> consultarColaboraciones();
    
    public List<Colaboracion> consultarColaboracionPorFechaDeInicio(String fecha);        
    
    public int editarFechaDeInicioDeColaboracion(String fechaDeInicio);
    
    public int editarFechaDeCierreDeColaboracion(String fechaDeCierre);
    
    public int editarIdiomaDeColaboracion(String idioma);
    
    public int editarExperienciaEducativaDeColaboracion(String experienciaEducativa);
    
    public int editarTipoDeColaboracion(String tipoDeColaboracion);
    
    public int darDeBajaColaboracionPorId(int idColaboracion);
}
