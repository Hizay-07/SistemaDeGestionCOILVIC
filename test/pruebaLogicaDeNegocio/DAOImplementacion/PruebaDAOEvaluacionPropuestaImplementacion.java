package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOEvaluacionPropuestaImplementacion;
import logicaDeNegocio.clases.EvaluacionPropuesta;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

public class PruebaDAOEvaluacionPropuestaImplementacion {
    
    @Before
    public void setUp() {
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentapruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        UsuarioSingleton.getInstancia(usuarioPrueba);
    }
    
    @Test
    public void pruebaRegistrarEvaluacionPropuestaExitosa(){
        EvaluacionPropuesta evaluacionPropuesta=new EvaluacionPropuesta();
        evaluacionPropuesta.setIdUsuario(1);
        evaluacionPropuesta.setIdPropuestaColaboracion(1);
        evaluacionPropuesta.setEvaluacion("Aprobada");
        evaluacionPropuesta.setFechaEvaluacion("2024-05-02");
        evaluacionPropuesta.setJustificacion("Me parece una propuesta interesante");
        DAOEvaluacionPropuestaImplementacion daoEvaluacionPropuesta=new DAOEvaluacionPropuestaImplementacion();
        int resultadoEsperado=1;
        int resultadoObtenido=daoEvaluacionPropuesta.registrarEvaluacionPropuesta(evaluacionPropuesta);
        assertEquals(resultadoEsperado,resultadoObtenido);        
    }
    
    @Test
    public void pruebaRegistrarEvaluacionPropuestaFallida() {
        EvaluacionPropuesta evaluacionPropuesta = new EvaluacionPropuesta();
        DAOEvaluacionPropuestaImplementacion dao = new DAOEvaluacionPropuestaImplementacion();
        int resultado = dao.registrarEvaluacionPropuesta(evaluacionPropuesta);
        assertEquals(0, resultado);
    }
    
    @Test
    public void pruebaConsultarEvaluacionesDePropuestaExitosa() {
        DAOEvaluacionPropuestaImplementacion dao = new DAOEvaluacionPropuestaImplementacion();
        List<EvaluacionPropuesta> evaluaciones = dao.consultarEvaluacionesDePropuesta();
        assertNotNull(evaluaciones);
    }
    
    @Test
    public void pruebaConsultarEvaluacionesDePropuestaFallida() {
        DAOEvaluacionPropuestaImplementacion dao = new DAOEvaluacionPropuestaImplementacion();
        List<EvaluacionPropuesta> evaluaciones = dao.consultarEvaluacionesDePropuesta();
        assertEquals(0, evaluaciones.size());
    }
    
}
