package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOActividadImplementacion;
import logicaDeNegocio.clases.Actividad;
import logicaDeNegocio.enums.EnumActividades;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

public class PruebaDAOActividadImplementacion {
    
    @Test
    public void pruebaRegistrarActividadExitosa(){
        DAOActividadImplementacion implementacion = new DAOActividadImplementacion();
        Actividad actividadPrueba = new Actividad();
        actividadPrueba.setNombre("Introduccion a colaboracion");
        actividadPrueba.setDescripcion("Poder entender el objetivo");
        actividadPrueba.setFechaDeInicio("2024-04-04");
        actividadPrueba.setFechaDeCierre("2024-04-10");
        actividadPrueba.setIdColaboracion(1);
        actividadPrueba.setNumeroActividad(25);
        actividadPrueba.setEstado(EnumActividades.Inactiva.toString());
        
        int resultadoInsercion = implementacion.registrarActividad(actividadPrueba);
        assertEquals(1,resultadoInsercion);
    } 
    
    @Test
    public void pruebaModificarActividadExitosa(){
        DAOActividadImplementacion implementacion = new DAOActividadImplementacion();
        Actividad actividadPrueba = new Actividad();
        actividadPrueba.setNombre("Prueba");
        actividadPrueba.setDescripcion("Poder entender el patron DAO");
        actividadPrueba.setFechaDeInicio("2024-04-04");
        actividadPrueba.setFechaDeCierre("2024-04-10");
        actividadPrueba.setIdColaboracion(1);
        actividadPrueba.setNumeroActividad(25);
        
        int resultadoInsercion = implementacion.modificarActividad(actividadPrueba, actividadPrueba.getNumeroActividad());
        assertEquals(1,resultadoInsercion);
    }
    
    @Test
    public void pruebaModificarFechaActividadExitosa(){
        DAOActividadImplementacion implementacion = new DAOActividadImplementacion();
        Actividad actividadPrueba = new Actividad();
        actividadPrueba.setNombre("Codigo de DAO");
        actividadPrueba.setDescripcion("Poder entender el patron DAO");
        actividadPrueba.setFechaDeInicio("2024-04-12");
        actividadPrueba.setFechaDeCierre("2024-04-20");
        actividadPrueba.setIdColaboracion(1);
        actividadPrueba.setNumeroActividad(25);
        
        int resultadoInsercion = implementacion.modificarFechaActividad(actividadPrueba, actividadPrueba.getNumeroActividad());
        assertEquals(1,resultadoInsercion);
    }
    
    @Test
    public void pruebaObtenerActividadesExitosa(){
        DAOActividadImplementacion implementacion = new DAOActividadImplementacion();
        List<Actividad> actividadesObtenidas = new ArrayList();
        List<Actividad> actividadesEsperadas = new ArrayList();
        Actividad actividadPrueba = new Actividad();
        actividadPrueba.setIdActividad(1);
        actividadPrueba.setNombre("Codigo de DAO");
        actividadPrueba.setDescripcion("Poder entender el patron DAO");
        actividadPrueba.setFechaDeInicio("2024-04-12");
        actividadPrueba.setFechaDeCierre("2024-04-20");
        actividadPrueba.setIdColaboracion(1);
        actividadPrueba.setNumeroActividad(25);
        actividadPrueba.setEstado(EnumActividades.Finalizada.toString());
        actividadesEsperadas.add(actividadPrueba);
        
        actividadesObtenidas = implementacion.obtenerActividades(1);
        assertEquals(actividadesObtenidas,actividadesEsperadas);
    }
    
    @Test
    public void pruebaObtenerNumeroDeActividadExitosa(){
        DAOActividadImplementacion implementacion = new DAOActividadImplementacion();
        Actividad actividadPrueba = new Actividad();
        actividadPrueba.setNombre("Codigo de DAO");
        actividadPrueba.setDescripcion("Poder entender el patron DAO");
        actividadPrueba.setFechaDeInicio("2024-04-12");
        actividadPrueba.setFechaDeCierre("2024-04-20");
        actividadPrueba.setIdColaboracion(1);
        
        int numeroObtenido = implementacion.obtenerNumeroDeActividad(actividadPrueba);
        assertEquals(25,numeroObtenido);
    }
    
    @Test
    public void pruebaValidarInexistenciaActividadExitosa(){
        DAOActividadImplementacion implementacion = new DAOActividadImplementacion();
        Actividad actividadPrueba = new Actividad();
        actividadPrueba.setNombre("Introduccion a la colaboracion");
        actividadPrueba.setDescripcion("Poder entender el patron DAO");
        actividadPrueba.setFechaDeInicio("2024-04-12");
        actividadPrueba.setFechaDeCierre("2024-04-20");
        actividadPrueba.setNumeroActividad(1);
        actividadPrueba.setIdColaboracion(1);
        
        boolean resultado = implementacion.validarInexistenciaDeActividad(actividadPrueba);
        assertFalse(resultado);
    }
    
    @Test
    public void pruebaActualizarEstadoActividad(){
        DAOActividadImplementacion implementacion = new DAOActividadImplementacion();
        Actividad actividadPrueba = new Actividad();
        actividadPrueba.setNombre("Introduccion a la colaboracion");
        actividadPrueba.setDescripcion("Poder entender el patron DAO");
        actividadPrueba.setFechaDeInicio("2024-04-12");
        actividadPrueba.setFechaDeCierre("2024-04-20");
        actividadPrueba.setNumeroActividad(25);
        actividadPrueba.setIdActividad(1);
        actividadPrueba.setIdColaboracion(1);
        
        int resultado = implementacion.actualizarEstadoActividad(actividadPrueba, EnumActividades.Finalizada.toString());
        assertEquals(1, resultado);
    }
    
}
