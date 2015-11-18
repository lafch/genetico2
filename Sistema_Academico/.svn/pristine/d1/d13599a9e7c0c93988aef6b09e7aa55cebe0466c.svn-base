package net.uch.cursoLibre.logicreport;
import ar.com.fdvs.dj.domain.builders.BuilderException;
import ar.com.fdvs.dj.domain.builders.DJBuilderException;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DJCalculation;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.GroupLayout;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.AutoText;

public class SubReportCantidadMatriculado {

    public static FastReportBuilder generateHeaderReport(){

        FastReportBuilder drb = new FastReportBuilder();

        try {

        Style headerColumn = new Style();
        headerColumn.setFont(Font.ARIAL_MEDIUM_BOLD);
        headerColumn.setBorderTop(Border.THIN);
        headerColumn.setHorizontalAlign(HorizontalAlign.CENTER);
        headerColumn.setVerticalAlign(VerticalAlign.TOP);

        Style headerColumnAlu = new Style();
        headerColumnAlu.setFont(Font.ARIAL_MEDIUM_BOLD);
        headerColumnAlu.setBorderTop(Border.THIN);
        headerColumnAlu.setHorizontalAlign(HorizontalAlign.LEFT);
        headerColumnAlu.setVerticalAlign(VerticalAlign.TOP);

        Style headerStyle = UtilJasper.createStyleOrientBorderBackgFont(HorizontalAlign.CENTER, Border.THIN, Color.LIGHT_GRAY, 10);

        drb.setTitle(" Relacion  de Matriculados / Pagos Realizados  ")
           .setLeftMargin(30).setRightMargin(30).setTopMargin(30).setBottomMargin(30)
           .setDetailHeight(20).setHeaderHeight(15);

        drb.addColumn("Nº", "numorden", Integer.class.getName(), 60, headerColumn, headerStyle);
        drb.addColumn("Nº", "numorden", Integer.class.getName(), 8, headerColumn, headerStyle)
           .addColumn("Codigo", "alucodigo", String.class.getName(),10,headerColumn, headerStyle)
           .addColumn("Alumno", "alumno", String.class.getName(), 60, headerColumnAlu, headerStyle)
           .setUseFullPageWidth(true);

        } catch (ColumnBuilderException e) {
                 e.printStackTrace();
        } catch (ClassNotFoundException e) {
                 e.printStackTrace();
             }

        return drb;
			
	}
	 

    public static FastReportBuilder generateSubReport(){
		

	FastReportBuilder drb = new FastReportBuilder();

        drb.setLeftMargin(80).setRightMargin(80).setTopMargin(0).setBottomMargin(0)
            .setDetailHeight(20).setHeaderHeight(15);
             
        Style celda=UtilJasper.createStyleOrientacion(HorizontalAlign.RIGHT,0);

        Style headerStyle = UtilJasper.createStyleOrientacionAndBorder(HorizontalAlign.RIGHT, Border.NO_BORDER);

        Style headerVariables = new Style();
        headerVariables.setFont(Font.ARIAL_MEDIUM_BOLD);
        headerVariables.setTextColor(Color.red);
        headerVariables.setBorderTop(Border.THIN);
        headerVariables.setHorizontalAlign(HorizontalAlign.RIGHT);
        headerVariables.setVerticalAlign(VerticalAlign.TOP);

        Style headerVariables2 = new Style();
        headerVariables2.setFont(Font.ARIAL_MEDIUM_BOLD);
        headerVariables2.setTextColor(Color.red);
        headerVariables2.setHorizontalAlign(HorizontalAlign.RIGHT);
        headerVariables2.setVerticalAlign(VerticalAlign.TOP);

        try {
            drb.addColumn("Pago", "decspago", String.class.getName(), 70,celda, headerStyle)
               .addColumn("Monto", "monto", Double.class.getName(), 10,celda, headerStyle)
               .addColumn("Forma pago", "pagocaja", String.class.getName(), 20,celda, headerStyle).setGrandTotalLegend("Monto a pagar").setGrandTotalLegendStyle(headerVariables2)
               .setWhenNoData("No registra pagos", headerVariables)
               .setUseFullPageWidth(true);
            drb.addGlobalFooterVariable(2, DJCalculation.SUM, headerVariables);

        } catch (ColumnBuilderException e) {
                 e.printStackTrace();
        } catch (ClassNotFoundException e) {
                 e.printStackTrace();
        }

            return drb;
			
        }

    public static JasperPrint integrateHeaderAndSubReport (int secid){
	        
      try {

           FastReportBuilder  report=	generateHeaderReport();
           FastReportBuilder  subreprot=generateSubReport();
          // ArrayList<AlumnoHistorial> listAlumno =ControllerAlumnos.getRelAlumnMatriculados(secid); //45
           ArrayList<AlumnoHistorial> listAlumno =ControllerAlumnos.getListAlutarSeccion(secid); //45
           report.addField("listpago",Collection.class.getName());
           report.addGroups(1).setGroupLayout(1, GroupLayout.EMPTY);

           if(!listAlumno.isEmpty()){
                report.addAutoText("MODULO   : "+listAlumno.get(0).getModulo(), AutoText.POSITION_HEADER, AutoText.ALIGMENT_LEFT,300);
                report.addAutoText("CURSO     : "+listAlumno.get(0).getCurso(), AutoText.POSITION_HEADER, AutoText.ALIGMENT_LEFT,300);
                report.addAutoText("TALLER    : "+listAlumno.get(0).getTaller(), AutoText.POSITION_HEADER, AutoText.ALIGMENT_LEFT,300);
                report.addAutoText("SECCION   : "+listAlumno.get(0).getObs(), AutoText.POSITION_HEADER, AutoText.ALIGMENT_LEFT,500);
                report.addAutoText("F. INICIO : "+listAlumno.get(0).getFinicio() +"  AL  "+listAlumno.get(0).getFfin() , AutoText.POSITION_HEADER, AutoText.ALIGMENT_LEFT,300);
                report.addAutoText("Cant. Mat. : "+listAlumno.get(0).getCantMatriculado(), AutoText.POSITION_HEADER, AutoText.ALIGMENT_RIGHT,300);
            }else
               report.addAutoText("No hay aluymnos matriculados", AutoText.POSITION_HEADER, AutoText.ALIGMENT_LEFT,300);
            
            report.addSubreportInGroupFooter(1, subreprot.build(),new ClassicLayoutManager(), "listpago",
                            DJConstants.DATA_SOURCE_ORIGIN_FIELD,
                            DJConstants.DATA_SOURCE_TYPE_COLLECTION);
            report.addAutoText(AutoText.AUTOTEXT_PAGE_X_OF_Y, AutoText.POSITION_FOOTER, AutoText.ALIGMENT_RIGHT,200,30);
            report.addAutoText(AutoText.AUTOTEXT_CREATED_ON,AutoText.POSITION_FOOTER, AutoText.ALIGMENT_LEFT,
					AutoText.PATTERN_DATE_DATE_TIME,200,30);
			
            report.setUseFullPageWidth(true);

            JRDataSource ds =  new JRBeanCollectionDataSource(listAlumno);
            JasperPrint jp = DynamicJasperHelper.generateJasperPrint( report.build(), new ClassicLayoutManager(),ds);
           
            return jp;
           
       } catch (JRException ex) {
           Logger.getLogger(SubReportCantidadMatriculado.class.getName()).log(Level.SEVERE, null, ex);
       } catch (DJBuilderException ex) {
           Logger.getLogger(SubReportCantidadMatriculado.class.getName()).log(Level.SEVERE, null, ex);
       } catch (BuilderException ex) {
           Logger.getLogger(SubReportCantidadMatriculado.class.getName()).log(Level.SEVERE, null, ex);
       }

      return null;
    }
 

}
