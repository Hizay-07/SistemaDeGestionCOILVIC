package logicaDeNegocio.manejadorDeArchivos;

import java.util.List;
import logicaDeNegocio.clases.Actividad;
import logicaDeNegocio.clases.Profesor;
import org.apache.log4j.Logger;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSmartCopy;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import logicaDeNegocio.clases.Colaboracion;
import logicaDeNegocio.clases.PropuestaColaboracion;
import logicaDeNegocio.clases.TipoColaboracion;

public class InformeImplementacion {
    
    private static final Logger LOG=Logger.getLogger(InformeImplementacion.class);
    
    public InformeImplementacion(){
    }
    
    public Document crearInformeDeColaboracion(Colaboracion colaboracion,List<Actividad> actividades, List<Profesor> profesores){
        Document informeColaboracion = new Document();
        informeColaboracion.setMargins(50, 50, 50, 50);
        String rutaInforme = "Informes\\informeDeColaboracionPrueba"+colaboracion.getIdColaboracion()+".pdf";
        try{
            PdfWriter.getInstance(informeColaboracion,new FileOutputStream(rutaInforme));
            PdfPTable tituloInforme = obtenerTituloDeInforme();
            Paragraph cuerpoDeInforme = obtenerCuerpoDelInforme(colaboracion,profesores);
            PdfPTable tablaActividades = obtenerActividadesDeInforme(actividades);
            informeColaboracion.open();
            Rectangle bordeDePagina = crearBordeDelInforme(informeColaboracion);
            informeColaboracion.add(bordeDePagina);
            informeColaboracion.add(tituloInforme);
            informeColaboracion.add(cuerpoDeInforme);
            informeColaboracion.add(tablaActividades);
            informeColaboracion.close();
        }catch(BadElementException | IOException excepcion){
            LOG.error(excepcion.getMessage());
            informeColaboracion = null;
        }catch(DocumentException  excepcion){
            LOG.error(excepcion.getMessage());
            informeColaboracion = null;
        }
        return informeColaboracion;
    }
    
    public Rectangle crearBordeDelInforme(Document informeColaboracion){
        float margenIzquierdo = informeColaboracion.leftMargin() - 10;
        float margenDerecho = informeColaboracion.getPageSize().getWidth() - informeColaboracion.rightMargin() + 10;
        float margenSuperior = informeColaboracion.getPageSize().getHeight() - informeColaboracion.topMargin() + 10;
        float margenInferior = informeColaboracion.bottomMargin() -10 ;
        Rectangle bordePagina = new Rectangle(margenIzquierdo, margenInferior, margenDerecho, margenSuperior);
        bordePagina.setBorder(Rectangle.BOX);
        bordePagina.setBorderColor(BaseColor.BLACK);
        bordePagina.setBorderWidth(2);
        return bordePagina;
    }
    
    public PdfPTable obtenerActividadesDeInforme(List<Actividad> actividades){
        PdfPTable tablaActividades = new PdfPTable(6);
        tablaActividades.addCell("No.Actividad");tablaActividades.addCell("Nombre");
        tablaActividades.addCell("Descripcion");tablaActividades.addCell("Inicio");
        tablaActividades.addCell("Cierre");tablaActividades.addCell("Estado");
        for(Actividad actividad : actividades){
            tablaActividades.addCell(Integer.toString(actividad.getNumeroActividad()));tablaActividades.addCell(actividad.getNombre());
            tablaActividades.addCell(actividad.getDescripcion());tablaActividades.addCell(actividad.getFechaDeInicio());
            tablaActividades.addCell(actividad.getFechaDeCierre());tablaActividades.addCell(actividad.getEstado());
        }
        return tablaActividades;
    }
    
    public PdfPTable obtenerTituloDeInforme(){
        PdfPTable tablaImagenTitulo = new PdfPTable(2);
        tablaImagenTitulo.setWidthPercentage(100);
        try{
            Paragraph tituloInforme = new Paragraph();
            tablaImagenTitulo.setWidths(new float[]{4, 1});
            Image logoCoil = Image.getInstance("src/interfazDeUsuario/Imagenes/Coil-Interfaz.png");
            logoCoil.scaleToFit(75, 75);
            tituloInforme.setFont(FontFactory.getFont("Arial", 18, Font.BOLD, BaseColor.BLACK));
            tituloInforme.add("Informe de colaboracion COIL-VIC \n \n");
            tituloInforme.setAlignment(Element.ALIGN_CENTER);
            PdfPCell celdaImagen = new PdfPCell(logoCoil, true);
            celdaImagen.setBorder(Rectangle.NO_BORDER);
            celdaImagen.setHorizontalAlignment(Element.ALIGN_RIGHT);
            PdfPCell celdaTitulo = new PdfPCell(tituloInforme);
            celdaTitulo.setBorder(Rectangle.NO_BORDER);
            celdaTitulo.setHorizontalAlignment(Element.ALIGN_RIGHT - 1);
            celdaTitulo.setVerticalAlignment(Element.ALIGN_MIDDLE); 
            tablaImagenTitulo.addCell(celdaTitulo);
            tablaImagenTitulo.addCell(celdaImagen);
        }catch(BadElementException | IOException excepcion){
            LOG.error(excepcion.getMessage());
        }catch(DocumentException excepcion){
            LOG.error(excepcion.getCause());
        }
        return tablaImagenTitulo;
    }
    
    public Paragraph obtenerCuerpoDelInforme(Colaboracion colaboracion,List<Profesor>profesores){
        Paragraph cuerpoDeInforme = new Paragraph();
        PropuestaColaboracion propuestaDeColaboracion = new PropuestaColaboracion();
        TipoColaboracion tipoColaboracion = new TipoColaboracion();
        propuestaDeColaboracion = colaboracion.getPropuestaColaboracion();
        tipoColaboracion = propuestaDeColaboracion.getTipoColaboracion();
        cuerpoDeInforme.setAlignment(Paragraph.ALIGN_JUSTIFIED_ALL);
        cuerpoDeInforme.setFont(FontFactory.getFont("Arial", 14, Font.NORMAL, BaseColor.BLACK));
        cuerpoDeInforme.add("\nEn el presente informe se muestra la información relevante sobre los acontecimientos "
                + "llevados a cabo del "+propuestaDeColaboracion.getFechaInicio()+" al "+propuestaDeColaboracion.getFechaCierre()+
                " en la colaboración COIL-VIC realizada por los profesores con experiencia COIL: \n \n"
        );
        for(Profesor profesor:profesores){
            cuerpoDeInforme.add(profesor.getNombre()+" "+profesor.getApellidoPaterno()+" "+profesor.getApellidoMaterno()+"\n");
        }
        cuerpoDeInforme.add("\nEsta experiencia de Colaboracion COIL VIC, contó con un número de "+colaboracion.getCantidadEstudiantes()+" "
                + "estudiantes. Pertenecientes al Programa Educativo Estudiantil: "+propuestaDeColaboracion.getProgramaEducativoEstudiantil()+" dentro"
                + " de la Experiencia Educativa: "+propuestaDeColaboracion.getExperienciaEducativa()+". \n\nAdemás, durante esta colaboración COIL-VIC"
                + " se utilizó el idioma "+propuestaDeColaboracion.getIdioma()+" para facilitar la comunicación entre estudiantes y docentes, con el"
                + " objetivo de "+propuestaDeColaboracion.getObjetivo()+". Esta colaboración fue de tipo "+tipoColaboracion.getTipo()+"\n \n"
        );
        return cuerpoDeInforme;
    }
    
    public int guardarArchivoDeInforme(File archivo,Document informeAGuardar,int idColaboracion){
        int resultadoGuardado = 0;
        Document informeColaboracion = new Document();
        try{
            PdfReader lectorPdf = new PdfReader("Informes/informeDeColaboracion"+idColaboracion+".pdf");
            int numPaginas = lectorPdf.getNumberOfPages();
            PdfCopy copiaPdf = new PdfCopy(informeColaboracion, new FileOutputStream(archivo));
            informeColaboracion.open();
            for (int i = 1; i <= numPaginas; i++) {
                copiaPdf.addPage(copiaPdf.getImportedPage(lectorPdf, i));
            }
            informeColaboracion.close();
            lectorPdf.close();
            copiaPdf.close();
            resultadoGuardado = 1;
        }catch(IOException | DocumentException excepcion){
            LOG.error(excepcion.getCause());
            resultadoGuardado = -1;
        }
        return resultadoGuardado;
    }
}
