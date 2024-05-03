package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOProfesorExternoImplementacion;
import logicaDeNegocio.clases.ProfesorExterno;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PruebaDAOProfesorExternoImplementacion {
    
    @Test
    public void pruebaRegistrarProfesorExternoExitosa() {
        ProfesorExterno profesorExterno = new ProfesorExterno();
        profesorExterno.setIdProfesor(7); 
        profesorExterno.setIdRepresentanteInstitucional(3); 
        DAOProfesorExternoImplementacion instancia = new DAOProfesorExternoImplementacion();
        int resultadoEsperado = 1;
        int resultadoObtenido = instancia.registrarProfesorExterno(profesorExterno);
        assertEquals(resultadoEsperado, resultadoObtenido);
    }
    
    @Test
    public void pruebaRegistrarProfesorExternoFracaso() {
        ProfesorExterno profesorExterno = new ProfesorExterno();
        DAOProfesorExternoImplementacion instancia = new DAOProfesorExternoImplementacion();
        int resultadoEsperado = 0;
        int resultadoObtenido = instancia.registrarProfesorExterno(profesorExterno);
        assertEquals(resultadoEsperado, resultadoObtenido);
    }

    @Test
    public void pruebaConsultarProfesoresExternosExitosa() {
        DAOProfesorExternoImplementacion instancia = new DAOProfesorExternoImplementacion();
        List<ProfesorExterno> externo = instancia.consultarProfesoresExternos();
        assertEquals(false, externo.isEmpty());
    }
    
    @Test
    public void pruebaConsultarProfesoresExternosFracaso() {
        DAOProfesorExternoImplementacion instancia = new DAOProfesorExternoImplementacion();
        List<ProfesorExterno> externo = instancia.consultarProfesoresExternos();
        assertEquals(true, externo.isEmpty()); 
    }
    
    @Test
    public void pruebaConsultarProfesoresExternosPorRepresentanteInstitucionalExitosa(){
        List<ProfesorExterno> resultadoEsperado = new ArrayList<>();
        ProfesorExterno profesorExterno = new ProfesorExterno();
        profesorExterno.setIdProfesorExterno(21); 
        resultadoEsperado.add(profesorExterno);
        DAOProfesorExternoImplementacion instancia = new DAOProfesorExternoImplementacion();
        List<ProfesorExterno> resultadoObtenido = new ArrayList<>();
        int idRepresentanteInstitucional = 3;
        resultadoObtenido=instancia.consultarProfesoresExternosPorRepresentanteInstitucional(idRepresentanteInstitucional);
        assertEquals(resultadoEsperado, resultadoObtenido);                                           
    }
    
    @Test
    public void pruebaConsultarProfesoresExternosPorRepresentanteInstitucionalFracaso(){
        List<ProfesorExterno> resultadoEsperado = new ArrayList<>();
        DAOProfesorExternoImplementacion instancia = new DAOProfesorExternoImplementacion();
        List<ProfesorExterno> resultadoObtenido = new ArrayList<>();
        int idRepresentanteInstitucional = 3; 
        resultadoObtenido = instancia.consultarProfesoresExternosPorRepresentanteInstitucional(idRepresentanteInstitucional);
        assertEquals(resultadoEsperado, resultadoObtenido); 
    }
    
    @Test
    public void pruebaConsultarIdRepresentanteInstitucionalPorIdProfesorExitosa(){
        int idProfesor=20;        
        int resultadoEsperado=1;
        DAOProfesorExternoImplementacion daoProfesorExterno=new DAOProfesorExternoImplementacion();
        int resultadoObtenido=daoProfesorExterno.consultarIdRepresentanteInstitucionalPorIdProfesor(idProfesor);
        assertEquals(resultadoEsperado, resultadoObtenido); 
    }
}
