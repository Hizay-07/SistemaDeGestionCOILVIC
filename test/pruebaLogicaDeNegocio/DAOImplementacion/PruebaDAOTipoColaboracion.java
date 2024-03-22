package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOTipoColaboracion;
import logicaDeNegocio.clases.TipoColaboracion;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PruebaDAOTipoColaboracion {
    
    @Test
    public void pruebaRegistrarTipoColaboracionExitosa(){
        TipoColaboracion tipoColaboracion=new TipoColaboracion();
        tipoColaboracion.setTipo("Implementacion COIL");
        DAOTipoColaboracion instancia=new DAOTipoColaboracion();
        int resultadoEsperado=1;
        int resultadoObtenido=instancia.registrarTipoColaboracion(tipoColaboracion);
        assertEquals(resultadoEsperado,resultadoObtenido);               
    }
    
    @Test
    public void pruebaEditarTipoPorIdExitosa(){
        DAOTipoColaboracion instancia=new DAOTipoColaboracion();
        int resultadoEsperado=1;
        int resultadoObtenido=instancia.editarTipoPorId(1, "Clase espejo");
        assertEquals(resultadoEsperado,resultadoObtenido);                              
    }
    
    @Test
    public void pruebaConsultarTiposDeColaboracionExitosa(){
        List<TipoColaboracion> listaEsperada=new ArrayList<>();
        TipoColaboracion tipoColaboracion=new TipoColaboracion();
        tipoColaboracion.setIdTipoColaboracion(1);
        tipoColaboracion.setTipo("Clase espejo");
        listaEsperada.add(tipoColaboracion);
        DAOTipoColaboracion instancia=new DAOTipoColaboracion();
        List<TipoColaboracion> listaObtenida=new ArrayList<>();
        listaObtenida=instancia.consultarTiposDeColaboracion();
        assertEquals(listaEsperada,listaObtenida);        
    }
    
}
