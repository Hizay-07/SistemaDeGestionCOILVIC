package logicaDeNegocio.interfaces;

import logicaDeNegocio.clases.Pais;

public interface PaisInterface {
    public int registrarPais(Pais paisAIngresar);
    public int obtenerNumeroDePais(Pais paisAConsultar);
}
