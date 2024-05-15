package pruebaLogicaDeNegocio.DAOImplementacion;

import java.io.File;
import logicaDeNegocio.DAOImplementacion.DAOEvidenciaImplementacion;
import logicaDeNegocio.clases.Evidencia;
import logicaDeNegocio.clases.Actividad;
import logicaDeNegocio.clases.Colaboracion;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class PruebaDAOEvidenciaImplementacion {
    
    @Test
    public void pruebaRegistrarEvidenciaExitosa(){
        DAOEvidenciaImplementacion pruebaDeMetodo = new DAOEvidenciaImplementacion();
        Evidencia evidenciaPrueba = new Evidencia();
        evidenciaPrueba.setNombre("Evidencia de prueba");
        evidenciaPrueba.setRutaEvidencia("C:\\Users\\chris\\OneDrive\\Escritorio\\Universidad");
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
    
    @Test
    public void pruebaModificarEvidenciaExitosa(){
        DAOEvidenciaImplementacion pruebaDeMetodo = new DAOEvidenciaImplementacion();
        Evidencia evidenciaPrueba = new Evidencia();
        evidenciaPrueba.setNombre("Evidencia de prueba");
        evidenciaPrueba.setRutaEvidencia("C:\\Users\\chris\\OneDrive\\Escritorio\\Universidad\\Escritor.java");
        evidenciaPrueba.setIdActividad(1);
        evidenciaPrueba.setIdEvidencia(1);
        int resultado = pruebaDeMetodo.modificarEvidencia(evidenciaPrueba);
        assertEquals(1,resultado);
    }
    
    @Test 
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
    public void pruebaObtenerEvidenciaDeActividad(){
        DAOEvidenciaImplementacion pruebaDeMetodo = new DAOEvidenciaImplementacion();
        List<Evidencia> evidenciasEsperadas = new ArrayList();
        List<Evidencia> evidenciasObtenidas = new ArrayList();
        Actividad actividadPrueba = new Actividad();
        Evidencia evidenciaPrueba = new Evidencia();
        actividadPrueba.setIdActividad(1);
        evidenciaPrueba.setNombre("Evidencia de prueba");
        evidenciaPrueba.setRutaEvidencia("C:\\Users\\chris\\OneDrive\\Escritorio\\Universidad\\Escritor.java");
        evidenciaPrueba.setIdActividad(1);
        evidenciaPrueba.setIdEvidencia(1);
        evidenciasEsperadas.add(evidenciaPrueba);
        evidenciasObtenidas = pruebaDeMetodo.obtenerEvidenciasDeActividad(1);
        assertEquals(evidenciasEsperadas,evidenciasObtenidas);
    }
    
    @Test
    public void pruebaFlujoFallidoObtenerEvidenciaDeActividad(){
        DAOEvidenciaImplementacion pruebaDeMetodo = new DAOEvidenciaImplementacion();
        List<Evidencia> evidenciasEsperadas = new ArrayList();
        List<Evidencia> evidenciasObtenidas = new ArrayList();
        Actividad actividadPrueba = new Actividad();
        Evidencia evidenciaPrueba = new Evidencia();
        actividadPrueba.setIdActividad(1);
        evidenciaPrueba.setNombre("Evidencia de prueba");
        evidenciaPrueba.setRutaEvidencia("C:\\Users\\chris\\OneDrive\\Escritorio");
        evidenciaPrueba.setIdActividad(1);
        evidenciaPrueba.setIdEvidencia(1);
        evidenciasEsperadas.add(evidenciaPrueba);
        evidenciasObtenidas = pruebaDeMetodo.obtenerEvidenciasDeActividad(1);
        assertNotEquals(evidenciasEsperadas,evidenciasObtenidas);
    }

}
