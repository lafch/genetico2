/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.uch.cursoLibre.logicreport;
import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.BuilderException;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.DJBuilderException;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.GroupLayout;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Page;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * 
 * @author JEAN CARLOS
 */
public class ReportHistorialCurso {

	public static FastReportBuilder generateHeaderHistorialCurse() {

		FastReportBuilder drb = new FastReportBuilder();

		try {

			Style headerStyle = UtilJasper.createStyleOrientBorderBackgFont(HorizontalAlign.CENTER, Border.THIN, Color.LIGHT_GRAY, 10);
			Style titleStyle = UtilJasper.createStyleOrientacionAndBorder(HorizontalAlign.LEFT, Border.DOTTED);
			Style subt = UtilJasper.createStyleOrientacionAndBorder(HorizontalAlign.LEFT, Border.THIN);

			Style h = new Style();
			h.setHorizontalAlign(HorizontalAlign.LEFT);
			h.setBorder(Border.THIN);
			h.setTextColor(Color.black);

			drb.setTitle(" Historial de Cursos llevados").setLeftMargin(30)
					.setRightMargin(30).setTopMargin(30).setBottomMargin(30)
					.setDetailHeight(20).setHeaderHeight(15)
					.setPageSizeAndOrientation(Page.Page_A4_Landscape());

			drb.addColumn("Modulo", "modulo", String.class.getName(), 60,titleStyle, headerStyle);
			drb.addColumn("Curso", "curso", String.class.getName(), 60, subt,subt);
			drb.addColumn("Curso", "curso", String.class.getName(), 50, h,headerStyle);
			drb.addColumn("Taller", "taller", String.class.getName(), 50, h,headerStyle);
			drb.addColumn("F. Inicio", "finicio", String.class.getName(), 15,subt, headerStyle);
			drb.addColumn("F. Fin", "ffin", String.class.getName(), 15, subt,headerStyle);
			drb.addColumn("Condicion", "condicion", String.class.getName(), 20,subt, headerStyle);

			drb.setUseFullPageWidth(true);

		} catch (ColumnBuilderException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return drb;

	}

	public static FastReportBuilder generateSubReportHistorialCurso() {

		FastReportBuilder drb = new FastReportBuilder();

		drb.setLeftMargin(80).setRightMargin(80).setTopMargin(0)
				.setBottomMargin(0).setDetailHeight(20).setHeaderHeight(15);

		Style celda = UtilJasper.createStyleOrientacion(HorizontalAlign.RIGHT,0);
		Style headerStyle = UtilJasper.createStyleOrientacionAndBorder(HorizontalAlign.RIGHT, Border.NO_BORDER);

		try {

			drb.addColumn("EVALUACIONES", "decripcion", String.class.getName(),
					150, celda, headerStyle).addColumn("NOTA", "nota",
					Double.class.getName(), 12, celda, headerStyle)
					.setUseFullPageWidth(true);

		} catch (ColumnBuilderException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return drb;

	}

	
	public static JasperPrint integrateReport(String alu_codigo) throws BuilderException {

		try {
		ArrayList<AlumnoHistorial> listAlumno = ControllerAlumnos.getHistorial(alu_codigo); // 45
		AlumnoHistorial historia=null;
		if(!listAlumno.isEmpty())
				historia=(AlumnoHistorial)listAlumno.get(0);
	          else{
		        historia=new AlumnoHistorial();  
		        historia.setAlucodigo(alu_codigo);
		        historia.setAlumno("---");
	          }
	           
			 
			FastReportBuilder report=generateHeaderHistorialCurse();
			FastReportBuilder subreport=generateSubReportHistorialCurso();
			report.addField("listNota", Collection.class.getName());
			report.addGroups(2).setGroupLayout(2, GroupLayout.EMPTY);
			report.addAutoText("", AutoText.POSITION_HEADER,AutoText.ALIGMENT_LEFT, 200);
			report.addAutoText("CODIGO   : " + historia.getAlucodigo(),AutoText.POSITION_HEADER, AutoText.ALIGMENT_LEFT, 300);
			report.addAutoText("ALUMNO    : " +historia.getAlumno(),AutoText.POSITION_HEADER, AutoText.ALIGMENT_LEFT, 300);
			report.addAutoText("", AutoText.POSITION_HEADER,AutoText.ALIGMENT_LEFT, 200);
                        report.setWhenNoData("No tiene cursos...", null);
			report.addAutoText(AutoText.AUTOTEXT_PAGE_X_OF_Y,
	        		   AutoText.POSITION_FOOTER, AutoText.ALIGMENT_RIGHT,200,30);
			report.addAutoText(AutoText.AUTOTEXT_CREATED_ON,
					AutoText.POSITION_FOOTER, AutoText.ALIGMENT_LEFT,
					AutoText.PATTERN_DATE_DATE_TIME,200,30);
			
			report.addSubreportInGroupFooter(2, subreport.build(),
					new ClassicLayoutManager(), "listNota",
					DJConstants.DATA_SOURCE_ORIGIN_FIELD,
					DJConstants.DATA_SOURCE_TYPE_COLLECTION);

			report.setUseFullPageWidth(true);

			JRDataSource ds = new JRBeanCollectionDataSource(listAlumno);
			JasperPrint jp = DynamicJasperHelper.generateJasperPrint(report
					.build(), new ClassicLayoutManager(), ds);
			
			return jp;

		} catch (JRException ex) {
			Logger.getLogger(SubReportCantidadMatriculado.class.getName()).log(
					Level.SEVERE, null, ex);
		} catch (DJBuilderException ex) {
			Logger.getLogger(SubReportCantidadMatriculado.class.getName()).log(
					Level.SEVERE, null, ex);
		} catch (BuilderException ex) {
			Logger.getLogger(SubReportCantidadMatriculado.class.getName()).log(
					Level.SEVERE, null, ex);
		}
		
		return null;

	}
	
}
