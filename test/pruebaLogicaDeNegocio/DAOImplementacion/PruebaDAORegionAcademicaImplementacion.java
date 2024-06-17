package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAORegionAcademicaImplementacion;
import logicaDeNegocio.clases.RegionAcademica;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.BeforeClass;
import org.junit.Test;

public class PruebaDAORegionAcademicaImplementacion {
    
    @BeforeClass
    public static void inicializar() {
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentapruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        UsuarioSingleton.getInstancia(usuarioPrueba);
    }
        
    @Test
    public void pruebaConsultarRegionesAcademicasExitosa() {
        DAORegionAcademicaImplementacion dao = new DAORegionAcademicaImplementacion();
        RegionAcademica regionXalapa=new RegionAcademica();
        regionXalapa.setRegion("Xalapa");
        RegionAcademica regionVeracruz=new RegionAcademica();
        regionVeracruz.setRegion("Veracruz");
        RegionAcademica regionOrizaba=new RegionAcademica();
        regionOrizaba.setRegion("Orizaba-Córdoba");
        RegionAcademica regionPozaRica=new RegionAcademica();
        regionPozaRica.setRegion("Poza Rica-Tuxpan");
        RegionAcademica regionMinatitlan=new RegionAcademica();
        regionMinatitlan.setRegion("Coatzacoalcos-Minatitlán");
        List<RegionAcademica> regionesAcademicasEsperadas=new ArrayList<>();
        regionesAcademicasEsperadas.add(regionXalapa);
        regionesAcademicasEsperadas.add(regionVeracruz);
        regionesAcademicasEsperadas.add(regionOrizaba);
        regionesAcademicasEsperadas.add(regionPozaRica);
        regionesAcademicasEsperadas.add(regionMinatitlan);        
        List<RegionAcademica> regionesAcademicasObtenidas = dao.consultarRegionesAcademicas();
        assertEquals(regionesAcademicasEsperadas,regionesAcademicasObtenidas);
    }
    
    @Test
    public void pruebaConsultarRegionesAcademicasFallida() {
        DAORegionAcademicaImplementacion dao = new DAORegionAcademicaImplementacion();
        RegionAcademica regionXalapa=new RegionAcademica();
        regionXalapa.setRegion("Xalapa");
        List<RegionAcademica> regionesAcademicasEsperadas=new ArrayList<>();
        regionesAcademicasEsperadas.add(regionXalapa);
        List<RegionAcademica> regionesAcademicasObtenidas = dao.consultarRegionesAcademicas();
        assertNotEquals(regionesAcademicasEsperadas, regionesAcademicasObtenidas);
    }       
    
    @Test
    public void pruebaConsultarIdDeRegionPorRegionExitosa() {
        DAORegionAcademicaImplementacion dao = new DAORegionAcademicaImplementacion();
        int idRegion = dao.consultarIdDeRegionPorRegion("Veracruz");
        assertEquals(2, idRegion);
    }
    
    @Test
    public void pruebaConsultarIdDeRegionPorRegionFallida() {
        DAORegionAcademicaImplementacion dao = new DAORegionAcademicaImplementacion();
        int idRegion = dao.consultarIdDeRegionPorRegion("Ciudad de México");
        assertEquals(0, idRegion);
    }      
    
    @Test
    public void pruebaVerificarRegionExitosa() {
        DAORegionAcademicaImplementacion daoRegion = new DAORegionAcademicaImplementacion();
        int resultado = daoRegion.verificarRegion();
        assertEquals(5,resultado);
    }

    @Test
    public void pruebaVerificarRegionFallida() {
        DAORegionAcademicaImplementacion daoRegion = new DAORegionAcademicaImplementacion();
        int resultado = daoRegion.verificarRegion();
        assertNotEquals(0,resultado);       
    }     

}
