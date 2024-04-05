package pruebaLogicaDeNegocio.DAOImplementacion;

import logicaDeNegocio.DAOImplementacion.DAOPaisImplementacion;
import logicaDeNegocio.clases.Pais;
import logicaDeNegocio.enums.EnumPais;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class pruebaDAOPaisImplementacion {
    
    @Test
    public void pruebaRegistrarPaisExitosa(){
        Pais paisPrueba = new Pais();
        DAOPaisImplementacion pruebaMetodo = new DAOPaisImplementacion(); 
        paisPrueba.setNombrePais(EnumPais.Colombia.toString());
       
        int resultadoInsercion = pruebaMetodo.registrarPais(paisPrueba);
        
        assertEquals(1,resultadoInsercion);        
    }
    
    @Test
    public void pruebaObtenerNumeroDePaisExitoso(){
        Pais paisPrueba = new Pais();
        DAOPaisImplementacion pruebaMetodo = new DAOPaisImplementacion(); 
        paisPrueba.setNombrePais(EnumPais.Colombia.toString());
        
        int resultadoConsulta = pruebaMetodo.obtenerNumeroDePais(paisPrueba);
        assertEquals(3,resultadoConsulta);
    }
}
