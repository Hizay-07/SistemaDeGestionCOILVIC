package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAORegionAcademicaImplementacion;
import logicaDeNegocio.clases.RegionAcademica;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;

public class PruebaDAORegionAcademicaImplementacion {
    
    @Test
    public void pruebaRegistrarRegionAcademicaExitosa(){
        RegionAcademica regionAcademica=new RegionAcademica();
        regionAcademica.setRegion("Poza Rica-Tuxpan");
        DAORegionAcademicaImplementacion instancia=new DAORegionAcademicaImplementacion();
        int resultadoEsperado=1;
        int resultadoObtenido=instancia.registrarRegionAcademica(regionAcademica);
        assertEquals(resultadoEsperado,resultadoObtenido);                
    }
    
    @Test
    public void pruebaRegistrarRegionAcademicaFracaso(){
        RegionAcademica regionAcademica=new RegionAcademica();        
        DAORegionAcademicaImplementacion instancia=new DAORegionAcademicaImplementacion();
        int resultadoEsperado=0;
        int resultadoObtenido=instancia.registrarRegionAcademica(regionAcademica);
        assertEquals(resultadoEsperado,resultadoObtenido);         
    }
    
    @Test
    public void pruebaConsultarRegionesAcademicasExitosa(){
        RegionAcademica regionAcademica=new RegionAcademica();
        regionAcademica.setRegion("Xalapa");
        RegionAcademica regionAcademica2=new RegionAcademica();
        regionAcademica2.setRegion("Veracruz");
        RegionAcademica regionAcademica3=new RegionAcademica();
        regionAcademica3.setRegion("Orizaba-Cordoba");
        RegionAcademica regionAcademica4=new RegionAcademica();
        regionAcademica4.setRegion("Poza Rica-Tuxpan");
        List<RegionAcademica> resultadoEsperado=new ArrayList<>();
        resultadoEsperado.add(regionAcademica);
        resultadoEsperado.add(regionAcademica2);
        resultadoEsperado.add(regionAcademica3);
        resultadoEsperado.add(regionAcademica4);
        List<RegionAcademica> resultadoObtenido=new ArrayList<>();
        DAORegionAcademicaImplementacion instancia=new DAORegionAcademicaImplementacion();
        resultadoObtenido=instancia.consultarRegionesAcademicas();
        assertEquals(resultadoEsperado,resultadoObtenido);                        
    }
    
    @Test
    public void pruebaConsultarRegionesAcademicasFracaso(){
        RegionAcademica regionAcademica=new RegionAcademica();
        regionAcademica.setRegion("Xalapa");        
        List<RegionAcademica> resultadoEsperado=new ArrayList<>();
        resultadoEsperado.add(regionAcademica);        
        List<RegionAcademica> resultadoObtenido=new ArrayList<>();
        DAORegionAcademicaImplementacion instancia=new DAORegionAcademicaImplementacion();
        resultadoObtenido=instancia.consultarRegionesAcademicas();
        assertNotEquals(resultadoEsperado,resultadoObtenido);        
    }
    
    @Test
    public void pruebaConsultarIdDeRegionPorRegionExitosa(){
        DAORegionAcademicaImplementacion instancia=new DAORegionAcademicaImplementacion();
        String region="Xalapa";
        int resultadoEsperado=1;
        int resultadoObtenido=instancia.consultarIdDeRegionPorRegion(region);
        assertEquals(resultadoEsperado,resultadoObtenido);        
    }
}
