package net.uch.cursoLibre.hibernateSpringDao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import net.uch.cursoLibre.managedBeans.beans.BeanClEstructuraPagosDetalleSecuencia;
import net.uch.mapping.ClAlumnoTarifa;
import net.uch.mapping.ClArbolAcademico;
import net.uch.mapping.ClEstructuraPagos;
import net.uch.mapping.ClEstructuraPagosDetalle;
import net.uch.mapping.base.BaseClEstructuraPagosDetalle;
import net.uch.util.CommonDAO;
import net.uch.util.CommonWeb;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HSEstructuraPagoDAOImpl extends HibernateDaoSupport implements HSEstructuraPagoDAO {

    @Override
    public List seleccionarEstructuras( String descripcion, int arb_id ) throws Exception {
        Criteria criteria = this.getSession().createCriteria( ClEstructuraPagos.class ).
                add( Restrictions.eq( "estpagActivo", "1" ) ).
                add( Restrictions.like( "estpagNombre", "%" + descripcion + "%" ) );
        /*
         * if (mod_id > 0) { criteria.add(Restrictions.eq("clModulo.modId",
         * mod_id)); }
         */
        System.out.println( "Primero: " + arb_id );
        if ( arb_id > 0 ) {
            System.out.println( "Segundo: " + arb_id );
            criteria.add( Restrictions.eq( "clArbolAcademico.arbId", arb_id ) );
        }

        return criteria.list();
    }

    @Override
    public List seleccionarDetalle( int estpag_id ) throws Exception {
        return this.getSession().createCriteria( ClEstructuraPagosDetalle.class ).
                add( Restrictions.eq( "estpagdetActivo", "1" ) ).
                add( Restrictions.eq( "estpagdetVisible", "1" ) ).
                add( Restrictions.eq( "clEstructuraPagos.estpagId", estpag_id ) ).list();
    }

    @Override
    public void insertarEstructuraPagos( ClEstructuraPagos estructura )
            throws Exception {
        this.getHibernateTemplate().save( estructura );
    }

    @Override
    public void insertarEstructuraPagoDetalle( ClEstructuraPagosDetalle estDetalle )
            throws Exception {
        this.getHibernateTemplate().save( estDetalle );
    }

    @Override
    public void actualizarEstructuraPagoDetalle( ClEstructuraPagosDetalle estDetalle )
            throws Exception {
        this.getHibernateTemplate().update( estDetalle );
    }

    @Override
    public void actualizarEstructuraPagos( ClEstructuraPagos estructura )
            throws Exception {
        this.getHibernateTemplate().update( estructura );
    }

    @Override
    public List verificarEliminar( int estpag_id ) throws Exception {
        return this.getSession().createCriteria( ClAlumnoTarifa.class, "ClAlumnoTarifa" ).
                createCriteria( "ClAlumnoTarifa.clEstructuraPagosDetalle", "ClEstructuraPagosDetalle" ).
                add( Restrictions.eq( "ClAlumnoTarifa.alutarActivo", "1" ) ).
                add( Restrictions.eq( "ClEstructuraPagosDetalle.clEstructuraPagos.estpagId", estpag_id ) ).list();
    }

    @Override
    public void eliminarEstructuraPagos( int estpag_id )
            throws Exception {
        String hqlUpdate = "update ClEstructuraPagos set estpagActivo = :newName where estpagId = :oldName";
        this.getSession().createQuery( hqlUpdate ).
                setString( "newName", "0" ).
                setInteger( "oldName", estpag_id ).
                executeUpdate();
    }

    @Override
    public ClEstructuraPagos buscarEstructuraPagos( int estpag_id ) throws Exception {
        return (ClEstructuraPagos) this.getSession().
                createCriteria( ClEstructuraPagos.class ).
                add( Restrictions.eq( "estpagId", estpag_id ) ).
                list().get( 0 );
    }

    @Override
    public List verificarEliminarDetalle( int estpagdet_id ) throws Exception {
        return this.getSession().
                createCriteria( ClAlumnoTarifa.class ).
                add( Restrictions.eq( "alutarActivo", "1" ) ).
                add( Restrictions.eq( "clEstructuraPagosDetalle.estpagdetId", estpagdet_id ) ).list();
    }

    @Override
    public void eliminarEstructuraPagosDetalle( int estpagdet_id ) throws Exception {
        String hqlUpdate = "update ClEstructuraPagosDetalle set estpagdetVisible = :newName where estpagdetId = :oldName";
        this.getSession().createQuery( hqlUpdate ).
                setString( "newName", "0" ).
                setString( "oldName", "" + estpagdet_id ).executeUpdate();
    }

    @Override
    public List<ClEstructuraPagos> seleccionarEstructurasXModulo( int arbId ) {

        return this.getSession().createCriteria( ClEstructuraPagos.class ).
                add( Restrictions.eq( "estpagActivo", "1" ) ).
                add( Restrictions.eq( "clArbolAcademico.arbId", arbId ) ).list();
    }

    @Override
    public List seleccionarEstructurasXTaller( int tal_id ) {
        System.out.println( "Taller_id: " + tal_id );
        return this.getSession().createCriteria( ClEstructuraPagos.class, "estpag" ).
                createCriteria( "estpag.clEstructuraPagosDetalles", "estpagdet" ).
                add( Restrictions.eq( "estpag.estpagActivo", "1" ) ).
                add( Restrictions.eq( "estpagdet.estpagdetActivo", "1" ) ).
                add( Restrictions.eq( "estpagdet.clArbolAcademico.arbId", tal_id ) ).list();
    }

    @Override
    public List<ClEstructuraPagosDetalle> listaEstructurasExistentes( int mod_id, int estpag_id, int tal_id )
            throws DataAccessResourceFailureException, HibernateException, IllegalStateException {

        Criteria c = this.getSession().createCriteria( ClEstructuraPagosDetalle.class, "estpagdet" ).
                createCriteria( "estpagdet.clEstructuraPagos", "estpag" ).
                add( Restrictions.eq( "estpag.estpagActivo", "1" ) ).
                add( Restrictions.eq( "estpagdet.estpagdetActivo", "1" ) ).
                add( Restrictions.eq( "estpag.estpagId", estpag_id ) ).
                add( Restrictions.eq( "estpag.clModulo.modId", mod_id ) );

        if ( tal_id != -1 ) {
            c.add( Restrictions.eq( "estpagdet.talId", tal_id ) );
        }

        return c.list();
    }

    @Override
    public List<ClAlumnoTarifa> listaAlumnoTarifaExistentes( int aluId, int modId, int estpag_id )
            throws DataAccessResourceFailureException, HibernateException, IllegalStateException {

        Criteria c = this.getSession().createCriteria( ClAlumnoTarifa.class, "alutar" ).
                createCriteria( "alutar.clEstructuraPagosDetalle", "estpagdet" ).
                createCriteria( "estpagdet.clEstructuraPagos", "estpag" ).
                add( Restrictions.eq( "alutar.alutarActivo", "1" ) ).
                add( Restrictions.eq( "estpagdet.estpagdetActivo", "1" ) ).
                add( Restrictions.eq( "estpag.estpagActivo", "1" ) ).
                add( Restrictions.eq( "alutar.clAlumno.aluId", aluId ) ).
                add( Restrictions.eq( "estpag.estpagId", estpag_id ) ).
                add( Restrictions.eq( "estpag.clModulo.modId", modId ) );

        return c.list();
    }

    @Override
    public List<ClEstructuraPagosDetalle> listarEstructuraDetalleBloque( int estpag_id ) {
        List<ClEstructuraPagosDetalle> lista = this.getSession().createCriteria( ClEstructuraPagosDetalle.class, "estpagdet" ).
                add( Restrictions.eq( "estpagdet.estpagdetActivo", "1" ) ).
                add( Restrictions.eq( "estpagdet.estpagdetVisible", "1" ) ).
                add( Restrictions.eq( "estpagdet.clEstructuraPagos.estpagId", estpag_id ) ).
                addOrder( Order.asc( "estpagdet.estpagdetOrden" ) ).list();

        return lista;
    }
    @Override
    public List<ClEstructuraPagosDetalle> listarEstructuraDetalle(int arbId){
                List<ClEstructuraPagosDetalle> lista = this.getSession().createCriteria( ClEstructuraPagosDetalle.class, "estpagdet" ).
                add( Restrictions.eq( "estpagdet.estpagdetActivo", "1" ) ).
                add( Restrictions.eq( "estpagdet.estpagdetVisible", "1" ) ).
                add( Restrictions.eq( "estpagdet.clArbolAcadCurso.arbId", arbId ) ).
                addOrder( Order.desc("estpagdet.estpagdetMonto" ) ).setMaxResults(1).list();
        return lista;     
    }
    
    @Override
    public List<ClEstructuraPagos> seleccionarEstructurasPagos( int mod_id ) {
        List<ClEstructuraPagos> lista = seleccionarEstructurasXModulo( mod_id );
        List<ClEstructuraPagos> estructura = this.getSession().createCriteria( ClEstructuraPagos.class ).
                add( Restrictions.eq( "estpagActivo", "1" ) ).add( Restrictions.eq( "estpagTipo", "068002" ) ).addOrder( Order.asc( "estpagNombre" ) ).list();
        for ( int i = 0; i < estructura.size(); i++ ) {
            ClEstructuraPagos pago = new ClEstructuraPagos();
            pago.setClEstructuraPagosDetalles( estructura.get( i ).getClEstructuraPagosDetalles() );
            //pago.setClModulo(estructura.get(i).getClModulo());
            pago.setClArbolAcademico( estructura.get( i ).getClArbolAcademico() );
            pago.setEstpagAbrev( estructura.get( i ).getEstpagAbrev() );
            pago.setEstpagActivo( estructura.get( i ).getEstpagActivo() );
            pago.setEstpagId( estructura.get( i ).getEstpagId() );
            pago.setEstpagNombre( "* " + estructura.get( i ).getEstpagNombre() );
            pago.setEstpagTipo( estructura.get( i ).getEstpagTipo() );
            lista.add( pago );
        }

        return lista;
    }

    @Override
    public ClArbolAcademico buscarModulo( int mod_id ) throws Exception {
        return (ClArbolAcademico) this.getSession().createCriteria( ClArbolAcademico.class, "arbIdPadre" ).
                add( Restrictions.eq( "arbId", mod_id ) ).
                add( Restrictions.eq( "arbNivel", 4 ) ).
                uniqueResult();
    }

    @Override
    public ClEstructuraPagosDetalle buscarEstructuraPagosDet( int estpagdet_id ) throws Exception {
        return (ClEstructuraPagosDetalle) this.getSession().
                createCriteria( ClEstructuraPagosDetalle.class ).
                add( Restrictions.eq( "estpagdetId", estpagdet_id ) ).
                uniqueResult();
    }

    @Override
    public BeanClEstructuraPagosDetalleSecuencia buscarEstPagDetSec( int estPagDetIniId, int estPagDetContId ) {
        BeanClEstructuraPagosDetalleSecuencia epds = null;
        String sQuery;
        SQLQuery sqlQuery;
        Object[] objFila;
        List lstAux;
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );

        sQuery = "SELECT "
                + "estpagdet_sec_id, "
                + "estpagdet_id, "
                + "estpagdet_cont_id, "
                + "fecha_creacion, "
                + "activo "
                + "FROM cl_estructura_pagos_detalle_secuencia "
                + "WHERE activo=1 AND estpagdet_id=:epdi_id AND estpagdet_cont_id=:epdc_id LIMIT 1;";
        try {
            sqlQuery = this.getSession().createSQLQuery( sQuery );
            sqlQuery.setInteger( "epdi_id", estPagDetIniId );
            sqlQuery.setInteger( "epdc_id", estPagDetContId );
            lstAux = sqlQuery.list();
            for ( Object objAux : lstAux ) {
                objFila = (Object[]) objAux;
                epds = new BeanClEstructuraPagosDetalleSecuencia();
                epds.setEstpagdetSecId( CommonWeb.parseObjToInt( objFila[0] ) );
                epds.setEstpagdetIni( CommonDAO.getClEstructuraPagoDAO().buscarEstructuraPagosDet( CommonWeb.parseObjToInt( objFila[1] ) ) );
                epds.setEstpagdetCont( CommonDAO.getClEstructuraPagoDAO().buscarEstructuraPagosDet( CommonWeb.parseObjToInt( objFila[2] ) ) );
//                try {
////                    epds.setFechaCreacion( sdf.parse( CommonWeb.parseObjToString( objFila[3] ) ) );
//                    Calendar cal = Calendar.getInstance();
//                    int a, m, d, h, mi, s;
//                    String[] asFecha, asTiempo;
//                    asFecha = CommonWeb.parseObjToString( objFila[3] ).split( " " )[0].split( "-" );
//                    asTiempo = CommonWeb.parseObjToString( objFila[3] ).split( " " )[1].split( ":" );
//                    a = CommonWeb.parseObjToInt( asFecha[0] );
//                    m = CommonWeb.parseObjToInt( asFecha[1] );
//                    d = CommonWeb.parseObjToInt( asFecha[2] );
//                    h = CommonWeb.parseObjToInt( asTiempo[0] );
//                    mi = CommonWeb.parseObjToInt( asTiempo[1] );
//                    s = CommonWeb.parseObjToInt( asTiempo[2] );
//                    cal.set( a, m, d, h, mi, s );
////                    epds.setFechaCreacion( new java.sql.Date( cal.getTimeInMillis() ) );
//                } catch ( Exception ex ) {
////                    epds.setFechaCreacion( null );
//                }
                epds.setActivo( CommonWeb.parseObjToInt( objFila[4] ) );
                break;
            }
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
        return epds;
    }

    @Override
    public int insertarActualizarContinuacionEstPagDet( BeanClEstructuraPagosDetalleSecuencia epds ) throws Exception {
        String sQueryInserUpdate;
        SQLQuery sqlQuery;
        if ( epds.getEstpagdetSecId() == null ) {
            sQueryInserUpdate = "INSERT INTO cl_estructura_pagos_detalle_secuencia(estpagdet_id,estpagdet_cont_id,fecha_creacion,activo) "
                    + "VALUES(:estpagdet_id,:estpagdet_cont_id,CURRENT_TIMESTAMP,:activo)";
        } else {
            sQueryInserUpdate = "UPDATE SET estpagdet_id=:estpagdet_id,estpagdet_cont_id=:estpagdet_cont_id,activo=:activo "
                    + "WHERE estpagdet_sec_id=:estpagdet_sec_id";
        }
        sqlQuery = this.getSession().createSQLQuery( sQueryInserUpdate );
        if ( epds.getEstpagdetSecId() != null ) {
            sqlQuery.setInteger( "estpagdet_sec_id", epds.getEstpagdetSecId() );
        }
        sqlQuery.setInteger( "estpagdet_id", epds.getEstpagdetIni().getEstpagdetId() );
        sqlQuery.setInteger( "estpagdet_cont_id", epds.getEstpagdetCont().getEstpagdetId() );
        sqlQuery.setInteger( "activo", epds.getActivo() );
        return sqlQuery.executeUpdate();
    }

    @Override
    public List<BeanClEstructuraPagosDetalleSecuencia> listarEstPagDetSecuencia( int iModIniId, int estPagIniId  ) {
        BeanClEstructuraPagosDetalleSecuencia epds;
        String sQuery;
        SQLQuery sqlQuery;
        Object[] objFila;
        List lstAux;
        List<BeanClEstructuraPagosDetalleSecuencia> lstEstPagDetSec;

        sQuery = "SELECT "
                + "estpagdet_sec_id, "
                + "estpagdet_id, "
                + "estpagdet_cont_id, "
                + "fecha_creacion, "
                + "activo "
                + "FROM cl_estructura_pagos_detalle_secuencia "
                + "WHERE activo=1 ";
        if ( iModIniId != 0 ) {
            sQuery += "AND estpagdet_id IN(SELECT epd.estpagdet_id FROM cl_estructura_pagos_detalle epd "
                    + "INNER JOIN cl_estructura_pagos ep ON (epd.estpag_id=ep.estpag_id) "
                    + "WHERE ep.arb_id=:modIniId AND epd.estpagdet_activo=1 ";
            if( estPagIniId != 0 ){
                sQuery += "AND ep.estpag_id=:estPagIniId ";
            }
            sQuery += "AND ep.estpag_activo=1 ) ";
        }
//        sQuery = "SELECT "
//                + "epds.estpagdet_sec_id, "//0
//                + "aModI.arb_descripcion MOD_INI,"//1
//                + "aCurI.arb_descripcion CUR_INI,"//2
//                + "epi.estpag_nombre EST_PAG_INI,"//3
//                + "epds.estpagdet_id EST_PAG_DET_ID_INI,"//4
//                + "epdi.estpagdet_nombre EST_PAG_DET_INI,"//5
//                + "aModI.arb_descripcion MOD_CONT,"//6
//                + "aCurC.arb_descripcion CUR_CONT,"//7
//                + "epc.estpag_nombre EST_PAG_CONT,"//8
//                + "epds.estpagdet_cont_id EST_PAG_DET_ID_CONT,"//9
//                + "epdc.estpagdet_nombre EST_PAG_DET_CONT,"//10
//                + "epds.fecha_creacion "//11
//                + "FROM cl_estructura_pagos_detalle epdi "
//                + "INNER JOIN cl_estructura_pagos epi ON(epdi.estpag_id=epi.estpag_id) "
//                + "INNER JOIN cl_arbol_academico aCurI ON(epdi.arb_id=aCurI.arb_id) "
//                + "INNER JOIN cl_arbol_academico aModI ON(epi.arb_id=aModI.arb_id) "
//                + "INNER JOIN cl_estructura_pagos_detalle_secuencia epds ON(epdi.estpagdet_id=epds.estpagdet_id) "
//                + "INNER JOIN cl_estructura_pagos_detalle epdc ON(epds.estpagdet_cont_id=epdc.estpagdet_id) "
//                + "INNER JOIN cl_arbol_academico aCurC ON(epdc.arb_id=aCurC.arb_id) "
//                + "INNER JOIN cl_estructura_pagos epc ON(epdc.estpag_id=epc.estpag_id) "
//                + "INNER JOIN cl_arbol_academico aModC ON(epc.arb_id=aModC.arb_id) "
//                + "WHERE epdi.estpagdet_activo=1 AND epi.estpag_activo=1 AND aCurI.arb_activo=1"
//                + "AND epds.activo=1 AND epdc.estpagdet_activo=1 AND aCurC.arb_activo=1 AND epc.estpag_activo=1 "
//                + "ORDER BY MOD_INI,CUR_INI,EST_PAG_INI,EST_PAG_DET_INI,CUR_INI,MOD_CONT,CUR_CONT,EST_PAG_CONT,EST_PAG_DET_CONT;";
        lstEstPagDetSec = new ArrayList<BeanClEstructuraPagosDetalleSecuencia>();
        try {
            sqlQuery = this.getSession().createSQLQuery( sQuery );
            if ( iModIniId != 0 ) {
                sqlQuery.setInteger( "modIniId", iModIniId );
            }
            if( estPagIniId != 0 ){
                sqlQuery.setInteger( "estPagIniId", estPagIniId );
            }
            lstAux = sqlQuery.list();
            for ( Object objAux : lstAux ) {
                objFila = (Object[]) objAux;
                epds = new BeanClEstructuraPagosDetalleSecuencia();
                epds.setEstpagdetSecId( CommonWeb.parseObjToInt( objFila[0] ) );
                epds.setEstpagdetIni( CommonDAO.getClEstructuraPagoDAO().buscarEstructuraPagosDet( CommonWeb.parseObjToInt( objFila[1] ) ) );
                epds.setEstpagdetCont( CommonDAO.getClEstructuraPagoDAO().buscarEstructuraPagosDet( CommonWeb.parseObjToInt( objFila[2] ) ) );
//                try {
//                    Calendar cal = Calendar.getInstance();
//                    int a, m, d, h, mi, s;
//                    String[] asFecha, asTiempo;
//                    asFecha = CommonWeb.parseObjToString( objFila[3] ).split( " " )[0].split( "-" );
//                    asTiempo = CommonWeb.parseObjToString( objFila[3] ).split( " " )[1].split( ":" );
//                    a = CommonWeb.parseObjToInt( asFecha[0] );
//                    m = CommonWeb.parseObjToInt( asFecha[1] );
//                    d = CommonWeb.parseObjToInt( asFecha[2] );
//                    h = CommonWeb.parseObjToInt( asTiempo[0] );
//                    mi = CommonWeb.parseObjToInt( asTiempo[1] );
//                    s = CommonWeb.parseObjToInt( asTiempo[2] );
//                    cal.set( a, m, d, h, mi, s );
//                    epds.setFechaCreacion( new java.sql.Date( cal.getTimeInMillis() ) );
//                } catch ( Exception ex ) {
//                    epds.setFechaCreacion( null );
//                }
                epds.setActivo( 1 );
                lstEstPagDetSec.add( epds );
            }
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
        return lstEstPagDetSec;
    }
}
