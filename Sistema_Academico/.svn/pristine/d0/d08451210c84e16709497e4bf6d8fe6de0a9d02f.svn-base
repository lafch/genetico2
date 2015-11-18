/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.logicreport;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.Colour;
import jxl.write.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import net.uch.commonService.ServiceFinder;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author cesardl
 */
public class WriteExcel {


   public static WritableFont getWritableFont(int isArial,int tam,int isBold){
     return new WritableFont(isBold!=1?WritableFont.TIMES:WritableFont.ARIAL,tam,isBold!=1?WritableFont.NO_BOLD:WritableFont.BOLD, true);
   }

   public static FileInputStream writePreInscritosSeccion(String nom_file,int sec_id) {

        Connection cnx =null;
        Statement st2 =null;
        ResultSet rs2=null;

        try {
            String csql = "";
            String ruta_file = System.getProperty("user.dir") + File.separator + nom_file;

            WritableWorkbook workbook = Workbook.createWorkbook(new File(ruta_file));
            WritableSheet sheet = workbook.createSheet(" Lista de Pre-Inscritos ", 0);

            DriverManagerDataSource dmds = (DriverManagerDataSource) ServiceFinder.findBean("dataSource");
            cnx = dmds.getConnection();

            csql = "select cl_seccion.sec_nombre,cl_seccion.sec_finicio,cl_seccion.sec_ffin, cl_taller_aperturado.talape_descripcion, cl_taller.tal_descripcion \n" + "from cl_seccion inner join cl_taller_aperturado on cl_seccion.talape_id=cl_taller_aperturado.talape_id \n" + "inner join cl_taller on cl_taller.tal_id=cl_taller_aperturado.tal_id \n" + "where cl_taller_aperturado.talape_activo='1' \n" + "and cl_taller_aperturado.talape_aperturado='1' \n" + "and cl_taller.tal_activo='1' \n" + "and cl_seccion.sec_id=" + sec_id;
            st2 = cnx.createStatement();
            rs2 = st2.executeQuery(csql);

            int fila=2;
//            WritableFont times13font = new WritableFont(WritableFont.TIMES,13, WritableFont.BOLD, true);

//            WritableFont times16font = new WritableFont(WritableFont.ARIAL,10, WritableFont.BOLD, true);
            WritableCellFormat formato1 = new WritableCellFormat(getWritableFont(1,10,1));
            formato1.setBorder(Border.ALL,BorderLineStyle.THIN);
            formato1.setBackground(Colour.LIGHT_GREEN);

            WritableCellFormat formato2 = new WritableCellFormat(getWritableFont(1,10,0));
            formato2.setBorder(Border.ALL, BorderLineStyle.THIN);

            WritableCellFormat formato3 = new WritableCellFormat(getWritableFont(1,10,0));
            formato3.setBorder(Border.BOTTOM, BorderLineStyle.THIN);

//            WritableCellFormat formato4 = new WritableCellFormat(times13font);

            sheet.addCell(new Label(4, 1, "Listado de Pre-Inscritos por Seccion",new WritableCellFormat(getWritableFont(0,14,1))));

            sheet.addCell(new Label(2, fila+3, "Nº",formato1));
            sheet.setColumnView(2,10);

            sheet.addCell(new Label(3,fila+3, "Codigo",formato1));
            sheet.setColumnView(3,15);

            sheet.addCell(new Label(4, fila+3, "Alumno",formato1));
            sheet.setColumnView(4,45);

            sheet.addCell(new Label(5, fila+3, "Unidad",formato1));
            sheet.setColumnView(5,15);

            sheet.addCell(new Label(6, fila+3, "Procedencia",formato1));
            sheet.setColumnView(6,20);

            sheet.addCell(new Label(7, fila+3, "Inscrito",formato1));
            sheet.setColumnView(7,20);

            if(rs2.next()) {
                sheet.addCell(new Label(2, fila, "Taller",formato3));
                sheet.addCell(new jxl.write.Label(3, fila,rs2.getString("talape_descripcion")));
                sheet.addCell(new Label(2,(fila+1), "Seccion",formato3));
                sheet.addCell(new jxl.write.Label(3, (fila+1),rs2.getString("sec_nombre")));
                sheet.addCell(new Label(5, fila, "F. Inicio",formato3));
                sheet.addCell(new jxl.write.Label(6, fila, rs2.getString("sec_finicio")));
                sheet.addCell(new Label(5, (fila+1), "F. Fin",formato3));
                sheet.addCell(new jxl.write.Label(6, (fila+1),rs2.getString("sec_ffin")));
            }

            csql = "select cl_alumno.alu_codigo ,upper(concat_ws(' ',alu_appaterno, alu_apmaterno, alu_nombres)) alumno, \n" + "(select tb_catalogo.cat_descripcion_elemento from tb_catalogo where concat(tb_catalogo.cat_codigo_grupo, cat_codigo_elemento)=alu_procedencia) procedencia, \n" + "(select tb_catalogo.cat_descripcion_elemento from tb_catalogo where concat(tb_catalogo.cat_codigo_grupo, cat_codigo_elemento)=alu_unidad) unidad, \n" + "(if(cl_matricula.mat_tipo='022001', 'INSCRITO','PRE-INSCRITO' ) ) inscrito, \n" + "cl_seccion.sec_nombre \n" + "from cl_alumno inner join cl_matricula on cl_alumno.alu_id=cl_matricula.alu_id \n" + "inner join cl_matricula_taller on cl_matricula_taller.mat_id=cl_matricula.mat_id \n" + "inner join cl_seccion on cl_seccion.sec_id=cl_matricula_taller.sec_id \n" + "where cl_matricula_taller.sec_id=" + sec_id + " and cl_matricula.mat_activo='1' \n" + "and cl_alumno.alu_activo \n" + "and cl_matricula.mat_tipo in('022005') \n" + "order by alumno";
            st2 = cnx.createStatement();
            rs2 = st2.executeQuery(csql);
            int f=0;
            for(int j=fila+4;rs2.next();j++) {
                sheet.addCell(new jxl.write.Number(2, j,++f,formato2));
                sheet.addCell(new jxl.write.Label(3, j,rs2.getString("alu_codigo"),formato2));
                sheet.addCell(new jxl.write.Label(4, j,rs2.getString("alumno"),formato2));
                sheet.addCell(new jxl.write.Label(5, j,rs2.getString("unidad"),formato2));
                sheet.addCell(new jxl.write.Label(6, j,rs2.getString("procedencia"),formato2));
                sheet.addCell(new jxl.write.Label(7, j,rs2.getString("inscrito"),formato2));
            }

            workbook.write();
            workbook.close();

            FileInputStream archivo = new FileInputStream(ruta_file);
            if (new File(ruta_file).delete()) {
                System.out.println("se borro el archivo");
            }
            return archivo;

        } catch (SQLException ex) {
            Logger.getLogger(WriteExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WriteExcel.class.getName()).log(Level.SEVERE, null, ex);
        }catch (WriteException ex) {

        }finally{
            try {
                cnx.close();
            } catch (SQLException ex) {
                Logger.getLogger(WriteExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;

	}


	public static FileInputStream writeInscritosSeccion(String nom_file,int sec_id) {

            Connection cnx = null;
            Statement st2 = null;
            ResultSet rs2 = null;
            String csql = "";

            try {
            String ruta_file = System.getProperty("user.dir") + File.separator + nom_file;
            DriverManagerDataSource dmds = (DriverManagerDataSource) ServiceFinder.findBean("dataSource");
            cnx = dmds.getConnection();
            WritableWorkbook workbook = Workbook.createWorkbook(new File(ruta_file));
            WritableSheet sheet = workbook.createSheet(" Lista de Inscritos por Seccion ", 0);

            csql = "select cl_seccion.sec_nombre,cl_seccion.sec_finicio,cl_seccion.sec_ffin, cl_taller_aperturado.talape_descripcion, cl_taller.tal_descripcion \n" + "from cl_seccion inner join cl_taller_aperturado on cl_seccion.talape_id=cl_taller_aperturado.talape_id \n" + "inner join cl_taller on cl_taller.tal_id=cl_taller_aperturado.tal_id \n" + "where cl_taller_aperturado.talape_activo='1' \n" + "and cl_taller_aperturado.talape_aperturado='1' \n" + "and cl_taller.tal_activo='1' \n" + "and cl_seccion.sec_id=" + sec_id;
            st2 = cnx.createStatement();
            rs2 = st2.executeQuery(csql);
            
            int fila = 2;
            WritableFont times16font = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, true);
            WritableCellFormat formato1 = new WritableCellFormat(times16font);
            formato1.setBorder(Border.ALL, BorderLineStyle.THIN);
            formato1.setBackground(Colour.LIGHT_GREEN);

            WritableCellFormat formato2 = new WritableCellFormat(times16font);
            formato2.setBorder(Border.ALL, BorderLineStyle.THIN);

            WritableCellFormat formato3 = new WritableCellFormat(times16font);
            formato3.setBorder(Border.BOTTOM, BorderLineStyle.THIN);

            sheet.addCell(new Label(4, 1, "Listado de Inscritos por Seccion"));
            sheet.addCell(new Label(2, fila + 3, "Nº", formato1));
            sheet.setColumnView(2, 10);
            sheet.addCell(new Label(3, fila + 3, "Codigo", formato1));
            sheet.setColumnView(3, 15);
            sheet.addCell(new Label(4, fila + 3, "Alumno", formato1));
            sheet.setColumnView(4, 45);
            sheet.addCell(new Label(5, fila + 3, "Unidad", formato1));
            sheet.setColumnView(5, 15);
            sheet.addCell(new Label(6, fila + 3, "Procedencia", formato1));
            sheet.setColumnView(6, 20);
            sheet.addCell(new Label(7, fila + 3, "Inscrito", formato1));
            sheet.setColumnView(7, 20);

            if (rs2.next()) {
                sheet.addCell(new Label(2, fila, "Taller",formato3));
                sheet.addCell(new jxl.write.Label(3, fila, rs2.getString("talape_descripcion")));
                sheet.addCell(new Label(2, fila + 1, "Seccion"));
                sheet.addCell(new jxl.write.Label(3, fila + 1, rs2.getString("sec_nombre"),formato3));
                sheet.addCell(new Label(5, fila, "F. Inicio"));
                sheet.addCell(new jxl.write.Label(6, fila, rs2.getString("sec_finicio"),formato3));
                sheet.addCell(new Label(5, fila + 1, "F. Fin"));
                sheet.addCell(new jxl.write.Label(6, fila + 1, rs2.getString("sec_ffin"),formato3));
            }
            csql = "select cl_alumno.alu_codigo ,upper(concat_ws(' ',alu_appaterno, alu_apmaterno, alu_nombres)) alumno, \n" + "(select tb_catalogo.cat_descripcion_elemento from tb_catalogo where concat(tb_catalogo.cat_codigo_grupo, cat_codigo_elemento)=alu_procedencia) procedencia, \n" + "(select tb_catalogo.cat_descripcion_elemento from tb_catalogo where concat(tb_catalogo.cat_codigo_grupo, cat_codigo_elemento)=alu_unidad) unidad, \n" + "(select tb_catalogo.cat_descripcion_elemento from tb_catalogo where concat(tb_catalogo.cat_codigo_grupo, cat_codigo_elemento)=alu_forma_pago) forma_pago, \n" + "(select tb_catalogo.cat_descripcion_elemento from tb_catalogo where concat(tb_catalogo.cat_codigo_grupo, cat_codigo_elemento)=alu_parentesco) parentesco, \n" + "(if(cl_matricula.mat_tipo='022001', 'INSCRITO','PRE-INSCRITO' ) ) inscrito, alu_nombre_familiar, \n" + "cl_seccion.sec_nombre, cl_alumno.alu_mail, alu_dni, alu_telefono, alu_direccion \n" + "from cl_alumno inner join cl_matricula on cl_alumno.alu_id=cl_matricula.alu_id \n" + "inner join cl_matricula_taller on cl_matricula_taller.mat_id=cl_matricula.mat_id \n" + "inner join cl_seccion on cl_seccion.sec_id=cl_matricula_taller.sec_id \n" + "where cl_matricula_taller.sec_id=" + sec_id + " and cl_matricula.mat_activo='1' \n" + "and cl_alumno.alu_activo \n" + "and cl_matricula.mat_tipo in('022001') \n" + "order by alumno";
            st2 = cnx.createStatement();
            rs2 = st2.executeQuery(csql);
            int f=0;
            for (int j = fila + 4; rs2.next(); j++) {
                    sheet.addCell(new jxl.write.Number(2, j, f++));
                    sheet.addCell(new jxl.write.Label(3, j, rs2.getString("alu_codigo"),formato2));
                    sheet.addCell(new jxl.write.Label(4, j, rs2.getString("alumno"),formato2));
                    sheet.addCell(new jxl.write.Label(5, j, rs2.getString("procedencia"),formato2));
                    sheet.addCell(new jxl.write.Label(6, j, rs2.getString("unidad"),formato2));
                    sheet.addCell(new jxl.write.Label(7, j, rs2.getString("inscrito"),formato2));
                    sheet.addCell(new jxl.write.Label(8, j, rs2.getString("forma_pago"),formato2));
                    sheet.addCell(new jxl.write.Label(9, j, rs2.getString("alu_direccion"),formato2));
                    sheet.addCell(new jxl.write.Label(10, j, rs2.getString("alu_dni"),formato2));
                    sheet.addCell(new jxl.write.Label(11, j, rs2.getString("alu_mail"),formato2));
                    sheet.addCell(new jxl.write.Label(12, j, rs2.getString("alu_telefono"),formato2));
                    sheet.addCell(new jxl.write.Label(13, j, rs2.getString("alu_nombre_familiar"),formato2));
                    sheet.addCell(new jxl.write.Label(14, j, rs2.getString("parentesco"),formato2));

                }
                workbook.write();
                workbook.close();
                FileInputStream archivo = new FileInputStream(ruta_file);
                if (new File(ruta_file).delete()) {
                    System.out.println("se borro el archivo");
                }
                return archivo;

        } catch (WriteException ex) {
            Logger.getLogger(WriteExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WriteExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(WriteExcel.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                cnx.close();
            } catch (SQLException ex) {
                Logger.getLogger(WriteExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;

        }

//public static FileInputStream createPDF(String nom_file){
//        try {
//            FileInputStream archivo = null;
//            String ruta_file = System.getProperty("user.dir") + File.separator + nom_file;
//            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
//            try {
//                //HtmlWriter pdf = HtmlWriter.getInstance(document,
//                //  new FileOutputStream("text.html"));
//                //RtfWriter2 pdf = RtfWriter2.getInstance(document,
//                //  new FileOutputStream("text.rtf"));
//                document.addSubject("This is the result of a Test.");
//                HeaderFooter header = new HeaderFooter(new Phrase("This is a header."), false);
//                HeaderFooter footer = new HeaderFooter(new Phrase("This is page "), new Phrase("."));
//                footer.setAlignment(Element.ALIGN_CENTER);
//                PdfWriter.getInstance(document, new FileOutputStream(ruta_file));
//                document.setHeader(header);
//                document.setFooter(footer);
//                document.open();
//                document.add(new Paragraph("This is test message"));
//                document.close();
//            } catch (DocumentException de) {
//                System.err.println(de.getMessage());
//            } catch (Exception de) {
//                System.err.println(de.getMessage());
//            }
//            archivo = new FileInputStream(ruta_file);
//            return archivo;
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(WriteExcel.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//  }

}
