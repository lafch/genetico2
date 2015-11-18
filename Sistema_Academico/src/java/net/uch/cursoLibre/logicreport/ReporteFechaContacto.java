/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.logicreport;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.constants.*;
import java.util.Date;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.uch.cursoLibre.managedBeans.beans.BeanReporte;
import net.uch.mapping.ClConsultaPublico;
import net.uch.util.CommonDAO;
//import org.apache.jasper.compiler.BeanRepository;

/**
 *
 * @author USUARIO
 */
public class ReporteFechaContacto {

    public static JasperPrint generarReporteFechaContacto( int iCatId, String sCentroId, int iEspId, int iAreaId,
            int iModId, int iCursoId, int iHorId, int iUsuId, Date fechaIni, Date fechaFin,
            String sProcId, String sMatTipo, String sPrioridadId,
            int iTipo, int iMedioId, int iMedioDetId ) {
        DynamicReport dr;
        FastReportBuilder report;
        JasperPrint jp;
        JRDataSource ds;
        List<BeanReporte> lstConsultaPub;

        report = new FastReportBuilder();
        report.setLeftMargin( 10 ).setRightMargin( 10 ).setTopMargin( 10 ).setBottomMargin( 10 ).setDetailHeight( 15 ).setHeaderHeight( 15 ).setSubtitleHeight( 15 ).setPageSizeAndOrientation( new Page( 2000, 3000, true ) );

        lstConsultaPub = CommonDAO.getClConsultaPublicoDAO().listarReporteFechaContacto( iCatId, sCentroId, iEspId, iAreaId, iModId, iCursoId, iHorId, iUsuId, fechaIni, fechaFin, sProcId, sMatTipo, sPrioridadId, iTipo, iMedioId, iMedioDetId );
        report.setTitle( "FECHA DE CONTACTO PARA " + ( iCatId == 1 ? "CURSO LIBRE" : "CARRERA PROFESIONAL" ) );
        report.addAutoText( "TOTAL : " + lstConsultaPub.size(), AutoText.POSITION_HEADER, AutoText.ALIGMENT_RIGHT, 300 );

        Style header = new Style();
        header.setBorder( Border.THIN );
        header.setHorizontalAlign( HorizontalAlign.CENTER );
        jp = null;
        try {
            report.addColumn( "Nro.", "expr1", String.class.getName(), 10, header );
            report.addColumn( "ALUMNO", "expr2", String.class.getName(), 60, header );
            if ( iCatId == 1 ) {
                report.addColumn( "ÁREA", "expr3", String.class.getName(), 50, header );
                report.addColumn( "MÓDULO", "expr4", String.class.getName(), 50, header );
                report.addColumn( "CURSO", "expr5", String.class.getName(), 50, header );
            } else {
                report.addColumn( "ESPECIALIDAD", "expr3", String.class.getName(), 40, header );
            }
            report.addColumn( "TURNO", "expr6", String.class.getName(), 15, header );
            report.addColumn( "PROCEDENCIA", "expr7", String.class.getName(), 20, header );
            report.addColumn( "PRIORIDAD", "expr8", String.class.getName(), 20, header );
            report.addColumn( "TLF. FIJO", "expr9", String.class.getName(), 20, header );
            report.addColumn( "TLF. CELULAR", "expr10", String.class.getName(), 25, header );
            report.addColumn( "F.CONTACTO", "expr11", String.class.getName(), 20, header );
            report.addColumn( "MEDIO", "expr13", String.class.getName(), 40, header );
            report.addColumn( "MEDIO DET.", "expr14", String.class.getName(), 40, header );
            report.addColumn( "EMAIL", "expr15", String.class.getName(), 40, header );
            report.addColumn( "DISTRITO", "expr18", String.class.getName(), 35, header );
            report.addColumn( "USUARIO", "expr19", String.class.getName(), 20, header );
            if ( iCatId == 1 ) {
                report.addColumn( "HORARIO", "expr16", String.class.getName(), 60, header );
                report.addColumn( "F.INSCRIPCION", "expr17", String.class.getName(), 20, header );

                //report.addColumn( "MATRICULADO", "expr12", String.class.getName(), 20, header );
            }
//            report.addColumn( "OBS.", "expr13", String.class.getName(), 8, header );
            report.setUseFullPageWidth( true );

            dr = report.build();
            ds = new JRBeanCollectionDataSource( lstConsultaPub );

            jp = DynamicJasperHelper.generateJasperPrint( dr, new ClassicLayoutManager(), ds );

        } catch ( JRException e ) {
            e.printStackTrace();
        } catch ( ColumnBuilderException e ) {
            e.printStackTrace();
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace();
        }
        return jp;
    }
}
