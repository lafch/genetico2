package net.uch.cursoLibre.logicreport;

import java.util.ArrayList;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.DJCalculation;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Page;
import java.util.List;

public class ReportHistorialDeudaObs {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        generateReportHistorialDeudaObs(8);
    }

    public static JasperPrint generateReportHistorialDeudaObs(int mod_id) {

        FastReportBuilder drb = new FastReportBuilder();
        drb.setLeftMargin(30).setRightMargin(30).setTopMargin(30).setBottomMargin(30).setDetailHeight(20).setHeaderHeight(15).setSubtitleHeight(10).setPageSizeAndOrientation(Page.Page_A4_Landscape());

        ArrayList<AlumnoHistorial> listAlumno = ControllerAlumnos.getListStudentDeudores(mod_id);
        AlumnoHistorial historia = null;
        if (listAlumno.isEmpty()) {
            historia = new AlumnoHistorial();
        } else {
            historia = (AlumnoHistorial) listAlumno.get(0);
        }

        drb.addAutoText(" ", AutoText.POSITION_HEADER, AutoText.ALIGMENT_LEFT, 200);
        drb.addAutoText("MODULO    : " + historia.getModulo(), AutoText.POSITION_HEADER, AutoText.ALIGMENT_LEFT, 300);
        drb.setTitle("Reporte deudores Modulo / Curso ");
        // drb.addAutoText("Reporte deudores Modulo / Curso ", AutoText.POSITION_HEADER, AutoText.ALIGMENT_CENTER,200);

        Style header = new Style();
        header.setBorder(Border.THIN);
        header.setHorizontalAlign(HorizontalAlign.CENTER);

        Style headeralu = new Style();
        headeralu.setBorder(Border.THIN);
        headeralu.setHorizontalAlign(HorizontalAlign.LEFT);

        Style grupo = UtilJasper.createStyleOrientacion(HorizontalAlign.LEFT, 1);

        try {
            drb.addColumn("Curso", "curso", String.class.getName(), 20, grupo);
            drb.addColumn("Taller", "taller", String.class.getName(), 60, grupo);
            drb.addColumn("Id", "idAlumno", Integer.class.getName(), 10, header);
            drb.addColumn("Alumno", "alumno", String.class.getName(), 40, headeralu);
            drb.addColumn("Monto a Pagar", "montopagar", Double.class.getName(), 10, header);
            drb.addColumn("Monto pagado", "montopagado", Double.class.getName(), 10, header);
            drb.addColumn("Monto Saldo", "montosaldo", Double.class.getName(), 10, header);
            drb.addColumn("Monto Recargo [20.00]", "montorecargo", Double.class.getName(), 12, header);
            /*drb.addColumn("Observacion", "observacion", String.class.getName(), 20, header);*/
            drb.addColumn("Observacion", "Obs",String.class.getName(), 20, header);
            drb.addGroups(2);

            drb.addGlobalFooterVariable(8, DJCalculation.SUM, null);
            drb.addAutoText(AutoText.AUTOTEXT_PAGE_X_OF_Y,
                    AutoText.POSITION_FOOTER, AutoText.ALIGMENT_RIGHT, 200, 30);
            drb.addAutoText(AutoText.AUTOTEXT_CREATED_ON,
                    AutoText.POSITION_FOOTER, AutoText.ALIGMENT_LEFT,
                    AutoText.PATTERN_DATE_DATE_TIME, 200, 30);

            drb.setUseFullPageWidth(true);

            DynamicReport dr = drb.build();
            JRDataSource ds = new JRBeanCollectionDataSource(listAlumno);

            JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), ds);

            return jp;

        } catch (JRException e) {
            e.printStackTrace();
        } catch (ColumnBuilderException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JasperPrint generateReportMontoDeudaSeccionObs(int sec_id) {

        FastReportBuilder report = new FastReportBuilder();
        report.setLeftMargin(30).setRightMargin(30).setTopMargin(30).setBottomMargin(30).setDetailHeight(20).setHeaderHeight(15).setSubtitleHeight(10).setPageSizeAndOrientation(Page.Page_A4_Landscape());

        List<AlumnoHistorial> listAlumno =ControllerAlumnos.getListMontoPagadoSeccion(sec_id); //45

        if(!listAlumno.isEmpty()){
                report.addAutoText("MODULO   : "+listAlumno.get(0).getModulo(), AutoText.POSITION_HEADER, AutoText.ALIGMENT_LEFT,300);
                report.addAutoText("CURSO     : "+listAlumno.get(0).getCurso(), AutoText.POSITION_HEADER, AutoText.ALIGMENT_LEFT,300);
                report.addAutoText("TALLER    : "+listAlumno.get(0).getTaller(), AutoText.POSITION_HEADER, AutoText.ALIGMENT_LEFT,300);
                report.addAutoText("F. INICIO : "+listAlumno.get(0).getFinicio() +"  AL  "+listAlumno.get(0).getFfin() , AutoText.POSITION_HEADER, AutoText.ALIGMENT_LEFT,300);
                report.addAutoText("Cant. Mat. : "+listAlumno.get(0).getCantMatriculado(), AutoText.POSITION_HEADER, AutoText.ALIGMENT_RIGHT,300);
                report.addAutoText("", AutoText.POSITION_HEADER, AutoText.ALIGMENT_RIGHT,300);
         }else
               report.addAutoText("No hay aluymnos matriculados", AutoText.POSITION_HEADER, AutoText.ALIGMENT_LEFT,300);

        Style header = new Style();
        header.setBorder(Border.THIN);
        header.setHorizontalAlign(HorizontalAlign.CENTER);

        Style headeralu = new Style();
        headeralu.setBorder(Border.THIN);
        headeralu.setHorizontalAlign(HorizontalAlign.LEFT);

        Style grupo = UtilJasper.createStyleOrientacion(HorizontalAlign.LEFT, 1);

        try {

            report.addColumn("Nº", "numorden", Integer.class.getName(), 8, header);
            report.addColumn("Codigo", "alucodigo", String.class.getName(), 10, header);
            report.addColumn("Alumno", "alumno", String.class.getName(), 40, headeralu);
            report.addColumn("Monto a Pagar", "montopagar", Double.class.getName(), 10, header);
            report.addColumn("Monto pagado", "montopagado", Double.class.getName(), 10, header);
            report.addColumn("Monto Saldo", "montosaldo", Double.class.getName(), 10, header);
            report.addColumn("Observacion", "Obs",String.class.getName(), 20, header);

            report.setUseFullPageWidth(true);

            DynamicReport dr = report.build();
            JRDataSource ds = new JRBeanCollectionDataSource(listAlumno);

            JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), ds);

            return jp;

        } catch (JRException e) {
            e.printStackTrace();
        } catch (ColumnBuilderException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
