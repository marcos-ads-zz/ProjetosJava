package modulo.relatorio;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
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

        //criamos um documento vazio
        Document documentoPDF = new Document();
        String url = "C:\\PmenosF\\ArquivosPDF\\relatorio.pdf";

        try {

            Rectangle pagesize = new Rectangle(215f, 720f);
            Document document = new Document(pagesize, 17f, 10f, 15f, 25f);
            PdfWriter.getInstance(document, new FileOutputStream(url));
            document.open();
            document.add(new Paragraph(texto));
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
            //Image imagem = Image.getInstance("C:\\imagens\\minhas\\brpig.jpg");
            //setar o tamanho da imagem
            //            imagem.scaleToFit(400, 200);
            //adicionar a imagem ao pdf
            //documentoPDF.add(imagem);

        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(geraRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            documentoPDF.close();
        }
    }

}
