package logicaDeNegocio.interfaces;

import java.util.List;
import logicaDeNegocio.clases.Pais;

public interface PaisInterface {
    public int registrarPais(Pais paisAIngresar);
    public int obtenerNumeroDePais(Pais paisAConsultar);
    public List<Pais> consultarPaises();
    public int verificarPais();
}
