/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.hibernateSpringDao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.uch.cursoLibre.managedBeans.beans.BeanReporte;
import net.uch.mapping.ClMatriculaTaller;
import net.uch.mapping.ClSeccion;
import net.uch.mapping.ClSeccionGrupo;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author cesardl
 */
public class HSSeccionCLDAOImpl extends HibernateDaoSupport implements HSSeccionCLDAO {

    @Override
    public ClSeccion seleccionarSeccion( Integer sec_id ) {
        return (ClSeccion)this.getSession().load( ClSeccion.class, sec_id );
    }

    @Override
    public List<ClSeccion> seleccionarSecciones( int talape_id ) {
        Criteria c = this.getSession().createCriteria( ClSeccion.class ).
                add( Restrictions.eq( "secActivo", "1" ) ).
                add( Restrictions.eq( "clArbolAperturado.arbapeId", talape_id ) ).
                addOrder( Order.desc( "secFinicio" ) );
        return c.list();
    }
    
    @Override
    public List<ClSeccion> seleccionarAsignaturas( Integer sec_id ) {
        Criteria c = this.getSession().createCriteria( ClSeccion.class ).
                add( Restrictions.eq( "secActivo", "1" ) ).
                add( Restrictions.eq( "secIdPadre", sec_id ) ).
                addOrder( Order.desc( "secNombre" ) );
        return c.list();
    }

    @Override
    public List<ClSeccion> seleccionarSeccionesxTaller( int talid ) {
        Criteria c = this.getSession().createCriteria( ClSeccion.class ).
                add( Restrictions.eq( "secActivo", "1" ) ).
                add( Restrictions.eq( "clArbolAcademico.arbId", talid ) ).
                addOrder( Order.desc( "secFinicio" ) );
        return c.list();
    }

    @Override
    public ClSeccion obtenerSeccion( int matId, int talId ) {
        Criteria c = this.getSession().createCriteria( ClSeccion.class, "sec" ).
                createCriteria( "sec.clMatriculaTallers", "mattal" ).
                createCriteria( "sec.clArbolAperturado", "talape" ).
                add( Restrictions.eq( "sec.secActivo", "1" ) ).
                add( Restrictions.eq( "mattal.mattalActivo", "1" ) ).
                add( Restrictions.eq( "talape.arbapeActivo", "1" ) ).
                add( Restrictions.eq( "mattal.clMatricula.matId", matId ) ).
                add( Restrictions.eq( "talape.clArbolAcademico.arbId", talId ) );

        return (ClSeccion)c.uniqueResult();
    }

    @Override
    public List<ClSeccion> listarSeccionXTaller( int iArbTallId ) {
        return this.getSession().createCriteria( ClSeccion.class, "sec" ).
                createCriteria( "sec.clArbolAperturado", "talape" ).
                createCriteria( "talape.clArbolAcademico", "tal" ).
                add( Restrictions.eq( "talape.arbapeActivo", "1" ) ).
                add( Restrictions.eq( "talape.arbapeVigente", "1" ) ).
                add( Restrictions.eq( "talape.arbapeAperturado", "1" ) ).
                add( Restrictions.eq( "tal.arbActivo", "1" ) ).
                add( Restrictions.eq( "tal.arbId", iArbTallId ) ).
                add( Restrictions.eq( "sec.secActivo", "1" ) ).
                add( Restrictions.le( "sec.secFinicioMatricula", new Date() ) ).
                add( Restrictions.ge( "sec.secFfinMatricula", new Date() ) ).
                list();
    }

    @Override
    public List<ClSeccion> listarSeccionesXModulo( int arbId ) {
        Criteria criteria;
        criteria = this.getSession().
                createCriteria( ClSeccion.class, "clSeccion" ).
                createCriteria( "clSeccion.clInicio", "clInicio" ).
                createCriteria( "clInicio.clArbolAcademico", "clArbolAcademico" ).
                add( Restrictions.eq( "clSeccion.secActivo", "1" ) ).
                add( Restrictions.eq( "clArbolAcademico.arbActivo", "1" ) ).
                add( Restrictions.eq( "clInicio.iniActivo", "1" ) ).
                add( Restrictions.eq( "clArbolAcademico.arbId", arbId ) ).
                add( Restrictions.le( "clSeccion.secFinicioMatricula", new Date() ) ).
                add( Restrictions.ge( "clSeccion.secFfinMatricula", new Date() ) );
        return criteria.list();
    }

    @Override
    public void insertarSeccion( ClSeccion sec ) {
        this.getHibernateTemplate().save( sec );
    }

    @Override
    public void actualizarSeccion( ClSeccion sec ) {
        this.getHibernateTemplate().merge( sec );
    }

    @Override
    public void eliminarSeccion( int sec_id ) {
        String hqlUpdate = "update ClSeccion set secActivo = '0' where secId = :sec_id";
        this.getSession().createQuery( hqlUpdate ).setString( "sec_id", "" + sec_id ).executeUpdate();
    }

    @Override
    public boolean existenMatriculasSeccion( int sec_id ) {
        return !this.getSession().createCriteria( ClMatriculaTaller.class, "ClMatriculaTaller" ).
                createCriteria( "ClMatriculaTaller.clMatricula", "ClMatricula" ).
                add( Restrictions.eq( "ClMatricula.matActivo", "1" ) ).
                add( Restrictions.eq( "ClMatriculaTaller.mattalActivo", "1" ) ).
                add( Restrictions.eq( "ClMatriculaTaller.clSeccion.secId", sec_id ) ).
                list().isEmpty();
    }

    @Override
    public List listarSeccionesModulo( int arb_id ) {
        List lista = new ArrayList();
        String hqlQuery = "select clSeccion.secId, clSeccion.secNombre, clSeccion.secFinicio, clSeccion.secFfin, "
                + "clArbolAcademico.arbDescripcion "
                + "from ClSeccion as clSeccion inner join clSeccion.clArbolAcademico as clArbolAcademico "
                + "where clSeccion.secActivo='1' "
                + "and clArbolAcademico.arbActivo='1' "
                + "and clArbolAcademico.arbId= :v_arb_id "
                + "group by clSeccion.secId "
                + "order by clSeccion.secFinicio";

        lista = this.getSession().createQuery( hqlQuery ).setInteger( "v_arb_id", arb_id ).list();
        return lista;
    }

    @Override
    public List listarcantidadadMatriculadosxSeccion( int arb_id, int cur_id, int anio, int mes ) {
        List lista = new ArrayList();
        /*
         * 0 sec_id 1 sec_nombre 2 sec_fecha_ini 3 sec_fecha_fin, 4 nombre_curso
         * 5 cantidad matri
         */
        String hqlQuery = "select clSeccion.secId, clSeccion.secNombre, clSeccion.secFinicio, clSeccion.secFfin, "
                + "clArbolAcademico1.arbDescripcion, count(clSeccion) "
                + "from ClMatriculaTaller clMatriculaTaller "
                + "inner join clMatriculaTaller.clSeccion as clSeccion "
                + "inner join clMatriculaTaller.clMatricula as clMatricula "
                + "inner join clSeccion.clArbolAcademico as clArbolAcademico1 "
                + "inner join clArbolAcademico1.clArbolAcademico as clArbolAcademico2 "
                + "where clSeccion.secActivo='1' "
                + "and clMatricula.matTipo='022001' "
                + "and clMatricula.matActivo='1' "
                + "and clArbolAcademico1.arbActivo='1' "
                + "and clArbolAcademico2.arbActivo='1' "
                + "and year(clSeccion.secFinicio)=" + anio + " "
                + "and clArbolAcademico2.arbId= :v_arb_id ";
        if ( cur_id > 0 ) {
            hqlQuery = hqlQuery + " and clArbolAcademico1.arbId=" + cur_id + " ";
        }
        if ( mes > 0 ) {
            hqlQuery = hqlQuery + " and month(clSeccion.secFinicio)=" + mes + " ";
        }

        hqlQuery = hqlQuery + "group by clSeccion.secId "
                + " order by clSeccion.secFinicio";
        lista = this.getSession().createQuery( hqlQuery ).setInteger( "v_arb_id", arb_id ).list();
        return lista;

    }

    public void guardarSecciones( List<ClSeccion> secciones ) {
        this.getHibernateTemplate().saveOrUpdateAll( secciones );
    }

    public List<ClSeccion> listarSeccionesPorInicio( int ini_id ) {
        List<ClSeccion> lista = this.getSession().createCriteria( ClSeccion.class, "clSeccion" ).add( Restrictions.eq( "clSeccion.secActivo", "1" ) ).add( Restrictions.eq( "clSeccion.clInicio.iniId", ini_id ) ).addOrder( Order.desc( "clSeccion.secFinicio" ) ).list();
        return lista;
    }

    @Override
    public List<ClSeccion> listarTodasSeccionesXCurso( int arbId ) {
        Criteria criteria;

        Calendar fecha1 = Calendar.getInstance();
        Calendar fecha2 = Calendar.getInstance();
        fecha1.setTime( new Date() );
        fecha1.add( fecha1.DAY_OF_MONTH, 60 );
        fecha2.setTime( new Date() );
        fecha2.add( fecha2.DAY_OF_MONTH, -180 );
        DateFormat format = new SimpleDateFormat( "yyyy-MM-dd" );
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
        String TfechaI = format.format( fecha2.getTime() );
        String TfechaF = format.format( fecha1.getTime() );
        Date fechaI = null;
        Date fechaF = null;
        try {
            fechaI = sdf.parse( TfechaI );
            fechaF = sdf.parse( TfechaF );
        } catch ( ParseException ex ) {
            Logger.getLogger( HSSeccionCLDAOImpl.class.getName() ).log( Level.SEVERE, null, ex );
        }

        criteria = this.getSession().
                createCriteria( ClSeccion.class, "clSeccion" ).
                createCriteria( "clSeccion.clArbolAcademico", "clArbolAcademico" ).
                add( Restrictions.eq( "clSeccion.secActivo", "1" ) ).
                add( Restrictions.eq( "clArbolAcademico.arbIdPadre", arbId ) ).
                add( Restrictions.eq( "clArbolAcademico.arbActivo", "1" ) ).
                add( Restrictions.between( "clSeccion.secFinicio", fechaI, fechaF ) );
        return criteria.list();

    }

    @Override
    public List<ClSeccion> listarSeccionesXCurso( int arbId ) {
        Criteria criteria;
        criteria = this.getSession().
                createCriteria( ClSeccion.class, "clSeccion" ).
                createCriteria( "clSeccion.clArbolAcademico", "clArbolAcademico" ).
                add( Restrictions.eq( "clSeccion.secActivo", "1" ) ).
                add( Restrictions.ge( "clSeccion.secFfinMatricula", new Date() ) ).
                add( Restrictions.eq( "clArbolAcademico.arbId", arbId ) ).
                add( Restrictions.eq( "clArbolAcademico.arbActivo", "1" ) );

        return criteria.list();
    }

    @Override
    public List listarSeccionesXDocente( int docId ) {
        int iSizeSec;
        BeanReporte secAux1;
        BeanReporte secAux2;
        ArrayList<BeanReporte> lstSecciones = new ArrayList<BeanReporte>();
        List<BeanReporte> lstSecAux;
        Map<Integer, BeanReporte> hmGrupSec;


        hmGrupSec = new HashMap<Integer, BeanReporte>();
        lstSecAux = listarSeccionesPorDocAgrupNoAgrup( 1, docId );

        iSizeSec = lstSecAux.size();
        for ( int i = 0; i < iSizeSec; i++ ) {
            secAux1 = lstSecAux.get( i );
//            secAux2 = hmGrupSec.get( secAux1.getSec_grup_id() );
            secAux2 = hmGrupSec.get( Integer.parseInt( secAux1.getExpr9() ) );
            if ( secAux2 == null ) {

                secAux1.setExpr1( "-" + secAux1.getExpr1() );//Carrera
                secAux1.setExpr2( "-" + secAux1.getExpr2() );//cursonombre
                secAux1.setExpr5( "-" + secAux1.getExpr5() );//secccion
                hmGrupSec.put( Integer.parseInt( secAux1.getExpr9() ), secAux1 );
            } else {
                secAux2.setExpr1( secAux2.getExpr1() + "<br />-" + secAux1.getExpr1() );
                secAux2.setExpr2( secAux2.getExpr2() + "<br />-" + secAux1.getExpr2() );
                secAux2.setExpr5( secAux2.getExpr5() + "<br />-" + secAux1.getExpr5() );
            }
        }

        lstSecciones.addAll( hmGrupSec.values() );
        lstSecciones.addAll( listarSeccionesPorDocAgrupNoAgrup( 2, docId ) );

        int iCont = 1;
        for ( BeanReporte cb : lstSecciones ) {
            cb.setExpr10( String.valueOf( iCont++ ) );
        }

        return lstSecciones;
    }

    private List<BeanReporte> listarSeccionesPorDocAgrupNoAgrup( int iAgrup, int iDocId ) {//blAgrup : 0=Ambos / 1=Agrup / 2=No Agrup
        String sAgrup;
        List lista = new ArrayList();
        ArrayList<BeanReporte> listaCurso;
        //AND ISNULL(cl_seccion.sec_grup_id )
        listaCurso = new ArrayList<BeanReporte>();
        switch ( iAgrup ) {
            case 1:
                sAgrup = " AND NOT ISNULL(cl_seccion.sec_grup_id ) ";
                break;
            case 2:
                sAgrup = " AND ISNULL(cl_seccion.sec_grup_id ) ";
                break;
            default:
                sAgrup = "";
        }
        try {

            String sql = "SELECT t.* FROM (select "
                    + "cl_modulo.arb_descripcion mod_descripcion," //expre1
                    + "cl_taller.arb_descripcion tal_descripcion," //expre2
                    + "cl_seccion.sec_id," //expre3
                    + "cl_seccion.sec_codigo," //expre4
                    + "cl_seccion.sec_nombre," //expre5
                    + "cl_seccion.sec_finicio," //expre6
                    + "ac_docente.doc_id," //expre7
                    + "ac_docente.doc_nombre, " //expre8
                    + "cl_seccion.sec_grup_id, " //expre9
                    + "cl_seccion.sec_fprorroga fprorroga, " //expre11
                    + "cl_seccion.sec_obs_prorroga obs_prorroga " //expre12
                    + "from  cl_seccion "
                    + "inner join cl_arbol_aperturado cl_taller_aperturado on cl_taller_aperturado.arbape_id=cl_seccion.arbape_id "
                    + "inner join cl_arbol_academico cl_taller on cl_taller.arb_id=cl_taller_aperturado.arb_id "
                    + "inner join cl_arbol_academico cl_curso on cl_curso.arb_id=cl_taller.arb_id_padre "
                    + "inner join cl_arbol_academico cl_modulo on cl_modulo.arb_id=cl_curso.arb_id_padre "
                    + "inner join cl_horaria on cl_horaria.sec_id=cl_seccion.sec_id "
                    + "inner join ac_docente on ac_docente.doc_id=cl_horaria.doc_id "
                    + "where cl_taller_aperturado.arbape_aperturado='1' "
                    + "and cl_taller_aperturado.arbape_activo='1' "
                    + "and cl_taller_aperturado.arbape_vigente='1' "
                    + "and cl_taller.arb_activo='1' "
                    + "and cl_curso.arb_activo='1' "
                    + "and cl_modulo.arb_activo='1' "
                    + "and cl_seccion.sec_activo='1' "
                    + "and cl_horaria.hor_activo='1' "
                    + "and ac_docente.doc_id=:v_arb_id "
                    + sAgrup
                    + "group by ac_docente.doc_id, cl_seccion.sec_id "
                    + "order by cl_seccion.sec_finicio desc,cl_modulo.arb_id) t";
            //System.out.println( "consulta:" + sql );
            lista = this.getSession().createSQLQuery( sql ).setInteger( "v_arb_id", iDocId ).list();
            int conta = 1;
            /*
             while ( lista.iterator().next()) {
             BeanReporte curso = new BeanReporte();
             curso.setExpr10(String.valueOf(conta) );
             curso.setExpr3(rs.getInt( "sec_id" ) );
             curso.setExpr9(rs.getInt( "sec_grup_id" ) );
             curso.setExpr1(rs.getString( "mod_descripcion" ) );
             curso.setExpr2( rs.getString( "tal_descripcion" ) );
             curso.setExpr4(rs.getString( "sec_codigo" ) );
             curso.setExpr5(rs.getString( "sec_nombre" ) );
             curso.setExpr6(rs.getDate( "sec_finicio" ) );
             listaCurso.add( curso );
             conta++;
             }*/
            Object[] arrObj;
            for ( int i = 0; i < lista.size(); i++ ) {
                arrObj = (Object[])lista.get( i );
                BeanReporte curso = new BeanReporte();
                curso.setExpr10( String.valueOf( conta ) );
                curso.setExpr3( String.valueOf( arrObj[2] ) );
                curso.setExpr9( String.valueOf( arrObj[8] ) );
                curso.setExpr1( String.valueOf( arrObj[0] ) );
                curso.setExpr2( String.valueOf( arrObj[1] ) );
                curso.setExpr4( String.valueOf( arrObj[3] ) );
                curso.setExpr5( String.valueOf( arrObj[4] ) );
                curso.setExpr6( String.valueOf( arrObj[5] ) );
                if(arrObj[9]==null){
                curso.setExpr11("");
                }else{
                curso.setExpr11( String.valueOf( arrObj[9] ) );    
                }
                
                curso.setExpr12( String.valueOf( arrObj[10] ) );
                listaCurso.add( curso );
                conta++;
            }
        } catch ( Exception e ) {
            listaCurso = new ArrayList<BeanReporte>();
            System.out.println( "listarCursosPorDocente -> " + e );
        }
        return listaCurso;
    }

    @Override
    public List traerSeccionesValidasPorModuloId( int iArbModId, int iAluId, int iArbCurId ) {
        String sQuery;
        SQLQuery sqlQuery;
        List lista;

        sQuery = "select * from ("
                + "SELECT "
                + "acur.arb_id cur_id, "//0
                + "atal.arb_id tall_id, "//1
                + "s.arbape_id, "//2
                + "s.sec_id, "//3
                + "s.sec_finicio,"//4
                + "(SELECT n.not_nota FROM cl_nota n WHERE n.alu_id=:p_alu_id AND n.sec_id=s.sec_id AND n.not_tipo='034002') promedio, "//5
                + "aape.arbape_nro_horas num_horas,"//6
                + "acur.arb_descripcion nom_curso, "//7
                + "s.sec_ffin "//8
                + "FROM cl_alumno a "
                + "INNER JOIN cl_matricula m ON(a.alu_id=m.alu_id) "
                + "INNER JOIN cl_matricula_taller mt ON(m.mat_id=mt.mat_id) "
                + "INNER JOIN cl_seccion s ON(mt.sec_id=s.sec_id) "
                + "INNER JOIN cl_arbol_academico atal ON(s.arb_id=atal.arb_id) "
                + "INNER JOIN cl_arbol_academico acur ON(atal.arb_id_padre=acur.arb_id) "
                + "INNER JOIN cl_arbol_academico amod ON(acur.arb_id_padre=amod.arb_id) "
                + "INNER JOIN cl_arbol_aperturado aape ON(s.arbape_id=aape.arbape_id) "
                + "WHERE a.alu_id = :p_alu_id AND amod.arb_id=:p_arb_mod_id AND m.mat_tipo='022001' and m.mat_activo=1 ";
        if ( iArbCurId != 0 ) {
            sQuery += " AND acur.arb_id=:p_cur_id ";
        }
        sQuery += "ORDER BY cur_id,s.sec_finicio DESC "
                + ") t "
                + "GROUP BY t.cur_id";
        sqlQuery = this.getSession().createSQLQuery( sQuery );
        sqlQuery.setInteger( "p_arb_mod_id", iArbModId ).setInteger( "p_alu_id", iAluId );
        if ( iArbCurId != 0 ) {
            sqlQuery.setInteger( "p_cur_id", iArbCurId );
        }
        lista = sqlQuery.list();
        return lista;
    }

    @Override
    public int traerAreaxSeccionId( int secId ) {
        String sQuery;
        SQLQuery sqlQuery;
        int areaId = 0;

        sQuery = "SELECT a.arb_id FROM cl_arbol_academico a "
                + "WHERE a.arb_id="
                + "(SELECT aa.arb_id_padre FROM cl_arbol_academico aa "
                + "WHERE aa.arb_id= "
                + "(SELECT aaa.arb_id_padre FROM cl_arbol_academico aaa "
                + "WHERE aaa.arb_id= "
                + "(SELECT aaaa.arb_id_padre FROM cl_seccion sc "
                + "INNER JOIN cl_arbol_aperturado ap ON(sc.arbape_id=ap.arbape_id) "
                + "INNER JOIN cl_arbol_academico aaaa ON(aaaa.arb_id=ap.arb_id) "
                + "WHERE sc.sec_id= :secId)))";
        sqlQuery = this.getSession().createSQLQuery( sQuery );
        sqlQuery.setInteger( "secId", secId );

        areaId = Integer.parseInt( sqlQuery.uniqueResult().toString() );
        return areaId;
    }

    @Override
    public ClSeccionGrupo traerClSeccionGrupoXId( int iIdSecGrup ) {
        Criteria criteria;
        ClSeccionGrupo clSecGrup;
        try {
            criteria = this.getSession().createCriteria( ClSeccionGrupo.class );
            clSecGrup = (ClSeccionGrupo)criteria.add( Restrictions.eq( "secGrupoId", iIdSecGrup ) ).
                    add( Restrictions.eq( "activo", "1" ) ).uniqueResult();
        } catch ( Exception ex ) {
            clSecGrup = null;
            ex.printStackTrace();
        }
        return clSecGrup;
    }

    @Override
    public void actualizarSeccionGrupo( ClSeccionGrupo clSecGrup ) {
        this.getHibernateTemplate().merge( clSecGrup );
    }

    @Override
    public List<ClSeccionGrupo> listarSeccionGrupos( String sNomGrup ) {
        Criteria criteria;
        List<ClSeccionGrupo> lstClSecGrup;
        try {
            criteria = this.getSession().createCriteria( ClSeccionGrupo.class );
            if ( !sNomGrup.isEmpty() ) {
                criteria.add( Restrictions.like( "nomSecGrupo", "%" + sNomGrup + "%" ) );
            }
            lstClSecGrup = criteria.add( Restrictions.eq( "activo", "1" ) ).list();
        } catch ( Exception ex ) {
            lstClSecGrup = new ArrayList<ClSeccionGrupo>();
        }
        return lstClSecGrup;
    }

    @Override
    public ClSeccionGrupo traerClSeccionGrupo( ClSeccion clSeccion ) {
        ClSeccionGrupo secGrup = null;
//        ClSeccionGrupoSeccion secGrupSec;
//        Criteria criteria;
//        try {
//            criteria = this.getSession().createCriteria( ClSeccionGrupoSeccion.class );
//            secGrupSec = (ClSeccionGrupoSeccion) criteria.add( Restrictions.eq( "activo", 1 ) ).
//                    add( Restrictions.eq( "clSeccion.secId", clSeccion.getSecId() ) ).
//                    uniqueResult();
//            if( secGrupSec != null ){
//                secGrup = secGrupSec.getClSeccionGrupo();
//            }
//        } catch ( Exception ex ) {
//            ex.printStackTrace();
//            secGrup = null;
//        }
        return secGrup;
    }

    @Override
    public void insertarSeccionGrupo( ClSeccionGrupo clSecGrupo ) {
        this.getHibernateTemplate().save( clSecGrupo );
    }

    @Override
    public ClSeccionGrupo traerClSeccionGrupoXDescripcion( String sDescSecGrup ) {
        List<ClSeccionGrupo> lstTmp;
        lstTmp = this.getSession().createCriteria( ClSeccionGrupo.class ).
                add( Restrictions.eq( "activo", "1" ) ).
                add( Restrictions.eq( "nomSecGrupo", sDescSecGrup ) ).
                list();
        if ( lstTmp.isEmpty() ) {
            return null;
        } else {
            return lstTmp.get( 0 );
        }
    }

    @Override
    public void registrarSeccionesPrecedentes( ClSeccion secTmp, List<ClSeccion> lstSeccionesPrecedentesNuev ) {
        Map<Integer, ClSeccion> hmSecPrecAct;
        Map<Integer, ClSeccion> hmSecPrecNuev;
        List<ClSeccion> lstSeccionesPrecedentesAct;

        hmSecPrecNuev = new HashMap<Integer, ClSeccion>();
        for ( ClSeccion clSeccionNuev : lstSeccionesPrecedentesNuev ) {
            hmSecPrecNuev.put( clSeccionNuev.getSecId(), clSeccionNuev );
        }
        hmSecPrecAct = new HashMap<Integer, ClSeccion>();
        lstSeccionesPrecedentesAct = listarSeccionesPrecedentes( secTmp.getSecId() );
        for ( ClSeccion clSeccionAct : lstSeccionesPrecedentesAct ) {
            hmSecPrecAct.put( clSeccionAct.getSecId(), clSeccionAct );
        }

        for ( ClSeccion clSeccionAct : lstSeccionesPrecedentesAct ) {
            if ( !hmSecPrecNuev.containsKey( clSeccionAct.getSecId() ) ) {
                clSeccionAct.setSecIdContinuacion( null );
                this.getHibernateTemplate().merge( clSeccionAct );
            }
        }
        for ( ClSeccion clSeccionNuev : lstSeccionesPrecedentesNuev ) {
            if ( !hmSecPrecAct.containsKey( clSeccionNuev.getSecId() ) ) {
                clSeccionNuev.setSecIdContinuacion( secTmp.getSecId() );
                this.getHibernateTemplate().merge( clSeccionNuev );
            }
        }

    }

    @Override
    public List<ClSeccion> listarSeccionesPrecedentes( int iSecId ) {
        Criteria criteria;
        List<ClSeccion> lstSeccPreced;

        lstSeccPreced = new ArrayList<ClSeccion>();
        try {
            criteria = this.getSession().createCriteria( ClSeccion.class );
            lstSeccPreced = criteria.add( Restrictions.eq( "secIdContinuacion", iSecId ) ).add( Restrictions.eq( "secActivo", "1" ) ).list();
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
        return lstSeccPreced;
    }

    @Override
    public void actualizarFechaProrroga( Date fechaPro, String sFechaObservacion, int iAlutarId ) {
        String hqlUpdate = "update ClSeccion "
                + "set secFProrroga = :v_prorroga, secObsProrroga= :v_observacion "
                + "where secId = :id";

        this.getSession().createQuery( hqlUpdate ).
                setDate( "v_prorroga", fechaPro ).
                setString( "v_observacion", sFechaObservacion ).
                setInteger( "id", iAlutarId ).executeUpdate();
    }
}
