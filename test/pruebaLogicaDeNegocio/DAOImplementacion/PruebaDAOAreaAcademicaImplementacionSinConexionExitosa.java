package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOAreaAcademicaImplementacion;
import logicaDeNegocio.clases.AreaAcademica;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class PruebaDAOAreaAcademicaImplementacionSinConexionExitosa {
    
    @Test
    public void pruebaConsultarAreasAcademicasSinConexionExitosa(){
        DAOAreaAcademicaImplementacion dao = new DAOAreaAcademicaImplementacion();                                        
        List<AreaAcademica> areasAcademicasObtenidas = dao.consultarAreasAcademicas();
        assertTrue(areasAcademicasObtenidas.isEmpty());        
    }
    
    @Test
    public void pruebaConsultarIdDeAreaAcademicaPorAreaSinConexionExitosa(){
        DAOAreaAcademicaImplementacion dao = new DAOAreaAcademicaImplementacion();
        int resultadoObtenido=dao.consultarIdDeAreaAcademicaPorArea("Humanidades");
        assertEquals(-1,resultadoObtenido);
    }
    
    @Test
    public void pruebaVerificarAreaAcademicaSinConexionExitosa(){
        DAOAreaAcademicaImplementacion dao = new DAOAreaAcademicaImplementacion();
        int resultado = dao.verificarAreaAcademica();
        assertEquals(-1,resultado);             
    }
    
}
