package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOAreaAcademicaImplementacion;
import logicaDeNegocio.clases.AreaAcademica;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

public class PruebaDAOAreaAcademicaImplementacionSinConexionExitosa {
    
    @BeforeClass
    public static void inicializar() {
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentaadmin@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        UsuarioSingleton.getInstancia(usuarioPrueba);
    }
    
    @Test
    public void pruebaConsultarAreasAcademicasSinConexionExitosa(){
        DAOAreaAcademicaImplementacion dao = new DAOAreaAcademicaImplementacion();
        AreaAcademica areaAcademicaEsperada = new AreaAcademica();
        areaAcademicaEsperada.setArea("Excepcion generada");
        List<AreaAcademica> areasAcademicasObtenidas = dao.consultarAreasAcademicas();
        assertEquals(areaAcademicaEsperada.getArea(),areasAcademicasObtenidas.get(0).getArea());        
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
