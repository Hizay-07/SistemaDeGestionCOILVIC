package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOProfesorExternoImplementacion;
import logicaDeNegocio.clases.ProfesorExterno;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class PruebaDAOProfesorExternoImplementacionSinConexionExitosa {
    
    @Test
    public void pruebaRegistrarProfesorExternoSinConexionExitosa() {
        DAOProfesorExternoImplementacion dao = new DAOProfesorExternoImplementacion();
        ProfesorExterno profesorExterno = new ProfesorExterno();
        profesorExterno.setIdProfesor(1); 
        profesorExterno.setIdRepresentanteInstitucional(1); 
        int resultado = dao.registrarProfesorExterno(profesorExterno);
        assertEquals(-1, resultado);
    }
    
    @Test
    public void pruebaConsultarProfesoresExternosSinConexionExitosa(){
        DAOProfesorExternoImplementacion dao = new DAOProfesorExternoImplementacion();
        List<ProfesorExterno> profesoresExternos = dao.consultarProfesoresExternos();
        assertTrue(profesoresExternos.isEmpty());
    }
    
    @Test
    public void pruebaConsultarProfesoresExternosPorRepresentanteInstitucionalSinConexionExitosa(){
        DAOProfesorExternoImplementacion dao = new DAOProfesorExternoImplementacion();
        List<ProfesorExterno> profesoresExternos = dao.consultarProfesoresExternosPorRepresentanteInstitucional(1);
        assertTrue(profesoresExternos.isEmpty());
    }
    
    @Test
    public void pruebaConsultarIdRepresentanteInstitucionalPorIdProfesorSinConexionExitosa(){
        DAOProfesorExternoImplementacion dao = new DAOProfesorExternoImplementacion();
        int idRepresentanteInstitucional = dao.consultarIdRepresentanteInstitucionalPorIdProfesor(1);
        assertEquals(-1, idRepresentanteInstitucional);
    }
    
    @Test
    public void pruebaEditarInstitucionProfesorExternoPorIdProfesorSinConexionExitosa(){
        DAOProfesorExternoImplementacion dao = new DAOProfesorExternoImplementacion();
        int resultado = dao.editarInstitucionProfesorExternoPorIdProfesor(1, 1);
        assertEquals(-1, resultado);
    }
    
}
