package net.uch.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.pdmodel.PDPage;
import net.uch.commonService.ServiceFinder;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class Print {


    public Print() {
    }

    /**
     * Genera el buffer del reporte en PDF
     * Antes utilizaba 'JRBeanCollectionDataSource jrbcds' como paremetro
     * @param jasper
     * @param parameters
     * @return
     */
    public ByteArrayOutputStream cargar_reporte(String jasper, HashMap parameters) {
        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            FacesContext context = FacesContext.getCurrentInstance();
            InputStream reportStream = context.getExternalContext().getResourceAsStream(jasper);

            DriverManagerDataSource dmds = (DriverManagerDataSource) ServiceFinder.findBean("dataSource");
            Connection connection = dmds.getConnection();

            JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, parameters, connection);
            JasperExportManager.exportReportToPdfStream(jasperPrint, buffer);
            File pdf = File.createTempFile("output.", ".pdf");
            JasperExportManager.exportReportToPdfStream(jasperPrint,new FileOutputStream(pdf)); 
            connection.close();

            return buffer;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
   

    public void imprimir(InputStream in) {
        try {
            PrintRequestAttributeSet attr = new HashPrintRequestAttributeSet();
            //attr.add(MediaSizeName.ISO_A4);
            attr.add(new Copies(1));
            PDDocument document = PDDocument.load(in);
            List pages = document.getDocumentCatalog().getAllPages();
            PDPage page = (PDPage) pages.get(0);
            BufferedImage image = page.convertToImage();
            DocFlavor flavor = DocFlavor.INPUT_STREAM.PNG;
            ByteArrayOutputStream buffer_img = new ByteArrayOutputStream();
            ImageIO.write(image, "png", new File("C:\\Prueba.png"));
            //ImageIO.write(image, "png", new FileOutputStream("//192.168.1.9/Imp_Publicaciones"));
            ImageIO.write(image, "png", buffer_img);
            byte[] bytes = buffer_img.toByteArray();
            InputStream is = new ByteArrayInputStream(bytes);
            Doc doc = new SimpleDoc(is, flavor, null);
            PrintService Printer = null;
            //PrintService pDefault = PrintServiceLookup.lookupDefaultPrintService();
            PrintService pDefault = null;
            PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
            if (services.length > 0) {
                for (int i = 0; i < services.length; i++) {
                    System.out.println("impresora:" + services[i].getName());
                    if (services[i].getName().equalsIgnoreCase("\\\\" + "192.168.1.9" + "\\" + "HP LaserJet 1022")) {
                        System.out.println("entro al if");
                        pDefault = services[i];
                        break;
                    }
                }
            }
            Printer = pDefault;
            DocPrintJob docPrintJob = Printer.createPrintJob();
            System.out.println("impresion ok");
            docPrintJob.print(doc, attr);///imprime
        } catch (Exception e) {
            System.err.println("No se pudo completar la impresion");
            e.printStackTrace();
        }
    }
}