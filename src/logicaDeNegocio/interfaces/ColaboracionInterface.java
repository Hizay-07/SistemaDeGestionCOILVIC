package logicaDeNegocio.interfaces;

import java.util.List;
import logicaDeNegocio.clases.Colaboracion;

<<<<<<< HEAD
public interface ColaboracionInterface {
    public int registrarColaboracion(Colaboracion colaboracion);
    
    public List<Colaboracion> consultarColaboraciones();
    
    public int registrarRetroalimentacionColaboracionPorId(int idColaboracion,String retroalimentacion);    
    
=======
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
>>>>>>> 9fda9407ddcf920dd702160fd57cbfce28ad6525
}
