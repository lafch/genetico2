package net.uch.cursoLibre.logicreport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.uch.administrativa.hibernateSpringDao.HSPagoDAO;
import net.uch.commonService.ServiceFinder;
import net.uch.cursoLibre.hibernateSpringDao.HSAlumnoTarifaCLDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSMatriculaCLDAO;
import net.uch.mapping.AdPago;
import net.uch.mapping.ClAlumno;
import net.uch.mapping.ClAlumnoTarifa;
import net.uch.mapping.ClArbolAcademico;
import net.uch.mapping.ClArbolAperturado;
import net.uch.mapping.ClMatricula;
import net.uch.mapping.ClMatriculaTaller;
import net.uch.util.CommonDAO;
import org.hibernate.SQLQuery;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class ControllerAlumnos {

    public static ArrayList<AlumnoHistorial> getRelAlumnMatriculados( int secid ) {
        try {
            ArrayList<AlumnoHistorial> listAlu = new ArrayList<AlumnoHistorial>();
            DriverManagerDataSource dmds = (DriverManagerDataSource) ServiceFinder.findBean( "dataSource" );

            Connection cn = dmds.getConnection();
            //Connection cn = JConnection.getConnection();
            PreparedStatement pstAlu = null;
            ResultSet rsAlu = null;
            StringBuilder alumnoSql = new StringBuilder( "select alu.alu_codigo,mat.alu_id,concat_Ws('   SEC.:  ',talape.talape_descripcion,sec.sec_nombre),DATE_FORMAT(sec.sec_finicio,'%d/%m/%y'),DATE_FORMAT(sec.sec_ffin,'%d/%m/%y'), " );
            alumnoSql.append( "concat_Ws(' ',alu.alu_appaterno,alu.alu_apmaterno,alu.alu_nombres) as 'Alumno', " );
            alumnoSql.append( "(select count(*) from cl_matricula_taller mattal inner join cl_matricula mat ON mattal.mat_id=mat.mat_id " );
            alumnoSql.append( "where mattal.sec_id=" + secid + " and mattal.mattal_activo='1' and mat.mat_Activo='1' and mat.mat_tipo='022001') as cant, " );
            alumnoSql.append( "(select mod_descripcion from cl_modulo where mod_id=(select mod_id from cl_curso where cur_id=(select cur_id from cl_taller where " );
            alumnoSql.append( "tal_id=talape.tal_id )) ) as modulo, " );
            alumnoSql.append( "(select cur_nombre from cl_curso where cur_id=(select cur_id from cl_taller where " );
            alumnoSql.append( "tal_id=talape.tal_id )) as curso , alu.alu_codigo as codigo " );
            alumnoSql.append( "from  cl_matricula_taller matta " );
            alumnoSql.append( "inner join cl_matricula mat ON matta.mat_id=mat.mat_id " );
            alumnoSql.append( "inner join cl_seccion sec ON matta.sec_id=sec.sec_id " );
            alumnoSql.append( "inner join cl_taller_aperturado talape ON sec.talape_id=talape.talape_id " );
            alumnoSql.append( "inner join cl_alumno alu ON alu.alu_id=mat.alu_id " );
            alumnoSql.append( "where matta.sec_id=" + secid + " and matta.mattal_activo='1' and sec.sec_activo ='1' " );
            alumnoSql.append( "and mat.mat_tipo='022001' and mat.mat_activo='1' and talape.talape_activo='1' " );
            alumnoSql.append( "order by Alumno " );
            StringBuilder pagoAlumno = new StringBuilder( "select epd.estpagdet_nombre,pag.pag_monto, " );
            pagoAlumno.append( "(case " );
            pagoAlumno.append( "when compag_procedencia='015001' then 'Vaocher-Comprobante' " );
            pagoAlumno.append( "when compag_procedencia='015002' then 'Pago en caja' " );
            pagoAlumno.append( "when compag_procedencia='015003' then 'Saldo' " );
            pagoAlumno.append( "when compag_procedencia='015004' then 'Vale' " );
            pagoAlumno.append( "when compag_procedencia='015005' then 'Vale-Boleta' " );
            pagoAlumno.append( "when compag_procedencia='015006' then 'Transferencia' " );
            pagoAlumno.append( "when compag_procedencia='015007' then 'Pago banco' " );
            pagoAlumno.append( "else 'otr. tipo pago' " );
            pagoAlumno.append( "END) as 'Tipo pago' " );
            pagoAlumno.append( "from ad_comprobante_pago compag " );
            pagoAlumno.append( "inner join ad_pago pag ON compag.compag_id=pag.compag_id " );
            pagoAlumno.append( "inner join cl_alumno_tarifa alutar ON alutar.alutar_id=pag.alutar_id " );
            pagoAlumno.append( "inner join cl_estructura_pagos_detalle  epd ON alutar.estpagdet_id=epd.estpagdet_id " );
            pagoAlumno.append( "inner join cl_estructura_pagos ep ON epd.estpag_id=ep.estpag_id " );
            pagoAlumno.append( "where compag.compag_activo='1' and pag.pag_Activo='1' and compag.compag_cliente_tipo='014003' " );
            pagoAlumno.append( "and compag.compag_tipo='037001' and compag.compag_cliente=?    and sec_id=?" );
            PreparedStatement pstNot = null;
            ResultSet rsNot = null;

            try {
                pstAlu = cn.prepareStatement( alumnoSql.toString() );
                //  System.out.println(alumnoSql.toString());
                rsAlu = pstAlu.executeQuery();
                for ( int i = 1; rsAlu.next(); i++ ) {
                    // System.out.println(rsAlu.getInt(2) + " ---> " + secid);
                    AlumnoHistorial pojo = new AlumnoHistorial();
                    pojo.setNumorden( i );
                    pojo.setIdAlumno( rsAlu.getInt( 2 ) );
                    pojo.setAlucodigo( rsAlu.getString( 1 ).trim() );
                    pojo.setAlumno( rsAlu.getString( "Alumno" ).trim() );
                    pojo.setModulo( rsAlu.getString( "modulo" ).trim() );
                    pojo.setCurso( rsAlu.getString( "curso" ).trim() );
                    pojo.setTaller( rsAlu.getString( 3 ).trim() );
                    pojo.setCantMatriculado( rsAlu.getInt( 7 ) );
                    pojo.setFinicio( rsAlu.getString( 4 ) );
                    pojo.setFfin( rsAlu.getString( 5 ) );

                    pstNot = cn.prepareStatement( pagoAlumno.toString() );
                    pstNot.setInt( 1, rsAlu.getInt( 2 ) );
                    pstNot.setInt( 2, secid );
                    rsNot = pstNot.executeQuery();
                    ArrayList<Pago> listpago = new ArrayList<Pago>();
                    double suma = 0.0;
                    while ( rsNot.next() ) {
                        //   System.out.println(" si entra  ps...");
                        Pago pago = new Pago();
                        pago.setDecspago( rsNot.getString( 1 ) );
                        pago.setMonto( rsNot.getDouble( 2 ) );
                        pago.setPagocaja( rsNot.getString( 3 ) );
                        suma = suma + rsNot.getDouble( 2 );
                        listpago.add( pago );
                    }
                    pojo.setListpago( listpago );
                    listAlu.add( pojo );
                }
            } catch ( SQLException e ) {
                e.printStackTrace();
            } finally {
                try {
                    pstAlu.close();
                    rsAlu.close();
                    if ( pstNot != null ) {
                        pstNot.close();
                    }
                    if ( rsNot != null ) {
                        rsNot.close();
                    }
                    cn.close();
                } catch ( SQLException e ) {
                    e.printStackTrace();
                }
            }

            return listAlu;

        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
        return null;
    }

    public static ArrayList<AlumnoHistorial> getListStudentDeudores( int mod_id ) {
        ArrayList<AlumnoHistorial> listAlu = new ArrayList<AlumnoHistorial>();
        DriverManagerDataSource dmds = (DriverManagerDataSource) ServiceFinder.findBean( "dataSource" );

        Connection cn = null;
        try {
            cn = dmds.getConnection();
        } catch ( SQLException ex ) {
            Logger.getLogger( ControllerAlumnos.class.getName() ).log( Level.SEVERE, null, ex );
        }

        PreparedStatement pstAlu = null;
        ResultSet rsAlu = null;

        StringBuilder alumnoSql = new StringBuilder( "select  alutar.alutar_id,mattal.sec_id,alutar.sec_id, " );
        alumnoSql.append( "mattal.mattal_id,alutar.estpagdet_id,alutar.alu_id, " );
        alumnoSql.append( "modulo.mod_descripcion,cur.cur_nombre,tal.tal_descripcion,concat(talape.talape_descripcion,' [',DATE_FORMAT(sec.sec_finicio,'%d/%m/%y'),' - ',DATE_FORMAT(sec.sec_ffin,'%d/%m/%y'),'] ') , " );
        alumnoSql.append( "(select concat_Ws(' ',alu_appaterno,alu_apmaterno,alu_nombres) from cl_alumno where alu_id=mat.alu_id) as alumno " );
        alumnoSql.append( "from cl_estructura_pagos_detalle epd " );
        alumnoSql.append( "inner join cl_alumno_tarifa alutar ON epd.estpagdet_id=alutar.estpagdet_id  " );
        alumnoSql.append( "inner join cl_seccion sec ON alutar.sec_id=sec.sec_id  " );
        alumnoSql.append( "inner join cl_taller_aperturado talape ON talape.talape_id=sec.talape_id " );
        alumnoSql.append( "inner join cl_matricula mat ON mat.mat_id=alutar.mat_id " );
        alumnoSql.append( "inner join cl_matricula_taller mattal on mattal.mat_id=mat.mat_id and sec.sec_id=mattal.sec_id " );
        alumnoSql.append( "inner join cl_taller tal ON talape.tal_id=tal.tal_id " );
        alumnoSql.append( "inner join cl_curso cur ON tal.cur_id=cur.cur_id " );
        alumnoSql.append( "inner join cl_modulo modulo ON modulo.mod_id=cur.mod_id " );
        alumnoSql.append( "where sec.sec_activo='1' and talape.talape_activo='1' and tal.tal_activo='1' " );
        alumnoSql.append( "and cur.cur_activo='1' and modulo.mod_activo='1' and alutar.alutar_estado<>2 and mattal.mattal_activo='1' and mat.mat_activo='1' " );
        alumnoSql.append( "and mat.mat_tipo='022001' and alutar.alutar_activo='1' and epd.estpagdet_activo='1'  and modulo.mod_id=? " );
        alumnoSql.append( "order by cur_nombre,mattal.sec_id,alumno " );

        StringBuilder pagosSql = new StringBuilder( "select alutar.alutar_monto as monto, " );
        pagosSql.append( "ifnull((select  pag.pag_monto from ad_pago pag inner join ad_comprobante_pago compag ON pag.compag_id=compag.compag_id " );
        pagosSql.append( "where  pag.pag_activo='1' and compag.compag_activo='1' and compag.compag_cliente_tipo='014003' " );
        pagosSql.append( "and compag.compag_tipo='037001' and pag.alutar_id=alutar.alutar_id " );
        pagosSql.append( "and compag.compag_cliente=alutar.alu_id ) ,0.00)as pagado, " );
        pagosSql.append( "(alutar.alutar_monto - ifnull((select pag.pag_monto from ad_pago pag " );
        pagosSql.append( "inner join  ad_comprobante_pago compag  ON pag.compag_id=compag.compag_id " );
        pagosSql.append( "inner join cl_alumno_tarifa alut ON pag.alutar_id=alut.alutar_id " );
        pagosSql.append( "where pag.pag_activo='1' and compag.compag_Activo='1' and compag.compag_cliente_tipo='014003' and compag.compag_tipo='037001' " );
        pagosSql.append( "and alut.alutar_id=alutar.alutar_id and alut.sec_id=alutar.sec_id),0.00) ) as saldo," );
        pagosSql.append( "(alutar.alutar_monto - ifnull((select pag.pag_monto from ad_pago pag " );
        pagosSql.append( "inner join  ad_comprobante_pago compag  ON pag.compag_id=compag.compag_id " );
        pagosSql.append( "inner join cl_alumno_tarifa alut ON pag.alutar_id=alut.alutar_id  " );
        pagosSql.append( "where pag.pag_activo='1' and compag.compag_Activo='1' and compag.compag_cliente_tipo='014003' and compag.compag_tipo='037001'  " );
        pagosSql.append( "and alut.alutar_id=alutar.alutar_id and alut.sec_id=alutar.sec_id),0.00) " );
        pagosSql.append( ") + 20.00  as recargo " );
        pagosSql.append( "FROM cl_alumno_tarifa alutar " );
        pagosSql.append( "where  alutar.alutar_id=? and alutar.alu_id=?  and alutar.sec_id=? " );

        PreparedStatement pstNot = null;
        ResultSet rsNot = null;

        try {
            pstAlu = cn.prepareStatement( alumnoSql.toString() );
            pstAlu.setInt( 1, mod_id );
            rsAlu = pstAlu.executeQuery();

            while ( rsAlu.next() ) {
                AlumnoHistorial pojo = new AlumnoHistorial();
                pojo.setNumorden( 0 );
                pojo.setModulo( rsAlu.getString( 7 ).trim() );
                pojo.setCurso( rsAlu.getString( 8 ).trim() );
                pojo.setTaller( rsAlu.getString( 10 ).trim() );
                pojo.setIdAlumno( rsAlu.getInt( 6 ) );
                pojo.setAlumno( rsAlu.getString( 11 ) );

                pstNot = cn.prepareStatement( pagosSql.toString() );
                pstNot.setInt( 1, rsAlu.getInt( 1 ) );
                pstNot.setInt( 2, rsAlu.getInt( 6 ) );
                pstNot.setInt( 3, rsAlu.getInt( 3 ) );
                rsNot = pstNot.executeQuery();

                while ( rsNot.next() ) {
                    pojo.setMontopagar( rsNot.getDouble( 1 ) );
                    pojo.setMontopagado( rsNot.getDouble( 2 ) );
                    pojo.setMontosaldo( rsNot.getDouble( 3 ) );
                    pojo.setMontorecargo( rsNot.getDouble( 4 ) );
                }

                listAlu.add( pojo );

            }

        } catch ( SQLException e ) {
            e.printStackTrace();
        } finally {
            try {
                pstAlu.close();
                rsAlu.close();
                cn.close();
            } catch ( SQLException e ) {
                e.printStackTrace();
            }

        }

        return listAlu;

    }

    public static ArrayList<AlumnoHistorial> getHistorial( String aluid ) {

        ArrayList<AlumnoHistorial> listHistorial = new ArrayList<AlumnoHistorial>();
        StringBuilder historialSql = new StringBuilder( "select  alu.alu_codigo as codigo,mat.alu_id,concat_Ws(' ', alu.alu_appaterno,alu.alu_apmaterno,alu.alu_nombres) as alumno, " );
        historialSql.append( "DATE_FORMAT(sec.sec_finicio,'%d/%m/%y') ,DATE_FORMAT(sec.sec_ffin,'%d/%m/%y'),sec.sec_id, sec.sec_nombre, " );
        historialSql.append( "(select mod_descripcion from cl_modulo where  mod_id=(select mod_id from cl_curso where cur_id=(select cur_id from cl_taller where tal_id=talape.tal_id )) ) as modulo, " );
        historialSql.append( "(select cur_nombre from cl_curso where cur_id=(select cur_id from cl_taller where tal_id=talape.tal_id))  as Curso, " );
        historialSql.append( "talape.talape_descripcion as 'Taller aperturado', " );
        historialSql.append( "(CASE " );
        historialSql.append( "when mat.mat_tipo ='022001' then 'Regular' " );
        historialSql.append( "when mat.mat_tipo ='022002' then 'Reservado' " );
        historialSql.append( "when mat.mat_tipo ='022003' then 'Retirado' " );
        historialSql.append( "when mat.mat_tipo ='022007' then 'Matricula Anulada / Reservada' " );
        historialSql.append( "else 'otr. ' " );
        historialSql.append( "END) as 'Condicion del Curso',alu.alu_id as idalu " );
        historialSql.append( "from cl_matricula mat " );
        historialSql.append( "inner join cl_alumno alu ON mat.alu_id=alu.alu_id " );
        historialSql.append( "inner join cl_matricula_taller matta ON mat.mat_id=matta.mat_id " );
        historialSql.append( "inner join cl_seccion sec ON matta.sec_id=sec.sec_id " );
        historialSql.append( "inner join cl_taller_aperturado talape ON talape.talape_id=sec.talape_id " );
        historialSql.append( "where alu.alu_codigo=? and mat.mat_tipo in ('022001','022002','022003','022007') " );
        historialSql.append( "and sec.sec_activo='1' and mat.mat_activo='1' and talape.tal_id<>77 " );
        historialSql.append( "order by modulo,curso " );
        // 92 como id

        StringBuilder notasSql = new StringBuilder( "select siseva.siseva_per_nombre,nota.not_nota " );
        notasSql.append( "from cl_nota  nota  inner join cl_sis_eva_personalizado siseva ON nota.siseva_per_id=siseva.siseva_per_id " );
        notasSql.append( "where  siseva.siseva_per_activo='1'  and nota.sec_id=? and nota.alu_id=? and nota.not_activo='1' " );

        DriverManagerDataSource dmds = (DriverManagerDataSource) ServiceFinder.findBean( "dataSource" );

        Connection cn = null;

        try {
            cn = dmds.getConnection();
        } catch ( SQLException ex ) {
            Logger.getLogger( ControllerAlumnos.class.getName() ).log( Level.SEVERE, null, ex );
        }

        PreparedStatement pstAlu = null;
        ResultSet rsAlu = null;

        PreparedStatement pstNot = null;
        ResultSet rsNot = null;

        try {

            pstAlu = cn.prepareStatement( historialSql.toString() );
            pstAlu.setString( 1, aluid );
            rsAlu = pstAlu.executeQuery();

            while ( rsAlu.next() ) {
                // System.out.println(rsAlu.getInt(2)+" ---> "+aluid);
                AlumnoHistorial pojo = new AlumnoHistorial();
                pojo.setIdAlumno( rsAlu.getInt( 2 ) );
                pojo.setAlucodigo( rsAlu.getString( 1 ) );
                pojo.setAlumno( rsAlu.getString( 3 ).trim() );
                pojo.setModulo( rsAlu.getString( 8 ).trim() );
                pojo.setCurso( rsAlu.getString( 9 ).trim() );
                pojo.setTaller( rsAlu.getString( 10 ).trim() );
                pojo.setFinicio( rsAlu.getString( 4 ) );
                pojo.setFfin( rsAlu.getString( 5 ) );
                pojo.setCondicion( rsAlu.getString( 11 ) );

                pstNot = cn.prepareStatement( notasSql.toString() );
                pstNot.setInt( 1, rsAlu.getInt( 6 ) );
                pstNot.setInt( 2, rsAlu.getInt( "idalu" ) );
                rsNot = pstNot.executeQuery();

                ArrayList<Nota> listNota = new ArrayList<Nota>();
                int i = 0;

                while ( rsNot.next() ) {
                    //  System.out.println(" no entra  ps...");
                    Nota nota = new Nota();
                    nota.setDecripcion( rsNot.getString( 1 ) );
                    nota.setNota( rsNot.getDouble( 2 ) );
                    listNota.add( nota );
                    i++;
                }

                pojo.setListNota( listNota );
                listHistorial.add( pojo );

            }

        } catch ( SQLException e ) {
            e.printStackTrace();
        } finally {
            try {
                pstAlu.close();
                rsAlu.close();
                cn.close();
            } catch ( SQLException e ) {
                e.printStackTrace();
            }

        }

        return listHistorial;

    }

    public static AlumnoHistorial getHeaderHorarioCurso( int aluid ) {

        StringBuilder historialSql = new StringBuilder( "select hor.hor_id,hor.sec_id, " );
        historialSql.append( "concat((CASE " );
        historialSql.append( "when hor.hor_dia='016001' then 'Lunes' " );
        historialSql.append( "when hor.hor_dia='016002' then 'Martes' " );
        historialSql.append( "when hor.hor_dia='016003' then 'Miercoles' " );
        historialSql.append( "when hor.hor_dia='016004' then 'Jueves' " );
        historialSql.append( "when hor.hor_dia='016005' then 'Viernes' " );
        historialSql.append( "when hor.hor_dia='016006' then 'Sabado' " );
        historialSql.append( "when hor.hor_dia='016007' then 'Domingo' " );
        historialSql.append( "else 'Dia de la Semana' " );
        historialSql.append( "END),' [ ' ,concat_ws(' - ',date_format(hor.hor_hini,'%H:%i %p'), date_format(hor.hor_hfin,'%H:%i %p')),' ]') as 'Horario', " );
        historialSql.append( "(CASE " );
        historialSql.append( "when hor.hor_tipo_clase='018001' then 'Teoria' " );
        historialSql.append( "when hor.hor_tipo_clase='018002' then 'Practica' " );
        historialSql.append( "when hor.hor_tipo_clase='018003' then 'Laboratorio' " );
        historialSql.append( "END) as 'Tipo Clase', " );
        historialSql.append( "ifnull((select aul_des from ac_aula where aul_id=hor.aul_id) ,' --- ')as 'Aula / Laboratorio', " );
        historialSql.append( "(select doc_nombre from ac_docente where doc_id=hor.doc_id ) as 'Docente' " );
        historialSql.append( "from cl_horaria hor inner join cl_seccion sec ON hor.sec_id=sec.sec_id " );
        historialSql.append( "where hor.hor_activo='1'   and hor.sec_id=? " );
        historialSql.append( "order by hor.sec_id " );

        StringBuilder notasSql = new StringBuilder( "  select count(*) , " );
        notasSql.append( "(select mod_descripcion from cl_modulo where mod_id= " );
        notasSql.append( "(select mod_id from cl_curso where cur_id= " );
        notasSql.append( "(select cur_id from cl_taller where tal_id=talape.tal_id))) as Modulo, " );
        notasSql.append( "(select cur_nombre from cl_curso where cur_id=(select cur_id from cl_taller where tal_id=talape.tal_id)) as 'Curso', " );
        notasSql.append( "talape.talape_descripcion as 'taller' " );
        notasSql.append( "from cl_seccion sec  " );
        notasSql.append( "inner join cl_matricula_taller matta ON matta.sec_id=sec.sec_id  " );
        notasSql.append( "inner join cl_matricula mat ON mat.mat_id=matta.mat_id " );
        notasSql.append( "inner join cl_taller_aperturado talape  ON talape.talape_id=sec.talape_id " );
        notasSql.append( "where sec.sec_activo='1'  and sec.sec_id=? and matta.mattal_activo='1' and mat.mat_activo='1' and  mat.mat_tipo='022001' " );

        DriverManagerDataSource dmds = (DriverManagerDataSource) ServiceFinder.findBean( "dataSource" );

        Connection cn = null;

        try {
            cn = dmds.getConnection();
        } catch ( SQLException ex ) {
            Logger.getLogger( ControllerAlumnos.class.getName() ).log( Level.SEVERE, null, ex );
        }

        PreparedStatement pstAlu = null;
        ResultSet rsAlu = null;

        PreparedStatement pstNot = null;
        ResultSet rsNot = null;
        String horario = "";
        AlumnoHistorial pojo = new AlumnoHistorial();

        try {
            pstAlu = cn.prepareStatement( historialSql.toString() );
            pstAlu.setInt( 1, aluid );
            rsAlu = pstAlu.executeQuery();

            while ( rsAlu.next() ) {
                horario = horario + rsAlu.getString( 3 ) + " / ";
                pojo.setAula( rsAlu.getString( 5 ) );
                pojo.setDocente( rsAlu.getString( 6 ) );

            }

            pojo.setHorario( horario );

            pstNot = cn.prepareStatement( notasSql.toString() );
            pstNot.setInt( 1, aluid );
            rsNot = pstNot.executeQuery();

            if ( rsNot.next() ) {
                pojo.setCantMatriculado( rsNot.getInt( 1 ) );
                pojo.setModulo( rsNot.getString( 2 ) );
                pojo.setCurso( rsNot.getString( 3 ) );
                pojo.setTaller( rsNot.getString( 4 ) );
            }

        } catch ( SQLException e ) {
            e.printStackTrace();
        } finally {
            try {
                pstAlu.close();
                rsAlu.close();
                pstNot.close();
                rsNot.close();
                cn.close();
            } catch ( SQLException e ) {
                e.printStackTrace();
            }

        }

        return pojo;

    }

    public static ArrayList<Object> getNotaForRegyster( int secid ) {

        ArrayList<Object> data = new ArrayList<Object>();
        ArrayList<AlumnoHistorial> alumnos = new ArrayList<AlumnoHistorial>();
        DriverManagerDataSource dmds = (DriverManagerDataSource) ServiceFinder.findBean( "dataSource" );
        Connection cn = null;

        try {
            cn = dmds.getConnection();
        } catch ( SQLException ex ) {
            Logger.getLogger( ControllerAlumnos.class.getName() ).log( Level.SEVERE, null, ex );
        }

        StringBuilder historialSql = new StringBuilder( "select hor.hor_id,hor.sec_id, " );
        historialSql.append( "concat((CASE " );
        historialSql.append( "when hor.hor_dia='016001' then 'Lunes' " );
        historialSql.append( "when hor.hor_dia='016002' then 'Martes' " );
        historialSql.append( "when hor.hor_dia='016003' then 'Miercoles' " );
        historialSql.append( "when hor.hor_dia='016004' then 'Jueves' " );
        historialSql.append( "when hor.hor_dia='016005' then 'Viernes' " );
        historialSql.append( "when hor.hor_dia='016006' then 'Sabado' " );
        historialSql.append( "when hor.hor_dia='016007' then 'Domingo' " );
        historialSql.append( "else 'Dia de la Semana' " );
        historialSql.append( "END),' [ ' ,concat_ws(' - ',date_format(hor.hor_hini,'%H:%i %p'), date_format(hor.hor_hfin,'%H:%i %p')),' ]') as 'Horario', " );
        historialSql.append( "(CASE " );
        historialSql.append( "when hor.hor_tipo_clase='018001' then 'Teoria' " );
        historialSql.append( "when hor.hor_tipo_clase='018002' then 'Practica' " );
        historialSql.append( "when hor.hor_tipo_clase='018003' then 'Laboratorio' " );
        historialSql.append( "END) as 'Tipo Clase', " );
        historialSql.append( "ifnull((select aul_des from ac_aula where aul_id=hor.aul_id) ,' --- ')as 'Aula / Laboratorio', " );
        historialSql.append( "ifnull((select doc_nombre from ac_docente where doc_id=hor.doc_id ),' ---') as 'Docente' " );
        historialSql.append( "from cl_horaria hor inner join cl_seccion sec ON hor.sec_id=sec.sec_id " );
        historialSql.append( "where hor.hor_activo='1'   and hor.sec_id=? " );
        historialSql.append( "order by hor.sec_id " );

        StringBuilder notasSql = new StringBuilder( "  select count(*) , " );
        notasSql.append( "(select mod_descripcion from cl_modulo where mod_id= " );
        notasSql.append( "(select mod_id from cl_curso where cur_id= " );
        notasSql.append( "(select cur_id from cl_taller where tal_id=talape.tal_id))) as Modulo, " );
        notasSql.append( "(select cur_nombre from cl_curso where cur_id=(select cur_id from cl_taller where tal_id=talape.tal_id)) as 'Curso', " );
        notasSql.append( "talape.talape_descripcion as 'taller' " );
        notasSql.append( "from cl_seccion sec  " );
        notasSql.append( "inner join cl_matricula_taller matta ON matta.sec_id=sec.sec_id  " );
        notasSql.append( "inner join cl_matricula mat ON mat.mat_id=matta.mat_id " );
        notasSql.append( "inner join cl_taller_aperturado talape  ON talape.talape_id=sec.talape_id " );
        notasSql.append( "where sec.sec_activo='1'  and sec.sec_id=? and matta.mattal_activo='1' and mat.mat_activo='1' and  mat.mat_tipo='022001' " );

        PreparedStatement pstAlu = null;
        ResultSet rsheader = null;

        PreparedStatement pstNot = null;
        ResultSet rsModulo = null;

        String horario = "";
        AlumnoHistorial pojo = new AlumnoHistorial();

        try {
            pstAlu = cn.prepareStatement( historialSql.toString() );
            pstAlu.setInt( 1, secid );
            rsheader = pstAlu.executeQuery();

            while ( rsheader.next() ) {
                horario = horario + rsheader.getString( 3 ) + " / ";
                pojo.setAula( rsheader.getString( 5 ) );
                pojo.setDocente( rsheader.getString( 6 ) );
            }

            pojo.setHorario( horario );

            pstNot = cn.prepareStatement( notasSql.toString() );
            pstNot.setInt( 1, secid );
            rsModulo = pstNot.executeQuery();

            if ( rsModulo.next() ) {
                pojo.setCantMatriculado( rsModulo.getInt( 1 ) );
                pojo.setModulo( rsModulo.getString( 2 ).trim() );
                pojo.setCurso( rsModulo.getString( 3 ).trim() );
                pojo.setTaller( rsModulo.getString( 4 ).trim() );
            }

        } catch ( SQLException e ) {
            e.printStackTrace();
        } finally {
            try {
                if ( pstAlu != null ) {
                    pstAlu.close();
                }
                if ( rsheader != null ) {
                    rsheader.close();
                }
                if ( pstNot != null ) {
                    pstNot.close();
                }
                if ( rsModulo != null ) {
                    rsModulo.close();
                }
            } catch ( SQLException e ) {
                e.printStackTrace();
            }
        }

        data.add( pojo );

        StringBuilder alumnoSql = new StringBuilder( "select mat.alu_id as id, alu.alu_codigo as codigo, " );
        alumnoSql.append( "concat_Ws(' ',alu_appaterno,alu_apmaterno,alu_nombres) as alumno " );
        alumnoSql.append( "from cl_matricula_taller mattal " );
        alumnoSql.append( "inner join cl_matricula mat ON mattal.mat_id=mat.mat_id " );
        alumnoSql.append( "inner join cl_seccion sec ON sec.sec_id=mattal.sec_id " );
        alumnoSql.append( "inner join cl_alumno alu ON mat.alu_id=alu.alu_id " );
        alumnoSql.append( "where mattal.sec_id=? and mattal.mattal_activo='1' and sec.sec_activo='1' and mat.mat_activo='1' and mat.mat_tipo='022001' " );
        alumnoSql.append( "order by alumno" );

        StringBuilder notaSql = new StringBuilder( "Select mat.alu_id,siseva.siseva_codigo,nota.not_nota " );
        notaSql.append( "from cl_matricula_taller mattal " );
        notaSql.append( "inner join cl_matricula mat ON mattal.mat_id=mat.mat_id " );
        notaSql.append( "inner join cl_seccion sec ON sec.sec_id=mattal.sec_id " );
        notaSql.append( "inner join cl_nota nota ON nota.sec_id=sec.sec_id and nota.alu_id=mat.alu_id " );
        notaSql.append( "inner join cl_sis_eva_personalizado siseva ON nota.siseva_per_id=siseva.siseva_per_id " );
        notaSql.append( "where mattal.sec_id=? and nota.alu_id=? and mattal.mattal_activo='1' and sec.sec_activo='1' and mat.mat_activo='1' " );
        notaSql.append( "and mat.mat_tipo='022001' " );


        PreparedStatement pstAlu1 = null;
        ResultSet rsAlu1 = null;

        PreparedStatement pstNot1 = null;
        ResultSet rsNot1 = null;

        try {
            pstAlu1 = cn.prepareStatement( alumnoSql.toString() );
            pstAlu1.setInt( 1, secid );
            rsAlu1 = pstAlu1.executeQuery();

            for ( int i = 1; rsAlu1.next(); i++ ) {
                AlumnoHistorial pojo1 = new AlumnoHistorial();
                pojo1.setNumorden( i );
                pojo1.setIdAlumno( rsAlu1.getInt( "id" ) );
                pojo1.setAlucodigo( rsAlu1.getString( "codigo" ) );
                pojo1.setAlumno( rsAlu1.getString( "alumno" ) );

                pstNot1 = cn.prepareStatement( notaSql.toString() );
                pstNot1.setInt( 1, secid );
                pstNot1.setInt( 2, rsAlu1.getInt( "id" ) );
                rsNot1 = pstNot1.executeQuery();

                ArrayList<Nota> listNotas = new ArrayList<Nota>();

                int cantNotas = 0;
                while ( rsNot1.next() ) {
                    cantNotas++;
                    Nota objNota = new Nota();
                    objNota.setDecripcion( rsNot1.getString( 2 ) );
                    objNota.setNota( rsNot1.getInt( 3 ) );
                    listNotas.add( objNota );

                    if ( rsNot1.getString( 2 ).equals( "EF" ) ) {
                        pojo1.setEF( String.valueOf( rsNot1.getInt( 3 ) ) );
                    }
                    if ( rsNot1.getString( 2 ).equals( "EP" ) ) {
                        pojo1.setEP( String.valueOf( rsNot1.getInt( 3 ) ) );
                    }
                    if ( rsNot1.getString( 2 ).equals( "EP1" ) ) {
                        pojo1.setEP1( String.valueOf( rsNot1.getInt( 3 ) ) );
                    }
                    if ( rsNot1.getString( 2 ).equals( "EP2" ) ) {
                        pojo1.setEP2( String.valueOf( rsNot1.getInt( 3 ) ) );
                    }
                    if ( rsNot1.getString( 2 ).equals( "PP" ) ) {
                        pojo1.setPP( String.valueOf( rsNot1.getInt( 3 ) ) );
                    }

                    if ( rsNot1.getString( 2 ).equals( "PT" ) ) {
                        pojo1.setPT( String.valueOf( rsNot1.getInt( 3 ) ) );
                    }
                    if ( rsNot1.getString( 2 ).equals( "EX" ) ) {
                        pojo1.setEX( String.valueOf( rsNot1.getInt( 3 ) ) );
                    }

                    if ( rsNot1.getString( 2 ).equals( "N.N." ) ) {
                        pojo1.setNN( String.valueOf( rsNot1.getInt( 3 ) ) );
                    }

                }

                //SACAR PROMEDIO PS.
                double prom = 0;
                for ( Nota sumNota : listNotas ) {
                    prom = prom + sumNota.getNota();
                }

                if ( !listNotas.isEmpty() ) {
                    Nota objProm = new Nota();
                    objProm.setDecripcion( "PROM" );
                    objProm.setNota( prom / cantNotas );
                    pojo1.setPROM( String.valueOf( prom / cantNotas ) );
                    listNotas.add( objProm );
                }

                pojo1.setListNota( listNotas );
                alumnos.add( pojo1 );
                data.add( alumnos );

            }

        } catch ( SQLException e ) {
            e.printStackTrace();
        } finally {
            try {
                pstAlu1.close();
                rsAlu1.close();
                if ( pstNot1 != null ) {
                    pstNot1.close();
                }
                if ( rsNot1 != null ) {
                    rsNot1.close();
                }
                cn.close();
            } catch ( SQLException e ) {
                e.printStackTrace();
            }

        }

        return data;

    }

    public static ArrayList<AlumnoHistorial> getListAlutarSeccion( int sec_id ) {
        ArrayList<AlumnoHistorial> listAlu = new ArrayList<AlumnoHistorial>();
        HSMatriculaCLDAO daoMat = (HSMatriculaCLDAO) ServiceFinder.findBean( "SpringHibernateDaoCLMatricula" );
        HSAlumnoTarifaCLDAO dao = (HSAlumnoTarifaCLDAO) ServiceFinder.findBean( "SpringHibernateDaoCLAlumnoTarifa" );

        // HSPagoDAO daoPago = (HSPagoDAO) ServiceFinder.findBean("SpringHibernateDaoPago");
        List<ClMatricula> tm = daoMat.totalMatriculadosSeccion( sec_id, 0, 1 );
         
        int i = 0;
        for ( ClMatricula mat : tm ) {
            ClAlumno alu = mat.getClAlumno();
            AlumnoHistorial pojo = new AlumnoHistorial();
            pojo.setAlucodigo( alu.getAluCodigo().trim() );
            pojo.setAlumno( alu.getAluAppaterno().trim() + " " + alu.getAluApmaterno().trim() + " " + alu.getAluNombres().trim() );
            pojo.setNumorden( ++i );
            pojo.setIdAlumno( alu.getAluId() );
            
            pojo.setCantMatriculado( tm.size() );

            if ( !mat.getClMatriculaTallers().isEmpty() ) {
                Iterator<ClMatriculaTaller> itMatTal = mat.getClMatriculaTallers().iterator();
                while ( itMatTal.hasNext() ) {
                    ClMatriculaTaller mattal = itMatTal.next();
                    ClArbolAcademico objArb=mattal.getClSeccion().getClArbolAcademico();
                    pojo.setModulo(objArb.getArbDescripcion());
                    pojo.setCurso(objArb.getArbAcadPadre().getArbDescripcion());
                    pojo.setTaller(objArb.getArbAcadPadre().getArbAcadPadre().getArbDescripcion().trim());
                    pojo.setObs(mattal.getClSeccion().getSecNombre().trim());
                    //ClCurso objCurso=mattal.getClSeccion().getClTallerAperturado().getClTaller().getClCurso();
                    /*
                     * ClCurso
                     * objCurso=mattal.getClSeccion().getClTallerAperturado().getClCurso();
                     * pojo.setModulo(objCurso.getClModulo().getModDescripcion().trim());
                     * pojo.setCurso(objCurso.getCurNombre().trim());
                     * pojo.setTaller(mattal.getClSeccion().getClTallerAperturado().getTalapeDescripcion().trim()
                     * +" Sec. : "+mattal.getClSeccion().getSecNombre().trim());
                     */
                    Date fInicio = mattal.getClSeccion().getSecFinicio();
                    Date fFin = mattal.getClSeccion().getSecFfin();
                    if ( fInicio != null ) {
                        pojo.setFinicio( new SimpleDateFormat( "dd-MM-yyyy" ).format( fInicio ).trim() );
                    } else {
                        pojo.setFinicio( "- No especifica" );
                    }
                    if ( fFin != null ) {
                        pojo.setFfin( new SimpleDateFormat( "dd-MM-yyyy" ).format( fFin ).trim() );
                    } else {
                        pojo.setFfin( "" );
                    }
                    break; // salimos para k no siga recorriendo los duplicados ps...
                }
            }

            try {
                List<ClAlumnoTarifa> alutars = dao.seleccionarTarifasXMatriculaAlumno( mat.getMatId(), alu.getAluId() );
                ArrayList<Pago> listpago = new ArrayList<Pago>();
                for ( ClAlumnoTarifa alutar : alutars ) {
                    Pago pago = new Pago();
                    pago.setDecspago( alutar.getClEstructuraPagosDetalle().getEstpagdetNombre().trim() );
                    pago.setMonto( alutar.getAlutarMonto() );
                    String condicion = alutar.getAlutarEstado().equals( "2" ) ? "Pagado" : alutar.getAlutarEstado().equals( "1" ) ? "A cuenta" : "Debe";
                    pago.setPagocaja( condicion );
                    listpago.add( pago );
                }
                pojo.setListpago( listpago );
                listAlu.add( pojo );

            } catch ( Exception ex ) {
                Logger.getLogger( ControllerAlumnos.class.getName() ).log( Level.SEVERE, null, ex );
            }

        }

        return listAlu;
    }

    public static List<AlumnoHistorial> getListMontoPagadoSeccion( int iSecId ) {
        return CommonDAO.getClAlumnoTarifaDAO().listarMontosPagadosPorSecId( iSecId );
    }
//    public static ArrayList<AlumnoHistorial> getListMontoPagadoSeccion( int sec_id ) {
//
//        ArrayList<AlumnoHistorial> listAlu = new ArrayList<AlumnoHistorial>();
//        HSMatriculaCLDAO daoMat = (HSMatriculaCLDAO) ServiceFinder.findBean( "SpringHibernateDaoCLMatricula" );
//        HSAlumnoTarifaCLDAO dao = (HSAlumnoTarifaCLDAO) ServiceFinder.findBean( "SpringHibernateDaoCLAlumnoTarifa" );
//
//        List<ClMatricula> tm = daoMat.totalMatriculadosSeccion( sec_id, 0, 1 );
//        int i = 0;
//        for ( ClMatricula mat : tm ) {
//            ClAlumno alu = mat.getClAlumno();
//            AlumnoHistorial pojo = new AlumnoHistorial();
//            pojo.setAlucodigo( alu.getAluCodigo() );
//            pojo.setAlumno( alu.getAluAppaterno() + " " + alu.getAluApmaterno() + " " + alu.getAluNombres() );
//            pojo.setNumorden( ++i );
//            pojo.setIdAlumno( alu.getAluId() );
//            pojo.setCantMatriculado( tm.size() );
//
//            if ( !mat.getClMatriculaTallers().isEmpty() ) {
//                Iterator<ClMatriculaTaller> itMatTal = mat.getClMatriculaTallers().iterator();
//                while ( itMatTal.hasNext() ) {
//                    ClMatriculaTaller mattal = itMatTal.next();
//                    pojo.setModulo( "" );
//                    pojo.setCurso( mattal.getClSeccion().getClArbolAperturado().getArbapeDescripcion().trim() );
//                    pojo.setTaller( mattal.getClSeccion().getClArbolAperturado().getArbapeDescripcion().trim() + "   Sec. : " + mattal.getClSeccion().getSecNombre().trim() );
//                    Date fInicio = mattal.getClSeccion().getSecFinicio();
//                    Date fFin = mattal.getClSeccion().getSecFfin();
//                    if ( fInicio != null ) {
//                        pojo.setFinicio( new SimpleDateFormat( "dd-MM-yyyy" ).format( fInicio ).trim() );
//                    } else {
//                        pojo.setFinicio( "- No especifica" );
//                    }
//                    if ( fFin != null ) {
//                        pojo.setFfin( new SimpleDateFormat( "dd-MM-yyyy" ).format( fFin ).trim() );
//                    } else {
//                        pojo.setFfin( "" );
//                    }
//                    break; // salimos para k no siga recorriendo los duplicados ps...
//                }
//            }
//
//            try {
//                HSPagoDAO daoPago = (HSPagoDAO) ServiceFinder.findBean( "SpringHibernateDaoPago" );
//                List<ClAlumnoTarifa> alutars = dao.seleccionarTarifasXMatricula( mat.getMatId(), alu.getAluId() );
//                double abonado = 0.0;
//                double total = 0.0;
//                for ( ClAlumnoTarifa alutar : alutars ) {
//                    total = total + (alutar.getAlutarMonto() + alutar.getAlutarMora());
//                    List<AdPago> ltmp = daoPago.listaPagosAlumnoLibre( alutar.getAlutarId() );
//                    for ( AdPago pago : ltmp ) {
//                        abonado += pago.getPagMonto();
//                        System.out.println( "PAGOS : " + pago.getAlutarId() + " / PagMonto : " + pago.getPagMonto() + " / COMPAG_ID : " + pago.getCompag().getId() + " / PAG_ID : " + pago.getId() + " / ABONADO : " + abonado );
//                    }
//                }
//
//                pojo.setMontopagado( abonado );
//                pojo.setMontopagar( total );
//                pojo.setMontosaldo( total - abonado );
//
//                listAlu.add( pojo );
//
//            } catch ( Exception ex ) {
//                Logger.getLogger( ControllerAlumnos.class.getName() ).log( Level.SEVERE, null, ex );
//            }
//
//        }
//
//        return listAlu;
//    }
}
