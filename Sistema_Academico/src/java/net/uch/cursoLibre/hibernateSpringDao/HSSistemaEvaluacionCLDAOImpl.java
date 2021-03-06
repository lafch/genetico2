/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.hibernateSpringDao;

import java.util.ArrayList;
import java.util.List;
import net.uch.mapping.ClArbolAperturado;
import net.uch.mapping.ClSeccion;
import net.uch.mapping.ClSisEvaDetalle;
import net.uch.mapping.ClSisEvaPersonalizado;
import net.uch.mapping.ClSisEvaluacion;
import net.uch.util.CommonDAO;
import net.uch.util.CommonWeb;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author cesardl
 */
public class HSSistemaEvaluacionCLDAOImpl extends HibernateDaoSupport implements HSSistemaEvaluacionCLDAO {

    @Override
    public ClSisEvaPersonalizado seleccionarSisEvaPerXSecId( int iSecId ) {
        String sQuery;
        ClSisEvaPersonalizado sisEvaPer;

        sQuery = "SELECT sep.* FROM cl_seccion s "
                + "INNER JOIN cl_arbol_aperturado aape ON(s.arbape_id=aape.arbape_id) "
                + "INNER JOIN cl_sis_evaluacion se ON(aape.siseva_id = se.siseva_id) "
                + "INNER JOIN cl_sis_eva_detalle sed ON(se.siseva_id=sed.siseva_id) "
                + "INNER JOIN cl_sis_eva_personalizado sep ON(sed.siseva_detalle_id = sep.siseva_detalle_id AND aape.arbape_id=sep.arbape_id) "
                + "WHERE s.sec_id=:p_sec_id and sed.siseva_detalle_tipo=2  AND sep.siseva_per_activo=1";
        sisEvaPer = (ClSisEvaPersonalizado) this.getSession().
                createSQLQuery( sQuery ).
                addEntity( ClSisEvaPersonalizado.class ).
                setInteger( "p_sec_id", iSecId ).uniqueResult();
        return sisEvaPer;
    }

    @Override
    public List<ClSisEvaDetalle> seleccionarSisEvaDet_ClSisEva( int siseva_id ) {
        Criteria c = this.getSession().createCriteria( ClSisEvaDetalle.class ).
                add( Restrictions.eq( "sisevaDetalleActivo", "1" ) ).
                add( Restrictions.eq( "clSisEvaluacion.sisevaId", siseva_id ) );
        return c.list();
    }

    @Override
    public ClSisEvaDetalle seleccionarSisEvaDetetallePorId( int iSisEvaDetId ) {//iSisEvaDetId
        Criteria c = this.getSession().createCriteria( ClSisEvaDetalle.class ).
                add( Restrictions.eq( "sisevaDetalleActivo", "1" ) ).
                add( Restrictions.eq( "sisevaDetalleId", iSisEvaDetId ) );
        return (ClSisEvaDetalle) c.uniqueResult();
    }

    @Override
    public List<ClSisEvaPersonalizado> seleccionarSisEvaPer_ClTalape( ClArbolAperturado talape ) {
        Criteria c = this.getSession().createCriteria( ClSisEvaPersonalizado.class, "sisevaper" ).
                createCriteria( "sisevaper.clSisEvaDetalle", "sisevadet" ).
                createCriteria( "sisevadet.clSisEvaluacion", "siseva" ).
                add( Restrictions.eq( "sisevadet.sisevaDetalleActivo", "1" ) ).
                add( Restrictions.eq( "sisevaper.sisevaPerActivo", "1" ) ).
                add( Restrictions.eq( "siseva.sisevaActivo", "1" ) ).
                add( Restrictions.eq( "sisevaper.clArbolAperturado.arbapeId", talape.getArbapeId() ) ).
                add( Restrictions.eq( "siseva.sisevaId", talape.getClSisEvaluacion().getSisevaId() ) ).
                addOrder( Order.asc( "sisevaper.sisevaPerOrden" ) );

        return c.list();
    }

    @Override
    public void insertarActualizarSistemaEvaluacion( ClSisEvaluacion siseva ) {
        this.getHibernateTemplate().saveOrUpdate( siseva );
    }

    @Override
    public ClSisEvaluacion seleccionarSistemaEvaluacion( int sisevaId ) {
        return (ClSisEvaluacion) this.getSession().createCriteria( ClSisEvaluacion.class ).
                add( Restrictions.eq( "sisevaActivo", "1" ) ).
                add( Restrictions.eq( "sisevaId", sisevaId ) ).uniqueResult();
    }

    @Override
    public void eliminarSistemasEvaluacionDetalle( List<Integer> sisevaIds ) {
        String hqlUpdate = "update ClSisEvaDetalle "
                + "set sisevaDetalleActivo = '0' "
                + "where sisevaDetalleId in (:v_id)";

        this.getSession().createQuery( hqlUpdate ).
                setParameterList( "v_id", sisevaIds ).executeUpdate();
    }

    @Override
    public List<ClSisEvaluacion> seleccionarSistemasEvaluacion() {
        return this.getSession().createCriteria( ClSisEvaluacion.class ).
                add( Restrictions.eq( "sisevaActivo", "1" ) ).list();
    }

    @Override
    public List<ClSisEvaluacion> seleccionarSistemasEvaluacion( String nombre ) {
        return this.getSession().createCriteria( ClSisEvaluacion.class ).
                add( Restrictions.eq( "sisevaActivo", "1" ) ).
                add( Restrictions.like( "sisevaNombre", "%" + nombre + "%" ) ).list();
    }

    @Override
    public void insertarActualizar_SisEvaPersonalizado( List<ClSisEvaPersonalizado> lPer ) throws DataAccessException {
        this.getHibernateTemplate().saveOrUpdateAll( lPer );
    }

    @Override
    public void eliminarSistemaEvaluacionPersonalizado( int id ) throws DataAccessException {
        String hql = "update ClSisEvaPersonalizado set sisevaPerActivo=:activo where sisevaPerId= :id";

        this.getSession().createQuery( hql ).setString( "id", "" + id ).
                setString( "activo", "0" ).executeUpdate();
    }

    @Override
    public List listarSisEvaDetallePorSecId( int iSecId ) {
        List lista = new ArrayList();
        String sQuery = "SELECT sed.* FROM cl_sis_evaluacion se "
                + "INNER JOIN cl_sis_eva_detalle sed ON(se.siseva_id=sed.siseva_id) "
                + "INNER JOIN cl_arbol_aperturado aape ON(se.siseva_id=aape.siseva_id) "
                + "INNER JOIN cl_seccion s ON(aape.arbape_id=s.arbape_id) "
                + "WHERE s.sec_id=:p_sec_id AND se.siseva_activo=1 AND sed.siseva_detalle_activo=1 "
                + "AND aape.arbape_activo=1 AND s.sec_activo=1 AND sed.siseva_detalle_tipo=1";
        try {
            lista = this.getSession().createSQLQuery( sQuery ).setInteger( "p_sec_id", iSecId ).list();
        } catch ( Exception ex ) {
            System.out.println( "EX MSJ " + ex.getMessage() );
            System.out.println( "EX " + ex );
            ex.printStackTrace();
        }

        return lista;
    }
    
    @Override
    public List listarSisEvaDetallePorSecAsig( int iSecId ) {
        List lista = new ArrayList();
        String sQuery = "SELECT sed.* FROM cl_sis_evaluacion se "
                + "INNER JOIN cl_sis_eva_detalle sed ON(se.siseva_id=sed.siseva_id) "
                + "INNER JOIN cl_arbol_aperturado aape ON(se.siseva_id=aape.siseva_id) "
                + "INNER JOIN cl_seccion s ON(aape.arbape_id=s.arbape_id) "
                + "WHERE s.sec_id=:p_sec_id AND se.siseva_activo=1 AND sed.siseva_detalle_activo=1 "
                + "AND aape.arbape_activo=1 AND s.sec_activo=1 AND sed.siseva_detalle_tipo=1";
        try {
            lista = this.getSession().createSQLQuery( sQuery ).setInteger( "p_sec_id", iSecId ).list();
        } catch ( Exception ex ) {
            System.out.println( "EX MSJ " + ex.getMessage() );
            System.out.println( "EX " + ex );
            ex.printStackTrace();
        }

        return lista;
    }

    @Override
    public List listarSistemaEvaluacionPorCurso( int arbape_id ) {
        List lista;
        String sQuery = "SELECT sep.siseva_per_id, se.siseva_codigo, sep.siseva_per_nombre, sep.siseva_per_peso, "
                + "sep.siseva_per_orden, sep.siseva_per_exa_semana "
                + "FROM cl_arbol_aperturado aap "
                + "INNER JOIN cl_sis_evaluacion se ON(aap.siseva_id=se.siseva_id) "
                + "INNER JOIN cl_sis_eva_detalle sed ON(se.siseva_id=sed.siseva_id) "
                + "INNER JOIN cl_sis_eva_personalizado sep ON(sed.siseva_detalle_id=sep.siseva_detalle_id AND aap.arbape_id=sep.arbape_id) "
                + "WHERE aap.arbape_id=:v_arbape_id AND sep.siseva_per_activo=1  "
                + "AND sed.siseva_detalle_activo=1 AND se.siseva_activo=1 AND aap.arbape_activo=1";
//        lista = this.getSession().createQuery(hqlQuery).setInteger("v_arbape_id", arbape_id).list();
        lista = this.getSession().createSQLQuery( sQuery ).setInteger( "v_arbape_id", arbape_id ).list();

        return lista;
    }

    @Override
    public List listarSistemaEvaPersonPorSeccionYDet( int iSecId, int iIdAsignatura ) {
        Query query;
        List lista = new ArrayList();
        String sQuery = "SELECT sep.siseva_per_id, siseva_detalle_codigo, sep.siseva_detalle_id, siseva_detalle_nombre, siseva_detalle_peso, "
                + "siseva_detalle_susti,siseva_detalle_tipo, siseva_per_nombre, siseva_per_exa_semana, siseva_per_peso, sep.siseva_codigo,sep.siseva_per_peso "
                + "FROM  cl_seccion s "
                + "INNER JOIN cl_arbol_aperturado aape ON(s.arbape_id=aape.arbape_id) "
                + "INNER JOIN cl_sis_evaluacion se ON(aape.siseva_id=se.siseva_id) "
                + "INNER JOIN cl_sis_eva_detalle sed ON(se.siseva_id=sed.siseva_id) "
                + "INNER JOIN cl_sis_eva_personalizado sep ON(sed.siseva_detalle_id=sep.siseva_detalle_id AND aape.arbape_id=sep.arbape_id) "
                + "WHERE s.sec_id=:p_sec_id "
                + "AND se.siseva_activo='1'  "
                + "AND sed.siseva_detalle_activo='1'  "
                + "AND sep.siseva_per_activo='1' ";
        if ( iIdAsignatura != -1 ) {
            sQuery += " AND sed.siseva_detalle_id=:p_siseva_det_id ";
        }
        sQuery += "ORDER BY sed.siseva_detalle_tipo, sep.siseva_detalle_id, sep.siseva_per_exa_semana";
        try {
            query = this.getSession().
                    createSQLQuery( sQuery ).
                    setInteger( "p_sec_id", iSecId );
            if ( iIdAsignatura != -1 ) {
                query.setInteger( "p_siseva_det_id", iIdAsignatura );
            }
            lista = query.list();
        } catch ( Exception ex ) {
            System.out.println( "EX MSJ " + ex.getMessage() );
            System.out.println( "EX " + ex );
            ex.printStackTrace();
        }

        return lista;
    }

    @Override
    public ClSisEvaPersonalizado seleccionarSisEvaPersonalizadoPorId( int iSisEvaPerId ) {
        return (ClSisEvaPersonalizado) this.getSession().load( ClSisEvaPersonalizado.class, iSisEvaPerId );
    }

    @Override
    public List<ClSisEvaPersonalizado> listarSisEvaPerPlantilla( int iSisevaId, int iSisevaDetId ) {
        String sQuery;
        SQLQuery query;
        ClSisEvaDetalle sed;
        ClSisEvaPersonalizado sep;
        Object[] aobjFila;
        List lstSisEvaPerPlantObj;
        List<ClSisEvaPersonalizado> lstSisEvaPerPlantilla;
        lstSisEvaPerPlantilla = new ArrayList<ClSisEvaPersonalizado>();
        try {
            sQuery = "SELECT sepp.* FROM cl_sis_evaluacion se "
                    + "INNER JOIN cl_sis_eva_detalle sed ON(se.siseva_id=sed.siseva_id) "
                    + "INNER JOIN cl_sis_eva_personalizado_plantilla sepp ON(sed.siseva_detalle_id=sepp.siseva_detalle_id) "
                    + "WHERE se.siseva_id=:siseva_id  AND siseva_detalle_tipo=1 AND sepp.siseva_per_activo=1 ";
            if ( iSisevaDetId != 0 ) {
                sQuery += "AND sed.siseva_detalle_id=:siseva_det_id ";
            }
            sQuery += "ORDER BY sepp.siseva_per_orden;";
            query = this.getSession().createSQLQuery( sQuery );
            query.setInteger( "siseva_id", iSisevaId );
            if ( iSisevaDetId != 0 ) {
                query.setInteger( "siseva_det_id", iSisevaDetId );
            }
            lstSisEvaPerPlantObj = query.list();
            for ( Object obj : lstSisEvaPerPlantObj ) {
                aobjFila = (Object[]) obj;
                sep = new ClSisEvaPersonalizado();
                sep.setSisevaPerId( CommonWeb.parseObjToInt( aobjFila[0] ) );
                sep.setSisevaPerNombre( CommonWeb.parseObjToString( aobjFila[1] ) );
                sep.setSisevaPerExaSemana( CommonWeb.parseObjToString( aobjFila[2] ) );
                sed = CommonDAO.getClSistemaEvaluacionDAO().seleccionarSisEvaDetetallePorId( CommonWeb.parseObjToInt( aobjFila[3] ) );
                sep.setClSisEvaDetalle( sed );
                sep.setSisevaCodigo( CommonWeb.parseObjToString( aobjFila[4] ) );
                sep.setSisevaPerPeso( Double.parseDouble( aobjFila[5] + "" ) );
                sep.setSisevaPerOrden( CommonWeb.parseObjToInt( aobjFila[6] ) );
                sep.setSisevaPerActivo( "1" );
                lstSisEvaPerPlantilla.add( sep );
            }

        } catch ( Exception ex ) {
            ex.printStackTrace();
            lstSisEvaPerPlantilla = new ArrayList<ClSisEvaPersonalizado>();
        }

        return lstSisEvaPerPlantilla;
    }

    @Override
    public int eliminarSistemaEvaluacionPerPlant( int iIdSisEvaPerPlant ) {
        String sQuery = "update cl_sis_eva_personalizado_plantilla set siseva_per_activo=:activo where siseva_per_id= :id";
        SQLQuery query = this.getSession().createSQLQuery( sQuery );
        return query.setInteger( "id", iIdSisEvaPerPlant ).
                setString( "activo", "0" ).executeUpdate();
    }

    @Override
    public int eliminarSistemaEvaluacion( int iIdSisEva ) {
        int iRet = 0;
        String sQuery;
        SQLQuery query;
        if ( iIdSisEva > 0 ) {
            try {
                sQuery = "update cl_sis_evaluacion set siseva_activo=:activo where siseva_id= :id";
                query = this.getSession().createSQLQuery( sQuery );
                iRet = query.setInteger( "id", iIdSisEva ).
                        setString( "activo", "0" ).executeUpdate();
            } catch ( Exception ex ) {
                ex.printStackTrace();
            }
        }

        return iRet;
    }
}
