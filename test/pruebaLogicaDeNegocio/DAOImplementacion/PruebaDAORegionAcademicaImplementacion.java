package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAORegionAcademicaImplementacion;
import logicaDeNegocio.clases.RegionAcademica;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class PruebaDAORegionAcademicaImplementacion {
    
    @Before
    public void setUp() {
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentapruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        UsuarioSingleton.getInstancia(usuarioPrueba);
    }
    
    @Test
    public void pruebaRegistrarRegionAcademicaExitosa() {
        DAORegionAcademicaImplementacion dao = new DAORegionAcademicaImplementacion();
        RegionAcademica regionAcademica = new RegionAcademica();
        regionAcademica.setRegion("Tuxtepec");
        int resultado = dao.registrarRegionAcademica(regionAcademica);
        assertEquals(1, resultado);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void pruebaFallidaRegistrarRegionAcademica() {
        DAORegionAcademicaImplementacion dao = new DAORegionAcademicaImplementacion();
        RegionAcademica regionAcademica = new RegionAcademica();
        regionAcademica.setRegion("Xalapa2000");
        int resultado = dao.registrarRegionAcademica(regionAcademica);
    }
    
    @Test
    public void pruebaConsultarRegionesAcademicasExitosa() {
        DAORegionAcademicaImplementacion dao = new DAORegionAcademicaImplementacion();
        List<RegionAcademica> regionesAcademicas = dao.consultarRegionesAcademicas();
        assertEquals(9, regionesAcademicas.size());
    }
    
    @Test(expected = AssertionError.class)
    public void pruebaFallidaConsultarRegionesAcademicas() {
        DAORegionAcademicaImplementacion dao = new DAORegionAcademicaImplementacion();
        List<RegionAcademica> regionesAcademicas = dao.consultarRegionesAcademicas();
        assertEquals(6, regionesAcademicas.size());
    }
    
    @Test
    public void pruebaConsultarIdDeRegionPorRegionExitosa() {
        DAORegionAcademicaImplementacion dao = new DAORegionAcademicaImplementacion();
        int idRegion = dao.consultarIdDeRegionPorRegion("Veracruz");
        assertEquals(2, idRegion);
    }
    
    @Test
    public void pruebaFallidaConsultarIdDeRegionPorRegion() {
        DAORegionAcademicaImplementacion dao = new DAORegionAcademicaImplementacion();
        int idRegion = dao.consultarIdDeRegionPorRegion("Ciudad de México");
        assertEquals(0, idRegion);
    }
    
    @Test
    public void pruebaVerificarRegionExitosa() {
        DAORegionAcademicaImplementacion daoRegion = new DAORegionAcademicaImplementacion();
        int resultado = daoRegion.verificarRegion();
        assertTrue(resultado > 1);
    }

    @Test(expected = RuntimeException.class)
    public void pruebaVerificarRegionFallida() {
        DAORegionAcademicaImplementacion daoRegion = new DAORegionAcademicaImplementacion() {
            @Override
            public int verificarRegion() {
                throw new RuntimeException();
            }
        };
        daoRegion.verificarRegion();
    }
    
    @Test
    public void pruebaVerificarRegionSinValoresFallida() {
        DAORegionAcademicaImplementacion daoRegion = new DAORegionAcademicaImplementacion() {
            @Override
            public int verificarRegion() {
                return 0;
            }
        };
        int resultado = daoRegion.verificarRegion();
        assertEquals( 0, resultado);
    }

}
