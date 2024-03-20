package pruebaLogicaDeNegocio.DAOImplementacion;

import logicaDeNegocio.DAOImplementacion.DAOPaisImplementacion;
import logicaDeNegocio.clases.Pais;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class pruebaDAOPaisImplementacion {
    
    @Test
    public void pruebaRegistrarPaisExitosa(){
        Pais paisPrueba = new Pais();
        DAOPaisImplementacion pruebaMetodo = new DAOPaisImplementacion(); 
        paisPrueba.setNombrePais("Mexico");
       
        int resultadoInsercion = pruebaMetodo.registrarPais(paisPrueba);
        
        assertEquals(1,resultadoInsercion);        
    }
    
    @Test
    public void pruebaObtenerNumeroDePaisExitoso(){
        Pais paisPrueba = new Pais();
        DAOPaisImplementacion pruebaMetodo = new DAOPaisImplementacion(); 
        paisPrueba.setNombrePais("Mexico");
        
        int resultadoConsulta = pruebaMetodo.obtenerNumeroDePais(paisPrueba);
        assertEquals(1,resultadoConsulta);
    }
}
