package Procesos;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Genera un archivo PDF con los datos de una Historia Clínica.
 * Cada PDF se guarda en la carpeta "HistorialesPDF" del proyecto.
 */
public class GeneradorPDF {

    public static String generar(String nombreArchivo, String titulo, List<String[]> campos)
            throws IOException, DocumentException {

        File carpeta = new File("HistorialesPDF");
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }
        File archivo = new File(carpeta, nombreArchivo);

        Document doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream(archivo));
        doc.open();

        Font fTitulo   = new Font(FontFamily.HELVETICA, 18, Font.BOLD);
        Font fEtiqueta = new Font(FontFamily.HELVETICA, 11, Font.BOLD);
        Font fValor    = new Font(FontFamily.HELVETICA, 11, Font.NORMAL);

        doc.add(new Paragraph(titulo, fTitulo));
        doc.add(new Paragraph(" "));

        for (String[] campo : campos) {
            Paragraph p = new Paragraph();
            p.add(new Chunk(campo[0] + ": ", fEtiqueta));
            p.add(new Chunk((campo[1] == null || campo[1].isEmpty()) ? "-" : campo[1], fValor));
            p.setSpacingAfter(6);
            doc.add(p);
        }

        doc.close();
        return archivo.getAbsolutePath();
    }
}
