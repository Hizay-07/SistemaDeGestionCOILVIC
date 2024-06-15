package pruebaLogicaDeNegocio.DAOImplementacion;

import logicaDeNegocio.DAOImplementacion.DAOEvaluacionPropuestaImplementacion;
import logicaDeNegocio.clases.EvaluacionPropuesta;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

public class PruebaDAOEvaluacionPropuestaImplementacion {
    
    @BeforeClass
    public static void inicializar() {
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
        evaluacionPropuesta.setFechaEvaluacion("2024-06-02");
        evaluacionPropuesta.setJustificacion("Me parece una propuesta interesante");
        DAOEvaluacionPropuestaImplementacion daoEvaluacionPropuesta=new DAOEvaluacionPropuestaImplementacion();
        int resultadoEsperado=1;
        int resultadoObtenido=daoEvaluacionPropuesta.registrarEvaluacionPropuesta(evaluacionPropuesta);
        assertEquals(resultadoEsperado,resultadoObtenido);        
    }
    
    @Test
    public void pruebaRegistrarEvaluacionPropuestaFallida() {
        EvaluacionPropuesta evaluacionPropuesta=new EvaluacionPropuesta();
        evaluacionPropuesta.setIdUsuario(1);
        evaluacionPropuesta.setIdPropuestaColaboracion(0);
        evaluacionPropuesta.setEvaluacion("Aprobada");
        evaluacionPropuesta.setFechaEvaluacion("2024-06-02");
        evaluacionPropuesta.setJustificacion("Me parece una propuesta interesante");
        DAOEvaluacionPropuestaImplementacion daoEvaluacionPropuesta=new DAOEvaluacionPropuestaImplementacion();
        int resultadoEsperado=-1;
        int resultadoObtenido=daoEvaluacionPropuesta.registrarEvaluacionPropuesta(evaluacionPropuesta);
        assertEquals(resultadoEsperado,resultadoObtenido);
    }                    
}
