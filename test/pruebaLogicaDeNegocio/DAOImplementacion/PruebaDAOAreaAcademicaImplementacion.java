package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOAreaAcademicaImplementacion;
import logicaDeNegocio.clases.AreaAcademica;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;

public class PruebaDAOAreaAcademicaImplementacion {
    
    @Test
    public void pruebaRegistrarAreaAcademicaExitosa(){
        AreaAcademica areaAcademica=new AreaAcademica();
        areaAcademica.setArea("Ciencias de la salud");
        int resultadoEsperado=1;
        DAOAreaAcademicaImplementacion instancia=new DAOAreaAcademicaImplementacion();
        int resultadoObtenido=instancia.registrarAreaAcademica(areaAcademica);
        assertEquals(resultadoEsperado,resultadoObtenido);
    }
    
    @Test
    public void pruebaRegistrarAreaAcademicaFracaso(){
        AreaAcademica areaAcademica=new AreaAcademica();        
        int resultadoEsperado=0;
        DAOAreaAcademicaImplementacion instancia=new DAOAreaAcademicaImplementacion();
        int resultadoObtenido=instancia.registrarAreaAcademica(areaAcademica);
        assertEquals(resultadoEsperado,resultadoObtenido);        
    }
    
    @Test
    public void pruebaConsultarAreasAcademicasExitosa(){
        AreaAcademica areaAcademica=new AreaAcademica();
        areaAcademica.setArea("Humanidades");
        AreaAcademica areaAcademica2=new AreaAcademica();
        areaAcademica2.setArea("Tecnica");
        AreaAcademica areaAcademica3=new AreaAcademica();
        areaAcademica3.setArea("Ciencias de la salud");
        List<AreaAcademica> resultadoEsperado=new ArrayList<>();
        resultadoEsperado.add(areaAcademica);
        resultadoEsperado.add(areaAcademica2);
        resultadoEsperado.add(areaAcademica3);
        List<AreaAcademica> resultadoObtenido=new ArrayList<>();
        DAOAreaAcademicaImplementacion instancia=new DAOAreaAcademicaImplementacion();
        resultadoObtenido=instancia.consultarAreasAcademicas();
        assertEquals(resultadoEsperado,resultadoObtenido);        
    }
    
    @Test
    public void pruebaConsultarAreasAcademicasFracaso(){
        AreaAcademica areaAcademica=new AreaAcademica();
        areaAcademica.setArea("Humanidades");       
        List<AreaAcademica> resultadoEsperado=new ArrayList<>();
        resultadoEsperado.add(areaAcademica);
        List<AreaAcademica> resultadoObtenido=new ArrayList<>();
        DAOAreaAcademicaImplementacion instancia=new DAOAreaAcademicaImplementacion();
        resultadoObtenido=instancia.consultarAreasAcademicas();
        assertNotEquals(resultadoEsperado,resultadoObtenido);         
    }    
    
    @Test
    public void pruebaConsultarIdDeAreaAcademicaPorAreaExitosa(){
        DAOAreaAcademicaImplementacion instancia=new DAOAreaAcademicaImplementacion();
        String area="Humanidades";
        int resultadoEsperado=1;
        int resultadoObtenido=instancia.consultarIdDeAreaAcademicaPorArea(area);
        assertEquals(resultadoEsperado,resultadoObtenido);            
    }
}
