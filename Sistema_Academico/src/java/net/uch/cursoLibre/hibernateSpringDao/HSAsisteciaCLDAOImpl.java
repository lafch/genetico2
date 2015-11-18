/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.hibernateSpringDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.uch.cursoLibre.managedBeans.beans.BeanCLAsistencia;
import net.uch.cursoLibre.managedBeans.beans.BeanReporte;
import net.uch.util.CommonWeb;
import org.hibernate.SQLQuery;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author LUIS
 */
public class HSAsisteciaCLDAOImpl extends HibernateDaoSupport implements HSAsisteciaCLDAO {

    Map<Integer, BeanReporte> hmListaAlumnos = new HashMap<Integer, BeanReporte>();

    @Override
    public List<BeanReporte> listarSesionesPorSecId( int iSecId ) {
        int iSize;
        BeanReporte repAsist;
        SQLQuery sqlQuery;
        Object[] aobjFila;
        List lstAux;
        List<BeanReporte> lista = new ArrayList<BeanReporte>();



        String hqlQuery = "select ses_id,ses_fecha_sesion from ac_sesion_asistencia where sec_id=:v_sec_id and ses_tipo_asistencia='050003' ORDER BY ses_fecha_sesion desc";
        try {
            sqlQuery = this.getSession().createSQLQuery( hqlQuery );
            sqlQuery.setInteger( "v_sec_id", iSecId );
            lstAux = sqlQuery.list();
            iSize = lstAux.size();
            for ( int i = 0; i < iSize; i++ ) {
                aobjFila = (Object[])lstAux.get( i );
                repAsist = new BeanReporte();
                repAsist.setExpr1( aobjFila[1] + "" );
                repAsist.setExpr2( aobjFila[0] + "" );
                lista.add( repAsist );
            }
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }

        return lista;
    }

    @Override
    //public List<BeanCLAsistencia> listarAsitenciaPorSecId( int iSecId, boolean blModular ) {
    public List<BeanCLAsistencia> listarAsitenciaPorSecId( int iSecId ) {

        int iSize;
        BeanCLAsistencia clAsis;
        BeanReporte repAsist;
        SQLQuery sqlQuery;
        Object[] aobjFila;
        List lstAux;
        List<BeanCLAsistencia> lista = new ArrayList<BeanCLAsistencia>();

        String hqlQuery = "";

        hqlQuery = "select \n"
                + "sa.ses_id,a.alu_id, alu_codigo, \n"
                + "concat_ws(' ', a.alu_appaterno, a.alu_apmaterno, a.alu_nombres) nombres,\n"
                + "if( sa.ses_fecha_registro < convert(m.mat_fecha, date),'NO PRESENTA MATRICULA',\n"
                + "if( isnull( \n"
                + "( select c.cat_descripcion_elemento \n"
                + "from tb_catalogo c \n"
                + "where concat(c.cat_codigo_grupo, c.cat_codigo_elemento)=sesefeasis_det_asistencia) ),\n"
                + "'NSTA',\n"
                + "(select if(c.cat_descripcion_elemento='ASISTIO','A',if(c.cat_descripcion_elemento='NO ASISTIO','F','---')) \n"
                + "from tb_catalogo c \n"
                + "where concat(c.cat_codigo_grupo, c.cat_codigo_elemento)=sesefeasis_det_asistencia))) asistencia,\n"
                + "convert(sa.ses_fecha_inicio, time) inicio,\n"
                + "convert(sa.ses_fecha_fin, time) fin, \n"
                + "tabla.sesefeasis_det_id \n"
                + "from cl_alumno a \n"
                + "inner join cl_matricula m on a.alu_id=m.alu_id \n"
                + "inner join cl_matricula_taller mt on mt.mat_id=m.mat_id \n"
                + "inner join cl_seccion s on s.sec_id=mt.sec_id \n"
                + "INNER JOIN cl_arbol_academico aTal ON(s.arb_id=aTal.arb_id)\n"
                + "INNER JOIN cl_arbol_academico aCur ON(aTal.arb_id_padre=aCur.arb_id)\n"
                + "INNER JOIN cl_arbol_academico aMod ON(aCur.arb_id_padre=aMod.arb_id)\n"
                + "inner join ac_sesion_asistencia sa on sa.sec_id=s.sec_id \n"
                + "inner join ac_docente d on d.doc_id=sa.doc_id \n"
                + "left join ( \n"
                + "select \n"
                + "sea.ses_id,\n"
                + "sea.sesefeasis_registro,\n"
                + "sead.alu_id,\n"
                + "min(sead.sesefeasis_det_asistencia) sesefeasis_det_asistencia,\n"
                + "sead.sesefeasis_det_id \n"
                + "from ac_sesion_efectiva_asistencia sea \n"
                + "inner join ac_sesion_efectiva_asis_det_cl sead on (sea.sesefeasis_id=sead.sesefeasis_id) \n"
                + "where sea.sesefeasis_activo='1' \n"
                + "and sead.sesefeasis_det_activo='1' \n"
                + "group by sead.alu_id,sea.ses_id) tabla on (tabla.ses_id= sa.ses_id AND tabla.alu_id=a.alu_id) \n"
                + "where m.mat_activo='1' \n"
                + "and m.mat_tipo='022001' \n"
                + "and s.sec_activo='1' \n"
                + "and aTal.arb_activo='1' \n"
                + "and aCur.arb_activo='1' \n"
                + "and aMod.arb_activo='1' \n"
                + "and sa.ses_tipo_asistencia='050003' \n"
                + "and s.sec_id=:v_sec_id \n"
                + "group by sa.ses_id,a.alu_id\n"
                + "order by sa.ses_fecha_registro,s.sec_nombre,inicio,4";

        try {
            sqlQuery = this.getSession().createSQLQuery( hqlQuery );
            sqlQuery.setInteger( "v_sec_id", iSecId );
            lstAux = sqlQuery.list();
            iSize = lstAux.size();

            lista = new ArrayList<BeanCLAsistencia>();
            for ( int i = 0; i < iSize; i++ ) {
                aobjFila = (Object[])lstAux.get( i );
                clAsis = new BeanCLAsistencia();
//                iAlumId = (Integer)aobjFila[1]; //AlumId
                clAsis.setSesId( CommonWeb.parseObjToInt( aobjFila[0] ) );//Id Sesion
                clAsis.setAlumId( CommonWeb.parseObjToInt( aobjFila[1] ) );//Id del alumno
                clAsis.setAlumCodigo( CommonWeb.parseObjToString( aobjFila[2] ) );//Codigo del alumno
                clAsis.setAlumNom( CommonWeb.parseObjToString( aobjFila[3] ) );//nombres del alumno
                clAsis.setAlumAsistencia( CommonWeb.parseObjToString( aobjFila[4] ) );//tipo asistencia
//                if ( clAsis.getAlumAsistencia().equals( "NSTA" ) ) {
//                    clAsis.setEstilo( "font-size: 13px; font-weight: bold; color: #FF0000;" );
//                } else {
//                    clAsis.setEstilo( "font-size: 13px; font-weight: bold; color: #0000FF;" );
//                }
                clAsis.setSesDetId( CommonWeb.parseObjToInt( aobjFila[7] == null ? 0 : aobjFila[7] ) );
                /*
                 repAsist.setExpr5( aobjFila[4] + "" ); //tipo asistencia
                 repAsist.setExpr6( aobjFila[5] + "" );//hora de inicio de la sesion
                 repAsist.setExpr7( aobjFila[6] + "" );//hora de culminacion de la sesion
                 */
                lista.add( clAsis );

            }
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
        return lista;
    }

    public List<BeanCLAsistencia> listarAsitenciaPorAlumId( int iSecId, boolean blModular, int iAlumId ) {
        int iSize;
        BeanCLAsistencia clAsis;
        BeanReporte repAsist;
        SQLQuery sqlQuery;
        Object[] aobjFila;
        List lstAux;
        List<BeanCLAsistencia> lista = new ArrayList<BeanCLAsistencia>();

        String hqlQuery = "";
        if ( blModular ) {
            hqlQuery = "select \n"
                    + "sa.ses_id,a.alu_id, alu_codigo,\n"
                    + "concat_ws(' ', a.alu_appaterno, a.alu_apmaterno, a.alu_nombres) nombres,concat( \n"
                    + "if( sa.ses_fecha_registro < convert(m.mat_fecha, date),'NO PRESENTA MATRICULA', \n"
                    + "if( isnull( \n"
                    + "( select c.cat_descripcion_elemento \n"
                    + "from tb_catalogo c \n"
                    + "where concat(c.cat_codigo_grupo, c.cat_codigo_elemento)=sesefeasis_det_asistencia) ),\n"
                    + "'NSTA', \n"
                    + "(select if(c.cat_descripcion_elemento='ASISTIO','A',if(c.cat_descripcion_elemento='NO ASISTIO','F','---')) \n"
                    + "from tb_catalogo c \n"
                    + "where concat(c.cat_codigo_grupo, c.cat_codigo_elemento)=sesefeasis_det_asistencia))),' ',\n"
                    + "if(isnull(time(sesefeasis_registro)),'-',time(sesefeasis_registro))) asistencia, \n"
                    + "convert(sa.ses_fecha_inicio, time) inicio, \n"
                    + "convert(sa.ses_fecha_fin, time) fin,  \n"
                    + "tabla.sesefeasis_det_id \n"
                    + "from cl_alumno a \n"
                    + "inner join cl_matricula m on a.alu_id=m.alu_id \n"
                    + "inner join cl_matricula_taller mt on mt.mat_id=m.mat_id \n"
                    + "inner join cl_seccion s on s.sec_id=mt.sec_id \n"
                    + "INNER JOIN cl_arbol_academico aTal ON(s.arb_id=aTal.arb_id)\n"
                    + "INNER JOIN cl_arbol_academico aCur ON(aTal.arb_id_padre=aCur.arb_id)\n"
                    + "INNER JOIN cl_arbol_academico aMod ON(aCur.arb_id_padre=aMod.arb_id)\n"
                    + "inner join ac_sesion_asistencia sa on sa.sec_id=s.sec_id \n"
                    + "inner join ac_docente d on d.doc_id=sa.doc_id \n"
                    + "left join ( \n"
                    + "select \n"
                    + "sea.ses_id,\n"
                    + "sea.sesefeasis_registro,\n"
                    + "sead.alu_id,\n"
                    + "(sead.sesefeasis_det_asistencia) sesefeasis_det_asistencia,\n"
                    + "sead.sesefeasis_det_id \n"
                    + "from ac_sesion_efectiva_asistencia sea \n"
                    + "inner join ac_sesion_efectiva_asis_det_cl sead on (sea.sesefeasis_id = sead.sesefeasis_id) \n"
                    + "where sea.sesefeasis_activo='1' \n"
                    + "and sead.sesefeasis_det_activo='1' ) tabla on (tabla.ses_id= sa.ses_id AND tabla.alu_id = a.alu_id) \n"
                    + "where m.mat_activo='1' \n"
                    + "and m.mat_tipo='022001' \n"
                    + "and s.sec_activo='1' \n"
                    + "and aTal.arb_activo='1' \n"
                    + "and aCur.arb_activo='1' \n"
                    + "and aMod.arb_activo='1' \n"
                    + "and sa.ses_tipo_asistencia='050003' \n"
                    + "and s.sec_id=:v_sec_id  \n"
                    + "and a.alu_id=:v_alu_id \n"
                    + "order by sa.ses_fecha_registro,s.sec_nombre,inicio,nombres";
        } else {
            hqlQuery = "select \n"
                    + "sa.ses_id,a.alu_id, alu_codigo, \n"
                    + "concat_ws(' ', a.alu_appaterno, a.alu_apmaterno, a.alu_nombres) nombres,\n"
                    + "if( sa.ses_fecha_registro < convert(m.mat_fecha, date),'NO PRESENTA MATRICULA',\n"
                    + "if( isnull( \n"
                    + "( select c.cat_descripcion_elemento \n"
                    + "from tb_catalogo c \n"
                    + "where concat(c.cat_codigo_grupo, c.cat_codigo_elemento)=sesefeasis_det_asistencia) ),\n"
                    + "'NSTA',\n"
                    + "(select if(c.cat_descripcion_elemento='ASISTIO','A',if(c.cat_descripcion_elemento='NO ASISTIO','F','---')) \n"
                    + "from tb_catalogo c \n"
                    + "where concat(c.cat_codigo_grupo, c.cat_codigo_elemento)=sesefeasis_det_asistencia))) asistencia,\n"
                    + "convert(sa.ses_fecha_inicio, time) inicio,\n"
                    + "convert(sa.ses_fecha_fin, time) fin, \n"
                    + "tabla.sesefeasis_det_id \n"
                    + "from cl_alumno a \n"
                    + "inner join cl_matricula m on a.alu_id=m.alu_id \n"
                    + "inner join cl_matricula_taller mt on mt.mat_id=m.mat_id \n"
                    + "inner join cl_seccion s on s.sec_id=mt.sec_id \n"
                    + "INNER JOIN cl_arbol_academico aTal ON(s.arb_id=aTal.arb_id)\n"
                    + "INNER JOIN cl_arbol_academico aCur ON(aTal.arb_id_padre=aCur.arb_id)\n"
                    + "INNER JOIN cl_arbol_academico aMod ON(aCur.arb_id_padre=aMod.arb_id)\n"
                    + "inner join ac_sesion_asistencia sa on sa.sec_id=s.sec_id \n"
                    + "inner join ac_docente d on d.doc_id=sa.doc_id \n"
                    + "left join ( \n"
                    + "select \n"
                    + "sea.ses_id,\n"
                    + "sea.sesefeasis_registro,\n"
                    + "sead.alu_id,\n"
                    + "min(sead.sesefeasis_det_asistencia) sesefeasis_det_asistencia,\n"
                    + "sead.sesefeasis_det_id \n"
                    + "from ac_sesion_efectiva_asistencia sea \n"
                    + "inner join ac_sesion_efectiva_asis_det_cl sead on (sea.sesefeasis_id = sead.sesefeasis_id) \n"
                    + "where sea.sesefeasis_activo='1' \n"
                    + "and sead.sesefeasis_det_activo='1' \n"
                    + "group by sead.alu_id,sea.ses_id) tabla on (tabla.ses_id= sa.ses_id AND tabla.alu_id = a.alu_id) \n"
                    + "where m.mat_activo='1' \n"
                    + "and m.mat_tipo='022001' \n"
                    + "and s.sec_activo='1' \n"
                    + "and aTal.arb_activo='1' \n"
                    + "and aCur.arb_activo='1' \n"
                    + "and aMod.arb_activo='1' \n"
                    + "and a.alu_id=:v_alu_id \n"
                    + "and sa.ses_tipo_asistencia='050003' \n"
                    + "and s.sec_id=:v_sec_id \n"
                    + "and a.alu_id=:v_alu_id \n"
                    + "group by sa.ses_id,a.alu_id\n"
                    + "order by sa.ses_fecha_registro,s.sec_nombre,inicio,4";
        }
        try {
            sqlQuery = this.getSession().createSQLQuery( hqlQuery );
            sqlQuery.setInteger( "v_sec_id", iSecId );
            sqlQuery.setInteger( "v_alu_id", iAlumId );
            lstAux = sqlQuery.list();
            iSize = lstAux.size();

            lista = new ArrayList<BeanCLAsistencia>();
            for ( int i = 0; i < iSize; i++ ) {
                aobjFila = (Object[])lstAux.get( i );
                clAsis = new BeanCLAsistencia();
//                iAlumId = (Integer)aobjFila[1]; //AlumId
                clAsis.setSesId( CommonWeb.parseObjToInt( aobjFila[0] ) );//Id Sesion
                clAsis.setAlumId( CommonWeb.parseObjToInt( aobjFila[1] ) );//Id del alumno
                clAsis.setAlumCodigo( CommonWeb.parseObjToString( aobjFila[2] ) );//Codigo del alumno 
                clAsis.setAlumNom( CommonWeb.parseObjToString( aobjFila[3] ) );//nombres del alumno 
                clAsis.setAlumAsistencia( CommonWeb.parseObjToString( aobjFila[4] ) ); //tipo asistencia
                if ( clAsis.getAlumAsistencia().equals( "NSTA" ) ) {
                    clAsis.setEstilo( "font-size: 13px; font-weight: bold; color: #FF0000;" );
                } else {
                    clAsis.setEstilo( "font-size: 13px; font-weight: bold; color: #0000FF;" );
                }

                clAsis.setSesDetId( CommonWeb.parseObjToInt( aobjFila[7] == null ? 0 : aobjFila[7] ) );
                /*
                 repAsist.setExpr5( aobjFila[4] + "" ); //tipo asistencia
                 repAsist.setExpr6( aobjFila[5] + "" );//hora de inicio de la sesion
                 repAsist.setExpr7( aobjFila[6] + "" );//hora de culminacion de la sesion
                 */
                lista.add( clAsis );

            }
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
        return lista;
    }

    //Medotos para reporte 
    @Override
    public List<BeanReporte> listarAsitenciaPorSecIdReporte( int iSecId ) {

        int iSize;
        BeanReporte clAsis;
        BeanReporte repAsist;
        SQLQuery sqlQuery;
        Object[] aobjFila;
        List lstAux;
        List<BeanReporte> lista = new ArrayList<BeanReporte>();

        String hqlQuery = "";

        hqlQuery = "select \n"
                + "sa.ses_id,a.alu_id, alu_codigo,sa.ses_fecha_sesion , \n"
                + "concat_ws(' ', a.alu_appaterno, a.alu_apmaterno, a.alu_nombres) nombres,\n"
                + "if( sa.ses_fecha_registro < convert(m.mat_fecha, date),'NO PRESENTA MATRICULA',\n"
                + "if( isnull( \n"
                + "( select c.cat_descripcion_elemento \n"
                + "from tb_catalogo c \n"
                + "where concat(c.cat_codigo_grupo, c.cat_codigo_elemento)=sesefeasis_det_asistencia) ),\n"
                + "'NSTA',\n"
                + "(select c.cat_descripcion_elemento \n"
                + "from tb_catalogo c \n"
                + "where concat(c.cat_codigo_grupo, c.cat_codigo_elemento)=sesefeasis_det_asistencia))) asistencia,\n"
                + "convert(sa.ses_fecha_inicio, time) inicio,\n"
                + "convert(sa.ses_fecha_fin, time) fin, \n"
                + "tabla.sesefeasis_det_id \n"
                + "from cl_alumno a \n"
                + "inner join cl_matricula m on a.alu_id=m.alu_id \n"
                + "inner join cl_matricula_taller mt on mt.mat_id=m.mat_id \n"
                + "inner join cl_seccion s on s.sec_id=mt.sec_id \n"
                + "INNER JOIN cl_arbol_academico aTal ON(s.arb_id=aTal.arb_id)\n"
                + "INNER JOIN cl_arbol_academico aCur ON(aTal.arb_id_padre=aCur.arb_id)\n"
                + "INNER JOIN cl_arbol_academico aMod ON(aCur.arb_id_padre=aMod.arb_id)\n"
                + "inner join ac_sesion_asistencia sa on sa.sec_id=s.sec_id \n"
                + "inner join ac_docente d on d.doc_id=sa.doc_id \n"
                + "left join ( \n"
                + "select \n"
                + "sea.ses_id,\n"
                + "sea.sesefeasis_registro,\n"
                + "sead.alu_id,\n"
                + "min(sead.sesefeasis_det_asistencia) sesefeasis_det_asistencia,\n"
                + "sead.sesefeasis_det_id \n"
                + "from ac_sesion_efectiva_asistencia sea \n"
                + "inner join ac_sesion_efectiva_asis_det_cl sead on (sea.sesefeasis_id=sead.sesefeasis_id) \n"
                + "where sea.sesefeasis_activo='1' \n"
                + "and sead.sesefeasis_det_activo='1' \n"
                + "group by sead.alu_id,sea.ses_id) tabla on (tabla.ses_id= sa.ses_id AND tabla.alu_id=a.alu_id) \n"
                + "where m.mat_activo='1' \n"
                + "and m.mat_tipo='022001' \n"
                + "and s.sec_activo='1' \n"
                + "and aTal.arb_activo='1' \n"
                + "and aCur.arb_activo='1' \n"
                + "and aMod.arb_activo='1' \n"
                + "and sa.ses_tipo_asistencia='050003' \n"
                + "and s.sec_id=:v_sec_id \n"
                + "group by sa.ses_id,a.alu_id\n"
                + "order by sa.ses_fecha_registro,s.sec_nombre,inicio,4";

        try {
            sqlQuery = this.getSession().createSQLQuery( hqlQuery );
            sqlQuery.setInteger( "v_sec_id", iSecId );
            lstAux = sqlQuery.list();
            iSize = lstAux.size();

            lista = new ArrayList<BeanReporte>();
            for ( int i = 0; i < iSize; i++ ) {
                aobjFila = (Object[])lstAux.get( i );
                clAsis = new BeanReporte();
//                iAlumId = (Integer)aobjFila[1]; //AlumId
                clAsis.setExpr1( CommonWeb.parseObjToString( aobjFila[0] ) );//Id Sesion
                clAsis.setExpr2( CommonWeb.parseObjToString( aobjFila[1] ) );//Id del alumno
                clAsis.setExpr3( CommonWeb.parseObjToString( aobjFila[2] ) );//Codigo del alumno
                clAsis.setExpr4( CommonWeb.parseObjToString( aobjFila[3] ) );//Fecha Sesion
                clAsis.setExpr5( CommonWeb.parseObjToString( aobjFila[4] ) );//nombres del alumno
                clAsis.setExpr6( CommonWeb.parseObjToString( aobjFila[5] ) );//tipo asistencia
                /*
                 repAsist.setExpr5( aobjFila[4] + "" ); //tipo asistencia
                 repAsist.setExpr6( aobjFila[5] + "" );//hora de inicio de la sesion
                 repAsist.setExpr7( aobjFila[6] + "" );//hora de culminacion de la sesion
                 */
                lista.add( clAsis );

            }
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
        return lista;
    }

    @Override
    public List<BeanReporte> listarAsitenciaPorAlumIdReporte( int iSecId, int iAlumId ) {

        int iSize;
        BeanReporte clAsis;
        BeanReporte repAsist;
        SQLQuery sqlQuery;
        Object[] aobjFila;
        List lstAux;
        List<BeanReporte> lista = new ArrayList<BeanReporte>();

        String hqlQuery = "";

        hqlQuery = "select \n"
                + "sa.ses_id,a.alu_id, alu_codigo,sa.ses_fecha_sesion ,\n"
                + "concat_ws(' ', a.alu_appaterno, a.alu_apmaterno, a.alu_nombres) nombres,concat( \n"
                + "if( sa.ses_fecha_registro < convert(m.mat_fecha, date),'NO PRESENTA MATRICULA', \n"
                + "if( isnull( \n"
                + "( select c.cat_descripcion_elemento \n"
                + "from tb_catalogo c \n"
                + "where concat(c.cat_codigo_grupo, c.cat_codigo_elemento)=sesefeasis_det_asistencia) ),\n"
                + "'NSTA', \n"
                + "(select c.cat_descripcion_elemento \n"
                + "from tb_catalogo c \n"
                + "where concat(c.cat_codigo_grupo, c.cat_codigo_elemento)=sesefeasis_det_asistencia))),' ',\n"
                + "if(isnull(time(sesefeasis_registro)),'-',time(sesefeasis_registro))) asistencia, \n"
                + "convert(sa.ses_fecha_inicio, time) inicio, \n"
                + "convert(sa.ses_fecha_fin, time) fin,  \n"
                + "tabla.sesefeasis_det_id \n"
                + "from cl_alumno a \n"
                + "inner join cl_matricula m on a.alu_id=m.alu_id \n"
                + "inner join cl_matricula_taller mt on mt.mat_id=m.mat_id \n"
                + "inner join cl_seccion s on s.sec_id=mt.sec_id \n"
                + "INNER JOIN cl_arbol_academico aTal ON(s.arb_id=aTal.arb_id)\n"
                + "INNER JOIN cl_arbol_academico aCur ON(aTal.arb_id_padre=aCur.arb_id)\n"
                + "INNER JOIN cl_arbol_academico aMod ON(aCur.arb_id_padre=aMod.arb_id)\n"
                + "inner join ac_sesion_asistencia sa on sa.sec_id=s.sec_id \n"
                + "inner join ac_docente d on d.doc_id=sa.doc_id \n"
                + "left join ( \n"
                + "select \n"
                + "sea.ses_id,\n"
                + "sea.sesefeasis_registro,\n"
                + "sead.alu_id,\n"
                + "(sead.sesefeasis_det_asistencia) sesefeasis_det_asistencia,\n"
                + "sead.sesefeasis_det_id \n"
                + "from ac_sesion_efectiva_asistencia sea \n"
                + "inner join ac_sesion_efectiva_asis_det_cl sead on (sea.sesefeasis_id = sead.sesefeasis_id) \n"
                + "where sea.sesefeasis_activo='1' \n"
                + "and sead.sesefeasis_det_activo='1' ) tabla on (tabla.ses_id= sa.ses_id AND tabla.alu_id = a.alu_id) \n"
                + "where m.mat_activo='1' \n"
                + "and m.mat_tipo='022001' \n"
                + "and s.sec_activo='1' \n"
                + "and aTal.arb_activo='1' \n"
                + "and aCur.arb_activo='1' \n"
                + "and aMod.arb_activo='1' \n"
                + "and sa.ses_tipo_asistencia='050003' \n"
                + "and s.sec_id=:v_sec_id  \n"
                + "and a.alu_id=:v_alu_id \n"
                + "order by sa.ses_fecha_registro,s.sec_nombre,inicio,nombres";

        try {
            sqlQuery = this.getSession().createSQLQuery( hqlQuery );
            sqlQuery.setInteger( "v_sec_id", iSecId );
            sqlQuery.setInteger( "v_alu_id", iAlumId );
            lstAux = sqlQuery.list();
            iSize = lstAux.size();

            lista = new ArrayList<BeanReporte>();
            for ( int i = 0; i < iSize; i++ ) {
                aobjFila = (Object[])lstAux.get( i );
                clAsis = new BeanReporte();
//                iAlumId = (Integer)aobjFila[1]; //AlumId
                clAsis.setExpr1( CommonWeb.parseObjToString( aobjFila[0] ) );//Id Sesion
                clAsis.setExpr2( CommonWeb.parseObjToString( aobjFila[1] ) );//Id del alumno
                clAsis.setExpr3( CommonWeb.parseObjToString( aobjFila[2] ) );//Codigo del alumno
                clAsis.setExpr4( CommonWeb.parseObjToString( aobjFila[3] ) );//Fecha Sesion
                clAsis.setExpr5( CommonWeb.parseObjToString( aobjFila[4] ) );//nombres del alumno
                clAsis.setExpr6( CommonWeb.parseObjToString( aobjFila[5] ) );//tipo asistencia
                /*
                 repAsist.setExpr5( aobjFila[4] + "" ); //tipo asistencia
                 repAsist.setExpr6( aobjFila[5] + "" );//hora de inicio de la sesion
                 repAsist.setExpr7( aobjFila[6] + "" );//hora de culminacion de la sesion
                 */
                lista.add( clAsis );

            }
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
        return lista;
    }
}
