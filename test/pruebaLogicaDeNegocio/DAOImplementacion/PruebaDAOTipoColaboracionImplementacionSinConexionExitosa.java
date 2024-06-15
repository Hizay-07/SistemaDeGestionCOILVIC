package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOTipoColaboracionImplementacion;
import logicaDeNegocio.clases.TipoColaboracion;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;

public class PruebaDAOTipoColaboracionImplementacionSinConexionExitosa {
            
    @Test
    public void pruebaConsultarTiposDeColaboracionSinConexionExitosa(){
        DAOTipoColaboracionImplementacion dao = new DAOTipoColaboracionImplementacion();
        List<TipoColaboracion> listaEsperada=new ArrayList<>();
        listaEsperada.add(new TipoColaboracion(1,"Clase espejo"));
        listaEsperada.add(new TipoColaboracion(2,"Implementación COIL-VIC"));        
        List<TipoColaboracion> tiposColaboracionObtenidas = dao.consultarTiposDeColaboracion();
        assertNotEquals(listaEsperada, tiposColaboracionObtenidas);         
    }
    
    @Test
    public void pruebaConsultarTipoColaboracionPorIdSinConexionExitosa(){
        DAOTipoColaboracionImplementacion dao = new DAOTipoColaboracionImplementacion();
        String tipo = dao.consultarTipoColaboracionPorId(1);
        assertEquals("", tipo);        
    }
    
    @Test
    public void pruebaConsultarIdTipoColaboracionPorTipoSinConexionExitosa(){
        DAOTipoColaboracionImplementacion dao = new DAOTipoColaboracionImplementacion();
        int idTipo = dao.consultarIdTipoColaboracionPorTipo("Implementación COIL-VIC");
        assertEquals(-1, idTipo);        
    }
    
    @Test
    public void pruebaVerificarTipoColaboracionSinConexionExitosa(){
        DAOTipoColaboracionImplementacion dao = new DAOTipoColaboracionImplementacion();
        int resultado = dao.verificarTipoColaboracion();
        assertEquals(-1,resultado);         
    }
    
}
