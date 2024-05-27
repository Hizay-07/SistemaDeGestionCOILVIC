package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOAreaAcademicaImplementacion;
import logicaDeNegocio.clases.AreaAcademica;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
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
    public void pruebaRegistrarAreaAcademicaExitosa() {
        DAOAreaAcademicaImplementacion dao = new DAOAreaAcademicaImplementacion();
        AreaAcademica areaAcademica = new AreaAcademica();
        areaAcademica.setArea("Ingeniería");
        int resultado = dao.registrarAreaAcademica(areaAcademica);
        assertEquals(1, resultado);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void pruebaFallidaRegistrarAreaAcademica() {
        DAOAreaAcademicaImplementacion dao = new DAOAreaAcademicaImplementacion();
        AreaAcademica areaAcademica = new AreaAcademica();
        areaAcademica.setArea("Artes_Plasticas");
        int resultado = dao.registrarAreaAcademica(areaAcademica);
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
    public void pruebaConsultarAreasAcademicasExitosa() {
        DAOAreaAcademicaImplementacion dao = new DAOAreaAcademicaImplementacion();
        List<AreaAcademica> areasAcademicas = dao.consultarAreasAcademicas();
        assertEquals(7, areasAcademicas.size());
    }

    
    @Test(expected = AssertionError.class)
    public void pruebaFallidaConsultarAreasAcademicas() {
        DAOAreaAcademicaImplementacion dao = new DAOAreaAcademicaImplementacion();
        List<AreaAcademica> areasAcademicas = dao.consultarAreasAcademicas();
        assertEquals(5, areasAcademicas.size());
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
    public void pruebaFallidaConsultarIdDeAreaAcademicaPorArea() {
        DAOAreaAcademicaImplementacion dao = new DAOAreaAcademicaImplementacion();
        int idArea = dao.consultarIdDeAreaAcademicaPorArea("Música");
        assertEquals(0, idArea);
    }

}
