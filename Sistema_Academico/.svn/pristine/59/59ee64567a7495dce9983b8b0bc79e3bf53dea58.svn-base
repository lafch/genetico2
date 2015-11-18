package net.uch.cursoLibre.logicreport;

import java.awt.Color;
import java.util.ArrayList;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;

public class ReporteHorario {

	@SuppressWarnings("unchecked")
        public static JasperPrint generateReportNotasAula(int secid) {
		FastReportBuilder drb = new FastReportBuilder();
		drb.setLeftMargin(30).setRightMargin(30).setTopMargin(30).setBottomMargin(30).setDetailHeight(2).setHeaderHeight(5);
		drb.setHeaderHeight(15).setTitle(" Reporte Horario y Relacion de ALumnos por Aula ")
				.setSubtitleHeight(10)
				.build();

		ArrayList<Object> listdata = ControllerAlumnos.getNotaForRegyster(secid);
		AlumnoHistorial alumno = (AlumnoHistorial) listdata.get(0);
		drb.addAutoText("MODULO    : " + alumno.getModulo(),AutoText.POSITION_HEADER, AutoText.ALIGMENT_LEFT, 300);
		drb.addAutoText("CURSO    : " + alumno.getCurso(),AutoText.POSITION_HEADER, AutoText.ALIGMENT_LEFT, 300);
		drb.addAutoText("TALLER    : " + alumno.getTaller(),AutoText.POSITION_HEADER, AutoText.ALIGMENT_LEFT, 300);
		drb.addAutoText("Cant. Mat. : " + alumno.getCantMatriculado(),AutoText.POSITION_HEADER, AutoText.ALIGMENT_RIGHT, 300);
		drb.addAutoText("DOCENTE : " + alumno.getDocente(),AutoText.POSITION_HEADER, AutoText.ALIGMENT_LEFT, 200);
		drb.addAutoText("AULA : " + alumno.getAula(), AutoText.POSITION_HEADER,AutoText.ALIGMENT_RIGHT, 400);
		drb.addAutoText("HORARIO : ", AutoText.POSITION_HEADER,AutoText.ALIGMENT_LEFT, 500);
		drb.addAutoText(AutoText.AUTOTEXT_PAGE_X_OF_Y,
     		   AutoText.POSITION_FOOTER, AutoText.ALIGMENT_RIGHT,200,30);
		drb.addAutoText(AutoText.AUTOTEXT_CREATED_ON,
				AutoText.POSITION_FOOTER, AutoText.ALIGMENT_LEFT,
				AutoText.PATTERN_DATE_DATE_TIME,200,30);
                drb.setWhenNoData("Listado de Alumnos Vacio", null);
		String[] hora = alumno.getHorario().split(" / ");
		int tam = hora.length;
		if (tam > 3) {
			drb.addAutoText("              " + hora[0] + " / " + hora[1]+ " / " + hora[2], AutoText.POSITION_HEADER,AutoText.ALIGMENT_LEFT, 600);
			int ntam = tam - 3;
			if (ntam > 3) {
				drb.addAutoText("              " + hora[3] + " / " + hora[4]+ " / " + hora[5], AutoText.POSITION_HEADER,AutoText.ALIGMENT_LEFT, 600);
			} else {
				String h = "";
				for (int i = 3; i < hora.length; i++)
					h = h + hora[i] + " / ";
				drb.addAutoText("              " + h, AutoText.POSITION_HEADER,AutoText.ALIGMENT_LEFT, 600);
			}
		} else {
			String h = "";
			for (int i = 0; i < hora.length; i++)
				h = h + hora[i] + " / ";
			drb.addAutoText("              " + h, AutoText.POSITION_HEADER,AutoText.ALIGMENT_LEFT, 600);
		}
        try {

		ArrayList<AlumnoHistorial> listAlumno = null;

               if(listdata.size()>1){
                   listAlumno = (ArrayList<AlumnoHistorial>) listdata.get(1);

                   Style headerStyle = UtilJasper.createStyleOrientBorderBackgFont(HorizontalAlign.CENTER, Border.THIN, Color.LIGHT_GRAY, 9);

                   Style headercenter = UtilJasper.createStyleOrientacion(HorizontalAlign.CENTER, 0);
		   headercenter.setBorderBottom(Border.THIN);
                   headercenter.setBorderLeft(Border.THIN);
                   headercenter.setBorderRight(Border.THIN);

                   Style headerAlumno=UtilJasper.createStyleOrientacion(HorizontalAlign.LEFT, 0);
                   headerAlumno.setBorderBottom(Border.THIN);

                   drb.addColumn("Nº", "numorden", Integer.class.getName(), 10,headercenter, headerStyle);
                   drb.addColumn("Codigo", "alucodigo", String.class.getName(), 10,headercenter, headerStyle);
                   drb.addColumn("Alumno", "alumno", String.class.getName(), 60,headerAlumno,headerStyle);

			// me aseguro k por lo menos un alumno tenga notas .....
			for (AlumnoHistorial alu : listAlumno) {

				if (!alu.getListNota().isEmpty()) {
					ArrayList<Nota> notas = alu.getListNota();
					for (int i = 0; i < notas.size(); i++) {
						Nota nota = alu.getListNota().get(i);

						drb.addColumn(nota.getDecripcion(), nota
								.getDecripcion(), String.class.getName(), 10,
								headercenter, headerStyle);
					}// salgo del bucle ....
					break;
				}
			}
                    }

                drb.setUseFullPageWidth(true);

			DynamicReport dr = drb.build();
			JRDataSource ds = new JRBeanCollectionDataSource(listAlumno);

			JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr,
					new ClassicLayoutManager(), ds);

			return jp;

		} catch (JRException e) {
			e.printStackTrace();
		} catch (ColumnBuilderException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch(IndexOutOfBoundsException e){
                        e.printStackTrace();
                }
		return null;
	}

}
