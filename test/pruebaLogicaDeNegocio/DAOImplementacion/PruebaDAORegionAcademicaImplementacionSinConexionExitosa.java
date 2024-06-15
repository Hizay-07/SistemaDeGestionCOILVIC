package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAORegionAcademicaImplementacion;
import logicaDeNegocio.clases.RegionAcademica;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PruebaDAORegionAcademicaImplementacionSinConexionExitosa {
    
    @Test
    public void pruebaConsultarRegionesAcademicasSinConexionExitosa(){
        DAORegionAcademicaImplementacion dao = new DAORegionAcademicaImplementacion();
        List<RegionAcademica> regionesAcademicasObtenidas = dao.consultarRegionesAcademicas();
        RegionAcademica regionAcademicaEsperada = new RegionAcademica();
        regionAcademicaEsperada.setRegion("Excepcion");
        assertEquals(regionAcademicaEsperada.getRegion(),regionesAcademicasObtenidas.get(0).getRegion());        
    }
    
    @Test
    public void pruebaConsultarIdDeRegionPorRegionSinConexionExitosa(){
        DAORegionAcademicaImplementacion dao = new DAORegionAcademicaImplementacion();
        int idRegion = dao.consultarIdDeRegionPorRegion("Veracruz");
        assertEquals(-1, idRegion);        
    }
    
    @Test
    public void pruebaVerificarRegionSinConexionExitosa(){
        DAORegionAcademicaImplementacion daoRegion = new DAORegionAcademicaImplementacion();
        int resultado = daoRegion.verificarRegion();
        assertEquals(-1,resultado);         
    }
    
}
