package pruebaLogicaDeNegocio.DAOImplementacion;

import logicaDeNegocio.DAOImplementacion.DAOEvidenciaImplementacion;
import logicaDeNegocio.clases.Evidencia;
import logicaDeNegocio.clases.Actividad;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

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
        evidenciasObtenidas = pruebaDeMetodo.obtenerEvidenciasDeActividad(actividadPrueba);
        assertEquals(evidenciasEsperadas,evidenciasObtenidas);
    }
    
}
