package pruebaLogicaDeNegocio.DAOImplementacion;

import java.io.File;
import logicaDeNegocio.DAOImplementacion.DAOEvidenciaImplementacion;
import logicaDeNegocio.clases.Evidencia;
import logicaDeNegocio.clases.Actividad;
import logicaDeNegocio.clases.Colaboracion;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;

public class PruebaDAOEvidenciaImplementacion {
    
    @Before
    public void setUp() {
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentapruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        UsuarioSingleton.getInstancia(usuarioPrueba);
    }
    
    @Test
    public void pruebaRegistrarEvidenciaExitosa(){
        DAOEvidenciaImplementacion pruebaDeMetodo = new DAOEvidenciaImplementacion();
        Evidencia evidenciaPrueba = new Evidencia();
        evidenciaPrueba.setNombre("Evidencia de prueba");
        evidenciaPrueba.setRutaEvidencia("Informes//Evidenciapruebauno");
        evidenciaPrueba.setIdActividad(1);
        int resultado = pruebaDeMetodo.agregarEvidencia(evidenciaPrueba);
        assertEquals(1,resultado);
    }
            
    @Test 
    public void pruebaRegistrarEvidenciaFallida(){
        DAOEvidenciaImplementacion pruebaEvidencia = new DAOEvidenciaImplementacion();
        Evidencia evidenciaPrueba = new Evidencia();
        evidenciaPrueba.setNombre("Prueba fallida");
        evidenciaPrueba.setRutaEvidencia("C://Users//oscar//OneDrive//--Coilvic");
        evidenciaPrueba.setIdActividad(1);
        int resultado = pruebaEvidencia.agregarEvidencia(evidenciaPrueba);
        assertEquals(1, resultado);     
    }
    
    @Test ()
    public void pruebaModificarEvidenciaExitosa(){
        DAOEvidenciaImplementacion pruebaDeMetodo = new DAOEvidenciaImplementacion();
        Evidencia evidenciaPrueba = new Evidencia();
        evidenciaPrueba.setNombre("Evidencia de prueba");
        evidenciaPrueba.setRutaEvidencia("Informes//Escritor.java");
        evidenciaPrueba.setIdActividad(2);
        evidenciaPrueba.setIdEvidencia(2);
        int resultado = pruebaDeMetodo.modificarEvidencia(evidenciaPrueba);
        assertEquals(1,resultado);
    }
    
    @Test (expected = AssertionError.class)
    public void pruebaModificarEvidenciaFallida(){
        DAOEvidenciaImplementacion pruebaDeMetodo = new DAOEvidenciaImplementacion();
        Evidencia evidenciaPrueba = new Evidencia();
        evidenciaPrueba.setNombre("Prueba modificar evidencia fallida");
        evidenciaPrueba.setRutaEvidencia("C://Users//OneDive--CoilvicFalllida");
        evidenciaPrueba.setIdActividad(1);
        int resultado = pruebaDeMetodo.modificarEvidencia(evidenciaPrueba);
        assertEquals(1, resultado);
    }    
    
    @Test
    public void pruebaObtenerEvidenciasDeActividadExitosa() {
        int idActividad = 1; 
        DAOEvidenciaImplementacion dao = new DAOEvidenciaImplementacion();
        List<Evidencia> evidencias = dao.obtenerEvidenciasDeActividad(idActividad);
        assertNotNull(evidencias);
    }
    
    @Test
    public void pruebaObtenerEvidenciasDeActividadFallida() {
        int idActividad = -1; 
        DAOEvidenciaImplementacion dao = new DAOEvidenciaImplementacion();
        List<Evidencia> evidencias = dao.obtenerEvidenciasDeActividad(idActividad);
        assertEquals(0, evidencias.size());
    }
    
    @Test
    public void pruebaObtenerNumeroDeEvidenciasExitosa(){
        int idActividad = 1; 
        DAOEvidenciaImplementacion dao = new DAOEvidenciaImplementacion();
        int numeroActividad = dao.obtenerNumeroDeEvidencia(idActividad);
        assertEquals(0,numeroActividad);
    }
    
    @Test
    public void pruebaObtenerNumeroDeEvidenciasFallida(){
        int idActividad = 1; 
        DAOEvidenciaImplementacion dao = new DAOEvidenciaImplementacion();
        int numeroActividad = dao.obtenerNumeroDeEvidencia(idActividad);
        assertEquals(-1,numeroActividad);
    }

}
