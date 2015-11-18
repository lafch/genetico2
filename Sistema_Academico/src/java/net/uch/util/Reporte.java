package net.uch.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.ResourceBundle;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.uch.commonService.ServiceFinder;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author ${user}
 */
public class Reporte {

    public void cargarReporte(OutputStream out, Object data, HashMap parametros, String url_reporte) throws IOException, Exception, EOFException {
        if (data.toString().equalsIgnoreCase("ficha")) {
            //FacesContext context = FacesContext.getCurrentInstance();
            // String sec_id="";
            /*HashMap parametros = new HashMap();
             parametros.put("logo", context.getExternalContext().getResource("/Imagenes/actions/logo_p.jpg"));
             parametros.put("sec_id", sec_id);*/

            String jasper = "/WEB-INF/Reportes/cursos_libres/" + url_reporte;

            Print print = new Print();

            ByteArrayOutputStream buffer = print.cargar_reporte(jasper, parametros);
//            FileOutputStream fos = new FileOutputStream (new File("D:\\myFile.pdf")); 
//            buffer.writeTo(fos);
            byte[] bytes = buffer.toByteArray();
//            fos.write(bytes);
            InputStream input = new ByteArrayInputStream(bytes);

            int size = input.available();
            byte[] pdf = new byte[size];
            input.read(pdf);
            out.write(pdf);

            buffer.flush();
            buffer.close();
            input.close();
            out.flush();
            out.close();
        }
    }

    public void cargarReportePdf(OutputStream out, Object data, HashMap parametros, String url_reporte) throws IOException, Exception, EOFException {
        if (data.toString() != null) {
            //FacesContext context = FacesContext.getCurrentInstance();
            // String sec_id="";
            /*HashMap parametros = new HashMap();
             parametros.put("logo", context.getExternalContext().getResource("/Imagenes/actions/logo_p.jpg"));
             parametros.put("sec_id", sec_id);*/

            String jasper = "/WEB-INF/Reportes/cursos_libres/" + data.toString();

            Print print = new Print();

            ByteArrayOutputStream buffer = print.cargar_reporte(jasper, parametros);
//            FileOutputStream fos = new FileOutputStream (new File("D:\\myFile.pdf")); 
//            buffer.writeTo(fos);
            byte[] bytes = buffer.toByteArray();
//            fos.write(bytes);
            InputStream input = new ByteArrayInputStream(bytes);

            int size = input.available();
            byte[] pdf = new byte[size];
            input.read(pdf);
            out.write(pdf);

            buffer.flush();
            buffer.close();
            input.close();
            out.flush();
            out.close();
        }
    }

    public void cargarReporteHorarioGenerado(HashMap parametros, String url_reporte) throws IOException, Exception, EOFException {
            FacesContext context = FacesContext.getCurrentInstance();
            parametros.put("logo", context.getExternalContext().getResource("/Imagenes/actions/logo_p.jpg"));
            String jasper = "/WEB-INF/Reportes/cursos_libres/" + url_reporte;
            
            InputStream reportStream = context.getExternalContext().getResourceAsStream(jasper);

            DriverManagerDataSource dmds = (DriverManagerDataSource) ServiceFinder.findBean("dataSource");
            Connection connection = dmds.getConnection();
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, parametros, connection);
//            File pdf = new File("D:\\sample22.pdf");
//            JasperExportManager.exportReportToPdfStream(jasperPrint, new FileOutputStream(pdf)); 
//            connection.close();
             ByteArrayOutputStream bous = new ByteArrayOutputStream();
             
            HttpServletResponse httpServletResponse=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();  
            httpServletResponse.setContentType("application/pdf");
            httpServletResponse.setHeader("Content-Disposition", "attachment; filename=file.pdf");
            ServletOutputStream servletOutputStream=httpServletResponse.getOutputStream();  
            JasperExportManager.exportReportToPdfStream(jasperPrint, bous); 
            httpServletResponse.setContentLength(bous.size());
            bous.writeTo(servletOutputStream);
            servletOutputStream.flush();
            servletOutputStream.close();
            FacesContext.getCurrentInstance().responseComplete();
            connection.close();
             
//             JasperExportManager.exportReportToPdfStream(jasperPrint, bous);
//             byte[] bytes = bous.toByteArray();
//             connection.close();
             
//            HttpServletResponse httpServletResponse=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();  
//            httpServletResponse.setContentType("application/application-download");
//            httpServletResponse.setHeader("Content-Disposition", "attachment; filename=<some file.pdf>");
//            OutputStream servletOutputStream=httpServletResponse.getOutputStream();  
//            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream); 
//            
//            servletOutputStream.flush();
//            servletOutputStream.close();
//            FacesContext.getCurrentInstance().responseComplete();
//            connection.close();
            
    }
}
