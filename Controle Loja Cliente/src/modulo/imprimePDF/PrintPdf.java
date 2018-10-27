package modulo.imprimePDF;

/**
 *
 * @author Jaime Alcides
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.nio.ByteBuffer;

import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
import com.sun.pdfview.PDFRenderer;

import javax.print.PrintService;

import javax.swing.JOptionPane;

public class PrintPdf {

    private PrinterJob pjob = null;
    private static String imp = "";
    private static String tipoDePapel;
    private PrintService impressora = null;
    private int cont = 0;

    public static void PrintPDF(String impressoraDefinida, String nomeArquivo, String localArquivo, String Papel) throws IOException, PrinterException {
        imp = impressoraDefinida;
        tipoDePapel = Papel;
        FileInputStream fis = new FileInputStream(localArquivo);
        PrintPdf printPDFFile = new PrintPdf(fis, nomeArquivo);
        printPDFFile.print();
    }

    public PrintPdf(InputStream inputStream, String jobName) throws IOException, PrinterException {
        byte[] pdfContent = new byte[inputStream.available()];
        inputStream.read(pdfContent, 0, inputStream.available());
        initialize(pdfContent, jobName);
    }

    public PrintPdf(byte[] content, String jobName) throws IOException, PrinterException {
        initialize(content, jobName);
    }

    private void initialize(byte[] pdfContent, String jobName) throws IOException, PrinterException {
        ByteBuffer bb = ByteBuffer.wrap(pdfContent);
        String nome = "";
        PDFFile pdfFile = new PDFFile(bb);
        PDFPrintPage pages = new PDFPrintPage(pdfFile);
        PrintService[] pservices = PrinterJob.lookupPrintServices();
        if (pservices.length > 0) {
            for (PrintService ps : pservices) {
                cont++;
                nome += ps.getName() + "\n";

                if (ps.getName().contains(imp)) {
                    impressora = ps;
                    break;
                }
            }
        }
        if (impressora != null) {
            pjob = PrinterJob.getPrinterJob();
            pjob.setPrintService(impressora);
            PageFormat pf = PrinterJob.getPrinterJob().defaultPage();
            pjob.setJobName(jobName);
            Book book = new Book();
            book.append(pages, pf, pdfFile.getNumPages());
            pjob.setPageable(book);
            Paper paper = new Paper();
            //Laterais das Páginas
            double marginh = 0;
            double marginw = 0;
            //Tamanho dos Cantos Superiores Esquerdo
            double xWidth = 225;
            JTelaPrintPDF jt = new JTelaPrintPDF();
            if (null == tipoDePapel) {
                JOptionPane.showMessageDialog(null, "Erro ao selecionar tipo de papel!");
            } else switch (tipoDePapel) {
                case "Termico":
                    paper.setImageableArea(marginw, marginh, xWidth, paper.getHeight());
                    break;
                case "A4":
                    paper.setImageableArea(marginw, marginh, paper.getWidth(), paper.getHeight());
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Erro ao selecionar tipo de papel!");
                    break;
            }
            pf.setPaper(paper);
        }
    }

    public void print() throws PrinterException {
        if (impressora != null) {
            pjob.print();
        }
    }
}

class PDFPrintPage implements Printable {

    private PDFFile file;

    PDFPrintPage(PDFFile file) {
        this.file = file;
    }

    @Override
    public int print(Graphics g, PageFormat format, int index) throws PrinterException {
        int pagenum = index + 1;
        if ((pagenum >= 1) && (pagenum <= file.getNumPages())) {
            Graphics2D g2 = (Graphics2D) g;
            PDFPage page = file.getPage(pagenum);

            Rectangle imageArea = new Rectangle((int) format.getImageableX(), (int) format.getImageableY(),
                    (int) format.getImageableWidth(), (int) format.getImageableHeight());
            g2.translate(0, 0);
            PDFRenderer pgs = new PDFRenderer(page, g2, imageArea, null, Color.BLUE);
            try {
                page.waitForFinish();
                pgs.run();
            } catch (InterruptedException ie) {
                JOptionPane.showMessageDialog(null, "Erro: " + ie.toString());
                //System.exit(0);
            }
            return PAGE_EXISTS;
        } else {
            return NO_SUCH_PAGE;
        }
    }
}
