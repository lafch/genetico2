package net.uch.cursoLibre.logicreport;

import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; 
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.AbstractLayoutManager;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.core.layout.ListLayoutManager;
import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.DynamicReport;

//import ar.com.
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import net.sf.jasperreports.engine.JasperPrint;
import net.uch.commonService.ServiceFinder;
import net.uch.cursoLibre.hibernateSpringDao.HSSeccionCLDAO;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class dynamicJasper {

    public JasperPrint generarReporteinscritosxSeccion(int sec_Id) {

        Connection cnx = null;
//		cnx = JConnection.getConnection();
        DriverManagerDataSource dmds = (DriverManagerDataSource) ServiceFinder.findBean("dataSource");

        try {
            cnx = dmds.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(dynamicJasper.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {

            int sec_id = sec_Id;
            System.out.println("el sec_id es -> " + sec_id);

            ArrayList<ac_alumno> al = new ArrayList<ac_alumno>();
            String csql = "";
            String taller = "";
            String seccion = "";

            Font font = new Font(
                    3,
                    Font.PDF_ENCODING_Identity_H_Unicode_with_horizontal_writing,
                    true);
            Font font2 = new Font(
                    3,
                    Font.PDF_ENCODING_Identity_H_Unicode_with_horizontal_writing,
                    true);

            font2.setFontSize(10);

            font.setBold(false);
            font.setFontSize(8);
            Style l = new Style();

            Style subt = new Style();
            l.setTextColor(Color.BLACK);
            l.setBorder(Border.THIN);
            l.setFont(font);

            Style headerStyle = new Style();
            headerStyle.setFont(new Font(9, Font._FONT_VERDANA, true));
            headerStyle.setBorder(Border.THIN);
            headerStyle.setHorizontalAlign(HorizontalAlign.CENTER);
            headerStyle.setVerticalAlign(VerticalAlign.MIDDLE);
            headerStyle.setBackgroundColor(Color.LIGHT_GRAY);
            headerStyle.setTextColor(Color.WHITE);
            headerStyle.setTransparency(Transparency.OPAQUE);
            Style titleStyle = new Style();
            titleStyle.setFont(new Font(15, Font._FONT_VERDANA, true));
            titleStyle.setHorizontalAlign(HorizontalAlign.CENTER);

            subt.setTextColor(Color.BLACK);
            subt.setFont(font2);
            subt.setHorizontalAlign(HorizontalAlign.CENTER);
            subt.setBorder(Border.THIN);
            subt.setVerticalAlign(VerticalAlign.MIDDLE);

            try {

                csql = "select cl_seccion.sec_nombre, cl_taller_aperturado.talape_descripcion, cl_taller.tal_descripcion \n"
                        + "from cl_seccion inner join cl_taller_aperturado on cl_seccion.talape_id=cl_taller_aperturado.talape_id \n"
                        + "inner join cl_taller on cl_taller.tal_id=cl_taller_aperturado.tal_id \n"
                        + "where cl_taller_aperturado.talape_activo='1' \n"
                        + "and cl_taller_aperturado.talape_aperturado='1' \n"
                        + "and cl_taller.tal_activo='1' \n"
                        + "and cl_seccion.sec_id=" + sec_id;

                System.out.println("sec id" + sec_id);
                Statement st2 = cnx.createStatement();
                ResultSet rs2 = st2.executeQuery(csql);
                while (rs2.next()) {

                    taller = rs2.getString("talape_descripcion");
                    /*espe = rs2.getString("esp_nombre");
                    curso = rs2.getString("cur_nombre");*/
                    seccion = rs2.getString("sec_nombre");
                    /*profesor = rs2.getString("profesor");*/

                }

            } catch (SQLException e) {
            }

            DynamicReportBuilder drb = new DynamicReportBuilder();
            Integer margin = new Integer(20);
            Integer margin_top = new Integer(20);
            drb.setHeaderHeight(10);
            drb.setTitle("UCH - Alumnos por Seccion").setSubtitle("Taller: " + taller + "    Seccion: " + seccion).setSubtitleStyle(subt).setTitleStyle(
                    titleStyle).setDetailHeight(new Integer(10)).setLeftMargin(
                    margin).setRightMargin(margin).setTopMargin(margin_top).setBottomMargin(
                    margin).setColumnsPerPage(new Integer(1));

            AbstractColumn columnNombre = ColumnBuilder.getInstance().setColumnProperty("nombres", String.class.getName()).setTitle("Alumno").setWidth(new Integer(40)).setStyle(l).setHeaderStyle(headerStyle).build();
            AbstractColumn ColumnNum = ColumnBuilder.getInstance().setColumnProperty("num", Integer.class.getName()).setTitle("Nro").setWidth(new Integer(6)).setStyle(l).setHeaderStyle(headerStyle).build();
            AbstractColumn ColumnCodigo = ColumnBuilder.getInstance().setColumnProperty("codigo", String.class.getName()).setTitle("Codigo").setWidth(new Integer(11)).setStyle(l).setHeaderStyle(headerStyle).build();

            AbstractColumn ColumnUnidad = ColumnBuilder.getInstance().setColumnProperty("unidad", String.class.getName()).setTitle("Unidad").setWidth(new Integer(11)).setStyle(l).setHeaderStyle(headerStyle).build();

            AbstractColumn ColumnProcedencia = ColumnBuilder.getInstance().setColumnProperty("procedencia", String.class.getName()).setTitle("Procedencia").setWidth(new Integer(11)).setStyle(l).setHeaderStyle(headerStyle).build();

            AbstractColumn ColumnInscrito = ColumnBuilder.getInstance().setColumnProperty("inscrito", String.class.getName()).setTitle("Inscrito").setWidth(new Integer(11)).setStyle(l).setHeaderStyle(headerStyle).build();




            drb.addColumn(ColumnNum);
            drb.addColumn(ColumnCodigo);
            drb.addColumn(columnNombre);
            drb.addColumn(ColumnUnidad);
            drb.addColumn(ColumnProcedencia);
            drb.addColumn(ColumnInscrito);

            try {

                csql = "select cl_alumno.alu_codigo ,upper(concat_ws(' ',alu_appaterno, alu_apmaterno, alu_nombres)) alumno, \n"
                        + "(select tb_catalogo.cat_descripcion_elemento from tb_catalogo where concat(tb_catalogo.cat_codigo_grupo, cat_codigo_elemento)=alu_procedencia) procedencia, \n"
                        + "(select tb_catalogo.cat_descripcion_elemento from tb_catalogo where concat(tb_catalogo.cat_codigo_grupo, cat_codigo_elemento)=alu_unidad) unidad, \n"
                        + "(if(cl_matricula.mat_tipo='022001', 'INSCRITO','PRE-INSCRITO' ) ) inscrito, \n"
                        + "cl_seccion.sec_nombre \n"
                        + "from cl_alumno inner join cl_matricula on cl_alumno.alu_id=cl_matricula.alu_id \n"
                        + "inner join cl_matricula_taller on cl_matricula_taller.mat_id=cl_matricula.mat_id \n"
                        + "inner join cl_seccion on cl_seccion.sec_id=cl_matricula_taller.sec_id \n"
                        + "where cl_matricula_taller.sec_id=" + sec_id
                        + " and cl_matricula.mat_activo='1' \n"
                        + "and cl_alumno.alu_activo \n"
                        + "and cl_matricula.mat_tipo in('022001') \n"
                        + "order by alumno";

                Statement st2 = cnx.createStatement();
                ResultSet rs2 = st2.executeQuery(csql);
                int w = 0;
                while (rs2.next()) {
                    w++;

                    ac_alumno a = new ac_alumno();

                    a.setNombres(rs2.getString("alumno"));
                    a.setCodigo(rs2.getString("alu_codigo"));
                    a.setUnidad(rs2.getString("procedencia"));
                    a.setProcedencia(rs2.getString("unidad"));
                    a.setInscrito(rs2.getString("inscrito"));
                    a.setNum(w);

                    al.add(a);
                }

            } catch (SQLException e) {
                System.out.println("error al generar el reporte -> " + e);
            }

            try {
                drb.setUseFullPageWidth(true);
                //drb.setTemplateFile("/pe/uch/edu/jrxml/template.jrxml");
                DynamicReport dr = drb.build();

                JRDataSource ds = new JRBeanCollectionDataSource(al);
                OutputProcessed ouputProcessed = processOutput("Excel");
                net.sf.jasperreports.engine.JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, ouputProcessed.layoutManager,
                        ds);
//				JRAbstractExporter exporter = ouputProcessed.exporter;
//				ByteArrayOutputStream baos = new ByteArrayOutputStream();
//				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
//				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
//				exporter.exportReport();
//
//				byte reportAsBytes[] = baos.toByteArray();
//				response.setContentType(ouputProcessed.contentType);
//				response.setHeader("Content-Disposition", "inline");
//				response.setContentLength(reportAsBytes.length);
//				javax.servlet.ServletOutputStream s = response
//						.getOutputStream();
//				s.write(reportAsBytes, 0, reportAsBytes.length);
//				s.flush();
//				s.close();

                cnx.close();
                return jp;

            } catch (Exception e) {

                throw new ServletException(e.getMessage());
            }
//
//			cnx.close();
//
//			return (mapping.getInputForward());

        } catch (Exception e) {

            return null;
        }

    }

    public JasperPrint generarReportePreInscritosxSeccion(int sec_Id) {

        Connection cnx = null;
//		cnx = JConnection.getConnection();
        DriverManagerDataSource dmds = (DriverManagerDataSource) ServiceFinder.findBean("dataSource");

        try {
            cnx = dmds.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(dynamicJasper.class.getName()).log(Level.SEVERE, null, ex);
        }


        try {

            int sec_id = sec_Id;


            ArrayList<ac_alumno> al = new ArrayList<ac_alumno>();
            String csql = "";
            String taller = "";
            String seccion = "";

            Font font = new Font(
                    3,
                    Font.PDF_ENCODING_Identity_H_Unicode_with_horizontal_writing,
                    true);
            Font font2 = new Font(
                    3,
                    Font.PDF_ENCODING_Identity_H_Unicode_with_horizontal_writing,
                    true);

            font2.setFontSize(10);

            font.setBold(false);
            font.setFontSize(8);
            Style l = new Style();

            Style subt = new Style();
            l.setTextColor(Color.BLACK);
            l.setBorder(Border.THIN);
            l.setFont(font);

            Style headerStyle = new Style();
            headerStyle.setFont(new Font(9, Font._FONT_VERDANA, true));
            headerStyle.setBorder(Border.THIN);
            headerStyle.setHorizontalAlign(HorizontalAlign.CENTER);
            headerStyle.setVerticalAlign(VerticalAlign.MIDDLE);
            headerStyle.setBackgroundColor(Color.LIGHT_GRAY);
            headerStyle.setTextColor(Color.WHITE);
            headerStyle.setTransparency(Transparency.OPAQUE);
            Style titleStyle = new Style();
            titleStyle.setFont(new Font(15, Font._FONT_VERDANA, true));
            titleStyle.setHorizontalAlign(HorizontalAlign.CENTER);

            subt.setTextColor(Color.BLACK);
            subt.setFont(font2);
            subt.setHorizontalAlign(HorizontalAlign.CENTER);
            subt.setBorder(Border.THIN);
            subt.setVerticalAlign(VerticalAlign.MIDDLE);

            try {

                csql = "select cl_seccion.sec_nombre, cl_taller_aperturado.talape_descripcion, cl_taller.tal_descripcion \n"
                        + "from cl_seccion inner join cl_taller_aperturado on cl_seccion.talape_id=cl_taller_aperturado.talape_id \n"
                        + "inner join cl_taller on cl_taller.tal_id=cl_taller_aperturado.tal_id \n"
                        + "where cl_taller_aperturado.talape_activo='1' \n"
                        + "and cl_taller_aperturado.talape_aperturado='1' \n"
                        + "and cl_taller.tal_activo='1' \n"
                        + "and cl_seccion.sec_id=" + sec_id;

                System.out.println("sec id" + sec_id);
                Statement st2 = cnx.createStatement();
                ResultSet rs2 = st2.executeQuery(csql);
                while (rs2.next()) {

                    taller = rs2.getString("talape_descripcion");
                    /*espe = rs2.getString("esp_nombre");
                    curso = rs2.getString("cur_nombre");*/
                    seccion = rs2.getString("sec_nombre");
                    /*profesor = rs2.getString("profesor");*/

                }

            } catch (SQLException e) {
            }

            DynamicReportBuilder drb = new DynamicReportBuilder();
            Integer margin = new Integer(20);
            Integer margin_top = new Integer(20);
            drb.setHeaderHeight(10);
            drb.setTitle("Alumnos Pre-inscritos por Seccion").setSubtitle("Taller: " + taller + "    Seccion: " + seccion).setSubtitleStyle(subt).setTitleStyle(
                    titleStyle).setDetailHeight(new Integer(10)).setLeftMargin(
                    margin).setRightMargin(margin).setTopMargin(margin_top).setBottomMargin(
                    margin).setColumnsPerPage(new Integer(1));

            AbstractColumn columnNombre = ColumnBuilder.getInstance().setColumnProperty("nombres", String.class.getName()).setTitle("Alumno").setWidth(new Integer(40)).setStyle(l).setHeaderStyle(headerStyle).build();
            AbstractColumn ColumnNum = ColumnBuilder.getInstance().setColumnProperty("num", Integer.class.getName()).setTitle("Nro").setWidth(new Integer(6)).setStyle(l).setHeaderStyle(headerStyle).build();
            AbstractColumn ColumnCodigo = ColumnBuilder.getInstance().setColumnProperty("codigo", String.class.getName()).setTitle("Codigo").setWidth(new Integer(11)).setStyle(l).setHeaderStyle(headerStyle).build();

            AbstractColumn ColumnUnidad = ColumnBuilder.getInstance().setColumnProperty("unidad", String.class.getName()).setTitle("Unidad").setWidth(new Integer(11)).setStyle(l).setHeaderStyle(headerStyle).build();

            AbstractColumn ColumnProcedencia = ColumnBuilder.getInstance().setColumnProperty("procedencia", String.class.getName()).setTitle("Procedencia").setWidth(new Integer(11)).setStyle(l).setHeaderStyle(headerStyle).build();

//			AbstractColumn ColumnInscrito = ColumnBuilder.getInstance()
//			.setColumnProperty("inscrito", String.class.getName())
//			.setTitle("Inscrito").setWidth(new Integer(11)).setStyle(l)
//			.setHeaderStyle(headerStyle).build();




            drb.addColumn(ColumnNum);
            drb.addColumn(ColumnCodigo);
            drb.addColumn(columnNombre);
            drb.addColumn(ColumnUnidad);
            drb.addColumn(ColumnProcedencia);
//			drb.addColumn(ColumnInscrito);

            try {

                csql = "select cl_alumno.alu_codigo ,upper(concat_ws(' ',alu_appaterno, alu_apmaterno, alu_nombres)) alumno, \n"
                        + "(select tb_catalogo.cat_descripcion_elemento from tb_catalogo where concat(tb_catalogo.cat_codigo_grupo, cat_codigo_elemento)=alu_procedencia) procedencia, \n"
                        + "(select tb_catalogo.cat_descripcion_elemento from tb_catalogo where concat(tb_catalogo.cat_codigo_grupo, cat_codigo_elemento)=alu_unidad) unidad, \n"
                        + "(if(cl_matricula.mat_tipo='022001', 'INSCRITO','PRE-INSCRITO' ) ) inscrito, \n"
                        + "cl_seccion.sec_nombre \n"
                        + "from cl_alumno inner join cl_matricula on cl_alumno.alu_id=cl_matricula.alu_id \n"
                        + "inner join cl_matricula_taller on cl_matricula_taller.mat_id=cl_matricula.mat_id \n"
                        + "inner join cl_seccion on cl_seccion.sec_id=cl_matricula_taller.sec_id \n"
                        + "where cl_matricula_taller.sec_id=" + sec_id
                        + " and cl_matricula.mat_activo='1' \n"
                        + "and cl_alumno.alu_activo \n"
                        + "and cl_matricula.mat_tipo in('022005') \n"
                        + "order by alumno";

                Statement st2 = cnx.createStatement();
                ResultSet rs2 = st2.executeQuery(csql);
                int w = 0;
                while (rs2.next()) {
                    w++;

                    ac_alumno a = new ac_alumno();

                    a.setNombres(rs2.getString("alumno"));
                    a.setCodigo(rs2.getString("alu_codigo"));
                    a.setUnidad(rs2.getString("procedencia"));
                    a.setProcedencia(rs2.getString("unidad"));
                    //a.setInscrito(rs2.getString("inscrito"));
                    a.setNum(w);

                    al.add(a);
                }

            } catch (SQLException e) {
                System.out.println("error al generar el reporte -> " + e);
            }

            try {
                drb.setUseFullPageWidth(true);
                //drb.setTemplateFile("/pe/uch/edu/jrxml/template.jrxml");
                DynamicReport dr = drb.build();

                JRDataSource ds = new JRBeanCollectionDataSource(al);
                OutputProcessed ouputProcessed = processOutput("Excel");
                net.sf.jasperreports.engine.JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, ouputProcessed.layoutManager,
                        ds);
//				JRAbstractExporter exporter = ouputProcessed.exporter;
//				ByteArrayOutputStream baos = new ByteArrayOutputStream();
//				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
//				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
//				exporter.exportReport();
//
//				byte reportAsBytes[] = baos.toByteArray();
//				response.setContentType(ouputProcessed.contentType);
//				response.setHeader("Content-Disposition", "inline");
//				response.setContentLength(reportAsBytes.length);
//				javax.servlet.ServletOutputStream s = response
//						.getOutputStream();
//				s.write(reportAsBytes, 0, reportAsBytes.length);
//				s.flush();
//				s.close();

                cnx.close();
                return jp;

            } catch (Exception e) {

                throw new ServletException(e.getMessage());
            }

//			cnx.close();
//
//			return (mapping.getInputForward());

        } catch (Exception e) {

            return null;
        }

    }

    public JasperPrint generarReporteinscritosxSeccion_datos(int sec_Id) {

        Connection cnx = null;
//		cnx = JConnection.getConnection();
        DriverManagerDataSource dmds = (DriverManagerDataSource) ServiceFinder.findBean("dataSource");

        try {
            cnx = dmds.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(dynamicJasper.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {

            int sec_id = sec_Id;
            System.out.println("el sec_id es -> " + sec_id);

            ArrayList<ac_alumno> al = new ArrayList<ac_alumno>();
            String csql = "";
            String taller = "";
            String seccion = "";

            Font font = new Font(
                    3,
                    Font.PDF_ENCODING_Identity_H_Unicode_with_horizontal_writing,
                    true);
            Font font2 = new Font(
                    3,
                    Font.PDF_ENCODING_Identity_H_Unicode_with_horizontal_writing,
                    true);

            font2.setFontSize(10);

            font.setBold(false);
            font.setFontSize(8);
            Style l = new Style();

            Style subt = new Style();
            l.setTextColor(Color.BLACK);
            l.setBorder(Border.THIN);
            l.setFont(font);

            Style headerStyle = new Style();
            headerStyle.setFont(new Font(9, Font._FONT_VERDANA, true));
            headerStyle.setBorder(Border.THIN);
            headerStyle.setHorizontalAlign(HorizontalAlign.CENTER);
            headerStyle.setVerticalAlign(VerticalAlign.MIDDLE);
            headerStyle.setBackgroundColor(Color.LIGHT_GRAY);
            headerStyle.setTextColor(Color.WHITE);
            headerStyle.setTransparency(Transparency.OPAQUE);
            Style titleStyle = new Style();
            titleStyle.setFont(new Font(15, Font._FONT_VERDANA, true));
            titleStyle.setHorizontalAlign(HorizontalAlign.CENTER);

            subt.setTextColor(Color.BLACK);
            subt.setFont(font2);
            subt.setHorizontalAlign(HorizontalAlign.CENTER);
            subt.setBorder(Border.THIN);
            subt.setVerticalAlign(VerticalAlign.MIDDLE);

            try {

                csql = "select cl_seccion.sec_nombre, cl_taller_aperturado.talape_descripcion, cl_taller.tal_descripcion \n"
                        + "from cl_seccion inner join cl_taller_aperturado on cl_seccion.talape_id=cl_taller_aperturado.talape_id \n"
                        + "inner join cl_taller on cl_taller.tal_id=cl_taller_aperturado.tal_id \n"
                        + "where cl_taller_aperturado.talape_activo='1' \n"
                        + "and cl_taller_aperturado.talape_aperturado='1' \n"
                        + "and cl_taller.tal_activo='1' \n"
                        + "and cl_seccion.sec_id=" + sec_id;

                System.out.println("sec id" + sec_id);
                Statement st2 = cnx.createStatement();
                ResultSet rs2 = st2.executeQuery(csql);
                while (rs2.next()) {

                    taller = rs2.getString("talape_descripcion");
                    /*espe = rs2.getString("esp_nombre");
                    curso = rs2.getString("cur_nombre");*/
                    seccion = rs2.getString("sec_nombre");
                    /*profesor = rs2.getString("profesor");*/

                }

            } catch (SQLException e) {
            }

            DynamicReportBuilder drb = new DynamicReportBuilder();
            Integer margin = new Integer(20);
            Integer margin_top = new Integer(20);
            drb.setHeaderHeight(10);
            drb.setTitle("UCH - Alumnos por Seccion").setSubtitle("Taller: " + taller + "    Seccion: " + seccion).setSubtitleStyle(subt).setTitleStyle(
                    titleStyle).setDetailHeight(new Integer(10)).setLeftMargin(
                    margin).setRightMargin(margin).setTopMargin(margin_top).setBottomMargin(
                    margin).setColumnsPerPage(new Integer(1));

            AbstractColumn columnNombre = ColumnBuilder.getInstance().setColumnProperty("nombres", String.class.getName()).setTitle("Alumno").setWidth(new Integer(60)).setStyle(l).setHeaderStyle(headerStyle).build();
            AbstractColumn ColumnNum = ColumnBuilder.getInstance().setColumnProperty("num", Integer.class.getName()).setTitle("Nro").setWidth(new Integer(10)).setStyle(l).setHeaderStyle(headerStyle).build();
            AbstractColumn ColumnCodigo = ColumnBuilder.getInstance().setColumnProperty("codigo", String.class.getName()).setTitle("Codigo").setWidth(new Integer(15)).setStyle(l).setHeaderStyle(headerStyle).build();

            AbstractColumn ColumnUnidad = ColumnBuilder.getInstance().setColumnProperty("unidad", String.class.getName()).setTitle("Unidad").setWidth(new Integer(20)).setStyle(l).setHeaderStyle(headerStyle).build();

            AbstractColumn ColumnFormaPago = ColumnBuilder.getInstance().setColumnProperty("forma_pago", String.class.getName()).setTitle("Forma p.").setWidth(new Integer(20)).setStyle(l).setHeaderStyle(headerStyle).build();

            AbstractColumn ColumnParentesco = ColumnBuilder.getInstance().setColumnProperty("parentesco", String.class.getName()).setTitle("Parentesco").setWidth(new Integer(25)).setStyle(l).setHeaderStyle(headerStyle).build();

            AbstractColumn ColumnNombreFami = ColumnBuilder.getInstance().setColumnProperty("alu_nombre_familiar", String.class.getName()).setTitle("Nombre Fami.").setWidth(new Integer(40)).setStyle(l).setHeaderStyle(headerStyle).build();

            AbstractColumn ColumnMail = ColumnBuilder.getInstance().setColumnProperty("alu_mail", String.class.getName()).setTitle("Correo").setWidth(new Integer(40)).setStyle(l).setHeaderStyle(headerStyle).build();

            AbstractColumn ColumnDni = ColumnBuilder.getInstance().setColumnProperty("alu_dni", String.class.getName()).setTitle("DNI").setWidth(new Integer(20)).setStyle(l).setHeaderStyle(headerStyle).build();

            AbstractColumn ColumnTelefono = ColumnBuilder.getInstance().setColumnProperty("alu_telefono", String.class.getName()).setTitle("Telefono").setWidth(new Integer(18)).setStyle(l).setHeaderStyle(headerStyle).build();

            AbstractColumn ColumnDireccion = ColumnBuilder.getInstance().setColumnProperty("alu_direccion", String.class.getName()).setTitle("Direccion").setWidth(new Integer(20)).setStyle(l).setHeaderStyle(headerStyle).build();

            AbstractColumn ColumnProcedencia = ColumnBuilder.getInstance().setColumnProperty("procedencia", String.class.getName()).setTitle("Procedencia").setWidth(new Integer(25)).setStyle(l).setHeaderStyle(headerStyle).build();

            AbstractColumn ColumnInscrito = ColumnBuilder.getInstance().setColumnProperty("inscrito", String.class.getName()).setTitle("Inscrito").setWidth(new Integer(11)).setStyle(l).setHeaderStyle(headerStyle).build();


            drb.addColumn(ColumnNum);
            drb.addColumn(ColumnCodigo);
            drb.addColumn(columnNombre);
            drb.addColumn(ColumnDni);
            drb.addColumn(ColumnTelefono);
            drb.addColumn(ColumnDireccion);
            drb.addColumn(ColumnMail);
            drb.addColumn(ColumnFormaPago);
            drb.addColumn(ColumnUnidad);
            drb.addColumn(ColumnProcedencia);
            drb.addColumn(ColumnParentesco);
            drb.addColumn(ColumnNombreFami);
            drb.addColumn(ColumnInscrito);

            try {

                csql = "select cl_alumno.alu_codigo ,upper(concat_ws(' ',alu_appaterno, alu_apmaterno, alu_nombres)) alumno, \n"
                        + "(select tb_catalogo.cat_descripcion_elemento from tb_catalogo where concat(tb_catalogo.cat_codigo_grupo, cat_codigo_elemento)=alu_procedencia) procedencia, \n"
                        + "(select tb_catalogo.cat_descripcion_elemento from tb_catalogo where concat(tb_catalogo.cat_codigo_grupo, cat_codigo_elemento)=alu_unidad) unidad, \n"
                        + "(select tb_catalogo.cat_descripcion_elemento from tb_catalogo where concat(tb_catalogo.cat_codigo_grupo, cat_codigo_elemento)=alu_forma_pago) forma_pago, \n"
                        + "(select tb_catalogo.cat_descripcion_elemento from tb_catalogo where concat(tb_catalogo.cat_codigo_grupo, cat_codigo_elemento)=alu_parentesco) parentesco, \n"
                        + "(if(cl_matricula.mat_tipo='022001', 'INSCRITO','PRE-INSCRITO' ) ) inscrito, alu_nombre_familiar, \n"
                        + "cl_seccion.sec_nombre, cl_alumno.alu_mail, alu_dni, alu_telefono, alu_direccion \n"
                        + "from cl_alumno inner join cl_matricula on cl_alumno.alu_id=cl_matricula.alu_id \n"
                        + "inner join cl_matricula_taller on cl_matricula_taller.mat_id=cl_matricula.mat_id \n"
                        + "inner join cl_seccion on cl_seccion.sec_id=cl_matricula_taller.sec_id \n"
                        + "where cl_matricula_taller.sec_id=" + sec_id
                        + " and cl_matricula.mat_activo='1' \n"
                        + "and cl_alumno.alu_activo \n"
                        + "and cl_matricula.mat_tipo in('022001') \n"
                        + "order by alumno";

                Statement st2 = cnx.createStatement();
                ResultSet rs2 = st2.executeQuery(csql);
                int w = 0;
                while (rs2.next()) {
                    w++;

                    ac_alumno a = new ac_alumno();

                    a.setNombres(rs2.getString("alumno"));
                    a.setCodigo(rs2.getString("alu_codigo"));
                    a.setUnidad(rs2.getString("procedencia"));
                    a.setProcedencia(rs2.getString("unidad"));
                    a.setInscrito(rs2.getString("inscrito"));
                    a.setForma_pago(rs2.getString("forma_pago"));
                    a.setAlu_direccion(rs2.getString("alu_direccion"));
                    a.setAlu_dni(rs2.getString("alu_dni"));
                    a.setAlu_mail(rs2.getString("alu_mail"));
                    a.setAlu_telefono(rs2.getString("alu_telefono"));
                    a.setAlu_nombre_familiar(rs2.getString("alu_nombre_familiar"));
                    a.setParentesco(rs2.getString("parentesco"));
                    a.setNum(w);

                    al.add(a);
                }

            } catch (SQLException e) {
                System.out.println("error al generar el reporte -> " + e);
            }

            try {
                drb.setUseFullPageWidth(true);
                //drb.setTemplateFile("/pe/uch/edu/jrxml/template.jrxml");
                DynamicReport dr = drb.build();

                JRDataSource ds = new JRBeanCollectionDataSource(al);
                OutputProcessed ouputProcessed = processOutput("Excel");
                net.sf.jasperreports.engine.JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, ouputProcessed.layoutManager,
                        ds);
//				JRAbstractExporter exporter = ouputProcessed.exporter;
//				ByteArrayOutputStream baos = new ByteArrayOutputStream();
//				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
//				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
//				exporter.exportReport();
//
//				byte reportAsBytes[] = baos.toByteArray();
//				response.setContentType(ouputProcessed.contentType);
//				response.setHeader("Content-Disposition", "inline");
//				response.setContentLength(reportAsBytes.length);
//				javax.servlet.ServletOutputStream s = response
//						.getOutputStream();
//				s.write(reportAsBytes, 0, reportAsBytes.length);
//				s.flush();
//				s.close();
                cnx.close();
                return jp;
            } catch (Exception e) {

                throw new ServletException(e.getMessage());
            }

//			cnx.close();
//
//			return (mapping.getInputForward());

        } catch (Exception e) {

            return null;
        }

    }

    public JasperPrint generarReporteMontoPago(int sec_Id) {

        Connection cnx = null;
//		cnx = JConnection.getConnection();
        DriverManagerDataSource dmds = (DriverManagerDataSource) ServiceFinder.findBean("dataSource");

        try {
            cnx = dmds.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(dynamicJasper.class.getName()).log(Level.SEVERE, null, ex);
        }


        try {

            int sec_id = sec_Id;
            System.out.println("el sec_id es -> " + sec_id);

            ArrayList<ac_alumno> al = new ArrayList<ac_alumno>();
            String csql = "";
            String taller = "";
            String seccion = "";

            Font font = new Font(
                    3,
                    Font.PDF_ENCODING_Identity_H_Unicode_with_horizontal_writing,
                    true);
            Font font2 = new Font(
                    3,
                    Font.PDF_ENCODING_Identity_H_Unicode_with_horizontal_writing,
                    true);

            font2.setFontSize(10);

            font.setBold(false);
            font.setFontSize(8);
            Style l = new Style();

            Style subt = new Style();
            l.setTextColor(Color.BLACK);
            l.setBorder(Border.THIN);
            l.setFont(font);

            Style headerStyle = new Style();
            headerStyle.setFont(new Font(9, Font._FONT_VERDANA, true));
            headerStyle.setBorder(Border.THIN);
            headerStyle.setHorizontalAlign(HorizontalAlign.CENTER);
            headerStyle.setVerticalAlign(VerticalAlign.MIDDLE);
            headerStyle.setBackgroundColor(Color.LIGHT_GRAY);
            headerStyle.setTextColor(Color.WHITE);
            headerStyle.setTransparency(Transparency.OPAQUE);
            Style titleStyle = new Style();
            titleStyle.setFont(new Font(15, Font._FONT_VERDANA, true));
            titleStyle.setHorizontalAlign(HorizontalAlign.CENTER);

            subt.setTextColor(Color.BLACK);
            subt.setFont(font2);
            subt.setHorizontalAlign(HorizontalAlign.CENTER);
            subt.setBorder(Border.THIN);
            subt.setVerticalAlign(VerticalAlign.MIDDLE);

            try {

                csql = "select cl_seccion.sec_nombre, cl_taller_aperturado.talape_descripcion, cl_taller.tal_descripcion \n"
                        + "from cl_seccion inner join cl_taller_aperturado on cl_seccion.talape_id=cl_taller_aperturado.talape_id \n"
                        + "inner join cl_taller on cl_taller.tal_id=cl_taller_aperturado.tal_id \n"
                        + "where cl_taller_aperturado.talape_activo='1' \n"
                        + "and cl_taller_aperturado.talape_aperturado='1' \n"
                        + "and cl_taller.tal_activo='1' \n"
                        + "and cl_seccion.sec_id=" + sec_id;

                System.out.println("sec id" + sec_id);
                Statement st2 = cnx.createStatement();
                ResultSet rs2 = st2.executeQuery(csql);
                while (rs2.next()) {

                    taller = rs2.getString("talape_descripcion");
                    /*espe = rs2.getString("esp_nombre");
                    curso = rs2.getString("cur_nombre");*/
                    seccion = rs2.getString("sec_nombre");
                    /*profesor = rs2.getString("profesor");*/

                }

            } catch (SQLException e) {
            }

            DynamicReportBuilder drb = new DynamicReportBuilder();
            Integer margin = new Integer(20);
            Integer margin_top = new Integer(20);
            drb.setHeaderHeight(10);
            drb.setTitle("UCH - Alumnos por Seccion").setSubtitle("Taller: " + taller + "    Seccion: " + seccion).setSubtitleStyle(subt).setTitleStyle(
                    titleStyle).setDetailHeight(new Integer(10)).setLeftMargin(
                    margin).setRightMargin(margin).setTopMargin(margin_top).setBottomMargin(
                    margin).setColumnsPerPage(new Integer(1));

            AbstractColumn columnNombre = ColumnBuilder.getInstance().setColumnProperty("nombres", String.class.getName()).setTitle("Alumno").setWidth(new Integer(100)).setStyle(l).setHeaderStyle(headerStyle).build();
            AbstractColumn ColumnNum = ColumnBuilder.getInstance().setColumnProperty("num", Integer.class.getName()).setTitle("Nro").setWidth(new Integer(30)).setStyle(l).setHeaderStyle(headerStyle).build();
            AbstractColumn ColumnCodigo = ColumnBuilder.getInstance().setColumnProperty("codigo", String.class.getName()).setTitle("Codigo").setWidth(new Integer(60)).setStyle(l).setHeaderStyle(headerStyle).build();

            AbstractColumn ColumnUnidad = ColumnBuilder.getInstance().setColumnProperty("unidad", String.class.getName()).setTitle("Unidad").setWidth(new Integer(100)).setStyle(l).setHeaderStyle(headerStyle).build();

            AbstractColumn ColumnFormaPago = ColumnBuilder.getInstance().setColumnProperty("forma_pago", String.class.getName()).setTitle("Forma p.").setWidth(new Integer(100)).setStyle(l).setHeaderStyle(headerStyle).build();

            AbstractColumn ColumnParentesco = ColumnBuilder.getInstance().setColumnProperty("parentesco", String.class.getName()).setTitle("Parentesco").setWidth(new Integer(100)).setStyle(l).setHeaderStyle(headerStyle).build();

            AbstractColumn ColumnNombreFami = ColumnBuilder.getInstance().setColumnProperty("alu_nombre_familiar", String.class.getName()).setTitle("Nombre Fami.").setWidth(new Integer(100)).setStyle(l).setHeaderStyle(headerStyle).build();

            AbstractColumn ColumnMail = ColumnBuilder.getInstance().setColumnProperty("alu_mail", String.class.getName()).setTitle("Correo").setWidth(new Integer(100)).setStyle(l).setHeaderStyle(headerStyle).build();

            AbstractColumn ColumnDni = ColumnBuilder.getInstance().setColumnProperty("alu_dni", String.class.getName()).setTitle("DNI").setWidth(new Integer(100)).setStyle(l).setHeaderStyle(headerStyle).build();

            AbstractColumn ColumnTelefono = ColumnBuilder.getInstance().setColumnProperty("alu_telefono", String.class.getName()).setTitle("Telefono").setWidth(new Integer(100)).setStyle(l).setHeaderStyle(headerStyle).build();

            AbstractColumn ColumnDireccion = ColumnBuilder.getInstance().setColumnProperty("alu_direccion", String.class.getName()).setTitle("Direccion").setWidth(new Integer(100)).setStyle(l).setHeaderStyle(headerStyle).build();

            AbstractColumn ColumnProcedencia = ColumnBuilder.getInstance().setColumnProperty("procedencia", String.class.getName()).setTitle("Procedencia").setWidth(new Integer(100)).setStyle(l).setHeaderStyle(headerStyle).build();

            AbstractColumn ColumnInscrito = ColumnBuilder.getInstance().setColumnProperty("inscrito", String.class.getName()).setTitle("Inscrito").setWidth(new Integer(100)).setStyle(l).setHeaderStyle(headerStyle).build();

            AbstractColumn ColumnMontoPagar = ColumnBuilder.getInstance().setColumnProperty("monto_pagar", String.class.getName()).setTitle("Monto a Pagar").setWidth(new Integer(100)).setStyle(l).setHeaderStyle(headerStyle).build();

            AbstractColumn ColumnMontoPagado = ColumnBuilder.getInstance().setColumnProperty("monto_pagado", String.class.getName()).setTitle("Monto Pagado").setWidth(100).setStyle(l).setHeaderStyle(headerStyle).build();


            drb.addColumn(ColumnNum);
            drb.addColumn(ColumnCodigo);
            drb.addColumn(columnNombre);
            drb.addColumn(ColumnDni);
            drb.addColumn(ColumnTelefono);
            drb.addColumn(ColumnDireccion);
            drb.addColumn(ColumnMail);
            drb.addColumn(ColumnProcedencia);
            drb.addColumn(ColumnParentesco);
            drb.addColumn(ColumnNombreFami);
            drb.addColumn(ColumnInscrito);
            drb.addColumn(ColumnUnidad);
            drb.addColumn(ColumnFormaPago);
            drb.addColumn(ColumnMontoPagar);

            drb.addColumn(ColumnMontoPagado);

            try {

                csql = "select cl_alumno.alu_codigo ,upper(concat_ws(' ',alu_appaterno, alu_apmaterno, alu_nombres)) alumno, \n"
                        + " (select tb_catalogo.cat_descripcion_elemento from tb_catalogo where concat(tb_catalogo.cat_codigo_grupo, cat_codigo_elemento)=alu_procedencia) procedencia, \n"
                        + " (select tb_catalogo.cat_descripcion_elemento from tb_catalogo where concat(tb_catalogo.cat_codigo_grupo, cat_codigo_elemento)=alu_unidad) unidad, \n"
                        + " (select tb_catalogo.cat_descripcion_elemento from tb_catalogo where concat(tb_catalogo.cat_codigo_grupo, cat_codigo_elemento)=alu_forma_pago) forma_pago, \n"
                        + " (select tb_catalogo.cat_descripcion_elemento from tb_catalogo where concat(tb_catalogo.cat_codigo_grupo, cat_codigo_elemento)=alu_parentesco) parentesco, \n"
                        + " (if(cl_matricula.mat_tipo='022001', 'INSCRITO','PRE-INSCRITO' ) ) inscrito, alu_nombre_familiar, \n"
                        + " cl_seccion.sec_nombre, cl_alumno.alu_mail, alu_dni, alu_telefono, alu_direccion,  cl_alumno_tarifa.alutar_monto, cl_alumno_tarifa.alutar_estado, if(isnull(tabla.monto),'0 ',tabla.monto) monto \n"
                        + " from cl_alumno inner join cl_matricula on cl_alumno.alu_id=cl_matricula.alu_id \n"
                        + " inner join cl_matricula_taller on cl_matricula_taller.mat_id=cl_matricula.mat_id \n"
                        + " inner join cl_seccion on cl_seccion.sec_id=cl_matricula_taller.sec_id \n"
                        + " inner join cl_alumno_tarifa on (cl_alumno.alu_id=cl_alumno_tarifa.alu_id and cl_alumno_tarifa.mat_id=cl_matricula.mat_id ) \n"
                        + " left join ( \n"
                        + " select sum(ad_pago.pag_monto*ad_pago.pag_can) monto, ad_pago.alutar_id, cl_alumno.alu_id from ad_comprobante_pago inner join ad_pago on ad_comprobante_pago.compag_id=ad_pago.compag_id \n"
                        + " inner join cl_alumno on cl_alumno.alu_id=ad_comprobante_pago.compag_cliente \n"
                        + " inner join cl_matricula on cl_alumno.alu_id=cl_matricula.alu_id \n"
                        + " inner join cl_matricula_taller on cl_matricula_taller.mat_id=cl_matricula.mat_id \n"
                        + " inner join cl_alumno_tarifa on (cl_alumno_tarifa.alu_id=cl_alumno.alu_id and cl_alumno_tarifa.mat_id=cl_matricula.mat_id and cl_alumno_tarifa.alutar_id=ad_pago.alutar_id) \n"
                        + " where ad_comprobante_pago.compag_activo='1' \n"
                        + " and ad_comprobante_pago.compag_cliente_tipo='014003' \n"
                        + " and ad_comprobante_pago.compag_tipo='037001' \n"
                        + " and cl_matricula.mat_activo='1' \n"
                        + " and cl_matricula.mat_tipo='022001' \n"
                        + " and cl_matricula_taller.mattal_activo='1' \n"
                        + " and cl_matricula_taller.sec_id=" + sec_id
                        + " group by ad_pago.alutar_id, cl_alumno.alu_id \n"
                        + " ) tabla on tabla.alutar_id=cl_alumno_tarifa.alutar_id \n"
                        + " where cl_matricula_taller.sec_id=" + sec_id
                        + " and cl_matricula.mat_activo='1' \n"
                        + " and cl_alumno.alu_activo \n"
                        + " and cl_matricula.mat_tipo in('022001') \n"
                        + " and cl_alumno_tarifa.alutar_activo='1' \n"
                        + " and cl_matricula_taller.mattal_activo='1' \n"
                        + " order by alumno";

                Statement st2 = cnx.createStatement();
                ResultSet rs2 = st2.executeQuery(csql);
                int w = 0;
                while (rs2.next()) {
                    w++;

                    ac_alumno a = new ac_alumno();
//alutar_monto 
                    a.setNombres(rs2.getString("alumno"));
                    a.setCodigo(rs2.getString("alu_codigo"));
                    a.setUnidad(rs2.getString("procedencia"));
                    a.setProcedencia(rs2.getString("unidad"));
                    a.setInscrito(rs2.getString("inscrito"));
                    a.setForma_pago(rs2.getString("forma_pago"));
                    a.setAlu_direccion(rs2.getString("alu_direccion"));
                    a.setAlu_dni(rs2.getString("alu_dni"));
                    a.setAlu_mail(rs2.getString("alu_mail"));
                    a.setAlu_telefono(rs2.getString("alu_telefono"));
                    a.setAlu_nombre_familiar(rs2.getString("alu_nombre_familiar"));
                    a.setParentesco(rs2.getString("parentesco"));
                    a.setMonto_pagar(rs2.getString("alutar_monto"));
                    a.setMonto_pagado(rs2.getString("monto"));
                    a.setNum(w);

                    al.add(a);
                }

            } catch (SQLException e) {
                System.out.println("error al generar el reporte -> " + e);
            }

            try {
                drb.setUseFullPageWidth(true);
                //drb.setTemplateFile("/pe/uch/edu/jrxml/template.jrxml");
                drb.setColumnSpace(40);
                drb.setIgnorePagination(true);
                DynamicReport dr = drb.build();

                JRDataSource ds = new JRBeanCollectionDataSource(al);
                OutputProcessed ouputProcessed = processOutput("Excelplano");
                net.sf.jasperreports.engine.JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, ouputProcessed.layoutManager,
                        ds);
//				JRAbstractExporter exporter = ouputProcessed.exporter;
//				ByteArrayOutputStream baos = new ByteArrayOutputStream();
//				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
//				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
//				exporter.exportReport();
//
//				byte reportAsBytes[] = baos.toByteArray();
//				response.setContentType(ouputProcessed.contentType);
//				response.setHeader("Content-Disposition", "inline");
//				response.setContentLength(reportAsBytes.length);
//				javax.servlet.ServletOutputStream s = response
//						.getOutputStream();
//				s.write(reportAsBytes, 0, reportAsBytes.length);
//				s.flush();
//				s.close();

                cnx.close();
                return jp;
            } catch (Exception e) {

                throw new ServletException(e.getMessage());
            }

//			cnx.close();

//			return (mapping.getInputForward());

        } catch (Exception e) {

            return null;
        }

    }

    public JasperPrint generarReporteMatriculadosFecha(int sec_Id, String fechaIni, String fechaFin) {

        Connection cnx = null;
//		cnx = JConnection.getConnection();
        DriverManagerDataSource dmds = (DriverManagerDataSource) ServiceFinder.findBean("dataSource");

        try {
            cnx = dmds.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(dynamicJasper.class.getName()).log(Level.SEVERE, null, ex);
        }


        try {

            int sec_id = sec_Id;
            System.out.println("el sec_id es -> " + sec_id);

            ArrayList<ac_alumno> al = new ArrayList<ac_alumno>();
            String csql = "";
            String taller = "";
            String seccion = "";
            String fecha_ini = "";
            String fecha_fin = "";

            Font font = new Font(
                    3,
                    Font.PDF_ENCODING_Identity_H_Unicode_with_horizontal_writing,
                    true);
            Font font2 = new Font(
                    3,
                    Font.PDF_ENCODING_Identity_H_Unicode_with_horizontal_writing,
                    true);

            font2.setFontSize(10);

            font.setBold(false);
            font.setFontSize(8);
            Style l = new Style();

            Style subt = new Style();
            l.setTextColor(Color.BLACK);
            l.setBorder(Border.THIN);
            l.setFont(font);

            Style headerStyle = new Style();
            headerStyle.setFont(new Font(9, Font._FONT_VERDANA, true));
            headerStyle.setBorder(Border.THIN);
            headerStyle.setHorizontalAlign(HorizontalAlign.CENTER);
            headerStyle.setVerticalAlign(VerticalAlign.MIDDLE);
            headerStyle.setBackgroundColor(Color.LIGHT_GRAY);
            headerStyle.setTextColor(Color.WHITE);
            headerStyle.setTransparency(Transparency.OPAQUE);
            Style titleStyle = new Style();
            titleStyle.setFont(new Font(15, Font._FONT_VERDANA, true));
            titleStyle.setHorizontalAlign(HorizontalAlign.CENTER);

            subt.setTextColor(Color.BLACK);
            subt.setFont(font2);
            subt.setHorizontalAlign(HorizontalAlign.CENTER);
            subt.setBorder(Border.THIN);
            subt.setVerticalAlign(VerticalAlign.MIDDLE);

            try {

                csql = "select cl_seccion.sec_nombre, cl_taller_aperturado.talape_descripcion, cl_taller.tal_descripcion, \n"
                        + "date_format(" + fechaIni + ",'%d-%m-%Y') fecha_ini, date_format(" + fechaFin + ",'%d-%m-%Y') fecha_Fin "
                        + " from cl_seccion inner join cl_taller_aperturado on cl_seccion.talape_id=cl_taller_aperturado.talape_id \n"
                        + "inner join cl_taller on cl_taller.tal_id=cl_taller_aperturado.tal_id \n"
                        + "where cl_taller_aperturado.talape_activo='1' \n"
                        + "and cl_taller_aperturado.talape_aperturado='1' \n"
                        + "and cl_taller.tal_activo='1' \n"
                        + "and cl_seccion.sec_id=" + sec_id;
                System.out.println(csql);

                System.out.println("sec id" + sec_id);
                Statement st2 = cnx.createStatement();
                ResultSet rs2 = st2.executeQuery(csql);
                while (rs2.next()) {

                    taller = rs2.getString("talape_descripcion");
                    fecha_ini = rs2.getString("fecha_ini");
                    fecha_fin = rs2.getString("fecha_fin");
                    seccion = rs2.getString("sec_nombre");
                    /*profesor = rs2.getString("profesor");*/

                }

            } catch (SQLException e) {
            }
            System.out.println("fecha_ini " + fecha_ini);

            DynamicReportBuilder drb = new DynamicReportBuilder();
            Integer margin = new Integer(20);
            Integer margin_top = new Integer(20);
            drb.setHeaderHeight(10);
            drb.setTitle("UCH - Alumnos por Seccion").setSubtitle("Taller: " + taller + "    Seccion: " + seccion + "   Fecha Inicio : " + fecha_ini + "   Fecha Fin : " + fecha_fin).setSubtitleStyle(subt).setTitleStyle(
                    titleStyle).setDetailHeight(new Integer(10)).setLeftMargin(
                    margin).setRightMargin(margin).setTopMargin(margin_top).setBottomMargin(
                    margin).setColumnsPerPage(new Integer(1));

            AbstractColumn columnNombre = ColumnBuilder.getInstance().setColumnProperty("nombres", String.class.getName()).setTitle("Alumno").setWidth(new Integer(40)).setStyle(l).setHeaderStyle(headerStyle).build();
            AbstractColumn ColumnNum = ColumnBuilder.getInstance().setColumnProperty("num", Integer.class.getName()).setTitle("Nro").setWidth(new Integer(6)).setStyle(l).setHeaderStyle(headerStyle).build();
            AbstractColumn ColumnCodigo = ColumnBuilder.getInstance().setColumnProperty("codigo", String.class.getName()).setTitle("Codigo").setWidth(new Integer(11)).setStyle(l).setHeaderStyle(headerStyle).build();

            AbstractColumn ColumnUnidad = ColumnBuilder.getInstance().setColumnProperty("unidad", String.class.getName()).setTitle("Unidad").setWidth(new Integer(11)).setStyle(l).setHeaderStyle(headerStyle).build();

            AbstractColumn ColumnProcedencia = ColumnBuilder.getInstance().setColumnProperty("procedencia", String.class.getName()).setTitle("Procedencia").setWidth(new Integer(11)).setStyle(l).setHeaderStyle(headerStyle).build();

            AbstractColumn ColumnInscrito = ColumnBuilder.getInstance().setColumnProperty("inscrito", String.class.getName()).setTitle("Inscrito").setWidth(new Integer(11)).setStyle(l).setHeaderStyle(headerStyle).build();




            drb.addColumn(ColumnNum);
            drb.addColumn(ColumnCodigo);
            drb.addColumn(columnNombre);
            drb.addColumn(ColumnUnidad);
            drb.addColumn(ColumnProcedencia);
            drb.addColumn(ColumnInscrito);

            try {

                csql = "select cl_alumno.alu_codigo ,upper(concat_ws(' ',alu_appaterno, alu_apmaterno, alu_nombres)) alumno, \n"
                        + " (select tb_catalogo.cat_descripcion_elemento from tb_catalogo where concat(tb_catalogo.cat_codigo_grupo, cat_codigo_elemento)=alu_procedencia) procedencia, \n"
                        + " (select tb_catalogo.cat_descripcion_elemento from tb_catalogo where concat(tb_catalogo.cat_codigo_grupo, cat_codigo_elemento)=alu_unidad) unidad, \n"
                        + " (if(cl_matricula.mat_tipo='022001', 'INSCRITO','PRE-INSCRITO' ) ) inscrito, mat_fecha \n"
                        + " from cl_alumno inner join cl_matricula on cl_alumno.alu_id=cl_matricula.alu_id \n"
                        + " inner join cl_matricula_taller on cl_matricula_taller.mat_id=cl_matricula.mat_id \n"
                        + " inner join cl_seccion on cl_seccion.sec_id=cl_matricula_taller.sec_id \n"
                        + " inner join cl_alumno_tarifa on (cl_alumno.alu_id=cl_alumno_tarifa.alu_id and cl_alumno_tarifa.mat_id=cl_matricula.mat_id ) \n"
                        + " where cl_matricula_taller.sec_id=" + sec_id
                        + " and cl_matricula.mat_activo='1' \n"
                        + " and cl_alumno.alu_activo \n"
                        + " and cl_matricula.mat_tipo in('022001') \n"
                        + " and date_format(mat_fecha,'%Y%m%d')>=" + fechaIni
                        + " and date_format(mat_fecha,'%Y%m%d')<=" + fechaFin
                        + " and cl_alumno_tarifa.alutar_activo='1' \n"
                        + " and cl_matricula_taller.mattal_activo='1' \n"
                        + " order by alumno";

                Statement st2 = cnx.createStatement();
                ResultSet rs2 = st2.executeQuery(csql);
                int w = 0;
                while (rs2.next()) {
                    w++;

                    ac_alumno a = new ac_alumno();

                    a.setNombres(rs2.getString("alumno"));
                    a.setCodigo(rs2.getString("alu_codigo"));
                    a.setUnidad(rs2.getString("procedencia"));
                    a.setProcedencia(rs2.getString("unidad"));
                    a.setInscrito(rs2.getString("inscrito"));
                    a.setNum(w);

                    al.add(a);
                }

            } catch (SQLException e) {
                System.out.println("error al generar el reporte -> " + e);
            }

            try {
                drb.setUseFullPageWidth(true);
                //drb.setTemplateFile("/pe/uch/edu/jrxml/template.jrxml");
                DynamicReport dr = drb.build();

                JRDataSource ds = new JRBeanCollectionDataSource(al);
                OutputProcessed ouputProcessed = processOutput("Excel");
                net.sf.jasperreports.engine.JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, ouputProcessed.layoutManager,
                        ds);
//				JRAbstractExporter exporter = ouputProcessed.exporter;
//				ByteArrayOutputStream baos = new ByteArrayOutputStream();
//				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
//				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
//				exporter.exportReport();
//
//				byte reportAsBytes[] = baos.toByteArray();
//				response.setContentType(ouputProcessed.contentType);
//				response.setHeader("Content-Disposition", "inline");
//				response.setContentLength(reportAsBytes.length);
//				javax.servlet.ServletOutputStream s = response
//						.getOutputStream();
//				s.write(reportAsBytes, 0, reportAsBytes.length);
//				s.flush();
//				s.close();
                cnx.close();

                return jp;
            } catch (Exception e) {

                throw new ServletException(e.getMessage());
            }


//			return (mapping.getInputForward());

        } catch (Exception e) {

            return null;
        }

    }

    public JasperPrint generarReporteCursosQInician(int mod_id, int cur_id, int anio_id, int mes_id) {
       // JasperPrint jp = new JasperPrint();
        try {
            Font fContenido = new Font();
            fContenido.setFontSize(10);

            Font fCabecera = new Font();
            fCabecera.setFontSize(12);
            fCabecera.setBold(true);


            Style estiloContenido = new Style();
            estiloContenido.setFont(fContenido);
            estiloContenido.setBorder(Border.THIN);

            Style estiloCabecera = new Style();
            estiloCabecera.setFont(fCabecera);
            estiloCabecera.setBorder(Border.THIN);
            estiloCabecera.setBackgroundColor(Color.GRAY);
            estiloCabecera.setTextColor(Color.WHITE);

            Style headerStyle = new Style();
            headerStyle.setFont(new Font(12, Font._FONT_VERDANA, true));
            headerStyle.setBorderBottom(Border.PEN_2_POINT);
            headerStyle.setBackgroundColor(Color.LIGHT_GRAY);
            headerStyle.setTextColor(Color.WHITE);
            headerStyle.setTransparency(Transparency.OPAQUE);
            headerStyle.setBorder(Border.THIN);
            

            Style titleStyle = new Style();
            titleStyle.setFont(new Font(16, Font._FONT_VERDANA, true));
            titleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
            titleStyle.setVerticalAlign(VerticalAlign.MIDDLE);

            Style subTitleStyle = new Style();
            subTitleStyle.setFont(new Font(18, Font._FONT_VERDANA, true));
            subTitleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
            subTitleStyle.setVerticalAlign(VerticalAlign.MIDDLE);


            List<reporteLibres> listaReporte = new ArrayList<reporteLibres>();
            DynamicReportBuilder drb = new DynamicReportBuilder();
            //FastReportBuilder drb = new FastReportBuilder();
            Integer margin = new Integer(20);
            Integer margin_top = new Integer(80);
           // drb.setHeaderHeight(10);
            drb.setTitle("UCH - Lista de Cursos que Inician").setTitleStyle(titleStyle).setDetailHeight(new Integer(10)).setLeftMargin(
                    margin).setRightMargin(margin).setTopMargin(margin_top).setBottomMargin(
                    margin).setSubtitleStyle(subTitleStyle).setColumnsPerPage(new Integer(1));


            AbstractColumn ColumnNum = ColumnBuilder.getInstance().setColumnProperty("rep_nro", Integer.class.getName()).setTitle("Nro").setWidth(new Integer(15)).setStyle(estiloContenido).build();
            AbstractColumn ColumnCurso = ColumnBuilder.getInstance().setColumnProperty("rep_curso", String.class.getName()).setTitle("Curso").setWidth(new Integer(70)).setStyle(estiloContenido).build();
            AbstractColumn ColumnSeccion = ColumnBuilder.getInstance().setColumnProperty("rep_seccion", String.class.getName()).setTitle("Seccion").setWidth(new Integer(70)).setStyle(estiloContenido).build();
            AbstractColumn ColumnFechaIni = ColumnBuilder.getInstance().setColumnProperty("rep_fecha_ini", Date.class.getName()).setTitle("Fec. Inicio").setWidth(new Integer(35)).setStyle(estiloContenido).setPattern("dd/MM/yyyy").build();
            AbstractColumn ColumnFechaFin = ColumnBuilder.getInstance().setColumnProperty("rep_fecha_fin", Date.class.getName()).setTitle("Fec. Fin").setWidth(new Integer(35)).setStyle(estiloContenido).setPattern("dd/MM/yyyy").build();
            AbstractColumn ColumnMatriculados = ColumnBuilder.getInstance().setColumnProperty("rep_cantidad_mat", Integer.class.getName()).setTitle("Matri.").setWidth(new Integer(20)).setStyle(estiloContenido).build();



            drb.addColumn(ColumnNum);
            drb.addColumn(ColumnCurso);
            drb.addColumn(ColumnSeccion);
            drb.addColumn(ColumnFechaIni);
            drb.addColumn(ColumnFechaFin);
            drb.addColumn(ColumnMatriculados);

            HSSeccionCLDAO daoSec = (HSSeccionCLDAO) ServiceFinder.findBean("SpringHibernateDaoCLSeccion");
            //List lista=daoSec.listarSeccionesModulo(this.w_cur_id);
            List lista = daoSec.listarcantidadadMatriculadosxSeccion(mod_id, cur_id, anio_id, mes_id);
            SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
            for (int i = 0; i < lista.size(); i++) {
                Object[] obj = (Object[]) lista.get(i);
                reporteLibres reporte = new reporteLibres(i + 1, obj[4].toString(), obj[1].toString(), formatoDelTexto.parse(obj[2].toString()),
                        formatoDelTexto.parse(obj[3].toString()), Integer.parseInt(obj[5].toString()));
                listaReporte.add(reporte);
            }

            drb.setUseFullPageWidth(true);
            //drb.setTemplateFile("/pe/uch/edu/jrxml/template.jrxml");
            DynamicReport dr = drb.build();
            //JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), ds);
            JRDataSource ds = new JRBeanCollectionDataSource(listaReporte);
            OutputProcessed ouputProcessed = processOutput("PDF");
            JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, ouputProcessed.layoutManager, ds);
            //jp=DynamicJasperHelper.generateJasperReport(dr, new ClassicLayoutManager(), ds);
            return jp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        

    }

    public JasperPrint generarCertificadoEstudios(int mod_id, int alu_id) {
        JasperPrint jasperPrint = null;
        try {
            Style titleStyle = new Style();
            titleStyle.setFont(new Font(22, Font._FONT_VERDANA, true));
            titleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
            titleStyle.setVerticalAlign(VerticalAlign.MIDDLE);

            Style subTitleStyle = new Style();
            subTitleStyle.setFont(new Font(18, Font._FONT_VERDANA, true));
            subTitleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
            subTitleStyle.setVerticalAlign(VerticalAlign.MIDDLE);
            DynamicReportBuilder drb = new DynamicReportBuilder();
            //FastReportBuilder drb=new FastReportBuilder();
            Integer margin = new Integer(20);
            Integer margin_top = new Integer(120);
            drb.setHeaderHeight(10);
            drb.setTitle("CERTIFICADO").setTitleStyle(titleStyle).setSubtitle("EL CENTRO DE CAPACITACIÓN PROFESIONAL DE LA "+
"UNIVERSIDAD DE CIENCIAS Y HUMANIDADES, HA CONFERIDO LA "+
"CERTIFICACIÓN A:").setDetailHeight(new Integer(50)).setLeftMargin(
                    margin).setRightMargin(margin).setTopMargin(margin_top).setBottomMargin(
                    margin).setHeaderHeight(new Integer(50)).setSubtitleStyle(subTitleStyle).setColumnsPerPage(new Integer(1));
             
             AbstractColumn ColumnNum = ColumnBuilder.getInstance().setColumnProperty("rep_dato_completo", String.class.getName()).setTitle("dato").setWidth(new Integer(15)).build();
             //drb.addAutoText("Autotext below Page counter", AutoText.POSITION_FOOTER, AutoText.ALIGMENT_LEFT);
             drb.addAutoText("Por haber culminado satisfactoriamente el MÓDULO DE <<NOMBRE MODULO>> , realizado del 15 de enero al 15 de febrero del 2011, con una de 20 horas obteniendo la nota aprobatoria de 18(dieciocho)", AutoText.POSITION_HEADER , AutoText.ALIGMENT_RIGHT);
            //drb.setTemplateFile("/pe/uch/edu/jrxml/template.jrxml");
             drb.addColumn(ColumnNum);
             drb.setUseFullPageWidth(true);
            DynamicReport dr = drb.build();
            List<reporteLibres> dato=new ArrayList<reporteLibres>();
            reporteLibres reporte1=new reporteLibres("RICHARD RONDINEL BUSTAMANTE", "15");
            reporteLibres reporte2=new reporteLibres("RONDINEL BUSTAMANTE RICHARD", "15");
            reporteLibres reporte3=new reporteLibres("BUSTAMANTE RICHARD RONDINEL", "15");
            dato.add(reporte1);
            dato.add(reporte2);
            dato.add(reporte3);
            JRDataSource ds = new JRBeanCollectionDataSource(dato);
            OutputProcessed ouputProcessed = processOutput("PDF");
            jasperPrint = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), ds);
            //HashMap p=new HashMap();
            //jasperPrint = DynamicJasperHelper.generateJasperPrint(dr, ouputProcessed.layoutManager, p);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jasperPrint;
    }
    // paartir de caa agregue codigo yop....
//	public ActionForward generarReporteprueba(ActionMapping mapping,
//			ActionForm form, HttpServletRequest request,
//			HttpServletResponse response,String sec_Id) {
//
//
//		try {
//
//			System.out.println("el sec_id es -> "+sec_Id);
//			try {
//
//				net.sf.jasperreports.engine.JasperPrint jp=ReportHistorialCurso.integrateReport(sec_Id);
//				OutputProcessed ouputProcessed = processOutput("PDF");
//				JRAbstractExporter exporter = ouputProcessed.exporter;
//				ByteArrayOutputStream baos = new ByteArrayOutputStream();
//				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
//				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
//				exporter.exportReport();
//
//				byte reportAsBytes[] = baos.toByteArray();
//				response.setContentType(ouputProcessed.contentType);
//				response.setHeader("Content-Disposition", "inline");
//				response.setContentLength(reportAsBytes.length);
//				javax.servlet.ServletOutputStream s = response
//						.getOutputStream();
//				s.write(reportAsBytes, 0, reportAsBytes.length);
//				s.flush();
//				s.close();
//
//			} catch (Exception e) {
//
//				throw new ServletException(e.getMessage());
//			}
//
//			return (mapping.getInputForward());
//
//		} catch (Exception e) {
//
//			return null;
//		}
//
//	}
//
//	public ActionForward generarReportDeudores(ActionMapping mapping,
//			ActionForm form, HttpServletRequest request,
//			HttpServletResponse response,int sec_Id) {
//
//
//		try {
//
//			System.out.println("el sec_id es -> "+sec_Id);
//			try {
//
//				net.sf.jasperreports.engine.JasperPrint jp=ReportHistorialDeuda.generateReportHistorialDeuda(sec_Id);
//				OutputProcessed ouputProcessed = processOutput("PDF");
//				JRAbstractExporter exporter = ouputProcessed.exporter;
//				ByteArrayOutputStream baos = new ByteArrayOutputStream();
//				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
//				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
//				exporter.exportReport();
//
//				byte reportAsBytes[] = baos.toByteArray();
//				response.setContentType(ouputProcessed.contentType);
//				response.setHeader("Content-Disposition", "inline");
//				response.setContentLength(reportAsBytes.length);
//				javax.servlet.ServletOutputStream s = response.getOutputStream();
//				s.write(reportAsBytes, 0, reportAsBytes.length);
//				s.flush();
//				s.close();
//
//			} catch (Exception e) {
//
//				throw new ServletException(e.getMessage());
//			}
//
//			return (mapping.getInputForward());
//
//		} catch (Exception e) {
//
//			return null;
//		}
//
//	}
//
//	public ActionForward generarReportHorario(ActionMapping mapping,
//			ActionForm form, HttpServletRequest request,
//			HttpServletResponse response,int sec_Id) {
//
//
//		try {
//
//			System.out.println("el sec_id es -> "+sec_Id);
//			try {
//
//				net.sf.jasperreports.engine.JasperPrint jp=ReporteHorario.generateReportNotasAula(sec_Id);
//				OutputProcessed ouputProcessed = processOutput("PDF");
//				JRAbstractExporter exporter = ouputProcessed.exporter;
//				ByteArrayOutputStream baos = new ByteArrayOutputStream();
//				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
//				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
//				exporter.exportReport();
//
//				byte reportAsBytes[] = baos.toByteArray();
//				response.setContentType(ouputProcessed.contentType);
//				response.setHeader("Content-Disposition", "inline");
//				response.setContentLength(reportAsBytes.length);
//				javax.servlet.ServletOutputStream s = response.getOutputStream();
//				s.write(reportAsBytes, 0, reportAsBytes.length);
//				s.flush();
//				s.close();
//
//			} catch (Exception e) {
//
//				throw new ServletException(e.getMessage());
//			}
//
//			return (mapping.getInputForward());
//
//		} catch (Exception e) {
//
//			return null;
//		}
//
//	}
//
//	public ActionForward generateReportRegysterNote(ActionMapping mapping,
//			ActionForm form, HttpServletRequest request,
//			HttpServletResponse response,int sec_Id) {
//
//		try {
//
//			System.out.println("el sec_id es -> "+sec_Id);
//			try {
//
//				net.sf.jasperreports.engine.JasperPrint jp=ReporteRegistroNota.generateReportNotasAula(sec_Id);
//				OutputProcessed ouputProcessed = processOutput("PDF");
//				JRAbstractExporter exporter = ouputProcessed.exporter;
//				ByteArrayOutputStream baos = new ByteArrayOutputStream();
//				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
//				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
//				exporter.exportReport();
//
//				byte reportAsBytes[] = baos.toByteArray();
//				response.setContentType(ouputProcessed.contentType);
//				response.setHeader("Content-Disposition", "inline");
//				response.setContentLength(reportAsBytes.length);
//				javax.servlet.ServletOutputStream s = response.getOutputStream();
//				s.write(reportAsBytes, 0, reportAsBytes.length);
//				s.flush();
//				s.close();
//
//			} catch (Exception e) {
//
//				throw new ServletException(e.getMessage());
//			}
//
//			return (mapping.getInputForward());
//
//		} catch (Exception e) {
//
//			return null;
//		}
//
//	}
//
//	public ActionForward generarReportRelacionALumnosXAula(ActionMapping mapping,
//			ActionForm form, HttpServletRequest request,
//			HttpServletResponse response,int sec_Id) {
//
//		try {
//
//			System.out.println("el sec_id es -> "+sec_Id);
//			try {
//
//				net.sf.jasperreports.engine.JasperPrint jp=SubReportCantidadMatriculado.integrateHeaderAndSubReport(sec_Id);
//				OutputProcessed ouputProcessed = processOutput("PDF");
//				JRAbstractExporter exporter = ouputProcessed.exporter;
//				ByteArrayOutputStream baos = new ByteArrayOutputStream();
//				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
//				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
//				exporter.exportReport();
//
//				byte reportAsBytes[] = baos.toByteArray();
//				response.setContentType(ouputProcessed.contentType);
//				response.setHeader("Content-Disposition", "inline");
//				response.setContentLength(reportAsBytes.length);
//				javax.servlet.ServletOutputStream s = response.getOutputStream();
//				s.write(reportAsBytes, 0, reportAsBytes.length);
//				s.flush();
//				s.close();
//
//			} catch (Exception e) {
//
//				throw new ServletException(e.getMessage());
//			}
//
//			return (mapping.getInputForward());
//
//		} catch (Exception e) {
//
//			return null;
//		}
//
//	}
//

    private OutputProcessed processOutput(String output) {
        OutputProcessed result = new OutputProcessed();
        if (output.equals("PDF")) {
            result.contentType = "application/pdf";
            result.exporter = new JRPdfExporter();
            result.layoutManager = new ClassicLayoutManager();
        } else if (output.equals("HTML")) {
            result.contentType = "text/html";
            result.exporter = new JRHtmlExporter();
            result.exporter.setParameter(
                    JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,
                    Boolean.FALSE);
            result.layoutManager = new ClassicLayoutManager();
        } else if (output.equals("Excel")) {
            result.contentType = "application/vnd.ms-excel";
            result.exporter = new JRXlsExporter();
            result.exporter.setParameter(
                    JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
                    Boolean.FALSE);
            result.exporter.setParameter(
                    JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
                    Boolean.TRUE);
            result.layoutManager = new ClassicLayoutManager();
        } else if (output.equals("Excelplano")) {
            result.contentType = "application/vnd.ms-excel";
            result.exporter = new JRXlsExporter();
            result.exporter.setParameter(
                    JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
                    Boolean.FALSE);
            result.exporter.setParameter(
                    JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
                    Boolean.TRUE);
            result.layoutManager = new ListLayoutManager();
        } else {
            result.contentType = "application/rtf";
            result.exporter = new JRRtfExporter();
            result.layoutManager = new ClassicLayoutManager();
        }
        return result;
    }

    private class OutputProcessed {

        public AbstractLayoutManager layoutManager;
        public JRAbstractExporter exporter;
        public String contentType;

        private OutputProcessed() {
        }
    }
}
