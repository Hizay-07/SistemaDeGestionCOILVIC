package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAORegionAcademicaImplementacion;
import logicaDeNegocio.clases.RegionAcademica;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

public class PruebaDAORegionAcademicaImplementacionSinConexionExitosa {
    
    @BeforeClass
    public static void inicializar() {
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentaadmin@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        UsuarioSingleton.getInstancia(usuarioPrueba);
    }
    
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
