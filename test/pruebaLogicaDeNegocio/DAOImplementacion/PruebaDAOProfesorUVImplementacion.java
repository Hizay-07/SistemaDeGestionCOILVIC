package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOProfesorUVImplementacion;
import logicaDeNegocio.clases.ProfesorUV;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class PruebaDAOProfesorUVImplementacion {
    
    private DAOProfesorUVImplementacion dao;
    
    @Before
    public void setUp() {
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentapruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        UsuarioSingleton.getInstancia(usuarioPrueba);
    }
    
    @Test
    public void pruebaRegistrarProfesorUVExitosa(){
        ProfesorUV profesorUV=new ProfesorUV();
        profesorUV.setNumeroDePersonal("1112");
        profesorUV.setCategoriaDeContratacion("Docente bases de datos");
        profesorUV.setTipoDeContratacion("Planta");
        profesorUV.setIdProfesor(15);
        profesorUV.setIdRegion(1);       
        profesorUV.setIdAreaAcademica(1);
        DAOProfesorUVImplementacion instancia=new DAOProfesorUVImplementacion();
        int resultadoEsperado=1;
        int resultadoObtenido=instancia.registrarProfesorUV(profesorUV);
        assertEquals(resultadoEsperado,resultadoObtenido);                                   
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaRegistrarProfesorUVFallido() {
        ProfesorUV profesor = new ProfesorUV();              
        profesor.setNumeroDePersonal(null);
        profesor.setTipoDeContratacion("Tiempo Completo");
        profesor.setCategoriaDeContratacion("A");
        profesor.setIdProfesor(999);
        profesor.setIdRegion(99);
        profesor.setIdAreaAcademica(1);
        DAOProfesorUVImplementacion dao=new DAOProfesorUVImplementacion();        
        int resultado = dao.registrarProfesorUV(profesor);
        assertEquals(0, resultado);
    }
    
    @Test
    public void pruebaConsultarProfesoresUVExitoso() {
        DAOProfesorUVImplementacion dao=new DAOProfesorUVImplementacion();        
        List<ProfesorUV> profesores = dao.consultarProfesoresUV();
        assertNotNull(profesores);
        assertFalse(profesores.isEmpty());
    }
    
    @Test
    public void pruebaConsultarProfesoresUVFracaso(){
        List<ProfesorUV> resultadoEsperado=new ArrayList<>();
        ProfesorUV profesorUV=new ProfesorUV();        
        resultadoEsperado.add(profesorUV);
        DAOProfesorUVImplementacion instancia=new DAOProfesorUVImplementacion();
        List<ProfesorUV> resultadoObtenido=new ArrayList<>();
        resultadoObtenido=instancia.consultarProfesoresUV();
        assertNotEquals(resultadoEsperado,resultadoObtenido);            
    }
    
    @Test
    public void pruebaEditarTipoDeContratacionDeProfesorUVPorNumeroDePersonalExitoso() {
        DAOProfesorUVImplementacion dao = new DAOProfesorUVImplementacion();
        int resultado = dao.editarTipoDeContratacionDeProfesorUVPorNumeroDePersonal("Tiempo completo", "1972");
        assertEquals(1, resultado);
    }

    @Test
    public void pruebaEditarTipoDeContratacionDeProfesorUVPorNumeroDePersonalFallido() {
        DAOProfesorUVImplementacion dao = new DAOProfesorUVImplementacion();
        int resultado = dao.editarTipoDeContratacionDeProfesorUVPorNumeroDePersonal("Tiempo Completo", "9999");
        assertEquals(0, resultado);
    }
        
    @Test
    public void pruebaEditarCategoriaDeContratacionDeProfesorUVPorNumeroDePersonalExitoso() {
        DAOProfesorUVImplementacion dao = new DAOProfesorUVImplementacion();
        int resultado = dao.editarCategoriaDeContratacionDeProfesorUVPorNumeroDePersonal("Investigador", "1972");
        assertEquals(1, resultado);
        dao.editarCategoriaDeContratacionDeProfesorUVPorNumeroDePersonal("Docente programacion", "1111");
    }
    
    @Test (expected = NullPointerException.class)
    public void pruebaEditarCategoriaDeContratacionDeProfesorUVPorNumeroDePersonalFallido() {
        int resultado = dao.editarCategoriaDeContratacionDeProfesorUVPorNumeroDePersonal("Investigador_becado01", "1972");
        assertEquals(0, resultado);
    }
    
    @Test
    public void pruebaEditarAreaAcademicaDeProfesorUVPorNumeroDePersonalExitoso() {
        DAOProfesorUVImplementacion dao = new DAOProfesorUVImplementacion();
        int resultado = dao.editarAreaAcademicaDeProfesorUVPorNumeroDePersonal(3, "1972");
        assertEquals(1, resultado);
        List<ProfesorUV> profesores = dao.consultarProfesoresUV();
        boolean encontrado = false;
        for (ProfesorUV profesor : profesores) {
            if (profesor.getNumeroDePersonal().equals("1972") && profesor.getIdAreaAcademica() == 3) {
                encontrado = true;
                break;
            }
        }
        assertTrue(encontrado);
        dao.editarAreaAcademicaDeProfesorUVPorNumeroDePersonal(2, "1972");
    }
    
    @Test
    public void pruebaEditarAreaAcademicaDeProfesorUVPorNumeroDePersonalFallido() {
        DAOProfesorUVImplementacion dao = new DAOProfesorUVImplementacion();
        int resultado = dao.editarAreaAcademicaDeProfesorUVPorNumeroDePersonal(3, "9999");
        assertEquals(0, resultado);
        List<ProfesorUV> profesores = dao.consultarProfesoresUV();
        boolean encontrado = false;
        for (ProfesorUV profesor : profesores) {
            if (profesor.getNumeroDePersonal().equals("9999")) {
                encontrado = true;
                break;
            }
        }
        assertFalse(encontrado);
    }
    
    @Test
    public void pruebaEditarRegionDeProfesorUVPorNumeroDePersonalExitoso() {
        DAOProfesorUVImplementacion dao = new DAOProfesorUVImplementacion();
        int resultado = dao.editarRegionDeProfesorUVPorNumeroDePersonal(2, "1111");
        assertEquals(1, resultado);
        dao.editarRegionDeProfesorUVPorNumeroDePersonal(1, "1111");
    }
    
    @Test
    public void pruebaEditarRegionDeProfesorUVPorNumeroDePersonalFallido() {
        DAOProfesorUVImplementacion dao = new DAOProfesorUVImplementacion();
        int resultado = dao.editarRegionDeProfesorUVPorNumeroDePersonal(2, "9999");
        assertEquals(0, resultado);
    }
    
    @Test
    public void pruebaEliminarProfesorUVExitoso() {
        DAOProfesorUVImplementacion dao = new DAOProfesorUVImplementacion();
        ProfesorUV profesorUV = new ProfesorUV();
        profesorUV.setNumeroDePersonal("1234");
        profesorUV.setTipoDeContratacion("Temporal");
        profesorUV.setCategoriaDeContratacion("Profesor");
        profesorUV.setIdProfesor(16);
        profesorUV.setIdRegion(4);
        profesorUV.setIdAreaAcademica(2);
        int resultadoAgregar = dao.registrarProfesorUV(profesorUV);
        assertEquals(1, resultadoAgregar);
        List<ProfesorUV> profesores = dao.consultarProfesoresUV();
        boolean encontrado = false;
        for (ProfesorUV profesor : profesores) {
            if (profesor.getNumeroDePersonal().equals("1234")) {
                encontrado = true;
                break;
            }
        }
        assertTrue(encontrado);
        int resultadoEliminar = dao.eliminarProfesorUV("lizeliminar@gmail.com");
        assertEquals(1, resultadoEliminar);
        profesores = dao.consultarProfesoresUV();
        encontrado = false;
        for (ProfesorUV profesor : profesores) {
            if (profesor.getNumeroDePersonal().equals("1234")) {
                encontrado = true;
                break;
            }
        }
        assertFalse(encontrado);
    }

    @Test (expected = AssertionError.class)
    public void pruebaEliminarProfesorUVFallido() {
        DAOProfesorUVImplementacion dao = new DAOProfesorUVImplementacion();
        int resultado = dao.eliminarProfesorUV("correoInexistente@uv.mx");
        assertEquals(0, resultado);
        resultado = dao.eliminarProfesorUV(null);
        assertEquals(-1, resultado);
    }

}
