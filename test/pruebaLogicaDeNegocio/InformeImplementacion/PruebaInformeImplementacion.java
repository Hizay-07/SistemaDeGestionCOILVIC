package pruebaLogicaDeNegocio.InformeImplementacion;

import com.itextpdf.text.Document;
import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.clases.Profesor;
import logicaDeNegocio.clases.Actividad;
import logicaDeNegocio.clases.Colaboracion;
import logicaDeNegocio.manejadorDeArchivos.InformeImplementacion;
import logicaDeNegocio.clases.PropuestaColaboracion;
import logicaDeNegocio.clases.TipoColaboracion;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class PruebaInformeImplementacion {
    
    @Test
    public void crearInformeDeColaboracion(){
        InformeImplementacion informe = new InformeImplementacion();
        Colaboracion colaboracion = new Colaboracion();
        PropuestaColaboracion propuesta = new PropuestaColaboracion();
        TipoColaboracion tipoColaboracion = new TipoColaboracion();
        List<Actividad> actividades = new ArrayList();
        List<Profesor> profesores = new ArrayList();
        Actividad actividad1 = new Actividad();
        Actividad actividad2 = new Actividad();
        Actividad actividad3 = new Actividad();
        Profesor profesor1 = new Profesor();
        Profesor profesor2 = new Profesor();
        tipoColaboracion.setIdTipoColaboracion(1);
        tipoColaboracion.setTipo("Clase espejo");
        propuesta.setExperienciaEducativa("Programación segura");
        propuesta.setFechaCierre("2024-11-25");
        propuesta.setFechaInicio("2024-09-12");
        propuesta.setIdioma("Inglés");
        propuesta.setObjetivo("Poder intercambiar ideas entre los estudiantes acerca de la programación multinúcleo");
        propuesta.setProgramaEducativoEstudiantil("Ingenieria de software");
        propuesta.setTipoColaboracion(tipoColaboracion);
        colaboracion.setCantidadEstudiantes(62);
        colaboracion.setPropuestaColaboracion(propuesta);
        profesor1.setNombre("Juan pablo");
        profesor1.setApellidoPaterno("Torres");
        profesor1.setApellidoMaterno("Ortiz");
        profesor2.setNombre("Christopher");
        profesor2.setApellidoPaterno("Vasquez");
        profesor2.setApellidoMaterno("Zapata");
        actividad1.setNumeroActividad(1);
        actividad1.setDescripcion("Poder realizar un informe");
        actividad1.setEstado("Inactiva");
        actividad1.setFechaDeCierre("2024-05-09");
        actividad1.setFechaDeInicio("2024-05-07");
        actividad1.setNombre("Informe en java");
        actividad2.setNumeroActividad(2);
        actividad2.setDescripcion("Poder realizar un resumen");
        actividad2.setEstado("Activa");
        actividad2.setFechaDeCierre("2024-05-09");
        actividad2.setFechaDeInicio("2024-05-07");
        actividad2.setNombre("Informe en java");
        actividad3.setNumeroActividad(3);
        actividad3.setDescripcion("Poder realizar un cuento");
        actividad3.setEstado("Activa");
        actividad3.setFechaDeCierre("2024-05-09");
        actividad3.setFechaDeInicio("2024-05-07");
        actividad3.setNombre("Informe en java");
        actividades.add(actividad1);
        actividades.add(actividad2);
        actividades.add(actividad3);
        profesores.add(profesor1);
        profesores.add(profesor2);
        
        Document documento = informe.crearInformeDeColaboracion(colaboracion, actividades, profesores);
        assertNotNull(documento);
    }
}
