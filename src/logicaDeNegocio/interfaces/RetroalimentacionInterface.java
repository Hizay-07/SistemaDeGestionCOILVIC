package logicaDeNegocio.interfaces;

public interface RetroalimentacionInterface {
    
    public int registrarRetroalimentacion(int idColaboracion);
    
    public int editarRetroalimentacionPorId(int idRetroalimentacion,String mensaje);
    
    public int editarEmisorDeRetroalimentacionPorId(int idRetroalimentacion,String emisor);
    
}
