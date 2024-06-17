package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOEvidenciaImplementacion;
import logicaDeNegocio.clases.Evidencia;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

public class PruebaDAOEvidenciaImplementacionSinConexionExitosa {
    
    @BeforeClass
    public static void inicializar() {
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentaadmin@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        UsuarioSingleton.getInstancia(usuarioPrueba);
    }
    
    @Test
    public void pruebaRegistrarEvidenciaSinConexionExitosa(){
        DAOEvidenciaImplementacion pruebaDeMetodo = new DAOEvidenciaImplementacion();
        Evidencia evidenciaPrueba = new Evidencia();
        evidenciaPrueba.setNombre("Evidencia de prueba");
        evidenciaPrueba.setRutaEvidencia("Informes//Evidenciapruebauno");
        int resultado = pruebaDeMetodo.agregarEvidencia(evidenciaPrueba);
        assertEquals(-1,resultado);
    }
    
    @Test
    public void pruebaModificarEvidenciaSinConexionExitosa(){
        DAOEvidenciaImplementacion pruebaDeMetodo = new DAOEvidenciaImplementacion();
        Evidencia evidenciaPrueba = new Evidencia();
        evidenciaPrueba.setNombre("Evidencia de prueba");
        evidenciaPrueba.setRutaEvidencia("Informes//Escritor.java");
        evidenciaPrueba.setIdActividad(1);
        int resultado = pruebaDeMetodo.modificarEvidencia(evidenciaPrueba);
        assertEquals(-1,resultado);
    }
    
    @Test
    public void pruebaObtenerEvidenciasDeActividadSinConexionExitosa(){
        int idActividad = 1; 
        DAOEvidenciaImplementacion dao = new DAOEvidenciaImplementacion();
        List<Evidencia> evidencias = dao.obtenerEvidenciasDeActividad(idActividad);
        Evidencia evidenciaEsperada = new Evidencia();
        evidenciaEsperada.setNombre("Excepcion");
        assertEquals(evidenciaEsperada.getNombre(), evidencias.get(0).getNombre());
    }
    
    @Test
    public void pruebaObtenerNumeroDeEvidenciasSinConexionExitosa(){
        int idActividad = 1; 
        DAOEvidenciaImplementacion dao = new DAOEvidenciaImplementacion();
        int numeroActividad = dao.obtenerNumeroDeEvidencia(idActividad);
        assertEquals(-1,numeroActividad);
    }
}
