package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.ArrayList;
import java.util.List;
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
    public void pruebaFlujoFallidoRegistrarPaisExitosa(){
        Pais paisPrueba = new Pais();
        DAOPaisImplementacion pruebaMetodo = new DAOPaisImplementacion(); 
        
        int resultadoInsercion = pruebaMetodo.registrarPais(paisPrueba);
        
        assertEquals(-1,resultadoInsercion); 
    }
    
    @Test
    public void pruebaObtenerNumeroDePaisExitosa(){
        Pais paisPrueba = new Pais();
        DAOPaisImplementacion pruebaMetodo = new DAOPaisImplementacion(); 
        paisPrueba.setNombrePais(EnumPais.Colombia.toString());
        
        int resultadoConsulta = pruebaMetodo.obtenerNumeroDePais(paisPrueba);
        assertEquals(3,resultadoConsulta);
    }
    
    @Test
    public void pruebaFlujoFallidoObtenerNumeroDePaisExitoso(){
        Pais paisPrueba = new Pais();
        DAOPaisImplementacion pruebaMetodo = new DAOPaisImplementacion(); 
        
        int resultadoConsulta = pruebaMetodo.obtenerNumeroDePais(paisPrueba);
        assertEquals(0,resultadoConsulta);
    }
    
    @Test
    public void pruebaConsultarPaisesExitosa(){        
        List<Pais> resultadoEsperado=new ArrayList<>();
        Pais pais1=new Pais();
        pais1.setNombrePais("Colombia");
        Pais pais2=new Pais();
        pais2.setNombrePais("Mexico");
        resultadoEsperado.add(pais1);
        resultadoEsperado.add(pais2);
        DAOPaisImplementacion pruebaMetodo = new DAOPaisImplementacion(); 
        List<Pais> resultadoObtenido=pruebaMetodo.consultarPaises();
        assertEquals(resultadoEsperado,resultadoObtenido);               
    }
}

