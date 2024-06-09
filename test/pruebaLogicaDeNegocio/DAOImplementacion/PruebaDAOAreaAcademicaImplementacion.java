package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOAreaAcademicaImplementacion;
import logicaDeNegocio.clases.AreaAcademica;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class PruebaDAOAreaAcademicaImplementacion {
    
    @Before
    public void setUp() {
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentapruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        UsuarioSingleton.getInstancia(usuarioPrueba);
    }
    
    @Test
    public void pruebaConsultarAreasAcademicasExitosa() {
        DAOAreaAcademicaImplementacion dao = new DAOAreaAcademicaImplementacion();
        List<AreaAcademica> areasAcademicasEsperadas=new ArrayList<>();
        AreaAcademica areaArtes=new AreaAcademica();
        areaArtes.setArea("Artes");
        AreaAcademica areaCienciasSalud=new AreaAcademica();
        areaCienciasSalud.setArea("Ciencias de la salud");
        AreaAcademica areaHumanidades=new AreaAcademica();
        areaHumanidades.setArea("Humanidades");
        AreaAcademica areaBiologica=new AreaAcademica();
        areaBiologica.setArea("Ciencias biológicas y agropecuarias");
        AreaAcademica areaEconomica=new AreaAcademica();
        areaEconomica.setArea("Económico-Administrativa");
        AreaAcademica areaTecnica=new AreaAcademica();
        areaTecnica.setArea("Técnica");
        areasAcademicasEsperadas.add(areaArtes);
        areasAcademicasEsperadas.add(areaCienciasSalud);
        areasAcademicasEsperadas.add(areaHumanidades);
        areasAcademicasEsperadas.add(areaBiologica);
        areasAcademicasEsperadas.add(areaEconomica);
        areasAcademicasEsperadas.add(areaTecnica);        
        List<AreaAcademica> areasAcademicasObtenidas = dao.consultarAreasAcademicas();
        assertEquals(areasAcademicasEsperadas,areasAcademicasObtenidas);
    }
    
    @Test
    public void pruebaConsultarAreasAcademicasFallida() {
        DAOAreaAcademicaImplementacion dao = new DAOAreaAcademicaImplementacion();        
        AreaAcademica areaArtes=new AreaAcademica();
        areaArtes.setArea("Artes");
        List<AreaAcademica> areasAcademicasEsperadas=new ArrayList<>();
        areasAcademicasEsperadas.add(areaArtes);
        List<AreaAcademica> areasAcademicasObtenidas = dao.consultarAreasAcademicas();
        assertNotEquals(areasAcademicasEsperadas,areasAcademicasObtenidas);
    }
        
    @Test
    public void pruebaConsultarAreasAcademicasSinConexionExitosa(){
        DAOAreaAcademicaImplementacion dao = new DAOAreaAcademicaImplementacion();                                        
        List<AreaAcademica> areasAcademicasObtenidas = dao.consultarAreasAcademicas();
        assertTrue(areasAcademicasObtenidas.isEmpty());        
    }
       
    @Test
    public void pruebaConsultarIdDeAreaAcademicaPorAreaExitosa(){
        DAOAreaAcademicaImplementacion instancia=new DAOAreaAcademicaImplementacion();
        String area="Humanidades";
        int resultadoEsperado=3;
        int resultadoObtenido=instancia.consultarIdDeAreaAcademicaPorArea(area);
        assertEquals(resultadoEsperado,resultadoObtenido);          
    }
    
    @Test
    public void pruebaConsultarIdDeAreaAcademicaPorAreaFallida() {
        DAOAreaAcademicaImplementacion dao = new DAOAreaAcademicaImplementacion();
        int idArea = dao.consultarIdDeAreaAcademicaPorArea("Música");
        assertEquals(0, idArea);
    }
            
    @Test
    public void pruebaConsultarIdDeAreaAcademicaPorAreaSinConexionExitosa(){
        DAOAreaAcademicaImplementacion dao = new DAOAreaAcademicaImplementacion();
        int resultadoObtenido=dao.consultarIdDeAreaAcademicaPorArea("Humanidades");
        assertEquals(-1,resultadoObtenido);
    }
    
    @Test
    public void pruebaVerificarAreaAcademicaExitosa() {
        DAOAreaAcademicaImplementacion dao = new DAOAreaAcademicaImplementacion();
        int resultado = dao.verificarAreaAcademica();
        assertEquals(6,resultado);
    }

    @Test
    public void pruebaVerificarAreaAcademicaFallida() {
        DAOAreaAcademicaImplementacion dao = new DAOAreaAcademicaImplementacion();
        int resultado = dao.verificarAreaAcademica();
        assertEquals(0,resultado);        
    }
        
    @Test
    public void pruebaVerificarAreaAcademicaSinConexionExitosa(){
        DAOAreaAcademicaImplementacion dao = new DAOAreaAcademicaImplementacion();
        int resultado = dao.verificarAreaAcademica();
        assertEquals(-1,resultado);             
    }

}
