/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.hibernateSpringDao;

import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.uch.cursoLibre.managedBeans.beans.BeanCLPublico;
import net.uch.cursoLibre.managedBeans.beans.BeanInfoMatricula;
import net.uch.cursoLibre.managedBeans.beans.BeanReporte;
import net.uch.mapping.ClConsultaPublico;
import net.uch.mapping.ClMedioPublicidadDet;
import net.uch.util.CommonWeb;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author Richard R B
 */
public class HSConsultaPublicoDAOImpl extends HibernateDaoSupport implements HSConsultaPublicoDAO {

    @Override
    public void agregarConsultaPublico( ClConsultaPublico consultaPublico ) {
        this.getHibernateTemplate().save( consultaPublico );
    }

    @Override
    public List<ClConsultaPublico> listarxAlumnoConsultaPublico( int alu_id ) {
        List<ClConsultaPublico> lista = this.getSession().createCriteria( ClConsultaPublico.class, "ClConsultaPublico" ).add( Restrictions.eq( "conActivo", "1" ) ).add( Restrictions.eq( "clAlumno.aluId", alu_id ) ).addOrder( Order.asc( "conFechaContacto" ) ).list();
        return lista;
    }

    @Override
    public void modificarCampoMatricula( int con_id ) {
        String hqlUpdate = "update ClConsultaPublico set conEstadoMatricula = :v_mat where conId = :v_consulta_id";
        this.getSession().createQuery( hqlUpdate ).setString( "v_mat", "2" ).setInteger( "v_consulta_id", con_id ).executeUpdate();
    }

    @Override
    public void delMatriculaConsulta( int mat_id ) {
        String hqlUpdate = "update ClConsultaPublico set conEstadoMatricula = :v_mat where matId = :v_mat_id";
        this.getSession().createQuery( hqlUpdate ).setString( "v_mat", "0" ).setInteger( "v_mat_id", mat_id ).executeUpdate();

    }

    @Override
    public void modificarCampoMatricula( int con_id, int mat_id ) {
        String hqlUpdate = "update ClConsultaPublico set conEstadoMatricula = :v_mat,matId = :v_mat_id where conId = :v_consulta_id";
        this.getSession().createQuery( hqlUpdate ).setString( "v_mat", "2" ).setInteger( "v_consulta_id", con_id ).setInteger( "v_mat_id", mat_id ).executeUpdate();
    }

    @Override
    public List<ClConsultaPublico> listarPublicoConsulta( int iCateg, String sCentroId, int iEspId, int are_id, int mod_id, int cur_id, int hor_id, int usu_id, Date fecha_ini, Date fecha_fin,
            String con_procedencia, String mat_tipo, String sPrioridadId, int tipo, int iMedioId, int iMedioDetId ) {

        //tipo
        /**
         * 0 para fecha_registro 1 para fecha_contactos
         *
         */
        Criteria criteria = this.getSession().createCriteria( ClConsultaPublico.class, "clConsultaPublico" );
        if ( iCateg == 1 ) {//Curso Libre
            criteria.createCriteria( "clConsultaPublico.clArbolAcademico", "clArbolAcademico" );
            criteria.add( Restrictions.eq( "clArbolAcademico.arbActivo", "1" ) );
        }
        criteria.add( Restrictions.eq( "clConsultaPublico.conActivo", "1" ) );
        //.add(Restrictions.eq("clArea.areActivo", "1"));

        if ( tipo == 0 ) {
            criteria.add( Restrictions.between( "clConsultaPublico.conFechaRegistro", fecha_ini, fecha_fin ) );
        } else {
            criteria.add( Restrictions.between( "clConsultaPublico.conFechaContacto", fecha_ini, fecha_fin ) );
        }

        if ( iCateg == 1 ) {//Curso Libre
            if ( are_id > 0 ) {
                System.out.println( "el area_id -> " + are_id );
                criteria.add( Restrictions.eq( "clConsultaPublico.areaId", are_id ) );
            }
            if ( mod_id > 0 ) {
                criteria.add( Restrictions.eq( "clArbolAcademico.arbId", mod_id ) );
            }
            if ( cur_id > 0 ) {

                criteria.add( Restrictions.eq( "clConsultaPublico.curId", cur_id ) );
            }
            if ( cur_id == -1 ) {
                criteria.add( Restrictions.isNull( "clConsultaPublico.curId" ) );
            }
        } else if ( iCateg == 2 ) {//Carrera Profesional
            criteria.add( Restrictions.isNull( "clConsultaPublico.clArbolAcademico" ) );
            if ( iEspId > 0 ) {
                criteria.add( Restrictions.eq( "clConsultaPublico.espId", iEspId ) );
            }
        }
        if ( hor_id > 0 ) {
            criteria.add( Restrictions.eq( "clConsultaPublico.plaId", hor_id ) );
        }
        if ( usu_id > 0 ) {
            criteria.add( Restrictions.eq( "clConsultaPublico.usuId", usu_id ) );
        }
        if ( !mat_tipo.equals( "1" ) ) {
            criteria.add( Restrictions.eq( "clConsultaPublico.conEstadoMatricula", mat_tipo ) );
        }
        if ( !con_procedencia.equals( "000000" ) ) {
            criteria.add( Restrictions.eq( "clConsultaPublico.conProcedencia", con_procedencia ) );
        }
        if ( !sPrioridadId.equals( "000000" ) ) {
            criteria.add( Restrictions.eq( "clConsultaPublico.sConPrioridad", sPrioridadId ) );
        }
        if ( iMedioId > 0 ) {
            criteria.add( Restrictions.eq( "clConsultaPublico.medId", iMedioId ) );
            if ( iMedioDetId > 0 ) {
                criteria.add( Restrictions.eq( "clConsultaPublico.detmedId", iMedioDetId ) );
            }
        }

        List<ClConsultaPublico> lista = criteria.list();
        return lista;
    }

    @Override
    public List<BeanReporte> listarReporteFechaContacto( int iCateg, String sCentroId, int iEspId, int iAreId, int iModId, int iCurId, int iHorId, int iUsuId, Date fechaIni,
            Date fechaFin, String sConProcedencia, String sMatTipo, String sPrioridadId, int tipo, int iMedioId, int iMedioDetId ) {
        int iCont;
        BeanReporte bRep;
        ByteBuffer buffer;
        Date fechaContacto;
        SimpleDateFormat sdf;
        String sSqlQuery;
        String sSqlSubQuery;
        SQLQuery query;
        SQLQuery subQuery;
        Object[] objFila;
        Object[] objFila2;
        List lstFechaCont;
        List lstHorario;
        List<BeanReporte> lstRepFechaCont;
        sdf = new java.text.SimpleDateFormat( "dd/MM/yyyy" );

        if ( iCateg == 1 ) {
            sSqlQuery = "SELECT CONCAT_WS(' ',al.alu_appaterno,al.alu_apmaterno,al.alu_nombres) as alumno, "//0
                    + "(SELECT IFNULL(arb_descripcion,'') FROM cl_arbol_academico where arb_id=cp.area_id) as area, "//1
                    + "(SELECT IFNULL(arb_descripcion,'') FROM cl_arbol_academico WHERE arb_id=cp.mod_id) as modulo, "//2
                    + "(SELECT IFNULL(arb_descripcion,'') FROM cl_arbol_academico where arb_id=cp.cur_id) as curso, "//3
                    + "CASE cp.pla_id WHEN '1' THEN 'M' WHEN '2' THEN 'T' ELSE 'N' END horario, "//4
                    + "CASE cp.con_procedencia WHEN '064001' THEN 'P' ELSE 'T' END procedencia, "//5
                    + "CASE cp.con_prioridad WHEN '079001' THEN 'Alta' WHEN '079002' THEN 'Media' ELSE 'Baja' END prioridad, "//6
                    + "IFNULL(al.alu_telefono,'') as telefono, "//7
                    + "IFNULL(al.alu_celular,'') as celular, "//8
                    + "cp.con_fecha_contacto, "//9
                    + "CASE cp.con_estado_matricula WHEN 0 THEN 'NO' ELSE 'SI' END matriculado, "//10
                    + "(SELECT IFNULL(mp.descripcion,'NO DETERMINADO') FROM cl_medio_publicidad mp WHERE mp.id_publicidad=cp.med_id) medio, "//11
                    + "IFNULL((SELECT IFNULL(mpd.pub_det_des,'NO DETERMINADO') FROM cl_medio_publicidad_det mpd WHERE mpd.id_pub_det=cp.detmed_id),'') medio_detalle, "//12
                    + " IFNULL(al.alu_mail,''), "//13
                    + "(SELECT IFNULL(dis_des,'NO DETERMINADO') as distrito from tb_distrito where al.alu_distrito=dis_cod) as distrito, " //14          
                    + " IFNULL((SELECT IFNULL(usu_usuario,'NO DETERMINADO') as usuario FROM tb_usuario where usu_id=cp.usu_id),'')," // 15
                    + " cast(IFNULL(cp.mat_id,'NM') as char(60)) as m_id " //16
                    + "FROM cl_consulta_publico cp "
                    + "INNER JOIN cl_alumno al ON(cp.alu_id=al.alu_id) ";
            sSqlQuery += ( !"0".equals( sCentroId ) ? " INNER JOIN cl_arbol_academico aArea ON(aArea.arb_id=cp.area_id)  " : "" );
            sSqlQuery += "WHERE "
                    + "cp.con_fecha_contacto >= :fecha_ini AND cp.con_fecha_contacto<=:fecha_fin";
            sSqlQuery += ( !"0".equals( sCentroId ) ? " AND aArea.arb_institucion='" + sCentroId + "' " : "" );
            sSqlQuery += ( iAreId > 0 ? " AND cp.area_id=:area_id " : "" );
            sSqlQuery += ( iModId > 0 ? " AND cp.mod_id=:mod_id " : "" );
            sSqlQuery += ( iCurId > 0 ? " AND cp.cur_id=:cur_id " : "" );
        } else {
            sSqlQuery = "SELECT CONCAT_WS(' ',al.alu_appaterno,al.alu_apmaterno,al.alu_nombres) as alumno, "//0
                    + "esp.esp_nombre, "//1
                    + "'modulo', "//2
                    + "'curso', "//3
                    + "CASE cp.pla_id WHEN '1' THEN 'M' WHEN '2' THEN 'T' ELSE 'N' END horario, "//4
                    + "CASE cp.con_procedencia WHEN '064001' THEN 'P' ELSE 'T' END procedencia, "//5
                    + "CASE cp.con_prioridad WHEN '079001' THEN 'Alta' WHEN '079002' THEN 'Media' ELSE 'Baja' END prioridad, "//6
                    + "IFNULL(al.alu_telefono,'') as telefono, "//7
                    + "IFNULL(al.alu_celular,'') as celular, "//8
                    + "cp.con_fecha_contacto, "//9
                    + "CASE cp.con_estado_matricula WHEN 0 THEN 'NO' ELSE 'SI' END matriculado, "//10
                    + "(SELECT IFNULL(mp.descripcion,'NO DETERMINADO') FROM cl_medio_publicidad mp WHERE mp.id_publicidad=cp.med_id) medio, "//11
                    + "(SELECT IFNULL(mpd.pub_det_des,'NO DETERMINADO') FROM cl_medio_publicidad_det mpd WHERE mpd.id_pub_det=cp.detmed_id) medio_detalle, "//12
                    + " IFNULL(al.alu_mail,'') as email, "//13
                    + "(SELECT IFNULL(dis_des,'NO DETERMINADO') as distrito from tb_distrito where al.alu_distrito=dis_cod) as distrito, " //14          
                    + "(SELECT IFNULL(usu_usuario,'NO DETERMINADO') as usuario FROM tb_usuario where usu_id=cp.usu_id), " // 15
                    + " cast(IFNULL(cp.mat_id,'NM') as char(60)) as m_id " //16
                    + "FROM cl_consulta_publico cp "
                    + "INNER JOIN cl_alumno as al ON(cp.alu_id=al.alu_id) "
                    + "INNER JOIN ac_especialidad as esp ON(cp.esp_id=esp.esp_id) "
                    + "WHERE "
                    + "cp.con_fecha_contacto >= :fecha_ini AND cp.con_fecha_contacto<=:fecha_fin";
            sSqlQuery += ( iEspId > 0 ? " AND cp.esp_id=:esp_id " : "" );
        }

        sSqlQuery += ( iHorId > 0 ? " AND cp.pla_id=:hor_id " : "" );
        sSqlQuery += ( iUsuId > 0 ? " AND cp.usu_id=:usu_id " : "" );
        sSqlQuery += ( iMedioId > 0 ? " AND cp.med_id=:med_id " : "" );
        sSqlQuery += ( iMedioDetId > 0 ? " AND cp.detmed_id=:detmed_id " : "" );
        sSqlQuery += ( !"1".equals( sMatTipo ) ? " AND cp.con_estado_matricula=:est_mat " : "" );
        sSqlQuery += ( !"000000".equals( sConProcedencia ) ? " AND cp.con_procedencia=:proc_id " : "" );
        sSqlQuery += ( !"000000".equals( sPrioridadId ) ? " AND cp.con_prioridad=:pri_id " : "" );
        if ( iCateg == 1 ) {
            sSqlQuery += " ORDER BY area, modulo, curso, cp.con_fecha_contacto";
        } else {
            sSqlQuery += " ORDER BY esp_nombre, cp.con_fecha_contacto";
        }
        query = this.getSession().createSQLQuery( sSqlQuery );
        query.setDate( "fecha_ini", fechaIni ).setDate( "fecha_fin", fechaFin );

        if ( iCateg == 1 ) {
            if ( iAreId > 0 ) {
                query.setInteger( "area_id", iAreId );
            }
            if ( iModId > 0 ) {
                query.setInteger( "mod_id", iModId );
            }
            if ( iCurId > 0 ) {
                query.setInteger( "cur_id", iCurId );
            }
        } else {
            if ( iEspId > 0 ) {
                query.setInteger( "esp_id", iEspId );
            }
        }
        if ( iHorId > 0 ) {
            query.setInteger( "hor_id", iHorId );
        }
        if ( iUsuId > 0 ) {
            query.setInteger( "usu_id", iUsuId );
        }
        if ( iMedioId > 0 ) {
            query.setInteger( "med_id", iMedioId );
        }
        if ( iMedioDetId > 0 ) {
            query.setInteger( "detmed_id", iMedioDetId );
        }
        if ( !"1".equals( sMatTipo ) ) {
            query.setString( "est_mat", sMatTipo );
        }
        if ( !"000000".equals( sConProcedencia ) ) {
            query.setString( "proc_id", sConProcedencia );
        }
        if ( !"000000".equals( sPrioridadId ) ) {
            query.setString( "pri_id", sPrioridadId );
        }
        lstFechaCont = query.list();
        lstRepFechaCont = new ArrayList<BeanReporte>();
        iCont = 0;
        for ( Object object : lstFechaCont ) {
            objFila = (Object[])object;
            try {
                fechaContacto = (Date)objFila[9];
//                if ( objFila[9] != null ) {
//                    buffer = ByteBuffer.wrap( (byte[]) objFila[9] );
//                    fechaContacto = new Date( buffer.getLong() );
//                } else {
//                    fechaContacto = null;
//                }
            } catch ( Exception ex ) {
                fechaContacto = null;
                System.out.println( "*EX : " + ex.getMessage() );
            }


            bRep = new BeanReporte();
            bRep.setExpr1( ( ++iCont ) + "" );//Contador
            bRep.setExpr2( objFila[0] + "" );//Nombre alumno
            bRep.setExpr3( objFila[1] + "" );//Nombre Area o Nombre Especialidad
            bRep.setExpr4( objFila[2] + "" );//Nombre Modulo o ''
            bRep.setExpr5( objFila[3] + "" );//Nombre Curso o ''
            bRep.setExpr6( objFila[4] + "" );//Horario
            bRep.setExpr7( objFila[5] + "" );//Procedencia
            bRep.setExpr8( objFila[6] + "" );//Prioridad
            bRep.setExpr9( objFila[7] + "" );//Telefono
            bRep.setExpr10( objFila[8] + "" );//Celular

            try {
                bRep.setExpr11( fechaContacto == null ? "NO DETERMINADO" : sdf.format( fechaContacto ) );//F. Contacto
            } catch ( Exception ex ) {
                System.out.println( "[HSConsultaPublicoDAOImpl:216] ERROR (" + iCont + ") : " + ex.getMessage() );
                bRep.setExpr11( "NO DETERMINADO" );
            }
            bRep.setExpr12( objFila[10] + "" );//Matriculado
            bRep.setExpr13( objFila[11] + "" );//Medio
            bRep.setExpr14( objFila[12] + "" );//Medio Detalle
            bRep.setExpr15( objFila[13] + "" ); //EMAIL
            bRep.setExpr18( objFila[14] + "" );//Distrito
            bRep.setExpr19( objFila[15] + "" ); //Usuario
            if ( iCateg == 1 ) {
                if ( !objFila[16].equals( "NM" ) ) {

                    String MatId = objFila[16] + "";
                    sSqlSubQuery = "select \n"
                            + " if(concat(t.truco,t.truco1)='UCH',concat(letra_capital(t.nueva_cadena),' de ', CAST(t.tiempo AS CHAR(80)),' y ',letra_capital(t.nueva_cadena2),' de ',CAST(t.tiempo2 AS CHAR(80))),\n"
                            + " if(t.nueva_cadena<>'',concat(lower(t.nueva_cadena),' de ',CAST(t.tiempo AS CHAR(80))),concat(lower(t.nueva_cadena2),' de ',CAST(t.tiempo2 AS CHAR(80))))) tiempo,CONCAT('SI',' ',fecha_registro) f_registro \n"
                            + " from ( "
                            + "select \n"
                            + " s.sec_nombre,\n"
                            + " s.sec_finicio as fecha_inicio,\n"
                            + " s.sec_ffin as fecha_fin,\n"
                            + " (select \n"
                            + " cast((replace(GROUP_CONCAT((select SUBSTRING(tb_catalogo.cat_descripcion_elemento,1,1) from tb_catalogo where CONCAT(tb_catalogo.cat_codigo_grupo,tb_catalogo.cat_codigo_elemento)=cl_horaria.hor_dia)),',','-'))as CHAR(60)) \n"
                            + " from cl_horaria where cl_horaria.hor_activo=1 and cl_horaria.sec_id=s.sec_id) as frecuencia,\n"
                            + " CONCAT(DATE_FORMAT(tabla.hora_inicio,'%h:%i'),if(hour(tabla.hora_inicio)<12,' a.m. ',' p.m. '),' a ',\n"
                            + " DATE_FORMAT(tabla.hora_fin,'%h:%i'),if(hour(tabla.hora_fin)<12,' a.m.',' p.m.')) as tiempo,\n"
                            + " cast(concat(SUBSTRING_INDEX(tabla.cadena,',', if(tabla.cantidad<5,(tabla.cantidad-1),1)),if(tabla.cantidad>=2,if(tabla.cantidad<5,' y',' a '),' '),SUBSTRING_INDEX(tabla.cadena,',', (-1))) as CHAR(60)) as nueva_cadena, \n"
                            + " if(tabla.cantidad>0,'UC',' ') as truco, \n"
                            + " cast(tabla2.cadena1 as char(60)) as cadena1,"
                            + " CONCAT(DATE_FORMAT(tabla2.hora_inicio,'%h:%i'),if(hour(tabla2.hora_inicio)<12,' a.m. ','p.m.'),' a ',\n"
                            + " DATE_FORMAT(tabla2.hora_fin,'%h:%i'),if(hour(tabla2.hora_fin)<12,' a.m.',' p.m.')) as tiempo2,\n"
                            + " cast(concat(SUBSTRING_INDEX(tabla2.cadena1,',', if(tabla2.cantidad<5,(tabla2.cantidad-1),1)),if(tabla2.cantidad>=2,if(tabla2.cantidad<5,' y',' a '),' '),SUBSTRING_INDEX(tabla2.cadena1,',', (-1))) as CHAR(60)) as nueva_cadena2, \n"
                            + " if(tabla2.cantidad>0,'H',' ') as truco1,\n"
                            + " DATE_FORMAT(m.mat_fecha,'%Y/%m/%d') as fecha_registro \n"
                            + " from \n"
                            + " (select REPLACE(GROUP_CONCAT(( \n"
                            + " select SUBSTR(tb_catalogo.cat_descripcion_elemento,1,2) \n"
                            + " from tb_catalogo \n"
                            + " where concat(tb_catalogo.cat_codigo_grupo,tb_catalogo.cat_codigo_elemento)= hor_dia and hor_dia not in('016006','016007')\n"
                            + " )),',',', ') as cadena,"
                            + " (min(hor_hini)) as hora_inicio,"
                            + " (max(hor_hfin)) as hora_fin, "
                            + " count(DISTINCT(hor_dia)) as cantidad \n"
                            + " from cl_horaria "
                            + " where sec_id=(select sec_id from cl_alumno_tarifa where mat_id=:mId and cl_alumno_tarifa.alutar_activo=1 and cl_alumno_tarifa.alutar_estado=2 limit 1) and hor_dia not in('016006','016007') \n"
                            + " ) as tabla,\n"
                            + " ( \n"
                            + " select REPLACE(GROUP_CONCAT(( \n"
                            + " select SUBSTR(tb_catalogo.cat_descripcion_elemento,1,2)\n"
                            + " from tb_catalogo \n"
                            + " where concat(tb_catalogo.cat_codigo_grupo,tb_catalogo.cat_codigo_elemento)= hor_dia and hor_dia in('016006','016007')\n"
                            + " )),',',', ') as cadena1,"
                            + " (min(hor_hini)) as hora_inicio, "
                            + " (max(hor_hfin)) as hora_fin,\n"
                            + " count(DISTINCT(hor_dia)) as cantidad\n"
                            + " from cl_horaria \n"
                            + " where sec_id=(select sec_id from cl_alumno_tarifa where mat_id=:mId and cl_alumno_tarifa.alutar_activo=1 and cl_alumno_tarifa.alutar_estado=2 limit 1) and hor_dia in('016006','016007')\n"
                            + " )as tabla2,\n"
                            + " cl_matricula as m \n"
                            + " INNER JOIN cl_matricula_taller as mt ON(mt.mat_id=m.mat_id) \n"
                            + " INNER JOIN cl_seccion as s ON(s.sec_id=mt.sec_id)\n"
                            + " where m.mat_activo=1 \n"
                            + " and m.mat_tipo='022001' \n"
                            + " and s.sec_activo=1 \n"
                            + " and m.mat_id=:mId) as t";

                    subQuery = this.getSession().createSQLQuery( sSqlSubQuery );
                    subQuery.setString( "mId", MatId );
//                    System.out.println( MatId);

                    int valor = subQuery.list().size();
                    if ( valor > 0 ) {
                        //System.out.println("consulta ok");
                        lstHorario = subQuery.list();
                        for ( Object object2 : lstHorario ) {
                            objFila2 = (Object[])object2;
                            bRep.setExpr16( objFila2[0] + "" );
                            bRep.setExpr17( objFila2[1] + "" );
                        }
                    }
                }
            }
            lstRepFechaCont.add( bRep );
        }

        return lstRepFechaCont;
    }

    @Override
    public void modificarConsultaPublico( int con_id, String con_observacion, Date con_fecha_contacto ) {
        String hqlUpdate = "update ClConsultaPublico set conFechaContacto = :v_fecha, conObservacionRegistro= :v_observacion where conId = :v_conId";
        this.getSession().createQuery( hqlUpdate ).setDate( "v_fecha", con_fecha_contacto ).setString( "v_observacion", con_observacion ).setInteger( "v_conId", con_id ).executeUpdate();
    }

    @Override
//    public List<BeanInfoMatricula> cantidadMatriculadosUsuario( Date fechaIni, Date fechaFin, String sCentroId, int iAreaId, int iModId, int iCurId ) {
//        int iSize;
//        BeanInfoMatricula infoMat;
//        String sQuery;
//        SQLQuery sqlQuery;
//        Object[] objFila;
//        List lstAux;
//        List<BeanInfoMatricula> lstCantMat;
//        lstCantMat = new ArrayList<BeanInfoMatricula>();
//        try {
//            sQuery = "SELECT t.usu_id,SUM(t.prematriculado)prematriculado,SUM(t.matriculado)matriculado FROM "
//                    + "(SELECT cp.*,"
//                    + "IF(cp.con_estado_matricula=0,1,0) prematriculado,"
//                    + "IF(cp.con_estado_matricula=2,1,0) matriculado "
//                    + "FROM cl_consulta_publico cp ";
//
//            sQuery += "WHERE cp.con_fecha_registro BETWEEN :fecha_ini AND :fecha_fin AND ISNULL(cp.esp_id) AND cp.con_activo=1 ";
//            sQuery += ( iAreaId > 0 ? "AND area_id=:area_id " : " " );
//            sQuery += ( iModId > 0 ? "AND mod_id=:mod_id " : " " );
//            sQuery += ( iCurId > 0 ? "AND cur_id=:cur_id " : " " );
//            sQuery += ( !"0".equals( sCentroId ) ? "AND cp.area_id IN(SELECT arb_id FROM cl_arbol_academico WHERE arb_institucion=:centro_id AND arb_nivel=1 AND arb_activo=1) " : " " );
//            sQuery += ")t GROUP BY t.usu_id";
//            sqlQuery = this.getSession().createSQLQuery( sQuery );
//
//            sqlQuery.setDate( "fecha_ini", fechaIni );
//            sqlQuery.setDate( "fecha_fin", fechaFin );
//            if ( iAreaId > 0 ) {
//                sqlQuery.setInteger( "area_id", iAreaId );
//            }
//            if ( iModId > 0 ) {
//                sqlQuery.setInteger( "mod_id", iModId );
//            }
//            if ( iCurId > 0 ) {
//                sqlQuery.setInteger( "cur_id", iCurId );
//            }
//            if ( !"0".equals( sCentroId ) ) {
//                sqlQuery.setString( "centro_id", sCentroId );
//            }
//            System.out.println( "QUERY : " + sQuery );
//            lstAux = sqlQuery.list();
//            iSize = lstAux.size();
//            for ( int i = 0; i < iSize; i++ ) {
//                objFila = (Object[])lstAux.get( i );
//                infoMat = new BeanInfoMatricula();
//                infoMat.setInf_contador( i + 1 );
//                infoMat.setUsu_id( CommonWeb.parseObjToInt( objFila[0] ) );
//                infoMat.setInf_informes( CommonWeb.parseObjToInt( objFila[1] ) );
//                infoMat.setInf_matriculados( CommonWeb.parseObjToInt( objFila[2] ) );
//                infoMat.setInf_totalAlumno( infoMat.getInf_matriculados() + infoMat.getInf_informes() );
//                lstCantMat.add( infoMat );
//            }
//        } catch ( Exception ex ) {
//            ex.printStackTrace();
//        }
//
//        return lstCantMat;
//    }
    public List<BeanInfoMatricula> cantidadMatriculadosUsuario( Date fechaIni, Date fechaFin, String sCentroId, int iAreaId, int iModId, int iCurId ) {
        int iSize;
        BeanInfoMatricula infoMat;
        String sQuery;
        SQLQuery sqlQuery;
        Object[] objFila;
        List lstAux;
        List<BeanInfoMatricula> lstCantMat;
        lstCantMat = new ArrayList<BeanInfoMatricula>();
        try {

            sQuery = "select usu_id, sum(prematriculado) as prematriculado , sum(matriculado) matriculado from (\n"
                    + "select * \n"
                    + "FROM ( \n"
                    + "SELECT t.usu_id,SUM(t.prematriculado)prematriculado,SUM(t.matriculado)matriculado \n"
                    + "FROM ( \n"
                    + "select usu_id, IF(aTar.alutar_estado=0,1,0) prematriculado,IF(aTar.alutar_estado=2,1,0) matriculado \n"
                    + "from cl_alumno_tarifa aTar \n"
                    + "INNER JOIN cl_seccion s ON(aTar.sec_id=s.sec_id) \n"
                    + "INNER JOIN cl_arbol_academico aTal ON(s.arb_id=aTal.arb_id) \n"
                    + "INNER JOIN cl_arbol_academico aCur ON(aTal.arb_id_padre=aCur.arb_id) \n"
                    + "INNER JOIN cl_arbol_academico aMod ON(aCur.arb_id_padre=aMod.arb_id) \n"
                    + "INNER JOIN cl_arbol_academico aArea ON(aMod.arb_id_padre=aArea.arb_id) \n"
                    + "where aTar.alutar_fecha_pago BETWEEN :fecha_ini AND :fecha_fin \n"
                    + "and alutar_activo=1 and usu_id in (select usu_id from tb_usuario where rol_id=38 and usu_activo=1) \n";

            sQuery += ( iAreaId > 0 ? "AND aArea.arb_id=:area_id " : " " );
            sQuery += ( iModId > 0 ? "AND aMod.arb_id=:mod_id " : " " );
            sQuery += ( iCurId > 0 ? "AND aCur.arb_id=:cur_id " : " " );
            sQuery += ( !"0".equals( sCentroId ) ? "AND aArea.arb_id IN(SELECT arb_id FROM cl_arbol_academico WHERE arb_institucion=:centro_id AND arb_nivel=1 AND arb_activo=1) " : " " );

            sQuery += " )t GROUP BY t.usu_id) matPre \n"
                    + "UNION \n"
                    + "select * \n"
                    + "FROM ( \n"
                    + "SELECT t1.usu_id,SUM(t1.informes)informes, 0 matriculados \n"
                    + "FROM ( \n"
                    + "SELECT usu_id,IF(cp.con_estado_matricula=0,1,0) informes \n"
                    + "FROM cl_consulta_publico cp \n"
                    + "WHERE cp.con_fecha_registro BETWEEN :fecha_ini AND :fecha_fin \n"
                    + "AND ISNULL(cp.esp_id) AND cp.con_activo=1 and cp.con_estado_matricula<>2 \n";
            sQuery += ( iAreaId > 0 ? "AND area_id=:area_id " : " " );
            sQuery += ( iModId > 0 ? "AND mod_id=:mod_id " : " " );
            sQuery += ( iCurId > 0 ? "AND cur_id=:cur_id " : " " );
            sQuery += ( !"0".equals( sCentroId ) ? "AND cp.area_id IN(SELECT arb_id FROM cl_arbol_academico WHERE arb_institucion=:centro_id AND arb_nivel=1 AND arb_activo=1) " : " " );
            sQuery += " )t1 GROUP BY t1.usu_id) conInf \n"
                    + ") tabla_master \n"
                    + "GROUP BY usu_id";


//            sQuery = "SELECT t.usu_id,SUM(t.prematriculado)prematriculado,SUM(t.matriculado)matriculado FROM "
//                    + "(SELECT cp.*,"
//                    + "IF(cp.con_estado_matricula=0,1,0) prematriculado,"
//                    + "IF(cp.con_estado_matricula=2,1,0) matriculado "
//                    + "FROM cl_consulta_publico cp ";
//
//            sQuery += "WHERE cp.con_fecha_registro BETWEEN :fecha_ini AND :fecha_fin AND ISNULL(cp.esp_id) AND cp.con_activo=1 ";
//            sQuery += ( iAreaId > 0 ? "AND area_id=:area_id " : " " );
//            sQuery += ( iModId > 0 ? "AND mod_id=:mod_id " : " " );
//            sQuery += ( iCurId > 0 ? "AND cur_id=:cur_id " : " " );
//            sQuery += ( !"0".equals( sCentroId ) ? "AND cp.area_id IN(SELECT arb_id FROM cl_arbol_academico WHERE arb_institucion=:centro_id AND arb_nivel=1 AND arb_activo=1) " : " " );
//            sQuery += ")t GROUP BY t.usu_id";
            sqlQuery = this.getSession().createSQLQuery( sQuery );

            sqlQuery.setDate( "fecha_ini", fechaIni );
            sqlQuery.setDate( "fecha_fin", fechaFin );
            if ( iAreaId > 0 ) {
                sqlQuery.setInteger( "area_id", iAreaId );
            }
            if ( iModId > 0 ) {
                sqlQuery.setInteger( "mod_id", iModId );
            }
            if ( iCurId > 0 ) {
                sqlQuery.setInteger( "cur_id", iCurId );
            }
            if ( !"0".equals( sCentroId ) ) {
                sqlQuery.setString( "centro_id", sCentroId );
            }
            System.out.println( "QUERY : " + sQuery );
            lstAux = sqlQuery.list();
            iSize = lstAux.size();
            for ( int i = 0; i < iSize; i++ ) {
                objFila = (Object[])lstAux.get( i );
                infoMat = new BeanInfoMatricula();
                infoMat.setInf_contador( i + 1 );
                infoMat.setUsu_id( CommonWeb.parseObjToInt( objFila[0] ) );
                infoMat.setInf_informes( CommonWeb.parseObjToInt( objFila[1] ) );
                infoMat.setInf_matriculados( CommonWeb.parseObjToInt( objFila[2] ) );
                infoMat.setInf_totalAlumno( infoMat.getInf_matriculados() + infoMat.getInf_informes() );
                lstCantMat.add( infoMat );
            }
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }

        return lstCantMat;
    }

//    @Override
//    public List<ClConsultaPublico> listadoInscritosPorUsuario( int usu_id, Date fechaIni, Date fechaFin, String tipo, int iAreaId, int iModId, int iCurId ) {
//        //List<ClConsultaPublico> lista=new ArrayList<ClConsultaPublico>();
//        Criteria criteria = (Criteria)this.getSession().createCriteria( ClConsultaPublico.class ).add( Restrictions.eq( "usuId", usu_id ) ).add( Restrictions.between( "conFechaRegistro", fechaIni, fechaFin ) ).add( Restrictions.eq( "conEstadoMatricula", tipo ) ).add( Restrictions.isNull( "espId" ) );
//
//        if ( iAreaId > 0 ) {
//            criteria.add( Restrictions.eq( "areaId", iAreaId ) );
//        }
//        if ( iModId > 0 ) {
//            criteria.add( Restrictions.eq( "clArbolAcademico.arbId", iModId ) );
//        }
//        if ( iCurId > 0 ) {
//            criteria.add( Restrictions.eq( "curId", iCurId ) );
//        }
//        if ( iCurId == -1 ) {
//            criteria.add( Restrictions.isNull( "curId" ) );
//        }
//        List lista = criteria.list();
//
//        return lista;
//    }
    @Override
    public List<ClConsultaPublico> listadoInscritosPorUsuario( int usu_id, Date fechaIni, Date fechaFin, String tipo, int iAreaId, int iModId, int iCurId, String sCentroId ) {
        //List<ClConsultaPublico> lista=new ArrayList<ClConsultaPublico>();
        String sQuery;
        SQLQuery sqlQuery;
        List<Integer> lstAux;
        Criteria criteria = (Criteria)this.getSession().createCriteria( ClConsultaPublico.class ).add( Restrictions.eq( "usuId", usu_id ) ).add( Restrictions.between( "conFechaRegistro", fechaIni, fechaFin ) ).add( Restrictions.eq( "conEstadoMatricula", tipo ) ).add( Restrictions.isNull( "espId" ) );

        if ( iAreaId > 0 ) {
            criteria.add( Restrictions.eq( "areaId", iAreaId ) );
        }
        if ( iModId > 0 ) {
            criteria.add( Restrictions.eq( "clArbolAcademico.arbId", iModId ) );
        }
        if ( iCurId > 0 ) {
            criteria.add( Restrictions.eq( "curId", iCurId ) );
        }
        if ( iCurId == -1 ) {
            criteria.add( Restrictions.isNull( "curId" ) );
        }
        if ( !"0".equals( sCentroId ) ) {
            sQuery = "SELECT arb_id FROM cl_arbol_academico WHERE arb_institucion=:centro_id AND arb_nivel=1 AND arb_activo=1";
            sqlQuery = this.getSession().createSQLQuery( sQuery );
            sqlQuery.setString( "centro_id", sCentroId );
            System.out.println( "QUERY : " + sQuery );
            lstAux = sqlQuery.list();
            criteria.add( Restrictions.in( "areaId", lstAux ) );
        }
        List lista = criteria.list();

        return lista;
    }

    @Override
    public ClConsultaPublico seleccionarConsultaPublico( int con_id ) {
        ClConsultaPublico clConsultaPublico = (ClConsultaPublico)this.getSession().createCriteria( ClConsultaPublico.class ).add( Restrictions.eq( "conId", con_id ) ).uniqueResult();
        return clConsultaPublico;
    }

    @Override
    public List<ClConsultaPublico> seleccionarConsultaPublicoporFecha( Date fecha, int usu_id ) {
        List<ClConsultaPublico> lista = new ArrayList<ClConsultaPublico>();
        try {

            DateFormat format = new SimpleDateFormat( "yyyy-MM-dd" );
            SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
            String Tfecha = format.format( fecha );
            Date w_fecha = sdf.parse( Tfecha );
            //Date fec=format.parse(fecha);
            lista = this.getSession().createCriteria( ClConsultaPublico.class ).add( Restrictions.eq( "conFechaContacto", w_fecha ) ).add( Restrictions.eq( "usuId", usu_id ) ).add( Restrictions.eq( "conActivo", "1" ) ).add( Restrictions.eq( "conEstadoMatricula", "0" ) ).list();

        } catch ( ParseException ex ) {
            Logger.getLogger( HSConsultaPublicoDAOImpl.class.getName() ).log( Level.SEVERE, null, ex );
        }
        return lista;
    }

    @Override
    public List<ClConsultaPublico> listarPublicoConsultaFechaRegistro( int loc_id, int are_id, int mat_id, int usu_id, String distrito, String procedencia, Date fecha_ini, Date fecha_fin ) {
        List<ClConsultaPublico> lista = new ArrayList<ClConsultaPublico>();
        Criteria criteria = this.getSession().createCriteria( ClConsultaPublico.class, "clConsultaPublico" ).createCriteria( "clConsultaPublico.clModulo", "clModulo" ).createCriteria( "clModulo.clArea", "clArea" ).createCriteria( "clConsultaPublico.clAlumno", "clAlumno" ).add( Restrictions.eq( "clConsultaPublico.conActivo", "1" ) ).add( Restrictions.eq( "clModulo.modActivo", "1" ) ).add( Restrictions.eq( "clAlumno.aluActivo", "1" ) ).add( Restrictions.eq( "clArea.areActivo", "1" ) );
        criteria.add( Restrictions.between( "clConsultaPublico.conFechaRegistro", fecha_ini, fecha_fin ) );
        if ( loc_id > 0 ) {
            System.out.println( "entro a local" );
            criteria.add( Restrictions.eq( "clConsultaPublico.acLocal.Id", loc_id ) );
        }
        if ( are_id > 0 ) {
            System.out.println( "el are_id -> " + are_id );
            criteria.add( Restrictions.eq( "clArea.areId", are_id ) );
        }
        if ( mat_id != 3 ) {
            System.out.println( "matricula -> " + String.valueOf( mat_id ) );
            criteria.add( Restrictions.eq( "clConsultaPublico.conEstadoMatricula", String.valueOf( mat_id ) ) );
        }
        if ( usu_id > 0 ) {
            System.out.println( "el usu_id -> " + usu_id );
            criteria.add( Restrictions.eq( "clConsultaPublico.usuId", usu_id ) );
        }
        if ( !distrito.equals( "000000" ) ) {
            System.out.println( "entro a distrito" );
        }
        if ( !procedencia.equals( "000000" ) ) {
            System.out.println( "la procedencia -> " + procedencia );
            criteria.add( Restrictions.eq( "clConsultaPublico.conProcedencia", procedencia ) );
        }
        criteria.addOrder( Order.asc( "clConsultaPublico.conFechaRegistro" ) );

        lista = criteria.list();
        return lista;

    }

    @Override
    public ClMedioPublicidadDet seleccionarMedioPublicidad( int medIddet ) {
        ClMedioPublicidadDet medio = (ClMedioPublicidadDet)this.getSession().createCriteria( ClMedioPublicidadDet.class, "ClMedioPublicidadDet" ).add( Restrictions.eq( "idPubDet", medIddet ) ).uniqueResult();


        return medio;
    }

    @Override
    public void eliminarConsultacl( String cons_id ) throws DataAccessException {
        String hqlUpdate = "update ClConsultaPublico set conActivo = :v_activo where conId = :v_id";
        this.getSession().createQuery( hqlUpdate ).setString( "v_activo", "0" ).setString( "v_id", cons_id ).executeUpdate();
    }

    @Override
    public void eliminarMatriculacl( int mat_id ) throws DataAccessException {
        String hqlUpdate = "update ClConsultaPublico set conEstadoMatricula = :v_activo where matId = :v_id";
        this.getSession().createQuery( hqlUpdate ).setString( "v_activo", "0" ).setInteger( "v_id", mat_id ).executeUpdate();
    }
    //    @Override // Se reemplaza por cambio en query y optimizacion de consulta
//    public List<BeanInfoMatricula> cantidadMatriculadosUsuario( Date fecha_ini, Date fecha_fin, int iAreaId, int iModId, int iCurId ) {
//        List<BeanInfoMatricula> info = new ArrayList<BeanInfoMatricula>();
//        Criteria criteria = (Criteria) this.getSession().createCriteria( ClConsultaPublico.class ).setProjection( Projections.projectionList().add( Projections.rowCount(), "cantidad" ).add( Projections.property( "usuId" ) ).add( Projections.groupProperty( "usuId" ) ) ).add( Restrictions.between( "conFechaRegistro", fecha_ini, fecha_fin ) ).add( Restrictions.isNull( "espId" ) );
//
//        if ( iAreaId > 0 ) {
//            System.out.println( "areaDAO:" + iAreaId );
//            criteria.add( Restrictions.eq( "areaId", iAreaId ) );
//        }
//        if ( iModId > 0 ) {
//            criteria.add( Restrictions.eq( "clArbolAcademico.arbId", iModId ) );
//        }
//        if ( iCurId > 0 ) {
//            criteria.add( Restrictions.eq( "curId", iCurId ) );
//        }
//        if ( iCurId == -1 ) {
//            criteria.add( Restrictions.isNull( "curId" ) );
//        }
//        List lista = criteria.list();
//
//        Object[] obj;
//        for ( int i = 0; i < lista.size(); i++ ) {
//            obj = (Object[]) lista.get( i );
//            Criteria criteria2 = (Criteria) this.getSession().createCriteria( ClConsultaPublico.class ).setProjection( Projections.projectionList().add( Projections.rowCount(), "cantidad" ).add( Projections.property( "usuId" ) ).add( Projections.groupProperty( "usuId" ) ) ).add( Restrictions.eq( "conEstadoMatricula", "0" ) ).add( Restrictions.eq( "usuId", Integer.parseInt( obj[1].toString() ) ) ).add( Restrictions.between( "conFechaRegistro", fecha_ini, fecha_fin ) ).add( Restrictions.isNull( "espId" ) );
//
//            if ( iAreaId > 0 ) {
//                criteria2.add( Restrictions.eq( "areaId", iAreaId ) );
//            }
//            if ( iModId > 0 ) {
//                criteria2.add( Restrictions.eq( "clArbolAcademico.arbId", iModId ) );
//            }
//            if ( iCurId > 0 ) {
//                criteria2.add( Restrictions.eq( "curId", iCurId ) );
//            }
//            if ( iCurId == -1 ) {
//                criteria2.add( Restrictions.isNull( "curId" ) );
//            }
//            List lista2 = criteria2.list();
//
//            Criteria criteria3 = (Criteria) this.getSession().createCriteria( ClConsultaPublico.class ).setProjection( Projections.projectionList().add( Projections.rowCount(), "cantidad" ).add( Projections.property( "usuId" ) ).add( Projections.groupProperty( "usuId" ) ) ).add( Restrictions.eq( "conEstadoMatricula", "2" ) ).add( Restrictions.eq( "usuId", Integer.parseInt( obj[1].toString() ) ) ).add( Restrictions.between( "conFechaRegistro", fecha_ini, fecha_fin ) );
//
//
//            if ( iAreaId > 0 ) {
//                criteria3.add( Restrictions.eq( "areaId", iAreaId ) );
//            }
//            if ( iModId > 0 ) {
//                criteria3.add( Restrictions.eq( "clArbolAcademico.arbId", iModId ) );
//            }
//            if ( iCurId > 0 ) {
//                criteria3.add( Restrictions.eq( "curId", iCurId ) );
//            }
//            if ( iCurId == -1 ) {
//                criteria3.add( Restrictions.isNull( "curId" ) );
//            }
//            List lista3 = criteria3.list();
//
//            BeanInfoMatricula mat = new BeanInfoMatricula();
//            mat.setInf_informes( 0 );
//            Object[] obj2;
//            Object[] obj3;
//            if ( !lista2.isEmpty() ) {
//                obj2 = (Object[]) lista2.get( 0 );
//                mat.setInf_informes( Integer.parseInt( obj2[0].toString() ) );
//
//            }
//            if ( !lista3.isEmpty() ) {
//                obj3 = (Object[]) lista3.get( 0 );
//                mat.setInf_matriculados( Integer.parseInt( obj3[0].toString() ) );
//
//            }
//
//            mat.setInf_contador( i + 1 );
//            //mat.setInf_matriculados(Integer.parseInt(obj[0].toString()));
//            mat.setUsu_id( Integer.parseInt( obj[1].toString() ) );
//            info.add( mat );
//
//        }
//
//        return info;
//    }

    @Override
    public List<BeanCLPublico> lstMatUsu( int usu_id, Date fechaIni, Date fechaFin, String tipo, int iAreaId, int iModId, int iCurId, String sCentroId ) {
        int iSize;
        BeanCLPublico ConsPub;
        String sQuery;
        SQLQuery sqlQuery;
        Object[] objFila;
        List lstAux;
        List<BeanCLPublico> lstMat;
        lstMat = new ArrayList<BeanCLPublico>();
        try {

            sQuery = "select tabla.* from (select CONCAT_WS(' ',a.alu_apmaterno,a.alu_apmaterno, a.alu_nombres) nomComp,\n"
                    + "CONCAT_WS(' - ',a.alu_celular,a.alu_telefono) telf, aArea.arb_descripcion area, \n"
                    + "aMod.arb_descripcion modulo,aCur.arb_descripcion curso, mat.mat_fecha fMatricula, 'matriculado' estado "
                    + "FROM cl_alumno_tarifa as aTar \n"
                    + "INNER JOIN cl_alumno as a ON (a.alu_id=aTar.alu_id) \n"
                    + "INNER JOIN cl_matricula as mat ON (mat.mat_id=aTar.mat_id) \n"
                    + "INNER JOIN cl_seccion as s ON(aTar.sec_id=s.sec_id) \n"
                    + "INNER JOIN cl_arbol_academico aTal ON(s.arb_id=aTal.arb_id) \n"
                    + "INNER JOIN cl_arbol_academico aCur ON(aTal.arb_id_padre=aCur.arb_id) \n"
                    + "INNER JOIN cl_arbol_academico aMod ON(aCur.arb_id_padre=aMod.arb_id) \n"
                    + "INNER JOIN cl_arbol_academico aArea ON(aMod.arb_id_padre=aArea.arb_id) \n"
                    + "where aTar.alutar_fecha_pago BETWEEN :fecha_ini AND :fecha_fin \n"
                    + "and alutar_activo=1 and aTar.usu_id in (select usu_id from tb_usuario where rol_id=38 and usu_activo=1) \n"
                    + "and a.alu_activo=1 and mat.mat_activo=1 and mat.mat_tipo='022001' \n"
                    + "and aTar.usu_id=:usu_id";

            sQuery += ( iAreaId > 0 ? "AND aArea.arb_id=:area_id " : " " );
            sQuery += ( iModId > 0 ? "AND aMod.arb_id=:mod_id " : " " );
            sQuery += ( iCurId > 0 ? "AND aCur.arb_id=:cur_id " : " " );
            sQuery += ( !"0".equals( sCentroId ) ? "AND aArea.arb_id IN(SELECT arb_id FROM cl_arbol_academico WHERE arb_institucion=:centro_id AND arb_nivel=1 AND arb_activo=1) " : " " );
            sQuery +=") tabla ";
            sqlQuery = this.getSession().createSQLQuery( sQuery );


            sqlQuery.setDate( "fecha_ini", fechaIni );
            sqlQuery.setDate( "fecha_fin", fechaFin );
            sqlQuery.setInteger( "usu_id", usu_id );

            if ( iAreaId > 0 ) {
                sqlQuery.setInteger( "area_id", iAreaId );
            }
            if ( iModId > 0 ) {
                sqlQuery.setInteger( "mod_id", iModId );
            }
            if ( iCurId > 0 ) {
                sqlQuery.setInteger( "cur_id", iCurId );
            }
            if ( !"0".equals( sCentroId ) ) {
                sqlQuery.setString( "centro_id", sCentroId );
            }
           // System.out.println( "QUERY : " + sQuery );
            lstAux = sqlQuery.list();
            iSize = lstAux.size();
            int i = 0;
            // for ( int i = 0; i < iSize; i++ ) {
            for ( Object objAux : lstAux ) {
                objFila = (Object[])objAux;
                // objFila = (Object[])lstAux.get( i );
                ConsPub = new BeanCLPublico();
                ConsPub.setContador( i + 1 );
                ConsPub.setNomAlum( CommonWeb.parseObjToString( objFila[0] ) );
                ConsPub.setTelAlum( CommonWeb.parseObjToString( objFila[1] ) );
                ConsPub.setsDesArea( CommonWeb.parseObjToString( objFila[2] ) );
                ConsPub.setDes_modulo( CommonWeb.parseObjToString( objFila[3] ) );
                ConsPub.setsDesCurso( CommonWeb.parseObjToString( objFila[4] ) );
                ConsPub.setsFechaRegistro( CommonWeb.parseObjToString( objFila[5] ) );
                ConsPub.setEstado( CommonWeb.parseObjToString( objFila[6] ) );
                lstMat.add( ConsPub );
                i++;
            }
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }

        return lstMat;
    }

    @Override
    public List<BeanCLPublico> lstInfUsu( int usu_id, Date fechaIni, Date fechaFin, String tipo, int iAreaId, int iModId, int iCurId, String sCentroId ) {
        int iSize;
        BeanCLPublico ConsPub;
        String sQuery;
        SQLQuery sqlQuery;
        Object[] objFila;
        List lstAux;
        List<BeanCLPublico> lstMat;
        lstMat = new ArrayList<BeanCLPublico>();
        try {

            sQuery = "select nomComp,telf,area,modulo,curso,fMatricula,max(estado) estado,matricula,alu_id,idCurso\n"
                    + "FROM\n"
                    + "("
                    + "select tablaM.* from (select CONCAT_WS(' ',a.alu_apmaterno,a.alu_apmaterno, a.alu_nombres) nomComp,\n"
                    + "CONCAT_WS(' - ',a.alu_celular,a.alu_telefono) telf, aArea.arb_descripcion area, \n"
                    + "aMod.arb_descripcion modulo,aCur.arb_descripcion curso, mat.mat_fecha fMatricula, if( mat.mat_tipo='022005','prematiculado','matriculado') estado,aTar.mat_id matricula,aTar.alu_id,aCur.arb_id idCurso\n"
                    + "from cl_alumno_tarifa aTar \n"
                    + "INNER JOIN cl_alumno a ON (a.alu_id=aTar.alu_id)\n"
                    + "INNER JOIN cl_matricula mat ON (mat.mat_id=aTar.mat_id) \n"
                    + "INNER JOIN cl_seccion s ON(aTar.sec_id=s.sec_id) \n"
                    + "INNER JOIN cl_arbol_academico aTal ON(s.arb_id=aTal.arb_id) \n"
                    + "INNER JOIN cl_arbol_academico aCur ON(aTal.arb_id_padre=aCur.arb_id) \n"
                    + "INNER JOIN cl_arbol_academico aMod ON(aCur.arb_id_padre=aMod.arb_id) \n"
                    + "INNER JOIN cl_arbol_academico aArea ON(aMod.arb_id_padre=aArea.arb_id) \n"
                    + "where aTar.alutar_fecha_pago BETWEEN :fecha_ini AND :fecha_fin \n"
                    + "and alutar_activo=1 \n"
                    + "and a.alu_activo=1 and mat.mat_activo=1 and mat.mat_tipo in ('022001','022005') \n"
                    + "and aTar.usu_id=:usu_id ";

            sQuery += ( iAreaId > 0 ? "AND aArea.arb_id=:area_id " : " " );
            sQuery += ( iModId > 0 ? "AND aMod.arb_id=:mod_id " : " " );
            sQuery += ( iCurId > 0 ? "AND aCur.arb_id=:cur_id " : " " );
            sQuery += ( !"0".equals( sCentroId ) ? "AND aArea.arb_id IN(SELECT arb_id FROM cl_arbol_academico WHERE arb_institucion=:centro_id AND arb_nivel=1 AND arb_activo=1) " : " " );

            sQuery += ") tablaM "
                    + "UNION "
                    + "select tablaC.* from (SELECT  CONCAT_WS(' ',a.alu_apmaterno,a.alu_apmaterno, a.alu_nombres) nomComp,\n"
                    + "CONCAT_WS(' - ',a.alu_celular,a.alu_telefono) telf, aArea.arb_descripcion area, \n"
                    + "aMod.arb_descripcion modulo,aCur.arb_descripcion curso, cp.con_fecha_registro fMatricula, \"Consulta\" estado, cp.mat_id matricula,cp.alu_id,cp.cur_id idCurso\n"
                    + "FROM cl_consulta_publico cp\n"
                    + "INNER JOIN cl_alumno a ON (cp.alu_id=a.alu_id)\n"
                    + "INNER JOIN cl_arbol_academico aCur ON(cp.cur_id=aCur.arb_id) \n"
                    + "INNER JOIN cl_arbol_academico aMod ON(cp.mod_id=aMod.arb_id) \n"
                    + "INNER JOIN cl_arbol_academico aArea ON(cp.area_id=aArea.arb_id)  \n"
                    + "WHERE cp.con_fecha_registro BETWEEN :fecha_ini AND :fecha_fin \n"
                    + "AND ISNULL(cp.esp_id) AND cp.con_activo=1 \n"
                    + "AND a.alu_activo=1\n"
                    + "and cp.usu_id=:usu_id ";
            sQuery += ( iAreaId > 0 ? "AND area_id=:area_id " : " " );
            sQuery += ( iModId > 0 ? "AND mod_id=:mod_id " : " " );
            sQuery += ( iCurId > 0 ? "AND cur_id=:cur_id " : " " );
            sQuery += ( !"0".equals( sCentroId ) ? "AND cp.area_id IN(SELECT arb_id FROM cl_arbol_academico WHERE arb_institucion=:centro_id AND arb_nivel=1 AND arb_activo=1) " : " " );
            sQuery += ") tablaC "
                    + ") tabla \n"
                    + "GROUP BY tabla.matricula,tabla.alu_id,tabla.idCurso";

            sqlQuery = this.getSession().createSQLQuery( sQuery );

            sqlQuery.setDate( "fecha_ini", fechaIni );
            sqlQuery.setDate( "fecha_fin", fechaFin );
            sqlQuery.setInteger( "usu_id", usu_id );

            if ( iAreaId > 0 ) {
                sqlQuery.setInteger( "area_id", iAreaId );
            }
            if ( iModId > 0 ) {
                sqlQuery.setInteger( "mod_id", iModId );
            }
            if ( iCurId > 0 ) {
                sqlQuery.setInteger( "cur_id", iCurId );
            }
            if ( !"0".equals( sCentroId ) ) {
                sqlQuery.setString( "centro_id", sCentroId );
            }
           // System.out.println( "QUERY : " + sQuery );
            lstAux = sqlQuery.list();
            iSize = lstAux.size();
            for ( int i = 0; i < iSize; i++ ) {
                objFila = (Object[])lstAux.get( i );
                ConsPub = new BeanCLPublico();
                ConsPub.setContador( i + 1 );
                ConsPub.setNomAlum( CommonWeb.parseObjToString( objFila[0] ) );
                ConsPub.setTelAlum( CommonWeb.parseObjToString( objFila[1] ) );
                ConsPub.setsDesArea( CommonWeb.parseObjToString( objFila[2] ) );
                ConsPub.setDes_modulo( CommonWeb.parseObjToString( objFila[3] ) );
                ConsPub.setsDesCurso( CommonWeb.parseObjToString( objFila[4] ) );
                ConsPub.setsFechaRegistro( CommonWeb.parseObjToString( objFila[5] ) );
                ConsPub.setEstado( CommonWeb.parseObjToString( objFila[6] ) );
                lstMat.add( ConsPub );
            }
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }

        return lstMat;
    }
}
