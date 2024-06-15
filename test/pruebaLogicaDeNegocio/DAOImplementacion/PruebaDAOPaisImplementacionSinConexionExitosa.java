package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOPaisImplementacion;
import logicaDeNegocio.clases.Pais;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class PruebaDAOPaisImplementacionSinConexionExitosa {
    
    @Test
    public void pruebaObtenerNumeroDePaisSinConexionExitosa(){
        Pais paisPrueba = new Pais();
        DAOPaisImplementacion pruebaMetodo = new DAOPaisImplementacion(); 
        paisPrueba.setNombrePais("México");       
        int resultadoConsulta = pruebaMetodo.obtenerNumeroDePais(paisPrueba);
        assertEquals(-1,resultadoConsulta);                
    }
    
    @Test
    public void pruebaConsultarPaisesSinConexionExitosa(){
        DAOPaisImplementacion dao = new DAOPaisImplementacion();
        List<Pais> paisesObtenidos = dao.consultarPaises();
        assertTrue(paisesObtenidos.isEmpty());        
    }
    
    @Test
    public void pruebaVerificarPaisSinConexionExitosa(){
        DAOPaisImplementacion dao = new DAOPaisImplementacion();
        int resultado = dao.verificarPais();
        assertEquals(-1,resultado);         
    }    
}
