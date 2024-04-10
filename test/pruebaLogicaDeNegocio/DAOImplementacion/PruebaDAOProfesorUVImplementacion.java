package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOProfesorUVImplementacion;
import logicaDeNegocio.clases.ProfesorUV;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PruebaDAOProfesorUVImplementacion {
    
    @Test
    public void pruebaRegistrarProfesorUVExitosa(){
        ProfesorUV profesorUV=new ProfesorUV();
        profesorUV.setNumeroDePersonal("S22010203");
        profesorUV.setCategoriaDeContratacion("Docente T.C.");
        profesorUV.setTipoDeContratacion("Planta");
        profesorUV.setIdProfesor(1);
        profesorUV.setIdRegion(1);       
        profesorUV.setIdAreaAcademica(1);
        DAOProfesorUVImplementacion instancia=new DAOProfesorUVImplementacion();
        int resultadoEsperado=1;
        int resultadoObtenido=instancia.registrarProfesorUV(profesorUV);
        assertEquals(resultadoEsperado,resultadoObtenido);                                   
    }
    
    @Test
    public void pruebaConsultarProfesoresUVExitosa(){        
        List<ProfesorUV> resultadoEsperado=new ArrayList<>();
        ProfesorUV profesorUV=new ProfesorUV();
        profesorUV.setNumeroDePersonal("S22010203");
        resultadoEsperado.add(profesorUV);
        DAOProfesorUVImplementacion instancia=new DAOProfesorUVImplementacion();
        List<ProfesorUV> resultadoObtenido=new ArrayList<>();
        resultadoObtenido=instancia.consultarProfesoresUV();
        assertEquals(resultadoEsperado,resultadoObtenido);                                                   
    }
    
    @Test
    public void pruebaConsultarProfesoresUVPorAreaAcademicaExitosa(){
        List<ProfesorUV> resultadoEsperado=new ArrayList<>();
        ProfesorUV profesorUV=new ProfesorUV();
        profesorUV.setNumeroDePersonal("S22010203");
        resultadoEsperado.add(profesorUV);
        DAOProfesorUVImplementacion instancia=new DAOProfesorUVImplementacion();
        List<ProfesorUV> resultadoObtenido=new ArrayList<>();
        int idAreaAcademica=2;
        resultadoObtenido=instancia.consultarProfesoresUVPorAreaAcademica(idAreaAcademica);
        assertEquals(resultadoEsperado,resultadoObtenido);                                           
    }
    
    @Test
    public void pruebaConsultarProfesoresUVPorRegionExitosa(){
        List<ProfesorUV> resultadoEsperado=new ArrayList<>();
        ProfesorUV profesorUV=new ProfesorUV();
        profesorUV.setNumeroDePersonal("S22010203");
        resultadoEsperado.add(profesorUV);
        DAOProfesorUVImplementacion instancia=new DAOProfesorUVImplementacion();
        List<ProfesorUV> resultadoObtenido=new ArrayList<>();
        int idRegion=2;
        resultadoObtenido=instancia.consultarProfesoresUVPorRegion(idRegion);
        assertEquals(resultadoEsperado,resultadoObtenido);                                                   
    }
    
    @Test
    public void pruebaEditarTipoDeContratacionDeProfesorUVPorNumeroDePersonalExitosa(){
        DAOProfesorUVImplementacion instancia=new DAOProfesorUVImplementacion();
        String numeroDePersonal="S22010203";
        String tipoDeContratacion="Interino por plaza";
        int resultadoEsperado=1;
        int resultadoObtenido=instancia.editarTipoDeContratacionDeProfesorUVPorNumeroDePersonal(tipoDeContratacion, numeroDePersonal);
        assertEquals(resultadoEsperado,resultadoObtenido);                                                   
    }
    
    @Test
    public void pruebaEditarCategoriaDeContratacionDeProfesorUVPorNumeroDePersonalExitosa(){
        DAOProfesorUVImplementacion instancia=new DAOProfesorUVImplementacion();
        String numeroDePersonal="S22010203";
        String categoriaDeContratacion="Investigador T.C.";
        int resultadoEsperado=1;
        int resultadoObtenido=instancia.editarCategoriaDeContratacionDeProfesorUVPorNumeroDePersonal(categoriaDeContratacion, numeroDePersonal);
        assertEquals(resultadoEsperado,resultadoObtenido);
    }
    
    @Test
    public void pruebaEditarAreaAcademicaDeProfesorUVPorNumeroDePersonalExitosa(){
        DAOProfesorUVImplementacion instancia=new DAOProfesorUVImplementacion();
        int idAreaAcademica=2;
        String numeroDePersonal="S22010203";
        int resultadoEsperado=1;
        int resultadoObtenido=instancia.editarAreaAcademicaDeProfesorUVPorNumeroDePersonal(idAreaAcademica, numeroDePersonal);
        assertEquals(resultadoEsperado,resultadoObtenido);                
    }
    
    @Test
    public void pruebaEditarRegionDeProfesorUVPorNumeroDePersonalExitosa(){
        DAOProfesorUVImplementacion instancia=new DAOProfesorUVImplementacion();
        int idRegion=2;
        String numeroDePersonal="S22010203";
        int resultadoEsperado=1;
        int resultadoObtenido=instancia.editarRegionDeProfesorUVPorNumeroDePersonal(idRegion, numeroDePersonal);
        assertEquals(resultadoEsperado,resultadoObtenido);        
    }
    
    @Test
    public void pruebaRegistrarAreaAcademicaExitosa(){
        DAOProfesorUVImplementacion instancia=new DAOProfesorUVImplementacion();
        String areaAcademica="Economico-administrativa";
        int resultadoEsperado=1;
        int resultadoObtenido=instancia.registrarAreaAcademica(areaAcademica);
        assertEquals(resultadoEsperado,resultadoObtenido);
    }
    
    @Test
    public void pruebaRegistrarRegionExitosa(){
        DAOProfesorUVImplementacion instancia=new DAOProfesorUVImplementacion();
        String region="Orizaba-Cordoba";
        int resultadoEsperado=1;
        int resultadoObtenido=instancia.registrarRegion(region);
        assertEquals(resultadoEsperado,resultadoObtenido);                
    }
}
