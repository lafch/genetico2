/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.academica.hibernateSpringDao;

import com.mysql.jdbc.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import net.uch.commonService.ServiceFinder;
import net.uch.mapping.AcAlumno;
import net.uch.mapping.AcSisEvaDetalle;
import net.uch.mapping.AcSisEvaPersonalizado;
import net.uch.util.CommonWeb;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author LUIS
 */
public class HSAlumnoNotaDAOImpl extends HibernateDaoSupport implements HSAlumnoNotaDAO {

    @Override
    public List<AcAlumno> listarAlumnosPorCursoSisEvaPer( int sec_id, List<Integer> lstSisEvaPer, String etapa ) throws Exception {
        try {
            int i = 0;
            String sLisSisEvaPes;
            List objSivEvaPer, objNota, objAlumno;
            Object[] aobjFila;
            List<AcAlumno> alumnos = new ArrayList<AcAlumno>();

            String sql;
            String sql2;
            Statement st;
            ResultSet rs98;
            sLisSisEvaPes = "(";
            if ( lstSisEvaPer == null ) {

                sql = "SELECT sep.siseva_per_id,sep.siseva_per_peso FROM ac_seccion s\n"
                        + "INNER JOIN ac_curso_aperturado aape ON(s.curape_id=aape.curape_Id)\n"
                        + "INNER JOIN ac_sis_eva_personalizado sep ON(sep.curape_id=aape.curape_id)\n"
                        + "INNER JOIN ac_sis_eva_detalle sed ON(sep.siseva_detalle_id=sed.siseva_detalle_id)\n"
                        + "WHERE s.sec_activo=1 AND aape.curape_activo=1 AND sep.siseva_per_activo=1 AND s.sec_id=" + sec_id + "\n"
                        + "and sep.siseva_per_setapa='" + etapa + "'";
                Query q = this.getSession().createSQLQuery( sql );
                lstSisEvaPer = new ArrayList<Integer>();
                objSivEvaPer = q.list();
                for ( Object obj : objSivEvaPer ) {
                    aobjFila = (Object[])obj;
                    lstSisEvaPer.add( CommonWeb.parseObjToInt( aobjFila[0] ) );


                }
            }

            if ( lstSisEvaPer != null && !lstSisEvaPer.isEmpty() ) {
                for ( Integer integer : lstSisEvaPer ) {
                    i++;
                    sLisSisEvaPes += integer + ( i < lstSisEvaPer.size() ? "," : "" );

                }
                //iSisEvaDetId = traerSisEvaDetPorSisEvaPerId( lstSisEvaPer.get( 0 ) );
            }
            sLisSisEvaPes += ")";

            sql = "select \n"
                    + "ac_alumno.alu_id,\n"
                    + "concat(ac_alumno.alu_codigo,'') alu_codigo,\n"
                    + "concat(upper(trim(ac_alumno.alu_appaterno)),' ',upper(trim(ac_alumno.alu_apmaterno)),' ',upper(trim(ac_alumno.alu_nombres))) alumno \n"
                    + "from ac_alumno \n"
                    + "inner join ac_matricula on ac_matricula.alu_id=ac_alumno.alu_id \n"
                    + "inner join ac_matricula_curso on ac_matricula_curso.mat_id=ac_matricula.mat_id \n"
                    + "where ac_matricula_curso.matcur_activo='1' \n"
                    + "and ac_matricula.mat_activo='1' \n"
                    + "and ac_matricula.mat_tipo='022001' \n"
                    + "and ac_alumno.alu_activo='1' \n"
                    + "and ac_matricula_curso.sec_id=" + sec_id + " \n"
                    + "order by alumno";
            Query q = this.getSession().createSQLQuery( sql );
            int cont = 1;

            objSivEvaPer = q.list();
            for ( Object obj : objSivEvaPer ) {
                aobjFila = (Object[])obj;
                AcAlumno al = new AcAlumno();
                al.setId( CommonWeb.parseObjToInt( aobjFila[0] ) );
                al.setAluCodigo( CommonWeb.parseObjToString( aobjFila[1] ) );
                al.setV_nombreCompleto( CommonWeb.parseObjToString( aobjFila[2] ) );
                al.setV_nro_alu( cont );
                al.setV_lstNotas( new ArrayList<String>() );
                al.setV_lstSisEvaPersIds( lstSisEvaPer );
                if ( lstSisEvaPer != null && !lstSisEvaPer.isEmpty() ) {
                    sql2 = "select "
                            + "ac_nota.not_nota "
                            + " from ac_nota "
                            + " where ac_nota.not_activo='1' "
                            + " and ac_nota.sec_id=" + sec_id + " "
                            + "  and ac_nota.siseva_per_id in " + sLisSisEvaPes + " "
                            + "and ac_nota.alu_id=" + CommonWeb.parseObjToInt( aobjFila[0] ) + " "
                            + " order by siseva_per_id";
                    Query q2 = this.getSession().createSQLQuery( sql2 );
                    objNota = q2.list();
                    for ( int o = 0; o < lstSisEvaPer.size(); o++ ) {

                        if ( o < objNota.size() ) {
                            if ( objNota.get( o ) == null ) {
                                al.getV_lstNotas().add( "" );
                            } else {
                                al.getV_lstNotas().add( objNota.get( o ).toString() );
                            }
                        } else {
                            al.getV_lstNotas().add( "" );
                        }


                    }
                }
                alumnos.add( al );
                cont++;
            }

            return alumnos;
        } catch ( Exception e ) {
            e.printStackTrace();
            return new ArrayList<AcAlumno>();
        }
    }

    @Override
    public List<Integer> listarSisEvaPerPorEtapa( String sEtapaEvaluacion, int iSecId ) throws Exception {
        List<Integer> lstSisEvaPer = new ArrayList<Integer>();
        try {

            String sQuery;
            List objSivEvaPer;
            Object[] aobjFila;
            sQuery = "select sep.* from ac_sis_eva_personalizado sep \n"
                    + " INNER JOIN ac_sis_eva_detalle sed ON(sep.siseva_detalle_id=sed.siseva_detalle_id) \n"
                    + " INNER JOIN ac_seccion s ON(sep.curape_id=s.curape_id) \n"
                    + " WHERE s.sec_id=" + iSecId + " and sep.siseva_per_setapa='" + sEtapaEvaluacion + "'"
                    + " ORDER BY sep.siseva_per_orden";
            Query q2 = this.getSession().createSQLQuery( sQuery );
            objSivEvaPer = q2.list();
            for ( Object obj : objSivEvaPer ) {
                aobjFila = (Object[])obj;
                lstSisEvaPer.add( CommonWeb.parseObjToInt( aobjFila[0] ) );
            }
            //this.getConnection().close();
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
        return lstSisEvaPer;
    }

    @Override
    public List<AcSisEvaPersonalizado> listarSisEvaPorSeccion( int sec_id, String sEtapaEvaluacion ) throws Exception {
        try {
            List<AcSisEvaPersonalizado> sisevas = new ArrayList<AcSisEvaPersonalizado>();

            String sql;
            List objSivEvaPer;
            Object[] aobjFila;

            if ( sEtapaEvaluacion != null ) {
                sql = " select * from ( "
                        + "select ac_sis_eva_personalizado.siseva_per_id,\n" //0
                        + "ac_sis_eva_detalle.siseva_detalle_codigo,\n" //1
                        + "ac_sis_eva_detalle.siseva_detalle_id,\n" //2
                        + "ac_sis_eva_detalle.siseva_detalle_nombre,\n" //3
                        + "ac_sis_eva_detalle.siseva_detalle_peso,\n" //4
                        + "ac_sis_eva_detalle.siseva_detalle_susti,\n" //5
                        + "ac_sis_eva_detalle.siseva_detalle_tnota,\n" //6
                        + "ac_sis_eva_personalizado.siseva_per_nombre,\n" //7
                        + "ac_sis_eva_personalizado.siseva_per_exa_semana,\n" //8
                        + "ac_sis_eva_personalizado.siseva_per_peso,\n" //9
                        + "concat(ac_sis_eva_personalizado.siseva_codigo,'') siseva_codigo," //10
                        + "ac_sis_eva_personalizado.siseva_per_tnota," //11 
                        + "ac_sis_eva_personalizado.siseva_per_agrupar," //12
                        + "ac_sis_eva_personalizado.siseva_per_setapa \n" //13
                        + "from ac_sis_eva_personalizado \n"
                        + "inner join ac_sis_eva_detalle on ac_sis_eva_detalle.siseva_detalle_id=ac_sis_eva_personalizado.siseva_detalle_id \n"
                        + "inner join ac_sis_evaluacion on ac_sis_evaluacion.siseva_id=ac_sis_eva_detalle.siseva_id \n"
                        + "inner join ac_curso_aperturado on ac_curso_aperturado.siseva_id=ac_sis_evaluacion.siseva_id \n"
                        + "inner join ac_seccion on ac_seccion.curape_id=ac_curso_aperturado.curape_Id \n"
                        + "where ac_seccion.sec_activo='1' and ac_curso_aperturado.curape_activo='1' \n"
                        + "and ac_curso_aperturado.curape_aperturado='1' and ac_sis_evaluacion.siseva_activo='1' \n"
                        + "and ac_sis_evaluacion.siseva_vigente='1' and ac_sis_eva_detalle.siseva_detalle_activo='1' \n"
                        + "and ac_sis_eva_personalizado.siseva_per_activo='1' \n"
                        + "and ac_sis_eva_personalizado.curape_id=ac_curso_aperturado.curape_Id  \n"
                        + "and ac_seccion.sec_id=" + sec_id + " and ac_sis_eva_personalizado.siseva_per_setapa='" + sEtapaEvaluacion + "' ) t";

                Query q2 = this.getSession().createSQLQuery( sql );
                objSivEvaPer = q2.list();
                for ( Object obj : objSivEvaPer ) {
                    aobjFila = (Object[])obj;
                    AcSisEvaPersonalizado e = new AcSisEvaPersonalizado();
                    AcSisEvaDetalle detalle = new AcSisEvaDetalle();
                    e.setId( CommonWeb.parseObjToInt( aobjFila[0] ) );
                    e.setSisevaCodigo( CommonWeb.parseObjToString( aobjFila[10] ) );
                    e.setSisevaPerNombre( CommonWeb.parseObjToString( aobjFila[7] ) );
                    detalle.setSisevaDetalleTipoNota( CommonWeb.parseObjToString( aobjFila[6] ) );
                    detalle.setId( CommonWeb.parseObjToInt( aobjFila[2] ) );
                    detalle.setSisevaDetalleNombre( CommonWeb.parseObjToString( aobjFila[3] ) );
                    e.setSisevaDetalle( detalle );
                    e.setSisevaPerSemestreEtapa( CommonWeb.parseObjToString( aobjFila[13] ) );
                    e.setSisevaPerTipoNota( CommonWeb.parseObjToString( aobjFila[11] ) );
                    e.setSisevaPerAgrupar( CommonWeb.parseObjToString( aobjFila[12] ) );
                    sisevas.add( e );
                }
                return sisevas;
            } else {
                return sisevas;
            }
        } catch ( Exception e ) {
            e.printStackTrace();
            return new ArrayList<AcSisEvaPersonalizado>();
        }
    }

    @Override
    public int grabarNotasNuevo( List<AcAlumno> lstAlumnos, List<List<String>> lstAlumnosNotas, int iSecId, List<AcSisEvaPersonalizado> lstSisPer, int doc_id ) throws Exception {
        int cont;
        int contarVacios;
        int flag;
        int iNumAlum;
        int iNumNotas;
        int iSisEvaPerId;
        double dNota;
        AcAlumno alumno;
        List<String> lstFilaAlum;
        String sqlCommit;
        List objNota;
        Object[] aobjFila;

        if ( !lstAlumnos.isEmpty() && iSecId > 0 && !lstAlumnosNotas.isEmpty() && !lstSisPer.isEmpty() ) {

            ResultSet rs1;

            try {
                double promedio = 0;
                int contar = 0;
                double sumar = 0;

                flag = 0;
                cont = 0;
                contarVacios = 0;
                iNumAlum = lstAlumnos.size();

                sqlCommit = "START TRANSACTION";

                this.getSession().createSQLQuery( sqlCommit );

                for ( int i = 0; i < iNumAlum; i++ ) {
                    alumno = lstAlumnos.get( i );
                    lstFilaAlum = lstAlumnosNotas.get( i );
                    iNumNotas = alumno.getV_lstNotas().size();
                    for ( int j = 0; j <= iNumNotas - 1; j++ ) {
                        System.out.println( j );
                        if ( lstFilaAlum.get( j + 3 ) == null ) {
                            dNota = 99;
                        } else {
                            dNota = lstFilaAlum.get( j + 3 ).isEmpty() ? 99 : Double.parseDouble( lstFilaAlum.get( j + 3 ) );
                        }
                        if ( dNota > 20 ) {
                            cont++;
                        }
                    }
                    if ( cont > 9 ) {
                        contarVacios++;
                        cont = 0;
                    }
                }

                if ( iNumAlum == contarVacios ) {
                    //st_transac.close();
                    //this.getConnection().close();
                    return 0;
                } else {


                    for ( int i = 0; i < iNumAlum; i++ ) {

                        alumno = lstAlumnos.get( i );  //alumno
                        lstFilaAlum = lstAlumnosNotas.get( i ); // listado de notas de alumno

                        iNumNotas = alumno.getV_lstNotas().size();
                        //alumno.setV_lstNotas(lstFilaAlum);
                        String sql2 = "";
                        String nota_temp = "";
                        //System.out.println( "cantidad de alumnos-->" + i );
                        for ( int j = 0; j <= iNumNotas - 1; j++ ) {
                            try {
                                //System.out.println( "cantidad notas-->" + iNumNotas + "--->cantidad id nota" + j );
                                // System.out.println( "cantidad id nota"+j );
                                /*
                                 * filtra las notas de tipo agrupar - y que tengan asosciado un padre
                                 * es necesario establecer un orden de creacion 
                                 * Ejemplo NORMAL AGRUPAR AGRUPAR AGRUPAR PP NORMAL
                                 */
                                if ( lstSisPer.get( j ).getSisevaPerTipoNota().equals( "034007" ) && !lstSisPer.get( j ).getSisevaPerAgrupar().isEmpty() ) {

                                    try {
                                        dNota = lstFilaAlum.get( j + 3 ).isEmpty() ? 99 : Double.parseDouble( lstFilaAlum.get( j + 3 ) );
                                    } catch ( Exception ex ) {
                                        dNota = 99;
                                    }

                                    dNota = dNota >= 0 && dNota <= 20 ? dNota : 99;

                                    /*datos necesarios*/
                                    iSisEvaPerId = lstSisPer.get( j ).getId();

                                    /*historial de ingreso de notas*/
                                    nota_temp = "insert into ac_notas_temporales_uch(nottem_fecha,nottem_nota,siseva_per_id,alu_id,sec_id,usu_id,tipo_usuario) "
                                            + "values(now(),'" + dNota + "'," + iSisEvaPerId + "," + alumno.getId() + "," + iSecId + "," + doc_id + ",'052002')";

                                    SQLQuery query3 = this.getSession().createSQLQuery( nota_temp );
                                    query3.executeUpdate();

                                    /*ingreso de notas ac_nota*/
                                    /*verifica si existe la nota ingresada */

                                    String sqlAux2 = "SELECT not_id FROM ac_nota"
                                            + " WHERE sec_id=" + iSecId + " AND alu_id=" + alumno.getId()
                                            + " AND siseva_per_id=" + iSisEvaPerId + " AND not_activo=1";
                                    //System.out.println( doc_id + "------>" + "0" + " consulta" + sqlAux2 );
                                    //  this.conectar();
                                    //SQLQuery q2 = this.getSession().createSQLQuery( sqlAux2 );
                                    //objNota = q2.list();
                                    int idNota = 0;
                                    Object obj = this.getSession().createSQLQuery( sqlAux2 ).uniqueResult();
                                    if ( obj != null ) {
                                        idNota = Integer.parseInt( obj.toString() );
                                    }
                                    String sqlAux3;

//                                    for ( Object obj : objNota ) {
//                                        System.out.println( "consulta-->"+sqlAux2 );
//                                        aobjFila = (Object[])obj;
//                                        idNota = CommonWeb.parseObjToInt( aobjFila[0] );
//                                    }


                                    if ( idNota != 0 ) {

                                        if ( dNota != 99 ) {
                                            sqlAux3 = "update ac_nota set ac_nota.not_creacion=curdate(),ac_nota.not_nota="
                                                    + dNota + " where ac_nota.not_id=" + idNota;
                                            flag = 1;

                                        } else {
                                            sqlAux3 = "update ac_nota set ac_nota.not_creacion=curdate(), ac_nota.not_nota=NULL "
                                                    + " where ac_nota.not_id=" + idNota;
                                            flag = 1;

                                        }

                                    } else {
                                        if ( dNota != 99 ) {
                                            sqlAux3 = "insert into ac_nota(not_nota,not_observacion,siseva_per_id,alu_id,not_creacion,sec_id)"
                                                    + " values(" + dNota + ",''," + iSisEvaPerId + "," + alumno.getId() + ",curdate()," + iSecId + ")";
                                            flag = 1;

                                        } else {
                                            sqlAux3 = "insert into ac_nota(not_nota,not_observacion,siseva_per_id,alu_id,not_creacion,sec_id)"
                                                    + " values(NULL,''," + iSisEvaPerId + "," + alumno.getId() + ",curdate()," + iSecId + ")";
                                            flag = 1;

                                        }
                                    }

                                    SQLQuery query4 = this.getSession().createSQLQuery( sqlAux3 );
                                    query4.executeUpdate();

                                    if ( dNota != 99 ) {
                                        sumar = sumar + dNota;
                                        contar++;
                                    }
                                }

                                /*filtramos las notas de tipo promedio 
                                 * que tenga dependencia de otros para su evaluacion*/

                                if ( lstSisPer.get( j ).getSisevaPerTipoNota().equals( "034001" ) ) {

                                    promedio = sumar / contar;

                                    promedio = Math.round( promedio * 100 );
                                    promedio = promedio / 100;

                                    promedio = promedio >= 0 && promedio <= 20 ? promedio : 0.0;

                                    /*datos necesarios*/
                                    iSisEvaPerId = lstSisPer.get( j ).getId();

//                                  /*historial de ingreso de notas*/
                                    nota_temp = "insert into ac_notas_temporales_uch(nottem_fecha,nottem_nota,siseva_per_id,alu_id,sec_id,usu_id,tipo_usuario) "
                                            + "values(now(),'" + promedio + "'," + iSisEvaPerId + "," + alumno.getId() + "," + iSecId + "," + doc_id + ",'052002')";


                                    SQLQuery query3 = this.getSession().createSQLQuery( nota_temp );
                                    query3.executeUpdate();



                                    /*verifica si existe la nota ingresada */
                                    String sqlAux4;
                                    int idNota = 0;
                                    sqlAux4 = "SELECT * FROM ac_nota"
                                            + " WHERE sec_id=" + iSecId + " AND alu_id=" + alumno.getId()
                                            + " AND siseva_per_id=" + iSisEvaPerId + " AND not_activo=1";

                                    Query q2 = this.getSession().createSQLQuery( sqlAux4 );
                                    objNota = q2.list();

                                    //String sqlAux4;
                                    for ( Object obj : objNota ) {
                                        aobjFila = (Object[])obj;
                                        idNota = CommonWeb.parseObjToInt( aobjFila[0] );
                                    }

                                    if ( objNota.size() > 0 && idNota != 0 ) {
                                        sqlAux4 = "update ac_nota set ac_nota.not_creacion=curdate(),ac_nota.not_nota="
                                                + promedio + " where ac_nota.not_id=" + idNota;

                                        flag = 1;

                                    } else {
                                        sqlAux4 = "insert into ac_nota(not_nota,not_observacion,siseva_per_id,alu_id,not_creacion,sec_id)"
                                                + " values(" + promedio + ",''," + iSisEvaPerId + "," + alumno.getId() + ",curdate()," + iSecId + ")";
                                        flag = 1;

                                    }
                                    this.getSession().createSQLQuery( sqlAux4 );
                                    SQLQuery query = this.getSession().createSQLQuery( sqlAux4 );
                                    query.executeUpdate();

                                    sumar = 0;
                                    contar = 0;
                                }

                                /* filtrando todos los datos que no son  tipo agrupar o promedio */
                                if ( !lstSisPer.get( j ).getSisevaPerTipoNota().equals( "034001" ) && !lstSisPer.get( j ).getSisevaPerTipoNota().equals( "034007" ) && !lstSisPer.get( j ).getSisevaPerTipoNota().equals( "034002" ) ) {

                                    try {
                                        dNota = lstFilaAlum.get( j + 3 ).isEmpty() ? 99 : Double.parseDouble( lstFilaAlum.get( j + 3 ) );
                                    } catch ( Exception ex ) {
                                        dNota = 99;
                                    }
                                    dNota = dNota >= 0 && dNota <= 20 ? dNota : 99;

                                    /*datos necesarios*/
                                    iSisEvaPerId = lstSisPer.get( j ).getId();

                                    /*historial de ingreso de notas*/
                                    nota_temp = "insert into ac_notas_temporales_uch(nottem_fecha,nottem_nota,siseva_per_id,alu_id,sec_id,usu_id,tipo_usuario) "
                                            + "values(now(),'" + dNota + "'," + iSisEvaPerId + "," + alumno.getId() + "," + iSecId + "," + doc_id + ",'052002')";
                                    SQLQuery query = this.getSession().createSQLQuery( nota_temp );
                                    query.executeUpdate();


                                    /*verifica si existe la nota ingresada */
                                    String sqlAux5;
                                    sqlAux5 = "SELECT * FROM ac_nota"
                                            + " WHERE sec_id=" + iSecId + " AND alu_id=" + alumno.getId()
                                            + " AND siseva_per_id=" + iSisEvaPerId + " AND not_activo=1";
                                    Query q2 = this.getSession().createSQLQuery( sqlAux5 );
                                    objNota = q2.list();
                                    int idNota = 0;

                                    for ( Object obj : objNota ) {
                                        aobjFila = (Object[])obj;
                                        idNota = CommonWeb.parseObjToInt( aobjFila[0] );
                                    }
                                    if ( objNota.size() > 0 && idNota != 0 ) {
                                        if ( dNota != 99 ) {
                                            sqlAux5 = "update ac_nota set ac_nota.not_creacion=curdate(),ac_nota.not_nota="
                                                    + dNota + " where ac_nota.not_id=" + idNota;
                                            flag = 1;

                                        } else {
                                            sqlAux5 = "update ac_nota set ac_nota.not_creacion=curdate(), ac_nota.not_nota=NULL "
                                                    + " where ac_nota.not_id=" + idNota;
                                            flag = 1;

                                        }
                                    } else {
                                        if ( dNota != 99 ) {
                                            sqlAux5 = "insert into ac_nota(not_nota,not_observacion,siseva_per_id,alu_id,not_creacion,sec_id)"
                                                    + " values(" + dNota + ",''," + iSisEvaPerId + "," + alumno.getId() + ",curdate()," + iSecId + ")";
                                            flag = 1;

                                        } else {
                                            sqlAux5 = "insert into ac_nota(not_nota,not_observacion,siseva_per_id,alu_id,not_creacion,sec_id)"
                                                    + " values(NULL,''," + iSisEvaPerId + "," + alumno.getId() + ",curdate()," + iSecId + ")";
                                            flag = 1;

                                        }

                                    }


                                    SQLQuery query2 = this.getSession().createSQLQuery( sqlAux5 );
                                    query2.executeUpdate();

                                }

                                if ( flag > 0 ) {
                                    iSisEvaPerId = lstSisPer.get( j ).getId();
                                    String sqlAux6;
                                    sqlAux6 = "select "
                                            + "ac_importacion_notas.impnot_id "
                                            + "from ac_importacion_notas "
                                            + "where ac_importacion_notas.impnot_activo='1' "
                                            + "and ac_importacion_notas.sec_id=" + iSecId + " "
                                            + "and ac_importacion_notas.siseva_per_id=" + iSisEvaPerId;

                                    Query q2 = this.getSession().createSQLQuery( sqlAux6 );
                                    objNota = q2.list();


                                    if ( objNota.size() == 0 ) {
                                        sqlAux6 = "insert into ac_importacion_notas(sec_id,siseva_per_id,impnot_estado) "
                                                + "values(" + iSecId + "," + iSisEvaPerId + ",'051002')";

                                        SQLQuery query = this.getSession().createSQLQuery( sqlAux6 );
                                        query.executeUpdate();

                                    }
//                                   

                                }
                            } catch ( Exception ex ) {
                                System.out.println( "error" + ex );
                                dNota = 0.0;
                            }
                        }
                    }
                    sqlCommit = "COMMIT";
                    this.getSession().createSQLQuery( sqlCommit );
                    // st_transac2.execute( sqlCommit );

                    return 1;


                }
            } catch ( Exception ex ) {
                sqlCommit = "ROLLBACK";
                this.getSession().createSQLQuery( sqlCommit );
                return 0;
            }

        } else {

            return 0;
        }
    }

    @Override
    public int recalcularPromedio( List<AcAlumno> lstAlumnos, int iSecId, List<AcSisEvaPersonalizado> lstSisPer, int doc_id ) throws Exception {
        int iSizeSisEvDet;
        int iSizeAlum;
        Double dProm = 99.0;
        String sQuery;
        String sSisEvaDetIds;
        int sSisEvaProm = 0;
        String etapa = "";
        AcAlumno alumno;
        List objNota;
        Object[] aobjFila;
        try {


            iSizeSisEvDet = lstSisPer.size();
            sSisEvaDetIds = "(";
            for ( int i = 0; i < iSizeSisEvDet; i++ ) {
                if ( "034005".equals( lstSisPer.get( i ).getSisevaPerTipoNota() )
                        || "034001".equals( lstSisPer.get( i ).getSisevaPerTipoNota() )
                        || "034006".equals( lstSisPer.get( i ).getSisevaPerTipoNota() ) ) {
                    sSisEvaDetIds += lstSisPer.get( i ).getId();
                    sSisEvaDetIds += ",";
                    etapa = lstSisPer.get( i ).getSisevaPerTipoNota();
                }
                if ( "034002".equals( lstSisPer.get( i ).getSisevaPerTipoNota() ) ) {
                    sSisEvaProm = lstSisPer.get( i ).getId();
                }
            }
            sSisEvaDetIds = sSisEvaDetIds.substring( sSisEvaDetIds.length() - 1 ).equals( "," ) ? sSisEvaDetIds.substring( 0, sSisEvaDetIds.length() - 1 ) : sSisEvaDetIds;
            sSisEvaDetIds += ")";
            iSizeAlum = lstAlumnos.size();
            for ( int i = 0; i < iSizeAlum; i++ ) {
                alumno = lstAlumnos.get( i );


                sQuery = " SELECT cast(sum(prom*peso)/sum(peso) as decimal) prom_total from (\n"
                        + " SELECT sed.siseva_detalle_id,sed.siseva_detalle_nombre, n.not_nota prom, \n"
                        + " sep.siseva_per_peso peso  \n"
                        + " FROM ac_seccion s  \n"
                        + " INNER JOIN ac_curso_aperturado aape ON(s.curape_id=aape.curape_id) \n"
                        + " INNER JOIN ac_sis_evaluacion se ON(aape.siseva_id=se.siseva_id) \n"
                        + " INNER JOIN ac_sis_eva_detalle sed ON(se.siseva_id=sed.siseva_id) \n"
                        + " INNER JOIN ac_sis_eva_personalizado sep ON(sed.siseva_detalle_id=sep.siseva_detalle_id) \n"
                        + " INNER JOIN ac_nota n ON(sep.siseva_per_id=n.siseva_per_id AND s.sec_id=n.sec_id) \n"
                        + " WHERE s.sec_activo=1 AND aape.curape_activo=1 AND se.siseva_activo=1 AND sed.siseva_detalle_activo=1 \n"
                        + " AND sep.siseva_per_activo=1 AND n.not_activo=1 AND s.sec_id=" + iSecId + "  AND n.alu_id=" + alumno.getId() + " "
                        + " AND sep.siseva_per_id IN " + sSisEvaDetIds + " \n"
                        + " GROUP BY sep.siseva_detalle_id\n"
                        + " )tabla\n";

                // Query q2 = this.getSession().createSQLQuery( sQuery );

                Object obj = this.getSession().createSQLQuery( sQuery ).uniqueResult();
                dProm = Double.parseDouble( obj.toString() );
                //objNota = q2.list();


//                for ( Object obj : objNota ) {
//                    aobjFila = (Object[])obj;
//                    dProm = CommonWeb.parseObjToDouble( aobjFila[0] );
//                }

                if ( dProm != 99.0 ) {
                    actualizarPromedio( iSecId, alumno.getId(), dProm, etapa, doc_id, sSisEvaProm );
                }
            }
            return 1;
        } catch ( Exception ex ) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public void actualizarPromedio( int iSecId, int iAluId, double dNota, String etapa, int doc_id, int sSisEvaProm ) throws Exception {
        String sQuery;
        String sqlH;
        int iIdNotaProm = 0;
        int idNotTemp = 0;
        Integer iSisEvaPerId = 0;
        String nota_temp = "";
        List objNota;
        Object[] aobjFila;

        if ( dNota != 99 ) {

            iSisEvaPerId = sSisEvaProm;

            if ( iSisEvaPerId != 0 ) {

                /*historial de ingreso de notas*/
                nota_temp = "insert into ac_notas_temporales_uch(nottem_fecha,nottem_nota,siseva_per_id,alu_id,sec_id,usu_id,tipo_usuario) "
                        + "values(now(),'" + dNota + "'," + iSisEvaPerId + "," + iAluId + "," + iSecId + "," + doc_id + ",'052002')";

                SQLQuery query = this.getSession().createSQLQuery( nota_temp );
                query.executeUpdate();


                /*ingreso de notas temporales*/
                sqlH = "select "
                        + "ac_notas_temporales.nottem_id,"
                        + "ac_notas_temporales.nottem_nota,"
                        + "ac_notas_temporales.nottem_fecha "
                        + "from ac_notas_temporales "
                        + "where ac_notas_temporales.nottem_activo='1' "
                        + "and ac_notas_temporales.siseva_per_id=" + iSisEvaPerId + " "
                        + "and ac_notas_temporales.sec_id=" + iSecId + " "
                        + "and ac_notas_temporales.alu_id=" + iAluId + " "
                        + "order by ac_notas_temporales.nottem_fecha desc "
                        + "limit 0,1";

                Query q2 = this.getSession().createSQLQuery( sqlH );
                objNota = q2.list();



                if ( objNota.size() > 0 ) {

                    for ( Object obj : objNota ) {
                        aobjFila = (Object[])obj;
                        idNotTemp = CommonWeb.parseObjToInt( aobjFila[0] );
                    }

                    sqlH = "update ac_notas_temporales "
                            + "set "
                            + "ac_notas_temporales.nottem_fecha=now(),"
                            + "ac_notas_temporales.nottem_nota='" + dNota + "' "
                            + "where ac_notas_temporales.nottem_id=" + idNotTemp;

                } else {

                    sqlH = "insert into ac_notas_temporales(nottem_fecha,nottem_nota,siseva_per_id,alu_id,sec_id) "
                            + "values(now(),'" + dNota + "'," + iSisEvaPerId + "," + iAluId + "," + iSecId + ")";
                }

                SQLQuery query2 = this.getSession().createSQLQuery( sqlH );
                query2.executeUpdate();





                iIdNotaProm = obtenerIdNotaPromedio( iSecId, iAluId, iSisEvaPerId );
                if ( iIdNotaProm > 0 ) {
                    sQuery = "update ac_nota set not_nota= " + dNota + " where not_id=" + iIdNotaProm;
                } else {
                    sQuery = "insert into ac_nota(not_nota,not_observacion,siseva_per_id,alu_id,not_creacion,sec_id) "
                            + "values(" + dNota + ",''," + iSisEvaPerId + "," + iAluId + ",curdate()," + iSecId + ")";
                }
                SQLQuery query3 = this.getSession().createSQLQuery( sQuery );
                query3.executeUpdate();

            }

        }
    }

    @Override
    public int obtenerIdNotaPromedio( int iSecId, int iAluId, int iSisEvaPerId ) throws Exception {
        int iNotId = 0;
        String sQuery;

        sQuery = "SELECT n.not_id FROM ac_nota n WHERE n.alu_id=" + iAluId + " AND n.sec_id="
                + iSecId + " AND n.siseva_per_id=" + iSisEvaPerId + " AND n.not_activo=1";

        Object obj = this.getSession().createSQLQuery( sQuery ).uniqueResult();
        if ( obj != null ) {
            iNotId = CommonWeb.parseObjToInt( obj.toString() );
        }


        return iNotId;
    }

    @Override
    public void calcularPromedioConsutitutorio( int sec_id ) throws Exception {
        Connection con = null;
        com.mysql.jdbc.CallableStatement cs = null;
        DriverManagerDataSource dmds = (DriverManagerDataSource) ServiceFinder.findBean("dataSource");
        java.sql.Connection cn = dmds.getConnection();
        try 
        {
            cs = (com.mysql.jdbc.CallableStatement) cn.prepareCall("call sp_genera(?)");
            // Cargamos los parametros de entrada
            cs.setInt(1, sec_id);
            // Ejecutamos
            cs.execute();
            cs.close();
        } 
        catch (Exception e) 
        {
            //throw new Exception(e); //Propagamos la Excepcion
        } 
        finally 
        {
            if (cs != null) //Finalizamos cerrando el CallableStatement
            {
                cs.close();
            }
            if (con != null) //Finalizamos cerrando la conexion
            {
                con.close();
            }
        }
    }

    @Override
    public void generarPromedioN( int sec_id ) {
        int var_act_id = 0;
        List objActa, objActaD;
        Object[] aobjFila;

        try {
            String sql = "select * from ac_acta where act_activo='1' and act_tipo='036001' and sec_id=" + sec_id;
            Query q2 = this.getSession().createSQLQuery( sql );
            objActa = q2.list();


            if ( objActa.size() > 0 ) {
                for ( Object obj : objActa ) {
                    aobjFila = (Object[])obj;
                    var_act_id = CommonWeb.parseObjToInt( aobjFila[0] );
                }
            } else {
                sql = "insert into ac_acta(sec_id,act_creacion,act_activo,act_tipo) values \n"
                        + "(" + sec_id + ",curdate(),'1','036001')";
                SQLQuery query3 = this.getSession().createSQLQuery( sql );
                query3.executeUpdate();

                Query q3 = this.getSession().createSQLQuery( "select act_id from ac_acta where act_activo='1' and act_tipo='036001' and sec_id=" + sec_id );
                var_act_id = CommonWeb.parseObjToInt( q3.uniqueResult().toString() );

            }


            sql = "select  ed.siseva_detalle_id,n.alu_id, n.not_nota from\n"
                    + "ac_nota n \n"
                    + "inner join ac_sis_eva_personalizado evp on n.siseva_per_id=evp.siseva_per_id\n"
                    + "inner join ac_sis_eva_detalle ed on evp.siseva_detalle_id=ed.siseva_detalle_id\n"
                    + "where n.not_activo='1' and ed.siseva_detalle_activo='1' and evp.siseva_per_activo='1'\n"
                    + "and n.sec_id=" + sec_id + " and evp.siseva_per_tnota='034002'\n"
                    + "group by ed.siseva_detalle_id,n.alu_id\n"
                    + "order by n.alu_id";
            Query q4 = this.getSession().createSQLQuery( sql );
            objActa = q4.list();


            if ( objActa.size() > 0 ) {
                int aluId = 0, detId = 0;
                double nota=0;
                for ( Object obj : objActa ) {
                    aobjFila = (Object[])obj;
                    detId = CommonWeb.parseObjToInt( aobjFila[0] );
                    aluId = CommonWeb.parseObjToInt( aobjFila[1] );
                    nota = CommonWeb.parseObjToDouble(aobjFila[2] );
                    String sqlN = "select * from ac_acta_detalle where actdet_tipo_nota='034001' and act_id= " + var_act_id + " "
                            + "and alu_id=" + aluId + " and siseva_detalle_id=" + detId + " and actdet_activo='1' ";

                    Query q5 = this.getSession().createSQLQuery( sqlN );
                    objActaD = q5.list();
                    
                        int detIdE = 0;
                        for ( Object obj2 : objActaD ) {
                            aobjFila = (Object[])obj2;
                            detIdE = CommonWeb.parseObjToInt( aobjFila[0] );
                        }
                        if(detIdE!=0){
                        sql = "update ac_acta_detalle set actdet_nota='" + nota + "' where actdet_id=" + detIdE;
                        SQLQuery query = this.getSession().createSQLQuery( sql );
                        query.executeUpdate();

                        } else {

                        sql = "insert into ac_acta_detalle(act_id,actdet_tipo_nota,siseva_detalle_id,actdet_nota,alu_id,actdet_activo) values \n"
                                + "(" + var_act_id + ",'034001'," + detId + "," + nota + "," + aluId + ",'1')";

                        SQLQuery query2 = this.getSession().createSQLQuery( sql );
                        query2.executeUpdate();
                        }
                    
                   
                }
            }

            String sisEva = "select count(*) cantidad \n"
                    + "from ac_sis_eva_personalizado inner join ac_curso_aperturado on ac_sis_eva_personalizado.curape_id=ac_curso_aperturado.curape_Id \n"
                    + "inner join ac_seccion on ac_seccion.curape_id=ac_curso_aperturado.curape_Id \n"
                    + "where ac_sis_eva_personalizado.siseva_per_activo='1' \n"
                    + "and ac_curso_aperturado.curape_aperturado='1' \n"
                    + "and ac_curso_aperturado.curape_activo='1' \n"
                    + "and ac_seccion.sec_activo='1' "
                    + "and ac_sis_eva_personalizado.siseva_per_tnota='034002' \n"
                    + "and ac_seccion.sec_id=" + sec_id;
            Query q = this.getSession().createSQLQuery( sisEva ).
                    addScalar( "cantidad", Hibernate.INTEGER );
            int cantiEva = Integer.parseInt( q.uniqueResult().toString() );


            String sqlPro = "select  a.act_id,alu_id, round(CAST(sum(actdet_nota) as DECIMAL)/" + cantiEva + ") as nota \n"
                    + "from ac_acta a inner join ac_acta_detalle ad  on a.act_id=ad.act_id \n"
                    + "where sec_id=" + sec_id + " and actdet_tipo_nota='034001' and ad.actdet_activo='1' and act_tipo='036001' \n"
                    + "group by alu_id";


            Query q5 = this.getSession().createSQLQuery( sqlPro );
            objActa = q5.list();

            if ( objActa.size() > 0 ) {

                int aluId = 0, actId = 0, nota = 0;
                for ( Object obj : objActa ) {
                    aobjFila = (Object[])obj;
                    actId = CommonWeb.parseObjToInt( aobjFila[0] );
                    aluId = CommonWeb.parseObjToInt( aobjFila[1] );
                    nota = CommonWeb.parseObjToInt( aobjFila[2] );


                    String sqlPro2 = "select * from ac_acta_detalle where actdet_tipo_nota='034002' and act_id=" + actId
                            + " and alu_id=" + aluId + " and siseva_detalle_id=0 and actdet_activo='1'";

                    Query q6 = this.getSession().createSQLQuery( sqlPro2 );
                    objActaD = q6.list();


                    
                        int detIdE = 0;
                        for ( Object obj2 : objActaD ) {
                            aobjFila = (Object[])obj2;
                            detIdE = CommonWeb.parseObjToInt( aobjFila[0] );
                        }

                        if ( detIdE != 0 ) {

                            String sqlAct = "update ac_acta_detalle set actdet_nota='" + nota + "' where actdet_id=" + detIdE;

                            SQLQuery query = this.getSession().createSQLQuery( sqlAct );
                            query.executeUpdate();
                        } else {
                            //System.out.println("paso 1.9");
                            String sqlIns = "insert into ac_acta_detalle "
                                    + "(act_id,actdet_tipo_nota,siseva_detalle_id,actdet_nota,alu_id,actdet_activo) values "
                                    + "(" + actId + ",'034002',0,'" + nota + "'," + aluId + ",'1')";

                            SQLQuery query3 = this.getSession().createSQLQuery( sqlIns );
                            query3.executeUpdate();
                        }
                    
                }
            }
        } catch ( Exception e ) {
            System.out.println( "error en la class promedio *->" + e );
        }

    }
}
