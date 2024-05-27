package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOEmisionPropuestaImplementacion;
import logicaDeNegocio.clases.EmisionPropuesta;
import logicaDeNegocio.clases.Profesor;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class PruebaDAOEmisionPropuestaImplementacion {
    
    @Before
    public void setUp() {
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentapruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        UsuarioSingleton.getInstancia(usuarioPrueba);
    }
    
    @Test
    public void testRegistrarEmisionPropuestaExitoso() {
        DAOEmisionPropuestaImplementacion dao = new DAOEmisionPropuestaImplementacion();
        EmisionPropuesta emisionPropuesta = new EmisionPropuesta();
        emisionPropuesta.setIdProfesor(1);
        emisionPropuesta.setIdPropuestaColaboracion(1);
        emisionPropuesta.setFechaEmision("2024-05-26");
        int resultado = dao.registrarEmisionPropuesta(emisionPropuesta);
        assertEquals(1, resultado);
        List<EmisionPropuesta> emisiones = dao.consultarEmisionesDePropuestas();
        assertTrue(emisiones.contains(emisionPropuesta));
    }
    
    @Test
    public void pruebaConsultarEmisionesDePropuestasExitosa(){
        EmisionPropuesta emisionPropuesta=new EmisionPropuesta();
        emisionPropuesta.setIdProfesor(1);
        emisionPropuesta.setIdPropuestaColaboracion(1);
        EmisionPropuesta emisionPropuesta2=new EmisionPropuesta();
        emisionPropuesta2.setIdProfesor(2);
        emisionPropuesta2.setIdPropuestaColaboracion(3);
        List<EmisionPropuesta> resultadoEsperado=new ArrayList<>();
        resultadoEsperado.add(emisionPropuesta);
        resultadoEsperado.add(emisionPropuesta2);   
        List<EmisionPropuesta> resultadoObtenido=new ArrayList<>();
        DAOEmisionPropuestaImplementacion daoEmisionPropuesta=new DAOEmisionPropuestaImplementacion();
        resultadoObtenido=daoEmisionPropuesta.consultarEmisionesDePropuestas();
        assertEquals(resultadoEsperado,resultadoObtenido);        
    }
    
    @Test
    public void pruebaConsultarIdProfesorPorIdPropuestaColaboracionExitosa(){
        int idPropuestaColaboracion=1;
        int resultadoEsperado=1;
        DAOEmisionPropuestaImplementacion daoEmisionPropuesta=new DAOEmisionPropuestaImplementacion();
        int resultadoObtenido=daoEmisionPropuesta.consultarIdProfesorPorIdPropuestaColaboracion(idPropuestaColaboracion);
        assertEquals(resultadoEsperado,resultadoObtenido);        
    }
  
    /*
    @Test
    public void pruebaConsultarIdPropuestaColaboracionProIdProfesor(){
        Profesor profesor = new Profesor();
        profesor.setIdProfesor(1);
        int resultadoEsperado=1;
        DAOEmisionPropuestaImplementacion daoEmisionPropuesta=new DAOEmisionPropuestaImplementacion();
        int resultadoObtenido=daoEmisionPropuesta.consultarIdPropuestaDeColaboracionPorIdProfesor(profesor);
        assertEquals(resultadoEsperado,resultadoObtenido);  
        
    }*/

}

