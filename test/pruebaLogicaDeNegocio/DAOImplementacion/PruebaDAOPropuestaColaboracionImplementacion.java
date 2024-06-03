package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOPropuestaColaboracionImplementacion;
import logicaDeNegocio.clases.Profesor;
import logicaDeNegocio.clases.PropuestaColaboracion;
import logicaDeNegocio.clases.TipoColaboracion;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;

public class PruebaDAOPropuestaColaboracionImplementacion {
    
    private DAOPropuestaColaboracionImplementacion dao;
    
    @Before
    public void setUp() {
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentapruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        UsuarioSingleton.getInstancia(usuarioPrueba);
    }
    
    @Test
    public void pruebaRegistrarPropuestaColaboracionExitosa(){
        DAOPropuestaColaboracionImplementacion instancia=new DAOPropuestaColaboracionImplementacion();
        PropuestaColaboracion propuestaColaboracion=new PropuestaColaboracion();
        propuestaColaboracion.setFechaInicio("2024-05-02");
        propuestaColaboracion.setFechaCierre("2024-06-23");
        propuestaColaboracion.setIdioma("Ingles");
        propuestaColaboracion.setExperienciaEducativa("POO");
        propuestaColaboracion.setObjetivo("Compartir los distintos para programar en c+");
        propuestaColaboracion.setProgramaEducativoEstudiantil("Ingenieria de software");
        propuestaColaboracion.setEstadoPropuesta("Registrada");
        TipoColaboracion tipoColaboracion=new TipoColaboracion();
        tipoColaboracion.setIdTipoColaboracion(1);
        propuestaColaboracion.setTipoColaboracion(tipoColaboracion);
        int resultadoEsperado=1;       
        int resultadoObtenido=instancia.registrarPropuestaColaboracion(propuestaColaboracion);
        assertEquals(resultadoEsperado,resultadoObtenido);                                
    }
    
    @Test (expected = AssertionError.class)
    public void pruebaRegistrarPropuestaColaboracionFracaso(){
        DAOPropuestaColaboracionImplementacion instancia=new DAOPropuestaColaboracionImplementacion();
        PropuestaColaboracion propuestaColaboracion=new PropuestaColaboracion();       
        int resultadoEsperado=0;       
        int resultadoObtenido=instancia.registrarPropuestaColaboracion(propuestaColaboracion);
        assertEquals(resultadoEsperado,resultadoObtenido);        
    }
    
    @Test
    public void pruebaConsultarPropuestasColaboracionExitoso() {
        List<PropuestaColaboracion> propuestas = dao.consultarPropuestasColaboracion();
        assertNotNull(propuestas);
        assertEquals(2, propuestas.size());
    }
    
    @Test
    public void pruebaConsultarPropuestasColaboracionFracaso(){
        PropuestaColaboracion propuestaColaboracion = new PropuestaColaboracion();
        List<PropuestaColaboracion> listaEsperada = new ArrayList<>();
        listaEsperada.add(propuestaColaboracion);
        List<PropuestaColaboracion> listaObtenida = dao.consultarPropuestasColaboracion();
        assertNotEquals(listaEsperada, listaObtenida);
    }
        
    @Test
    public void pruebaConsultarPropuestasColaboracionPorFechaDeInicioExitosa(){
        PropuestaColaboracion propuestaColaboracion = new PropuestaColaboracion();
        propuestaColaboracion.setIdPropuestaColaboracion(1);
        TipoColaboracion tipoColaboracion = new TipoColaboracion();
        tipoColaboracion.setIdTipoColaboracion(1);
        propuestaColaboracion.setTipoColaboracion(tipoColaboracion);
        propuestaColaboracion.setObjetivo("Compartir los distintos para programar en java");
        List<PropuestaColaboracion> listaEsperada = new ArrayList<>();
        listaEsperada.add(propuestaColaboracion);
        List<PropuestaColaboracion> listaObtenida = dao.consultarPropuestasColaboracionPorFechaDeInicio("2024-05-02");
        assertEquals(listaEsperada, listaObtenida);
    }
    
    @Test
    public void pruebaConsultarPropuestasColaboracionPorFechaDeInicioFracaso(){
        PropuestaColaboracion propuestaColaboracion=new PropuestaColaboracion();                           
        List<PropuestaColaboracion> listaEsperada=new ArrayList<>();
        listaEsperada.add(propuestaColaboracion);
        List<PropuestaColaboracion> listaObtenida=new ArrayList<>();
        DAOPropuestaColaboracionImplementacion instancia=new DAOPropuestaColaboracionImplementacion();
        listaObtenida=instancia.consultarPropuestasColaboracionPorFechaDeInicio("2024-08-10");
        assertNotEquals(listaEsperada,listaObtenida);        
    }        
    
    @Test
    public void pruebaEditarFechaDeInicioDePropuestaColaboracionPorIdExitosa(){
        int resultadoEsperado = 1;
        int resultadoObtenido = dao.editarFechaDeInicioDePropuestaColaboracionPorId("2024-10-16", 1);
        assertEquals(resultadoEsperado, resultadoObtenido);        
    }
    
    @Test
    public void pruebaEditarFechaDeInicioDePropuestaColaboracionPorIdFracaso(){
        int resultadoEsperado = 0;
        int resultadoObtenido = dao.editarFechaDeInicioDePropuestaColaboracionPorId("2024-10-16", 0);
        assertEquals(resultadoEsperado, resultadoObtenido);          
    }
        
    @Test
    public void pruebaEditarFechaDeCierreDePropuestaColaboracionPorIdExitosa(){
        int resultadoEsperado = 1;
        int resultadoObtenido = dao.editarFechaDeCierreDePropuestaColaboracionPorId("2024-12-12", 1);
        assertEquals(resultadoEsperado, resultadoObtenido);        
    }
    
    @Test
    public void pruebaEditarFechaDeCierreDePropuestaColaboracionPorIdFracaso(){
        int resultadoEsperado = 0;
        int resultadoObtenido = dao.editarFechaDeCierreDePropuestaColaboracionPorId("2024-12-12", 0);
        assertEquals(resultadoEsperado, resultadoObtenido);        
    }
    
    @Test
    public void pruebaAprobarPropuestaColaboracionPorIdExitosa(){
        DAOPropuestaColaboracionImplementacion instancia=new DAOPropuestaColaboracionImplementacion();
        int resultadoEsperado=1;
        int resultadoObtenido=instancia.aprobarPropuestaColaboracionPorId(2);
        assertEquals(resultadoEsperado,resultadoObtenido);                        
    }
        
    @Test
    public void pruebaAprobarPropuestaColaboracionPorIdFracaso(){
        DAOPropuestaColaboracionImplementacion instancia=new DAOPropuestaColaboracionImplementacion();
        int resultadoEsperado=0;
        int resultadoObtenido=instancia.aprobarPropuestaColaboracionPorId(0);
        assertEquals(resultadoEsperado,resultadoObtenido);        
    }
    
    @Test
    public void pruebaRechazarPropuestaColaboracionPorIdExitosa(){
        DAOPropuestaColaboracionImplementacion instancia=new DAOPropuestaColaboracionImplementacion();
        int resultadoEsperado=1;
        int resultadoObtenido=instancia.rechazarPropuestaColaboracionPorId(1);
        assertEquals(resultadoEsperado,resultadoObtenido);                                
    }            
    
    @Test
    public void pruebaRechazarPropuestaColaboracionPorIdFracaso(){
        DAOPropuestaColaboracionImplementacion instancia=new DAOPropuestaColaboracionImplementacion();
        int resultadoEsperado=0;
        int resultadoObtenido=instancia.rechazarPropuestaColaboracionPorId(0);
        assertEquals(resultadoEsperado,resultadoObtenido);        
    }
    
    @Test
    public void pruebaConsultarPropuestasDeColaboracionAprobadasExitosa(){
        PropuestaColaboracion propuestaColaboracion = new PropuestaColaboracion();
        TipoColaboracion tipoColaboracion = new TipoColaboracion();
        tipoColaboracion.setIdTipoColaboracion(1);
        propuestaColaboracion.setTipoColaboracion(tipoColaboracion);
        propuestaColaboracion.setExperienciaEducativa("Programacion");
        propuestaColaboracion.setObjetivo("Compartir los distintos para programar en java");
        List<PropuestaColaboracion> resultadoEsperado = new ArrayList<>();
        resultadoEsperado.add(propuestaColaboracion);
        List<PropuestaColaboracion> resultadoObtenido = dao.consultarPropuestasDeColaboracionAprobadas();
        assertEquals(resultadoEsperado, resultadoObtenido);        
    }
    
    @Test
    public void pruebaConsultarPropuestasDeColaboracionAprobadasFallida(){
        List<PropuestaColaboracion> resultadoEsperado = new ArrayList<>();
        List<PropuestaColaboracion> resultadoObtenido = dao.consultarPropuestasDeColaboracionAprobadas();
        assertNotEquals(resultadoEsperado, resultadoObtenido);        
    }
    
    @Test
    public void pruebaConsultarPropuestasDeColaboracionRegistradas(){
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario("cuentaadmin@gmail.com");
        usuario.setContrasenia("Contrasenia123*");
        usuario.setCorreo("cuentaadmin@gmail.com");
        usuario.setTipoDeUsuario("Administrativo");
        UsuarioSingleton.getInstancia(usuario);
        PropuestaColaboracion propuestaColaboracion = new PropuestaColaboracion();
        TipoColaboracion tipoColaboracion = new TipoColaboracion();
        Profesor profesor = new Profesor();
        tipoColaboracion.setIdTipoColaboracion(2);
        tipoColaboracion.setTipo("Implementación COIL-VIC");
        propuestaColaboracion.setTipoColaboracion(tipoColaboracion);
        propuestaColaboracion.setIdioma("Español");
        propuestaColaboracion.setExperienciaEducativa("Programación segura");
        propuestaColaboracion.setObjetivo("Crear software de calidad");
        propuestaColaboracion.setFechaInicio("2024-05-14");
        propuestaColaboracion.setFechaCierre("2024-05-22");
        propuestaColaboracion.setEstadoPropuesta("Registrada");
        propuestaColaboracion.setIdPropuestaColaboracion(4);
        profesor.setIdProfesor(1);
        profesor.setNombre("Chris");
        profesor.setApellidoPaterno("Vasquez");
        profesor.setApellidoMaterno("Zapata");
        profesor.setCorreo("correo@gmail.com");
        profesor.setEstado("Esperando");
        propuestaColaboracion.setProfesor(profesor);
        List<PropuestaColaboracion> resultadoEsperado = new ArrayList<>();
        resultadoEsperado.add(propuestaColaboracion);
        List<PropuestaColaboracion> resultadoObtenido = dao.consultarPropuestasDeColaboracionRegistradas();
        assertEquals(resultadoEsperado, resultadoObtenido);    
    }
    
    @Test
    public void pruebaConsultarPropuestasDeColaboracionRegistradasFallida(){
        List<PropuestaColaboracion> resultadoEsperado = new ArrayList<>();
        List<PropuestaColaboracion> resultadoObtenido = dao.consultarPropuestasDeColaboracionRegistradas();
        assertNotEquals(resultadoEsperado, resultadoObtenido);    
    }
    
    @Test
    public void pruebaObtenerIdPropuestaColaboracionAprobadaPorIdProfesorExitosa(){
        int idProfesor=2;
        int resultadoEsperado=1;
        DAOPropuestaColaboracionImplementacion daoPropuestaColaboracion=new DAOPropuestaColaboracionImplementacion();
        int resultadoObtenido=daoPropuestaColaboracion.obtenerIdPropuestaColaboracionAprobadaPorIdProfesor(idProfesor);
        assertEquals(resultadoEsperado,resultadoObtenido);                        
    }
    
    @Test
    public void pruebaObtenerIdPropuestaColaboracionAprobadaPorIdProfesorFallida(){
        int resultadoEsperado = 0;
        int resultadoObtenido = dao.obtenerIdPropuestaColaboracionAprobadaPorIdProfesor(2);
        assertNotEquals(resultadoEsperado, resultadoObtenido);                        
    }
    
}
