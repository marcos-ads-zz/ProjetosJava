package modulo.campanhas.relatorio;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Marcos Júnior <marcosneri@outlook.com.br>
 */
public class geraRelatorio {

    public void relatorioPDF(String texto) {
        String url = "C:\\PmenosF\\ArquivosPDF\\relatorioCampanhas.pdf";
        try {
            Rectangle pagesize = new Rectangle(215f, 720f);
            Document document = new Document(pagesize, 0f, 0f, 15f, 25f);
            Font font = new Font(
                    FontFamily.HELVETICA, 7, Font.BOLD,
                    BaseColor.BLACK);
            PdfWriter.getInstance(document, new FileOutputStream(url)).setInitialLeading(5);
            document.open();
            Image imagem = Image.getInstance("img/desk/logopmenos.png");
            //setar o tamanho da imagem
            imagem.scaleToFit(100, 50);
            //adicionar a imagem ao pdf
            document.add(imagem);
            Chunk chunk = new Chunk(texto, font);
            document.add(chunk);
            //document.add(new Paragraph(texto, font));
            document.close();

            //cria uma instancia do documento e da o nome do pdf
            //  documentoPDF.setPageSize(PageSize.A7);
            //   documentoPDF.setMargins(5, 5, 0, 0);
            //   PdfWriter.getInstance(documentoPDF, new FileOutputStream(url));
            //abrir o documento
            //   documentoPDF.open();
            //setar o tamanho da página
            //documentoPDF.setPageSize(PageSize.B8);
            //adicinando primeiro paragrafo
            //   documentoPDF.add(new Paragraph(texto));
            //nova pagina
            //documentoPDF.newPage();
            //paragrafo da segunda pagina
            //documentoPDF.add(new Paragraph("Parágrafo de teste da segunda página"));
            //imagem do relatorio
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(geraRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(geraRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    private static void adicionaDocumento(Document document, String pais, String id) throws DocumentException {
//        document.add(new Chunk(pais));
//        document.add(new Chunk(" "));
//        Font font = new Font(
//                FontFamily.HELVETICA, 6, Font.BOLD,
//                BaseColor.WHITE);
//        Chunk id_chunk = new Chunk(id, font);
//        id_chunk.setBackground(BaseColor.BLACK, 1f, 0.5f, 1f, 1.5f);
//        id_chunk.setTextRise(6);
//        document.add(id_chunk);
//        document.add(Chunk.NEWLINE);
//    }
}
